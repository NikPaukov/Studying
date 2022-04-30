package com.example.lab2.param.processing;
import com.example.lab2.validate.IValidator;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class CountServletFormContainer implements ParamsServletContainer {
    Map<String, String[]> params;

    public void setParams(HttpServletRequest request) {
        params = request.getParameterMap();

    }

    public Double getParam(String name) {
        return (Double) Double.parseDouble(params.get(name)[0]);
    }

    public String validate(IValidator validator) {


        StringBuilder errorMsg = new StringBuilder("Помилка в параметрах: ");
        for (String name : params.keySet()) {
            if(!params.containsKey("a") || !params.containsKey("b") || !params.containsKey("c") || !params.containsKey("d")){
                return "Відсутні необхідні параметри";}
            if (!validator.isValid(params.get(name)[0])) {
                errorMsg.append(name).append(", ");
            }
        }
        if ((errorMsg.toString()).equals("Помилка в параметрах: ")) return null;
        return errorMsg.toString();
    }

    public void addCookies(HttpServletResponse response) {
        for (String name : params.keySet()) {
            Cookie cookie = new Cookie(name, params.get(name)[0]);
            cookie.setMaxAge(2880);
            response.addCookie(cookie);
        }

    }

    public void addCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, params.get(name)[0]);
        cookie.setMaxAge(2880);
        response.addCookie(cookie);
    }

}
