package code.google.com.opengis.gestion;

import java.util.Date;

import javax.swing.JOptionPane;

public class Alquiler {
	private String iddispositivo;
	private String dni_usuario;
	private String fecha_alquiler;
	private String fecha_devolucion;
	private String periodo;

	/**
	 * 
	 * @return Este método nos devuelve el Identificador de dispositivo
	 */
	public String getIddispositivo() {
		return iddispositivo;
	}

	/**
	 * Con este método cambiamos Identificador del dispositivo del alquiler
	 * 
	 * @param iddispositivo
	 *            Le pasamos el nuevo Identificador
	 */
	public void setIddispositivo(String iddispositivo) {
		this.iddispositivo = iddispositivo;
	}

	/**
	 * 
	 * @return Este método nos devuelve el DNI del usuario que realiza este
	 *         alquiler
	 */
	public String getDni_usuario() {
		return dni_usuario;
	}

	/**
	 * Con este método cambiamos el DNI del usuario que realiza este alquiler
	 * 
	 * @param dni_usuario
	 *            Le pasamos el nuevo DNI
	 */
	public void setDni_usuario(String dni_usuario) {
		this.dni_usuario = dni_usuario;
	}

	/**
	 * Este método nos devuelve la fecha en la que se inicia este alquiler
	 * 
	 * @return
	 */
	public String getFecha_alquiler() {
		return fecha_alquiler;
	}

	/**
	 * Con este método cambiamos la fecha en la que se inicia este alquiler
	 * 
	 * @param fecha_alquiler
	 *            Le pasamos la nueva fecha de alquiler
	 */
	public void setFecha_alquiler(String fecha_alquiler) {
		this.fecha_alquiler = fecha_alquiler;
	}

	/**
	 * Este método nos devuelve la fecha en la que se finaliza este alquiler
	 * 
	 * @return
	 */
	public String getFecha_devolucion() {
		return fecha_devolucion;
	}

	/**
	 * Con este método cambiamos la fecha en la que se finaliza este alquiler
	 * 
	 * @param fecha_devolucion
	 *            Le pasamos la nueva fecha de devolución
	 */
	public void setFecha_devolucion(String fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}

	/**
	 * Este método nos devuelve el período de este alquiler
	 * 
	 * @return
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Con este método cambiamos el período de este alquiler
	 * 
	 * @param periodo
	 *            Le pasamos el nuevo período de alquiler
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Alquiler(String iddispositivo, String dni_usuario,
			String fecha_alquiler, String fecha_devolucion, String periodo) {
		this.iddispositivo = iddispositivo;
		this.dni_usuario = dni_usuario;
		this.fecha_alquiler = fecha_alquiler;
		this.fecha_devolucion = fecha_devolucion;
		this.periodo = periodo;
	}

	public String calcularDNI(String dni) {
		int pletra;
		String aux = "";
		String[] arrayLetra = { "T", "R", "W", "A", "G", "M", "Y", "F", "P",
				"D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C",
				"K", "E", "T" };
		for (int x = 0; x < 8; x++) {// este for nos cojera los primeros
										// carácteres y los guardara en el
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
	 * @return boolean que nos determinará si el dni introducido es verdadero o
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
										// carácteres y los guardará en el
										// string aux
			aux = aux + dni.charAt(x);
		}
		try {
			pletra = Integer.parseInt(aux); // si no fueran enteros saldriamos
											// del metodo con un false
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null,
					"LOS 8 PRIMEROS DIGITOS HAN DE SER ENTEROS");
			return false;
		}
		pletra = pletra % 23;
		aux2 = dni.charAt(dni.length() - 1) + "";

		if (arrayLetra[pletra].equalsIgnoreCase(aux2)) {
			String dni1 = aux + arrayLetra[pletra];// lo colocamos en formato de
													// 9
													// carácteres
			return true;
		} else {
			JOptionPane
					.showMessageDialog(null,
							"EL NUMERO QUE HA INTRODUCIDO NO SE CORRESPONDE CON LA LETRA");
			return false;
		}
	}

	public static boolean validarDatos(String iddispositivo,
			String dni_usuario, String fecha_alquiler, String fecha_devolucion,
			String periodo) {
		boolean b = false;

		// Compruebo que el ID de dispositivo sea numérico
		b = isInteger(iddispositivo);

		if (b == false) {
			JOptionPane.showMessageDialog(null,
					"Error. El ID de dispositivo ha de ser numérico.");
			return false;

		} else {

			Date fechaAhora = new Date();

			if (fecha_alquiler.equals("")) {
				JOptionPane.showMessageDialog(null,
						"La fecha de alquiler no puede estar en blanco");
				return false;
			} else {

				@SuppressWarnings("deprecation")
				Date fecha_alq = new Date(fecha_alquiler);

				if (fecha_alquiler.length() != 10
						|| fecha_alq.getTime() > fechaAhora.getTime()) {

					JOptionPane.showMessageDialog(null,
							"Error. La fecha indicada no es correcta");
					return false;
				} else {

					if (fecha_devolucion.equals("")) {
						JOptionPane
								.showMessageDialog(null,
										"La fecha de devolución no puede estar en blanco");
						return false;
					} else {

						@SuppressWarnings("deprecation")
						Date fecha_dev = new Date(fecha_devolucion);

						if (fecha_devolucion.length() != 10
								|| fecha_dev.getTime() < fecha_alq.getTime()) {

							JOptionPane
									.showMessageDialog(null,
											"Error. La fecha de devolución no es correcta");
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

	public static boolean isInteger(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}
}
