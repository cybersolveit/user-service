package com.cybersolveit.userservice.dto;

import lombok.Data;

@Data
public class StudentResponse {
    private Long studentId;
    private String email;
    private String firstName;
    private String lastName;
    private String course;
    private String addedBy;

}
