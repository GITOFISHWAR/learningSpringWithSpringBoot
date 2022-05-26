/**
 * 
 */
package com.learnspringboot.learningspring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ishwarsingh
 *
 */

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
	
	Guest findGuestByGuestId(Long id);
	Guest findGuestByFirstName(String fName);

}
