package code.google.com.opengis.gestion;
//

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;
import code.google.com.opengis.gestionVISUAL.PrestamoPanelGestion;

public class Prestamo {
	private String iddispositivo;
	private String dni_usuario;
	private String fecha_prestamo;
	private String fecha_devolucion;

	/**
	 * 
	 * @return Este método nos devuelve el Identificador de dispositivo
	 */
	public String getIddispositivo() {
		return iddispositivo;
	}

	/**
	 * Con este método cambiamos Identificador del dispositivo del préstamo
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
	 *         préstamo
	 */
	public String getDni_usuario() {
		return dni_usuario;
	}

	/**
	 * Con este método cambiamos el DNI del usuario que realiza este préstamo
	 * 
	 * @param dni_usuario
	 *            Le pasamos el nuevo DNI
	 */
	public void setDni_usuario(String dni_usuario) {
		this.dni_usuario = dni_usuario;
	}

	/**
	 * Este método nos devuelve la fecha en la que se inicia este préstamo
	 * 
	 * @return
	 */
	public String getFecha_prestamo() {
		return fecha_prestamo;
	}

	/**
	 * Con este método cambiamos la fecha en la que se inicia este préstamo
	 * 
	 * @param fecha_préstamo
	 *            Le pasamos la nueva fecha de prestamo
	 */
	public void setFecha_prestamo(String fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}

	/**
	 * Este método nos devuelve la fecha en la que se finaliza este préstamo
	 * 
	 * @return
	 */
	public String getFecha_devolucion() {
		return fecha_devolucion;
	}

