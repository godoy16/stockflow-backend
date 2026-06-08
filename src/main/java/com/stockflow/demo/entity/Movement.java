package com.stockflow.demo.entity;

import com.stockflow.demo.enums.MovementType;
import com.stockflow.demo.enums.Severity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="movements")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Movement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
	private Product product;
	@Column(name="type",nullable=false,length=100)
	@Enumerated(EnumType.STRING)
	private MovementType type;
	@Column(name="quantity",nullable=false)
	@Min(value=0,message="La cantidad no debe ser menor a cero")
	private int quantity;
	@Column(name="reason")
	@Size(max=100,message="La razon no debe superar 100 caracteres.")
	private String reason;

}
