package com.example.cursach.authorization;

import com.example.cursach.fileUtils.XmlFileService;
import com.example.cursach.tasks.Task;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "StartController", value = "/start")
public class StartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("filename") != null){
            try {
                XmlFileService xmlFileService = new XmlFileService((String) request.getSession().getAttribute("filename"));
                request.setAttribute("tasks", Task.convertXMLListtoTaskList(xmlFileService.readEntitiesTags()));
            } catch (Exception e){
                request.setAttribute("error", "Виникла нутрішня помилка серверу");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
        } else{

            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
