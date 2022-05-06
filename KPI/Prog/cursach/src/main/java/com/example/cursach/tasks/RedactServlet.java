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

@WebServlet(name = "RedactServlet", value = "/redact")
public class RedactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = (String) request.getSession().getAttribute("filename");
        if(filename==null){
            request.setAttribute("error", "З початку треба авторізуватися!");
            response.sendRedirect("login.jsp");
            return;
        }


        List<Validator> validators = List.of(
                new ParamNamesValidator(new String[]{"oldName", "name", "priority", "duration"}),
                new ParamExistenceValidator(), new TaskParamValidator());
        StringBuilder errorMsg = new StringBuilder();

        if(!AuthorizationUtils.validate(request.getParameterMap(), validators, errorMsg)){
            request.setAttribute("error", errorMsg.toString());
            request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
            return;
        }

        XmlFileService xml = new XmlFileService(filename);
        try {
            int sc = xml.delete(request.getParameter("oldName"));
            if(sc==0){
                request.setAttribute("error", "Задача з таким ім'ям не знайдена");
            }
            xml.write(new Task(request.getParameter("name"), request.getParameter("priority"),
                    request.getParameter("duration")).toXml());
            List<Task> taskList = Task.convertXMLListtoTaskList(xml.readEntitiesTags());
            request.setAttribute("tasks", taskList);
            request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
        } catch (Exception e){
            request.setAttribute("error", "Виникла внутрішня помилка серверу! спробуйте ще раз пізніше");
            request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
        }


    }
}
