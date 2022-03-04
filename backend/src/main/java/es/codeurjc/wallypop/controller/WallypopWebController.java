package es.codeurjc.wallypop.controller;


import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repository.UserRepository;

import java.sql.SQLException;



import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.service.CategoryService;

@Controller
public class WallypopWebController {
	
	@Autowired
	private CategoryService categoryservice;

	@Autowired
	private UserRepository userRepository;
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();
		

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("NAME", principal.getName());
			Optional<User> us = userRepository.findByNAME(principal.getName());
			if (us.isPresent()) {
				model.addAttribute("FULL_NAME", us.get().getFULL_NAME());
				model.addAttribute("TEL", us.get().getTEL());
				model.addAttribute("sell", us.get().getN_SELL());
				model.addAttribute("sold", us.get().getN_SOLD());
			}
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}

	@GetMapping("/")
	public String showIndex(Model model) {
		model.addAttribute("Categories", categoryservice.findAll());
		return "index";
	}
	
	@RequestMapping("/perfilAdmin")
	public String perfiladmin() {
		return "perfilAdmin";
	}
	
	@RequestMapping("/adcommercial")
	public String adcommercial() {
		return "adcommercial";
	}
	
	@GetMapping("/categoriasAdminListado")
	public String categoriasAdminListado(Model model) {
		model.addAttribute("category", categoryservice.findAll());
		return "categoriasAdminListado";
	}
	
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
	
	@RequestMapping("/coderebootpass")
	public String coderebootpas() {
		return "coderebootpass";
	}
	
	@RequestMapping("/commercial")
	public String commercial() {
		return "commercial";
	}
	
	@RequestMapping("/favorites")
	public String favorites() {
		return "favorites"; 
	}
	
	@RequestMapping("/formularioReporte")
	public String formularioReporte() {
		return "formularioReporte"; 
	}
	
	@RequestMapping("/help")
	public String help() {
		return "help"; 
	}
	
	@RequestMapping("/perfil")
	public String perfil() {
		return "perfil";
	}
	
	@RequestMapping("/post")
	public String post() {
		return "post";
	}
	
	@RequestMapping("/reportesAdmin")
	public String reporteadmin() {
		return "reportesAdmin";
	}
	
	@RequestMapping("/VisualizaReporte")
	public String visualizareporte() {
		return "VisualizaReporte";
	}
	
	@RequestMapping("/yourcommercial")
	public String yourcommercial() {
		return "yourcommercial";
	}
	
	@RequestMapping("/yourcommercialsold")
	public String yourcommercialsold() {
		return "yourcommercialsold";
	}
	
	
	@GetMapping("category/{id}/imagen")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
		Optional<Category> category = categoryservice.findById(id);

		if (category.get().getPHOTO() != null) {
			Resource file = new InputStreamResource(category.get().getPHOTO().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(category.get().getPHOTO().length()).body(file);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/commercial/{TITLE}")
	public String categoryFilter(Model model, @PathVariable String TITLE) {
		model.addAttribute("Articles", categoryservice.findByCategory(TITLE));
		return "commercial";
	}
	
}
