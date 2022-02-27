package es.codeurjc.wallypop.dao;

import java.util.List;
import es.codeurjc.wallypop.model.Article;
 
public interface ArticleDAO {
	boolean addArticle(Article b);
	boolean updateArticlek(Article b);
	boolean deleteArticle(long articleId);
	Article getArticle(long articleId);
	List<Article> getArticles();
}
