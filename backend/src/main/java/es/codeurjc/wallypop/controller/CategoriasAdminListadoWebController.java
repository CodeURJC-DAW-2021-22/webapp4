package es.codeurjc.wallypop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoriasAdminListadoWebController {
	@RequestMapping("/categoriasAdminListado")
	public String categoriasAdminListado() {
		return "categoriasAdminListado";
	}
}