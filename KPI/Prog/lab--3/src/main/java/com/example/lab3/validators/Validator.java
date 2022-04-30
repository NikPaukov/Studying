package com.example.lab3.validators;

import java.util.Map;

public interface Validator {
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg);
}
