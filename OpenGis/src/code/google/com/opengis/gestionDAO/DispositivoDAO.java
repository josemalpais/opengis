package code.google.com.opengis.gestionDAO;

import java.sql.*;

import javax.swing.JOptionPane;

/**
 * Esta clase permite insertar, modificar, y dar de baja dispositivos
 * 
 * @author Iván Serrano y Jose Alapont
 * 
 */

public class DispositivoDAO {

	static boolean existe;
	static String resultado;
	static ConectarDBA ConectarDBA = new ConectarDBA();

	private String iddispositivo;
	private String modelo;
	private String numSerie;

	/**
	 * ***************GETTERS Y SETTERS
	 */

	public static String getResultado() {
		return resultado;
	}

	public static void setResultado(String resultado) {
		DispositivoDAO.resultado = resultado;
	}

	public String getIddispositivo() {
		return iddispositivo;
	}

	public void setIddispositivo(String iddispositivo) {
		this.iddispositivo = iddispositivo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	/**
	 * ***************CONSTRUCTOR
	 */
	public DispositivoDAO(String iddispositivo, String modelo, String numSerie) {
		super();
		this.iddispositivo = iddispositivo;
		this.modelo = modelo;
		this.numSerie = numSerie;
	}

	/***************************************************************************
	 ******************************** MÉTODOS*************************************
	 ****************************************************************************/

	/**
	 * ESTE MÉTODO COMPROBARÁ QUE NO EXISTA EL DISPOSITIVO.
	 */

	public static boolean comprobarDispositivo(String iddispositivo)
			throws SQLException {
		ConectarDBA.acceder();
		String sentencia = "SELECT * FROM `dispositivo` WHERE `iddispositivo` LIKE '"
				+ iddispositivo + "'";
		ResultSet rs = ConectarDBA.consulta(sentencia);

		resultado = "";
		while (rs.next()) {
			resultado = rs.getString(1);
			System.out.println(resultado);
		}
		System.out.println("Enviado: " + iddispositivo + " esperado: "
				+ resultado);

		if (resultado.equals(iddispositivo)) {
			existe = true;
			System.out.println("El estado de existe es: " + existe);
		} else {
			existe = false;
			System.out.println("El estado de existe es: " + existe);
		}
		rs.close();
		return existe;
	}

	/**
	 * ESTE MÉTODO DARÁ DE ALTA EL DISPOSITIVO INDICADO, COMPROBANDO QUE NO
	 * EXISTA.
	 */

	public static void altaDispositivo(String iddispositivo, String modelo,
			String numSerie) throws SQLException {
		comprobarDispositivo(iddispositivo);

		if (existe == true) {

			JOptionPane.showMessageDialog(null,
					"El ID de Dispositivo ya existe");

		} else {
			ConectarDBA.acceder();
			String sentencia = "INSERT INTO `dai2opengis`.`dispositivo` (`iddispositivo` ,`modelo` ,`num_serie` ,`disponible` ,`activo`) VALUES ('"
					+ iddispositivo
					+ "', '"
					+ modelo
					+ "','"
					+ numSerie
					+ "','1','1')";
			ConectarDBA.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					"Se ha dado de alta el nuevo dispositivo");

		}

		ConectarDBA.cerrarCon();

	}

	/**
	 * ESTE MÉTODO DARÁ DE BAJA EL DISPOSITIVO INDICADO, COMPROBANDO QUE YA
	 * EXISTA, Y MODIFICANDO SU CAMPO "ACTIVO" A FALSO.
	 */

