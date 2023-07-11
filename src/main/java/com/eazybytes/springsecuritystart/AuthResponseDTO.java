package com.eazybytes.springsecuritystart;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String email;
    private String accessToken;

    public AuthResponseDTO() { }

    public AuthResponseDTO(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }
}
