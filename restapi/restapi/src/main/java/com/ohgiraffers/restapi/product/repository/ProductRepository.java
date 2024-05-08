package com.ohgiraffers.restapi.product.repository;

import com.ohgiraffers.restapi.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByProductOrderable(String status);
	
	Page<Product> findByProductOrderable(String status, Pageable paging);
	
	List<Product> findByProductNameContaining(String search);

	List<Product> findByCategoryCode(int categoryCode);

}
