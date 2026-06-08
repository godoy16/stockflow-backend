package com.stockflow.demo.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductDto {
	private Long id;
	private String sku;
	private String name;
	private String category;
	private int minStock;
	private int currentStock;
	private Long unitPrice;
}
