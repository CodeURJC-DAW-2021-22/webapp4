package es.codeurjc.wallypop.controller;

import java.io.IOException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.service.ReportService; 

@Controller 
public class FormularioReporteWebController {
	
	@Autowired
	private ReportService reportService;
	
	@PostMapping("/newformularioReporte")
	public String newformularioReporte(Model model,Report report,MultipartFile imageField) throws IOException{
		if (!imageField.isEmpty()) {
			report.setPROOF(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));			
		}
		reportService.save(report);
		return "formularioReporte"; }
	
	
	@RequestMapping("/formularioReporte")
	public String formularioReporte(Model model) {
	model.addAttribute("report",new Report());
	return "formularioReporte";
	}

}