package com.example.cursach.tasks;

import com.example.cursach.authorization.AuthorizationUtils;
import com.example.cursach.authorization.validation.ParamExistenceValidator;
import com.example.cursach.authorization.validation.ParamNamesValidator;
import com.example.cursach.authorization.validation.TaskParamValidator;
import com.example.cursach.authorization.validation.Validator;
import com.example.cursach.fileUtils.XmlFileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteTaskServlet", value = "/delete")
public class DeleteTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String filename = (String) request.getSession().getAttribute("filename");
        if(filename==null){
            request.setAttribute("error", "З початку треба авторізуватися!");
            response.sendRedirect("login.jsp");
            return;
        }

        List<Validator> validators = List.of(new ParamNamesValidator(new String[]{"name"}),
                new ParamExistenceValidator(), new TaskParamValidator());
        StringBuilder errorMsg = new StringBuilder();

        if(!AuthorizationUtils.validate(request.getParameterMap(), validators, errorMsg)){
            request.setAttribute("error", errorMsg.toString());
            request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
            return;
        }



        try{
        XmlFileService xml = new XmlFileService(filename);
        int sc = xml.delete(name);
            if(sc==0){
                request.setAttribute("error", "Задача з таким ім'ям не знайдена");
            }
        request.setAttribute("tasks", Task.convertXMLListtoTaskList(xml.readEntitiesTags()));
        request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
        return;
        } catch (Exception e){
            System.out.println(e.getMessage());
            request.setAttribute("error", "Виникла нутрішня помилка серверу");
            request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
        }

    }

}
