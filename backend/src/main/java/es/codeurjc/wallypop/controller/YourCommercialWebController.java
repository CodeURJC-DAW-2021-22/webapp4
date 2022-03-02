package es.codeurjc.wallypop.controller;

import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class YourCommercialWebController {
	
	// private List listArticlesUser;
	
	@RequestMapping("/yourcommercial")
	 public String yourcommercial(Model model) {
		model.addAttribute("exito_creacion_nuevo_anuncio", "");	// moustache	
			return "yourcommercial";

		
	}
	@RequestMapping("/yourcommercial_exito_message")
	public String mensajeCreadoExito(Model model) {
		model.addAttribute("exito_creacion_nuevo_anuncio", "Enhorabuena! El nuevo anuncio ha sido creado con éxito");	// moustache		
		return "yourcommercial";
	}
	
	@RequestMapping("/yourcommercialsold")
	public String yourcommercialsold() {
		return "yourcommercialsold";
	}
	
	
	
}
