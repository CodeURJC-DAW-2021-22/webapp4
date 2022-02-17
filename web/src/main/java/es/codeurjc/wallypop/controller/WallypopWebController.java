package es.codeurjc.wallypop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class WallypopWebController {

	@GetMapping("/")
	public String showIndex(Model model) {
		return "index";
	}
	
}
