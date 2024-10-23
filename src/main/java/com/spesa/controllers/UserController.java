package com.spesa.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spesa.dtos.UserDto;
import com.spesa.models.User;
import com.spesa.services.userService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
		
	
	@Autowired
	userService service;
	
	
	@GetMapping
	@Operation(description = "Busca todos os usuarios (apenas Administradores)")
	public ResponseEntity<List<User>> findAll(){
		var users = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	
	@GetMapping("/sobra/{id}")
	@Operation(description = "Calcula a sobra mensal do usuario (receitas - despesas)")
	public ResponseEntity<Double> sobra(@PathVariable(value = "id") String id){
		return ResponseEntity.status(HttpStatus.OK).body(service.sobra(id));
		
	}
	
	
	@GetMapping("/{id}")
	@Operation(description = "Busca usuario pelo id")
	public ResponseEntity<User> findById(@PathVariable(value = "id") UUID id) {
		var user = service.findById(id);
		return  ResponseEntity.status(HttpStatus.OK).body(user);
		
	}
	
	@PutMapping("/{id}")
	@Operation(description = "Atualiza os dados do usuario")
	public ResponseEntity<User> updateUser(@RequestBody @Valid UserDto userDto, @PathVariable(value = "id") UUID id){
		
		var user = new User();
		BeanUtils.copyProperties(userDto, user);
		
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, user));
	}
	@GetMapping("/pdf/{id}")
	@Operation(description = "Gera um relatório em pdf com: receitas, despesas, valor total das despesas e das receitas, e a sobra")
	public ResponseEntity<byte[]> gerarPdf(@PathVariable UUID id) {
	    byte[] pdfBytes = service.gerarPdf(id);

	    HttpHeaders headers = new HttpHeaders();

	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio.pdf");
	    headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

	    return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}

	
	@DeleteMapping("/{id}")
	@Operation(description = "Deleta usuario")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id")UUID id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	
	

}
