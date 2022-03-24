package es.codeurjc.wallypop.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	    @PostMapping("")
	    @ResponseStatus(HttpStatus.CREATED)
	    public Report createReport(@RequestBody Report report) {
	        reportService.save(report);
	        return report;
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Report> updateReport(@PathVariable long id, @RequestBody Report updatedReport) throws SQLException {
	        if (reportService.exist(id)) {
	            return reportService.updateReport(id, updatedReport);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Report> deleteReport(@PathVariable long id) {

	        try {
	            reportService.delete(id);
	            return new ResponseEntity<>(null, HttpStatus.OK);

	        } catch (EmptyResultDataAccessException e) {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }

	    }

}
