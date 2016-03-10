package web.service;

import web.entity.Role;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@LocalBean
@Stateless
public class RoleDao
{
    @PersistenceContext(unitName = "MySqlDS2")
    private EntityManager em;

    public void save(Role role)
    {
        em.persist(role);
    }

}
