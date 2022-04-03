package es.codeurjc.wallypop.controller.api.admin;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.service.CategoryService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import static es.codeurjc.wallypop.controller.api.CategoryRestController.getCategoryResponseEntity;
import static es.codeurjc.wallypop.controller.api.CategoryRestController.getObjectResponseEntity;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/admin/categories")
public class AdminCategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    List<Category> all() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Article>> getCategory(@PathVariable long id) {

        return getCategoryResponseEntity(id, categoryService);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        categoryService.save(category);
        return category;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category updatedCategory) throws SQLException {
        if (categoryService.exist(id)) {
            return categoryService.updateCategory(id, updatedCategory);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable long id) {

        try {
            categoryService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping("/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        Category category = categoryService.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri();

        category.setPHOTO(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        categoryService.save(category);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        return getObjectResponseEntity(id, categoryService);
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<Object> deleteImage(@PathVariable long id) throws IOException {

        Category category = categoryService.findById(id).orElseThrow();

        category.setPHOTO(null);

        categoryService.save(category);

        return ResponseEntity.noContent().build();
    }
}
