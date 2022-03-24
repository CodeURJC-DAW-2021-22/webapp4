package es.codeurjc.wallypop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.codeurjc.wallypop.security.jwt.JwtRequestFilter;



@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	//Expose AuthenticationManager as a Bean to be used in other services
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/api/**");

		// All user
		// New account
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users");
		// Categories
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/categories/**");
		// Reports
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/reports");

		// Only for admin
		// Admin Users
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/admin/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/admin/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/admin/**").hasRole("ADMIN");

		// Admin Categories
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/categories/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/categories/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("ADMIN");

		// Admin Reports
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/reports/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/reports/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/reports/**").hasRole("ADMIN");


		// All user registered

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/me/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/me/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/me/**").hasRole("USER");




		// Login
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/auth/login");
		// Logout only logged users
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/auth/logout").hasRole("USER");
		// Refresh token only logged users
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/auth/refresh").hasRole("USER");
		
		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement in REST APIs)
		http.csrf().disable();

		// Disable Http Basic Authentication
		http.httpBasic().disable();
		
		// Disable Form login Authentication
		http.formLogin().disable();

		// Avoid creating session 
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Add JWT Token filter
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
}
