package es.codeurjc.wallypop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdcommercialWebController {

	 	 
	 @RequestMapping("/adcommercial")
	 public String adcommercial(Model model) {
		model.addAttribute("campos_obligatorios", "");		
		model.addAttribute("campo_obligatorio_city", "");	
		model.addAttribute("campo_obligatorio_title", "");	
		model.addAttribute("campo_obligatorio_description", "");			
		model.addAttribute("campo_obligatorio_price", "");	
		return "adcommercial";
	 }

	public String controlCampoObligatorio_1(Model model, String city, String title, String description, String price) {
		model.addAttribute("campos_obligatorios", "Los campos marcados con * son obligatorios");	
		if (city.length() == 0) {
			model.addAttribute("campo_obligatorio_city", "*");	
			model.addAttribute("campo_obligatorio_title", "");	
			model.addAttribute("campo_obligatorio_description", "");			
			model.addAttribute("campo_obligatorio_price", "");			

		}else if (title.length() == 0) {
			model.addAttribute("campo_obligatorio_city", "");	
			model.addAttribute("campo_obligatorio_title", "*");	
			model.addAttribute("campo_obligatorio_description", "");			
			model.addAttribute("campo_obligatorio_price", "");	
			
		}else if (description.length() == 0) {
			model.addAttribute("campo_obligatorio_city", "");	
			model.addAttribute("campo_obligatorio_title", "");	
			model.addAttribute("campo_obligatorio_description", "*");			
			model.addAttribute("campo_obligatorio_price", "");	
			
		}else if (price.length() == 0) {
			model.addAttribute("campo_obligatorio_city", "");	
			model.addAttribute("campo_obligatorio_title", "");	
			model.addAttribute("campo_obligatorio_description", "");			
			model.addAttribute("campo_obligatorio_price", "*");	
			
		}
		 return "adcommercial";
	}

	public String controlCampoObligatorio(Model model, String cITY, String tITLE, String dESCRIPTION, String pRICE) {
		// TODO Auto-generated method stub
		return null;
	}
	 
}
