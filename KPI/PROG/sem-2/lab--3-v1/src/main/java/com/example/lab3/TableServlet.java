package com.example.lab3;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "count", value = "/count")
public class TableServlet extends HttpServlet {
    FormulaOneCounter counter;
    CookiesService cookiesUtils;

    public void init() {
        counter = new FormulaOneCounter();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //getting params
        Map<String, String[]> params = request.getParameterMap();
        //validating
        String error;
        if ((error = ParamUtils.validate(params)) != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/jsp/error-page.jsp").forward(request, response);
            return;
        }
        //parsing params to Double
        Map<String, Double> doubleParams = ParamUtils.getDoubleParams(params);
        double[] res = new double[ParamUtils.countParamsLength(doubleParams) + 1];


        //Counting
        counter.count(res, doubleParams);


        //adding cookies and attributes
        cookiesUtils = new CookiesService(response);
        cookiesUtils.addAllCookie(doubleParams);
        request.setAttribute("res", res);
        request.setAttribute("params", doubleParams);
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    public void destroy() {
    }
}