package com.example.cursach.authorization;

import com.example.cursach.authorization.validation.Validator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

public class AuthorizationUtils {


    /* Driver Code */
    public static String PasswordEncrypter(String password) {
        String encryptedpassword = null;
        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");
            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());
            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));}

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(1);
            return e.getMessage();
        }

        /* Display the unencrypted and encrypted passwords. */
        return encryptedpassword;
    }
    public static boolean validate(Map<String, String[]> map,
                            List<Validator> validators, StringBuilder errorMsg) {

        for(Validator validator: validators){
            if(!validator.validate(map, errorMsg)) return false;
        }
        return true;
    }

}
