package es.codeurjc.wallypop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.repositories.ArticleRepository;

@Controller
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private ArticleRepository articleRepository;

	
	
	@Override
	public void run(String... args) throws Exception {
		
		// Article (id_user,
		articleRepository.save(new Article("001", "Alicante", "Zapatillas deportivas", "Zapatillas deportivas NBA", (float) 9.99, "0001"));

	}
}
