package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
