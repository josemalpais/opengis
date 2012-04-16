package code.google.com.opengis.gestion;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

/**
 * @author kAStRo! Esta clase nos permite validar los datos de un Apero.
 * 
 */

public class Apero {
	public String idApero;
	public String nomApero;
	public int tamApero;
	public String descApero;
	public int idTarea_Apero;
	public String activ_Apero;
	public String idUser;
	static boolean existe;
	static String resultado;

	// ////////// C O N S T R U C T O R ////////////////

	public Apero(String i, String nombre, int tamaño, String descrip,
			int tarea, String activo, String idUser) {

		this.idApero = i;
		this.nomApero = nombre;
		this.tamApero = tamaño;
		this.descApero = descrip;
		this.idTarea_Apero = tarea;
		this.activ_Apero = activo;
		this.idUser = idUser;

	}

	// ///////// M E T O D O S G E T T E R A N D S E T T E R /////////

	// ID APERO
	public String getIdApero() {
		return idApero;
	}

	public void setIdApero(String idApero) {
		String str = idApero + ""; //$NON-NLS-1$
		if ((str.length() > 1) && (str.length() < 9)) { // comprobamos que
														// idapero tenga entre 1
														// y 8 digitos
			this.idApero = idApero;
		} else {
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementIdBetween1And8")); //$NON-NLS-1$
		}
	}

	// NOMBRE APERO
	public String getNomApero() {
		return nomApero;
	}

	public void setNomApero(String nomApero) {
		Boolean nm = isInteger(nomApero);
		if (nm.equals(true) || nomApero.length() < 2 || nomApero.length() > 20) {
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementNameNumericNorEmpty")); //$NON-NLS-1$
		} else {
			this.nomApero = nomApero;
		}
	}

	// TAMAÑO APERO
	public int getTamApero() {
		return tamApero;
	}

	public void setTamApero(int tamApero) {
		if (this.tamApero <= 0 || this.tamApero > 15) {
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementSizeCharsNorNegative")); //$NON-NLS-1$
		} else {
			this.tamApero = tamApero;
		}
	}

	// DESCRIPCION APERO
	public String getDescApero() {
		return descApero;
	}

	public void setDescApero(String descApero) {
		if (descApero.length() > 30 || descApero.length() <= 0) {
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementWrongDesc")); //$NON-NLS-1$
		} else {
			this.descApero = descApero;
		}
	}

	// TAREA APERO
	public int getIdTarea_Apero() {
		return idTarea_Apero;
	}

	public void setIdTarea_Apero(int idTarea_Apero) {
		if (idTarea_Apero < 1 || idTarea_Apero > 4) {
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementWrongTask")); //$NON-NLS-1$
		} else {
			this.idTarea_Apero = idTarea_Apero;
		}
	}

	// APERO ACTIVO
	public String isActiv_Apero() {
		return activ_Apero;
	}

	public void setActiv_Apero(String activ_Apero) {
		this.activ_Apero = activ_Apero;
	}

