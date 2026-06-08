package com.stockflow.demo.dto;

import com.stockflow.demo.enums.MovementType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovementRequest {
	
	private Long productId;
	@NotNull
	private MovementType type;
	private int quantity;
	private String reason;

}
