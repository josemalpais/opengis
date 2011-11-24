/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionDAO;
import java.sql.*;

import javax.swing.JOptionPane;

/**
* @author Pipepito & Juan Carlos Garc�a
* Clase que establece conexi�n con la base de datos y nos permite insertar, modificar y eliminar usuarios.
* 
* �ltima modificaci�n 14/11/11
* 
*/


public class UsuariosDAO {

	static boolean existe;
	static String resultado;
	static ConectarDBA dba = new ConectarDBA();
	
	private String Dni;
	private String Nombre;
	private String Apellidos;
	private String Telefono;
	private String Direccion;
	private String Poblacion;
	private String Fecha_nac;
	private String Contrase�a;
	private String Provincia;
	private String Cp;
	private String tipo;
	private String email;

/** Constructor de la clase UsuariosDAO
 * 
 * En el momento que llamamos a la clase UsuariosDAO debemos pasarle los siguientes parametros
 * 
 * @param Dni El DNI del usuario que vamos a introducir
 * @param Nombre El Nombre del Usuario que vamos a introducir
 * @param Apellidos Los Apellidos del Usuario que vamos a introducir
 * @param Telefono El Telefono del usuario que vamos a introducir
 * @param Direccion La direcci�n del Usuario que vamos a introducir
 * @param Poblacion La poblaci�n del Usuario que vamos a introducir
 * @param fecha_nac La fecha de Nacimiento del Usuario que vamos a introducir
 */

	public UsuariosDAO(String Dni,String Nombre,String Apellidos,String Telefono,String Direccion,String Poblacion, String Provincia, String Cp, String fecha_nac, String contrase�a, String tipo, String email){
		
		this.Dni = Dni;
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.Telefono = Telefono;
		this.Direccion = Direccion;
		this.Poblacion = Poblacion;
		this.Fecha_nac = fecha_nac;
		this.Contrase�a = contrase�a;
		this.Provincia = Provincia;
		this.Cp = Cp;
		this.tipo = tipo;
		this.email = email;
	
		
	}

	
/**
 * Este m�todo compara el dni que le pasamos por par�metro con todos los dni de la tabla usuarios, se puede emplear para todos los m�todos de la clase UsuariosDAO
 * *****************************************IMPORTANTE****************************************
 * *				El m�todo abre una conexi�n, pero la debemos cerrar desde				 *
 * *							el resto de m�todos que llamen a este.						 *
 * *******************************************************************************************
 * @param dni par�metro DNI que es el campo a buscar en la tabla usuarios, si existe, cambiar� el estado del boolean a true, en caso contrario a false.
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
	 * Este m�todo compara un dni que obtiene por par�metro utilizando el m�todo comprobarUsuario(), si existe, no lo dar� de alta en la BD.
	 * En el caso de que no exista lo dar� de alta como nuevo usuario.
	 * 
	 * Despu�s cierra la conexi�n que abre el m�todo comprobarUsuario().
	 *
	 * @throws SQLException
	 */

	public void altaUsuario() throws SQLException{
		
		existe = false;
		
		comprobarUsuario(this.Dni);
		
		if (existe == true){ 
		
			JOptionPane.showMessageDialog(null,"El DNI ya existe");
			
		}else{
			String sentencia= "INSERT INTO `dai2opengis`.`usuario` (`dni`, `nombre`, `apellidos`, `email`, `password`, `tipo`, `veces`, `tel�fono`, `direcci�n`, `poblaci�n`, `provincia`, `cp`, `fecha_nacimiento`, `activo`) VALUES ('"+ this.Dni +"', '"+this.Nombre+"', '"+this.Apellidos+"', '"+this.email+"', '"+this.Contrase�a+"', '"+this.tipo+"', '0', '"+this.Telefono+"', '"+this.Direccion+"', '"+this.Poblacion+"', '"+this.Provincia+"', '"+this.Cp+"', '"+this.Fecha_nac+"', '0')";
			dba.modificar(sentencia);

			JOptionPane.showMessageDialog(null,"Se ha dado de alta el nuevo usuario");
			
			
		}
		
		
		dba.cerrarCon();
		
		
	}

	/** 
	 * Este m�todo compara un dni que obtiene por par�metro utilizando el m�todo comprobarUsuario(), si existe, lo borrar� de la BD.
	 * Despu�s cierra la conexi�n que abre el m�todo comprobarUsuario().
	 * @throws SQLException
	 */
	
	public void borrarUsuario() throws SQLException{
		comprobarUsuario(this.Dni);
		if (existe == true){ 
		
			String sentencia = "DELETE FROM `usuario` WHERE `dni` = '"+this.Dni+"'";
			dba.modificar(sentencia);

			JOptionPane.showMessageDialog(null,"Usuario eliminado correctamente");
			
		}else{
			
			JOptionPane.showMessageDialog(null,"El usuario no existe");
		}
		
		dba.cerrarCon();
		
		
	}
	
	public void MoficicarUsuario() throws SQLException{
		comprobarUsuario(this.Dni);
		if (existe == true){
			
			//String sentencia = "UPDATE INTO `dai2opengis`.`usuario` (`dni` ,`nombre` ,`apellidos` ,`tel�fono` ,`direcci�n` ,`poblaci�n` ,`fecha_nacimiento`, `activo`) VALUES ('"+ this.Dni +"', '" + this.Nombre  + "','" + this.Apellidos +"','" + this.Telefono +"','" + this.Direccion +"','" + this.Poblacion + "','" + this.Fecha_nac  +"', '0')";
			String sentencia = "UPDATE `dai2opengis`.`usuario` SET `Nombre` = '"+this.Nombre+"', `Apellidos` = '"+this.Apellidos+"', `Tel�fono` = '"+this.Telefono+"',`Direcci�n` = '"+this.Direccion+"',`Poblaci�n` = '"+this.Poblacion+"',`fecha_nacimiento` = '"+this.Fecha_nac+"',`password` = '"+this.Contrase�a+"',`Provincia` = '"+this.Provincia+"',`Cp` = '"+this.Cp+"',`tipo` = '"+this.tipo+"',`email`='"+this.email+"' WHERE `dni` LIKE '"+this.Dni+"'";
			System.out.println("por ahora funciona");
			dba.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Se ha modificado el usuario");
			
			
		}else{
			
			JOptionPane.showMessageDialog(null,"El DNI no existe");
			
		}
		
	}

	
	
	
	
}

