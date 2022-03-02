package es.codeurjc.wallypop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.repositories.ArticleRepository;

@Controller
public class AdcommercialWebController {
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private YourCommercialWebController controladorYourCommercial;

	@RequestMapping("/adcommercial_1")
	 public String adcommercial_1 (Model model, @RequestParam String cITY,@RequestParam String tITLE, @RequestParam String dESCRIPTION, @RequestParam String pRICE ) {
		model.addAttribute("CITY", cITY);
		model.addAttribute("TITLE", tITLE);
		model.addAttribute("DESCRIPTION", dESCRIPTION);
		model.addAttribute("PRICE", pRICE);
		
		if (cITY.length() == 0  || tITLE.length() == 0 || dESCRIPTION.length() == 0 || pRICE.length() == 0) {
			return this.controlCampoObligatorio_1(model,cITY, tITLE,dESCRIPTION,pRICE); //call method this class

		}else {
			articleRepository.save(new Article("001", cITY, tITLE, dESCRIPTION, pRICE, "0001")); //save data in repository Article
			// Consultamos en el repositorio todos los artcículos
			// listArticlesUser = articleRepository.getListArticles("001");
			// el siguiente paso es incrustar toda la info. de listArticleUser (que contiene todos los articulos del usercon id 001) en template yourcommercial.html (recordar que hay que hacer paginación de 10 en 10 artiíulos)
			// Incrustamos en yourcommercial.html un mensaje "Nuevo articulo añadido con exito"
			this.controladorYourCommercial.mensajeCreadoExito(model);
			return "yourcommercial_exito_message"; //call methos YourComercialController
		}
	}
	 	 
	 @RequestMapping("/adcommercial")
	 public String adcommercial(Model model) {
		model.addAttribute("campos_obligatorios", "");				// moustache
		model.addAttribute("campo_obligatorio_city", "");			// moustache
		model.addAttribute("campo_obligatorio_title", "");			// moustache
		model.addAttribute("campo_obligatorio_description", "");	// moustache			
		model.addAttribute("campo_obligatorio_price", "");			// moustache
		return "adcommercial";
	 }

	public String controlCampoObligatorio_1(Model model, String city, String title, String description, String price) {
		model.addAttribute("campos_obligatorios", "Los campos marcados con * son obligatorios");	// moustache
		if (city.length() == 0) {
			model.addAttribute("campo_obligatorio_city", "*");			// moustache
			model.addAttribute("campo_obligatorio_title", "");			// moustache
			model.addAttribute("campo_obligatorio_description", "");	// moustache		
			model.addAttribute("campo_obligatorio_price", "");			// moustache

		}else if (title.length() == 0) {
			model.addAttribute("campo_obligatorio_city", "");			// moustache
			model.addAttribute("campo_obligatorio_title", "*");			// moustache
			model.addAttribute("campo_obligatorio_description", "");	// moustache		
			model.addAttribute("campo_obligatorio_price", "");			// moustache
			
		}else if (description.length() == 0) {
			model.addAttribute("campo_obligatorio_city", "");			// moustache
			model.addAttribute("campo_obligatorio_title", "");			// moustache
			model.addAttribute("campo_obligatorio_description", "*");	// moustache			
			model.addAttribute("campo_obligatorio_price", "");		// moustache
			
		}else if (price.length() == 0) {
			model.addAttribute("campo_obligatorio_city", "");			// moustache
			model.addAttribute("campo_obligatorio_title", "");			// moustache
			model.addAttribute("campo_obligatorio_description", "");	// moustache			
			model.addAttribute("campo_obligatorio_price", "*");			// moustache
			
		}
		 return "adcommercial";
	}
	 
}
