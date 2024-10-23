package com.spesa.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spesa.dtos.AuthDto;
import com.spesa.dtos.LoginResponseDto;
import com.spesa.dtos.UserDto;
import com.spesa.infra.security.TokenService;
import com.spesa.models.User;
import com.spesa.repositories.UserRepository;
import com.spesa.services.userService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	userService service;
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	TokenService tokenService;
	
	@PostMapping("/login")
	@Operation(description = "Login de usuario com autenticação JWT")
	public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthDto authDto){
		var userPassword = new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());
		var auth = this.authManager.authenticate(userPassword);
		
		var token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDto(token));
	}
	@PostMapping("/register")
	@Operation(description = "Registro de conta do usuario")
	public ResponseEntity<User> createUser(@RequestBody @Valid UserDto userDtos) {
		
		if(repository.findByUsername(userDtos.username()) != null) return ResponseEntity.badRequest().build();
		String encryptedPass = new BCryptPasswordEncoder().encode(userDtos.password());
		var user = new User();
		BeanUtils.copyProperties(userDtos, user);
		user.setPassword(encryptedPass);
		return ResponseEntity.status(HttpStatus.OK).body(service.create(user));
		
	}
	
}
