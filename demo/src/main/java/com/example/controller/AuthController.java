package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;

import com.example.dto.AuthenticationResponse;
import com.example.dto.LoginRequest;
import com.example.dto.RegisterRequest;
import com.example.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/signup" )
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
	/*
	 * La chiamata richiede un oggetto RegisterRequest che è quindi un DTO
	 * Data Transfer Object con il quale trasferiamo dati dell'utente che saranno
	 * parte del RequestBody 
	 */
		authService.signup(registerRequest);
		return new ResponseEntity <>("User registration Successful!", OK);
	}
	
	
	@PostMapping("/login")
	public AuthenticationResponse login (@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
	
	
	@GetMapping("accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token){
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account Activated Successfully", OK);
	}
}
