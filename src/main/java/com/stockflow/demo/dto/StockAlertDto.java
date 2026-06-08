package com.stockflow.demo.dto;


import com.stockflow.demo.enums.Severity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StockAlertDto {
	private Long productId;
	private String productName;
	private int minStock;
	private int currentStock;
	@Enumerated(EnumType.STRING)
	private Severity severity;

}
