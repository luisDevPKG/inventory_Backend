package com.example.inventory.inventory.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inventory.inventory.Reponse.CategoryResponseRest;
import com.example.inventory.inventory.Dao.CategoryDao;
import com.example.inventory.inventory.Model.Category;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true) // si algo falla durante la consulta NO se afecta la base datos
    public ResponseEntity<CategoryResponseRest> search() {
        
        // se instancia el objeto que se va armar
        CategoryResponseRest response = new CategoryResponseRest();
        try {
            // declarar lista de category -- se castea
            List<Category> category = (List<Category>) categoryDao.findAll();
            // Se llena el metodo de category y se setea la lista obtenida con todas las categorias
            response.getCategoryResponse().setCategory(category);
            // Se setea la metadata
            response.setMetadata("Repuesta ok", "00", "Respuesta Exitosa");
        } catch (Exception e) {
            // setear metadata
            response.setMetadata("Repuesta fail", "-1", "Respuesta fallida");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

}
