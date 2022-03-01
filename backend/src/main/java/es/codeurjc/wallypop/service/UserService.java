package es.codeurjc.wallypop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void save(User us) {
		userRepository.save(us);
	}

}
