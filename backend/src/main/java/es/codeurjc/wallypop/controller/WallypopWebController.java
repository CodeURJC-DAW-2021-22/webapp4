package es.codeurjc.wallypop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.model.Mail;
import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.CategoryService;
import es.codeurjc.wallypop.service.EmailService;
import es.codeurjc.wallypop.service.MapService;
import es.codeurjc.wallypop.service.ReportService;
import es.codeurjc.wallypop.service.UserService;
import net.bytebuddy.matcher.ModifierMatcher.Mode;

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

	@GetMapping("/")
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
		newArticle(); // SUM 1 to N_SELL
		articleService.save(article);
		return "yourcommercial_success";
	}
	
	private void newArticle() {
		usLogged.newArticle();
		userService.save(usLogged);
	}
	
	/* private void deleteArticle(Article article) {
		article.getUSER().deleteArticle();
		userService.save(article.getUSER());
	} */

	@GetMapping("/categoriasAdminListado")
	public String categoriasAdminListado(Model model) {
		model.addAttribute("category", categoryservice.findAll());
		return "categoriasAdminListado";
	}

	@RequestMapping("/categoriasAdmin")
	public String categoriasAdmin(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("lcategory", categoryservice.findAll());
		model.addAttribute("lcategories", 0);
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

	/* @RequestMapping("/coderebootpass")
	public String coderebootpas() {
		return "coderebootpass";
	} */

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
		model.addAttribute("Articles", categoryservice.findById(id).get().getARTICLES());
		model.addAttribute("lcategory", categoryservice.findAll());
		return "commercial";
	}
	
	@PostMapping("/commercialCategory")
	public String commercialCategory(Model model, long lcategories) {
		return "redirect:/commercial/" + String.valueOf(lcategories);
	}

	@RequestMapping("/favorites")
	public String favorites() {
		return "favorites";
	}

	@PostMapping("/newformularioReporte/{id_article}")
	public String newformularioReporte(Model model, Report report, MultipartFile imageField,@PathVariable long id_article) throws IOException {
		if (!imageField.isEmpty()) {
			report.setPROOF(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
		}
        report.setARTICLE(articleService.findById(id_article).get());
		reportService.save(report);
		return "redirect:/commercial/";
	}

	@RequestMapping("/{id_article}/formularioReporte")
	public String formularioReporteID(Model model, @PathVariable long id_article) {
		Report report = new Report();
		model.addAttribute("id_article",id_article);
		model.addAttribute("report", report);
		return "formularioReporte";
	}

	/*@RequestMapping("/help")
	public String help() {
		return "help";
	} */

	@RequestMapping("/perfil")
	public String perfil() {
		return "perfil";
	}
	
	@RequestMapping(value = "/post/{id_article}", method = RequestMethod.GET, produces = "application/json")
	public String postID(Model model, @PathVariable long id_article, @RequestParam(required = false, defaultValue = "") String r) {
		Optional<Article> article = articleService.findById(id_article);
		if (!r.isEmpty()) {
			model.addAttribute("emailSended", true);
		}
		if (article.isPresent()) {
			Article a = article.get();
			if (a.getPHOTO() == null) {
				model.addAttribute("sample_img", true);
			}
			String direction = a.getCITY() + " " + a.getPOSTAL_CODE();
			try {
				String[] resultAPI = MapService.getLatitudeLongitude(direction);
				model.addAttribute("MapAPI", true);
				model.addAttribute("lat", resultAPI[0]);
				model.addAttribute("lon", resultAPI[1]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (usLogged != null) {
				long id_user_logged = usLogged.getID_USER();
				if (id_user_logged == a.getUserID() || usLogged.isIS_ADMIN()) {
					model.addAttribute("Owner", true);
				} else {
					// Only sum a visit if im not the owner o an admin
					visit(a);
					model.addAttribute("ID_BUYER", id_user_logged);
					model.addAttribute("To", a.getUserEmail());
					model.addAttribute("Resume", "Artículo: " + a.getTITLE() + " Email: " + usLogged.getNAME() + " Tel: " + usLogged.getTEL());
					model.addAttribute("Mail", new Mail());
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
		
	@PostMapping("/message/{id_article}/{id_buyer}")
	public String sendEmail(Model model, Mail mail, @PathVariable long id_article, @PathVariable long id_buyer) {
		try {
			EmailService.sendEmail(mail);
			return "redirect:/post/" + id_article + "/?r=0";
		} catch (Exception e) {
			return "redirect:/post/" + id_article + "/?r=1";
		}
	}
	
	@RequestMapping("/reserve/{id_article}/{bool}")
	public String reserve(Model model, @PathVariable long id_article, @PathVariable Boolean bool) {
		articleService.reserve(id_article, bool, usLogged.getID_USER(), usLogged.isIS_ADMIN());
		return "redirect:/post/" + id_article;
	}
	
	@RequestMapping("/sell/{id_article}/{bool}")
	public String sell(Model model, @PathVariable long id_article, @PathVariable Boolean bool) {
		articleService.sell(id_article, bool, usLogged.getID_USER(), usLogged.isIS_ADMIN());
		return "redirect:/post/" + id_article;
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
		Article article = reportService.findById(id).get().getARTICLE();
		if (article != null) {
			//model.addAttribute("article", article);
			return "/post/" + article.getID_ARTICLE();
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
	
	@GetMapping("/VisualizaReporte/{id}/deleteArticle")
	public String deleteArticle(Model model, @PathVariable long id) {
		long idArticle = reportService.findById(id).get().getARTICLE().getID_ARTICLE(); 
		articleService.delete(idArticle); 
		model.addAttribute("report", reportService.findAll());
		return "reportesAdmin";
	}

	// Este es el método que se llama cuando vamos al apartado TUS ANUNCIOS
	@RequestMapping("/yourcommercial")
	public String yourcommercial(Model model) {
		model.addAttribute("exito_creacion_nuevo_anuncio", "");
		model.addAttribute("Articles", usLogged.getARTICLES());
		return "yourcommercial";
	}

	// Este es el método que se llama cuando agragamos un nuevo anuncio y toto va
	// bien
	@RequestMapping("/yourcommercial_success")
	public String mensajeCreadoExito(Model model) {
		model.addAttribute("exito_creacion_nuevo_anuncio", "Enhorabuena! El nuevo anuncio ha sido creado con éxito");
		model.addAttribute("Articles", usLogged.getARTICLES());
		return "yourcommercial";
	}

	@RequestMapping("/yourcommercialsold")
	public String yourcommercialsold(Model model) {
		model.addAttribute("Articles", usLogged.getARTICLESSold());
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


}
