/**
 * 
 */
package com.evolent.health.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ContactNotFoundException
 * 
 * @author Bharat
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends Exception {

	public ContactNotFoundException(String message) {
		super(message);
	}

}
