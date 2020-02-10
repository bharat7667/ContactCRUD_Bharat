/**
 * 
 */
package com.evolent.health.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

/**
 * Entity class for Contact
 * @author Bharat
 *
 */
@Entity
@Table(name="Contact", uniqueConstraints=@UniqueConstraint(columnNames="mobileNumber"))
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long contactId;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z]*$")
	@Size(min = 2, message = "First Name should have atleast 2 characters")
	private String firstName;

	@Pattern(regexp = "^[a-zA-Z]*$")
	@Size(min = 2, message = "Last Name should have atleast 2 characters")
	private String lastName;

	@NotEmpty(message = "Email must not be empty")
	@Size(min = 4, max = 200)
	@Email(message = "Please provide a valid email")
	private String email;


	@Pattern(regexp = "^[7-9][0-9]{9}$" , message = "Please enter valid mobile 10 digit number")
	private String mobileNumber;

	private Boolean status;

	
	public Contact() {
		super();
	}

	public Contact(Long contactId,
			@NotNull @Pattern(regexp = "^[a-zA-Z]*$") @Size(min = 2, message = "First Name should have atleast 2 characters") String firstName,
			@Pattern(regexp = "^[a-zA-Z]*$") @Size(min = 2, message = "Last Name should have atleast 2 characters") String lastName,
			@NotEmpty @Size(min = 4, max = 200) @Email(message = "Please provide a valid email") String email,
			@Pattern(regexp = "^[7-9][0-9]{9}$", message = "Please enter valid mobile 10 digit number") String mobileNumber,
			Boolean status) {
		super();
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.status = status;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", mobileNumber=" + mobileNumber + ", status=" + status + "]";
	}

}
