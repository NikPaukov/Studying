package com.example.lab3.validators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IsParamDoubleValidator implements Validator{



    @Override
    public boolean validate(Map<String, String[]> map,     StringBuilder errorMsg) {
        List<String> errorParams = new LinkedList<>();
        boolean valid = true;
        for(String name: map.keySet()){
            if(!map.get(name)[0].matches("-?\\d+(\\.\\d+)?")){
                errorParams.add(name);
                valid = false;}
        }
        if(!valid){
            errorMsg.append("Параметри не є числами:").append(errorParams).append("\n");
            return false;
        }
        return true;
    }
}
