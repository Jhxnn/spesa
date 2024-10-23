
package com.spesa.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="expenses")
public class Expense {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID expenseId;
	private String description;
	private double value;
	private String category;
	private String date;
	private String userId;
	public UUID getExpenseId() {
		return expenseId;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "  \n  \n Descrição: " + description + "\n Valor: " + value + "\n Categoria: "
				+ category + " \n Data: " + date + "\n \n";
	}
	
}
