package com.example.cursach.authorization.validation;

import java.util.Map;

public class ParamsLengthValidator implements Validator{
    @Override
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg) {
        if(map.get("username")[0].length() > 16){
            errorMsg.append("Максимальна довжина імені: 16\n");
            System.out.println(11);
            return false;
        }
        if(map.get("username")[0].length() < 6){
            System.out.println(12);
            errorMsg.append("Мінімальна довжина імені: 6\n");
            return false;
        }
        if(map.get("password")[0].length() < 6){
            System.out.println(13);
            errorMsg.append("Мінімальна довжина паролю: 6\n");
            return false;
        }
        return true;
    }
}
