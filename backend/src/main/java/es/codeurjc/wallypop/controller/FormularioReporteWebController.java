package es.codeurjc.wallypop.controller;
import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.repositories.ReportRepository; 

@Controller 
public class FormularioReporteWebController {
	
	@Autowired
	private ReportRepository reportRepository;
	
	@RequestMapping("/formularioReporte")
	public String formularioReporte(Model model,@RequestParam String dESCRIPTION) {
		model.addAttribute("DESCRIPTION", dESCRIPTION);
		reportRepository.save(new Report(null,null,null,null));
		return "formularioReporte"; }
}
