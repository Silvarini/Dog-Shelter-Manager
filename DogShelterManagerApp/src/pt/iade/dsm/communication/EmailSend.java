package pt.iade.dsm.communication;
/*
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

  
/*
 * 
 * This class will be used to send emails to the guests about their adoption requests.
 * This is still in development.
 * 
 * */
/*
public class EmailSend {

	/*public static void sendEmail(String recipient, String state) {

        final String username = "managershelterdog@gmail.com";
        final String password = "dogsheltermanagerpassword123";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient)
            );
            message.setSubject("Adoption");
            if(state.equals("accepted"))
            message.setText("Congrats your adoption has been accepted!\nPlease contact us to mark a date to levy the dog.");
            if(state.equals("rejected"))
            message.setText("We are sorry to inform you that your adoption application has been rejected");
            
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }*/
  
    /*  public static void SendMail() {
      String host="mail.javatpoint.com";  
      final String user="managershelterdog@gmail.com";//change accordingly  
      final String password="dogsheltermanagerpassword123";//change accordingly  
        
      String to="ivanilsonpittagros@gmail.com";//change accordingly  
      
       //Get the session object  
       Properties props = new Properties();  
       props.put("mail.smtp.host",host);  
       props.put("mail.smtp.auth", "true");  
         
       Session session = Session.getDefaultInstance(props,  
        new javax.mail.Authenticator() {  
          protected PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(user,password);  
          }  
        });  
      
       //Compose the message  
        try {  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(user));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject("javatpoint");  
         message.setText("This is simple program of sending email using JavaMail API");  
           
        //send the message  
         Transport.send(message);  
      
         System.out.println("message sent successfully...");  
       
         } catch (MessagingException e) {e.printStackTrace();}  
     }  
    
}/**/
	

