package es.codeurjc.wallypop.controller;


import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.UserService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewAccountWebController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/newaccount")
    public String postBody(Model model) {
		model.addAttribute("user", new User());
        return "newaccount";
    }
	
	@PostMapping("/newaccount")
	public String newUser(Model model, User us) throws IOException {
		us.setPASSWORD(userService.encodePassword(us.getPASSWORD()));
		userService.save(us);
		return "index";
	}

}
