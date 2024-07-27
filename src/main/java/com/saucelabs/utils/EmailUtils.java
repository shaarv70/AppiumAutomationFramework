package com.saucelabs.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

public final class EmailUtils {
	
	private EmailUtils() {}
	
	 public static void sendReport() {
		 
		 try { 
			 
			     String  path = System.getProperty("user.dir")+"\\target\\extent-test-output\\"+"report.html";
				 
				 // Create the email message 
		         HtmlEmail email = new HtmlEmail();
				
				 email.setHostName("smtp.googlemail.com");// in realtime this will be changed along with the below Smtpport
				  email.setSmtpPort(465);
				  email.setAuthenticator(new DefaultAuthenticator("arvindsharma50480@gmail.com","qeealsivqwqrsnxn"));// here we will be giving our email and pass of gmail
				  email.setSSLOnConnect(true);
				  email.setFrom("arvindsharma50480@gmail.com"); //Sender
				  email.setSubject("Test Results");
				  email.setMsg("Please find Attached Report....");
				  email.addTo("arvindsharma50480@gmail.com"); //Receiver 
				  
				  EmailAttachment attachment = new EmailAttachment();
		            attachment.setPath(path);
		            attachment.setDisposition(EmailAttachment.ATTACHMENT);
		            attachment.setDescription("Test Report");
		            attachment.setName("report.html"); // Ensure the file extension is .html
		            email.attach(attachment);
				//  email.attach(url, "extent report", "please check report..."); 
				  email.send(); // send the email 
				  }
				 
		 
		 catch(Exception e) { e.printStackTrace(); }
		 
	 }
	

}
