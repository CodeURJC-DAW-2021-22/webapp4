package es.codeurjc.wallypop.controller;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.service.CategoryService;

@Controller
public class CategoriasAdminListadoWebController {

	@Autowired
	private CategoryService categoryservice;

	@GetMapping("/categoriasAdminListado")
	public String categoriasAdminListado(Model model) {
		model.addAttribute("category", categoryservice.findAll());
		return "categoriasAdminListado";
	}
	
	@GetMapping("/{id}/image")
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

}
