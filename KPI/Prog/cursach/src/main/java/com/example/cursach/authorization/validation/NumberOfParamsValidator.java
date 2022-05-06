package com.example.cursach.authorization.validation;
import java.util.Map;
import java.util.Set;

public class NumberOfParamsValidator implements Validator {
    int number;

    public NumberOfParamsValidator(int number) {
        this.number = number;
    }

    public boolean validate(Map<String, String[]> map,     StringBuilder errorMsg) {
        if (map.size() != number) {
            errorMsg.append("Неочікувана кількість параметрів\n");
            return false;
        }
        return true;
    }
}
