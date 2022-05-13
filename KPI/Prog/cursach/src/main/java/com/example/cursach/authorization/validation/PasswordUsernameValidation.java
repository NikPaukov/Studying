package com.example.cursach.authorization.validation;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class PasswordUsernameValidation implements Validator {


    @Override
    public boolean validate(Map<String, String[]> map, StringBuilder errorMsg) {


        String username = map.get("username")[0];
        String password = map.get("password")[0];
        if (username.matches("[^\\w!]+")) {
            errorMsg.append("Username contains forbidden symbols");
            return false;
        }
        if (password.matches("[^\\w!]+")) {
            errorMsg.append("Password contains forbidden symbols");
            return false;
        }

        return true;
    }
}
