package es.codeurjc.wallypop.service;

import java.util.List;

import es.codeurjc.wallypop.model.Article;
 
 
public interface ArticleService {
 
	boolean addArticle(es.codeurjc.wallypop.model.Article article);
	boolean updateArticle(Article article);
	boolean deleteArticle(long articleId);
	Article getArticle(long articleId);
	List<Article> getArticles();
 
}
