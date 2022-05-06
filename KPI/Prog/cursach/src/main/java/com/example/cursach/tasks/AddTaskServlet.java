package com.example.cursach.tasks;

import com.example.cursach.authorization.AuthorizationUtils;
import com.example.cursach.authorization.validation.*;
import com.example.cursach.fileUtils.XmlFileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddTaskServlet", value = "/add")
public class AddTaskServlet extends HttpServlet {

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

        //Validation
        List<Validator> validators = List.of(
                new ParamNamesValidator(new String[]{"name", "priority", "date", "time"}),
                new ParamExistenceValidator(), new TaskParamValidator());
        StringBuilder errorMsg = new StringBuilder();

        if(!AuthorizationUtils.validate(request.getParameterMap(), validators, errorMsg)){
            request.setAttribute("error", errorMsg.toString());
            request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
            return;
        }


        XmlFileService xml = new XmlFileService(filename);



        Task task = new Task(request.getParameter("name"), request.getParameter("priority"),
                request.getParameter("date") + " " + request.getParameter("time"));

        try {
            xml.write(task.toXml());

            List<Task> e = Task.convertXMLListtoTaskList(xml.readEntitiesTags());
            request.setAttribute("tasks", Task.convertXMLListtoTaskList(xml.readEntitiesTags()));
            request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
        }catch (Exception e){
            request.setAttribute("error", "Виникла внутрішня помилка серверу! спробуйте ще раз пізніше");
            request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
        }

    }
}
