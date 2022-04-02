package es.codeurjc.wallypop.security;

import es.codeurjc.wallypop.security.jwt.JwtRequestFilter;
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
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/categories/**");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/reports");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/articles");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/articles/{id}");

        // Only for admin
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/admin/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/admin/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/admin/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/admin/**").hasRole("ADMIN");


        // All user registered
        // User
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("USER");
        // Articles
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/articles/me").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/articles/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/articles/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/articles/**").hasRole("USER");
        // Favorites
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/favorites/me").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/articles/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/articles/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/articles/**").hasRole("USER");


        // Login
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/auth/login");
        // Logout only logged users
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/auth/logout").hasRole("USER");
        // Refresh token only logged users
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/auth/refresh").hasRole("USER");

        // Other URLs can be accessed without authentication
        // http.authorizeRequests().anyRequest().permitAll();

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
