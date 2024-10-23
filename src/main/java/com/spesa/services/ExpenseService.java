package com.spesa.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spesa.dtos.ExpenseDto;
import com.spesa.models.Expense;
import com.spesa.repositories.ExpenseRepository;

@Service
public class ExpenseService {
	
	
	@Autowired
	ExpenseRepository expenseRepository;
	
	public List<Expense> getUserExpenses(String id) {
		
		return expenseRepository.findByuserId(id);
	}
	
	public Expense createExpense(ExpenseDto expenseDto) {
		
		var expense = new Expense();
		BeanUtils.copyProperties(expenseDto, expense);
		return expenseRepository.save(expense);
	}
	
	public double totalExpense(String id) {
		
		return expenseRepository.expenseTotal(id);
	}
	
	public Expense updateExpense(UUID id, ExpenseDto expenseDto) {
		
		var expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("despesa não encontrada"));
		
		expense.setCategory(expenseDto.category());
		expense.setDate(expenseDto.date());
		expense.setDescription(expenseDto.description());
		expense.setValue(expenseDto.value());
		
		return expenseRepository.save(expense);
		
		
	}
	
	public void deleteExpense(UUID id) {
		
		var expense = expenseRepository.findById(id).orElseThrow(()-> new RuntimeException("Despesa não encontrada"));
		expenseRepository.delete(expense);
		
		
	}

}


