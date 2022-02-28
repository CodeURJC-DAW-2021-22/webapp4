package es.codeurjc.wallypop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repositories.ArticleRepository;
import es.codeurjc.wallypop.repositories.UserRepository;

@Controller
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	private ArticleRepository articleRepository;	// Declaration Object ArticleRepository to add new Articles

	//private Blob img ; 							// Declaration Object Blob class to create new image
	private Category categoria_1;					// Declaration Object category to create new category
	private Category categoria_2;
	private List<Category> lista_categorias;	// Declaration Object list to save categories
	
	@Override
	public void run(String... args) throws Exception {
	// Data for User
		userRepository.save(new User("Jesús", "1234", "j@j.es", "666666666", true));
		userRepository.save(new User("David", "1234", "d@d.es", "666666666"));
	
	// Data for Articles
		// First: Initializer User Object with call User constructor (we don't save new user in data base, it's proof data)
		// Second: Initializer Blob Object with call Blob constructor (TO DO)
		// Third: Initializer Category Objects with call Category constructor
		this.categoria_1 = (Category) new Category("Deporte", "Árticulos deportivos que las personas utilizan para practicar deporte o para vestir en su día a día");
		this.categoria_2 = (Category) new Category("Moda", "Árticulos relacionados con las últimas tendencias en moda");
		// Fourth: Initializer List of Categories and add two categories
		this.lista_categorias =  new ArrayList<Category>();
		this.lista_categorias.add((Category)this.categoria_1);
		this.lista_categorias.add((Category)this.categoria_2);

		// Fifth: mount Article object and save in article repository
		//articleRepository.save(new Article(new User("David", "1234", "d@d.es", "666666666"),"Madrid" ,"Deportivas", "Se venden zapatillas deportivas sin usar, último modelo, son las mismas que llevan tus deportistas favoritos", (float) 19.90, this.lista_categorias ));

	}
}
