package com.example.lab3.validators;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParamExistenceValidator implements Validator{

    @Override
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg) {
        List<String> errorParams = new LinkedList<>();
        boolean valid = true;
        for(String name: map.keySet()){
            if(map.get(name).length == 0){
                valid=false;
                errorParams.add(name);
            }
        }
        if(!valid){
            errorMsg.append("Задані пусті параметри:").append(errorParams).append("\n");
            return false;
        }
        return true;
    }
}
