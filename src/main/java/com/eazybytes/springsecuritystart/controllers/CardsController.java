package com.eazybytes.springsecuritystart.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myCards")
public class CardsController {

    @GetMapping
    public String getCards() {
        return "Cards";
    }
}
