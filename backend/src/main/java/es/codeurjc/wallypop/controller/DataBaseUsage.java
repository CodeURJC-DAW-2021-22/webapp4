package es.codeurjc.wallypop.controller;

import java.io.IOException;
import java.sql.Blob;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.CategoryService;
import es.codeurjc.wallypop.service.ReportService;
import es.codeurjc.wallypop.service.UserService;

@Controller
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ReportService reportService;

	@Override
	public void run(String... args) throws Exception {
		// User
		userService.save(new User("Jesús", passwordEncoder.encode("1234"), "j@j.es", "666666666", true));
		userService.save(new User("David", passwordEncoder.encode("1234"), "d@d.es", "666666666"));

		// Category
		categoryService.save(new Category("Videojuegos", "Compra y vende videojuegos de todo tipo",
				convertToBLOB("/sample_image/videojuego.jpg"), "gamepad"));
		categoryService.save(new Category("Ropa", "Dale una segunda vida a las prendas que ya no usas",
				convertToBLOB("/sample_image/ropa.png"), "shirt"));		
		categoryService.save(new Category("Artesanía", "Lo mejor de la tierra cerca de tí",
				convertToBLOB("/sample_image/figurasArtesaniaCanaria.jpg"), "artstation"));
		categoryService.save(new Category("Herramientas", "Hazlo tu mismo, lo bueno sale barato",
				convertToBLOB("/sample_image/herramientas.jpg"), "screwdriver-wrench"));
		categoryService.save(new Category("Informática", "Viste a tu ordenador con los mejores complementos",
				convertToBLOB("/sample_image/informatica.jpg"), "desktop"));
		categoryService.save(new Category("Naturaleza", "Salir al campo nunca fue tan fácil",
				convertToBLOB("/sample_image/naturaleza.jpg"), "person-hiking"));
		categoryService.save(new Category("Perfumes", "Los perfumes más conocidos a precios desconocidos",
				convertToBLOB("/sample_image/perfumes.jpg"), "gift"));
		
		List<Category> lCat = new LinkedList<>();
		for (int i = 1; i< categoryService.findAll().size(); i++) {
			lCat.add(categoryService.findById(i).get());
		}

		// Article
		articleService.save(new Article(userService.findById(1).get(), "Zapatillas deportivas", "Descripción","Madrid", "28001",
			(float) 99.99, convertToBLOB("/sample_image/ropa.png"), lCat));
		articleService.save(new Article(userService.findById(2).get(), "Zapatillas correr", "Descripción", "Madrid", "28001",
			(float) 50.99, convertToBLOB("/sample_image/ropa.png"), lCat));			
		articleService.save(new Article(userService.findById(1).get(), "Botas montaña", "Deja que tus pies descanses mientras caminas", "Extremadura", "23001",
			(float) 59.99, convertToBLOB("/sample_image/botasMontana.jpg"), lCat));
		articleService.save(new Article(userService.findById(1).get(), "Cestas de mimbre", "Cestas de mimbre hechas a mano, con asa muy resistente", "Canarias", "28001",
			(float) 8.99, convertToBLOB("/sample_image/cestaMimbre.jpg"), lCat));
		articleService.save(new Article(userService.findById(1).get(), "Jarrones", "Jarrones de cerámica hechos a mano, con colores vivos", "Teruel", "28001",
			(float) 8.99, convertToBLOB("/sample_image/jarron.jpg"), lCat));
		articleService.save(new Article(userService.findById(1).get(), "Llaves inglesas", "Se venden llaves inglesas con mango", "Barcelona", "28001",
			(float) 18.99, convertToBLOB("/sample_image/llaveInglesa.jpg"), lCat));
		articleService.save(new Article(userService.findById(1).get(), "Kit electrónica", "Se vende kit de electrónica para que puedas arreglar tus dispositivos electrónicos como móviles, ordenadores y todo tipo de dispositivos", "A Coruña", "28001",
			(float) 14.99, convertToBLOB("/sample_image/kitElectronica.jpg"), lCat));
		articleService.save(new Article(userService.findById(1).get(), "Teclados", "Teclado castellano. Tu imaginación no tiene límites", "Madrid", "28001",
			(float) 9.99, convertToBLOB("/sample_image/teclado.jpg"), lCat));
		articleService.save(new Article(userService.findById(1).get(), "Ratones", "Ratones inalámbricos muy veloces. Deja que tu puntero apunte por ti", "Cuenca", "28001",
			(float) 4.99, convertToBLOB("/sample_image/raton.jpg"), lCat));
		articleService.save(new Article(userService.findById(1).get(), "Saco de dormir", "Se venden sacos de dormir muy confortables, para que no pases frio durante la noche", "Madrid", "28001",
			(float) 29.99, convertToBLOB("/sample_image/sacoDormir.jpg"), lCat));
		articleService.save(new Article(userService.findById(1).get(), "Linterna", "Se venden linternas ligeras a buen precio, viene sin dos pilas AAA", "Aragón", "28001",
			(float) 4.99, convertToBLOB("/sample_image/linterna.jpg"), lCat));
		articleService.save(new Article(userService.findById(1).get(), "Colonia Aquarel", "Se venden perfume Aquarel para mujer a buen precio, el olor dura mucho tiempo", "Asturias", "28001",
			(float) 11.99, convertToBLOB("/sample_image/perfume.jpg"), lCat));
		articleService.save(new Article(userService.findById(1).get(), "Colonia para niños", "Se venden colonias para niños a buen precio", "Granada", "28001",
			(float) 4.99, convertToBLOB("/sample_image/perfumeNinno.jpg"), lCat));

		// Report
		reportService.save(new Report(articleService.findAll().get(0), "a@a.es", "Descripción"));
	}

	public void setCategoryImage(Category category, String ruta) throws IOException {
		Resource image = new ClassPathResource(ruta);
		category.setPHOTO(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

	public Blob convertToBLOB(String ruta) throws IOException {
		Resource image = new ClassPathResource(ruta);
		return BlobProxy.generateProxy(image.getInputStream(), image.contentLength());
	}
}
