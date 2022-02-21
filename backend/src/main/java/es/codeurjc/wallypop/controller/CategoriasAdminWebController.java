package es.codeurjc.wallypop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoriasAdminWebController {
	@RequestMapping("/categoriasAdmin")
	public String categoriasAdmin() {
		return "categoriasAdmin";
	}
}
