package com.example.inventory.inventory.Dao;

import com.example.inventory.inventory.Model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Long>{
    
}
