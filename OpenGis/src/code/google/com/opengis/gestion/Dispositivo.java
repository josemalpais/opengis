package code.google.com.opengis.gestion;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ConectarDBA;

/**
 * 
 * @author Iván Serrano y Jose Alapont
 * 
 */
public class Dispositivo {
	private String iddispositivo;
	private String modelo;
	private String numSerie;

	/**
	 * ***************GETTERS Y SETTERS
	 */
	public String getIddispositivo() {
		System.out.println("Dispositivo.iddispositivo = " + this.iddispositivo
				+ ".");
		return iddispositivo;
	}

	public void setIddispositivo(String iddispositivo) {
		this.iddispositivo = iddispositivo;
		System.out.println("Dispositivo.iddispositivo (nuevo) = "
				+ this.iddispositivo + ".");
	}

	public String getModelo() {
		System.out.println("Dispositivo.modelo = " + this.modelo + ".");
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
		System.out.println("Dispositivo.modelo (nuevo) = " + this.modelo + ".");
	}

	public String getNumSerie() {
		System.out.println("Dispositivo.numSerie = " + this.numSerie + ".");
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
		System.out.println("Dispositivo.numSerie (nuevo) = " + this.numSerie
				+ ".");
	}

	/**
	 * ***************CONSTRUCTOR
	 */
	public Dispositivo(String iddispositivo, String modelo, String numSerie,
			boolean disponible, boolean activo) throws SQLException {
		super();
		boolean b1 = this.validarDatos(iddispositivo, modelo, numSerie);
		if (b1 = true) {

			this.iddispositivo = iddispositivo;
			this.modelo = modelo;
			this.numSerie = numSerie;
		} else {
			JOptionPane.showMessageDialog(null,
					"Los datos son incorrectos. Compruebe los datos.");
		}
	}

	/***************************************************************************
	********************************MÉTODOS*************************************
	****************************************************************************/
	/**
	 * Este método comprobará que los datos introducidos mediante la interfaz
	 * sean correctos. En cuyo caso, se podrán subir a la base de datos.
	 */
	public Boolean validarDatos(String iddispositivo, String modelo,
			String numSerie) {

		if (iddispositivo.length() != 5) {
			/**
			 * Longitud del id de dispositivo, que ha de ser de 5 caracteres.
			 **/

			JOptionPane
					.showMessageDialog(null,
							"Error. El identificador de dispositivo ha de ser de 5 caracteres.");
			return false;

		} else {

			Boolean b = isInteger(iddispositivo);

			if (b = false) {

				JOptionPane
						.showMessageDialog(null,
								"Error. El identificador de dispositivo ha de ser numérico.");
				return false;

			} else {

				if (modelo.length() > 15) {
					/**
					 * Longitud del modelo, que ha de ser de 15 caracteres como
					 * máximo.
					 **/

					JOptionPane
							.showMessageDialog(null,
									"Error. La longitud del modelo no puede superar los 15 caracteres.");
					return false;

				} else {

					if (numSerie.length() != 5) {

						JOptionPane
								.showMessageDialog(null,
										"Error. El número de serie tiene que tener 5 dígitos.");
						return false;

					} else {

						b = isInteger(numSerie);

						if (b = false) {

							JOptionPane
									.showMessageDialog(null,
											"Error. El número de serie ha de ser numérico.");
							return false;

						}

						else {

							return true;
							/**
							 * Si todos los datos son correctos devuelve True.
							 */
						}
					}
				}
			}
		}
	}

	/**
	 * Este método comprueba que un String sea un Número. Si lo es, devuelve
	 * "True". Si no, devueve "False"
	 */
	public boolean isInteger(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

}
