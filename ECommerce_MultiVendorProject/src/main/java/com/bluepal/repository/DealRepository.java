package com.bluepal.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bluepal.model.Deal;

public interface DealRepository extends JpaRepository<Deal,Long> {

}
