package es.codeurjc.wallypop.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.repositories.ReportRepository; 

@Controller 
public class FormularioReporteWebController {
	
	@Autowired
	private ReportRepository reportRepository;
	
	@RequestMapping("/formularioReporte")
	public String formularioReporte(Model model,@RequestParam String eMAIL,@RequestParam String dESCRIPTION,@RequestParam MultipartFile pROOF) throws IOException{
		model.addAttribute("EMAIL", eMAIL);
		model.addAttribute("DESCRIPTION", dESCRIPTION);
		model.addAttribute("PROOF", pROOF);
	    Report report = new Report(null,eMAIL,null,dESCRIPTION);
		if (!pROOF.isEmpty()) {
			report.setPROOF(BlobProxy.generateProxy(pROOF.getInputStream(), pROOF.getSize()));			
		}
		reportRepository.save(report);
		return "formularioReporte"; }
}