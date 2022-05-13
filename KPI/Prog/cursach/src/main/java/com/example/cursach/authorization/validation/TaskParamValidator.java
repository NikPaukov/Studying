package com.example.cursach.authorization.validation;

import java.util.Map;

public class TaskParamValidator implements Validator{

    @Override
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg) {
        for(String key: map.keySet()){
            if(map.get(key)[0].matches("<.*>")){
                errorMsg.append("Don`t use XML tags:").append(key);
                return false;
            }
        }
        return true;
    }
}
