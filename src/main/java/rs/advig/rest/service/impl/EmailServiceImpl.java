package rs.advig.rest.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import rs.advig.rest.model.Response;
import rs.advig.rest.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public int sendInvoice(Response response) {

		String text = "<div style='text-align: center; font-size: medium; font-family: Courier, monospace; white-space:pre'>"
				+ response.getJournal() + "</div>";
		text += "Ovo je <a href='" + response.getVerificationUrl() + "'>LINK</a> za proveru računa";
		
		 // Recipient's email ID needs to be mentioned.
        String to = response.getRequestId().getOptions().getBuyerEmailAddress();

        // Sender's email ID needs to be mentioned
        String from = "elefakt.rs@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("elefakt.rs@gmail.com", "zdemcckjrpbviopt");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(false);

        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set From: header field of the header.
        try {
			message.setFrom(new InternetAddress(from));
			
			// Set To: header field of the header.
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        
	        // Set Subject: header field
	        message.setSubject("Isporuka računa br: " + response.getInvoiceNumber());

	        // Now set the actual message
	        message.setContent(text, "text/html; charset=utf-8");

	        System.out.println("sending...");
	        // Send message
	        Transport.send(message);
	        System.out.println("Sent message successfully....");
	        
	        return 1;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      

		return 99;
	}

}
