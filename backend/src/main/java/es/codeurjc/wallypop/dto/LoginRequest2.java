package es.codeurjc.wallypop.dto;

public class LoginRequest2 {

    private String email;
    private String password;

    public LoginRequest2() {
    }

    public LoginRequest2(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest [username=" + email + ", password=" + password + "]";
    }
}