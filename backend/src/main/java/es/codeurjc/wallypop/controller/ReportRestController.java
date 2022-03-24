package es.codeurjc.wallypop.controller;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.ReportService;

@RestController
@RequestMapping("/api/report")
public class ReportRestController {
	
	@Autowired
	private ReportService reportService;
	@Autowired
	private ArticleService articleService;
	
	 @GetMapping("/")
	    List<Report> all() {
	        return reportService.findAll();
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<Report> getReport(@PathVariable long id) {
	        Optional<Report> or = reportService.findById(id);
	        if (or.isPresent()) {
	            Report report = or.get();
	            return new ResponseEntity<>(report, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
		@GetMapping("/{id}/reporte/proof")
		public ResponseEntity<Object> downloadReporteProof(@PathVariable long id) throws SQLException {

			Report report = reportService.findById(id).orElseThrow();

			if (report.getPROOF() != null) {

				Resource file = new InputStreamResource(report.getPROOF().getBinaryStream());

				return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
						.contentLength(report.getPROOF().length()).body(file);

			} else {
				return ResponseEntity.notFound().build();
			}
		}
		
	    
	    @GetMapping("/{id}/reportedArticle")
	    public ResponseEntity<Article> getArticleReported(@PathVariable long id) {
	        Optional<Article> oa = articleService.findById(reportService.findById(id).get().getARTICLE().getID_ARTICLE());
	        if (oa.isPresent()) {
	            Article articleReported = oa.get();
	            return new ResponseEntity<>(articleReported, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

		@GetMapping("/{id}/reportedArticle/image")
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
		

	    
	    @PostMapping("")
	    @ResponseStatus(HttpStatus.CREATED)
	    public Report createReport(@RequestBody Report report) {
	        reportService.save(report);
	        return report;
	    }
	    
		@PostMapping("/{id}/image")
		public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
				throws IOException {
			Report report = reportService.findById(id).orElseThrow();

			URI location = fromCurrentRequest().build().toUri();
			report.setPROOF(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
			reportService.save(report);

			return ResponseEntity.created(location).build();
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
