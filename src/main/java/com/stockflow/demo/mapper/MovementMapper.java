package com.stockflow.demo.mapper;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.stockflow.demo.dto.MovementRequest;
import com.stockflow.demo.dto.MovementResponse;
import com.stockflow.demo.entity.Movement;
import com.stockflow.demo.entity.Product;



@Component
public class MovementMapper {
	
	public Movement toEntity(MovementRequest movementResquest,Product product) {
		if(movementResquest==null)
			return null;
		return Movement.builder()
				.product(product)
				.type(movementResquest.getType())
				.quantity(movementResquest.getQuantity())
				.reason(movementResquest.getReason())
				.build();
	}
	
	public MovementResponse toDto(Movement movement) {
		if(movement==null)
			return null;
		return MovementResponse.builder()
				.id(movement.getId())
				.productCategory(movement.getProduct().getCategory())
				.productCurrentStock(movement.getProduct().getCurrentStock())
				.productName(movement.getProduct().getName())
				.productPrice(movement.getProduct().getUnitPrice())
				.productSku(movement.getProduct().getSku())
				.quantity(movement.getQuantity())
				.reason(movement.getReason())
				.type(movement.getType())
				.timestamp(movement.getTimestap())
				.build();
				
	}
	public List<MovementResponse> toDtoResponse(List<Movement> movements){
		if(movements.isEmpty())
			return Collections.emptyList();
		return movements.stream().map(this::toDto).toList();
	}

}
