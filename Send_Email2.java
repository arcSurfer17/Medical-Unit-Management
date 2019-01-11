package view;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
  
class Send_Email2{  
	

	public static void sendMail(String to, String user, String password, String file_path) {
	  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	   
	  Properties props = System.getProperties();
	  props.setProperty("mail.smtp.host", "smtp.gmail.com");
	  props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	  props.setProperty("mail.smtp.socketFactory.fallback", "false");
	  props.setProperty("mail.smtp.port", "465");
	  props.setProperty("mail.smtp.socketFactory.port", "465");
	  props.put("mail.smtp.auth", "true");
	  props.put("mail.debug", "true");
	  props.put("mail.store.protocol", "pop3");
	  props.put("mail.transport.protocol", "smtp");
	  
	  Session session = Session.getDefaultInstance(props,  
	   new javax.mail.Authenticator() {  
	   protected PasswordAuthentication getPasswordAuthentication() {  
	   return new PasswordAuthentication(user,password);  
	   }  
	  });  
	     
	  //2) compose message     
	  try{  
	    MimeMessage message = new MimeMessage(session);  
	    message.setFrom(new InternetAddress(user));  
	    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	    message.setSubject("Medical Report");  
	      
	    //3) create MimeBodyPart object and set your message text     
	    BodyPart messageBodyPart1 = new MimeBodyPart();  
	    messageBodyPart1.setText("PFA Medical Report");  
	      
	    //4) create new MimeBodyPart object and set DataHandler object to this object      
	    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
	  
	    String filename =  file_path; //"/home/rohan/eclipse-workspace/Medical_Unit/16ucs090.docx" //change accordingly  
	    DataSource source = new FileDataSource(filename);  
	    messageBodyPart2.setDataHandler(new DataHandler(source));  
	    messageBodyPart2.setFileName(filename);  
	     
	     
	    //5) create Multipart object and add MimeBodyPart objects to this object      
	    Multipart multipart = new MimeMultipart();  
	    multipart.addBodyPart(messageBodyPart1);  
	    multipart.addBodyPart(messageBodyPart2);  
	  
	    //6) set the multiplart object to the message object  
	    message.setContent(multipart );  
	     
	    //7) send message  
	    Transport.send(message);  
	   
	   System.out.println("message sent....");  
	   }catch (MessagingException ex) {ex.printStackTrace();}  
 }
} 