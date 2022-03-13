package es.codeurjc.wallypop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryrepository;

	public void delete(long id) {
		Category cat = findById(id).get();
		for (Article a : cat.getARTICLES()) {
			List<Category> lCategories = a.getCATEGORYS();
			lCategories.remove(cat);
		}
		categoryrepository.deleteById(id);
	}

	public boolean exist(long id) {
		return categoryrepository.existsById(id);
	}

	public List<Category> findAll() {
		return categoryrepository.findAll();
	}

	public Optional<Category> findById(long id) {
		return categoryrepository.findById(id);
	}

	public void save(Category category) {
		categoryrepository.save(category);
	}

}
