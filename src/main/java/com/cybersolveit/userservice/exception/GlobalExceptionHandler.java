package com.cybersolveit.userservice.exception;

import com.cybersolveit.userservice.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserServiceException.class,UserNotFoundException.class})
    public ApiResponse handleException(Exception ex){
        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setMessage("Something went wrong");
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setErrors(ex.getMessage());
        return apiResponse;
    }
}

