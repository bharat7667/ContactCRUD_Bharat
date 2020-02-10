package com.evolent.health.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.evolent.health.entity.Contact;
import com.evolent.health.exceptions.ContactNotFoundException;
import com.evolent.health.repository.ContactRepository;
import com.evolent.health.service.ContactServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ContactTest {

	@Mock
	private ContactRepository contactRepository;

	@InjectMocks
	private ContactServiceImpl contactServiceimp;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	/**
	 * Test method getAllContacts at contactService
	 * 
	 * @author Bharat.chaudhari
	 * @date Feb 10, 2020
	 */
	@Test
	public void testGetAllContacts() {
		Contact c1 = new Contact((long) 1, "Bharat", "chaudhari", "bgchaudhari1603@gmail.com", "9727282750", true);
		Contact c2 = new Contact((long) 2, "Sandeep", "srivastava", "sandeepsrivastava2103@gmail.com", "8797754984",
				true);
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(c1);
		contacts.add(c2);

		when(contactRepository.findAll()).thenReturn(contacts);

		List<Contact> responseContact = this.contactServiceimp.getAllContacts();

		assertEquals("Bharat", responseContact.get(0).getFirstName());
		assertEquals("bgchaudhari1603@gmail.com", responseContact.get(0).getEmail());
		assertTrue(responseContact.get(0).getStatus());
		assertEquals("Sandeep", responseContact.get(1).getFirstName());
		verify(contactRepository, times(1)).findAll();
	}

	/**
	 * Test method GetContactById at contactService
	 * 
	 * @author Bharat.chaudhari
	 * @throws ContactNotFoundException
	 * @date Feb 10, 2020
	 */
	@Test
	public void testGetContactById() throws ContactNotFoundException {
		Contact c1 = new Contact((long) 1, "Bharat", "chaudhari", "bgchaudhari1603@gmail.com", "9727282750", true);

		when(contactRepository.findById((long) 1)).thenReturn(Optional.of(c1));

		Contact responseContact = this.contactServiceimp.getContactById((long) 1);

		assertEquals("Bharat", responseContact.getFirstName());
		assertEquals("bgchaudhari1603@gmail.com", responseContact.getEmail());
		assertTrue(responseContact.getStatus());
		verify(contactRepository, times(1)).findById((long) 1);
	}

	
	/**
	 * Test method GetContactById at contactService if contact id is not found then
	 * it will throw ContactNotFoundException
	 * 
	 * @author Bharat.chaudhari
	 * @throws ContactNotFoundException
	 * @date Feb 10, 2020
	 */
	@Test
	public void testGetContactByIdException() throws ContactNotFoundException {
		when(contactRepository.findById((long) 7)).thenReturn(Optional.ofNullable(null));
		this.exception.expect(ContactNotFoundException.class);
		this.contactServiceimp.getContactById((long) 7);
		verify(contactRepository, times(1)).findById((long) 7);
	}

}
