package es.codeurjc.wallypop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.service.ReportService;

@RestController
@RequestMapping("/api/report")
public class ReportRestController {
	
	@Autowired
	private ReportService reportService;
	
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


}
