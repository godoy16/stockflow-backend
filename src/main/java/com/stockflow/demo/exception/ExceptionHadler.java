package com.stockflow.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHadler {
	@ExceptionHandler(ProductNotFoudException.class)
	public ResponseEntity<ErrorResponse> productNotFound(ProductNotFoudException ex, HttpServletRequest request){
		return buildResponse(HttpStatus.NOT_FOUND,ex.getMessage(),request.getRequestURI());
	}
	@ExceptionHandler(BussinesValidationException.class)
	public ResponseEntity<ErrorResponse> methodArgumentNoValid(BussinesValidationException ex, HttpServletRequest request){
		return buildResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),request.getRequestURI());
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> fieldValidationException(MethodArgumentNotValidException ex, HttpServletRequest request){
		return buildResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),request.getRequestURI());
	}
	@ExceptionHandler(GeneralException.class)
	public ResponseEntity<ErrorResponse> generalException(GeneralException ex, HttpServletRequest request){
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),request.getRequestURI());
	}
	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<ErrorResponse> unsufficientException(InsufficientStockException ex, HttpServletRequest request){
		return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY,ex.getMessage(),request.getRequestURI());
	}
	@ExceptionHandler(RequestNotPermitted.class)
	public ResponseEntity<ErrorResponse> rateLimiterException(RequestNotPermitted ex, HttpServletRequest request){
		return buildResponse(HttpStatus.TOO_MANY_REQUESTS,ex.getMessage()+ "Demasiadas solicitudes.",request.getRequestURI());
	}
	
	private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status,String message,String path){
		ErrorResponse response = ErrorResponse.builder()
				.error(status.name())
				.message(message)
				.path(path)
				.status(status.value())
				.timestamp(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(response,status);
		
	}

}
