package com.evolent.health.service;

import java.util.List;

import com.evolent.health.entity.Contact;
import com.evolent.health.exceptions.ContactNotFoundException;

public interface ContactService{
	
	public List<Contact> getAllContacts();

	public Contact getContactById(Long id) throws ContactNotFoundException ;

	public Contact updateContact(Contact entity, Long id) throws ContactNotFoundException ;

	public Contact saveContact(Contact entity);

	public void deleteContactById(Long id) throws ContactNotFoundException;
	
	public Contact updateStatus(Boolean isActive, Long id) throws ContactNotFoundException;

}
