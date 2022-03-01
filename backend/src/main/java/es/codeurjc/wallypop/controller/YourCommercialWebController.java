package es.codeurjc.wallypop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.repositories.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class YourCommercialWebController {
	
	@Autowired
	private ArticleRepository articleRepository;

	@RequestMapping("/yourcommercial")
	 public String yourcommercial(Model model, @RequestParam String cITY,@RequestParam String tITLE, @RequestParam String dESCRIPTION, @RequestParam float pRICE ) {
		model.addAttribute("CITY", cITY);
		model.addAttribute("TITLE", tITLE);
		model.addAttribute("DESCRIPTION", dESCRIPTION);
		model.addAttribute("PRICE", pRICE);
		articleRepository.save(new Article("001", cITY, tITLE, dESCRIPTION, (float) pRICE, "0001"));
		return "yourcommercial";
	}
	
	@RequestMapping("/yourcommercialsold")
	public String yourcommercialsold() {
		return "yourcommercialsold";
	}
	
}
