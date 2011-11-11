package code.google.com.opengis.gestion;
import java.sql.*;

import code.google.com.opengis.gestionDAO.ConectarDBA;


public class ValidarLogin {

	private String User;
	private String Password;
	boolean validacion;

	private char tipodato;

	
	
	
	

	//url="http://www.db4free.net/phpMyAdmin/index.php?target=server_databases.php&token=11ac3398ec9e98c883a291c82348fa50#PMAURL:db=dai2opengis&server=1&target=db_structure.php&token=11ac3398ec9e98c883a291c82348fa50"
	
	public ValidarLogin(String user, String password) {
		
	}

	
	public String getUser() {
		return User;
	}


	public void setUser(String user) {
		User = user;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}

	
	
	public boolean aceptarUser(String user,String password){
		try{
		      
		     ConectarDBA dba = new ConectarDBA();
		     dba.acceder();		     
		     
		      String sentencia = "SELECT dni_usuario, password, tipo FROM users WHERE dni_usuario LIKE '"+user+"'AND password LIKE '"+password+"'";
		      
		      ResultSet rs = dba.consulta(sentencia);
		      while (rs.next()){
			      String tipo = rs.getString(3);
			      tipodato = tipo.charAt(0);
			      } 
			      rs.close();
			      dba.cerrarCon();
			      validacion = true;
		return validacion;}
		catch(SQLException aoaao){
			validacion = false;
			return validacion;
		     }
	}

	public void validarUser(String user,String password){
		
		
		
		if(validacion==true){
			
				
        	    //una vez valide los datos elije el tipo de usuario que es para darle permisos
				switch(tipodato /*sentencia que devuelve el tipo*/){
				case 't': //t de trabajador
					
					break;
				case 'd': //d de dueño
					
					break;
				case 'a': // de administrador
					
				    break;
				default:
					
				};}
	  else 
				System.out.println("el user no es correcto");
				
			
			 
		}
		}
	
		
	

