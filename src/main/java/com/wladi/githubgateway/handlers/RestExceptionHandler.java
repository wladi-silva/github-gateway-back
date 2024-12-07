package com.wladi.githubgateway.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wladi.githubgateway.exceptions.ErrorResponse;
import com.wladi.githubgateway.exceptions.GithubEmptyRepositoriesException;

import feign.FeignException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GithubEmptyRepositoriesException.class)
    public ResponseEntity<ErrorResponse> githubEmptyRepositoriesExceptionHandler(GithubEmptyRepositoriesException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorResponse> feignExceptionNotFoundHandler(FeignException exception) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "Not found in Github");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception exception) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

}
