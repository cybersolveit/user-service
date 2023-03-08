package com.cybersolveit.userservice.exception;

import com.cybersolveit.userservice.dto.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        List<FieldError> fieldErrors = ex.getFieldErrors();
        ApiResponse apiResponse= new ApiResponse();
        List<String> errors= new ArrayList<>();
        for(FieldError error: fieldErrors){
            errors.add(error.getDefaultMessage());
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Validation Error");
        apiResponse.setErrors(errors);
        return ResponseEntity.badRequest().body(apiResponse);

    }

    @ExceptionHandler({UserServiceException.class,UserNotFoundException.class})
    public ApiResponse handleException(Exception ex){
        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setMessage("Something went wrong");
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setErrors(ex.getMessage());
        return apiResponse;
    }



}

