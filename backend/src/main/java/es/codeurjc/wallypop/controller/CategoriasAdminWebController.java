package es.codeurjc.wallypop.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.service.CategoryService;

@Controller
public class CategoriasAdminWebController {
	
	@Autowired
	private CategoryService categoryservice;
	
	@RequestMapping("/categoriasAdmin")
	public String categoriasAdmin(Model model) {
		model.addAttribute("category", new Category());
		return "categoriasAdmin";
	}
	
	@PostMapping("/newCategory")
	public String newCategory(Model model, Category category) throws IOException{
		categoryservice.save(category);
		return "categoriasAdmin";
	}
}
