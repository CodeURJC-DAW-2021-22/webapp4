package es.codeurjc.wallypop.util;

import java.util.ArrayList;
import java.util.List;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.model.User;
 
public class ArticleRepositoryStructureData {
	public static List<Article> articleList;
	private static Category categoria_1;					// Declaration Object category to create new category
	private static Category categoria_2;
	private static ArrayList<Category> lista_categorias;
 
	public static List<Article> populateArticleList(){

		articleList = new ArrayList<Article>();
		lista_categorias = new ArrayList<Category>();

		categoria_1 = (Category) new Category("Deporte", "Árticulos deportivos que las personas utilizan para practicar deporte o para vestir en su día a día");
		categoria_2 = (Category) new Category("Moda", "Árticulos relacionados con las últimas tendencias en moda");
		lista_categorias.add(categoria_1);
		lista_categorias.add(categoria_2);

		
		articleList.add(new Article(new User("Jesús", "1234", "j@j.es", "666666666", true),"Madrid", "Se vende ...","Se venden zapatillas ...", (float)9.99, lista_categorias));
		
		return articleList;
	}
 
}




