package es.codeurjc.wallypop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class NewAccountWebController {
	
	@RequestMapping("/newaccount")
	public String newaccount() {
		return "newaccount";
	}
}
