package code.google.com.opengis.gestionDAO;

import java.sql.*;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestion.Dispositivo;

/**
 * Esta clase permite insertar, modificar, y dar de baja dispositivos
 * 
 * @author Iván Serrano y Jose Alapont
 * 
 */

public class DispositivoDAO {

	static boolean existe;
	static String resultado;
	static ConectarDBA dba = new ConectarDBA();

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

	public static boolean comprobarDispositivo(String iddispositivo)throws SQLException 
	{
		ConectarDBA.acceder();
		String sentencia = "SELECT * FROM `dispositivo` WHERE `iddispositivo` LIKE '"+iddispositivo+"'";
		ResultSet rs = dba.consulta(sentencia);
		while (rs.next()) {
			resultado = rs.getString(1);
			System.out.println(resultado);
		}
		System.out.println("Enviado: " + iddispositivo + " esperado: "
				+ resultado);

		if (resultado.equals(iddispositivo)) 
		{
			existe = true;
			System.out.println("El estado de existe es: " + existe);
		}
		else {
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
	
	public void altaDispositivo() throws SQLException {
		comprobarDispositivo(this.iddispositivo);

		if (existe == true) {

			JOptionPane.showMessageDialog(null,
					"El ID de Dispositivo ya existe");

		} else {
			ConectarDBA.acceder();
			String sentencia = "INSERT INTO `dai2opengis`.`dispositivo` (`iddispositivo` ,`modelo` ,`num_serie` ,`disponible` ,`activo`) VALUES ('"
					+ this.iddispositivo
					+ "', '"
					+ this.modelo
					+ "','"
					+ this.numSerie + "','1','1')";
			dba.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					"Se ha dado de alta el nuevo dispositivo");

		}

		dba.cerrarCon();

	}

	/**
	 * ESTE MÉTODO DARÁ DE BAJA EL DISPOSITIVO INDICADO, COMPROBANDO QUE YA
	 * EXISTA, Y MODIFICANDO SU CAMPO "ACTIVO" A FALSO.
	 */
	
	public void borrarDispositivo() throws SQLException {
		comprobarDispositivo(this.iddispositivo);
		if (existe == true) {

			String sentencia = "UPDATE dispositivo SET `activo` = '0' , `disponible`= '0' WHERE `iddispositivo` = '"
					+ this.iddispositivo + "'";
			dba.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					"Dispositivo dado de baja correctamente");

		} else {

			JOptionPane.showMessageDialog(null, "El dispositivo no existe");
		}

		dba.cerrarCon();

	}
	public void modificarDispositivo(String modelo, String numSerie) throws SQLException {
		comprobarDispositivo(this.iddispositivo);
		if (existe == true) {
			
			String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `modelo` = '"+modelo+"', `num_serie` = '"+numSerie+"' WHERE `dispositivo`.`iddispositivo` =" + this.iddispositivo;
			dba.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					"Dispositivo modificado correctamente");

		} else {

			JOptionPane.showMessageDialog(null, "El dispositivo no existe");
		}

		dba.cerrarCon();

	}
	public void disponibleNo() throws SQLException {
		comprobarDispositivo(this.iddispositivo);
		if (existe == true) {
			
			String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `disponible` = '0' WHERE `dispositivo`.`iddispositivo` =" + this.iddispositivo;
			dba.modificar(sentencia);
			
			JOptionPane.showMessageDialog(null,
					"Dispositivo modificado correctamente. Ahora no está disponible.");

		} else {

			JOptionPane.showMessageDialog(null, "El dispositivo no existe");
		}

		dba.cerrarCon();

	}
	public void disponibleSi() throws SQLException {
		comprobarDispositivo(this.iddispositivo);
		if (existe == true) {
			
			String sentencia = "UPDATE `dai2opengis`.`dispositivo` SET `disponible` = '1' WHERE `dispositivo`.`iddispositivo` =" + this.iddispositivo;
			dba.modificar(sentencia);
			
			JOptionPane.showMessageDialog(null,
					"Dispositivo modificado correctamente. Ahora sí está disponible.");

		} else {

			JOptionPane.showMessageDialog(null, "El dispositivo no existe");
		}

		dba.cerrarCon();
		}
	
	public static void cojerDatos(String iddispositivo){
		if (Dispositivo.validarDatos(iddispositivo)==true){
			try {
				if (DispositivoDAO.comprobarDispositivo(iddispositivo)==true){
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//
	
}
