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
	String variable;

	public ValidarLogin(JFrame ven, String user, String password)
			throws SQLException {
		this.user = user;
		this.password = password;

		tipoDeUser(ven);

	}

	/*********************
	 * recibe un char de la clase LoginDao y dado el tipo selecciona una opcion
	 * 
	 * @throws SQLException
	 ********************/
	public void tipoDeUser(JFrame ven) throws SQLException {

		LoginDao lgo = new LoginDao(this.user, this.password);
		tipodato = datosDba();

		// una vez valide los datos elije el tipo de usuario que es para darle
		// permisos
		switch (tipodato /* sentencia que devuelve el tipo */) {
		case 't': // t de trabajador
			// System.out.println(" funciona es un trabajador");
			VentanaPrincipal ln1 = new VentanaPrincipal(tipodato);
			ven.dispose();

			break;
		case 'd': // d de dueño
			// System.out.println(" funciona es dueño");
			VentanaPrincipal ln2 = new VentanaPrincipal(tipodato);
			ven.dispose();

			break;
		case 'a': // de administrador
			// System.out.println(" funciona es  Admin");
			VentanaPrincipal ln3 = new VentanaPrincipal(tipodato);

			ven.dispose();

			break;
		default:

			JOptionPane.showMessageDialog(this,
					"Introduzca usuario y contraseña correctamente",
					"Login no válido", JOptionPane.ERROR_MESSAGE);
		}
		;

	}


	
	/***
	 * Metodo que introduce el usuario y contraseña a la base de datos para
	 * comparar y devuelve un char con el tipo de usuario que tiene que ser
	 ***/
	public char datosDba() throws SQLException {
		ConectarDBA.acceder();

		String sentencia = "SELECT dni, password, nombre, apellidos, tipo, email FROM usuario WHERE dni LIKE '"
				+ this.user + "'AND password LIKE '" + this.password + "' AND `activo` = '0'";
		ResultSet rs = ConectarDBA.consulta(sentencia);
		
		while (rs.next()) {
			String tipo = rs.getString(3);
			tipodato = tipo.charAt(0);
		}
		rs.close();
		ConectarDBA.cerrarCon();
		return tipodato;
	}

	public String email() throws SQLException {
		
		ConectarDBA.acceder();

		String sentencia = "SELECT usuario.email FROM dai2opengis.usuario WHERE dni LIKE '"
				+ this.user + "'";
		ResultSet rs = ConectarDBA.consulta(sentencia);

		while (rs.next()) {
			variable = rs.getString(1);
		}
		return variable;
	}

	public String pass() throws SQLException {
		
		ConectarDBA.acceder();

		String sentencia = "SELECT usuario.password FROM dai2opengis.usuario WHERE dni LIKE '"
				+ this.user + "'";
		ResultSet rs = ConectarDBA.consulta(sentencia);
		while (rs.next()) {
			variable = rs.getString(1);
		}
		return variable;
	}

	public String nombre() throws SQLException {
		
		ConectarDBA.acceder();

		String sentencia = "SELECT usuario.nombre FROM dai2opengis.usuario WHERE dni LIKE '"
				+ this.user + "'";
		ResultSet rs = ConectarDBA.consulta(sentencia);
		while (rs.next()) {
			variable = rs.getString(1);
		}
		return variable;
	}

	public String apellido() throws SQLException {
		
		ConectarDBA.acceder();

		String sentencia = "SELECT usuario.apellidos FROM dai2opengis.usuario WHERE dni LIKE '"
				+ this.user + "'";
		ResultSet rs = ConectarDBA.consulta(sentencia);
		while (rs.next()) {
			variable = rs.getString(1);
		}
		return variable;
	}
	
	public void ValidarLogin(JFrame ven, String user,String password) throws SQLException {
		this.user = user;
		this.password = password;
		
		tipoDeUser(ven);
		
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
