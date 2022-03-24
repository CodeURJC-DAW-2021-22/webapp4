package es.codeurjc.wallypop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
	
	public void deleteById(long id) {
		categoryrepository.deleteById(id);
	}
	
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category updatedCategory){
        	Category cat = findById(id).get();
            updatedCategory.setARTICLES(cat.getARTICLES());
            if (updatedCategory.getDESCRIPTION() == null) {
            	updatedCategory.setDESCRIPTION(cat.getDESCRIPTION());
            }
            if (updatedCategory.getICON() == null) {
            	updatedCategory.setICON(cat.getICON());
            }
            updatedCategory.setID_CATEGORY(id);
            updatedCategory.setPHOTO(cat.getPHOTO());
            updatedCategory.setSize(cat.getSize());
            if (updatedCategory.getTITLE() == null) {
            	updatedCategory.setTITLE(cat.getTITLE());
            }
            save(updatedCategory);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

}
