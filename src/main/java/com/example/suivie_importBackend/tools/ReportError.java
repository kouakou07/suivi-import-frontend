package com.example.suivie_importBackend.tools;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ReportError {
    public static Map<String, String> getErrors(BindingResult res){
        Map<String, String> result = new HashMap<String, String>();
        for(FieldError field : res.getFieldErrors()){
            result.put(field.getField(), field.getDefaultMessage());
        }
        return result;
    }


    public static Object notFound(Object data){
        return new ResponseEntity<Object>(data, HttpStatus.NOT_FOUND);
    }
    public static Object unauthorize(Object data){
        return new ResponseEntity<Object>(data, HttpStatus.UNAUTHORIZED);
    }

    public static Object forbidden(Object data){
        return new ResponseEntity<Object>(data, HttpStatus.FORBIDDEN);
    }
}
