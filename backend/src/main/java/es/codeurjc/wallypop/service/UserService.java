package es.codeurjc.wallypop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repository.UserRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String encode(String text) {
		return passwordEncoder.encode(text);
	}

	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findByNAME(String name) {
		return userRepository.findByNAME(name);
	}

	public void save(User us) {
		userRepository.save(us);
	}

	public boolean userExists(User us) {
		return userRepository.findByNAME(us.getNAME()).isPresent();
	}

	public List<User> findAll() { return userRepository.findAll(); }

	public boolean exist(long id) {
		return userRepository.existsById(id);
	}

	public void deleteById(long id) {
		userRepository.deleteById(id);
	}

	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User updatedUser) {
		User us = findById(id).get();
		if (updatedUser.getNAME() == null) {
			updatedUser.setNAME(us.getNAME());
		}
		if (updatedUser.getFULL_NAME() == null) {
			updatedUser.setFULL_NAME(us.getFULL_NAME());
		}
		if (updatedUser.getTEL() == null) {
			updatedUser.setTEL(us.getTEL());
		}
		updatedUser.setIS_ADMIN(us.isIS_ADMIN());
		updatedUser.setPASSWORD(encode(updatedUser.getPASSWORD()));
		updatedUser.setN_SELL(us.getN_SELL());
		updatedUser.setN_SOLD(us.getN_SOLD());
		updatedUser.setARTICLES(us.getARTICLES());
		updatedUser.setFAVORITES(us.getFAVORITES());
		updatedUser.setID_USER(id);
		save(updatedUser);

		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	public String newToken(User user) {
		String token = "Wallypop_" + encode(user.getNAME() + "/" + new java.sql.Date(System.currentTimeMillis()));
		user.setTOKEN(token);
		return token;
	}

	public Optional<User> findByTOKEN(String TOKEN) {
		return userRepository.findByTOKEN(TOKEN);
	}
}
