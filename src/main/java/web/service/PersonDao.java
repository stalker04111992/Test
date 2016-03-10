package web.service;

import web.entity.Person;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@LocalBean
@Stateless
public class PersonDao {

    @PersistenceContext(unitName = "MySqlDS2")
    private EntityManager em;

    public void save(Person person) {

       //em.joinTransaction(); // this is necessary, the entitymanager needs to be called specifically

        em.persist(person);
    }

    public List<Person> findAll() {
        List<Person> list = em.createQuery("select u from Person u").getResultList();
        return list;
    }

    public Person getPerson(int id) {
        Person persons = em.find(Person.class, id);
        return persons;
    }

    public void update (Person person, int id)
    {
        Person person1 = em.find(Person.class, id);
        if (person1 == null)
        {
            return;
        }
        person1.setLastName(person.getLastName());
        person1.setFirstName(person.getFirstName());
        person1.setPatronymic(person.getPatronymic());
        person1.setAddress(person.getAddress());
        person1.setDate(person.getDate());
        person1.setPhone(person.getPhone());
        person1.setEmail(person.getEmail());
    }

    public int getId(Person person)
    {
        List<Person> persons = em.createQuery(
                "SELECT c FROM Person c WHERE c.firstName = :firstName and c.lastName = :lastName and c.patronymic = :patronymic and c.date = :date and c.address = :address")
                .setParameter("firstName", person.getFirstName())
                .setParameter("lastName", person.getLastName())
                .setParameter("patronymic", person.getPatronymic())
                .setParameter("patronymic", person.getPatronymic())
                .setParameter("date", person.getDate())
                .setParameter("address", person.getAddress())
                .getResultList();

        if (persons.size() > 0)
        {
            return persons.get(0).getId();
        }
        else
        {
            return -1;
        }
    }
}
