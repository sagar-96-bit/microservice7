package com.example.microservicedemo.employee.controller;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;
@Component
public class Utils {
    private static final String str="qwertyuibnmjkl123490765SDGMPWEYUIOZXSDFGBVlkljgono689067";
    private static Random r=new SecureRandom();
    public static String generateRandomNumb(int length){
          return generateUserIdNumber(length);
    }

    private static String generateUserIdNumber(int length) {
           StringBuilder sb=new StringBuilder();
           for (int i=0;i<length;i++){
                 sb.append(str.charAt(r.nextInt(str.length())));
           }
            return sb.toString();
    }


}
