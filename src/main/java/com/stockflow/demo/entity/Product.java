package com.stockflow.demo.entity;


import com.stockflow.demo.exception.MethodArgumentNotValidException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="sku",nullable=false,length=100)
	@Size(max=100,message="EL SKU tiene un maximo de 100 caracteres")
	@NotBlank(message="SKU es mandatorio")
	private String sku;
	@Column(name="name",nullable=false,length=100)
	@Size(max=100,message="EL nombre tiene un maximo de 100 caracteres")
	@NotBlank(message="El campo nombre es mandatorio")
	private String name;
	@Column(name="category",nullable=false,length=100)
	@Size(max=100,message="La categoria tiene un maximo de 100 caracteres")
	@NotBlank(message="El campo categoria es mandatorio")
	private String category;
	@Column(name="min_stock",nullable=false)
	@Min(value=0)
	private int minStock;
	@Column(name="current_stock",nullable=false)
	@Min(value=0)
	private int currentStock;
	@NotNull(message = "El Precio es obligatorio")
	@DecimalMin(value = "0.0", inclusive = false, message = "Precio debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2)
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
	private Long unitPrice;
	
	public void addStock(int quantity) {
		if(quantity<=0) {
			throw new MethodArgumentNotValidException("La cantidad a agregar debe ser mayoy a cero");
		}
		this.currentStock+=quantity;
	}
	public void removeStok(int quantity) {
		if(quantity<=0) {
			throw new MethodArgumentNotValidException("La cantidad de restar debe ser mayor a cero.");
		}
		if(this.currentStock<quantity) {
			throw new MethodArgumentNotValidException("Cantidad insuficiente de Stock para realizar la operacion.");
		}
		this.currentStock-=quantity;
	}
	

}
