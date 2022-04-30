package com.example.lab3.validators;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StepValidator implements Validator {
    String[] paramNames;

    public StepValidator(String[] paramNames) {
        this.paramNames = paramNames;
    }

    @Override
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg) {
        List<String> errorParams = new LinkedList<>();
        boolean valid = true;
        for (int i = 2; i < paramNames.length; i += 3) {
            if (
                    (Double.parseDouble(map.get(paramNames[i])[0]) == 0 ||
                            (((Double.parseDouble(map.get(paramNames[i - 1])[0]) -
                                    Double.parseDouble(map.get(paramNames[i - 2])[0])) /
                                    Double.parseDouble(map.get(paramNames[i])[0])) > 10))) {
                valid = false;
                errorParams.add(paramNames[i]);
            }
        }
        if (!valid) {
            errorMsg.append("Некоректний шаг:").append(errorParams).append("\n");
            return false;
        }
        return true;
    }
}
