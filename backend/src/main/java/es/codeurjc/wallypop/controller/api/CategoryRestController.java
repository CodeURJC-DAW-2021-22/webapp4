package es.codeurjc.wallypop.controller.api;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    public static ResponseEntity<List<Article>> getCategoryResponseEntity(@PathVariable long id, CategoryService categoryService) {
        Optional<Category> op = categoryService.findById(id);
        if (op.isPresent()) {
            Category category = op.get();
            return new ResponseEntity<>(category.getARTICLES(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public static ResponseEntity<Object> getObjectResponseEntity(@PathVariable long id, CategoryService categoryService) throws SQLException {
        Category category = categoryService.findById(id).orElseThrow();

        if (category.getPHOTO() != null) {

            Resource file = new InputStreamResource(category.getPHOTO().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(category.getPHOTO().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    List<Category> all() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Article>> getCategory(@PathVariable long id) {

        return getCategoryResponseEntity(id, categoryService);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        return getObjectResponseEntity(id, categoryService);
    }

}
