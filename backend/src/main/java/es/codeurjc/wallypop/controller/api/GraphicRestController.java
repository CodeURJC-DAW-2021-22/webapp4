package es.codeurjc.wallypop.controller.api;

import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/graphic")
public class GraphicRestController {
    @Autowired
    private CategoryService categoryservice;

    @GetMapping("")
    public List<Category> graphic() throws SQLException {
        List<Category> lCategory = categoryservice.graphic();
        return lCategory;
    }
}
