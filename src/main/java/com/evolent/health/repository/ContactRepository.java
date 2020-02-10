/**
 * 
 */
package com.evolent.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evolent.health.entity.Contact;

/**
 * Jpa Repository for Contact 
 * @author Bharat Chaudhari
 * @date Feb 10, 2020
 */

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
