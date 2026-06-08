package com.stockflow.demo.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ErrorResponse {
	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	

}
