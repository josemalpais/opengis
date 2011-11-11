package code.google.com.opengis.gestionDAO;
import java.sql.*;

public class ConectarDBA {
	private String userdba ;
	private String passworddba;
	private String url;
	
	public ConectarDBA(String user, String password, String direccion) {
		this.userdba= user;
		this.passworddba = password;
		this.url = direccion;
		
	}

	
	public void acceder() throws SQLException{
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			
		    Connection connected = DriverManager.getConnection(url,userdba,passworddba);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	      
	  
	      
	}
}
