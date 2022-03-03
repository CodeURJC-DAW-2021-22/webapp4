package es.codeurjc.wallypop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.repositories.ReportRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ReporteAdminWebController {

	@Autowired
	private ReportRepository reportRepository;
	
	@JsonIgnore
	@RequestMapping(value="/reporteAdmin")
	public String reporteadmin(Model model) {
		model.addAttribute("report",reportRepository.findAll());
		return "reporteAdmin";
	}
	
	@RequestMapping("/VisualizaReporte")
	public String visualizareporte(Model model) {
		return "VisualizaReporte";
	}
	
	@GetMapping("/VisualizaReporte/{id}")
	public String showReeport(Model model, @PathVariable long id) {

		Optional<Report> report = reportRepository.findById(id);
		if (report.isPresent()) {
			model.addAttribute("report", report.get());
			return "VisualizaReporte";
		} else {
			return "reporteAdmin";
		}

	}
	
	@GetMapping("/VisualizaReporte/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
		Optional<Report> report = reportRepository.findById(id);

		if (report.get().getPROOF() != null) {
			Resource file = new InputStreamResource(report.get().getPROOF().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(report.get().getPROOF().length()).body(file);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	

}
