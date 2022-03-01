package es.codeurjc.wallypop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class YourCommercialWebController {

	@RequestMapping("/yourcommercial")
	 public String yourcommercial(Model model, @RequestParam String cITY,@RequestParam String tITLE, @RequestParam String dESCRIPTION, @RequestParam float pRICE ) {
		model.addAttribute("CITY", cITY);
		model.addAttribute("TITLE", tITLE);
		model.addAttribute("DESCRIPTION", dESCRIPTION);
		model.addAttribute("PRICE", pRICE);
		return "yourcommercial";
	}
	
	@RequestMapping("/yourcommercialsold")
	public String yourcommercialsold() {
		return "yourcommercialsold";
	}
	
}
