package es.codeurjc.wallypop.security;

import java.security.SecureRandom;

import javax.servlet.Servlet;

import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
    ServletRegistrationBean<Servlet> h2servletRegistration(){
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>( new WebdavServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/newaccount").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.authorizeRequests().antMatchers("/coderebootpass").permitAll();
		http.authorizeRequests().antMatchers("/formularioreporte").permitAll();
		http.authorizeRequests().antMatchers("/post").permitAll();
		http.authorizeRequests().antMatchers("/help").permitAll();
		
		/* H2 CONSOLE */
		http.authorizeRequests().antMatchers("/h2_console/**").permitAll();
        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers().frameOptions().sameOrigin();

		// Private pages
		/* USER */
		http.authorizeRequests().antMatchers("/adcommercial").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/favorites").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/perfil").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/yourcommercial").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/yourcommercialsold").hasAnyRole("USER");

		/* ADMIN */
		http.authorizeRequests().antMatchers("/categoriasAdmin").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/categoriasAdminListado").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/grafico").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/perfilAdmin").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/reporteAdmin").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/visualizaReporte").hasAnyRole("ADMIN");

		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/loginerror");

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
	}
}
