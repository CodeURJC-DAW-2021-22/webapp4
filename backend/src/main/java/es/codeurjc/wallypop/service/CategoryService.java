package es.codeurjc.wallypop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryrepository;
	
	public Optional<Category> findById(long id) {
		return categoryrepository.findById(id);
	}
	
	public boolean exist(long id) {
		return categoryrepository.existsById(id);
	}

	public List<Category> findAll() {
		return categoryrepository.findAll();
	}

	public void save(Category category) {
		categoryrepository.save(category);
	}

	public void delete(long id) {
		categoryrepository.deleteById(id);
	}
	
	public Optional<Category> findByCategory(String category){
		return categoryrepository.findByTITLE(category);
	}
	
}
