package es.codeurjc.wallypop.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.repositories.ArticleRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class YourCommercialWebController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	private Article article;
	
	private Article article_1;
	
	private ArrayList listArticlesUser;
	
	// Este es el método que se llama cuando vamos al apartado TUS ANUNCIOS
	@RequestMapping("/yourcommercial")
	 public String yourcommercial(Model model) {
		model.addAttribute("exito_creacion_nuevo_anuncio", "");							// moustache	
		listArticlesUser = (ArrayList) articleRepository.findAll();
		model.addAttribute("articleList", listArticlesUser);							// moustache

		for (int i = 0; i< listArticlesUser.size(); i++) {
			this.article = new Article();
			this.article = (Article) listArticlesUser.get(i);
			System.out.println(article.toString()); 									// con esta linea queda desmostrado que, en cada iteración del bucle for, se almacena la info de cada articulo contenido en la lista debtro del objeto "article". Y tambie´n que previamente almacenamos todo el contenido del repositorio en la lista
			model.addAttribute("title_article", this.article.getTITLE());				// moustache
			model.addAttribute("price_article", this.article.getPRICE());				// moustache	
			model.addAttribute("city_article", this.article.getCITY());					// moustache	
			model.addAttribute("date_article", this.article.getDATE());					// moustache	
		}
			return "yourcommercial";
	}
	
	// Este es el método que se llama cuando agragamos un nuevo anuncio y toto va bien
	@RequestMapping("/yourcommercial_exito_message")
	public String mensajeCreadoExito(Model model) {
		model.addAttribute("exito_creacion_nuevo_anuncio", "Enhorabuena! El nuevo anuncio ha sido creado con éxito");
		listArticlesUser = (ArrayList) articleRepository.findAll();
		for (int i = 0; i < listArticlesUser.size(); i++) {
			model.addAttribute("articleList", listArticlesUser);						// moustache
			this.article = new Article();
			this.article = (Article) listArticlesUser.get(i);
			System.out.println(article.toString()); 									// con esta linea queda desmostrado que, en cada iteración del bucle for, se almacena la info de cada articulo contenido en la lista debtro del objeto "article". Y tambie´n que previamente almacenamos todo el contenido del repositorio en la lista
			model.addAttribute("title_article", this.article.getTITLE());				// moustache
			model.addAttribute("price_article", this.article.getPRICE());				// moustache	
			model.addAttribute("city_article", this.article.getCITY());					// moustache	
			model.addAttribute("date_article", this.article.getDATE());					// moustache	
		}		
		return "yourcommercial";
	}
	
	@RequestMapping("/yourcommercialsold")
	public String yourcommercialsold() {
		return "yourcommercialsold";
	}		
}
