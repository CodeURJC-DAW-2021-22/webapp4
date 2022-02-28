package es.codeurjc.wallypop.controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdcommercialWebController {
	 
	 
	 @RequestMapping("/adcommercial")
	 public String adcommercial(Model model, @RequestParam String cITY,@RequestParam String tITLE, @RequestParam String dESCIPRIPTION, @RequestParam float pRICE ) {
		model.addAttribute("CITY", cITY);
		model.addAttribute("TITLE", tITLE);
		model.addAttribute("DESCRIPTION", dESCIPRIPTION);
		model.addAttribute("PRICE", pRICE);
		return "adcommercial";
	 } 
	 
	 //@RequestMapping("/adcommercial")
	 //public String adcommercial() {
	//	 return "adcommercial";
	 //}
}
