package es.codeurjc.wallypop.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repository.ArticleRepository;
import es.codeurjc.wallypop.repository.UserRepository;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserRepository userRepository;

	public void delete(long id) {
		articleRepository.deleteById(id);
	}

	public void deletePost(long id, Long id_user, boolean admin) {
		Article a = findById(id).get();
		if (a.getUserID() == id_user || admin) {
			delete(id);
		}
	}

	public boolean exist(long id) {
		return articleRepository.existsById(id);
	}

	public List<Article> findAll() {
		return articleRepository.findAll();
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

	public Optional<Article> findById(long id) {
		return articleRepository.findById(id);
	}

	public void reserve(long id, boolean bool, long id_user, boolean admin) {
		Article a = findById(id).get();
		if (a.getUserID() == id_user || admin) {
			a.setRESERVED(bool);
			save(a);
		}
	}

	public void save(Article article) {
		articleRepository.save(article);
	}

	public void sell(long id, boolean bool, long id_user, boolean admin) {
		Article a = findById(id).get();
		User user = userRepository.findById(id_user).get();
		if (a.getUserID() == id_user || admin) {
			a.setSOLD(bool);
			user.sell(bool);
			userRepository.save(user);
			a.setRESERVED(false);
			save(a);
		}
	}

	/*
	 * public List<Article> findReserved(Boolean bool) { Optional<List<Article>>
	 * lResult = articleRepository.findByReserved(bool); if (lResult.isPresent()) {
	 * return lResult.get(); } return new LinkedList<Article>(); }
	 *
	 * public List<Article> findSold(Boolean bool) { Optional<List<Article>> lResult
	 * = articleRepository.findBySold(bool); if (lResult.isPresent()) { return
	 * lResult.get(); } return new LinkedList<Article>(); }
	 */

}
