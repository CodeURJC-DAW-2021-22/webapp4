package es.codeurjc.wallypop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ReporteAdminWebController {

	@RequestMapping("/reporteAdmin")
	public String reporteadmin() {
		return "reporteAdmin";
	}
	
	@RequestMapping("/VisualizaReporte")
	public String visualizareporte() {
		return "VisualizaReporte";
	}
}
