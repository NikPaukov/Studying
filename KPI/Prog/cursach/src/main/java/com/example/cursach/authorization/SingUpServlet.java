package com.example.cursach.authorization;

import com.example.cursach.fileUtils.XmlFileService;
import com.example.cursach.authorization.validation.*;
import com.example.cursach.tasks.Task;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "SingUpServlet", value = "/singup")
public class SingUpServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        char[] username = request.getParameter("username").toCharArray();
        char[] password = request.getParameter("password").toCharArray();
        String encryptedPassword = AuthorizationUtils.passwordEncrypter(new String(password));
        //Validation
        String[] paramNames = new String[]{"username", "password"};
        StringBuilder errorMsg = new StringBuilder("");
        List<Validator> validators = List.of(new ParamExistenceValidator(),new NumberOfParamsValidator(2),
                new ParamNamesValidator(paramNames), new PasswordUsernameValidation());

        if (!AuthorizationUtils.validate(request.getParameterMap(), validators, errorMsg)) {
            request.setAttribute("error", errorMsg.toString());
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        //Adding
        String filename = new String(username) + '&' + encryptedPassword;
        XmlFileService xml = new XmlFileService(filename);
        int success = xml.create();
        if (success == 0) {
            request.setAttribute("error", "Ви вже маєте аккаунт");
            request.getRequestDispatcher("singup.jsp").forward(request, response);
            return;
        }
        if (success < 0) {
            request.setAttribute("error", "Виникла внутрішня помилка сервера");
            request.getRequestDispatcher("singup.jsp").forward(request, response);
            return;
        }
        Arrays.fill(username, (char) 0);
        Arrays.fill(password, (char) 0);
        request.getSession().setAttribute("fileName", filename);

        try{
            List<Task> tasks = Task.convertXMLListtoTaskList(xml.readEntitiesTags());
            request.getSession().setAttribute("tasks", tasks);
        } catch (Exception e){
            request.setAttribute("error", "Виникла нутрішня помилка серверу");
            request.getRequestDispatcher("singup.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);

    }
}
