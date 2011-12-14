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
	 * @return Este m�todo nos devuelve el Identificador de dispositivo
	 */
	public String getIddispositivo() {
		return iddispositivo;
	}

	/**
	 * Con este m�todo cambiamos Identificador del dispositivo del alquiler
	 * 
	 * @param iddispositivo
	 *            Le pasamos el nuevo Identificador
	 */
	public void setIddispositivo(String iddispositivo) {
		this.iddispositivo = iddispositivo;
	}

	/**
	 * 
	 * @return Este m�todo nos devuelve el DNI del usuario que realiza este
	 *         alquiler
	 */
	public String getDni_usuario() {
		return dni_usuario;
	}

	/**
	 * Con este m�todo cambiamos el DNI del usuario que realiza este alquiler
	 * 
	 * @param dni_usuario
	 *            Le pasamos el nuevo DNI
	 */
	public void setDni_usuario(String dni_usuario) {
		this.dni_usuario = dni_usuario;
	}

	/**
	 * Este m�todo nos devuelve la fecha en la que se inicia este alquiler
	 * 
	 * @return
	 */
	public String getFecha_alquiler() {
		return fecha_alquiler;
	}

	/**
	 * Con este m�todo cambiamos la fecha en la que se inicia este alquiler
	 * 
	 * @param fecha_alquiler
	 *            Le pasamos la nueva fecha de alquiler
	 */
	public void setFecha_alquiler(String fecha_alquiler) {
		this.fecha_alquiler = fecha_alquiler;
	}

	/**
	 * Este m�todo nos devuelve la fecha en la que se finaliza este alquiler
	 * 
	 * @return
	 */
	public String getFecha_devolucion() {
		return fecha_devolucion;
	}

	/**
	 * Con este m�todo cambiamos la fecha en la que se finaliza este alquiler
	 * 
	 * @param fecha_devolucion
	 *            Le pasamos la nueva fecha de devoluci�n
	 */
	public void setFecha_devolucion(String fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}
/**
 * Constructor de a clase Alquiler
 * @param iddispositivo Le pasamos el Dispositivo que ser� alquilado
 * @param dni_usuario Le pasamos el Usuario que realizar� el alquiler
 * @param fecha_alquiler Le pasamos la fecha del alquiler
 */
	public Alquiler(String iddispositivo, String dni_usuario,
			String fecha_alquiler) {
		this.iddispositivo = iddispositivo;
		this.dni_usuario = dni_usuario;
		this.fecha_alquiler = fecha_alquiler;
	}
/**
 * M�todo que comprueba si un Dispositivo est� alquilado
 * @param iddispositivo Le pasamos el Dispositivo que vamos a comprobar
 * @param dni_usuario 
 * @return
 */
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
							registro[i] = rs.getObject(i + 1); // Guardamos los registros de pr�stamos
							System.out.println(i + " : " + registro[i]);
							}
						if (registro[3].toString().equals("no")){
							abierto = true;
							System.out.println("He encontrado un alquiler abierto");
						}else{
							System.out.println("Este alquiler est� cerrado");
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
	/**
	 * M�todo que, si los datos son correctos y no hay un Alquiler abierto 
	 * para ese Dispositivo, crea un nuevo registro de Alquiler en la Base de Datos 
	 * @param iddispositivo Le pasamos el Dispositivo que ser� alquilado
	 * @param dni_usuario Le pasamos el Usuario que alquilar� el Dispositivo
	 */
	public static void crearAlquiler(String iddispositivo,
			String dni_usuario){
		if (comprobarAlquilerAbierto(iddispositivo, dni_usuario)==true){
			JOptionPane.showMessageDialog(null, "Ya existe un alquiler abierto para este dispositivo.");
		}else{
			Calendar c = new GregorianCalendar();	//Con estas 5 instrucciones calculo la fecha actual
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = Integer.toString((c.get(Calendar.MONTH))+1);
			String a�o = Integer.toString(c.get(Calendar.YEAR));
			String fecha = dia+"/"+mes+"/"+a�o;
			System.out.println("La fecha actual es : "+fecha+".");
			try {
				ConectarDBA.modificar("INSERT INTO `prestamo`(`iddispositivo`, `dni_usuario`, `fecha_alquiler`) VALUES ('1','44859921P','"+fecha+"')");
				JOptionPane.showMessageDialog(null, "Alquiler abierto correctamente.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
	}
	/**
	 * M�todo que cierra un Alquiler, comprobando 
	 * que haya uno abierto con los par�metros pasados. 
	 * @param iddispositivo Le pasamos el Dispositivo cuyos Alquileres seleccionaremos en busca de uno abierto
	 * @param dni_usuario
	 */
	public static void cerrarAlquiler(String iddispositivo,
			String dni_usuario){
		if (comprobarAlquilerAbierto(iddispositivo, dni_usuario)==false){
			JOptionPane.showMessageDialog(null, "No existe un alquiler abierto para este dispositivo.");
		}else{
			try {
				Calendar c = new GregorianCalendar();	//Con estas 5 instrucciones calculo la fecha actual
				String dia = Integer.toString(c.get(Calendar.DATE));
				String mes = Integer.toString((c.get(Calendar.MONTH))+1);
				String a�o = Integer.toString(c.get(Calendar.YEAR));
				String fecha = dia+"/"+mes+"/"+a�o;
				System.out.println("La fecha actual es : "+fecha+".");
				ConectarDBA.modificar("UPDATE `prestamo` SET `fecha_devol` = '"+fecha+"' WHERE `iddispositivo` = '"+iddispositivo+"' AND `fecha_devol` = 'no'");
				JOptionPane.showMessageDialog(null, "Alquiler cerrado correctamente.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
/*	public static boolean validarDatos(String iddispositivo,
			String dni_usuario, String fecha_alquiler) throws HeadlessException, SQLException {
		boolean b = false;

		// Compruebo que el ID de dispositivo sea num�rico
		b = isInteger(iddispositivo);

		if (b == false) {
			JOptionPane.showMessageDialog(null,
					"Error. El ID de dispositivo ha de ser num�rico.");
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
						|| fecha_alq.getTime() > fechaAhora.getTime()) {

					JOptionPane.showMessageDialog(null,
							"Error. La fecha indicada no es correcta");
					return false;
				}else {

							return true;
							/**
							 * Si todos los datos son correctos devuelve True.
							 */
/*						}
					}
				}
			}
		}*/
	
	/**
	 * M�todo que comprueba que el ID de Dispositivo y el DNI de Usuario 
	 * sean correctos, est�n en la base de datos y est�n activos
	 */
	public static boolean validarDatos(String iddispositivo,
			String dni_usuario) throws HeadlessException, SQLException {
		boolean b = false;

		// Compruebo que el ID de dispositivo sea num�rico
		b = isInteger(iddispositivo);

		if (b == false) {
			JOptionPane.showMessageDialog(null,
					"Error. El ID de dispositivo ha de ser num�rico.");
			return false;

		} else{
			if(!(ConectarDBA.comprobarExiste("dispositivo", "iddispositivo", iddispositivo, true))){
				JOptionPane.showMessageDialog(null,
				"Error. No existe un dispositivo activo con el ID introducido.");
		return false;
			}else {
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
		}
/**
 * M�todo que comprueba si la cadena introducida es un n�mero entero
 * @param cadena Le pasamos la cadena a comprobar
 * @return Devuelve "True" si es un n�mero entero y "False" si no lo es
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
