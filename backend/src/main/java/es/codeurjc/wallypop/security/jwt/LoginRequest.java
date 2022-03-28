package es.codeurjc.wallypop.security.jwt;

public class LoginRequest {

	//private String email;
	private String token;

	public LoginRequest() {
	}

	public LoginRequest(String token) { //, String email) {
		//this.email = email;
		this.token = token;
	}

	/*public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	 */

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

/*
	@Override
	public String toString() {
		return "LoginRequest [username=" + email + ", password=" + token + "]";
	}

	 */
}
