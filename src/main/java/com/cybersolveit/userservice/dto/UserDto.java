package com.cybersolveit.userservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

// DTO -> Data Transfer object
@Data
public class UserDto {

    @Email(message = "Should be a proper email")
    @NotBlank(message = "should not be blank")
    private String email;

    @NotBlank(message = "first name should not be blank")
    @Size(min = 4,max = 8,message = "first name should be between 4 to 8 character")
    @Pattern(regexp = "^[a-zA-Z]*$",message = "should not be have any specials character or numbers")
    private String firstName;



    @NotBlank(message = "lastname should not be blank")
    private String lastName;
}
