package marstelecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping(value = "/create")
	public ResponseEntity<Person> create(@RequestBody Person person) {
		int id = persondao.createPerson(person);
		if (id != 0)
			return new ResponseEntity<Person>(HttpStatus.CREATED);

		return new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Person> update(@RequestBody Person person) {
		String result = persondao.updatePerson(person);
		if (result.equalsIgnoreCase("Success"))
			return new ResponseEntity<Person>(HttpStatus.CREATED);
		else
			return new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping(value = "/updatePersonAddress")
	public ResponseEntity<Person> updatePersonAddress(@RequestBody Person person) {
		String result = persondao.updatePersonAddress(person);
		if (result.equalsIgnoreCase("Success"))
			return new ResponseEntity<Person>(HttpStatus.CREATED);
		else
			return new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@GetMapping(value = "/getall")
	public ResponseEntity<List<Person>> findAll() {
		return ResponseEntity.ok(persondao.findAll());
	}

	@GetMapping(value = "/getPersonCount")
	public ResponseEntity<Integer> findCount() {
		return ResponseEntity.ok(persondao.findCount());
	}

	@DeleteMapping(value = "/deletePerson/{id}")
	public ResponseEntity<Person> deletePerson(@PathVariable("id") int id) {
		String result = persondao.delete(id);
		if (result.equalsIgnoreCase("Success"))
			return new ResponseEntity<Person>(HttpStatus.CREATED);
		else
			return new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value = "/updateAddress")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
		String result = persondao.updateAddress(address);
		if (result.equalsIgnoreCase("Success"))
			return new ResponseEntity<Address>(HttpStatus.CREATED);
		else
			return new ResponseEntity<Address>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}