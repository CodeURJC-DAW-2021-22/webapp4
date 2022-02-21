package es.codeurjc.wallypop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PostWebController {

	@RequestMapping("/post")
	public String post() {
		return "post";
	}
}
