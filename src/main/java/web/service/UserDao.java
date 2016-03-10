package web.service;

import web.entity.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@LocalBean
@Stateless
public class UserDao
{
    @PersistenceContext(unitName = "MySqlDS2")
    private EntityManager em;

    public void save(User user)
    {
        em.persist(user);
    }

    public User FindByUsername(String username)
    {
        List<User> users = em.createQuery(
                "SELECT c FROM User c WHERE c.username = :username")
                .setParameter("username", username)
                .getResultList();

        if (users.size() > 0)
        {
            return users.get(0);
        }
        else
        {
            return null;
        }
    }

}
