package com.example.usuarios.exception.handler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.usuarios.exception.DuplicateResourceException;
import com.example.usuarios.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<Object> handleDuplicateResourceException(
			DuplicateResourceException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.CONFLICT.value());
		body.put("error", HttpStatus.CONFLICT.getReasonPhrase());
		body.put("message", ex.getMessage());
		body.put("path", request.getDescription(false).replace("uri=", ""));
		
		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}
	
//	@ExceptionHandler(InvalidArgumentsExcepcion.class)
//	public ResponseEntity<Object> handleDuplicateResourceException(
//			InvalidArgumentsExcepcion ex, WebRequest request) {
//		
//		Map<String, Object> body = new LinkedHashMap<>();
//		body.put("timestamp", LocalDateTime.now());
//		body.put("status", HttpStatus.BAD_REQUEST.value());
//		body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
//		body.put("message", ex.getMessage());
//		body.put("path", request.getDescription(false).replace("uri=", ""));
//		
//		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//	}

	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase()); 

        
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("messages", errors); 
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, headers, status);
    }
}
