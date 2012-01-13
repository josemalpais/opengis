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
 * @author Juan Carlos García Clase que valida los datos de los usuarios que
 *         acabamos de insertar desde nuestra clase visual. Además, realiza las
 *         llamadas a los métodos del paquete DAO.
 * 
 *         Última modificación 14/11/11
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
	 *            La dirección del Usuario que vamos a introducir
	 * @param Poblacion
	 *            La población del Usuario que vamos a introducir
	 * @param fecha_nac
	 *            La fecha de Nacimiento del Usuario que vamos a introducir
	 */

	public Usuarios(String Dni, String Nombre, String Apellidos,
			String Telefono, String Direccion, String Poblacion,
			String Provincia, String Cp, String fecha_nac, String contraseña,
			String tipo, String email) {

		this.dni = Dni;
		this.nombre = Nombre;
		this.apellidos = Apellidos;
		this.telefono = Telefono;
		this.direccion = Direccion;
		this.poblacion = Poblacion;
		this.fechaNac = fecha_nac;
		this.password = contraseña;
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

	public void validarDatos() {
		this.valido = true;
		if (ValidacionDatos.validarDni(dni) == false) {
			this.valido = false;
		} else {

			if (ValidacionDatos.validarTexto(this.nombre,
					Idioma.getString("etFirstName")) == false //$NON-NLS-1$
					|| ValidacionDatos.validarTexto(this.apellidos,
							Idioma.getString("etLastName")) == false
					|| ValidacionDatos.validarTextoEspecial(this.direccion,
							Idioma.getString("etAddress")) == false //$NON-NLS-1$
					|| ValidacionDatos.validarEmail(this.email) == false
					|| ValidacionDatos.validarNumerico(this.telefono,
							Idioma.getString("etTelephone"), 9) == false) { //$NON-NLS-1$

				this.valido = false;
			} else {

				if (this.fechaNac.equals("00/00/0000")) {
					JOptionPane.showMessageDialog(null,
							Idioma.getString("msgErrorWrongDate")); //$NON-NLS-1$
					this.valido = false;
				} else {

					Date fechaAhora = new Date();

					if (this.fechaNac.equals("")) { //$NON-NLS-1$

						JOptionPane.showMessageDialog(null,
								Idioma.getString("msgErrorEmptyBirthDate")); //$NON-NLS-1$
						this.valido = false;

					} else {

						for (int i = 0; i < this.fechaNac.length(); i++) {
							if (Character.isDigit(this.fechaNac.charAt(i)) == false
									&& this.fechaNac.charAt(i) != '/') {

								JOptionPane.showMessageDialog(null,
										Idioma.getString("msgErrorWrongDate")); //$NON-NLS-1$

								this.valido = false;
								break;
							}
						}

						if (this.valido) {
							@SuppressWarnings("deprecation")
							Date fechaNac = new Date(this.fechaNac);

							if (this.fechaNac.length() != 10
									|| fechaNac.getTime() > fechaAhora
											.getTime()) {

								JOptionPane.showMessageDialog(null,
										Idioma.getString("msgErrorWrongDate")); //$NON-NLS-1$
								this.valido = false;

							} else {

								Boolean r = isInteger(this.password);

								if (this.password.length() == 0
										|| r.equals(true)) {

									JOptionPane.showMessageDialog(null, Idioma
											.getString("msgErrorPasswordType")); //$NON-NLS-1$
									this.valido = false;

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

	/**
	 * Este método utiliza la clase UsuariosDAO, creando un objeto del mismo con
	 * los parametros indicados en su constructor. Después, ejecuta el método
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

			String sentencia = "INSERT INTO `dai2opengis`.`usuario` (`dni`, `nombre`, `apellidos`, `email`, `password`, `tipo`, `veces`, `teléfono`, `dirección`, `población`, `provincia`, `cp`, `fecha_nacimiento`, `activo`) VALUES ('" //$NON-NLS-1$
					+ this.dni + "', '" //$NON-NLS-1$
					+ this.nombre + "', '" //$NON-NLS-1$
					+ this.apellidos + "', '" //$NON-NLS-1$
					+ this.email + "', '" //$NON-NLS-1$
					+ this.password + "', '" //$NON-NLS-1$
					+ this.tipo + "', '0', '" //$NON-NLS-1$
					+ this.telefono + "', '" //$NON-NLS-1$
					+ this.direccion + "', '" //$NON-NLS-1$
					+ this.poblacion + "', '" //$NON-NLS-1$
					+ this.provincia + "', '" //$NON-NLS-1$
					+ this.cp + "', '" //$NON-NLS-1$
					+ this.fechaNac + "', '1')"; //$NON-NLS-1$
			// System.out.println(sentencia);
			ConectarDBA.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgNewUserSuccess")); //$NON-NLS-1$

		}

		ConectarDBA.cerrarCon();

	}

	/**
	 * Este método utiliza la clase UsuariosDAO, creando un objeto del mismo con
	 * los parametros indicados en su constructor. Después, ejecuta el método
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

			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgUserNotExist")); //$NON-NLS-1$
		}

		ConectarDBA.cerrarCon();
	}

	public void modificarUsuario() throws SQLException {

		existe = false;

		ConectarDBA.comprobarExiste("usuario", "dni", this.dni, false); //$NON-NLS-1$ //$NON-NLS-2$
		if (ConectarDBA.getExiste() == true) {

			String sentencia = "UPDATE `dai2opengis`.`usuario` SET `Nombre` = '" + this.nombre + "', `Apellidos` = '" + this.apellidos + "', `Teléfono` = '" + this.telefono + "',`Dirección` = '" + this.direccion + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					"',`Población` = '"
					+ this.poblacion
					+ "',`fecha_nacimiento` = '" + this.fechaNac + "',`password` = '" + this.password + "',`Provincia` = '" + this.provincia + "',`Cp` = '" + this.cp + "',`tipo` = '" + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
					this.tipo
					+ "',`email`='" + this.email + "' WHERE `dni` LIKE '" + this.dni + "'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgUserChanged")); //$NON-NLS-1$

		} else {

			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgIdNotExists")); //$NON-NLS-1$

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
	 * Este método comprueba si una cadena String introducida es un Número. Si
	 * es un número, devuelve true.
	 * 
	 * @param input
	 *            Cadena de texto que queremos comprobar si es un número
	 * @return Devolvemos True si es un número, devolvemos False si no lo és.
	 * 
	 */

	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e2) {
			return false;
		}

	}

}
