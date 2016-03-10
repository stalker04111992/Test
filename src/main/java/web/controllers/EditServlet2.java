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
import java.util.List;

@WebServlet("/editContact")
public class EditServlet2 extends HttpServlet {

    @EJB
    private PersonDao personDao;

    @EJB
    private PhoneDao phoneDao;

    @EJB
    private EmailDao emailDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int number1 = new Integer(request.getParameter("PhoneRadio")) - 1;
        int number2 = new Integer(request.getParameter("EmailRadio")) - 1;

        int id = new Integer(request.getParameter("id"));
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String patronymic = request.getParameter("Patronymic");
        String birthDate = request.getParameter("BirthDate");
        String address = request.getParameter("Address");
        String []phones = request.getParameterValues("Phone");
        String []typePhones = request.getParameterValues("TypePhone");
        String []emails = request.getParameterValues("Email");
        String []typeEmails = request.getParameterValues("TypeEmail");

        if (number1 > phones.length)
        {
            number1 = 0;
        }

        if (number2 > emails.length)
        {
            number2 = 0;
        }

        if (firstName.isEmpty() || lastName.isEmpty() || patronymic.isEmpty() || phones[number1].isEmpty() || emails[number2].isEmpty())
        {
            request.setAttribute("error", "Поля не должны быть пустыми");
            Person person = new Person(firstName, lastName, patronymic, birthDate, address, number1, number2);
            request.setAttribute("person", person);
            request.setAttribute("phones", phones);
            request.setAttribute("emails", emails);
            request.getRequestDispatcher("WEB-INF/pages/AddContacts.jsp").forward(request, response);
            return;
        }

        try
        {
            Person person = new Person(firstName, lastName, patronymic, birthDate, address, number1, number2);
            personDao.update(person, id);
            phoneDao.remove(id);
            emailDao.remove(id);

            for(int i = 0; i < phones.length; i++)
            {
                if (phones[i].isEmpty() || phones[i] == "")
                {
                    continue;
                }

                Phone phone = new Phone(id, phones[i], typePhones[i], i);
                phoneDao.update(phone);
            }
            for(int i = 0; i < emails.length; i++)
            {
                if (emails[i].isEmpty())
                {
                    continue;
                }

                Email email = new Email(id, emails[i], typeEmails[i], i);
                emailDao.update(email);
            }
            request.setAttribute("error", "Контакт изменен успешно");

        }
        catch (Throwable throwable)
        {
            request.setAttribute("error", "Ошибка при работе с базой данных");
        }
        finally
        {
            request.getRequestDispatcher("WEB-INF/pages/AddContacts.jsp").forward(request, response);
        }
    }

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
            int number = new Integer(request.getParameter("Select"));
            try
            {
                Person person = personDao.getPerson(number);
                List<Phone> phones = phoneDao.getPhone(number);
                List<Email> emails = emailDao.getEmail(number);

                if (person == null || phones == null || emails == null)
                {
                    throw new Exception();
                }

                request.setAttribute("person", person);
                request.setAttribute("phones", phones);
                request.setAttribute("emails", emails);

            }
            catch (Throwable exception)
            {
                request.setAttribute("error", "Ошибка при работе с базой данных");
            }
            finally
            {
                request.getRequestDispatcher("WEB-INF/pages/EditContacts2.jsp").forward(request, response);
            }
        }
        else
        {
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }
    }
}
