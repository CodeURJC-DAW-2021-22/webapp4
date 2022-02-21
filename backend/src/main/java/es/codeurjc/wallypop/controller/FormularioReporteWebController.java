package es.codeurjc.wallypop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 

@Controller 
public class FormularioReporteWebController {
	@RequestMapping("/formularioReporte")
	public String formularioReporte() {
		return "formularioReporte"; }
}
