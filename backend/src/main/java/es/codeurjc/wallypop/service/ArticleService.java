package es.codeurjc.wallypop.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.repository.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	public Optional<Article> findById(long id) {
		return articleRepository.findById(id);
	}
	
	public boolean exist(long id) {
		return articleRepository.existsById(id);
	}

	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	public void save(Article article) {
		articleRepository.save(article);
	}

	public void delete(long id) {
		articleRepository.deleteById(id);
	}
	
	public List<Article> findArticlesByCategory(long id) {
		Optional<Category> c = categoryService.findById(id);
		List<Article> lResult = new LinkedList<>();
		if (c.isPresent()) {
			for (Article a : findAll()) {
				if (a.getCATEGORYS().contains(c.get())) {
					lResult.add(a);
				}
			}
		}
		return lResult;
	}
	
}
