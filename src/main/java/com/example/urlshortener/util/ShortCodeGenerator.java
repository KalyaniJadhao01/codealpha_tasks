package com.example.urlshortener.util;

import java.security.SecureRandom;

public class ShortCodeGenerator {
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    public static String generateCode(){

        StringBuilder code = new StringBuilder();

        for(int i=0 ; i<6 ; i++){
            code.append(
                    CHARS.charAt(random.nextInt(CHARS.length()))
            );
        }
        return code.toString();
    }
}
