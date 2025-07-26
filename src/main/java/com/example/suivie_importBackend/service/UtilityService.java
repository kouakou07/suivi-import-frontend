package com.example.suivie_importBackend.service;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UtilityService {

    Logger logger = LoggerFactory.getLogger(UtilityService.class);

    public String generatePassword(){
        String prefix = "LW";
        String ending = "D";
        Random random = new Random();
        int randNumber =  random.nextInt(10000, 99999);
        return prefix + String.valueOf(randNumber) + ending;
    }


}
