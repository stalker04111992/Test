package web.controllers;

import web.auth.Auth;
import web.entity.User;
import web.service.UserDao;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet
{
    @EJB
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //set UTF-8 encoding characters
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Auth auth = (Auth) request.getSession().getAttribute("auth");
        if (auth != null)
        {
            return;
        }
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //set UTF-8 encoding characters
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.FindByUsername(username);
        if (user == null)
        {
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("error", "Пользователь с указанным именем не найден");
            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }

        if (user.getEnabled() == 0)
        {
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("error", "Пользователь заблокирован");
            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }

        if (user.getPassword().equals(password))
        {
            String []roles = new String[]{"user"};
            Auth auth = new Auth(user, roles);
            HttpSession session = request.getSession(false);
            session.setAttribute("auth", auth);
            request.setAttribute("auth", auth);
            String page = (String)session.getAttribute("DestinationPage");
            if (page == null)
            {
                response.sendRedirect("index");
            }
            else
            {
                session.removeAttribute("DestinationPage");
                response.sendRedirect(page);
            }
        }
        else
        {
            request.setAttribute("error", "Введен неверный пароль");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }
    }

}
