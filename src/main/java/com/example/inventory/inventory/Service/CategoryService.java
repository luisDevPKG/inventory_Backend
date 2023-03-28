package com.example.inventory.inventory.Service;

import com.example.inventory.inventory.Reponse.CategoryResponseRest;
import com.example.inventory.inventory.Model.Category;

import org.springframework.http.ResponseEntity;

public interface CategoryService {

    // Methods
    public ResponseEntity<CategoryResponseRest> search();

    public ResponseEntity<CategoryResponseRest> searchById(Long id);

    public ResponseEntity<CategoryResponseRest> saveCategory(Category category);

    public ResponseEntity<CategoryResponseRest> updateCategory(Category category, Long id);

    public ResponseEntity<CategoryResponseRest> deleteCategory(Long id);
}
