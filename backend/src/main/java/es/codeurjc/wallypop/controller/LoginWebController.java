package es.codeurjc.wallypop.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class LoginWebController {
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/loginerror")
	public String loginerror() {
		return "loginerror";
	}
	
}
