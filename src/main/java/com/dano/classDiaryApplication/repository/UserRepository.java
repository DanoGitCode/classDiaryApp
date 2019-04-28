/**
 * 
 */
package com.dano.classDiaryApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dano.classDiaryApplication.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

}
