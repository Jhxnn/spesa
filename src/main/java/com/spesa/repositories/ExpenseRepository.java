package com.spesa.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spesa.models.Expense;
import com.spesa.models.Revenue;

public interface ExpenseRepository extends JpaRepository<Expense, UUID>{

	
	public List<Expense> findByuserId(String id);
	

    @Query("SELECT SUM(r.value) FROM Expense r WHERE r.userId = :cond")
    public Double expenseTotal(@Param("cond") String id);

	}
