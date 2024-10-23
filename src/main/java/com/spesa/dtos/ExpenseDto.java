package com.spesa.dtos;


public record ExpenseDto(String description, double value, String category, String date, String userId) {

}
