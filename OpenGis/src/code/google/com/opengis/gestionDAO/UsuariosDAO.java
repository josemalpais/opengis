/*****************************************************************************
 * Copyright (c) 2011 [OpenGisTeam]                                           *
 ******************************************************************************/
package code.google.com.opengis.gestionDAO;

import java.sql.*;

import javax.swing.JOptionPane;

/**
 * @author Pipepito & Juan Carlos Garc�a Clase que establece conexi�n con la
 *         base de datos y nos permite insertar, modificar y eliminar usuarios.
 * 
 *         �ltima modificaci�n 14/11/11
 * 
 */

public class UsuariosDAO {

	static boolean existe;
	static boolean activo;
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

	/**
	 * Constructor de la clase UsuariosDAO
	 * 
	 * En el momento que llamamos a la clase UsuariosDAO debemos pasarle los
	 * siguientes parametros
	 * 
	 * @param Dni
	 *            El DNI del usuario que vamos a introducir
	 * @param Nombre
	 *            El Nombre del Usuario que vamos a introducir
	 * @param Apellidos
	 *            Los Apellidos del Usuario que vamos a introducir
	 * @param Telefono
	 *            El Telefono del usuario que vamos a introducir
	 * @param Direccion
	 *            La direcci�n del Usuario que vamos a introducir
	 * @param Poblacion
	 *            La poblaci�n del Usuario que vamos a introducir
	 * @param fecha_nac
	 *            La fecha de Nacimiento del Usuario que vamos a introducir
	 */

