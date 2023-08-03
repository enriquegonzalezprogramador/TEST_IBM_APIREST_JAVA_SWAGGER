package com.juniorenriquegonalez.springboot.web.apirest.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorenriquegonalez.springboot.web.apirest.models.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{
		
	Optional<User> findByEmail(String email);
}
