package com.stockflow.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stockflow.demo.dto.StockAlertDto;
import com.stockflow.demo.entity.StockAlert;

public interface StockAlertService {
	Page<StockAlertDto> findAll(Pageable pageable);
	StockAlertDto saveAlert(StockAlert alert);
	
	
	
}
