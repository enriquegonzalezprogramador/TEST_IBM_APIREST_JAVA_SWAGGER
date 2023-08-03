package com.juniorenriquegonalez.springboot.web.apirest.models.services.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.juniorenriquegonalez.springboot.web.apirest.configAuth.JwtService;
import com.juniorenriquegonalez.springboot.web.apirest.controllers.auth.AuthenticationRequest;
import com.juniorenriquegonalez.springboot.web.apirest.controllers.auth.AuthenticationResponse;
import com.juniorenriquegonalez.springboot.web.apirest.controllers.auth.RegisterRequest;
import com.juniorenriquegonalez.springboot.web.apirest.models.dao.UserRepository;
import com.juniorenriquegonalez.springboot.web.apirest.models.entity.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	@Autowired
	private  UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	
	private AuthenticationManager  authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		var user = User.builder()
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		
		repository.save(user);
		
		var jwtToken = jwtService.generateToken(user);
		
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		// TODO Auto-generated method stub
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword())
				);
		
			var user = repository.findByEmail(request.getEmail())
							.orElseThrow();
		
			var jwtToken = jwtService.generateToken(user);
			
			return AuthenticationResponse.builder()
					.token(jwtToken)
					.build();
		}
	}
	
}