	public static void borrarDispositivo(String id) throws SQLException {
		int confirmado = JOptionPane.showConfirmDialog(null,
				"¿Dar de baja el dispositivo seleccionado?");
		if (JOptionPane.OK_OPTION == confirmado) {
			if (comprobarDispositivo(id) == true) {
				try {
					// compruebo si el dispositivo está activo
					ConectarDBA.acceder();
					ResultSet rs = ConectarDBA
							.consulta("SELECT `activo` FROM dispositivo WHERE `iddispositivo` = '"
									+ id + "'");
					String aux;
					rs.next();
					aux = rs.getString(1);

					if ((aux == "0") || (aux == "false")) {
						JOptionPane.showMessageDialog(null,
								"El dispositivo ya estaba desactivado.");
					} else {
						String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `activo` = '0', `disponible` = '0' WHERE `dispositivo`.`iddispositivo` ="
								+ id;
						ConectarDBA.modificar(sentencia);
						JOptionPane
								.showMessageDialog(null,
										"Dispositivo modificado correctamente. Se ha desactivado el dispositivo.");
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, "El dispositivo no existe");
			}
			;
			ConectarDBA.cerrarCon();
		} else {
			JOptionPane.showMessageDialog(null,
					"El dispositivo no ha sido dado de baja.");
		}
	}

	public static void reactivarDispositivo(String id) throws SQLException {

		if (comprobarDispositivo(id) == true) {
			try {
				// compruebo si el dispositivo está activo
				ConectarDBA.acceder();
				ResultSet rs = ConectarDBA
						.consulta("SELECT `activo` FROM dispositivo WHERE `iddispositivo` = '"
								+ id + "'");
				int aux;
				rs.next();
				aux = rs.getInt(1);

				if (aux == 1) {
					JOptionPane.showMessageDialog(null,
							"El dispositivo ya estaba activado.");
				} else {
					String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `activo` = '1' WHERE `dispositivo`.`iddispositivo` ="
							+ id;
					ConectarDBA.modificar(sentencia);
					JOptionPane
							.showMessageDialog(null,
									"Dispositivo modificado correctamente. Se ha reactivado el dispositivo.");
					int confirmado = JOptionPane.showConfirmDialog(null,
							"¿Liberar dispositivo?");
					if (JOptionPane.OK_OPTION == confirmado) {
						sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `disponible` = '1' WHERE `dispositivo`.`iddispositivo` ="
								+ id;
						ConectarDBA.modificar(sentencia);
						JOptionPane.showMessageDialog(null,
								"Dispositivo liberado.");
					}
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
		comprobarDispositivo(iddispositivo);
		if (existe == true) {

			String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `modelo` = '"
					+ modelo
					+ "', `num_serie` = '"
					+ numSerie
					+ "' WHERE `dispositivo`.`iddispositivo` =" + iddispositivo;
			ConectarDBA.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					"Dispositivo modificado correctamente");

		} else {

			JOptionPane.showMessageDialog(null, "El dispositivo no existe");
		}

		ConectarDBA.cerrarCon();

	}

	public static void disponibleNo(String id) throws SQLException {
		if (comprobarDispositivo(id) == true) {
			try {
				// compruebo si el dispositivo está activo
				ConectarDBA.acceder();
				ResultSet rs = ConectarDBA
						.consulta("SELECT `activo` FROM dispositivo WHERE `iddispositivo` = '"
								+ id + "'");
				int aux;
				rs.next();
				aux = rs.getInt(1);

				// compruebo que el dispositivo esté disponible
				rs = ConectarDBA
						.consulta("SELECT `disponible` FROM dispositivo WHERE `iddispositivo` = '"
								+ id + "'");
				int aux2;
				rs.next();
				aux2 = rs.getInt(1);

				if (aux == 1) {
					if (aux2 == 0) {
						JOptionPane.showMessageDialog(null,
								"El dispositivo no estaba disponible.");
					} else {
						String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `disponible` = '0' WHERE `dispositivo`.`iddispositivo` ="
								+ id;
						ConectarDBA.modificar(sentencia);
						JOptionPane
								.showMessageDialog(null,
										"Dispositivo modificado correctamente. Ahora ya no está disponible.");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"El dispositivo no está activo");
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
						.consulta("SELECT `activo` FROM dispositivo WHERE `iddispositivo` = '"
								+ id + "'");
				int aux;
				rs.next();
				aux = rs.getInt(1);

				// compruebo que el dispositivo no esté disponible
				rs = ConectarDBA
						.consulta("SELECT `disponible` FROM dispositivo WHERE `iddispositivo` = '"
								+ id + "'");
				int aux2;
				rs.next();
				aux2 = rs.getInt(1);

				if (aux == 1) {
					if (aux2 == 1) {
						JOptionPane.showMessageDialog(null,
								"El dispositivo ya estaba disponible.");
					} else {
						String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `disponible` = '1' WHERE `dispositivo`.`iddispositivo` ="
								+ id;
						ConectarDBA.modificar(sentencia);
						JOptionPane
								.showMessageDialog(null,
										"Dispositivo modificado correctamente. Ahora sí está disponible.");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"El dispositivo no está activo");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		;
		ConectarDBA.cerrarCon();
	}

	public static ResultSet buscarDispositivo(String campo, String criterio) {
		ResultSet rs = null;
		ConectarDBA.acceder();
		String sentencia = "SELECT `iddispositivo`, `modelo`, `num_serie`, `disponible`, `activo` FROM `dispositivo` WHERE  `"
				+ campo + "` LIKE '" + criterio + "%'";
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
					.consulta("SELECT MAX(iddispositivo) FROM dispositivo");
			String aux;

			rs.next();
			aux = rs.getString(1);

			if (aux == "") {

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