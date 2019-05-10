/**
 * 
 */
package com.dano.classDiaryApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dano.classDiaryApplication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByEmail (String email);

}
