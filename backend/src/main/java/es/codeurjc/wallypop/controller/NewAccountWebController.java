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
    public String newAccount(Model model) {
		model.addAttribute("user", new User());
        return "newaccount";
    }
	
	@RequestMapping("/newaccounterror")
    public String newAccountError(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("ERROR", true);
        return "newaccount";
    }
	
	@PostMapping("/newaccount")
	public String newUser(Model model, User us) throws IOException {
		us.setPASSWORD(userService.encodePassword(us.getPASSWORD()));
		if (userService.userExists(us)) {
			return "newaccounterror";
		} else {
			userService.save(us);
			return "login";
		}
	}

}
