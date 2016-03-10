package web.service;

import web.entity.Email;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@LocalBean
@Stateless
public class EmailDao {

    @PersistenceContext(unitName = "MySqlDS2")
    private EntityManager em;


    public void save(Email email){
        em.persist(email);
    }

    public List<Email> findAll()
    {
        List<Email> list = em.createQuery("select u from Email u").getResultList();
        return list;
    }

    public Email getEmail(int id, int def)
    {
        List<Email> emails = em.createQuery(
                "SELECT c FROM Email c WHERE c.personID = :id and c.def = :def")
                .setParameter("id", id)
                .setParameter("def", def)
                .getResultList();

        if (emails.size() > 0)
        {
            return emails.get(0);
        }
        else
        {
            return null;
        }
    }

    public void remove(int id)
    {
        List<Email> emails = getEmail(id);
        if (emails != null)
        {
            for(Email email: emails)
            {
                em.remove(email);
            }
        }
    }

    public void update(Email email)
    {
        em.merge(email);
    }

    public List<Email> getEmail(int id)
    {
        List<Email> emails = em.createQuery(
                "SELECT c FROM Email c WHERE c.personID = :id")
                .setParameter("id", id)
                .getResultList();

        if (emails.size() > 0)
        {
            return emails;
        }
        else
        {
            return null;
        }
    }
}
