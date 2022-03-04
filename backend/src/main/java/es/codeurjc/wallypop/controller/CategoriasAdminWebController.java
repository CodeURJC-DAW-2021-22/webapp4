package es.codeurjc.wallypop.controller;
import java.io.IOException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.service.CategoryService;

@Controller
public class CategoriasAdminWebController {
	
	@Autowired
	private CategoryService categoryservice;
	
	@RequestMapping("/categoriasAdmin")
	public String categoriasAdmin(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("lcategory", categoryservice.findAll());
		return "categoriasAdmin";
	}
	
	@PostMapping("/newCategory")
	public String newCategory(Model model, Category category, MultipartFile imageField) throws IOException{
		
		if (!imageField.isEmpty()) {
			category.setPHOTO(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
		}

		categoryservice.save(category);
		return "categoriasAdmin";
	}
}
