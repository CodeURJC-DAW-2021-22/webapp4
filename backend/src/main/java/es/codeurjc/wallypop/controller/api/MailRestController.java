package es.codeurjc.wallypop.controller.api;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Mail;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.EmailService;
import es.codeurjc.wallypop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/mail")
public class MailRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @PostMapping("/{from}/{idArticle}")
    public ResponseEntity<Mail> mail(@PathVariable long from, @PathVariable long idArticle, @RequestBody Mail mail) {
        //Mail mail = new Mail();
        Article a = articleService.findById(idArticle).get();
        User us = userService.findById(from).get();
        mail.setTo(a.getUserEmail());
        mail.setResume("Artículo " + a.getTITLE() + " Correo: " + us.getNAME() + " Tel: " + us.getTEL());
        mail.setMessage(mail.getMessage());
        EmailService.sendEmail(mail);
        return ResponseEntity.ok(mail);
    }

}


