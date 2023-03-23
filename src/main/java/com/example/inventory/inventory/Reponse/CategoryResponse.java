package com.example.inventory.inventory.Reponse;

import com.example.inventory.inventory.Model.Category;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private List<Category> category;
    
}
