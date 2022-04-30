package com.example.lab2.Counter;

import com.example.lab2.validate.CountResValidator;
import com.example.lab2.validate.CounterParamsValidator;
import com.example.lab2.validate.IValidator;
import com.example.lab2.param.processing.CountServletFormContainer;
import com.example.lab2.param.processing.ParamsServletContainer;
import com.example.lab2.utils.CookiesUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CountServlet", value = "/result")
public class CountServlet extends HttpServlet {
    ICounter counter;
    IValidator paramValidator;
    IValidator resValidator;
    CountServletFormContainer container;

    @Override
    public void init() throws ServletException {
        super.init();
        counter = new CounterService();
        paramValidator = new CounterParamsValidator();
        resValidator = new CountResValidator();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double res;
        container = new CountServletFormContainer();
        container.setParams(request);
        String erorMsg = container.validate(paramValidator);

        if (erorMsg != null) {
            request.setAttribute("error", erorMsg);
            request.getRequestDispatcher("/WEB-INF/jsps/error-page.jsp").forward(request, response);
            return;
        }

        res = counter.count(container.getParam("a"), container.getParam("b"),
                container.getParam("c"), container.getParam("d"));

        container.addCookies(response);

        request.setAttribute("container", container);
        if (resValidator.isValid(res)) {
            request.setAttribute("res", res);
        } else {
            request.setAttribute("res", "Помилка при підрахнуках. перевірте математичну корректність");
        }
        request.getRequestDispatcher("/WEB-INF/jsps/result.jsp").forward(request, response);
    }

}

