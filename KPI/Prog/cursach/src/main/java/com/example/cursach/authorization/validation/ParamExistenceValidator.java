package com.example.cursach.authorization.validation;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParamExistenceValidator implements Validator{


    @Override
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg) {
        List<String> errorParams = new LinkedList<>();
        boolean valid = true;
        for(String name: map.keySet()){
            if(map.get(name).length == 0 || map.get(name)[0].isEmpty()){
                valid=false;
                errorParams.add(name);
            }
        }
        if(!valid){
            errorMsg.append("Задані пусті параметри:").append(errorParams.toString()).append("\n");
            return false;
        }
        return true;
    }
}
