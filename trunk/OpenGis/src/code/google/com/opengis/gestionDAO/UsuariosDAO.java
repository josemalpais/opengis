/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionDAO;
import java.sql.*;

import javax.swing.JOptionPane;

/**
* @author Pipepito & Juan Carlos García
* Clase que establece conexión con la base de datos y nos permite insertar, modificar y eliminar usuarios.
* 
* Última modificación 14/11/11
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
	private String Contraseña;
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
 * @param Direccion La dirección del Usuario que vamos a introducir
 * @param Poblacion La población del Usuario que vamos a introducir
 * @param fecha_nac La fecha de Nacimiento del Usuario que vamos a introducir
 */

	public UsuariosDAO(String Dni,String Nombre,String Apellidos,String Telefono,String Direccion,String Poblacion, String Provincia, String Cp, String fecha_nac, String contraseña, String tipo, String email){
		
		this.Dni = Dni;
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.Telefono = Telefono;
		this.Direccion = Direccion;
		this.Poblacion = Poblacion;
		this.Fecha_nac = fecha_nac;
		this.Contraseña = contraseña;
		this.Provincia = Provincia;
		this.Cp = Cp;
		this.tipo = tipo;
		this.email = email;
	
		
	}

	
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
	 * Este método compara un dni que obtiene por parámetro utilizando el método comprobarUsuario(), si existe, no lo dará de alta en la BD.
	 * En el caso de que no exista lo dará de alta como nuevo usuario.
	 * 
	 * Después cierra la conexión que abre el método comprobarUsuario().
	 *
	 * @throws SQLException
	 */

	public void altaUsuario() throws SQLException{
		
		existe = false;
		
		comprobarUsuario(this.Dni);
		
		if (existe == true){ 
		
			JOptionPane.showMessageDialog(null,"El DNI ya existe");
			
		}else{
			String sentencia= "INSERT INTO `dai2opengis`.`usuario` (`dni`, `nombre`, `apellidos`, `email`, `password`, `tipo`, `veces`, `teléfono`, `dirección`, `población`, `provincia`, `cp`, `fecha_nacimiento`, `activo`) VALUES ('"+ this.Dni +"', '"+this.Nombre+"', '"+this.Apellidos+"', '"+this.email+"', '"+this.Contraseña+"', '"+this.tipo+"', '0', '"+this.Telefono+"', '"+this.Direccion+"', '"+this.Poblacion+"', '"+this.Provincia+"', '"+this.Cp+"', '"+this.Fecha_nac+"', '0')";
			dba.modificar(sentencia);

			JOptionPane.showMessageDialog(null,"Se ha dado de alta el nuevo usuario");
			
			
		}
		
		
		dba.cerrarCon();
		
		
	}

	/** 
	 * Este método compara un dni que obtiene por parámetro utilizando el método comprobarUsuario(), si existe, lo borrará de la BD.
	 * Después cierra la conexión que abre el método comprobarUsuario().
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
			
			//String sentencia = "UPDATE INTO `dai2opengis`.`usuario` (`dni` ,`nombre` ,`apellidos` ,`teléfono` ,`dirección` ,`población` ,`fecha_nacimiento`, `activo`) VALUES ('"+ this.Dni +"', '" + this.Nombre  + "','" + this.Apellidos +"','" + this.Telefono +"','" + this.Direccion +"','" + this.Poblacion + "','" + this.Fecha_nac  +"', '0')";
			String sentencia = "UPDATE `dai2opengis`.`usuario` SET `Nombre` = '"+this.Nombre+"', `Apellidos` = '"+this.Apellidos+"', `Teléfono` = '"+this.Telefono+"',`Dirección` = '"+this.Direccion+"',`Población` = '"+this.Poblacion+"',`fecha_nacimiento` = '"+this.Fecha_nac+"',`password` = '"+this.Contraseña+"',`Provincia` = '"+this.Provincia+"',`Cp` = '"+this.Cp+"',`tipo` = '"+this.tipo+"',`email`='"+this.email+"' WHERE `dni` LIKE '"+this.Dni+"'";
			System.out.println("por ahora funciona");
			dba.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Se ha modificado el usuario");
			
			
		}else{
			
			JOptionPane.showMessageDialog(null,"El DNI no existe");
			
		}
		
	}

	
	
	
	
}

