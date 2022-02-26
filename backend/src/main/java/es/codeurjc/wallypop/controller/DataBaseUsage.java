package es.codeurjc.wallypop.controller;

import java.io.IOException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repositories.CategoryRepository;
import es.codeurjc.wallypop.repositories.UserRepository;

@Service
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("Jesús", "1234", "j@j.es", "666666666", true));
		userRepository.save(new User("David", "1234", "d@d.es", "666666666"));
		
		//Category
		Category category1 = new Category("Videojuegos", "Compra y vende videojuegos de todo tipo");
		setCategoryImage(category1, "/sample_image/videojuego.jpg");
		categoryRepository.save(category1);
	}
	
	public void setCategoryImage(Category category, String ruta) throws IOException {
		Resource image = new ClassPathResource(ruta);
		category.setPHOTO(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

}
