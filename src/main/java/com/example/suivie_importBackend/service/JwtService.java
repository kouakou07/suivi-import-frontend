package com.example.suivie_importBackend.service;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService { String secret = "CORIS";

    @Autowired
    ParameterService parameterService;

    public String generateToken(String username, String audience){

        if(audience.compareTo("user") == 0){
            Integer minuteSession = parameterService.getSessionTime();
            Date now = new Date();
            Date dateExp = Date.from(now.toInstant().plus(minuteSession, ChronoUnit.MINUTES));

            return Jwts.builder()
                    .setSubject(username)
                    .setAudience(audience)
                    .setExpiration(dateExp)
                    .signWith(SignatureAlgorithm.HS256, this.secret)
                    .compact();
        }else{
            Date now = new Date();
            Date dateExp = Date.from(now.toInstant().plus(5, ChronoUnit.MINUTES));

            return Jwts.builder()
                    .setSubject(username)
                    .setAudience(audience)
                    .setExpiration(dateExp)
                    .signWith(SignatureAlgorithm.HS256, this.secret)
                    .compact();
        }

    }


    public String getUsernameFromToken(String token){
        try{
            return Jwts.parser().
                    setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch(Exception e){
            return null;
        }

    }

    public String getAudienceFromToken(String token){
        try{
            return Jwts.parser().
                    setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getAudience();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "";
        }
    }

}
