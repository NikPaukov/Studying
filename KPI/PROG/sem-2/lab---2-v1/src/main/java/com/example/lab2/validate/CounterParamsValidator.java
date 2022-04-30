package com.example.lab2.validate;

import com.example.lab2.validate.IValidator;

public class CounterParamsValidator implements IValidator {

    public static void main(String[] args) {
        System.out.println(((0.2-0.5)/0) > 10);
    }
    @Override
    public boolean isValid(Object param) {
        if(param==null) return false;
        String paramString = (String) param;
        return paramString.matches("-?\\d+(\\.\\d+)?");
    }
}
