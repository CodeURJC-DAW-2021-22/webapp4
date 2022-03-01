package es.codeurjc.wallypop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdcommercialWebController {
	 	 
	 @RequestMapping("/adcommercial")
	 public String adcommercial() {
		 return "adcommercial";
	 }
	 
	 
	 
}
