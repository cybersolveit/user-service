package com.cybersolveit.userservice.dto;


import lombok.Data;

// DTO -> Data Transfer object
@Data
public class UserDto {


    private String email;
    private String firstName;
    private String lastName;
}
