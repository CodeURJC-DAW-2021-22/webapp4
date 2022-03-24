package es.codeurjc.wallypop.controller.api;

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
@RequestMapping("/api/reports")
public class ReportRestController {
	
	@Autowired
	private ReportService reportService;

	    @PostMapping("")
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Report> createReport(@RequestBody Report report) {
	        reportService.save(report);
			return new ResponseEntity<>(report, HttpStatus.OK);
	    }
	    


}
