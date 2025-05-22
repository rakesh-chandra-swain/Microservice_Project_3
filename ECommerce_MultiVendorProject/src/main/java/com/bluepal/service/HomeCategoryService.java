package com.bluepal.service;

import java.util.List;

import com.bluepal.model.HomeCategory;

public interface HomeCategoryService {
    HomeCategory createCategory(HomeCategory categories);
    List<HomeCategory> createCategories(List<HomeCategory> categories);
    List<HomeCategory> getAllCategories();
    HomeCategory updateCategory(HomeCategory categories,Long id) throws Exception;
}
