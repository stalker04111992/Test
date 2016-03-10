package web.controllers;

import web.auth.Auth;
import web.entity.Person;
import web.service.PersonDao;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    @EJB
    private PersonDao personDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //no cashing
        response.setHeader("Cache-Control", "no-cache, must-revalidate, private, no-store, s-maxage=0, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        //get auth
        Auth auth = (Auth) request.getSession().getAttribute("auth");
        if (auth == null) {
            request.getSession().setAttribute("DestinationPage", "ticket");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }
        if (auth.ExistRole("user"))
        {
            try
            {
                List<Person> persons = personDao.findAll();
                request.setAttribute("persons", persons);
            }
            catch (Throwable exception)
            {
                request.setAttribute("error", "Ошибка при работе с базой данных");
            }
            finally
            {
                request.getRequestDispatcher("WEB-INF/pages/EditContacts.jsp").forward(request, response);
            }
        }
        else
        {
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }
    }
}