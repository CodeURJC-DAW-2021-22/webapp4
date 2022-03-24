package es.codeurjc.wallypop.controller.api;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repository.ArticleRepository;
import es.codeurjc.wallypop.security.jwt.ArticleRequest;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.CategoryService;
import es.codeurjc.wallypop.service.UserService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("")
    List<Article> all() {
        return articleService.findAll();
    }

    @GetMapping("/me")
    public ResponseEntity<List<Article>> me(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            User user = userService.findByNAME(principal.getName()).get();
            return new ResponseEntity<>(user.getARTICLES(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        Article article = articleService.findById(id).orElseThrow();
        if (article.getPHOTO() != null) {

            Resource file = new InputStreamResource(article.getPHOTO().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(article.getPHOTO().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Article> createArticle(HttpServletRequest request, @RequestBody ArticleRequest articleRequest) {
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            Article article = new Article();
            article.setUSER(userService.findByNAME(principal.getName()).get());
            return getArticleResponseEntity(articleRequest, article);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> createArticle(HttpServletRequest request, @RequestBody ArticleRequest articleRequest, @PathVariable long id) {
        Principal principal = request.getUserPrincipal();
        if(principal != null && articleService.findById(id).isPresent()) {
            Article article = articleService.findById(id).get();
            User user = userService.findByNAME(principal.getName()).get();
            if (article.getUserID() == user.getID_USER() || user.isIS_ADMIN()) {
                return getArticleResponseEntity(articleRequest, article);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        Article article = articleService.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri();

        article.setPHOTO(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        articleService.save(article);

        return ResponseEntity.created(location).build();
    }

    private ResponseEntity<Article> getArticleResponseEntity(@RequestBody ArticleRequest articleRequest, Article article) {
        article.setTITLE(articleRequest.getTITLE());
        article.setDESCRIPTION(articleRequest.getDESCRIPTION());
        article.setCITY(articleRequest.getCITY());
        article.setPOSTAL_CODE(articleRequest.getPOSTAL_CODE());
        article.setPRICE(articleRequest.getPRICE());
        for (int i : articleRequest.getCategories()) {
            Optional<Category> c = categoryService.findById(i);
            if (c.isPresent()) {
                article.addCategory(c.get());
            }
        }
        articleService.save(article);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Article> deleteArticle(HttpServletRequest request, @PathVariable long id) {
        try {
            Principal principal = request.getUserPrincipal();
            if(principal != null && articleService.findById(id).isPresent()) {
                Article article = articleService.findById(id).get();
                User user = userService.findByNAME(principal.getName()).get();
                if (article.getUserID() == user.getID_USER() || user.isIS_ADMIN()) {
                    articleService.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<Object> deleteImage(@PathVariable long id) throws IOException {

        Article article = articleService.findById(id).orElseThrow();

        article.setPHOTO(null);

        articleService.save(article);

        return ResponseEntity.noContent().build();
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Article> updateArticleID(@PathVariable long id, @RequestBody Article updatedArticle) throws SQLException {
        if (articleService.exist(id)) {
        	Article cat = articleService.findById(id).get();
        	updatedArticle.setCATEGORYS(cat.getCATEGORYS());
        	updatedArticle.setTITLE(cat.getTITLE());
        	updatedArticle.setDESCRIPTION(cat.getDESCRIPTION());
        	updatedArticle.setID_ARTICLE(id);
        	updatedArticle.setPHOTO(cat.getPHOTO());
        	updatedArticle.setPOSTAL_CODE(cat.getPOSTAL_CODE());
        	updatedArticle.setPRICE(cat.getPRICE());
        	updatedArticle.setCITY(cat.getCITY());
        	updatedArticle.setRESERVED(cat.isRESERVED());
        	updatedArticle.setSOLD(cat.isSOLD());
        	updatedArticle.setUSER(cat.getUSER());
        	
        	articleService.save(updatedArticle);

            return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    /*@GetMapping("/yourcommercial")
    public ResponseEntity<Map<String, Object>> getAllArticlesByUser(@RequestBody User user) {
        try {
            List<Article> articles = new LinkedList<Article>();
            Pageable paging = (Pageable) PageRequest.of(0, 10);
            Page<Article> pageTuts;

            pageTuts = articleService.findAll(paging);
            //pageTuts = articleService.findByUSERS(user, paging);
            articles = user.getARTICLES();
            Map<String, Object> response = new HashMap<>();
            response.put("articles", articles);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    


}
