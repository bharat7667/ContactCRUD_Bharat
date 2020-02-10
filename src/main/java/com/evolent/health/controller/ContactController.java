/**
 * 
 */
package com.evolent.health.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evolent.health.entity.Contact;
import com.evolent.health.exceptions.ContactNotFoundException;
import com.evolent.health.service.ContactService;

/**
 * Controller for Contact class
 * 
 * @author Bharat
 * @date Feb 10, 2020
 *
 */
@RestController
@RequestMapping(path = "/contact-v1")
public class ContactController {

	@Autowired
	private ContactService contactService;

	/**
	 * Will return list of available contacts
	 *
	 * @see "http://localhost:8080/contact-v1/contacts"
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 * @return ResponseBean of list of contact
	 * @throws None
	 *
	 */
	@GetMapping("/contacts")
	public ResponseEntity<List<Contact>> getAllContatcs() {

		List<Contact> list = contactService.getAllContacts();

		return new ResponseEntity<List<Contact>>(list, HttpStatus.OK);
	}

	/**
	 * Will return specific Contact based on ID
	 *
	 * @see "http://localhost:8080//contact-v1/contacts/1"
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 * @return ResponseBean of contact
	 * @throws ContactNotFoundException
	 *
	 */
	@GetMapping("/contacts/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable(name = "id") Long contactId)
			throws ContactNotFoundException {

		return new ResponseEntity<>(contactService.getContactById(contactId), HttpStatus.OK);

	}

	/**
	 * Save New Contact
	 *
	 * @see "http://localhost:8080/contact-v1/contacts"
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 * @return ResponseBean of contact
	 * @throws None
	 *
	 */
	@PostMapping("/contacts")
	public ResponseEntity<Contact> saveContact(@Valid @RequestBody Contact contact) {
		Contact updated = contactService.saveContact(contact);
		return new ResponseEntity<Contact>(updated, HttpStatus.OK);
	}

	/**
	 * Delete Contact for given Id
	 *
	 * @see "http://localhost:8080/contact-v1/contacts/1"
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 * @return HttpStatus
	 * @throws ContactNotFoundException
	 *
	 */
	@DeleteMapping("/contacts/{id}")
	public HttpStatus deleteContactById(@PathVariable(name = "id") @NotNull Long id) throws ContactNotFoundException {
		contactService.deleteContactById(id);
		return HttpStatus.OK;

	}

	/**
	 * Update Contact for Specific Id
	 *
	 * @see "http://localhost:8080/contact-v1/contacts/1"
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 * @return ResponseEntity of Contact
	 * @throws None
	 *
	 */
	@PutMapping("/contacts/{id}")
	public ResponseEntity<Contact> updateContact(@Valid @RequestBody Contact contact, @PathVariable long id)
			throws ContactNotFoundException {
		Contact updated = contactService.updateContact(contact, id);
		return new ResponseEntity<Contact>(updated, HttpStatus.OK);
	}

	/**
	 * Activate/ inactivate Contact
	 *
	 * @see "http://localhost:8080/contact-v1/contacts/2?active=1"
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 * @return ResponseEntity of Contact
	 * @throws None
	 *
	 */
	@PatchMapping("/contacts/{id}")
	public ResponseEntity<Contact> chnageActivationStatus(@RequestParam("active") Boolean isactive,
			@PathVariable long id) throws ContactNotFoundException {
		Contact updated = contactService.updateStatus(isactive, id);
		return new ResponseEntity<Contact>(updated, HttpStatus.OK);
	}

}
