package com.dano.classDiaryApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dano.classDiaryApplication.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByRole(String role);
}
