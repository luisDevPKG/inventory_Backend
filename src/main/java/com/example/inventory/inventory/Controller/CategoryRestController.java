package com.example.inventory.inventory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory.inventory.Reponse.CategoryResponseRest;
import com.example.inventory.inventory.Service.CategoryService;

@RestController
@RequestMapping("/api/v1") // genero la uri general del servicio
public class CategoryRestController {

    @Autowired
    private CategoryService service;

    // defino el metodo que permitira obtener todas las categorias
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategory(){
        ResponseEntity<CategoryResponseRest> response = service.search();
        return response;
    }
    
}
