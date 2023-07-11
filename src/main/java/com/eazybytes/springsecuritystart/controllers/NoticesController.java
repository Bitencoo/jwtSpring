package com.eazybytes.springsecuritystart.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notices")
public class NoticesController {

    @GetMapping
    public String getNotices() {
        return "Notices";
    }
}
