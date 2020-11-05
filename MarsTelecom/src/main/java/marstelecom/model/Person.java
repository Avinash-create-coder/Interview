package marstelecom.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// Model class.
@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "permanent_address")
	private Address permanentAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "residential_address")
	private Address residentialAddress;

	public Person() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the permanentAddress
	 */
	public Address getPermanentAddress() {
		return permanentAddress;
	}

	/**
	 * @param permanentAddress the permanentAddress to set
	 */
	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	/**
	 * @return the residentialAddress
	 */
	public Address getResidentialAddress() {
		return residentialAddress;
	}

	/**
	 * @param residentialAddress the residentialAddress to set
	 */
	public void setResidentialAddress(Address residentialAddress) {
		this.residentialAddress = residentialAddress;
	}
}