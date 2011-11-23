package code.google.com.opengis.gestion;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EnviarMail
{

	public EnviarMail(){
		try
	        {
	            // Propiedades
	            Properties prop = new Properties();
	            //Host
	            prop.setProperty("mail.smtp.host", "smtp.1and1.es");
	            //Puerto
	            prop.setProperty("mail.smtp.port", "587");
	            //Usuario
	            prop.setProperty("mail.smtp.user", "opengis@pipepito.es");
	            prop.setProperty("mail.smtp.auth", "true");

	            // Cargamos propiedades
	            Session session = Session.getDefaultInstance(prop);

	            // Mensaje
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("opengis@pipepito.es"));
	            message.addRecipient(
	                Message.RecipientType.TO,
	                //MAIL DEL USUARIO 
	                new InternetAddress("eduardo.moret.morales@gmail.com"));
	            message.setSubject("Recuperación de clave");
	            message.setText(
	            	//MENSAJE QUE SE QUIERE ENVIAR
	                "ESTOY JUANKEANDO EL INTERNET!");

	            // Envío
	            Transport t = session.getTransport("smtp");
	            t.connect("opengis@pipepito.es", "dai20112012");
	            t.sendMessage(message, message.getAllRecipients());

	            t.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		
	}
	
    public static void main(String[] args)
    {
       new EnviarMail();
    }
}