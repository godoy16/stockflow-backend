package com.stockflow.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stockflow.demo.dto.MovementRequest;
import com.stockflow.demo.dto.MovementResponse;

public interface MovementService {
	
	MovementResponse saveMovement(MovementRequest movementRequest);
	Page<MovementResponse> findByProductId(Long id,Pageable pageable);

}
