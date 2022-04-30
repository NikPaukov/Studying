package com.example.lab2.validate;

public class CountResValidator implements IValidator{

    @Override
    public boolean isValid(Object res) {
        Double resString = (Double)res;
        if (resString.isNaN()) return false;
        return true;
    }
}
