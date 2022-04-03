package es.codeurjc.wallypop.controller.api.admin;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/reports")
public class AdminReportRestController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("")
    List<Report> all() {
        return reportService.findAll();
    }

    @GetMapping("/{idReport}")
    public ResponseEntity<Report> getReport(@PathVariable long idReport) {

        Optional<Report> or = reportService.findById(idReport);
        if (or.isPresent()) {
            Report report = or.get();
            return new ResponseEntity<>(report, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/proof")
    public ResponseEntity<Object> downloadReporteProof(@PathVariable long id) throws SQLException {

        Report report = reportService.findById(id).orElseThrow();

        if (report.getPROOF() != null) {

            Resource file = new InputStreamResource(report.getPROOF().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/zip")
                    .contentLength(report.getPROOF().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/article")
    public ResponseEntity<Article> getArticleReported(@PathVariable long id) {
        Optional<Article> oa = articleService.findById(reportService.findById(id).get().getARTICLE().getID_ARTICLE());
        if (oa.isPresent()) {
            Article articleReported = oa.get();
            return new ResponseEntity<>(articleReported, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/article/image")
    public ResponseEntity<Object> downloadReportedArticleImage(@PathVariable long id) throws SQLException {

        Article article = articleService.findById(reportService.findById(id).get().getARTICLE().getID_ARTICLE()).orElseThrow();

        if (article.getPHOTO() != null) {

            Resource file = new InputStreamResource(article.getPHOTO().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(article.getPHOTO().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/rejectReport")
    public ResponseEntity<Report> deleteReport(@PathVariable long id) {

        try {
            reportService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}/aceptReport")
    public ResponseEntity<Article> deleteArticleReported(@PathVariable long id) {

        try {
            articleService.delete(reportService.findById(id).get().getARTICLE().getID_ARTICLE());
            reportService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
