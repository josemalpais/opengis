

package code.google.com.opengis.gestion;

import java.sql.*;
import java.sql.SQLException;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionVISUAL.VentanaPrincipal;

public class main {


	private static VentanaPrincipal vp;

	public static void main(String[]args) throws SQLException {
		
		
		System.out.println("Hola a todos! Soy pepe y estoy probando");
		vp = new VentanaPrincipal();
		//Lo que está comentado es una consulta para probar la clase conexión. Att. Pepe.
		
		/*ConectarDBA dba = new ConectarDBA();
		dba.acceder();
		String sentencia = "SELECT * FROM users";
		ResultSet rs = dba.consulta(sentencia);
		while (rs.next()){
			System.out.println(rs.getString(1));
			
		}
		rs.close();
		dba.cerrarCon();*/
	}
	

}
