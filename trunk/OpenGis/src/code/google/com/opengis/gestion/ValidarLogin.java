package code.google.com.opengis.gestion;
import java.sql.*;

import code.google.com.opengis.gestionDAO.ConectarDBA;


public class ValidarLogin {

	private String user;
	private String password;
	boolean validacion;

	private char tipodato;

	
	
	
	

	//url="http://www.db4free.net/phpMyAdmin/index.php?target=server_databases.php&token=11ac3398ec9e98c883a291c82348fa50#PMAURL:db=dai2opengis&server=1&target=db_structure.php&token=11ac3398ec9e98c883a291c82348fa50"
	
	public ValidarLogin(String user,String password) {
		this.user = user;
		this.password = password;
	}

	
	public String getUser() {
		return user;
	}


	public void setUser(String user1) {
		user = user1;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password1) {
		password = password1;
	}

	/*********************
	 * metodo que se conecta a la dba selecciona el usuario y la contraseña que se le a dado a la clase
	 * y elije  el tipo de usuario que es.
	 ********************/
	
	public boolean aceptarUser(){
		try{
		      
		     ConectarDBA dba = new ConectarDBA();
		     dba.acceder();		     
		     
		      String sentencia = "SELECT dni_usuario, password, tipo FROM users WHERE dni_usuario LIKE '"+this.user+"'AND password LIKE '"+this.password+"'";
		      
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

	/*********************
	 * dado el tipo si es true el resultado de validacion elije un tipo
	 * que es y te habre una ventana o otra
	 ********************/
	public void validarUser(boolean validacion){
		
		
		
		if(validacion==true){
			
				
        	    //una vez valide los datos elije el tipo de usuario que es para darle permisos
				switch(tipodato /*sentencia que devuelve el tipo*/){
				case 't': //t de trabajador
					System.out.println(" funciona es un trabajador");
					break;
				case 'd': //d de dueño
					System.out.println(" funciona es dueño");
					break;
				case 'a': // de administrador
					System.out.println(" funciona es  Admin");
				    break;
				default:
					System.out.println("no tiene tipo");
				};}
	  else 
				System.out.println("el user/contraseña no es correcto");
				
			
			 
		}
		}
	
		
	

