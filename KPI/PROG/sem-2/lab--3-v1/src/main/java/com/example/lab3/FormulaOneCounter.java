package com.example.lab3;

import java.util.Map;

public class FormulaOneCounter implements Counter {
    public double[] count(double[] res, Map<String, Double> doubleParams) {
        int iter = 0;

        for (Double i = doubleParams.get("aFrom"); i < doubleParams.get("aTo"); i += doubleParams.get("aStep")) {
            for (Double j = doubleParams.get("bFrom"); j < doubleParams.get("bTo"); j += doubleParams.get("bStep")) {
                for (Double k = doubleParams.get("cFrom"); k < doubleParams.get("cTo"); k += doubleParams.get("cStep")) {
                    for (Double l = doubleParams.get("dFrom"); l < doubleParams.get("dTo"); l += doubleParams.get("dStep")) {
                        res[iter] = count(i, j, k, l);
                        iter++;
                    }
                }
            }
        }
        return res;
    }

    @Override
    public Double count(double a, double b, double c, double d) {
        return (Math.cos(b) + Math.sin(Math.sqrt(a))) / (2 * Math.log(c) + Math.exp(d));
    }
}
