package com.example.lab3;

import com.example.lab3.validators.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableOneParamSerivce {
    String[] paramNames;

    public TableOneParamSerivce(String[] paramNames) {
        this.paramNames = paramNames;
    }


    public boolean validate(Map<String, String[]> map,
                                  List<Validator> validators, StringBuilder errorMsg) {

        for(Validator validator: validators){
            if(!validator.validate(map, errorMsg)) return false;
        }
        return true;
    }


    public Map<String, Double> reTypeParams(Map<String, String[]> params) {
        Map<String, Double> res = new HashMap<>();
        for (String name : params.keySet()) {
            res.put(name, Double.parseDouble(params.get(name)[0]));
        }
        return res;
    }

    public int countParamsLength(Map<String, Double> params) {
        int res = 1;
        String[] paramsNames = new String[]{"a", "b", "c", "d"};
        for (String paramName : paramsNames) {
            System.out.println(res);
            res *= (int) Math.ceil((params.get(paramName + "To") -
                    params.get(paramName + "From")) /
                    params.get(paramName + "Step"));
        }
        return res;
    }

    public double[] setParamsFromRequest(HttpServletRequest request) {
        Map<String, Double> requestParams = (Map<String, Double>) request.getAttribute("params");
        double[] params = new double[paramNames.length];
        for (int i = 0; i < 12; i++) {
            params[i] = requestParams.get(paramNames[i]);
        }
        return params;
    }
    public double[] setParamsFromSession(HttpSession session) {
        Map<String, Double> requestParams = (Map<String, Double>) session.getAttribute("params");
        double[] params = new double[paramNames.length];
        for (int i = 0; i < 12; i++) {
            params[i] = requestParams.get(paramNames[i]);
        }
        return params;
    }

}
