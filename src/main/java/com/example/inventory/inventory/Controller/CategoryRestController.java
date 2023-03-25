package com.example.inventory.inventory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory.inventory.Model.Category;
import com.example.inventory.inventory.Reponse.CategoryResponseRest;
import com.example.inventory.inventory.Service.CategoryService;

@RestController
@RequestMapping("/api/v1") // genero la uri general del servicio
public class CategoryRestController {

    @Autowired
    private CategoryService service;

    // defino el metodo que permitira obtener todas las categorias
    /**
     * obtener todas la categorias
     * @return
     */
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategory(){
        ResponseEntity<CategoryResponseRest> response = service.search();
        return response;
    }
    /**
     * obtener todas las categorias por id
     * @param id
     * @return
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoryById(@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = service.searchById(id);
        return response;
    }
    
    /**
     * guardar categoria
     * @param category
     * @return
     */
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> saveCategory(@RequestBody Category category){
        ResponseEntity<CategoryResponseRest> response = service.saveCategory(category);
        return response;
    }

}
