package es.codeurjc.wallypop.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class NewAccountWebController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/newaccount")
    public String postBody(Model model) {
		model.addAttribute("user", new User());
        return "newaccount";
    }
	
	@PostMapping("/register")
	public User singUp(@ModelAttribute User us) {
		//userRepository.save(us);
		System.out.println(us.getFULL_NAME());
		System.out.println(us.getEncodedPASSWORD());
		System.out.println(us.getNAME()); // EMAIL
		System.out.println(us.getFULL_NAME());
		return null;
	}
}
