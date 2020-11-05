package marstelecom.test.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.transaction.annotation.Transactional;

import marstelecom.exception.PersonDaoException;
import marstelecom.model.Address;
import marstelecom.model.Person;
import marstelecom.repository.PersonDao;

@Transactional
@PowerMockIgnore("javax.management.*")
@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(PersonDao.class)
public class PersonDaoTest {

	@InjectMocks
	private PersonDao personDao;

	@Mock
	private SessionFactory sf;

	@Mock
	private Session session;

	@Mock
	private Criteria criteria;

	@Mock
	private Transaction transaction;

	@Mock
	private HibernateException exception;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() {
		personDao = null;
	}

	@Test
	public void testCreatePerson() throws PersonDaoException {

		Person person = new Person();

		Mockito.when(sf.getCurrentSession()).thenReturn(session);
		Mockito.when(session.beginTransaction()).thenReturn(transaction);
		Mockito.when(session.save(person)).thenReturn(1);
		Mockito.when(session.getTransaction()).thenReturn(transaction);
		Integer i = personDao.createPerson(person);
		assertThat(i, is(1));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testFindAll() throws PersonDaoException {

		Person person = new Person();

		Person person1 = new Person();

		List<Person> list = Arrays.asList(person, person1);

		Mockito.when(sf.getCurrentSession()).thenReturn(session);
		Mockito.when(session.createCriteria(Person.class)).thenReturn(criteria);
		Mockito.when(session.createCriteria(Person.class).list()).thenReturn(list);
		List<Person> resultList = personDao.findAll();
		assertThat(resultList.size(), is(list.size()));
	}

	@Test
	public void testUpdatePersonSuccess() throws PersonDaoException {

		Person person = new Person();

		Mockito.when(sf.getCurrentSession()).thenReturn(session);
		Mockito.when(session.beginTransaction()).thenReturn(transaction);
		Mockito.doNothing().when(session).saveOrUpdate(person);
		Mockito.when(session.getTransaction()).thenReturn(transaction);
		String resultList = personDao.updatePerson(person);
		assertThat(resultList, is("Success"));
	}

	@Test
	public void testUpdatePersonFailure() throws PersonDaoException {

		Person person = new Person();

		Mockito.when(sf.getCurrentSession()).thenReturn(session);
		Mockito.when(session.beginTransaction()).thenReturn(transaction);
		Mockito.doThrow(exception).when(session).saveOrUpdate(person);
		Mockito.when(session.getTransaction()).thenReturn(transaction);
		try {
			personDao.updatePerson(person);
		} catch (PersonDaoException exception) {
			assertThat(exception.getMessage(), is("Error while updating person"));
		}
	}

	@Test
	public void testUpdatePersonAddressSuccess() throws PersonDaoException {
		Person person = new Person();
		person.setId(1);

		Person existPerson = new Person();
		existPerson.setFirstName("Mars");
		existPerson.setLastName("Telecom");

		Mockito.when(sf.getCurrentSession()).thenReturn(session);
		Mockito.when(session.beginTransaction()).thenReturn(transaction);
		Mockito.when(session.get(Person.class, person.getId())).thenReturn(existPerson);

		Mockito.when(session.getTransaction()).thenReturn(transaction);

		Mockito.when(sf.openSession()).thenReturn(session);

		Mockito.doNothing().when(session).saveOrUpdate(person);

		String resultList = personDao.updatePersonAddress(person);

		assertThat(resultList, is("Success"));

	}

	@Test
	public void testUpdatePersonAddressFailure() throws PersonDaoException {
		Person person = new Person();
		person.setId(1);

		Person existPerson = new Person();
		existPerson.setFirstName("Mars");
		existPerson.setLastName("Telecom");

		Mockito.when(sf.getCurrentSession()).thenReturn(session);
		Mockito.when(session.beginTransaction()).thenReturn(transaction);
		Mockito.when(session.get(Person.class, person.getId())).thenReturn(existPerson);

		Mockito.when(session.getTransaction()).thenReturn(transaction);

		Mockito.when(sf.openSession()).thenReturn(session);

		Mockito.doThrow(exception).when(session).saveOrUpdate(person);
		try {
			personDao.updatePersonAddress(person);
		} catch (PersonDaoException exception) {
			assertThat(exception.getMessage(), is("Error while updating person address"));
		}

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testFindCount() throws PersonDaoException {

		Person person = new Person();

		Person person1 = new Person();

		List<Person> list = Arrays.asList(person, person1);

		Mockito.when(sf.getCurrentSession()).thenReturn(session);
		Mockito.when(session.createCriteria(Person.class)).thenReturn(criteria);
		Mockito.when(session.createCriteria(Person.class).list()).thenReturn(list);
		int resultList = personDao.findCount();
		assertThat(resultList, is(2));
	}

	@Test
	public void testDeleteSuccess() throws PersonDaoException {

		Person person = new Person();
		person.setId(1);

		Mockito.when(sf.getCurrentSession()).thenReturn(session);
		Mockito.when(session.beginTransaction()).thenReturn(transaction);
		Mockito.when(session.get(Person.class, person.getId())).thenReturn(person);
		Mockito.when(session.getTransaction()).thenReturn(transaction);
		Mockito.doNothing().when(session).delete(person);
		String result = personDao.delete(1);
		assertThat(result, is("Success"));
	}

	@Test
	public void testDeleteFailure() throws PersonDaoException {

		Person person = new Person();
		person.setId(1);

		Mockito.when(sf.getCurrentSession()).thenReturn(session);
		Mockito.when(session.beginTransaction()).thenReturn(transaction);
		Mockito.when(session.get(Person.class, person.getId())).thenReturn(person);
		Mockito.when(session.getTransaction()).thenReturn(transaction);
		Mockito.doThrow(exception).when(session).delete(person);
		try {
			personDao.delete(1);
		} catch (PersonDaoException exception) {
			assertThat(exception.getMessage(), is("Error while deleting person"));
		}
	}

	@Test
	public void testUpdateAddressSuccess() throws PersonDaoException {

		Address addr = new Address();

		Mockito.when(sf.getCurrentSession()).thenReturn(session);
		Mockito.when(session.beginTransaction()).thenReturn(transaction);
		Mockito.doNothing().when(session).saveOrUpdate(addr);
		Mockito.when(session.getTransaction()).thenReturn(transaction);
		String resultList = personDao.updateAddress(addr);
		assertThat(resultList, is("Success"));
	}

	@Test
	public void testUpdateAddressFailure() throws PersonDaoException {

		Address addr = new Address();

		Mockito.when(sf.getCurrentSession()).thenReturn(session);
		Mockito.when(session.beginTransaction()).thenReturn(transaction);
		Mockito.doThrow(exception).when(session).saveOrUpdate(addr);
		Mockito.when(session.getTransaction()).thenReturn(transaction);
		try {
			personDao.updateAddress(addr);
		} catch (PersonDaoException exception) {
			assertThat(exception.getMessage(), is("Error while updating address"));
		}
	}

}
