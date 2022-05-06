package com.example.cursach.authorization.validation;
import java.util.*;

public class ParamNamesValidator implements Validator{
    String[] paramNames;

    public ParamNamesValidator(String[] paramNames) {
        this.paramNames = paramNames;
    }



    @Override
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg) {
            String[] paramNamesArr = paramNames;
            List<String> errorParams = new LinkedList<>();
            for(String name : paramNamesArr){
                if(!map.containsKey(name)){
                    errorParams.add(name);}
            }
            if(errorParams.size()!=0){
                errorMsg.append("Відсутні параметри: ").append(errorParams).append("\n");
                return false;
            }
            return true;
        }
}
