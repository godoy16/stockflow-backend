package com.stockflow.demo.mapper;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.stockflow.demo.dto.StockAlertDto;
import com.stockflow.demo.entity.Product;
import com.stockflow.demo.entity.StockAlert;
import com.stockflow.demo.enums.Severity;

@Component
public class StockAlertMapper {
	
	public StockAlert toEntity(Product product,Severity severity) {
		return StockAlert.builder()
				.currentStock(product.getCurrentStock())
				.productId(product.getId())
				.productName(product.getName())
				.severity(severity)
				.minStock(product.getMinStock())
				.build();
	}
	
	public StockAlertDto toDto(StockAlert stock) {
		if(stock==null)
			return null;
		return StockAlertDto.builder()
				.currentStock(stock.getCurrentStock())
				.minStock(stock.getMinStock())
				.productId(stock.getProductId())
				.productName(stock.getProductName())
				.severity(stock.getSeverity())
				.build();
	}
	
	public List<StockAlertDto> toDtoList(List<StockAlert> stockAlerts){
		if(stockAlerts.isEmpty())
			return Collections.emptyList();
		return stockAlerts.stream().map(this::toDto).toList();
	}

}
