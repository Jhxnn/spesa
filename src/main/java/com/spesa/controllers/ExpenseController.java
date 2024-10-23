package com.spesa.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.spesa.dtos.ExpenseDto;
import com.spesa.models.Expense;
import com.spesa.services.ExpenseService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/expense")

public class ExpenseController {

	
	@Autowired
	
	ExpenseService expenseService;
	
	
	@GetMapping("/{UserId}")
	@Operation(description = "Busca todas as despesas de um usuario passando seu id pela URL")
	public ResponseEntity<List<Expense>> getExpenses(@PathVariable(value="UserId") String id){
		return ResponseEntity.status(HttpStatus.OK).body(expenseService.getUserExpenses(id));
		
	}
	
	@GetMapping("/total/{UserId}")
	@Operation(description = "Calcula o valor total das despesas de um determinado usuario pelo seu id passado na URL")
	public ResponseEntity<Double> totalExpense(@PathVariable(value = "UserId") String id){
		
		double total = expenseService.totalExpense(id);
		return ResponseEntity.status(HttpStatus.OK).body(total);
	}
	@PostMapping
	@Operation(description = "Cria uma despesa")
	public ResponseEntity<Expense> createExpense(@RequestBody @Valid ExpenseDto expenseDto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.createExpense(expenseDto));
	}
	
	@PutMapping("/{id}")
	@Operation(description = "Atualiza a despesa passada pela URL")
	public ResponseEntity<Expense> updateExpense(@PathVariable(value = "id") UUID id, @RequestBody @Valid ExpenseDto expenseDto){
		
		return ResponseEntity.status(HttpStatus.OK).body(expenseService.updateExpense(id, expenseDto));
	}
	
	@DeleteMapping("/{id}")
	@Operation(description = "Deleta a despesa passada na URL")
	public ResponseEntity<Expense> deleteExpense(@PathVariable(value = "id") UUID id){
		
		expenseService.deleteExpense(id);
		return ResponseEntity.noContent().build();
		
	}
}
