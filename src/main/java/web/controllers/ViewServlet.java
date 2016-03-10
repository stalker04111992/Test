package web.controllers;

import web.auth.Auth;
import web.entity.Email;
import web.entity.Person;
import web.entity.Phone;
import web.service.EmailDao;
import web.service.PersonDao;
import web.service.PhoneDao;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {

    @EJB
    private PersonDao personDao;

    @EJB
    private PhoneDao phoneDao;

    @EJB
    private EmailDao emailDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        //get auth
        Auth auth = (Auth) request.getSession().getAttribute("auth");
        if (auth == null) {
            request.getSession().setAttribute("DestinationPage", "ticket");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }
        if (auth.ExistRole("user"))
        {
            try {
                List<Person> persons = personDao.findAll();
                List<Phone> phones = new ArrayList<Phone>();
                List<Email> emails = new ArrayList<Email>();

                for (Person person : persons)
                {
                    phones.add(phoneDao.getPhone(person.getId(), person.getPhone()));
                    emails.add(emailDao.getEmail(person.getId(), person.getEmail()));
                }

                request.setAttribute("persons", persons);
                request.setAttribute("phones", phones);
                request.setAttribute("emails", emails);

            }
            catch (Throwable exception)
            {
                request.setAttribute("error", "Ошибка работы с базой данных");
            }
            finally {
                request.getRequestDispatcher("WEB-INF/pages/ViewContacts.jsp").forward(request, response);
            }
        }
        else
        {
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }
    }
}