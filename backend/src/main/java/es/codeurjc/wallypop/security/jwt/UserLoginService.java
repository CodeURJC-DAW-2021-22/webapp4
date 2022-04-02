package es.codeurjc.wallypop.security.jwt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.codeurjc.wallypop.dto.LoginRequest;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private JwtCookieManager cookieUtil;

	@Autowired
	private UserService userService;

	public ResponseEntity<AuthResponse> login(LoginRequest loginRequest, String encryptedAccessToken, String
			encryptedRefreshToken) {
		
		/*Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		 */

		//SecurityContextHolder.getContext().setAuthentication(authentication);
		HttpHeaders responseHeaders = new HttpHeaders();
		Optional<User> us = userService.findByTOKEN(loginRequest.getToken());
		if (us.isPresent()) {


			String accessToken = SecurityCipher.decrypt(encryptedAccessToken);
			String refreshToken = SecurityCipher.decrypt(encryptedRefreshToken);

			String username = us.get().getNAME(); //loginRequest.getEmail();
			UserDetails user = userDetailsService.loadUserByUsername(username);

			Boolean accessTokenValid = jwtTokenProvider.validateToken(accessToken);
			Boolean refreshTokenValid = jwtTokenProvider.validateToken(refreshToken);


			Token newAccessToken;
			Token newRefreshToken;
			if (!accessTokenValid && !refreshTokenValid) {
				newAccessToken = jwtTokenProvider.generateToken(user);
				newRefreshToken = jwtTokenProvider.generateRefreshToken(user);
				addAccessTokenCookie(responseHeaders, newAccessToken);
				addRefreshTokenCookie(responseHeaders, newRefreshToken);
			}

			if (!accessTokenValid && refreshTokenValid) {
				newAccessToken = jwtTokenProvider.generateToken(user);
				addAccessTokenCookie(responseHeaders, newAccessToken);
			}

			if (accessTokenValid && refreshTokenValid) {
				newAccessToken = jwtTokenProvider.generateToken(user);
				newRefreshToken = jwtTokenProvider.generateRefreshToken(user);
				addAccessTokenCookie(responseHeaders, newAccessToken);
				addRefreshTokenCookie(responseHeaders, newRefreshToken);
			}

			AuthResponse loginResponse = new AuthResponse(AuthResponse.Status.SUCCESS,
					"Autenticación correcta.");
			return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
		}
		AuthResponse loginResponse = new AuthResponse(AuthResponse.Status.FAILURE,
				"Token no válido !");
		return ResponseEntity.ok().body(loginResponse);
	}

	public ResponseEntity<AuthResponse> refresh(String encryptedRefreshToken) {
		
		String refreshToken = SecurityCipher.decrypt(encryptedRefreshToken);
		
		Boolean refreshTokenValid = jwtTokenProvider.validateToken(refreshToken);
		
		if (!refreshTokenValid) {
			AuthResponse loginResponse = new AuthResponse(AuthResponse.Status.FAILURE,
					"Sesión no válida !");
			return ResponseEntity.ok().body(loginResponse);
		}

		String username = jwtTokenProvider.getUsername(refreshToken);
		UserDetails user = userDetailsService.loadUserByUsername(username);
				
		Token newAccessToken = jwtTokenProvider.generateToken(user);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil
				.createAccessTokenCookie(newAccessToken.getTokenValue(), newAccessToken.getDuration()).toString());

		AuthResponse loginResponse = new AuthResponse(AuthResponse.Status.SUCCESS,
				"Autenticación correcta.");
		return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
	}

	public String getUserName() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getName();
	}

	public String logout(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				cookie.setMaxAge(0);
				cookie.setValue("");
				cookie.setHttpOnly(true);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}

		return "Sesión finalizada con éxito";
	}

	private void addAccessTokenCookie(HttpHeaders httpHeaders, Token token) {
		httpHeaders.add(HttpHeaders.SET_COOKIE,
				cookieUtil.createAccessTokenCookie(token.getTokenValue(), token.getDuration()).toString());
	}

	private void addRefreshTokenCookie(HttpHeaders httpHeaders, Token token) {
		httpHeaders.add(HttpHeaders.SET_COOKIE,
				cookieUtil.createRefreshTokenCookie(token.getTokenValue(), token.getDuration()).toString());
	}
}
