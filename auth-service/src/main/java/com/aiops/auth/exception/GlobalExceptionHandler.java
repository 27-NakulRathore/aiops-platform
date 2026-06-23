package com.aiops.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aiops.auth.common.ErrorResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(ResourceAlreadyExistsException.class)
//    public ResponseEntity<Map<String, Object>> handleAlreadyExists(
//            ResourceAlreadyExistsException ex) {
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("success", false);
//        response.put("message", ex.getMessage());
//        response.put("timestamp", LocalDateTime.now());
//
//        return ResponseEntity.status(HttpStatus.CONFLICT)
//                .body(response);
//    }
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleAlreadyExists(
	        ResourceAlreadyExistsException ex) {

	    return ResponseEntity.status(HttpStatus.CONFLICT)
	            .body(
	                    ErrorResponse.builder()
	                            .success(false)
	                            .message(ex.getMessage())
	                            .errorCode("USER_ALREADY_EXISTS")
	                            .timestamp(LocalDateTime.now())
	                            .build()
	            );
	}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, Object> response = new HashMap<>();

        response.put(
                "message",
                ex.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage());

        response.put("success", false);

        return ResponseEntity.badRequest().body(response);
    }
}