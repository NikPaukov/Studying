package com.example.cursach.authorization.validation;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class PasswordUsernameValidation implements Validator{

    public static void main(String[] args) {
        TreeMap<Integer, Integer> set = new TreeMap();
        set.put(1, 1);
        set.put(1, 1);
        set.put(1, 1);
        set.put(1, 1);
        set.put(1, 1);
        set.put(2, 2);
        set.put(0, 2);
        System.out.println(set.toString());
    }
    @Override
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg) {
        char[] allowedChars="~`!@#$%^&*()_-+={[}]|\\:;\"'<,>.?/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmonpqrstuvwxyz"
                .toCharArray();


        char[] username = map.get("username")[0].toCharArray();
        char[] password = map.get("password")[0].toCharArray();
        Arrays.sort(password);
        Arrays.sort(username);
        StringBuilder errorSymbols = new StringBuilder("Некоректні символи:");
        for(char character: allowedChars){
        if(Arrays.binarySearch(username, character) < 0){
            errorSymbols.append(character).append(", ");
        }
        }
        for(char character: allowedChars){
            if(Arrays.binarySearch(password, character) < 0){
                errorSymbols.append(character).append(", ");
            }
        }
        Arrays.fill(username, (char) 0);
        Arrays.fill(password, (char) 0);
        if(errorSymbols.toString().equals("Некоректні символи:"))return true;
        else{
            errorMsg.append(errorSymbols.append("\n"));
            return false;
        }
    }
}
