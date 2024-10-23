package com.spesa.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.spesa.models.User;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		
		
		try {
			
			Algorithm algoritm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("spesa")
					.withSubject(user.getUsername())
					.withExpiresAt(getExpirationDate())
					.sign(algoritm);
			return token;
		}
		catch(JWTCreationException exception) {
			throw new RuntimeException("erro ao gerar token", exception);
		}


	}
	public String validateToken(String token) {
		
		
		try{
		Algorithm algoritm = Algorithm.HMAC256(secret);
		
		return JWT.require(algoritm)
				.withIssuer("spesa")
				.build()
				.verify(token)
				.getSubject();
		}
		catch(JWTVerificationException exception) {
			return "";
		}
	}

	private Instant getExpirationDate() {
	    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.ofHours(-3));
	}
}
