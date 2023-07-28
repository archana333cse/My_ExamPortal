package com.exam1.service;

import com.exam1.model.exam.Category;
import jakarta.persistence.SecondaryTable;

import java.util.Set;

public interface CategoryService {
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public Set<Category> getCategories();
    public Category getCategory(Long categoryId);
    public void deleteCateroy(Long categoryId);
}
