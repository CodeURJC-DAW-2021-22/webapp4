package es.codeurjc.wallypop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.repositories.ReportRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ReporteAdminWebController {

	@Autowired
	private ReportRepository reportRepository;
	
	@RequestMapping(value="/reporteAdmin", method=RequestMethod.GET)
	public String reporteadmin(Model model) {
        List <Report> reportList= reportRepository.findAll(Sort.by("ID_REPORT"));
        model.addAttribute("REPORTS", reportList);
		return "reporteAdmin";
	}
	
	@RequestMapping("/VisualizaReporte")
	public String visualizareporte() {
		return "VisualizaReporte";
	}
}
