package com.example.service;

import static com.example.util.Constant.ACTIVATION_EMAIL;
import static java.time.Instant.now;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.AuthenticationResponse;
import com.example.dto.LoginRequest;
import com.example.dto.RegisterRequest;
import com.example.exception.DemoException;
import com.example.model.NotificationEmail;
import com.example.model.User;
import com.example.model.VerificationToken;
import com.example.repository.UserRepository;
import com.example.repository.VerificationTokenRepository;
import com.example.security.JwtProvider;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
        
    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setCreated(now());
        user.setEnabled(false);

        userRepository.save(user);
       log.info("User Registered Successfully, Sending Authentication Email");
       String token = generateVerificationToken(user);
        String message = mailContentBuilder.build("Thank you for signing up to Spring Reddit, please click on the below url to activate your account : "
              + ACTIVATION_EMAIL + "/" + token);

        mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), message));
    }

    private String generateVerificationToken (User user) {
    	String token = UUID.randomUUID().toString();
    	VerificationToken verificationToken = new VerificationToken();
    	verificationToken.setToken(token);
    	verificationToken.setUser(user);
    	verificationTokenRepository.save(verificationToken);
    	return token;
    }
    
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
    
    public AuthenticationResponse login(LoginRequest loginRequest) {
    	Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    	SecurityContextHolder.getContext().setAuthentication(authenticate);
    	String autheticationToken = jwtProvider.generateToken(authenticate);
    	return new AuthenticationResponse (autheticationToken, loginRequest.getUsername());
    }
    
    public void verifyAccount (String token) {
    	Optional <VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
    	verificationTokenOptional.orElseThrow(() -> new DemoException("Invalid Token"));
    	fetchUserAndEnable(verificationTokenOptional.get());
    }
    
    @Transactional
    private void fetchUserAndEnable (VerificationToken verificationToken) {
    	String username = verificationToken.getUser().getUsername();
    	User user = userRepository.findByUsername(username).orElseThrow(() -> new DemoException ("User not Found with id -" + username));
    	user.setEnabled(true);
    	userRepository.save(user);
    }
}
