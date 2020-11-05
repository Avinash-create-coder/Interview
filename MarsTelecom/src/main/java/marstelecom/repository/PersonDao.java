package marstelecom.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import marstelecom.exception.PersonDaoException;
import marstelecom.model.Address;
import marstelecom.model.Person;

// Database repository class.
@Repository
public class PersonDao {

	@Autowired
	private SessionFactory sf;

	public Integer createPerson(Person person) throws PersonDaoException {
		Integer id = null;
		try {
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			id = (Integer) s.save(person);
			s.getTransaction().commit();
		} catch (HibernateException exception) {
			throw new PersonDaoException("Error while creating person");
		}
		return id;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Person> findAll() throws PersonDaoException {
		List<Person> list = null;
		try {
			Session s = sf.getCurrentSession();
			list = s.createCriteria(Person.class).list();
		} catch (HibernateException exception) {
			throw new PersonDaoException("Error while retreiveing person list");
		}
		return list;
	}


	public String updatePerson(Person person) throws PersonDaoException {
		Session s = null;
		try {
			s = sf.getCurrentSession();
			s.beginTransaction();
			s.saveOrUpdate(person);
		} catch (HibernateException exception) {
			throw new PersonDaoException("Error while updating person");
		}
		s.getTransaction().commit();
		return "Success";

	}

	public String updatePersonAddress(Person person) throws PersonDaoException {
		Session s = null;
		Session newSession = null;

		try {
			s = sf.getCurrentSession();
			s.beginTransaction();
			Person existPerson = s.get(Person.class, person.getId());
			person.setFirstName(existPerson.getFirstName());
			person.setLastName(existPerson.getLastName());
			s.getTransaction().commit();
			s.close();
			newSession = sf.openSession();
			newSession.beginTransaction();
			newSession.saveOrUpdate(person);
		} catch (HibernateException exception) {
			throw new PersonDaoException("Error while updating person address");
		}
		newSession.getTransaction().commit();
		return "Success";
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public int findCount() throws PersonDaoException {
		Session s = null;
		List<Person> list = null;
		try {
			s = sf.getCurrentSession();
			list = s.createCriteria(Person.class).list();
		} catch (HibernateException exception) {
			throw new PersonDaoException("Error while fetching person count");
		}
		return list.size();
	}

	public String delete(int id) throws PersonDaoException {
		Session s = null;
		try {
			s = sf.getCurrentSession();
			s.beginTransaction();
			Person person = s.get(Person.class, id);
			s.delete(person);
		} catch (HibernateException exception) {
			throw new PersonDaoException("Error while deleting person");
		}
		s.getTransaction().commit();
		return "Success";
	}

	public String updateAddress(Address address) throws PersonDaoException {
		Session s = null;
		try {
			s = sf.getCurrentSession();
			s.beginTransaction();

			s.saveOrUpdate(address);
		} catch (HibernateException exception) {
			throw new PersonDaoException("Error while updating address");
		}
		s.getTransaction().commit();
		return "Success";
	}

}