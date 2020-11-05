package marstelecom.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import marstelecom.model.Address;
import marstelecom.model.Person;

// Database repository class.
@Repository
public class PersonDao {

	@Autowired
	private SessionFactory sf;

	public Integer createPerson(Person person) {
		Session s = sf.getCurrentSession();

		s.beginTransaction();
		Integer id = (Integer) s.save(person);
		s.getTransaction().commit();

		return id;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Person> findAll() {
		Session s = sf.getCurrentSession();
		List<Person> list = s.createCriteria(Person.class).list();
		return list;
	}


	public String updatePerson(Person person) {
		Session s = sf.getCurrentSession();

		s.beginTransaction();
		try {
			s.saveOrUpdate(person);
		} catch (Exception exception) {
			return "Failure";
		}
		s.getTransaction().commit();
		return "Success";

	}

	public String updatePersonAddress(Person person) {
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		Person existPerson = s.get(Person.class, person.getId());

		person.setFirstName(existPerson.getFirstName());
		person.setLastName(existPerson.getLastName());
		s.getTransaction().commit();
		s.close();
		Session newSession = sf.openSession();
		newSession.beginTransaction();
		try {
			newSession.saveOrUpdate(person);
		} catch (Exception exception) {
			return "Failure";
		}
		newSession.getTransaction().commit();
		return "Success";
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public int findCount() {
		Session s = sf.getCurrentSession();
		List<Person> list = s.createCriteria(Person.class).list();
		return list.size();
	}

	public String delete(int id) {
		Session s = sf.getCurrentSession();

		s.beginTransaction();
		Person person = s.get(Person.class, id);
		try {
			s.delete(person);
		} catch (Exception exception) {
			return "Failure";
		}
		s.getTransaction().commit();
		return "Success";
	}

	public String updateAddress(Address address) {
		Session s = sf.getCurrentSession();
		s.beginTransaction();
	
		try {
			s.saveOrUpdate(address);
		} catch (Exception exception) {
			return "Failure";
		}
		s.getTransaction().commit();
		return "Success";
	}

}