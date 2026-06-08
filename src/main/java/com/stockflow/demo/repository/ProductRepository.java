package com.stockflow.demo.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stockflow.demo.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
	Page<Product> findAll(Pageable pageable);
	Page<Product> findByCategory(String category, Pageable pageable);
	@Query("""
		    SELECT count(p)
		    FROM Product p
		    WHERE p.currentStock <= p.minStock
		""")
	Long gettotalProductInAlert();
	

}
