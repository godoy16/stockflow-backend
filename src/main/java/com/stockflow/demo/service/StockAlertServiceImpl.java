package com.stockflow.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockflow.demo.dto.StockAlertDto;
import com.stockflow.demo.entity.StockAlert;
import com.stockflow.demo.mapper.StockAlertMapper;
import com.stockflow.demo.repository.StockAlertRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
@Service
public class StockAlertServiceImpl implements StockAlertService{

	@Autowired
	StockAlertRepository stockAlertRepository;
	@Autowired
	StockAlertMapper stockAlertMapper;
	
	@Transactional(readOnly = true)
	@Retry(name = "getAlertsRetry")
	@CircuitBreaker(name = "getAlertsService",fallbackMethod = "getAlertsFallback")
	@RateLimiter(name = "getAlertsRateLimiter")
	@Override
	public Page<StockAlertDto> findAll(Pageable pageable) {
		Page<StockAlert> stockAlertList= stockAlertRepository.findAllProductWithAlertCritical(pageable);
		
		return stockAlertList.map(stockAlertMapper::toDto);
	}

	@Override
	public StockAlertDto saveAlert(StockAlert alert) {
		StockAlert alertSave = stockAlertRepository.saveAndFlush(alert);
		
		return stockAlertMapper.toDto(alertSave);
	}
	
	public Page<StockAlertDto> getAlertsFallback(Pageable pageable,Exception ex){
		return Page.empty(pageable);
	}
	

}