	public UsuariosDAO(String Dni, String Nombre, String Apellidos,
			String Telefono, String Direccion, String Poblacion,
			String Provincia, String Cp, String fecha_nac, String contrase�a,
			String tipo, String email) {

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
	 * Este m�todo compara el dni que le pasamos por par�metro con todos los dni
	 * de la tabla usuarios, se puede emplear para todos los m�todos de la clase
	 * UsuariosDAO
	 * *****************************************IMPORTANTE***********
	 * ***************************** * El m�todo abre una conexi�n, pero la
	 * debemos cerrar desde * * el resto de m�todos que llamen a este. *
	 * ********
	 * ******************************************************************
	 * *****************
	 * 
	 * @param dni
	 *            par�metro DNI que es el campo a buscar en la tabla usuarios,
	 *            si existe, cambiar� el estado del boolean a true, en caso
	 *            contrario a false.
	 * @throws SQLException
	 */
	public static void comprobarUsuario(String dni) throws SQLException {

		ConectarDBA.acceder();
		String sentencia = "SELECT `dni` FROM `usuario` WHERE `dni` LIKE '" //$NON-NLS-1$
				+ dni + "'"; //$NON-NLS-1$
		ResultSet rs = ConectarDBA.consulta(sentencia);
		resultado = new String();
		activo = false;
		while (rs.next()) {
			System.out.println("Ejecuto el while"); //$NON-NLS-1$

			resultado = rs.getString(1);
			System.out.println(resultado);

		}

		System.out.println("Enviado: " + dni + " esperado: " //$NON-NLS-1$ //$NON-NLS-2$
				+ resultado.toString());
		if (resultado == null) {
			existe = false;
			System.out.println("El estado de existe es: " + existe); //$NON-NLS-1$
		} else if (resultado.equals(dni)) {
			existe = true;

			System.out.println("El estado de existe es: " + existe); //$NON-NLS-1$
		}

		rs.close();

		String sentencia2 = "SELECT `activo` FROM `usuario` WHERE `dni` LIKE '" //$NON-NLS-1$
				+ dni + "'"; //$NON-NLS-1$
		ResultSet rs2 = ConectarDBA.consulta(sentencia2);
		activo = false;
		while (rs2.next()) {
			System.out.println("Ejecuto el segundo While"); //$NON-NLS-1$
			activo = rs2.getBoolean(1);
		}
		if (activo == false) {
			activo = true;
		}
		rs2.close();
		System.out.println("El estado de activo es: " + activo); //$NON-NLS-1$
		// System.out.println("Ejecutada sentencia "+sentencia);

	}

	public static ResultSet buscarUsuario(String criterio)
			throws SQLException {
		ResultSet rs = null;
		ConectarDBA.acceder();
		String sentencia = "SELECT * FROM `usuario` WHERE dni LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%' OR apellidos LIKE '%"+criterio+"%' OR direcci�n LIKE '%"+criterio+"%' OR poblaci�n LIKE '%"+criterio+"%' OR provincia LIKE '%"+criterio+"%'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		try {
			rs = ConectarDBA.consulta(sentencia);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return rs;

		// System.out.println("Ejecutada sentencia "+sentencia);

	}

	/**
	 * Este m�todo compara un dni que obtiene por par�metro utilizando el m�todo
	 * comprobarUsuario(), si existe, no lo dar� de alta en la BD. En el caso de
	 * que no exista lo dar� de alta como nuevo usuario.
	 * 
	 * Despu�s cierra la conexi�n que abre el m�todo comprobarUsuario().
	 * 
	 * @throws SQLException
	 */

	public void altaUsuario() throws SQLException {

		existe = false;

		comprobarUsuario(this.Dni);

		if (existe == true) {

			JOptionPane.showMessageDialog(null, Idioma.getString("msgIDExists")); //$NON-NLS-1$

		} else {
			String sentencia = "INSERT INTO `dai2opengis`.`usuario` (`dni`, `nombre`, `apellidos`, `email`, `password`, `tipo`, `veces`, `tel�fono`, `direcci�n`, `poblaci�n`, `provincia`, `cp`, `fecha_nacimiento`, `activo`) VALUES ('" //$NON-NLS-1$
					+ this.Dni
					+ "', '" //$NON-NLS-1$
					+ this.Nombre
					+ "', '" //$NON-NLS-1$
					+ this.Apellidos
					+ "', '" //$NON-NLS-1$
					+ this.email
					+ "', '" //$NON-NLS-1$
					+ this.Contrase�a
					+ "', '" //$NON-NLS-1$
					+ this.tipo
					+ "', '0', '" //$NON-NLS-1$
					+ this.Telefono
					+ "', '" //$NON-NLS-1$
					+ this.Direccion
					+ "', '" //$NON-NLS-1$
					+ this.Poblacion
					+ "', '" //$NON-NLS-1$
					+ this.Provincia
					+ "', '" //$NON-NLS-1$
					+ this.Cp
					+ "', '" //$NON-NLS-1$
					+ this.Fecha_nac + "', '0')"; //$NON-NLS-1$
			ConectarDBA.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgNewUserSuccess")); //$NON-NLS-1$

		}

		ConectarDBA.cerrarCon();

	}

	/**
	 * Este m�todo compara un dni que obtiene por par�metro utilizando el m�todo
	 * comprobarUsuario(), si existe, lo borrar� de la BD. Despu�s cierra la
	 * conexi�n que abre el m�todo comprobarUsuario().
	 * 
	 * @throws SQLException
	 */

	public void borrarUsuario() throws SQLException {
		comprobarUsuario(this.Dni);
		if (existe == true) {

			String sentencia = "DELETE FROM `usuario` WHERE `dni` = '" //$NON-NLS-1$
					+ this.Dni + "'"; //$NON-NLS-1$
			ConectarDBA.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgUserInactiveSuccedd")); //$NON-NLS-1$

		} else {

			JOptionPane.showMessageDialog(null, Idioma.getString("msgUserNotExist")); //$NON-NLS-1$
		}

		ConectarDBA.cerrarCon();

	}

	public void MoficicarUsuario() throws SQLException {
		comprobarUsuario(this.Dni);
		if (existe == true) {

			// String sentencia =
			// "UPDATE INTO `dai2opengis`.`usuario` (`dni` ,`nombre` ,`apellidos` ,`tel�fono` ,`direcci�n` ,`poblaci�n` ,`fecha_nacimiento`, `activo`) VALUES ('"+
			// this.Dni +"', '" + this.Nombre + "','" + this.Apellidos +"','" +
			// this.Telefono +"','" + this.Direccion +"','" + this.Poblacion +
			// "','" + this.Fecha_nac +"', '0')";
			String sentencia = "UPDATE `dai2opengis`.`usuario` SET `Nombre` = '" //$NON-NLS-1$
					+ this.Nombre
					+ "', `Apellidos` = '" //$NON-NLS-1$
					+ this.Apellidos
					+ "', `Tel�fono` = '" //$NON-NLS-1$
					+ this.Telefono
					+ "',`Direcci�n` = '" //$NON-NLS-1$
					+ this.Direccion
					+ "',`Poblaci�n` = '" //$NON-NLS-1$
					+ this.Poblacion
					+ "',`fecha_nacimiento` = '" //$NON-NLS-1$
					+ this.Fecha_nac
					+ "',`password` = '" //$NON-NLS-1$
					+ this.Contrase�a
					+ "',`Provincia` = '" //$NON-NLS-1$
					+ this.Provincia
					+ "',`Cp` = '" //$NON-NLS-1$
					+ this.Cp
					+ "',`tipo` = '" //$NON-NLS-1$
					+ this.tipo
					+ "',`email`='" //$NON-NLS-1$
					+ this.email + "' WHERE `dni` LIKE '" + this.Dni + "'"; //$NON-NLS-1$ //$NON-NLS-2$
			System.out.println("por ahora funciona"); //$NON-NLS-1$
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null, Idioma.getString("msgUserChanged")); //$NON-NLS-1$

		} else {

			JOptionPane.showMessageDialog(null, Idioma.getString("msgIdNotExists")); //$NON-NLS-1$

		}
		ConectarDBA.cerrarCon();
	}

	public static void DesactivarUsuario(String dni) throws SQLException {
		System.out.println(dni);
		comprobarUsuario(dni);
		if (existe == true && activo == true) {
			String sentencia = "UPDATE `usuario` SET `activo` = '1' WHERE `dni` LIKE '" //$NON-NLS-1$
					+ dni + "'"; //$NON-NLS-1$
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null, Idioma.getString("msgUserDisabled")); //$NON-NLS-1$
		}
	}
	
	public static void ActivarUsuario(String dni) throws SQLException {
		System.out.println(dni);
		comprobarUsuario(dni);
		if (existe == true && activo == true) {
			String sentencia = "UPDATE `usuario` SET `activo` = '0' WHERE `dni` LIKE '" //$NON-NLS-1$
					+ dni + "'"; //$NON-NLS-1$
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null, Idioma.getString("msgUserEnabled")); //$NON-NLS-1$
		}
	}

}
