

package code.google.com.opengis.gestion;

import java.sql.*;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.UsuariosDAO;
import code.google.com.opengis.gestionVISUAL.VentanaPrincipal;

public class main {


	private static VentanaPrincipal vp;

	public static void main(String[]args) throws SQLException {
		
		
		
		/*System.out.println("Soy Iván y estoy probando a insertar un nuevo dispositivo en la base de datos.");
		//Dispositivo disp1 = new Dispositivo("20503", "modelillo01", "00001", false, true);
		
		ConectarDBA dba = new ConectarDBA();
		dba.acceder();
		String sentencia = "INSERT INTO `dai2opengis`.`dispositivo` (`iddispositivo` ,`modelo` ,`num_serie` ,`disponible` ,`activo`) VALUES (`20202` ,`modellillo` ,`10001` ,`false` ,`true`)";
		dba.modificar(sentencia);
		sentencia = "SELECT * FROM dispositivo";
		ResultSet rs = dba.consulta(sentencia);
		while (rs.next()){
			System.out.println(rs.getString(1));
		}
		rs.close();
		dba.cerrarCon();
		*/
		
	
		//vp = new VentanaPrincipal();
		//Lo que está comentado es una consulta para probar la clase conexión. Att. Pepe.
		
		/***************************************
		ConectarDBA dba = new ConectarDBA();
		dba.acceder();
		String sentencia = "SELECT * FROM users";
		ResultSet rs = dba.consulta(sentencia);
		while (rs.next()){
			System.out.println(rs.getString(1));
		}
		rs.close();
		dba.cerrarCon();
		/**************************************/
		
		/*************************************
		ConectarDBA dba = new ConectarDBA();
		dba.acceder();
		String sentencia = "INSERT INTO `dai2opengis`.`usuario` (`dni` ,`nombre` ,`apellidos` ,`teléfono` ,`dirección` ,`población` ,`fecha_nacimiento`) VALUES ('53758355S', 'Jose Francisco', 'Lara Fortea', '659677695', 'Cami Reial 40 1', 'Catarroja', '1990-01-25')";
		dba.modificar(sentencia);
		dba.cerrarCon();
		/**************************************/
		//UsuariosDAO.borrarUsuario("53758352S");
		//UsuariosDAO.comprobarUsuario("53858355S");
	}
	

}
