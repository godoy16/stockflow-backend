package com.stockflow.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockflow.demo.entity.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement,Long>{
	
	Page<Movement> findByProductId(Long id,Pageable pageable);

}
