package es.codeurjc.wallypop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repository.UserRepository;

@Controller
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("Jesús", "1234", "j@j.es", "666666666", true));
		userRepository.save(new User("David", "1234", "d@d.es", "666666666"));
	}

}
