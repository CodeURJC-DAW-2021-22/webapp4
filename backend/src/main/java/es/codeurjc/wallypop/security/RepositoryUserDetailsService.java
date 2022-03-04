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
import es.codeurjc.wallypop.repository.UserRepository;

@Service
public class RepositoryUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String EMAIL) throws UsernameNotFoundException {
		User user = userRepository.findByNAME(EMAIL)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (user.isIS_ADMIN()) {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return new org.springframework.security.core.userdetails.User(user.getNAME(), 
				user.getPASSWORD(), roles);
	}
	
}
