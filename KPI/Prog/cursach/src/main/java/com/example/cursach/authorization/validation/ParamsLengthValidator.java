package com.example.cursach.authorization.validation;

import java.util.Map;

public class ParamsLengthValidator implements Validator{
    @Override
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg) {
        if(map.get("username")[0].length() > 16){
            errorMsg.append("Max username length: 16\n");
            return false;
        }
        if(map.get("username")[0].length() < 6){
            errorMsg.append("Min username length: 6\n");
            return false;
        }
        if(map.get("password")[0].length() < 6){
            errorMsg.append("Min password length: 6\n");
            return false;
        }
        return true;
    }
}
