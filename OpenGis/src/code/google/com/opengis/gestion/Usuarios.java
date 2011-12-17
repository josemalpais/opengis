/*****************************************************************************
 * Copyright (c) 2011 [OpenGisTeam]                                           *
 ******************************************************************************/
package code.google.com.opengis.gestion;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

/**
 * @author Juan Carlos Garc�a Clase que valida los datos de los usuarios que
 *         acabamos de insertar desde nuestra clase visual. Adem�s, realiza las
 *         llamadas a los m�todos del paquete DAO.
 * 
 *         �ltima modificaci�n 14/11/11
 * 
 */

public class Usuarios {

	private String dni;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String direccion;
	private String poblacion;
	private String fechaNac;
	private String password;
	private String provincia;
	private String cp;
	private String tipo;
	private Boolean valido;
	private String email;

	private boolean existe;
	private boolean activo;

	/**
	 * Constructor de la clase Usuarios
	 * 
	 * En el momento que llamamos a la clase Usuario debemos pasarle los
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

	public Usuarios(String Dni, String Nombre, String Apellidos,
			String Telefono, String Direccion, String Poblacion,
			String Provincia, String Cp, String fecha_nac, String contrase�a,
			String tipo, String email) {

		this.dni = Dni;
		this.nombre = Nombre;
		this.apellidos = Apellidos;
		this.telefono = Telefono;
		this.direccion = Direccion;
		this.poblacion = Poblacion;
		this.fechaNac = fecha_nac;
		this.password = contrase�a;
		this.cp = Cp;
		this.tipo = tipo;
		this.provincia = Provincia;
		this.email = email;
		this.valido = false;

	}

	/**
	 * Getter de la clase Usuario.
	 * 
	 * @return Devuelve True si los datos son correctos. Devuelve False si los
	 *         datos son incorrectos.
	 */

	public Boolean getValido() {

		return valido;

	}

	/**
	 * Este m�todo validar� los datos que el usuario introduzca en la clase
	 * visual. En el caso de que alg�n dato no sea correcto mostrar� un mensaje
	 * de error y cargar� valido con False. A la hora de llamar los siguientes
	 * m�todos comprobaremos si este nos ha dado FALSE, en ese caso no podremos
	 * hacer nada hasta que no corrijamos el error.
	 * 
	 */

	public String calcularDNI(String dni) {
		int pletra;
		String aux = ""; //$NON-NLS-1$
		String[] arrayLetra = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
				"D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"K", "E", "T" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		for (int x = 0; x < 8; x++) {// este for nos cojera los primeros
										// car�cteres y los guardara en el
										// string aux
			aux = aux + dni.charAt(x);
		}
		pletra = Integer.parseInt(aux);
		pletra = pletra % 23;
		aux = aux + arrayLetra[pletra];

