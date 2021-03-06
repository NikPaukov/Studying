package com.example.lab3.TableOne;

import com.example.lab3.Counter;
import com.example.lab3.FormulaOneCounter;
import com.example.lab3.TableOneParamSerivce;
import com.example.lab3.validators.*;

import java.io.*;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "count", value = "/count")
public class TableOneServlet extends HttpServlet {
    Counter counter;
    String[] paramNames;
    TableOneParamSerivce paramSerivce;

    public void init() {
        counter = new FormulaOneCounter();
        paramSerivce = new TableOneParamSerivce(paramNames);
        paramNames = new String[]{"aFrom", "aTo", "aStep", "bFrom",
                "bTo", "bStep", "cFrom", "cTo", "cStep", "dFrom", "dTo", "dStep",};
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //getting params
        Map<String, String[]> params = request.getParameterMap();
        //validating

        StringBuilder errorMsg = new StringBuilder("Виникла помилка при обробці параметрів:\n");
        List<Validator> validators = List.of(new ParamExistenceValidator(), new NumberOfParamsValidator(12), new ParamNamesValidator(paramNames),
                new IsParamDoubleValidator(), new ParamToFromDiffValidator(paramNames), new StepValidator(paramNames));

        if (!paramSerivce.validate(params, validators, errorMsg)) {
            request.setAttribute("error", errorMsg.toString());
            request.getRequestDispatcher("/WEB-INF/jsp/error-page.jsp").forward(request, response);
            return;
        }
        //parsing params to Double
        Map<String, Double> doubleParams = paramSerivce.reTypeParams(params);



        //Counting
       double[] res = counter.count(paramSerivce.countParamsLength(doubleParams), doubleParams);


        //adding cookies and attributes
        request.getSession().setAttribute("params", doubleParams);

        request.setAttribute("res", res);
        request.setAttribute("params", doubleParams);
        request.setAttribute("paramNames", paramNames);
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    public void destroy() {
    }
}