package com.eazybytes.springsecuritystart.controllers;

import com.eazybytes.springsecuritystart.AuthDTO;
import com.eazybytes.springsecuritystart.AuthResponseDTO;
import com.eazybytes.springsecuritystart.config.EazyBankUsernamePwdAuthenticationProvider;
import com.eazybytes.springsecuritystart.model.Customer;
import com.eazybytes.springsecuritystart.repositories.CustomerRepository;
import com.eazybytes.springsecuritystart.utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EazyBankUsernamePwdAuthenticationProvider authenticationProvider;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        Customer savedCustomer = null;
        ResponseEntity response = null;
        try {
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> registerUser(@RequestBody AuthDTO customer) {
        Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword()));
        String token = jwtTokenUtil.generateAccessToken(authentication);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(authentication.getPrincipal().toString(), token);
        return new ResponseEntity(authResponseDTO, HttpStatus.OK);
    }

}
