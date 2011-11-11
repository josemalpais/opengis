package code.google.com.opengis.gestionDAO;

import java.sql.SQLException;

public class UsuariosDAO {

	
	
	
	public static void borrarUsuario(String dni) throws SQLException{
		
		ConectarDBA dba = new ConectarDBA();
		dba.acceder();
		String sentencia = "DELETE FROM `dai2opengis`.`usuario` WHERE `usuario`.`dni` = \'"+dni+"\'";
		dba.modificar(sentencia);
		dba.cerrarCon();
		System.out.println("Ejecutada sentencia:"+sentencia);
		
		
	}
	
	
	
	
}
