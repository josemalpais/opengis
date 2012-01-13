package code.google.com.opengis.gestion;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.Idioma;

public class ValidacionDatos {

	static String fechaValida;

	public static Boolean getValido() {

		return valido;

	}

	public static String getFecha() {

		return fechaValida;

	}

	private static boolean valido;

	/**
	 * Este método validará los datos que el usuario introduzca en la clase
	 * visual. En el caso de que algún dato no sea correcto mostrará un mensaje
	 * de error y cargará valido con False. A la hora de llamar los siguientes
	 * métodos comprobaremos si este nos ha dado FALSE, en ese caso no podremos
	 * hacer nada hasta que no corrijamos el error.
	 * 
	 */
	public static String calcularDNI(String dni) {
		dni = dni.trim();
		int pletra;
		String aux = ""; //$NON-NLS-1$
		String[] arrayLetra = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
				"D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"K", "E", "T" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
	public static boolean validarDni(String dni) {
		dni = dni.trim();
		int pletra;
		String aux = ""; //$NON-NLS-1$
		String aux2 = ""; //$NON-NLS-1$
		String[] arrayLetra = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
				"D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"K", "E", "T" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		if (dni.length() != 9) {
			JOptionPane.showMessageDialog(null,
					Idioma.getString("msgIdCardErrorManyChars")); //$NON-NLS-1$

			valido = false;
			return false;
		} else {
			for (int x = 0; x < 8; x++) {// este for nos cojera los 8 primeros
											// carácteres y los guardará en el
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
				valido = false;
				return false;
			}
			pletra = pletra % 23;
			aux2 = dni.charAt(dni.length() - 1) + ""; //$NON-NLS-1$

			if (arrayLetra[pletra].equalsIgnoreCase(aux2)) {
				dni = aux + arrayLetra[pletra];// lo colocamos en formato de 9
												// carácteres
				return true;
			} else {
				JOptionPane.showMessageDialog(null,
						Idioma.getString("msgIdCardUnmatchWord")); //$NON-NLS-1$
				valido = false;
				return false;
			}
		}
	}

	/**
	 * Método que comprueba si el texto que le pasamos es numérico o está vacio.
	 * 
	 * @param texto
	 *            Texto a comprobar
	 * @return devuelve si el texto es válido o no.
	 */
	public static boolean validarTexto(String texto, String nombreCampo) {
		boolean r = isInteger(texto);
		texto = texto.trim();
		for (int i = 0; i < texto.length(); i++) {
			if (Character.isLetter(texto.charAt(i)) == false
					&& texto.charAt(i) != (' ')) {
				JOptionPane.showMessageDialog(
						null,
						Idioma.getString("msgErrorField") //$NON-NLS-1$
								+ nombreCampo
								+ Idioma.getString("msgErrorNotSpecialChar")); //$NON-NLS-1$
				valido = false;

				return false;

			}
			if (texto.charAt(i) == ' ' && texto.charAt(i - 1) == ' ') {

				JOptionPane.showMessageDialog(
						null,
						Idioma.getString("msgErrorField") //$NON-NLS-1$
								+ nombreCampo
								+ Idioma.getString("msgErrorBlankSpace")); //$NON-NLS-1$
				return false;
			}
		}

		if (r == true || texto.length() < 2) {
			JOptionPane.showMessageDialog(
					null,
					Idioma.getString("msgErrorField") //$NON-NLS-1$
							+ nombreCampo
							+ Idioma.getString("msgErrorEmptyNorNumeric")); //$NON-NLS-1$
			valido = false;

			return false;

		} else {

			return true;

		}
	}

	public static boolean validarTextoEspecial(String texto, String nombreCampo) {
		boolean r = isInteger(texto);
		texto = texto.trim();
		for (int i = 0; i < texto.length(); i++) {

			if (texto.charAt(i) == ' ' && texto.charAt(i - 1) == ' ') {

				JOptionPane.showMessageDialog(
						null,
						Idioma.getString("msgErrorField") //$NON-NLS-1$
								+ nombreCampo
								+ Idioma.getString("msgErrorBlankSpace")); //$NON-NLS-1$
				return false;

			}
		}

		if (r == true || texto.length() < 2) {
			JOptionPane.showMessageDialog(
					null,
					Idioma.getString("msgErrorField") //$NON-NLS-1$
							+ nombreCampo
							+ Idioma.getString("msgErrorEmptyNorNumeric")); //$NON-NLS-1$
			valido = false;

			return false;

		} else {

			return true;

		}

	}

