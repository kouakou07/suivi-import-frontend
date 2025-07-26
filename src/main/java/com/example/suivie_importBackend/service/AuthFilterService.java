package com.example.suivie_importBackend.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthFilterService implements Filter{

    @Autowired
    JwtService jwtService;

    @Autowired
    UserDetailService userDetailService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException, UsernameNotFoundException{
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String bearToken = httpRequest.getHeader("Authorization");
        String tokenName = "Bearer ";
        if(bearToken != null && bearToken.startsWith(tokenName)){
            String token = bearToken.substring(tokenName.length());
            String username = jwtService.getUsernameFromToken(token);

            if(username != null  && SecurityContextHolder.getContext().getAuthentication() == null ){
               
                UserDetails userDetail = null;
                try{
                    userDetail = userDetailService.loadUserByUsername(token);
                    UsernamePasswordAuthenticationToken usernamePassworkAuth = new UsernamePasswordAuthenticationToken(userDetail, userDetail.getUsername(), userDetail.getAuthorities());
                    usernamePassworkAuth.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(httpRequest)
                    );
                    SecurityContextHolder.getContext().setAuthentication(usernamePassworkAuth);
                }catch(UsernameNotFoundException userException){

                }
               
            }
            
        }
        filterChain.doFilter(request, response);
    }
}
