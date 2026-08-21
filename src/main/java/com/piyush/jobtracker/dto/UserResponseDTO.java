package com.piyush.jobtracker.dto;

import com.piyush.jobtracker.enums.Role;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String college;
    private int yearOfPassing;
    private Role role;

    public UserResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(int yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserResponseDTO(Long id,
                           String name,
                           String email,
                           String college,
                           int yearOfPassing,
                           Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.college = college;
        this.yearOfPassing = yearOfPassing;
        this.role = role;
    }
}
