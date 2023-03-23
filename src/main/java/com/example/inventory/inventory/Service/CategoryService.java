package com.example.inventory.inventory.Service;

import com.example.inventory.inventory.Reponse.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    // Methods
    public ResponseEntity<CategoryResponseRest> search();

    public ResponseEntity<CategoryResponseRest> searchById(Long id);
    
}
