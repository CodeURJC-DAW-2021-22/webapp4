package es.codeurjc.wallypop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.dao.ArticleDAO;
import es.codeurjc.wallypop.model.Article;

@Service("articleservice")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDAO articledao;
	public boolean addArticle(Article article) {
		return articledao.addArticle(article);
	}

	@Override
	public boolean updateArticle(Article article) {
		return articledao.updateArticlek(article);
	}

	@Override
	public boolean deleteArticle(long articleId) {
		return articledao.deleteArticle(articleId);
	}

	@Override
	public Article getArticle(long articleId) {
		return articledao.getArticle(articleId);
	}

	@Override
	public List<Article> getArticles() {
		return articledao.getArticles();
	}
	
}
