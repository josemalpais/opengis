package code.google.com.opengis.gestion;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

/**
 * 
 * @author Iván Serrano y Jose Alapont
 * 
 *         SALUDOS PAREJA, SOY PEPE, FIJAROS CÓMO OS HE COMENTADO EL CÓDIGO, QUE
 *         ES COMO SE HACE, SE ESCRIBE /** SE LE DA AL INTRO Y EL ECLIPSE NOS
 *         ESCRIBE LOS PARÁMETROS QUE HAY QUE COMENTAR, ESO ES IMPORTANTE.
 *         Aparte os he corregido un par de cosas en el constructor.
 */
public class Dispositivo {
	// private String iddispositivo;
	private String modelo;
	private String numSerie;

	/**
	 * ***************GETTERS Y SETTERS
	 */
	/*
	 * public String getIddispositivo() {
	 * System.out.println("Dispositivo.iddispositivo = " + this.iddispositivo +
	 * "."); return iddispositivo; }
	 */
	/**
	 * 
	 * @param iddispositivo
	 */
	/*
	 * public void setIddispositivo(String iddispositivo) { this.iddispositivo =
	 * iddispositivo; System.out.println("Dispositivo.iddispositivo (nuevo) = "
	 * + this.iddispositivo + "."); }
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
	/*
	 * public Dispositivo(String iddispositivo, String modelo, String numSerie){
	 * //super(); boolean b1 = this.validarDatos(iddispositivo, modelo,
	 * numSerie); if (b1 = true) {
	 * 
	 * this.iddispositivo = iddispositivo; this.modelo = modelo; this.numSerie =
	 * numSerie; } else { JOptionPane.showMessageDialog(null,
	 * "Los datos son incorrectos. Compruebe los datos."); } }
	 */
	/**
	 * 
	 * @param modelo
	 * @param numSerie
	 */
	public Dispositivo(String modelo, String numSerie) {
		// super();
		boolean b1 = this.validarDatos(modelo, numSerie);
		if (b1 = true) {

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
		boolean b = false;
		if (modelo.length() > 15) {
			/**
			 * Longitud del modelo, que ha de ser de 15 caracteres como máximo.
			 **/
			JOptionPane
					.showMessageDialog(null,
							Idioma.getString("msgModelLengthError")); //$NON-NLS-1$
			return false;

		} else {

			if ((numSerie.length() < 10) || (numSerie.length() > 30)) {

				JOptionPane
						.showMessageDialog(null,
								Idioma.getString("msgBetween10and30")); //$NON-NLS-1$
				return false;

			} else {
				try {
					numSerie = numSerie.toString();
					b = true;
				} catch (Exception e) {
					b = false;
				}

				if (b = false) {

					JOptionPane
							.showMessageDialog(null,
									Idioma.getString("msgSerialNumberError")); //$NON-NLS-1$
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

}
