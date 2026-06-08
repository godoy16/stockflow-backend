package com.stockflow.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockflow.demo.dto.ProductDto;
import com.stockflow.demo.entity.Product;
import com.stockflow.demo.exception.ProductNotFoudException;
import com.stockflow.demo.mapper.ProductMapper;
import com.stockflow.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductMapper productMapper;
	@Transactional(readOnly = true)
	@Override
	public ProductDto getProductById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoudException("No se encontro el producto."));
		return productMapper.toDto(product);
		
	}
	@Transactional(readOnly = true)
	@Override
	public Page<ProductDto> getProductByCategory(String category,Pageable pageable) {
		Page<Product> productList = productRepository.findByCategory(category, pageable);
		return productList.map(productMapper::toDto);
	}
	@Transactional(readOnly = true)
	@Override
	public Product getEntityById(Long id) {
		return productRepository.findById(id).orElseThrow(()-> new ProductNotFoudException("No se encontro el producto."));
	}
	@Transactional(readOnly = true)
	@Override
	public void saveProduct(Product product) {
		productRepository.saveAndFlush(product);
		
	}

}
