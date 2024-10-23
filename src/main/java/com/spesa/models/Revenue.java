

package com.spesa.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "revenues")
public class Revenue {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID revenueID;
	private String description;
	private double value;
	private String category;
	private String data;
	private String userId;
	
	public UUID getRevenueID() {
		return revenueID;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
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
				+ category + " \n Data: " + data + "\n \n \n";
	} 
	
	
}
