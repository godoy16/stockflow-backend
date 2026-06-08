package com.stockflow.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockflow.demo.dto.MovementRequest;
import com.stockflow.demo.dto.MovementResponse;
import com.stockflow.demo.entity.Movement;
import com.stockflow.demo.entity.Product;
import com.stockflow.demo.entity.StockAlert;
import com.stockflow.demo.enums.MovementType;
import com.stockflow.demo.enums.Severity;
import com.stockflow.demo.exception.InsufficientStockException;
import com.stockflow.demo.mapper.MovementMapper;
import com.stockflow.demo.repository.MovementRepository;
@Service
public class MovementSeriviceImpl implements MovementService{
	
	@Autowired
	ProductService productService;
	@Autowired 
	MovementRepository movementRepository;
	@Autowired 
	StockAlertService stockAlertService;
	@Autowired
	MovementMapper movementMapper;
	
	@Transactional
	@Override
	public MovementResponse saveMovement(MovementRequest movementRequest) {
		Product product = productService.getEntityById(movementRequest.getProductId());
		if(product.getCurrentStock()<movementRequest.getQuantity()&& movementRequest.getType().equals(MovementType.OUT))
			throw new InsufficientStockException(
					String.format("Stock insuficiente para el producto %s. Stock Actual: %d, cantidad solicitada: %d.",
							product.getName(),product.getCurrentStock(),movementRequest.getQuantity()));
		Product productUpated=updateCurrentStock(product,movementRequest.getType(),movementRequest.getQuantity());
		Movement movement = movementMapper.toEntity(movementRequest, productUpated);
		Movement movementSave = movementRepository.saveAndFlush(movement);
		MovementResponse response =  movementMapper.toDto(movementSave);
		productService.saveProduct(productUpated);
		StockAlert alert = stockAlertEntity(productUpated);
		stockAlertService.saveAlert(alert);
		return response;
	}
	@Transactional(readOnly = true)
	@Override
	public Page<MovementResponse> findByProductId(Long id,Pageable pageable) {
		Page<Movement> movementList = movementRepository.findByProductId(id, pageable);
		return movementList.map(movementMapper::toDto);
	}
	
	private Product updateCurrentStock(Product product,MovementType type, int quantity) {
		if(type.equals(MovementType.IN)) {
			product.addStock(quantity);
		}else {
			product.removeStok(quantity);
		}
		return product;
	}
	private StockAlert stockAlertEntity(Product product) {
		Severity severity = product.getCurrentStock()<=product.getMinStock()?Severity.CRITICAL:Severity.LOW;
		return StockAlert.builder()
				.currentStock(product.getCurrentStock())
				.productId(product.getId())
				.productName(product.getName())
				.minStock(product.getMinStock())
				.severity(severity).build();
				
	}

}
