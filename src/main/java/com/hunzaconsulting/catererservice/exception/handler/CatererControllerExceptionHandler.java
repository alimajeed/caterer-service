package com.hunzaconsulting.catererservice.exception.handler;

import com.hunzaconsulting.catererservice.exception.BadRequestException;
import com.hunzaconsulting.catererservice.exception.ResourceNotFoundException;
import com.hunzaconsulting.catererservice.payload.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CatererControllerExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> resolveException(BadRequestException exception) {
        ApiResponse apiResponse = exception.getApiResponse();
        log.error("##BadRequestException##", exception);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> resolveException(ResourceNotFoundException exception) {
        ApiResponse apiResponse = exception.getApiResponse();
        log.error("##ResourceNotFoundException##", exception);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
