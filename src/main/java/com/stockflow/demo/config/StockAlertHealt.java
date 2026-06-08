package com.stockflow.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.stockflow.demo.repository.ProductRepository;
@Component
public class StockAlertHealt implements HealthIndicator{
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public Health health() {
		Long totalProduct = productRepository.count();
		Long productInAlert = productRepository.gettotalProductInAlert();
		if(totalProduct==0) {
			 return Health.up()
	                    .withDetail("message", "Lista vacia de productos.")
	                    .build();
		}
		double percentage = (productInAlert * 100.0) / totalProduct;
		Map<String, Object> details = new HashMap<>();
        details.put("totalProducts", totalProduct);
        details.put("alertProducts", productInAlert);
        details.put("percentage", percentage);

        if (percentage > 20) {
            return Health.down()
                    .withDetails(details)
                    .withDetail("message", "El inventario de productos se encuentra en estado critico.s")
                    .build();
        }

        return Health.up()
                .withDetails(details)
                .withDetail("message", "Estado normal de inventario")
                .build();
	}

}
