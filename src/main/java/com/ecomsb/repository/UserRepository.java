package com.ecomsb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomsb.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	  Optional<User> findUserByEmail(String email);
}
