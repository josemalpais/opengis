package code.google.com.opengis.gestion;
import java.io.FileWriter;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.LoginDao;
import code.google.com.opengis.gestionVISUAL.VentanaPrincipal;


public class ValidarLogin extends JFrame {

	
    String user;
    String password;
    char tipodato;

	
	
	
	

	
	public ValidarLogin(String user,String password) throws SQLException {
		this.user = user;
		this.password = password;
		tipoDeUser();
	}

	
	

	
	
	

	/*********************
	 * recibe un char de la clase LoginDao y dado el tipo selecciona una opcion
	 * @throws SQLException 
	 ********************/
	public void tipoDeUser() throws SQLException{
		
		
		LoginDao lgo = new LoginDao(this.user, this.password);
		tipodato = lgo.datosDba();
		
			
				
        	    //una vez valide los datos elije el tipo de usuario que es para darle permisos
				switch(tipodato /*sentencia que devuelve el tipo*/){
				case 't': //t de trabajador
					System.out.println(" funciona es un trabajador");
					VentanaPrincipal ln1 = new VentanaPrincipal();
					break;
				case 'd': //d de dueño
					System.out.println(" funciona es dueño");
					VentanaPrincipal ln2 = new VentanaPrincipal();
					break;
				case 'a': // de administrador
					System.out.println(" funciona es  Admin");
					VentanaPrincipal ln3 = new VentanaPrincipal();
				    break;
				default:
					
					JOptionPane.showMessageDialog(this,"Introduzca usuario y contraseña correctamente", "Login no válido", JOptionPane.ERROR_MESSAGE);
				};
				
				
			
			 
		}




	public String getUser() {
		return user;
	}




	public void setUser(String user) {
		this.user = user;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public char getTipodato() {
		return tipodato;
	}




	public void setTipodato(char tipodato) {
		this.tipodato = tipodato;
	}
		}
	
		
	

