package es.codeurjc.wallypop.controller.api;

import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/graphic")
public class GraphicRestController {
    @Autowired
    private CategoryService categoryservice;

    @GetMapping("")
    public List<Data> graphic() throws SQLException {
        List<Category> lCategory = categoryservice.graphic();
        List<Data> result = new ArrayList<>();
        for (Category c : lCategory) {
            result.add(new Data(c.getTITLE(), c.getSize()));
        }
        return result;
    }
}

class Data {
    public String name;
    public int y;

    public Data(String name, int y){
        this.name = name;
        this.y = y;
    }
}
