package com.cybersolveit.userservice.dto;

import lombok.Data;

@Data
public class ApiResponse {

    private Integer statusCode;
    private Object data;
    private String message;
    private Object errors;
}
