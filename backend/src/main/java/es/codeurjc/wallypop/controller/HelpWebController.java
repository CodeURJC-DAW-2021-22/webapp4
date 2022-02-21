package es.codeurjc.wallypop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 

@Controller 
public class HelpWebController {
	@RequestMapping("/help")
	public String help() {
		return "help"; } 
}
