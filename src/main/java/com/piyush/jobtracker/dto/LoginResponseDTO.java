package com.piyush.jobtracker.dto;



public class LoginResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String token;

    public LoginResponseDTO(Long id, String name, String email, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getToken() { return token; }
}