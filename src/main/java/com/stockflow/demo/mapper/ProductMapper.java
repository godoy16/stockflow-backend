package com.stockflow.demo.mapper;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.stockflow.demo.dto.ProductDto;
import com.stockflow.demo.entity.Product;

@Component
public class ProductMapper {
	
	public ProductDto toDto(Product product) {
		if(product==null)
			return null;
		return ProductDto.builder()
				.id(product.getId())
				.sku(product.getSku())
				.name(product.getName())
				.category(product.getCategory())
				.minStock(product.getMinStock())
				.currentStock(product.getCurrentStock())
				.unitPrice(product.getUnitPrice())
				.build();
	}
	public List<ProductDto> toDtoList(List<Product> productList){
		if(productList.isEmpty())
			return Collections.emptyList();
		return productList.stream().map(this::toDto).toList();
	}

}
