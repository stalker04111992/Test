package web.controllers;

import web.auth.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "logout")
public class LogoutServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Auth auth = (Auth) request.getSession().getAttribute("auth");
        if (auth == null)
        {
            return;
        }
        //set UTF-8 encoding characters
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //close session
        HttpSession session = request.getSession(false);
        session.removeAttribute("auth");
        session.invalidate();
        response.sendRedirect("index");
    }
}
