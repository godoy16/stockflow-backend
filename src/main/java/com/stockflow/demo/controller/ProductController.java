package com.stockflow.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockflow.demo.config.ApiPath;
import com.stockflow.demo.dto.ProductDto;
import com.stockflow.demo.service.ProductService;

@RestController
@RequestMapping(ApiPath.BASE_V1)
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id){
		ProductDto productDto = productService.getProductById(id);
		return ResponseEntity.ok(productDto);
	}
	@GetMapping("/products")
	public ResponseEntity<Page<ProductDto>> findByCategory(@RequestParam("category") String category,@PageableDefault(page = 0,
            size = 10,
            sort = "id",
            direction = Sort.Direction.ASC)Pageable pageable){
		Page<ProductDto> productDtos = productService.getProductByCategory(category, pageable);
		return ResponseEntity.ok(productDtos);
	}
	

}
