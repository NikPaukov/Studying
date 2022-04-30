package com.example.lab2.Counter;

import com.example.lab2.param.processing.CountServletFormContainer;

public class CounterService implements ICounter{
    public double count(Double a, Double b, Double c, Double d){
        return (Math.cos(b) + Math.sin(Math.sqrt(a)))/(2*Math.log(c) + Math.exp(d));
    }

}
