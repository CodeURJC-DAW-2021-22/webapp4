package es.codeurjc.wallypop.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.Mail;

@Service
public class EmailService {
	public static void sendEmail(Mail mail) {
		// Article Owner
		String to = mail.getTo();

		// Application email
		String from = "wallypopcorp@gmail.com";

		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, "******");

			}

		});

		// Used to debug SMTP issues
		// session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(mail.getResume());

			// Now set the actual message
			message.setText(mail.getMessage());

			// Send message
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

}
