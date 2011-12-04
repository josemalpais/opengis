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
import code.google.com.opengis.gestionDAO.UsuariosDAO;

/**
 * @author Juan Carlos Garc�a Clase que valida los datos de los usuarios que
 *         acabamos de insertar desde nuestra clase visual. Adem�s, realiza las
 *         llamadas a los m�todos del paquete DAO.
 * 
 *         �ltima modificaci�n 14/11/11
 * 
 */

public class Usuarios {

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
	private Boolean valido;
	private String email;
	private UsuariosDAO enlace;

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

		this.Dni = Dni;
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.Telefono = Telefono;
		this.Direccion = Direccion;
		this.Poblacion = Poblacion;
		this.Fecha_nac = fecha_nac;
		this.Contrase�a = contrase�a;
		this.Cp = Cp;
		this.tipo = tipo;
		this.Provincia = Provincia;
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
		String aux = "";
		String[] arrayLetra = { "T", "R", "W", "A", "G", "M", "Y", "F", "P",
				"D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C",
				"K", "E", "T" };
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
		String aux = "";
		String aux2 = "";
		String[] arrayLetra = { "T", "R", "W", "A", "G", "M", "Y", "F", "P",
				"D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C",
				"K", "E", "T" };

		for (int x = 0; x < 8; x++) {// este for nos cojera los 8 primeros
										// car�cteres y los guardar� en el
										// string aux
			aux = aux + dni.charAt(x);
		}
		try {
			pletra = Integer.parseInt(aux); // si no fueran enteros saldriamos
											// del metodo con un false
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null,
					"LOS 8 PRIMEROS DIGITOS HAN DE SER ENTEROS");
			valido = false;
			return false;
		}
		pletra = pletra % 23;
		aux2 = dni.charAt(dni.length() - 1) + "";

		if (arrayLetra[pletra].equalsIgnoreCase(aux2)) {
			Dni = aux + arrayLetra[pletra];// lo colocamos en formato de 9
											// car�cteres
			return true;
		} else {
			JOptionPane
					.showMessageDialog(null,
							"El n�mero de DNI no se corresponde con la letra introducida.");
			valido = false;
			return false;
		}
	}

	public void validarDatos() {

		if (this.Dni.length() != 9) { // El DNI tiene que tener 9 caracteres

			JOptionPane.showMessageDialog(null,
					"Error. El DNI solo puede contener 9 caracteres");

			this.valido = false;

		} else {
			if (validarDni(Dni) == false) {
				this.valido = false;
			} else {
				Boolean r = isInteger(this.Nombre);

				if (r.equals(true) || this.Nombre.length() < 2) {

					JOptionPane
							.showMessageDialog(null,
									"Error. El nombre no puede ser numerico ni estar vac�o");
					this.valido = false;

				} else {

					r = isInteger(this.Apellidos);

					if (r.equals(true) || this.Apellidos.length() < 2) {

						JOptionPane
								.showMessageDialog(null,
										"Error. Los apellidos no pueden ser numericos ni estar vac�os");
						this.valido = false;

					} else {

						r = isInteger(this.Telefono);

						if (this.Telefono.length() != 9 || r.equals(false)) {

							JOptionPane
									.showMessageDialog(null,
											"Error. El n�mero de telefono tiene que tener 9 d�gitos");
							this.valido = false;

						} else {

							r = isInteger(this.Direccion);

							if (r.equals(true) || this.Direccion.length() < 2) {

								JOptionPane
										.showMessageDialog(null,
												"Error. La direcci�n no puede ser num�rica ni estar vac�a");
								this.valido = false;

							} else {

								r = isInteger(this.Poblacion);

								if (r.equals(true)
										|| this.Poblacion.length() < 2) {

									JOptionPane
											.showMessageDialog(null,
													"Error. La poblaci�n no puede ser numerica ni estar vacia");
									this.valido = false;

								} else {

									Date fechaAhora = new Date();
									
									if(this.Fecha_nac.equals("")){
										
										JOptionPane.showMessageDialog(null,"La fecha de nacimiento no puede estar en blanco");
										this.valido = false;
										
									}else{
									
									@SuppressWarnings("deprecation")
									Date fechaNac = new Date(this.Fecha_nac);
									

									if (this.Fecha_nac.length() != 10
											|| fechaNac.getTime() > fechaAhora
													.getTime()) {

										JOptionPane
												.showMessageDialog(null,
														"Error. La fecha indicada no es correcta");
										this.valido = false;

									} else {

										r = isInteger(this.email);

										if (this.email.length() == 0
												|| r.equals(true)) {

											JOptionPane
													.showMessageDialog(null,
															"Error. El campo email no puede estar en blanco ni ser num�rico");

										} else {

											r = isInteger(this.Cp);

											if (this.Cp.length() != 5
													|| r.equals(false)) {

												JOptionPane
														.showMessageDialog(
																null,
																"Error. El C�digo Postal debe tener 5 cifras y ser solo num�rico");

											} else {

												r = isInteger(this.Provincia);

												if (this.Provincia.length() == 0
														|| r.equals(true)) {

													JOptionPane
															.showMessageDialog(
																	null,
																	"Error. La provincia no puede esta vac�a ni ser num�rico");

	 											} else {

													r = isInteger(this.Contrase�a);

													if (this.Contrase�a
															.length() == 0
															|| r.equals(true)) {

														JOptionPane
																.showMessageDialog(
																		null,
																		"Error. La Contrase�a es obligatoria y debe ser alfanum�rica");

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
		ConectarDBA.comprobarExiste("usuario", "dni", this.Dni, true);

		if (existe == true) {

			if (activo == true) {
				JOptionPane.showMessageDialog(null,
						"El DNI ya existe y se encuentra Activado");

			} else {
				JOptionPane.showMessageDialog(null,
						"El DNI ya existe y se encuentra Inactivo");

			}

		} else {
			String sentencia = "INSERT INTO `dai2opengis`.`usuario` (`dni`, `nombre`, `apellidos`, `email`, `password`, `tipo`, `veces`, `tel�fono`, `direcci�n`, `poblaci�n`, `provincia`, `cp`, `fecha_nacimiento`, `activo`) VALUES ('"
					+ this.Dni
					+ "', '"
					+ this.Nombre
					+ "', '"
					+ this.Apellidos
					+ "', '"
					+ this.email
					+ "', '"
					+ this.Contrase�a
					+ "', '"
					+ this.tipo
					+ "', '0', '"
					+ this.Telefono
					+ "', '"
					+ this.Direccion
					+ "', '"
					+ this.Poblacion
					+ "', '"
					+ this.Provincia
					+ "', '"
					+ this.Cp
					+ "', '"
					+ this.Fecha_nac + "', '0')";
			ConectarDBA.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					"Se ha dado de alta el nuevo usuario");

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

		ConectarDBA.comprobarExiste("usuario", "dni", this.Dni, false);

		if (existe == true) {

			String sentencia = "DELETE FROM `usuario` WHERE `dni` = '"
					+ this.Dni + "'";
			ConectarDBA.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					"Usuario eliminado correctamente");

		} else {

			JOptionPane.showMessageDialog(null, "El usuario no existe");
		}

		ConectarDBA.cerrarCon();
	}

	public void modificarUsuario() {
		enlace = new UsuariosDAO(this.Dni, this.Nombre, this.Apellidos,
				this.Telefono, this.Direccion, this.Poblacion, this.Provincia,
				this.Cp, this.Fecha_nac, this.Contrase�a, this.tipo, this.email);
		try {

			enlace.MoficicarUsuario();

		} catch (SQLException e) {

			JOptionPane
					.showMessageDialog(null, "Error al Modificar el usuario");
		}

	}
	
	public void desactivarUsuario(String dni) throws SQLException{
		//System.out.println(dni);
		ConectarDBA.desactivar("usuario", "dni", dni);
	}
	
	public void activarUsuario(String dni) throws SQLException{
		//System.out.println(dni);
		ConectarDBA.activar("usuario", "dni", dni);
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
