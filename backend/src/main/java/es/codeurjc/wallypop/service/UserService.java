package es.codeurjc.wallypop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void save(User us) {
		userRepository.save(us);
	}
	
	public String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	public boolean userExists(User us) {
		return userRepository.findByNAME(us.getNAME()).isPresent();
	}

	public Optional<User> findByNAME(String name) {
		return userRepository.findByNAME(name);
	}
	
	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}
}
