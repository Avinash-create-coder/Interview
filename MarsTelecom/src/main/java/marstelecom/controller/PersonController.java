package marstelecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marstelecom.exception.PersonDaoException;
import marstelecom.model.Address;
import marstelecom.model.Person;
import marstelecom.repository.PersonDao;

// Controller class.
@RestController
@RequestMapping(value = "/person")
public class PersonController {

	@Autowired
	private PersonDao persondao;

	// Create a new record in database.
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value = "/create")
	public ResponseEntity create(@RequestBody Person person) throws PersonDaoException {
		int id = persondao.createPerson(person);
		if (id != 0)
			return new ResponseEntity("Person Created Successfully", HttpStatus.OK);

		return new ResponseEntity("Failed to create person", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping(value = "/update")
	public ResponseEntity update(@RequestBody Person person) throws PersonDaoException {
		String result = persondao.updatePerson(person);
		if (result.equalsIgnoreCase("Success"))
			return new ResponseEntity("Person Updated Successfully", HttpStatus.OK);
		else
			return new ResponseEntity("Failed to update person", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping(value = "/updatePersonAddress")
	public ResponseEntity updatePersonAddress(@RequestBody Person person) throws PersonDaoException {
		String result = persondao.updatePersonAddress(person);
		if (result.equalsIgnoreCase("Success"))
			return new ResponseEntity("Updated the person Address", HttpStatus.OK);
		else
			return new ResponseEntity("Failed to update person address", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/getall")
	public ResponseEntity<List<Person>> findAll() throws PersonDaoException {
		return ResponseEntity.ok(persondao.findAll());
	}

	@GetMapping(value = "/getPersonCount")
	public ResponseEntity<Integer> findCount() throws PersonDaoException {
		return ResponseEntity.ok(persondao.findCount());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping(value = "/deletePerson/{id}")
	public ResponseEntity deletePerson(@PathVariable("id") int id) throws PersonDaoException {
		String result = persondao.delete(id);
		if (result.equalsIgnoreCase("Success"))
			return new ResponseEntity("Person deleted successfully", HttpStatus.OK);
		else
			return new ResponseEntity("Failed to delete person", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping(value = "/updateAddress")
	public ResponseEntity updateAddress(@RequestBody Address address) throws PersonDaoException {
		String result = persondao.updateAddress(address);
		if (result.equalsIgnoreCase("Success"))
			return new ResponseEntity("Updated the Address", HttpStatus.OK);
		else
			return new ResponseEntity("Failed to update address", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}