		return dni;
	}

	/**
	 * Clase que valida el DNI, y lo coloca en formato 00000000L
	 * 
	 * @param dni
	 * @return boolean que nos determinar� si el dni introducido es verdadero o
	 *         falso.
	 */
	public boolean validarDni(String dni) {
		int pletra;
		String aux = ""; //$NON-NLS-1$
		String aux2 = ""; //$NON-NLS-1$
		String[] arrayLetra = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
				"D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"K", "E", "T" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		if (dni.length() != 9) {
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgIdCardErrorManyChars")); //$NON-NLS-1$

			this.valido = false;
			return false;
		} else {
			for (int x = 0; x < 8; x++) {// este for nos cojera los 8 primeros
											// car�cteres y los guardar� en el
											// string aux
				aux = aux + dni.charAt(x);
			}
			try {
				pletra = Integer.parseInt(aux); // si no fueran enteros
												// saldriamos
												// del metodo con un false
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null,
						Idioma.getString("msgIdCardErrorNumbers")); //$NON-NLS-1$
				this.valido = false;
				return false;
			}
			pletra = pletra % 23;
			aux2 = dni.charAt(dni.length() - 1) + ""; //$NON-NLS-1$

			if (arrayLetra[pletra].equalsIgnoreCase(aux2)) {
				dni = aux + arrayLetra[pletra];// lo colocamos en formato de 9
												// car�cteres
				return true;
			} else {
				JOptionPane
						.showMessageDialog(null,
								Idioma.getString("msgIdCardUnmatchWord")); //$NON-NLS-1$
				this.valido = false;
				return false;
			}
		}
	}

	/**
	 * M�todo que comprueba si el texto que le pasamos es num�rico o est� vacio.
	 * 
	 * @param texto
	 *            Texto a comprobar
	 * @return devuelve si el texto es v�lido o no.
	 */
	public boolean validarTexto(String texto, String nombreCampo) {
		Boolean r = isInteger(texto);

		for (int i = 0; i < texto.length(); i++) {
			if (Character.isLetter(texto.charAt(i)) == false && texto.charAt(i) != (' ')) {
				JOptionPane
						.showMessageDialog(
								null,
								Idioma.getString("msgErrorField") //$NON-NLS-1$
										+ nombreCampo
										+ Idioma.getString("msgErrorNotSpecialChar")); //$NON-NLS-1$
				this.valido = false;

				return false;

			}
			if (texto.charAt(i) == ' ' && texto.charAt(i - 1) == ' ') {
				

				JOptionPane
						.showMessageDialog(
								null,
								Idioma.getString("msgErrorField") //$NON-NLS-1$
										+ nombreCampo
										+ Idioma.getString("msgErrorBlankSpace")); //$NON-NLS-1$
				return false;
			}
		}

		if (r.equals(true) || texto.length() < 2) {
			JOptionPane.showMessageDialog(null, Idioma.getString("msgErrorField") //$NON-NLS-1$
					+ nombreCampo + Idioma.getString("msgErrorEmptyNorNumeric")); //$NON-NLS-1$
			this.valido = false;

			return false;

		} else {

			return true;

		}
	}

	public boolean validarTextoEspecial(String texto, String nombreCampo) {
		Boolean r = isInteger(texto);

		for (int i = 0; i < texto.length(); i++) {

			if (texto.charAt(i) == ' ' && texto.charAt(i - 1) == ' ') {
				

				JOptionPane
						.showMessageDialog(
								null,
								Idioma.getString("msgErrorField") //$NON-NLS-1$
										+ nombreCampo
										+ Idioma.getString("msgErrorBlankSpace")); //$NON-NLS-1$
				return false;
			}
		}

		if (r.equals(true) || texto.length() < 2) {
			JOptionPane.showMessageDialog(null, Idioma.getString("msgErrorField") //$NON-NLS-1$
					+ nombreCampo + Idioma.getString("msgErrorEmptyNorNumeric")); //$NON-NLS-1$
			this.valido = false;

			return false;

		} else {

			return true;

		}

	}

	public void validarDatos() {

		if (validarDni(dni) == false) {
			this.valido = false;
		} else {

			Boolean r = isInteger(this.nombre);

			if (validarTexto(this.nombre, Idioma.getString("etFirstName")) == false //$NON-NLS-1$
					|| validarTexto(this.apellidos, Idioma.getString("etLastName")) == false) { //$NON-NLS-1$

				this.valido = false;
			} else {

				if (validarTextoEspecial(this.direccion, Idioma.getString("etAddress")) == false //$NON-NLS-1$
						|| validarTextoEspecial(this.email, Idioma.getString("etMail")) == false) { //$NON-NLS-1$

					this.valido = false;
				} else {

					r = isInteger(this.telefono);

					if (this.telefono.length() != 9 || r.equals(false)) {

						JOptionPane
								.showMessageDialog(null,
										Idioma.getString("msgErrorPhoneNumber")); //$NON-NLS-1$
						this.valido = false;

					} else {

						Date fechaAhora = new Date();

						if (this.fechaNac.equals("")) { //$NON-NLS-1$

							JOptionPane
									.showMessageDialog(null,
											Idioma.getString("msgErrorEmptyBirthDate")); //$NON-NLS-1$
							this.valido = false;

						} else {

							@SuppressWarnings("deprecation")
							Date fechaNac = new Date(this.fechaNac);

							if (this.fechaNac.length() != 10
									|| fechaNac.getTime() > fechaAhora
											.getTime()) {

								JOptionPane
										.showMessageDialog(null,
												Idioma.getString("msgErrorWrongDate")); //$NON-NLS-1$
								this.valido = false;

							} else {

								r = isInteger(this.cp);

								if (this.cp.length() != 5 || r.equals(false)) {

									JOptionPane
											.showMessageDialog(null,
													Idioma.getString("msgErrorPostalCode")); //$NON-NLS-1$

								} else {

									r = isInteger(this.password);

									if (this.password.length() == 0
											|| r.equals(true)) {

										JOptionPane
												.showMessageDialog(null,
														Idioma.getString("msgErrorPasswordType")); //$NON-NLS-1$

									} else {

										this.valido = true; // En
															// el
															// caso
															// de
															// que
															// todos
															// los
															// datos
															// sean
															// correctos
															// devolveremos
															// True

									}
								}

							}

						}

					}

				}

			}
		}

	}

	/**
	 * Este m�todo utiliza la clase UsuariosDAO, creando un objeto del mismo con
	 * los parametros indicados en su constructor. Despu�s, ejecuta el m�todo
	 * crear Usuario, definido en la clase UsuariosDAO.
	 * 
	 * @throws SQLException
	 * 
	 */

	public void crearUsuario() throws SQLException {

		existe = false;
		activo = false;
		ConectarDBA.comprobarExiste("usuario", "dni", this.dni, true); //$NON-NLS-1$ //$NON-NLS-2$

		if (existe == true) {

			if (activo == true) {
				JOptionPane.showMessageDialog(null,
						Idioma.getString("msgIDAlreadyExistsActive")); //$NON-NLS-1$

			} else {
				JOptionPane.showMessageDialog(null,
						Idioma.getString("msgIDAlreadyExistsInactive")); //$NON-NLS-1$

			}

		} else {

			String sentencia = "INSERT INTO `dai2opengis`.`usuario` (`dni`, `nombre`, `apellidos`, `email`, `password`, `tipo`, `veces`, `tel�fono`, `direcci�n`, `poblaci�n`, `provincia`, `cp`, `fecha_nacimiento`, `activo`) VALUES ('" //$NON-NLS-1$
					+ this.dni
					+ "', '" //$NON-NLS-1$
					+ this.nombre
					+ "', '" //$NON-NLS-1$
					+ this.apellidos
					+ "', '" //$NON-NLS-1$
					+ this.email
					+ "', '" //$NON-NLS-1$
					+ this.password
					+ "', '" //$NON-NLS-1$
					+ this.tipo
					+ "', '0', '" //$NON-NLS-1$
					+ this.telefono
					+ "', '" //$NON-NLS-1$
					+ this.direccion
					+ "', '" //$NON-NLS-1$
					+ this.poblacion
					+ "', '" //$NON-NLS-1$
					+ this.provincia
					+ "', '" //$NON-NLS-1$
					+ this.cp
					+ "', '" //$NON-NLS-1$
					+ this.fechaNac + "', '1')"; //$NON-NLS-1$
			//System.out.println(sentencia);
			ConectarDBA.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgNewUserSuccess")); //$NON-NLS-1$

		}

		ConectarDBA.cerrarCon();

	}

	/**
	 * Este m�todo utiliza la clase UsuariosDAO, creando un objeto del mismo con
	 * los parametros indicados en su constructor. Despu�s, ejecuta el m�todo
	 * borrar Usuario, definido en la clase UsuariosDAO.
	 * 
	 * @throws SQLException
	 * 
	 */

	public void borrarUsuario() throws SQLException {

		existe = false;

		ConectarDBA.comprobarExiste("usuario", "dni", this.dni, false); //$NON-NLS-1$ //$NON-NLS-2$

		if (existe == true) {

			String sentencia = "DELETE FROM `usuario` WHERE `dni` = '" //$NON-NLS-1$
					+ this.dni + "'"; //$NON-NLS-1$
			ConectarDBA.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgUserInactiveSucceed")); //$NON-NLS-1$

		} else {

			JOptionPane.showMessageDialog(null, Idioma.getString("msgUserNotExist")); //$NON-NLS-1$
		}

		ConectarDBA.cerrarCon();
	}

	public void modificarUsuario() throws SQLException {

		existe = false;

		ConectarDBA.comprobarExiste("usuario", "dni", this.dni, false); //$NON-NLS-1$ //$NON-NLS-2$
		if (ConectarDBA.getExiste() == true) {

			 String sentencia = "UPDATE `dai2opengis`.`usuario` SET `Nombre` = '" + this.nombre + "', `Apellidos` = '"  + this.apellidos + "', `Tel�fono` = '" + this.telefono + "',`Direcci�n` = '" + this.direccion + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			 "',`Poblaci�n` = '" + this.poblacion + "',`fecha_nacimiento` = '" + this.fechaNac + "',`password` = '" + this.password +  "',`Provincia` = '" + this.provincia + "',`Cp` = '" + this.cp + "',`tipo` = '" + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
			  this.tipo + "',`email`='" + this.email + "' WHERE `dni` LIKE '" + this.dni + "'";  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			
			//System.out.println("por ahora funciona");
			//System.out.println(sentencia);
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgUserChanged")); //$NON-NLS-1$
			//System.out.print("Va bien");
		} else {

			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgIdNotExists")); //$NON-NLS-1$
			//System.out.print("canvio false x true");
		}
		ConectarDBA.cerrarCon();


	}

	public static void desactivarUsuario(String dni) throws SQLException {
		// System.out.println(dni);
		ConectarDBA.desactivar("usuario", "dni", dni); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static void activarUsuario(String dni) throws SQLException {
		// System.out.println(dni);
		ConectarDBA.activar("usuario", "dni", dni); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Este m�todo comprueba si una cadena String introducida es un N�mero. Si
	 * es un n�mero, devuelve true.
	 * 
	 * @param input
	 *            Cadena de texto que queremos comprobar si es un n�mero
	 * @return Devolvemos True si es un n�mero, devolvemos False si no lo �s.
	 * 
	 */

	public boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e2) {
			return false;
		}

	}

}
