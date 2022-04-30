package com.example.lab3.validators;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParamToFromDiffValidator implements Validator{
    String[] paramNames;

    public ParamToFromDiffValidator(String[] paramNames) {
        this.paramNames = paramNames;
    }

    @Override
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg) {
        boolean valid = true;
        List<String> errorParams = new LinkedList<>();
        for(int i = 1; i < paramNames.length; i+=3){
            if(Double.parseDouble(map.get(paramNames[i])[0]) - Double.parseDouble(map.get(paramNames[i-1])[0]) <=0){
                errorParams.add(paramNames[i]);
                valid = false;
            }
        }
        if(!valid){
            errorMsg.append("Некоректно задані межі параметрів:").append(errorParams).append("\n");
            return false;
        }
        return true;
    }
}
