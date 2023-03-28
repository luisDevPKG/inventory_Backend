package com.example.inventory.inventory.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inventory.inventory.Reponse.CategoryResponseRest;
import com.example.inventory.inventory.Dao.CategoryDao;
import com.example.inventory.inventory.Model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // Retornar categoria por id
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        // se instancia el objeto que se va armar
        CategoryResponseRest response = new CategoryResponseRest();

        List<Category> list = new ArrayList<>();

        try {

             // se declara un objeto optional: porque el metodo findById devuelve un objeto optional
             Optional<Category> categoria = categoryDao.findById(id);
             if (categoria.isPresent()) {
                // se agrega la categoria a la lista
                list.add(categoria.get());
                // se setea la lista a la respuesta
                response.getCategoryResponse().setCategory(list);
                response.setMetadata("Repuesta ok", "00", "Categoria encontrada");
             } else {
                response.setMetadata("Repuesta fail", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
             }

        } catch (Exception e) {
            // setear metadata
            response.setMetadata("Repuesta fail", "-1", "Respuesta fallida");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> saveCategory(Category category) {
        // se instancia el objeto que se va armar
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try {
            // guardo en bd y lo almaceno en el objeto categorySaved
            Category categorySaved = categoryDao.save(category);
            if (categorySaved != null) {
                list.add(categorySaved);
                response.getCategoryResponse().setCategory(list);
                response.setMetadata("Repuesta ok", "00", "Categoria encontrada");
            } else {
                response.setMetadata("Repuesta fail", "-1", "Error al guardar el registro");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
            
        } catch (Exception e) {
             // setear metadata
             response.setMetadata("Repuesta fail", "-1", "Error al guardar el registro");
             e.getStackTrace();
             return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> updateCategory(Category category, Long id) {
            // se instancia el objeto que se va armar
            CategoryResponseRest response = new CategoryResponseRest();
            List<Category> list = new ArrayList<>();

            try {
                // se declara un objeto optional: porque el metodo findById devuelve un objeto optional
                Optional<Category> categoria = categoryDao.findById(id);

                if (categoria.isPresent()) {
                    // se procede actualizar el registro
                    categoria.get().setName(category.getName());
                    categoria.get().setDescription(category.getDescription());

                    // actualizo y guardo la categoria
                    Category categoryToUpdate = categoryDao.save(categoria.get());
                    if (categoryToUpdate != null) {
                        list.add(categoryToUpdate);
                        response.getCategoryResponse().setCategory(list);
                        response.setMetadata("Respuesta ok", "00", "Categoria actualizada");
                    } else {
                        response.setMetadata("Repuesta fail", "-1", "Error no se pudo actualizar el registro");
                        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
                    }

                } else {
                    response.setMetadata("Repuesta fail", "-1", "Error no se encuentra el registro");
                    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
                }


            } catch (Exception e) {
                 // setear metadata
                 response.setMetadata("Repuesta fail", "-1", "Error al actualizar el registro");
                 e.getStackTrace();
                 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deleteCategory(Long id) {
        // se instancia el objeto que se va armar
        CategoryResponseRest response = new CategoryResponseRest();
        try {
            categoryDao.deleteById(id);
            // setear metadata
            response.setMetadata("Repuesta ok", "00", "Se elimino el registro correctamente");

        } catch (Exception e) {
             // setear metadata
             response.setMetadata("Repuesta fail", "-1", "Error al eliminar el registro");
             e.getStackTrace();
             return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
    
}
