package es.codeurjc.wallypop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 

@Controller
public class CommercialWebController {
	@RequestMapping("/commercial")
	public String commercial() {
		return "commercial";
	}

}
