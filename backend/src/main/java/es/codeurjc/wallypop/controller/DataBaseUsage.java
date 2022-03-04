package es.codeurjc.wallypop.controller;

import java.io.IOException;
import java.sql.Blob;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.CategoryService;
import es.codeurjc.wallypop.service.ReportService;
import es.codeurjc.wallypop.service.UserService;

@Controller
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ReportService reportService;

	@Override
	public void run(String... args) throws Exception {
		// User
		userService.save(new User("Jesús", passwordEncoder.encode("1234"), "j@j.es", "666666666", true));
		userService.save(new User("David", passwordEncoder.encode("1234"), "d@d.es", "666666666"));

		// Category
		categoryService.save(new Category("Videojuegos", "Compra y vende videojuegos de todo tipo",
				convertToBLOB("/sample_image/videojuego.jpg"), "gamepad"));
		categoryService.save(new Category("Ropa", "Dale una segunda vida a las prendas que ya no usas",
				convertToBLOB("/sample_image/ropa.png"), "shirt"));

		// Article
		List<Category> lCat = new LinkedList<>();
		if (categoryService.findById(1).isPresent() && categoryService.findById(2).isPresent()) {
			lCat.add(categoryService.findById(1).get());
			lCat.add(categoryService.findById(2).get());
			articleService.save(new Article(userService.findById(1).get(), "Zapatillas deportivas", "Descripción",
					"Madrid", (float) 99.99, convertToBLOB("/sample_image/ropa.png"), lCat));
			articleService.save(new Article(userService.findById(2).get(), "Zapatillas correr", "Descripción", "Madrid",
					(float) 50.99, convertToBLOB("/sample_image/ropa.png"), lCat));
		}

		// Report

		reportService.save(new Report(articleService.findAll().get(0), "a@a.es", "Descripción"));

	}

	public void setCategoryImage(Category category, String ruta) throws IOException {
		Resource image = new ClassPathResource(ruta);
		category.setPHOTO(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

	public Blob convertToBLOB(String ruta) throws IOException {
		Resource image = new ClassPathResource(ruta);
		return BlobProxy.generateProxy(image.getInputStream(), image.contentLength());
	}
}
