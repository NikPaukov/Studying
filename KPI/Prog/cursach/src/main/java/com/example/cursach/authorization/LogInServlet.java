package com.example.cursach.authorization;

import com.example.cursach.fileUtils.XmlFileService;
import com.example.cursach.authorization.validation.*;
import com.example.cursach.tasks.Task;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogInServlet", value = "/login")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        char[] username = request.getParameter("username").toCharArray();
        char[] password = request.getParameter("password").toCharArray();
        String encryptedPassword = AuthorizationUtils.PasswordEncrypter(new String(password));
        //Validation
        StringBuilder errorMsg = new StringBuilder("");
        List<Validator> validators = List.of(new ParamExistenceValidator(),
                new ParamNamesValidator(new String[]{"username", "password"}), new PasswordUsernameValidation());

        if (!AuthorizationUtils.validate(request.getParameterMap(), validators, errorMsg)) {
            request.setAttribute("error", errorMsg.toString());
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }


        String filename = new String(username) + '&' + encryptedPassword;
        XmlFileService xmlFileService = new XmlFileService(filename);
        int status = xmlFileService.hasUser();
        if(status==-1){
            request.setAttribute("error", "Виникла нутрішня помилка серверу");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if(status==0){
            request.setAttribute("error", "Не правильний логін\\пароль");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        System.out.println("log: "+filename);
        request.getSession().setAttribute("filename", filename);
        try {
            List<Task> tasks = Task.convertXMLListtoTaskList(xmlFileService.readEntitiesTags());
            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
        } catch (Exception e){
            request.setAttribute("error", "Виникла нутрішня помилка серверу");
            request.getRequestDispatcher("login.jsp").forward(request, response);        }
    }
}
