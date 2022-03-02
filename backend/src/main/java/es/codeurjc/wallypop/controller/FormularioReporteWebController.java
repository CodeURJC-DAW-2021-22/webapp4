package es.codeurjc.wallypop.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.repositories.ArticleRepository;
import es.codeurjc.wallypop.repositories.ReportRepository; 

@Controller 
public class FormularioReporteWebController {
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@PostMapping("/newformularioReporte")
	public String newformularioReporte(Model model,Report report,MultipartFile imageField) throws IOException{
		if (!imageField.isEmpty()) {
			report.setPROOF(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));			
		}
		reportRepository.save(report);
		return "formularioReporte"; }
	
	
	@RequestMapping("/formularioReporte")
	public String formularioReporte(Model model) {
	model.addAttribute("report",new Report());
	return "formularioReporte";
	}

}