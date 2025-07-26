package com.example.suivie_importBackend.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.Enum.Password;
import com.example.suivie_importBackend.Enum.Status;
import com.example.suivie_importBackend.models.UserM;
import com.example.suivie_importBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ParameterService parameterService;

    @Autowired
    JwtService jwtService;


    @Override
    public UserDetails loadUserByUsername(String token) {
        String username = jwtService.getUsernameFromToken(token);
        String audience = jwtService.getAudienceFromToken(token);

        if (!audience.equals("user")) {
            throw new UsernameNotFoundException("Audience non autorisée : " + audience);
        }

        Optional<UserM> optUser = this.userRepository.findFirstByUsernameAndStatusAndOldAndDeleted(username, Status.DEFAULT_ENABLE, Password.CHANGED, Deletion.NO);

        if (optUser.isEmpty()) {
            throw new UsernameNotFoundException(username + " n'existe pas");
        }

        UserM user = optUser.get();
        Date now = new Date();

        if (now.after(user.getDateActivity())) {
            throw new UsernameNotFoundException(username + " Inactivité détectée");
        }

        user.setDateActivity(Date.from(now.toInstant().plus(parameterService.getInactivityTime(), ChronoUnit.MINUTES)));
        user = userRepository.save(user);

        String role = "ROLE_" + user.getRole().getLibelle().toUpperCase();
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role));
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(user.getUsername(), user.getPassword(), roles);
    }
}
