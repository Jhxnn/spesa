package com.spesa.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
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

import com.spesa.dtos.RevenueDto;
import com.spesa.models.Revenue;
import com.spesa.services.RevenueService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

	@Autowired
	RevenueService revenueService;
	
	@GetMapping("/{UserId}")
	@Operation(description = "Busca todas as receitas de um usuario passando seu id pela URL")
	public ResponseEntity<List<Revenue>> userRevenues(@PathVariable(value = "UserId")String id){
		
		var revenues = revenueService.getUserRevenues(id);
		return ResponseEntity.status(HttpStatus.OK).body(revenues);
		
	}
	@GetMapping("/total/{UserId}")
	@Operation(description = "Calcula o valor total das receitas de um determinado usuario pelo seu id passado na URL")
	public ResponseEntity<Double> totalRevenues(@PathVariable(value="UserId") String id){
		
		double total = revenueService.totalRevenues(id);
		return ResponseEntity.status(HttpStatus.OK).body(total);
		
	}
	@PostMapping
	@Operation(description = "Cria uma receita")
	public ResponseEntity<Revenue> createRevenue(@RequestBody @Valid RevenueDto revenueDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(revenueService.createRevenue(revenueDto));
		
	}
	@PutMapping("/{id}")
	@Operation(description = "Atualiza a receita passada na URL")
	public ResponseEntity<Revenue> updateRevenue(@RequestBody @Valid RevenueDto revenueDto, @PathVariable(value = "id") UUID id){
		var revenue = new Revenue();
		BeanUtils.copyProperties(revenueDto,revenue);
		return ResponseEntity.status(HttpStatus.OK).body(revenueService.updateRevenue(id, revenue));
		
	}
	
	@DeleteMapping("/{id}")
	@Operation(description = "Deleta a receita passada na URL")
	public ResponseEntity<Revenue> deleteRevenue(@PathVariable(value = "id") UUID id) {
		
		revenueService.deleteRevenue(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
}
