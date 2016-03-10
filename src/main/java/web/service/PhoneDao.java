package web.service;

import web.entity.Person;
import web.entity.Phone;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@LocalBean
@Stateless
public class PhoneDao {

    @PersistenceContext(unitName = "MySqlDS2")
    private EntityManager em;


    public void save(Phone phone){
        em.persist(phone);
    }

    public List<Phone> findAll()
    {
        List<Phone> list = em.createQuery("select u from Phone u").getResultList();
        return list;
    }

    public Phone getPhone(int id, int def)
    {
        List<Phone> phones = em.createQuery(
                "SELECT c FROM Phone c WHERE c.personID = :id and c.def = :def")
                .setParameter("id", id)
                .setParameter("def", def)
                .getResultList();

        if (phones.size() > 0)
        {
            return phones.get(0);
        }
        else
        {
            return null;
        }
    }

    public void remove(int id)
    {
        List<Phone> phones = getPhone(id);
        if (phones != null)
        {
            for(Phone phone: phones)
            {
                em.remove(phone);
            }
        }
    }

    public void update(Phone phone)
    {
        em.persist(phone);
    }

    public List<Phone> getPhone(int id)
    {
        List<Phone> phones = em.createQuery(
                "SELECT c FROM Phone c WHERE c.personID = :id")
                .setParameter("id", id)
                .getResultList();

        if (phones.size() > 0)
        {
            return phones;
        }
        else
        {
            return null;
        }
    }

}
