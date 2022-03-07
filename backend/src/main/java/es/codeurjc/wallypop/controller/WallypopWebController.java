package es.codeurjc.wallypop.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.CategoryService;
import es.codeurjc.wallypop.service.ReportService;
import es.codeurjc.wallypop.service.UserService;

@Controller
public class WallypopWebController {

	@Autowired
	private CategoryService categoryservice;

	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ReportService reportService;
	
	private User usLogged = null;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("NAME", principal.getName());
			Optional<User> us = userService.findByNAME(principal.getName());
			if (us.isPresent()) {
				User user = us.get();
				usLogged = user;
				model.addAttribute("ID_USER", user.getID_USER());
				model.addAttribute("FULL_NAME", user.getFULL_NAME());
				model.addAttribute("TEL", user.getTEL());
				model.addAttribute("sell", user.getN_SELL());
				model.addAttribute("sold", user.getN_SOLD());
			}
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}

	@GetMapping(value = { "/", "/index" })
	public String showIndex(Model model) {
		model.addAttribute("Categories", categoryservice.findAll());
		return "index";
	}

	@RequestMapping("/adcommercial")
	public String adcommercial(Model model) {
		model.addAttribute("Article", new Article());
		model.addAttribute("lcategory", categoryservice.findAll());
		return "adcommercial";
	}

	@PostMapping("/newcommercial")
	public String newCommercial(Model model, Article article, MultipartFile imageField) throws IOException {
		if (!imageField.isEmpty()) {
			article.setPHOTO(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
		}
		article.setUSER(usLogged);
		articleService.save(article);
		return "yourcommercial_success";
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
	public String newCategory(Model model, Category category, MultipartFile imageField) throws IOException {

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
	public String commercial(Model model) {
		model.addAttribute("Articles", articleService.findAll());
		model.addAttribute("lcategory", categoryservice.findAll());
		return "commercial";
	}
	
	@RequestMapping("/errorcommercial")
	public String errorcommercial(Model model) {
		model.addAttribute("Articles", articleService.findAll());
		model.addAttribute("lcategory", categoryservice.findAll());
		model.addAttribute("ERROR", true);
		return "commercial";
	}

	@RequestMapping("/commercial/{id}")
	public String commercial_filter(Model model, @PathVariable long id) {
		model.addAttribute("Filtered", true);
		// model.addAttribute("Articles", articleService.findArticlesByCategory(id));
		model.addAttribute("Articles", articleService.findAll());
		model.addAttribute("lcategory", categoryservice.findAll());
		return "commercial";
	}

	@RequestMapping("/favorites")
	public String favorites() {
		return "favorites";
	}

	@PostMapping("/newformularioReporte")
	public String newformularioReporte(Model model, Report report, MultipartFile imageField) throws IOException {
		if (!imageField.isEmpty()) {
			report.setPROOF(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
		}
		reportService.save(report);
		return "formularioReporte";
	}

	@RequestMapping("/formularioReporte")
	public String formularioReporte(Model model) {
		model.addAttribute("report", new Report());
		return "formularioReporte";
	}

	@RequestMapping("/{id_article}/formularioReporte")
	public String formularioReporteID(Model model, @PathVariable long id_article) {
		Report report = new Report();
		report.setARTICLE(articleService.findById(id_article).get());
		model.addAttribute("report", report);
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
	public String post(Model model) {
		return "post";
	}
	
	@GetMapping("/post/{id}")
	public String postID(Model model, @PathVariable long id) {
		Optional<Article> article = articleService.findById(id);
		if (article.isPresent()) {
			Article a = article.get();
			if (usLogged != null) {
				if (usLogged.getID_USER() == a.getUserID() || usLogged.isIS_ADMIN()) {
					model.addAttribute("Owner", true);
				} else {
					// Only sum a visit if im not the owner o an admin
					visit(a);
				}
			} else {
				// Visit because im not registered user
				visit(a);
			}
			
			model.addAttribute("Article", a);
			model.addAttribute("User", a.getUSER());
			return "post";
		} else {
			return "errorcommercial";
		}
	}
	
	private void visit(Article a) {
		a.visit();
		articleService.save(a);
	}

	@RequestMapping("/reportesAdmin")
	public String reporteadmin(Model model) {
		model.addAttribute("report", reportService.findAll());
		return "reportesAdmin";
	}

	@RequestMapping("/VisualizaReporte")
	public String visualizareporte(Model model) {
		return "VisualizaReporte";
	}

	@GetMapping("/VisualizaReporte/{id}")
	public String showReport(Model model, @PathVariable long id) {

		Optional<Report> report = reportService.findById(id);
		if (report.isPresent()) {
			model.addAttribute("report", report.get());
			return "VisualizaReporte";
		} else {
			return "reporteAdmin";
		}

	}

	@GetMapping("/VisualizaReporte/{id}/image")
	public ResponseEntity<Object> downloadZIPReport(@PathVariable long id) throws SQLException {
		Optional<Report> report = reportService.findById(id);

		if (report.get().getPROOF() != null) {
			Resource file = new InputStreamResource(report.get().getPROOF().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/zip")
					.contentLength(report.get().getPROOF().length()).body(file);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/VisualizarPost/{id}")
	public String showArticleReported(Model model, @PathVariable long id) {
		Optional<Article> article = articleService
				.findById((long) reportService.findById(id).get().getARTICLE().getID_ARTICLE());
		if (article.isPresent()) {
			model.addAttribute("article", article.get());
			return "post";
		} else {
			return "/VisualizaReporte/{id}";
		}

	}
	
	@GetMapping("/VisualizaReporte/{id}/delete")
	public String deleteReport(Model model, @PathVariable long id) {
		reportService.delete(id);
		model.addAttribute("report", reportService.findAll());
		return "reportesAdmin";
	}

	// Este es el método que se llama cuando vamos al apartado TUS ANUNCIOS
	@RequestMapping("/yourcommercial")
	public String yourcommercial(Model model) {
		model.addAttribute("exito_creacion_nuevo_anuncio", "");
		model.addAttribute("Articles", articleService.findAll());
		return "yourcommercial";
	}

	// Este es el método que se llama cuando agragamos un nuevo anuncio y toto va
	// bien
	@RequestMapping("/yourcommercial_success")
	public String mensajeCreadoExito(Model model) {
		model.addAttribute("exito_creacion_nuevo_anuncio", "Enhorabuena! El nuevo anuncio ha sido creado con éxito");
		model.addAttribute("Articles", articleService.findAll());
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

	@GetMapping("article/{id}/imagen")
	public ResponseEntity<Object> downloadImageArticle(@PathVariable long id) throws SQLException {
		Optional<Article> article = articleService.findById(id);

		if (article.get().getPHOTO() != null) {
			Resource file = new InputStreamResource(article.get().getPHOTO().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(article.get().getPHOTO().length()).body(file);
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
