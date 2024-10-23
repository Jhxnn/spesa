package com.spesa.dtos;


public record UserDto(
		String name, 
		String username, 
		String password,
		String role) {

}
