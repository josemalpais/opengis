package code.google.com.opengis.gestion;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.LoginDao;


public class EnviarMail
{
private String emailUsuario;
private String passUsuario;
private String nombre;
private String apellido;
	public EnviarMail(String user){
		try
	        {
			LoginDao ln = new LoginDao(user);
	           emailUsuario = ln.email();
	           passUsuario = ln.pass();
	           nombre = ln.nombre();
	           apellido=ln.apellido();
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
	            if ( (emailUsuario == null) || (emailUsuario.equals("")) ){
	            	JOptionPane.showMessageDialog(null,"Inserte un DNI Valido,si el DNI es correcto hable con el administrador");
	            }else{
	            
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("opengis@pipepito.es"));
	            message.addRecipient(
	                Message.RecipientType.TO,
	                //MAIL DEL USUARIO 
	                new InternetAddress(emailUsuario));
	            message.setSubject("Recuperación de clave");
	           
	            message.setText( 
	            		"Hola "+nombre+" "+apellido+" tu contraseña es : "+passUsuario
	                );

	            // Envío
	            Transport t = session.getTransport("smtp");
	            t.connect("opengis@pipepito.es", "dai20112012");
	            t.sendMessage(message, message.getAllRecipients());
	            JOptionPane.showMessageDialog(null,"se ha mandado un correo a "+emailUsuario+" compruébelo");

	            t.close();}
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		
	}
	
   
}