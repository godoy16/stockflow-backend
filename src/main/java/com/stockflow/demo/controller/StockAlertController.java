package com.stockflow.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockflow.demo.config.ApiPath;
import com.stockflow.demo.dto.StockAlertDto;
import com.stockflow.demo.service.StockAlertService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(ApiPath.BASE_V1)
public class StockAlertController {
	
	@Autowired
	StockAlertService stockAlertService;
	@GetMapping("/alerts")
	public ResponseEntity<Page<StockAlertDto>> findById(@PageableDefault(page = 0,
            size = 10,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
		Page<StockAlertDto> listAlerts = stockAlertService.findAll(pageable);
		return ResponseEntity.ok(listAlerts);
	}

}
