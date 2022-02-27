package es.codeurjc.wallypop.controller;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.service.ArticleService;
 
@RestController
public class ArticleController {
	
	
 
	@Autowired
	ArticleService articleservice;
	
	@GetMapping(value="/articles", produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Article>> getAllArticle(){
		List<Article> articles = articleservice.getArticles();
		HttpStatus ResponseHTTPStatus = null;
		if (articles.size()>0) {
			ResponseHTTPStatus= HttpStatus.OK;
		}else {
			ResponseHTTPStatus= HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<List<Article>>(articles, ResponseHTTPStatus);
	}
 
	@GetMapping(value="/article/{articleId}",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Article> getArticle(@PathVariable("articleId") String articleId){
		Article article = articleservice.getArticle(Long.parseLong(articleId));
		HttpStatus ResponseHTTPStatus = null;

		if (article.getTITLE().length() > 0) {
			ResponseHTTPStatus= HttpStatus.OK;
		}else {
			ResponseHTTPStatus= HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<Article>(article, ResponseHTTPStatus);
	}
 
	@PostMapping(value = "/article/", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> AddArticle(@RequestBody Article article,UriComponentsBuilder ucBuilder){
		boolean result = articleservice.addArticle(article);
		HttpStatus ResponseHTTPStatus = null;

		if (result == true) {
			ResponseHTTPStatus= HttpStatus.CREATED;
		}else {
			ResponseHTTPStatus= HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Object>(ResponseHTTPStatus);
}
 
	@PutMapping(value="/article/", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> updateArticle(@RequestBody Article article){
		boolean result = articleservice.updateArticle(article);
		HttpStatus ResponseHTTPStatus = null;
		
		if (result == true) {
			ResponseHTTPStatus= HttpStatus.CREATED;
		}else {
			ResponseHTTPStatus= HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Object>(ResponseHTTPStatus);
 
}
 
	@DeleteMapping(value="/article/{articleId}")
	public ResponseEntity<?> deleteArticle(@PathVariable("bookId") long articleId){
		boolean result = articleservice.deleteArticle(articleId);
		HttpStatus ResponseHTTPStatus = null;
		
		if (result == true) {
			ResponseHTTPStatus= HttpStatus.OK;
		}else {
			ResponseHTTPStatus= HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<Object>(ResponseHTTPStatus);
 
}
 
}
