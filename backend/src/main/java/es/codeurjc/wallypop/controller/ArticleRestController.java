package es.codeurjc.wallypop.controller;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    List<Article> all() {
        return articleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable long id) {

        Optional<Article> op = articleService.findById(id);
        if (op.isPresent()) {
            Article article = op.get();
            return new ResponseEntity<>(article, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Article createArticle(@RequestBody Article article) {
    	articleService.save(article);
        return article;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody Article updatedArticle) throws SQLException {
        if (articleService.exist(id)) {
        	updatedArticle.setID_ARTICLE(id);
        	articleService.save(updatedArticle);

            return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable long id) {

        try {
        	articleService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
