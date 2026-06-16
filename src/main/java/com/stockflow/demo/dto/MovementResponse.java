package com.stockflow.demo.dto;



import java.sql.Timestamp;

import com.stockflow.demo.enums.MovementType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovementResponse {
	private Long id;
	private String productName;
	private int productCurrentStock;
	private String productSku;
	private String productCategory;
	private Long productPrice;
	private MovementType type;
	private int quantity;
	private String reason;
	private Timestamp timestamp;

}
