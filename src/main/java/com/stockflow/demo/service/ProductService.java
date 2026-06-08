package com.stockflow.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stockflow.demo.dto.ProductDto;
import com.stockflow.demo.entity.Product;

public interface ProductService {
	
	ProductDto getProductById(Long id);
	Page<ProductDto> getProductByCategory(String category,Pageable pageable);
	Product getEntityById(Long id);
	void saveProduct(Product product);
	

}
