package com.example.cursach.authorization.validation;

import java.util.Arrays;
import java.util.Map;

public class PasswordUsernameValidation implements Validator{


    @Override
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg) {
        char[] forbidden = new char[33];
        for(int i = 0; i < forbidden.length-1; i++){
            forbidden[i] = (char)i;
        }
        forbidden[32] =(char) 38;
        char[] username = map.get("username")[0].toCharArray();
        char[] password = map.get("password")[0].toCharArray();
        Arrays.sort(password);
        Arrays.sort(username);
        StringBuilder errorSymbols = new StringBuilder("Некоректні символи:");
        for(char character: forbidden){
        if(Arrays.binarySearch(username, character) > 0){
            errorSymbols.append(character).append(", ");
        }
        }
        for(char character: forbidden){
            if(Arrays.binarySearch(password, character) > 0){
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