	/**
	 * Con este método cambiamos la fecha en la que se finaliza este préstamo
	 * 
	 * @param fecha_devolucion
	 *            Le pasamos la nueva fecha de devolución
	 */
	public void setFecha_devolucion(String fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}
/**
 * Constructor de a clase Prestamo
 * @param iddispositivo Le pasamos el Dispositivo que será prestado
 * @param dni_usuario Le pasamos el Usuario que realizará el préstamo
 * @param fecha_prestamo Le pasamos la fecha del préstamo
 */
	public Prestamo(String iddispositivo, String dni_usuario,
			String fecha_prestamo) {
		this.iddispositivo = iddispositivo;
		this.dni_usuario = dni_usuario;
		this.fecha_prestamo = fecha_prestamo;
	}
/**
 * Método que comprueba si un Dispositivo está prestado
 * @param iddispositivo Le pasamos el Dispositivo que vamos a comprobar
 * @param dni_usuario 
 * @return
 */
	public static boolean comprobarPrestamoAbierto(String iddispositivo,
			String dni_usuario){
		boolean abierto = false;
		
			try {
				if (validarDatos(iddispositivo, dni_usuario)==true){
					ResultSet rs = ConectarDBA.buscar("SELECT * FROM `prestamo` WHERE `iddispositivo` = '"+iddispositivo+"'"); //$NON-NLS-1$ //$NON-NLS-2$
					int nColumnas = rs.getMetaData().getColumnCount();
					
					Object[] registro = new Object[nColumnas];
					while (rs.next()) {

						for (int i = 0; i < nColumnas; i++) {
							registro[i] = rs.getObject(i + 1); // Guardamos los registros de préstamos
							System.out.println(i + " : " + registro[i]); //$NON-NLS-1$
							}
						if (registro[4].toString().equals("no")){ //$NON-NLS-1$
							abierto = true;
							System.out.println("He encontrado un préstamo abierto"); //$NON-NLS-1$
						}else{
							System.out.println("Este préstamo está cerrado"); //$NON-NLS-1$
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
	 * Método que, si los datos son correctos y no hay un Préstamo abierto 
	 * para ese Dispositivo, crea un nuevo registro de Préstamo en la Base de Datos 
	 * @param iddispositivo Le pasamos el Dispositivo que será prestado
	 * @param dni_usuario Le pasamos el Usuario al que se le prestará el Dispositivo
	 */
	public static void crearPrestamo(String iddispositivo,
			String dni_usuario){
		if (comprobarPrestamoAbierto(iddispositivo, dni_usuario)==true){
			JOptionPane.showMessageDialog(null, Idioma.getString("msgLoanAlreadyExists")); //$NON-NLS-1$
		}else{
			Calendar c = new GregorianCalendar();	//Con estas 5 instrucciones calculo la fecha actual
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = Integer.toString((c.get(Calendar.MONTH))+1);
			String año = Integer.toString(c.get(Calendar.YEAR));
			String fecha = dia+"/"+mes+"/"+año; //$NON-NLS-1$ //$NON-NLS-2$
			System.out.println("La fecha actual es : "+fecha+"."); //$NON-NLS-1$ //$NON-NLS-2$
			
			try {
				ConectarDBA.modificar("INSERT INTO `prestamo`(`iddispositivo`, `dni_usuario`, `fecha_alquiler`) VALUES ('"+iddispositivo+"','"+dni_usuario+"','"+fecha+"')"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				JOptionPane.showMessageDialog(null, Idioma.getString("msgLoanSuccess")); //$NON-NLS-1$
				
				String modifDispo = "UPDATE `dispositivo` SET `disponible`='1' WHERE iddispositivo='" + iddispositivo +"' "; //$NON-NLS-1$ //$NON-NLS-2$
				ConectarDBA.modificar(modifDispo);
				
				ConectarDBA.cerrarCon();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
	}
	/**
	 * Método que cierra un Préstamo, comprobando 
	 * que haya uno abierto con los parámetros pasados. 
	 * @param iddispositivo Le pasamos el Dispositivo cuyos Préstamos seleccionaremos en busca de uno abierto
	 * @param dni_usuario
	 */
	public static void cerrarPrestamo(String iddispositivo,
			String dni_usuario){
		if (comprobarPrestamoAbierto(iddispositivo, dni_usuario)==false){
			JOptionPane.showMessageDialog(null, "No existe un préstamo abierto para este dispositivo."); //$NON-NLS-1$
		}else{
			try {
				Calendar c = new GregorianCalendar();	//Con estas 5 instrucciones calculo la fecha actual
				String dia = Integer.toString(c.get(Calendar.DATE));
				String mes = Integer.toString((c.get(Calendar.MONTH))+1);
				String año = Integer.toString(c.get(Calendar.YEAR));
				String fecha = dia+"/"+mes+"/"+año; //$NON-NLS-1$ //$NON-NLS-2$
				System.out.println("La fecha actual es : "+fecha+"."); //$NON-NLS-1$ //$NON-NLS-2$
				ConectarDBA.modificar("UPDATE `prestamo` SET `fecha_devol` = '"+fecha+"' WHERE `iddispositivo` = '"+iddispositivo+"' AND `fecha_devol` = 'no'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				JOptionPane.showMessageDialog(null, Idioma.getString("msgLoanReturned")); //$NON-NLS-1$
				
				String modifDispo = "UPDATE `dispositivo` SET `disponible`='0' WHERE iddispositivo='" + iddispositivo +"' "; //$NON-NLS-1$ //$NON-NLS-2$
				ConectarDBA.modificar(modifDispo);
				
				ConectarDBA.cerrarCon();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Método que comprueba que modifica datos del préstamo, como el ID de dispositivo prestado o 
	 * el DNI del usuario que solicita el préstamo 
	 * @param idprestamo Le pasamos el ID de préstamo que se modifica
	 * @param iddispositivo Le pasamos el nuevo ID de dispositivo
	 * @param dni_usuario Le pasamos el nuevo DNI de usuario
	 * @param aux Este parámetro comprueba si se ha cambiado el ID de dispositivo para hacer una validación u otra
	 */
	public static void modificarPrestamo(String idprestamo, String iddispositivo, String dni_usuario,String aux){
		try {
			if(validarDatos(iddispositivo, dni_usuario)){
			if (aux.equals(iddispositivo)){
				try {
					if (comprobarPrestamoAbierto(iddispositivo, dni_usuario)==true){
						
						JOptionPane.showMessageDialog(null, Idioma.getString("msgLoanAlreadyExists")); //$NON-NLS-1$
					}else{
						ConectarDBA.modificar("UPDATE `prestamo` SET `dni_usuario` = '"+dni_usuario+"' WHERE `id_prestamo` = '"+idprestamo+"' AND `fecha_devol` = 'no'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						JOptionPane.showMessageDialog(null, Idioma.getString("msgLoanUpdated")); //$NON-NLS-1$
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				}
			else{
				if (comprobarPrestamoAbierto(iddispositivo, dni_usuario)==true){
			
				JOptionPane.showMessageDialog(null, Idioma.getString("msgLoanAlreadyExists")); //$NON-NLS-1$
			}else{
				try {
					ConectarDBA.modificar("UPDATE `prestamo` SET `iddispositivo` = '"+iddispositivo+"', `dni_usuario` = '"+dni_usuario+"' WHERE `id_prestamo` = '"+idprestamo+"' AND `fecha_devol` = 'no'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					ConectarDBA.modificar("UPDATE `dispositivo` SET `disponible`='1' WHERE iddispositivo='" + iddispositivo +"'"); //$NON-NLS-1$ //$NON-NLS-2$
					ConectarDBA.modificar("UPDATE `dispositivo` SET `disponible`='0' WHERE iddispositivo='" + aux +"'"); //$NON-NLS-1$ //$NON-NLS-2$
					JOptionPane.showMessageDialog(null, Idioma.getString("msgLoanUpdated")); //$NON-NLS-1$
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			}
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que comprueba que el ID de Dispositivo y el DNI de Usuario 
	 * sean correctos, estén en la base de datos y estén activos
	 */
	public static boolean validarDatos(String iddispositivo,
			String dni_usuario) throws HeadlessException, SQLException {
		
		boolean b = false;

		b = ConectarDBA.comprobarExiste("dispositivo", "iddispositivo", iddispositivo, false); //$NON-NLS-1$ //$NON-NLS-2$
		
		if (b == false) {
			JOptionPane.showMessageDialog(null,
					//"Error. El ID de dispositivo ha de ser numérico.");
					Idioma.getString("msgErrorDeviceNotActiveWithID")); //$NON-NLS-1$
			return false;

		} else{
			b = ConectarDBA.comprobarExiste("usuario", "dni", dni_usuario, false); //$NON-NLS-1$ //$NON-NLS-2$
			if (b == false){
				JOptionPane.showMessageDialog(null,
						Idioma.getString("msgErrorNotActiveUser")); //$NON-NLS-1$
				return false;
			/*if(!(ConectarDBA.comprobarExiste("dispositivo", "iddispositivo", iddispositivo, true))){
				JOptionPane.showMessageDialog(null,
				"Error. No existe un dispositivo activo con el ID introducido.");
		return false;*/
			}/*else {
			if(!(ConectarDBA.comprobarExiste("usuario", "dni", dni_usuario, true))){
				JOptionPane.showMessageDialog(null,
						"Error. No existe un usuario activo con el DNI introducido.");
				return false;
			}*/else {

				return true;
				/**
				 * Si todos los datos son correctos devuelve True.
				 */
		
			}
		}
	}
/**
 * Método que comprueba si la cadena introducida es un número entero
 * @param cadena Le pasamos la cadena a comprobar
 * @return Devuelve "True" si es un número entero y "False" si no lo es
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
