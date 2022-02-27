package es.codeurjc.wallypop.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.util.ArticleRepositoryStructureData;
 
@Component
class ArticleDAOImpl implements ArticleDAO {
	static List<Article> articles = null;
	static {
		articles = ArticleRepositoryStructureData.populateArticleList();
	}
 
	public boolean addArticle(Article b) {
		try {
			articles.add(b);
		}
		catch(Exception e)
		{
			return false;
		}
			return true;
		}
 
	public boolean updateArticlek(Article b) {
		for (int i = 0; i < articles.size(); i++){
			Article article = (Article) articles.get(i);
			if (article.getID_ARTICLE()==b.getID_ARTICLE()){
				article.setUSER(b.getUSER());
				article.setTITLE(b.getTITLE());
				article.setDESCRIPTION(b.getDESCRIPTION());
				article.setPRICE(b.getPRICE());
				article.setCATEGORY(b.getCATEGORY());

				return true;
			}
 
		}
		return false;
	}

	public Article getArticle(long articleId){
		for (int i = 0; i < articles.size(); i++){
			Article article = (Article) articles.get(i);
			if (article.getID_ARTICLE() == articleId){
				return article;
			}
		}
		return null;
	}

	@Override
	public List<Article> getArticles() {
 
		return articles;
	}

	@Override
	public boolean deleteArticle(long articleId) {
		Article article = getArticle(articleId);
		return articles.remove(article);
}


}

