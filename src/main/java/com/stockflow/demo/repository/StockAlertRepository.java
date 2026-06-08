package com.stockflow.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stockflow.demo.entity.StockAlert;
@Repository
public interface StockAlertRepository extends JpaRepository<StockAlert,Long>{
	Page<StockAlert> findAll(Pageable pageable);
	@Query("""
		    SELECT p
		    FROM StockAlert p
		    WHERE p.currentStock <= p.minStock
		""")
	Page<StockAlert> findAllProductWithAlertCritical(Pageable pageable);

}
