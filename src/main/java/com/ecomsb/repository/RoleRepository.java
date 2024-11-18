package com.ecomsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomsb.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	

}
