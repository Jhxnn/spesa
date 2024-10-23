package com.spesa.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.spesa.models.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	
	UserDetails findByUsername(String username);

}