	public static boolean validarEmail(String email) {

		email = email.trim();
		boolean r = isInteger(email);

		if (r == true || email.length() < 2) {
			JOptionPane.showMessageDialog(
					null,
					Idioma.getString("msgErrorField") //$NON-NLS-1$
							+ Idioma.getString("etMail")
							+ Idioma.getString("msgErrorEmptyNorNumeric")); //$NON-NLS-1$
			valido = false;

			return false;

		} else {
			boolean at = false;
			boolean dot = false;
			for (int i = 0; i < email.length(); i++) {
				if (email.charAt(i) == '@') {
					at = true;

				} else if (email.charAt(i) == '.') {
					dot = true;

				}

			}
			if (at != true || dot != true) {
				JOptionPane.showMessageDialog(
						null,
						Idioma.getString("msgErrorField") //$NON-NLS-1$
								+ Idioma.getString("etMail")
								+ Idioma.getString("msgMustContainEmail")); //$NON-NLS-1$
				return false;
			}
		}
		return true;
	}

	public static boolean validarNumerico(String numero, String nombreCampo,
			int longitud) {
		numero = numero.trim();

		if (numero.length() != longitud) {
			JOptionPane.showMessageDialog(
					null,
					Idioma.getString("msgErrorField") + nombreCampo
							+ Idioma.getString("msgMustContain") + longitud
							+ Idioma.getString("etDigit"));
			return false;
		} else {
			for (int i = 0; i < longitud; i++) {
				if (Character.isDigit(numero.charAt(i)) == false) {
					JOptionPane.showMessageDialog(
							null,
							Idioma.getString("msgErrorField") //$NON-NLS-1$
									+ nombreCampo
									+ Idioma.getString("msgOnlyNumber")); //$NON-NLS-1$
					return false;
				}
			}
			return true;
		}

	}

	public static boolean validarNumerico(String numero, String nombreCampo) {
		numero = numero.trim();

		for (int i = 0; i < numero.length(); i++) {
			if (Character.isDigit(numero.charAt(i)) == false) {
				JOptionPane.showMessageDialog(
						null,
						Idioma.getString("msgErrorField") //$NON-NLS-1$
								+ nombreCampo
								+ Idioma.getString("msgOnlyNumber")); //$NON-NLS-1$
				return false;

			}
			return true;
		}
		return false;

	}

	public static boolean validarFecha(String fecha) {

		for (int i = 0; i < fecha.length(); i++) {
			if (Character.isDigit(fecha.charAt(i)) == false
					&& fecha.charAt(i) != '/') {

				JOptionPane.showMessageDialog(null,
						Idioma.getString("msgErrorWrongDate")); //$NON-NLS-1$

				return false;

			}
		}
		String dia = "";
		String mes = "";
		String año = "";
		int o = 0;
		while (Character.isDigit(fecha.charAt(o)) == true) {
			dia = dia + fecha.charAt(o);
			o++;

		}
		if (dia.length() == 1) {
			dia = "0" + dia;
			
		}
		fecha = dia;
		o = fecha.length();
		while (Character.isDigit(fecha.charAt(o)) == true) {
			mes = mes + fecha.charAt(o);
			o++;
		}
		if (mes.length() == 1) {
			mes = "0" + mes;			
		}
		fecha = fecha + "/" + mes + "/";
		o = fecha.length();
		System.out.println("o es " + o);
		System.out.println("Longitud de fecha " + fecha.length());
		while (Character.isDigit(fecha.charAt(o)) == true) {			
			if (o == fecha.length()) {
				año = año + fecha.charAt(o);
				System.out.println("Estado de o " + o);
				break;
			} else {
				o++;
			}
			
		}
		if (año.length() != 4) {

			for (int a = año.length(); a < 4; a++) {
				año = "0" + año;
			}
		}

		fechaValida = dia + "/" + mes + "/" + año;
		System.out.println(fechaValida);
		return true;
	}

	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e2) {
			return false;
		}

	}

}
