package web.controllers;

import web.auth.Auth;
import web.entity.Role;
import web.entity.User;
import web.service.RoleDao;
import web.service.UserDao;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet
{
    @EJB
    private UserDao userDao;

    @EJB
    private RoleDao roleDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //set UTF-8 encoding characters
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //get auth
        Auth auth = (Auth) request.getSession().getAttribute("auth");
        if (auth == null)
        {
            request.getRequestDispatcher("WEB-INF/pages/registration.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Auth auth;
        auth = (Auth) request.getSession().getAttribute("auth");
        if (auth != null) {
            return;
        }
        //set UTF-8 encoding characters
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //get Parametrs
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");
        //compare passwords
        if (!password.equals(password2)) {
            request.setAttribute("error", "Пароль введен неверно");
            request.getRequestDispatcher("WEB-INF/pages/registration.jsp").forward(request, response);
            return;
        }
        try
        {
            //seach user
            User user = userDao.FindByUsername(username);
            if (user != null)
            {
                request.setAttribute("error", "Пользователь с таким именем уже существует");
                request.getRequestDispatcher("WEB-INF/pages/registration.jsp").forward(request, response);
            }
        }
        catch (Throwable throwable)
        {
            //System.err.println(throwable);
            request.setAttribute("error", "Ошибка работы с базой данных");
            request.getRequestDispatcher("WEB-INF/pages/registration.jsp").forward(request, response);
        }

        try
        {
            User newUser = new User(username, password, 1, email);
            userDao.save(newUser);
            Role newRole = new Role(userDao.FindByUsername(username).getId(), "user");
            roleDao.save(newRole);
            response.sendRedirect("index");
        }
        catch (Throwable throwable)
        {
            //System.err.println(throwable);
            request.setAttribute("error", "Ошибка добавления в базу данных");
            request.getRequestDispatcher("WEB-INF/pages/registration.jsp").forward(request, response);
        }
    }
}
