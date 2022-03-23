package es.codeurjc.wallypop.service;

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
	/*
	public Page<Article> findAllPageable(Pageable pageable) {
		return articleRepository.findAllPageable(pageable);
	}
	*/
	/*
	public List<Category> findCategorierByID_ARTICLE(long id_article) {
		return articleRepository.findCategorierByID_ARTICLE(id_article);
	}
	*/
	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	public List<Article> findArticlesByCategory(long id) {
		return articleRepository.findByCATEGORYS(categoryService.findById(id).get());
	}

	public List<Article> findByCITYContainingIgnoreCaseAndSOLDFalse(String city) {
		return articleRepository.findByCITYContainingIgnoreCaseAndSOLDFalse(city);
	}

	public Optional<Article> findById(long id) {
		return articleRepository.findById(id);
	}

	public List<Article> findBySOLDFalse() {
		return articleRepository.findBySOLDFalse();
	}

	public List<Article> findBySOLDTrue() {
		return articleRepository.findBySOLDTrue();
	}

	public List<Article> findByTITLEContainingIgnoreCaseAndCITYContainingIgnoreCaseAndSOLDFalseOrDESCRIPTIONContainingIgnoreCaseAndCITYContainingIgnoreCaseAndSOLDFalse(
			String query, String city) {
		return articleRepository
				.findByTITLEContainingIgnoreCaseAndCITYContainingIgnoreCaseAndSOLDFalseOrDESCRIPTIONContainingIgnoreCaseAndCITYContainingIgnoreCaseAndSOLDFalse(
						query, city, query, city);
	}

	public List<Article> findByTITLEContainingIgnoreCaseAndSOLDFalseOrDESCRIPTIONContainingIgnoreCaseAndSOLDFalse(
			String query) {
		return articleRepository
				.findByTITLEContainingIgnoreCaseAndSOLDFalseOrDESCRIPTIONContainingIgnoreCaseAndSOLDFalse(query, query);
	}

	public List<Article> findByTitleContainingOrDescriptionContainingOrCITYContaining(String query, String city) {
		return articleRepository
				.findByTITLEContainingIgnoreCaseOrDESCRIPTIONContainingIgnoreCaseOrCITYContainingIgnoreCaseAndSOLDFalse(
						query, query, city);
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
	
	public void deleteById(long id) {
		articleRepository.deleteById(id);
	}
}
