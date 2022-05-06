package com.example.cursach.authorization.validation;
import java.util.Map;

public interface Validator {
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg);
}
