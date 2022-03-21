package es.codeurjc.wallypop.controller;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.repository.ArticleRepository;
import es.codeurjc.wallypop.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private ArticleRepository articleRepository;

    public ResponseEntity<Map<String, Object>> getAllArticles(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
          ) {
        try {
          List<Article> articles = new ArrayList<Article>();
          Pageable paging = (Pageable) PageRequest.of(page, size);
          
          Page<Article> pageTuts;
          if (title == null)
            pageTuts = articleService.findAllPageable(paging);
          else
            pageTuts = articleRepository.findByTitleContaining(title, paging);
          articles = pageTuts.getContent();
          Map<String, Object> response = new HashMap<>();
          response.put("articles", articles);
          response.put("currentPage", pageTuts.getNumber());
          response.put("totalItems", pageTuts.getTotalElements());
          response.put("totalPages", pageTuts.getTotalPages());
          return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
      }
    
    
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
    public ResponseEntity<Article> updateArticleID(@PathVariable long id, @RequestBody Article updatedArticle) throws SQLException {
        if (articleService.exist(id)) {
        	updatedArticle.setID_ARTICLE(id);
        	articleService.save(updatedArticle);

            return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticleSOLD(@PathVariable long id, @RequestBody Article updatedArticle) throws SQLException {
        if (articleService.exist(id)) {
        	updatedArticle.setSOLD(true);
        	articleService.save(updatedArticle);

            return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticleReserved(@PathVariable long id, @RequestBody Article updatedArticle) throws SQLException {
        if (articleService.exist(id)) {
        	updatedArticle.setRESERVED(true);
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
