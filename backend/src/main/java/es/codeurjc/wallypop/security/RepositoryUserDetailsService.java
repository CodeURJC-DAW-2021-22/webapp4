package es.codeurjc.wallypop.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repositories.UserRepository;


@Service
public class RepositoryUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));


		return new org.springframework.security.core.userdetails.User(user.getFULL_NAME(), 
				user.getPASSWORD(), null);

	}
}
