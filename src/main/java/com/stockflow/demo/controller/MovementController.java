package com.stockflow.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockflow.demo.config.ApiPath;
import com.stockflow.demo.dto.MovementRequest;
import com.stockflow.demo.dto.MovementResponse;
import com.stockflow.demo.service.MovementService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(ApiPath.BASE_V1)
public class MovementController {
	@Autowired
	MovementService movementService;
	
	@PostMapping("/movements")
	public ResponseEntity<MovementResponse> createMovement(@Valid @RequestBody MovementRequest request){
		MovementResponse response = movementService.saveMovement(request);
		return ResponseEntity.ok(response);
	}
	@GetMapping("/movements/{productId}/history")
	public ResponseEntity<Page<MovementResponse>> getHistoryMovementByProduct(@PathVariable("productId") Long productId ,
			@PageableDefault(page = 0,
            size = 10,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
		Page<MovementResponse> movements=movementService.findByProductId(productId, pageable);
		return ResponseEntity.ok(movements);
	}

}
