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
			String numSerie,int disponible,int activo) throws SQLException {
		comprobarDispositivo(iddispositivo);

		if (existe == true) {

			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgDeviceAlreadyExist")); //$NON-NLS-1$

		} else {
			ConectarDBA.acceder();
			String sentencia = "INSERT INTO `dai2opengis`.`dispositivo` (`iddispositivo` ,`modelo` ,`num_serie` ,`disponible` ,`activo`) VALUES ('" //$NON-NLS-1$
					+ iddispositivo
					+ "', '" //$NON-NLS-1$
					+ modelo
					+ "','" //$NON-NLS-1$
					+ numSerie
					+ "','"+disponible+"','"+activo+"')"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			ConectarDBA.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgNewDeviceAdded")); //$NON-NLS-1$

		}

		ConectarDBA.cerrarCon();

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