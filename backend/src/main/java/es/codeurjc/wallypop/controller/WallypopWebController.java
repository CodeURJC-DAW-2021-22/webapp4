package es.codeurjc.wallypop.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repository.UserRepository;

@Controller
public class WallypopWebController {

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
	
	@RequestMapping("/categoriasAdminListado")
	public String categoriasAdminListado() {
		return "categoriasAdminListado";
	}
	
	@RequestMapping("/categoriasAdmin")
	public String categoriasAdmin() {
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
	
}
