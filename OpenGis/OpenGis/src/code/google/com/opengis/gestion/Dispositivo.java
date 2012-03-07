package code.google.com.opengis.gestion;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

/**
 * 
 * @author Iván Serrano y Jose Alapont

 */
public class Dispositivo {
	// private String iddispositivo;
	private String modelo;
	private String numSerie;
	static boolean existe;
	static String resultado;

	/**
	 * ***************GETTERS Y SETTERS
	 */
	/**
	 * 
	 * @param iddispositivo
	 */

	/**
	 * 
	 * @return
	 */
	public String getModelo() {
		System.out.println("Dispositivo.modelo = " + this.modelo + "."); //$NON-NLS-1$ //$NON-NLS-2$
		return modelo;
	}

	/**
	 * 
	 * @param modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
		System.out.println("Dispositivo.modelo (nuevo) = " + this.modelo + "."); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * 
	 * @return
	 */
	public String getNumSerie() {
		System.out.println("Dispositivo.numSerie = " + this.numSerie + "."); //$NON-NLS-1$ //$NON-NLS-2$
		return numSerie;
	}

	/**
	 * 
	 * @param numSerie
	 */
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
		System.out.println("Dispositivo.numSerie (nuevo) = " + this.numSerie //$NON-NLS-1$
				+ "."); //$NON-NLS-1$
	}

	/**
	 * ***************CONSTRUCTOR
	 */
	/**
	 * 
	 * @param iddispositivo
	 *            Recibe un id de dispositivo
	 * @param modelo
	 *            Recibe un modelo de dispositivo
	 * @param numSerie
	 *            Recibe el número de serie del dispositivo
	 */

	/**
	 * 
	 * @param modelo
	 * @param numSerie
	 */
	public Dispositivo(String modelo, String numSerie) {
		if (Dispositivo.validarDatos(modelo, numSerie) == true) {

			this.modelo = modelo;
			this.numSerie = numSerie;
		} else {
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgDeviceDataWrong")); //$NON-NLS-1$
		}
	}

	/***************************************************************************
	 ******************************** MÉTODOS*************************************
	 ****************************************************************************/
	/**
	 * Este método comprobará que los datos introducidos mediante la interfaz
	 * sean correctos. En cuyo caso, se podrán subir a la base de datos.
	 * 
	 * @param iddispositivo
	 *            Introducimos la cadena con el id del dispositivo a comprobar
	 * @param modelo
	 *            Introducimos la cadena con el modelo del dispositivo
	 * @param numSerie
	 *            Introducimos la cadena con el número de serie del dispositivo
	 * @return Devuelve el estado, si True está todo correcto, si devuelve False
	 *         se han introducido mal los datos.
	 */

	public static Boolean validarDatos(String modelo, String numSerie) {


		if (ValidacionDatos.validarTextoEspecial(modelo,
				Idioma.getString("etModel")) == false) {

			return false;

		} else {

			if (modelo.length() > 15) {
				/**
				 * Longitud del modelo, que ha de ser de 15 caracteres como
				 * máximo.
				 **/
				JOptionPane.showMessageDialog(null,
						Idioma.getString("msgModelLengthError")); //$NON-NLS-1$
				return false;

			} else {
				if (ValidacionDatos.validarNumerico(numSerie,
						Idioma.getString("etSerialNumber")) == false) {
					return false;
				} else {
					if ((numSerie.length() < 10) || (numSerie.length() > 30)) {

						JOptionPane.showMessageDialog(null,
								Idioma.getString("msgBetween10and30")); //$NON-NLS-1$
						return false;

					} else {

						return true;
						/**
						 * Si todos los datos son correctos devuelve True.
						 */
					}
				}
			}
		}

	}

	/**
	 * Este método comprueba que un String sea un Número. Si lo es, devuelve
	 * "True". Si no, devuelve "False"
	 * 
	 * @param cadena
	 *            Aquí escribimos el String a comprobar.
	 * @return Devuelve el estado.
	 */
	public static boolean isInteger(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	/**
	 * ESTE MÉTODO COMPROBARÁ QUE NO EXISTA EL DISPOSITIVO.
	 */

	public static boolean comprobarDispositivo(String iddispositivo)
			throws SQLException {
		ConectarDBA.acceder();
		String sentencia = "SELECT * FROM `dispositivo` WHERE `iddispositivo` LIKE '" //$NON-NLS-1$
				+ iddispositivo + "'"; //$NON-NLS-1$
		ResultSet rs = ConectarDBA.consulta(sentencia);

		resultado = ""; //$NON-NLS-1$
		while (rs.next()) {
			resultado = rs.getString(1);
			System.out.println(resultado);
		}
		System.out.println("Enviado: " + iddispositivo + " esperado: " //$NON-NLS-1$ //$NON-NLS-2$
				+ resultado);

		if (resultado.equals(iddispositivo)) {
			existe = true;
			System.out.println("El estado de existe es: " + existe); //$NON-NLS-1$
		} else {
			existe = false;
			System.out.println("El estado de existe es: " + existe); //$NON-NLS-1$
		}
		rs.close();
		return existe;
	}

	/**
	 * ESTE MÉTODO DARÁ DE ALTA EL DISPOSITIVO INDICADO, COMPROBANDO QUE NO
	 * EXISTA.
	 */

	public static void altaDispositivo(String iddispositivo, String modelo,
			String numSerie, int disponible, int activo) throws SQLException {
		comprobarDispositivo(iddispositivo);
		if ((Dispositivo.validarDatos(modelo, numSerie)) == false) {
		} else {
			if (existe == true) {

				JOptionPane.showMessageDialog(null,
						Idioma.getString("msgDeviceAlreadyExist")); //$NON-NLS-1$

			} else {
				ConectarDBA.acceder();
				String sentencia = "INSERT INTO `dai2opengis`.`dispositivo` (`iddispositivo` ,`modelo` ,`num_serie` ,`disponible` ,`activo`) VALUES ('" //$NON-NLS-1$
						+ iddispositivo + "', '" //$NON-NLS-1$
						+ modelo + "','" //$NON-NLS-1$
						+ numSerie + "','" + disponible + "','" + activo + "')"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				ConectarDBA.modificar(sentencia);

				JOptionPane.showMessageDialog(null,
						Idioma.getString("msgNewDeviceAdded")); //$NON-NLS-1$

			}

			ConectarDBA.cerrarCon();

		}
	}
	
	/**
	 * ESTE MÉTODO DARÁ DE BAJA EL DISPOSITIVO INDICADO, COMPROBANDO QUE YA
	 * EXISTA, Y MODIFICANDO SU CAMPO "ACTIVO" A FALSO.
	 */

	public static void borrarDispositivo(String id) throws SQLException {
		int confirmado = JOptionPane.showConfirmDialog(null,
				Idioma.getString("msgSetDeviceInactive"),"",JOptionPane.YES_NO_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
		if (JOptionPane.YES_OPTION == confirmado) {
			if (comprobarDispositivo(id) == true) {
				try {
					// compruebo si el dispositivo está activo
					ConectarDBA.acceder();
					ResultSet rs = ConectarDBA
							.consulta("SELECT `activo` FROM dispositivo WHERE `iddispositivo` = '" //$NON-NLS-1$
									+ id + "'"); //$NON-NLS-1$
					String aux;
					rs.next();
					aux = rs.getString(1);

					if ((aux == "0") || (aux == "false")) { //$NON-NLS-1$ //$NON-NLS-2$
						JOptionPane.showMessageDialog(null,
								Idioma.getString("msgDeviceAlreadyInactive")); //$NON-NLS-1$
					} else {
						String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `activo` = '1', `disponible` = '1' WHERE `dispositivo`.`iddispositivo` =" //$NON-NLS-1$
								+ id;
						ConectarDBA.modificar(sentencia);
						JOptionPane
								.showMessageDialog(null,
										Idioma.getString("msgDeviceInactive")); //$NON-NLS-1$
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, Idioma.getString("msgDeviceNotExist")); //$NON-NLS-1$
			}
			;
			ConectarDBA.cerrarCon();
		} else {
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgDeviceInactiveError")); //$NON-NLS-1$
		}
	}
	
	public static void reactivarDispositivo(String id) throws SQLException {

		if (comprobarDispositivo(id) == true) {
			try {
				// compruebo si el dispositivo está activo
				ConectarDBA.acceder();
				ResultSet rs = ConectarDBA
						.consulta("SELECT `activo` FROM dispositivo WHERE `iddispositivo` = '" //$NON-NLS-1$
								+ id + "'"); //$NON-NLS-1$
				int aux;
				rs.next();
				aux = rs.getInt(1);

				if (aux == 0) {
					JOptionPane.showMessageDialog(null,
							Idioma.getString("msgDeviceAlreadyActived")); //$NON-NLS-1$
				} else {
					String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `activo` = '0',`disponible` = '0' WHERE `dispositivo`.`iddispositivo` =" //$NON-NLS-1$
							+ id;
					ConectarDBA.modificar(sentencia);
					JOptionPane
							.showMessageDialog(null,
									Idioma.getString("msgDeviceActived")); //$NON-NLS-1$
					
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		;
		ConectarDBA.cerrarCon();
	}

	public static void modificarDispositivo(String iddispositivo,
			String modelo, String numSerie) throws SQLException {
		if ((Dispositivo.validarDatos(modelo, numSerie)) == false){
		}else{
		comprobarDispositivo(iddispositivo);
		if (existe == true) {

			String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `modelo` = '" //$NON-NLS-1$
					+ modelo
					+ "', `num_serie` = '" //$NON-NLS-1$
					+ numSerie
					+ "' WHERE `dispositivo`.`iddispositivo` =" + iddispositivo; //$NON-NLS-1$
			ConectarDBA.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgDeviceModSuccess")); //$NON-NLS-1$

		} else {

			JOptionPane.showMessageDialog(null, Idioma.getString("msgDeviceNotExist")); //$NON-NLS-1$
		}
		}

		ConectarDBA.cerrarCon();

	}
	
	public static void disponibleNo(String id) throws SQLException {
		if (comprobarDispositivo(id) == true) {
			try {
				// compruebo si el dispositivo está activo
				ConectarDBA.acceder();
				ResultSet rs = ConectarDBA
						.consulta("SELECT `activo` FROM dispositivo WHERE `iddispositivo` = '" //$NON-NLS-1$
								+ id + "'"); //$NON-NLS-1$
				int aux;
				rs.next();
				aux = rs.getInt(1);

				// compruebo que el dispositivo esté disponible
				rs = ConectarDBA
						.consulta("SELECT `disponible` FROM dispositivo WHERE `iddispositivo` = '" //$NON-NLS-1$
								+ id + "'"); //$NON-NLS-1$
				int aux2;
				rs.next();
				aux2 = rs.getInt(1);

				if (aux == 1) {
					if (aux2 == 0) {
						JOptionPane.showMessageDialog(null,
								Idioma.getString("msgDeviceNotAvailable")); //$NON-NLS-1$
					} else {
						String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `disponible` = '0' WHERE `dispositivo`.`iddispositivo` =" //$NON-NLS-1$
								+ id;
						ConectarDBA.modificar(sentencia);
						JOptionPane
								.showMessageDialog(null,
										Idioma.getString("msgDeviceModSuccess2")); //$NON-NLS-1$
					}
				} else {
					JOptionPane.showMessageDialog(null,
							Idioma.getString("msgDeviceNotActive")); //$NON-NLS-1$
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		;
		ConectarDBA.cerrarCon();
	}
	
	public static void disponibleSi(String id) throws SQLException {
		if (comprobarDispositivo(id) == true) {
			try {
				// compruebo si el dispositivo está activo
				ConectarDBA.acceder();
				ResultSet rs = ConectarDBA
						.consulta("SELECT `activo` FROM dispositivo WHERE `iddispositivo` = '" //$NON-NLS-1$
								+ id + "'"); //$NON-NLS-1$
				int aux;
				rs.next();
				aux = rs.getInt(1);

				// compruebo que el dispositivo no esté disponible
				rs = ConectarDBA
						.consulta("SELECT `disponible` FROM dispositivo WHERE `iddispositivo` = '" //$NON-NLS-1$
								+ id + "'"); //$NON-NLS-1$
				int aux2;
				rs.next();
				aux2 = rs.getInt(1);

				if (aux == 1) {
					if (aux2 == 1) {
						JOptionPane.showMessageDialog(null,
								Idioma.getString("msgDeviceAlreadyActive")); //$NON-NLS-1$
					} else {
						String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `disponible` = '1' WHERE `dispositivo`.`iddispositivo` =" //$NON-NLS-1$
								+ id;
						ConectarDBA.modificar(sentencia);
						JOptionPane
								.showMessageDialog(null,
										Idioma.getString("msgDeviceModSuccess3")); //$NON-NLS-1$
					}
				} else {
					JOptionPane.showMessageDialog(null,
							Idioma.getString("msgDeviceNotActive")); //$NON-NLS-1$
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		;
		ConectarDBA.cerrarCon();
	}
	
	public static ResultSet buscarDispositivo(String criterio) {
		ResultSet rs = null;
		ConectarDBA.acceder();
		String sentencia = "SELECT * FROM `dispositivo` WHERE  iddispositivo LIKE '%"+criterio+"%' OR modelo LIKE '%"+criterio+"%' OR num_serie LIKE '%"+criterio+"%' ORDER BY iddispositivo"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		try {
			rs = ConectarDBA.consulta(sentencia);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return rs;
	}
	
	public static int calcularNuevoID() {
		int iddispositivo = 0;
		try {

			ConectarDBA.acceder();
			ResultSet rs = ConectarDBA
					.consulta("SELECT MAX(iddispositivo) FROM dispositivo"); //$NON-NLS-1$
			String aux;

			rs.next();
			aux = rs.getString(1);

			if (aux == "") { //$NON-NLS-1$

				iddispositivo = 0;
			} else {
				iddispositivo = rs.getInt(1);
			}
			iddispositivo = iddispositivo + 1;
			return (iddispositivo);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return iddispositivo;
	}

}
