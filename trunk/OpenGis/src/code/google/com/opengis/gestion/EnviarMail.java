package code.google.com.opengis.gestion;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.Idioma;
import code.google.com.opengis.gestionDAO.LoginDao;


public class EnviarMail
{
private String emailUsuario;
private String passUsuario;
private String nombre;
private String apellido;
	public EnviarMail(String user, String idioma){
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
	            prop.setProperty("mail.smtp.host", "smtp.1and1.es"); //$NON-NLS-1$ //$NON-NLS-2$
	            //Puerto
	            prop.setProperty("mail.smtp.port", "587"); //$NON-NLS-1$ //$NON-NLS-2$
	            //Usuario
	            prop.setProperty("mail.smtp.user", "opengis@pipepito.es"); //$NON-NLS-1$ //$NON-NLS-2$
	            prop.setProperty("mail.smtp.auth", "true"); //$NON-NLS-1$ //$NON-NLS-2$

	            // Cargamos propiedades
	            Session session = Session.getDefaultInstance(prop);

	            // Mensaje
	            if ( (emailUsuario == null) || (emailUsuario.equals("")) ){ //$NON-NLS-1$
	            	JOptionPane.showMessageDialog(null,Idioma.getString("msgMailValidId")); //$NON-NLS-1$
	            }else{
	            
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("opengis@pipepito.es")); //$NON-NLS-1$
	            message.addRecipient(
	                Message.RecipientType.TO,
	                //MAIL DEL USUARIO 
	                new InternetAddress(emailUsuario));
	            
	            message.setSubject(Idioma.getString("msgMailPasswordRecovery")); //$NON-NLS-1$
	           
	            message.setText( 
	            		Idioma.getString("msgMailHello")+nombre+" "+apellido+Idioma.getString("msgMailYourPassword")+passUsuario //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	                );

	            // Envío
	            Transport t = session.getTransport("smtp"); //$NON-NLS-1$
	            t.connect("opengis@pipepito.es", "dai20112012"); //$NON-NLS-1$ //$NON-NLS-2$
	            t.sendMessage(message, message.getAllRecipients());
	            JOptionPane.showMessageDialog(null,Idioma.getString("msgMailSent")+emailUsuario+Idioma.getString("msgMailCheck")); //$NON-NLS-1$ //$NON-NLS-2$

	            t.close();}
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		
	}
	
   
}