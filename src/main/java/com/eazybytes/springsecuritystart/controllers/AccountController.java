package com.eazybytes.springsecuritystart.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myAccount")
public class AccountController {

    @GetMapping
    public String getAccountDetails(){
        return "Account Details";
    }
}
