package com.bluepal.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bluepal.model.HomeCategory;

public interface HomeCategoryRepository extends JpaRepository<HomeCategory,Long> {
}
