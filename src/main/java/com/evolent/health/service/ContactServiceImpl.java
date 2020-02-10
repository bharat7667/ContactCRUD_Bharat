/**
 * 
 */
package com.evolent.health.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evolent.health.entity.Contact;
import com.evolent.health.exceptions.ContactNotFoundException;
import com.evolent.health.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	/**
	 * Fetch all the Contacts
	 *
	 * @param None
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 * @return List of Contact
	 *
	 */
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	
	/**
	 * Fetch Contacts based on ID
	 *
	 * @param id
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 * @return Contact
	 * @throws ContactNotFoundException
	 *
	 */
	public Contact getContactById(Long id) throws ContactNotFoundException {

		Optional<Contact> contact = contactRepository.findById(id);

		if (contact.isPresent()) {
			return contact.get();
		} else {
			throw new ContactNotFoundException("No contact record exist for given id:" + id);
		}
	}

	
	/**
	 * Update Contact
	 *
	 * @param Contact,Id
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 * @return Contact
	 * @throws ContactNotFoundException
	 *
	 */
	public Contact updateContact(Contact entity, Long id) throws ContactNotFoundException {

		Optional<Contact> contact = contactRepository.findById(id);

		if (contact.isPresent()) {
			System.out.println("update conctact");
			Contact newEntity = contact.get();
			newEntity.setEmail(entity.getEmail());
			newEntity.setFirstName(entity.getFirstName());
			newEntity.setLastName(entity.getLastName());
			newEntity.setMobileNumber(entity.getMobileNumber());
			newEntity.setStatus(entity.getStatus());
			newEntity = contactRepository.save(newEntity);
			return newEntity;
		} else {
			throw new ContactNotFoundException("No contact record exist for given id:" + id);
		}
	}

	
	/**
	 * Save contact
	 *
	 * @param Contact,Id
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 * @return Contact
	 * @throws None
	 *
	 */
	public Contact saveContact(Contact entity) {
		return contactRepository.save(entity);
	}

	
	/**
	 * Delete contact
	 *
	 * @param Id
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 * @return void
	 * @throws ContactNotFoundException
	 *
	 */
	public void deleteContactById(Long id) throws ContactNotFoundException {

		Optional<Contact> contact = contactRepository.findById(id);

		if (contact.isPresent()) {
			contactRepository.deleteById(id);
		} else {
			throw new ContactNotFoundException("No contact record exist for given id:" + id);
		}
	}

	/**
	 * Update Status of user (Active/Inactive)
	 *
	 * @param isActive,Id
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 * @return Contact
	 * @throws ContactNotFoundException
	 *
	 */
	public Contact updateStatus(Boolean isActive, Long id) throws ContactNotFoundException {

		Optional<Contact> contact = contactRepository.findById(id);

		if (contact.isPresent()) {
			Contact contactEntity = contact.get();
			
			System.out.println("update Status");
			
			contactEntity.setStatus(isActive);
			return contactRepository.save(contactEntity);
		} else {
			throw new ContactNotFoundException("No contact record exist for given id:" + id);
		}
	}


}
