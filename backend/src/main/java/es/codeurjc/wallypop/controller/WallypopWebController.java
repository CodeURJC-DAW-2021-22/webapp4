package es.codeurjc.wallypop.controller;

import es.codeurjc.wallypop.model.*;
import es.codeurjc.wallypop.service.*;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class WallypopWebController {
    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private CategoryService categoryservice;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ReportService reportService;

    private User usLogged = null;

    @RequestMapping("/adcommercial")
    public String adcommercial(Model model) {
        model.addAttribute("Article", new Article());
        model.addAttribute("lcategory", categoryservice.findAll());
        return "adcommercial";
    }

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
                model.addAttribute("TOKEN", user.getTOKEN());
                model.addAttribute("usLogged", usLogged);
            }
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/addFavorite/{id_article}")
    public String addFavorite(Model model, @PathVariable long id_article) {
        Favorites favorites = favoritesService.findByUSERAndARTICLE(usLogged,
                articleService.findById(id_article).get());
        if (favorites != null) {
            favoritesService.delete(favorites);
        } else {
            Favorites favorite = new Favorites(usLogged, articleService.findById(id_article).get());
            favoritesService.save(favorite);
        }
        return "redirect:/commercial/";
    }

    @RequestMapping("/search/commercial")
    public String commercialFiltered(Model model, String id_category) {
        if (id_category == null) {
            return "redirect:/commercial/";
        }
        return "redirect:/commercial/" + id_category;
    }

    @RequestMapping("/category")
    public String category(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("lcategory", categoryservice.findAll());
        model.addAttribute("lcategories", 0);
        return "category";
    }

    @GetMapping("/categoryList")
    public String categoryList(Model model) {
        model.addAttribute("category", categoryservice.findAll());
        return "categoryList";
    }

    /*
     * private void deleteArticle(Article article) {
     * article.getUSER().deleteArticle(); userService.save(article.getUSER()); }
     */


    @RequestMapping("/modifyDataUser")
    public String modifyDataUser(Model model) {
        model.addAttribute("old_user", usLogged);
        model.addAttribute("user", new User());
        return "modifyDataUser";
    }

    @PostMapping("/modifyData")
    public String modifyData(Model model, User user) {
        User user2 = userService.findById(usLogged.getID_USER()).get();
        user2.setFULL_NAME(user.getFULL_NAME());
        user2.setPASSWORD(userService.encode(user.getPASSWORD()));
        user2.setTEL(user.getTEL());
        userService.save(user2);
        return "redirect:/profile";
    }

    @RequestMapping("/commercial")
    public String commercial(Model model) {
        model.addAttribute("Articles", articleService.findBySOLDFalse());
        model.addAttribute("lcategory", categoryservice.findAll());
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
        return "redirect:/commercial/" + lcategories;
    }

    @RequestMapping("/search")
    public String commercialFiltered(Model model, String query, String city) {
        if (!city.equals("") && !query.equals("")) {
            model.addAttribute("Articles", articleService
                    .findByTITLEContainingIgnoreCaseAndCITYContainingIgnoreCaseAndSOLDFalseOrDESCRIPTIONContainingIgnoreCaseAndCITYContainingIgnoreCaseAndSOLDFalse(
                            query, city));
        } else if (city.equals("")) {
            model.addAttribute("Articles", articleService
                    .findByTITLEContainingIgnoreCaseAndSOLDFalseOrDESCRIPTIONContainingIgnoreCaseAndSOLDFalse(query));
        } else if (query.equals("")) {
            model.addAttribute("Articles", articleService.findByCITYContainingIgnoreCaseAndSOLDFalse(city));
        } else {
            return "redirect:/commercial";
        }
        model.addAttribute("lcategory", categoryservice.findAll());
        return "commercial";
    }

    @GetMapping("/showReport/{id}/deleteArticle")
    public String deleteArticle(Model model, @PathVariable long id) {
        long idArticle = reportService.findById(id).get().getARTICLE().getID_ARTICLE();
        articleService.delete(idArticle);
        model.addAttribute("report", reportService.findAll());
        return "reports";
    }

    @GetMapping("/categoryList/{id}/delete")
    public String deleteCategory(Model model, @PathVariable long id) {
        categoryservice.delete(id);
        // model.addAttribute("categoryd", categoryservice.findAll());
        return "redirect:/categoryList";
    }

    /*
     * @RequestMapping("/coderebootpass") public String coderebootpas() { return
     * "coderebootpass"; }
     */

    @RequestMapping("/delete/{id_article}")
    public String deletePost(Model model, @PathVariable long id_article) {
        articleService.deletePost(id_article, usLogged.getID_USER(), usLogged.isIS_ADMIN());
        return "redirect:/yourcommercial/";
    }

    @GetMapping("/showReport/{id}/delete")
    public String deleteReport(Model model, @PathVariable long id) {
        reportService.deleteById(id);
        model.addAttribute("report", reportService.findAll());
        return "report";
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

    @GetMapping("/showReport/{id}/image")
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

    @RequestMapping("/errorcommercial")
    public String errorcommercial(Model model) {
        model.addAttribute("Articles", articleService.findAll());
        model.addAttribute("lcategory", categoryservice.findAll());
        model.addAttribute("ERROR", true);
        return "commercial";
    }

    @RequestMapping("/favorites")
    public String favorites(Model model) {
        List<Favorites> LFavArticles = usLogged.getFAVORITES();
        List<Article> lArticles = new LinkedList<>();
        for (Favorites fav : LFavArticles) {
            lArticles.add(fav.getARTICLE());
        }
        model.addAttribute("Articles", lArticles);
        return "favorites";
    }

    @RequestMapping("/{id_article}/formularioReporte")
    public String formularioReporteID(Model model, @PathVariable long id_article) {
        Report report = new Report();
        model.addAttribute("id_article", id_article);
        model.addAttribute("report", report);
        return "formularioReporte";
    }

    // Este es el m??todo que se llama cuando agragamos un nuevo anuncio y toto va
    // bien
    @RequestMapping("/yourcommercial_success")
    public String mensajeCreadoExito(Model model) {
        model.addAttribute("exito_creacion_nuevo_anuncio", "Enhorabuena! El nuevo anuncio ha sido creado con ??xito");
        model.addAttribute("Articles", usLogged.getARTICLES());
        return "yourcommercial";
    }

    private void newArticle() {
        usLogged.newArticle();
        userService.save(usLogged);
    }

    @PostMapping("/newCategory")
    public String newCategory(Model model, Category category, MultipartFile imageField) throws IOException {

        if (!imageField.isEmpty()) {
            category.setPHOTO(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
        }

        categoryservice.save(category);
        return "category";
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

    @PostMapping("/newformularioReporte/{id_article}")
    public String newformularioReporte(Model model, Report report, MultipartFile imageField,
                                       @PathVariable long id_article) throws IOException {
        if (!imageField.isEmpty()) {
            report.setPROOF(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
        }
        report.setARTICLE(articleService.findById(id_article).get());
        reportService.save(report);
        return "redirect:/commercial/";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        usLogged.updateN_Sell();
        userService.save(usLogged);
        model.addAttribute("TOKEN", usLogged.getTOKEN());
        return "profile";
    }

    @RequestMapping(value = "/post/{id_article}", method = RequestMethod.GET, produces = "application/json")
    public String postID(Model model, @PathVariable long id_article,
                         @RequestParam(required = false, defaultValue = "") String r) {
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
                    model.addAttribute("Resume", "Art??culo: " + a.getTITLE() + " Email: " + usLogged.getNAME()
                            + " Tel: " + usLogged.getTEL());
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

    @RequestMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("report", reportService.findAll());
        return "reports";
    }

    @RequestMapping("/reserve/{id_article}/{bool}")
    public String reservePost(Model model, @PathVariable long id_article, @PathVariable Boolean bool) {
        articleService.reserve(id_article, bool, usLogged.getID_USER(), usLogged.isIS_ADMIN());
        return "redirect:/post/" + id_article;
    }

    @RequestMapping("/sell/{id_article}/{bool}")
    public String sellPost(Model model, @PathVariable long id_article, @PathVariable Boolean bool) {
        articleService.sell(id_article, bool, usLogged.getID_USER(), usLogged.isIS_ADMIN());
        return "redirect:/post/" + id_article;
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

    @GetMapping("/showPost/{id}")
    public String showArticleReported(Model model, @PathVariable long id) {
        Article article = reportService.findById(id).get().getARTICLE();
        if (article != null) {
            // model.addAttribute("article", article);
            return "/post/" + article.getID_ARTICLE();
        } else {
            return "/showReport/{id}";
        }

    }

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("Categories", categoryservice.findAll());
        return "index";
    }

    @GetMapping("/showReport/{id}")
    public String showReport(Model model, @PathVariable long id) {

        Optional<Report> report = reportService.findById(id);
        if (report.isPresent()) {
            model.addAttribute("report", report.get());
            return "showReport";
        } else {
            return "reports";
        }

    }

    @RequestMapping("/sold")
    public String sold(Model model) {
        model.addAttribute("Articles", articleService.findBySOLDTrue());
        model.addAttribute("lcategory", categoryservice.findAll());
        return "commercial";
    }

    @RequestMapping("/graphic")
    public String graphic(Model model) throws SQLException {
        List<Category> lCategory = categoryservice.graphic();
        model.addAttribute("Categories", lCategory);
        return "graphic";
    }

    private void visit(Article a) {
        a.visit();
        articleService.save(a);
    }

    @RequestMapping("/showReport")
    public String showReport(Model model) {
        return "showReport";
    }

    // Este es el m??todo que se llama cuando vamos al apartado TUS ANUNCIOS
    @RequestMapping("/yourcommercial")
    public String yourcommercial(Model model) {
        model.addAttribute("exito_creacion_nuevo_anuncio", "");
        model.addAttribute("Articles", usLogged.getARTICLES());
        return "yourcommercial";
    }

    @RequestMapping("/yourcommercialsold")
    public String yourcommercialsold(Model model) {
        model.addAttribute("Articles", usLogged.getARTICLESSold());
        return "yourcommercialsold";
    }

    @RequestMapping("/token")
    public String createToken(Model model) {
        User us = userService.findById(usLogged.getID_USER()).get();
        userService.newToken(us);
        usLogged = us;
        userService.save(us);
        return "redirect:/profile";
    }

}