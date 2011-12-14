package code.google.com.opengis.gestion;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

public class Alquiler {
	private String iddispositivo;
	private String dni_usuario;
	private String fecha_alquiler;
	private String fecha_devolucion;

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

	public Alquiler(String iddispositivo, String dni_usuario,
			String fecha_alquiler) {
		this.iddispositivo = iddispositivo;
		this.dni_usuario = dni_usuario;
		this.fecha_alquiler = fecha_alquiler;
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

	public static boolean comprobarAlquilerAbierto(String iddispositivo,
			String dni_usuario){
		boolean abierto = false;
		
			try {
				if (validarDatos(iddispositivo, dni_usuario)==true){
					ResultSet rs = ConectarDBA.buscar("SELECT * FROM `prestamo` WHERE `iddispositivo` = '"+iddispositivo+"'");
					int nColumnas = rs.getMetaData().getColumnCount();
					
					Object[] registro = new Object[nColumnas];
					while (rs.next()) {

						for (int i = 0; i < nColumnas; i++) {
							registro[i] = rs.getObject(i + 1); // Guardamos los registros de préstamos
							System.out.println(i + " : " + registro[i]);
							}
						if (registro[3].toString().equals("no")){
							abierto = true;
							System.out.println("He encontrado un alquiler abierto");
						}else{
							System.out.println("Este alquiler está cerrado");
						}
						}
				}
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return abierto;
	}
	public static void crearAlquiler(String iddispositivo,
			String dni_usuario){
		if (comprobarAlquilerAbierto(iddispositivo, dni_usuario)==true){
			JOptionPane.showMessageDialog(null, "Ya existe un alquiler abierto para este dispositivo.");
		}else{
			Calendar c = new GregorianCalendar();	//Con estas 5 instrucciones calculo la fecha actual
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = Integer.toString((c.get(Calendar.MONTH))+1);
			String año = Integer.toString(c.get(Calendar.YEAR));
			String fecha = dia+"/"+mes+"/"+año;
			System.out.println("La fecha actual es : "+fecha+".");
			try {
				ConectarDBA.modificar("INSERT INTO `prestamo`(`iddispositivo`, `dni_usuario`, `fecha_alquiler`) VALUES ('1','44859921P','"+fecha+"')");
				JOptionPane.showMessageDialog(null, "Alquiler abierto correctamente.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
	}
	public static void cerrarAlquiler(String iddispositivo,
			String dni_usuario){
		if (comprobarAlquilerAbierto(iddispositivo, dni_usuario)==false){
			JOptionPane.showMessageDialog(null, "No existe un alquiler abierto para este dispositivo.");
		}else{
			try {
				Calendar c = new GregorianCalendar();	//Con estas 5 instrucciones calculo la fecha actual
				String dia = Integer.toString(c.get(Calendar.DATE));
				String mes = Integer.toString((c.get(Calendar.MONTH))+1);
				String año = Integer.toString(c.get(Calendar.YEAR));
				String fecha = dia+"/"+mes+"/"+año;
				System.out.println("La fecha actual es : "+fecha+".");
				ConectarDBA.modificar("UPDATE `prestamo` SET `fecha_devol` = '"+fecha+"' WHERE `iddispositivo` = '"+iddispositivo+"' AND `fecha_devol` = 'no'");
				JOptionPane.showMessageDialog(null, "Alquiler cerrado correctamente.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static boolean validarDatos(String iddispositivo,
			String dni_usuario, String fecha_alquiler) throws HeadlessException, SQLException {
		boolean b = false;

		// Compruebo que el ID de dispositivo sea numérico
		b = isInteger(iddispositivo);

		if (b == false) {
			JOptionPane.showMessageDialog(null,
					"Error. El ID de dispositivo ha de ser numérico.");
			return false;

		} else {
			if(!(ConectarDBA.comprobarExiste("usuario", "dni", dni_usuario, true))){
				JOptionPane.showMessageDialog(null,
						"Error. No existe un usuario activo con el DNI introducido.");
				return false;
			}else{
			
			Date fechaAhora = new Date();

			if (fecha_alquiler.equals("")) {
				JOptionPane.showMessageDialog(null,
						"La fecha de alquiler no puede estar en blanco");
				return false;
			} else {

				@SuppressWarnings("deprecation")
				Date fecha_alq = new Date(fecha_alquiler);

				if (fecha_alquiler.length() != 10
					/*	|| fecha_alq.getTime() > fechaAhora.getTime()*/) {

					JOptionPane.showMessageDialog(null,
							"Error. La fecha indicada no es correcta");
					return false;
				}else {

							return true;
							/**
							 * Si todos los datos son correctos devuelve True.
							 */
						}
					}
				}
			}
		}
	public static boolean validarDatos(String iddispositivo,
			String dni_usuario) throws HeadlessException, SQLException {
		boolean b = false;

		// Compruebo que el ID de dispositivo sea numérico
		b = isInteger(iddispositivo);

		if (b == false) {
			JOptionPane.showMessageDialog(null,
					"Error. El ID de dispositivo ha de ser numérico.");
			return false;

		} else {
			if(!(ConectarDBA.comprobarExiste("usuario", "dni", dni_usuario, true))){
				JOptionPane.showMessageDialog(null,
						"Error. No existe un usuario activo con el DNI introducido.");
				return false;
			}else {

							return true;
							/**
							 * Si todos los datos son correctos devuelve True.
							 */
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