	// USUARIO APERO
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		if (idUser.length() != 9) { // El DNI del propietario debe tener el
									// siguiente formato: ########L
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementWrongOwner")); //$NON-NLS-1$
		} else {
			this.idUser = idUser;
		}
	}

	// /////////// M E T O D O D E V A L I D A C I O N //////////////////
	public Boolean validarDatos(String id, String nombre, String tamaño,
			String descrip, String tarea, String activo, String idUser) {

		if (idUser.length() != 9) {
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementWrongOwner")); //$NON-NLS-1$

			return false;
		} else {

			if (ValidacionDatos.validarTexto(this.nomApero, "nombre") == false) {

				return false;
			} else {

				if (nombre.length() > 20) {

					JOptionPane
							.showMessageDialog(
									null,
									Idioma.getString("msgImplementNameNumericNorEmpty")); //$NON-NLS-1$

					return false;

				} else {

					if (this.tamApero <= 0 || this.tamApero > 999) {
						JOptionPane.showMessageDialog(null, Idioma
								.getString("msgImplementSizeCharsNorNegative")); //$NON-NLS-1$
						return false;

					} else {
						if (descrip.length() > 30 || descrip.length() <= 0) {
							JOptionPane.showMessageDialog(null,
									Idioma.getString("msgImplementWrongDesc")); //$NON-NLS-1$
							return false;
						} else {
							int tar = Integer.parseInt(tarea);
							if (tar < 1 || tar > 4) {
								JOptionPane
										.showMessageDialog(
												null,
												Idioma.getString("msgImplementNotTask")); //$NON-NLS-1$
								return false;
							} else {

								boolean usuarioValido = false;

								try {

									ConectarDBA.acceder();

									String sql = "SELECT dni FROM usuario WHERE dni= '"
											+ idUser + "'";

									ResultSet resu = ConectarDBA.buscar(sql);

									resu.next();

									if (resu.getString(1) != null) {

										usuarioValido = true;

									} else {

										usuarioValido = false;

									}

									ConectarDBA.cerrarCon();

								} catch (Exception e2) {

								}

								if (usuarioValido == false) {

									JOptionPane
											.showMessageDialog(
													null,
													Idioma.getString("msgErrorIDUnmatchUser"));

									return false;

								} else {
									System.out.println("Validacion OK!"); //$NON-NLS-1$
									return true;

								}
							}
						}
					}

				}
			}
		}
	}

	// //////////// M E T O D O S A L T E R N A T I V O S /////////////////

	public boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e2) {
			return false;
		}
	}

	public boolean esDecimal(String cad) {
		try {
			Double.parseDouble(cad);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	// ////////////M E T O D O S G E S T I O N D E B B D D /////////////////

	// Este metodo se encarga de comprobar si un apero ya existe en nuestra base
	// de datos
	// comparando la id de dicho apero con los de la base de datos

	public static void comprobarApero(String id) throws SQLException {

		ConectarDBA.acceder();
		String sentencia = "SELECT * FROM `apero` WHERE `idapero` LIKE '" + id + "'"; //$NON-NLS-1$ //$NON-NLS-2$
		ResultSet rs = ConectarDBA.consulta(sentencia);
		while (rs.next()) {
			resultado = rs.getString(1);
		}
		if (resultado == null) {
			existe = false;
		} else if (resultado.equals(id)) {
			existe = true;
		}
		rs.close();
	}

	// Este metodo se encarga de dar de alta un nuevo apero
	// comprobando antes si existe en la base de datos
	public void altaApero() throws SQLException {
		comprobarApero(this.idApero);
		if (existe == true) {
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementAlreadyExist")); //$NON-NLS-1$
		} else {
			String sentencia = "INSERT INTO `dai2opengis`.`apero` (`idapero` ,`nombre` ,`tamanyo` ,`descripcion` ,`idtarea` ,`activo` ,`dni_usuario`) VALUES ('" + this.idApero + "', '" + this.nomApero + "','" + this.tamApero + "','" + this.descApero + "','" + this.idTarea_Apero + "','" + this.activ_Apero + "','" + this.idUser + "')"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgNewImplementAdded")); //$NON-NLS-1$
		}
		ConectarDBA.cerrarCon();
	}

	// Este metodo se encarga de dar de baja un nuevo apero
	// comprobando antes si esta creado en la base de datos
	public void borrarApero() throws SQLException {
		comprobarApero(this.idApero);
		if (existe == true) {
			String sentencia = "DELETE FROM `apero` WHERE `idapero` = '" + this.idApero + "'"; //$NON-NLS-1$ //$NON-NLS-2$
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementDeleted")); //$NON-NLS-1$
		} else {
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementNotExist")); //$NON-NLS-1$
		}
		ConectarDBA.cerrarCon();
	}

	public static ResultSet buscarApero(String campo, String criterio)
			throws SQLException {
		ResultSet rs = null;
		ConectarDBA.acceder();
		String sentencia = "SELECT `idapero` ,`nombre` ,`tamanyo` ,`descripcion` ,`idtarea` ,`dni_usuario`,`activo` FROM `apero` WHERE  `" + campo + "` LIKE '%" + criterio + "%'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		try {
			rs = ConectarDBA.consulta(sentencia);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return rs;

	}

	public static void DesactivarApero(String id) throws SQLException {
		comprobarApero(id);
		if (existe == true) {
			String sentencia = "UPDATE `apero` SET `activo` = '0' WHERE `idapero` LIKE '" + id + "'"; //$NON-NLS-1$ //$NON-NLS-2$
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementDisabled")); //$NON-NLS-1$
		}
	}

	public static void activarApero(String id) throws SQLException {
		comprobarApero(id);
		if (existe == true) {
			String sentencia = "UPDATE `apero` SET `activo` = '1' WHERE `idapero` LIKE '" + id + "'"; //$NON-NLS-1$ //$NON-NLS-2$
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null, "Se ha activado el apero"); //$NON-NLS-1$
		}
	}

	public void MoficicarApero() throws SQLException {
		comprobarApero(this.idApero);
		if (existe == true) {
			// String sentencia =
			// "UPDATE INTO `dai2opengis`.`usuario` (`dni` ,`nombre` ,`apellidos` ,`teléfono` ,`dirección` ,`población` ,`fecha_nacimiento`, `activo`) VALUES ('"+
			// this.Dni +"', '" + this.Nombre + "','" + this.Apellidos +"','" +
			// this.Telefono +"','" + this.Direccion +"','" + this.Poblacion +
			// "','" + this.Fecha_nac +"', '0')";
			String sentencia = "UPDATE `dai2opengis`.`apero` SET `nombre` = '" + this.nomApero + "', `tamanyo` = '" + this.tamApero + "', `descripcion` = '" + this.descApero + "',`idtarea` = '" + this.idTarea_Apero + "',`activo` = '" + this.activ_Apero + "',`dni_usuario` = '" + this.idUser + "' WHERE `idapero` LIKE '" + this.idApero + "'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementModSuccess")); //$NON-NLS-1$

		} else {

			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgImplementIdNotExist")); //$NON-NLS-1$

		}
		ConectarDBA.cerrarCon();
	}

	public static ResultSet buscarApero(String criterio) throws SQLException {
		ResultSet rs = null;
		ConectarDBA.acceder();
		String sentencia = "SELECT `idapero` ,`nombre` ,`tamanyo` ,`descripcion` ,`idtarea` ,`dni_usuario`,`activo` FROM `apero` WHERE idapero LIKE '%" + criterio + "%' OR nombre LIKE '%" + criterio + "%' OR tamanyo LIKE '%" + criterio + "%' OR descripcion LIKE '%" + criterio + "%' OR idtarea LIKE '%" + criterio + "%' OR dni_usuario LIKE '%" + criterio + "%'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		try {
			rs = ConectarDBA.consulta(sentencia);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return rs;

	}

}
