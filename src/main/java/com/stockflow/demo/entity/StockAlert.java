package com.stockflow.demo.entity;

import com.stockflow.demo.enums.Severity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="stock_alerts")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StockAlert {
	@Id
	private Long productId;
	@Column(name="product_name",nullable=false,length=100)
	@Size(max=100,message="EL nombre tiene un maximo de 100 caracteres")
	@NotBlank(message="El campo nombre es mandatorio")
	private String productName;
	@Column(name="min_stock",nullable=false)
	@Min(value=0)
	private int minStock;
	@Column(name="current_stock",nullable=false)
	@Min(value=0)
	private int currentStock;
	@Column(name="severity")
	@Enumerated(EnumType.STRING)
	private Severity severity;
}
