package es.codeurjc.wallypop.model;

public class Mail {
	private String to;
	private String resume;
	private String message;
	
	public Mail() {
		
	}
	
	public Mail(String to, String resume, String message) {
		super();
		this.to = to;
		this.resume = resume;
		this.message = message;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
