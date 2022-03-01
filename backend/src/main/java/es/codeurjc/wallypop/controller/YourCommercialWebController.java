package es.codeurjc.wallypop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.collections.List;
import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.repositories.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class YourCommercialWebController {
	
	@Autowired
	private ArticleRepository articleRepository;
	// private List listArticlesUser;
	@Autowired
	private AdcommercialWebController controladorArticulos;

	@RequestMapping("/yourcommercial")
	 public String yourcommercial(Model model, @RequestParam String cITY,@RequestParam String tITLE, @RequestParam String dESCRIPTION, @RequestParam String pRICE ) {
		model.addAttribute("CITY", cITY);
		model.addAttribute("TITLE", tITLE);
		model.addAttribute("DESCRIPTION", dESCRIPTION);
		model.addAttribute("PRICE", pRICE);
		// Guardamos en el repositorio ArtícleRepository.java
		if (cITY.length() == 0  || tITLE.length() == 0 || dESCRIPTION.length() == 0 || pRICE.length() == 0) {
			return this.controladorArticulos.controlCampoObligatorio_1(model,cITY, tITLE,dESCRIPTION,pRICE);


		}else {
			articleRepository.save(new Article("001", cITY, tITLE, dESCRIPTION, pRICE, "0001"));
			// Consultamos en el repositorio todos los artcículos
			// listArticlesUser = articleRepository.getListArticles("001");
			// el siguiente paso es incrustar toda la info. de listArticleUser (que contiene todos los articulos del usercon id 001) en template yourcommercial.html (recordar que hay que hacer paginación de 10 en 10 artiíulos)
			// Incrustamos en yourcommercial.html un mensaje "Nuevo articulo añadido con exito"
			model.addAttribute("exito_creacion_nuevo_anuncio", "Enhorabuena! El nuevo anuncio ha sido creado con éxito");			
			return "yourcommercial";

		}
	}
	
	@RequestMapping("/yourcommercialsold")
	public String yourcommercialsold() {
		return "yourcommercialsold";
	}
	
}
