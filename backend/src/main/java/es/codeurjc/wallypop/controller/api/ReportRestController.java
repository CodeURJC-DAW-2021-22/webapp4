package es.codeurjc.wallypop.controller.api;

import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.ReportService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/reports")
public class ReportRestController {


    @Autowired
    private ReportService reportService;
    @Autowired
    private ArticleService articleService;

    @PostMapping("/{idArticle}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Report> createReport(@PathVariable long idArticle, @RequestBody Report report) {
        report.setARTICLE(articleService.findById(idArticle).get());
        reportService.save(report);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @PostMapping("/{idReport}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long idReport, @RequestParam MultipartFile imageFile)
            throws IOException {
        Report report = reportService.findById(idReport).orElseThrow();

        URI location = fromCurrentRequest().build().toUri();
        report.setPROOF(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        reportService.save(report);

        return ResponseEntity.created(location).build();
    }

}
