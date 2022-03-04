package es.codeurjc.wallypop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	/* LOGIN */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/loginerror")
	public String loginerror(Model model) {
		model.addAttribute("ERROR", true);
		return "login";
	}
	
	/* SING UP */
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
