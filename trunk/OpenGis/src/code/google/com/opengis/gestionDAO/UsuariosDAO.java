/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionDAO;
import java.sql.*;

/**
* @author Pipepito
* Clase que establece conexión con la base de datos y nos permite insertar, modificar y eliminar usuarios.
* 
* Última modificación 12/11/11
* 
*/


public class UsuariosDAO {

static boolean existe;
static String resultado;
static ConectarDBA dba = new ConectarDBA();
	
/**
 * Este método compara el dni que le pasamos por parámetro con todos los dni de la tabla usuarios, se puede emplear para todos los métodos de la clase UsuariosDAO
 * *****************************************IMPORTANTE****************************************
 * *				El método abre una conexión, pero la debemos cerrar desde				 *
 * *							el resto de métodos que llamen a este.						 *
 * *******************************************************************************************
 * @param dni parámetro DNI que es el campo a buscar en la tabla usuarios, si existe, cambiará el estado del boolean a true, en caso contrario a false.
 * @throws SQLException
 */
public static void comprobarUsuario(String dni) throws SQLException{
		
		
		ConectarDBA.acceder();
		String sentencia = "SELECT * FROM `usuario` WHERE `dni` LIKE '"+dni+"'";
		ResultSet rs = dba.consulta(sentencia);
		while(rs.next()){
			System.out.println("Ejecuto el while");
			resultado = rs.getString(1);

		}
			
			System.out.println("Enviado: "+dni+" esperado: "+resultado);
			if(resultado == null){
				existe = false;
				System.out.println("El estado de existe es: "+existe);
			}else if (resultado.equals(dni)){
				existe = true;
				System.out.println("El estado de existe es: "+existe);
			}
		
		rs.close();
		
		//System.out.println("Ejecutada sentencia "+sentencia);
		
	}
	/** 
	 * Este método compara un dni que obtiene por parámetro utilizando el método comprobarUsuario(), si existe, lo borrará de la BD.
	 * Después cierra la conexión que abre el método comprobarUsuario().
	 * @param dni Parámetro DNI del usuario que vamos a borrar.
	 * @throws SQLException
	 */
	public static void borrarUsuario(String dni) throws SQLException{
		comprobarUsuario(dni);
		if (existe == true){ 
			
			
			String sentencia = "DELETE FROM `usuario` WHERE `dni` = '"+dni+"'";
			dba.modificar(sentencia);
			//System.out.println("Ejecutada sentencia:"+sentencia);
		}else if (existe == false){
			System.out.println("El usuario no existe");
		}
		dba.cerrarCon();
		
		
	}
	
	
	
	
}
