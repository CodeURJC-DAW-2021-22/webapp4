package es.codeurjc.wallypop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 

@Controller
public class CoderebootpassWebController {
	@RequestMapping("/coderebootpass")
	public String coderebootpas() {
		return "coderebootpass";
	}

}