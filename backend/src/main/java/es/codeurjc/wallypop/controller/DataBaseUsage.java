package es.codeurjc.wallypop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.repositories.ArticleRepository;
import java.io.IOException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repository.CategoryRepository;
import es.codeurjc.wallypop.repository.UserRepository;


@Service
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		articleRepository.save(new Article("Alicante", "Zapatillas deportivas", "Zapatillas deportivas NBA", "9.99"));
		//articleRepository.findAll();
		userRepository.save(new User("Jesús", passwordEncoder.encode("1234"), "j@j.es", "666666666", true));
		userRepository.save(new User("David", passwordEncoder.encode("1234"), "d@d.es", "666666666"));
		
		//Category
		Category category1 = new Category("Videojuegos", "Compra y vende videojuegos de todo tipo", null, "gamepad");
		setCategoryImage(category1, "/sample_image/videojuego.jpg");
		categoryRepository.save(category1);
		
		Category category2 = new Category("Ropa", "Dale una segunda vida a las prendas que ya no usas", null, "shirt");
		setCategoryImage(category2, "/sample_image/ropa.png");
		categoryRepository.save(category2);
	}
	
	public void setCategoryImage(Category category, String ruta) throws IOException {
		Resource image = new ClassPathResource(ruta);
		category.setPHOTO(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}
}
