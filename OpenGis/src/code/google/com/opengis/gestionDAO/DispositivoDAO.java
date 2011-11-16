package code.google.com.opengis.gestionDAO;

import java.sql.*;

import javax.swing.JOptionPane;

// Esta clase permite insertar, modificar, y dar de baja dispositivos

public class DispositivoDAO {

	static boolean existe;
	static String resultado;
	static ConectarDBA dba = new ConectarDBA();

	private String iddispositivo;
	private String modelo;
	private String numSerie;

	//private int disponible;
	//private int activo;

	// //////////////////// CONSTRUCTOR ///////////////////////

	public DispositivoDAO(String iddispositivo, String modelo, String numSerie) {
		super();
		this.iddispositivo = iddispositivo;
		this.modelo = modelo;
		this.numSerie = numSerie;
		//this.disponible = 1;
		//this.activo = 1;
	}
	
	//***************************************************************************//
	//********************************MÉTODOS************************************//
	//***************************************************************************//
	
	// ///////////////////////// ESTE MÉTODO COMPROBARÁ QUE NO EXISTA EL DISPOSITIVO.
	
	public static void comprobarDispositivo(String iddispositivo)
			throws SQLException {

		ConectarDBA.acceder();
		String sentencia = "SELECT * FROM `dispositivo` WHERE `iddispositivo` LIKE '"
				+ iddispositivo + "'";
		ResultSet rs = dba.consulta(sentencia);
		while (rs.next()) {
			resultado = rs.getString(1);
		}

		System.out.println("Enviado: " + iddispositivo + " esperado: "
				+ resultado);
		if (resultado == null) {
			existe = false;
			System.out.println("El estado de existe es: " + existe);
		} else if (resultado.equals(iddispositivo)) {
			existe = true;
			System.out.println("El estado de existe es: " + existe);
		}

		rs.close();
	}

	// ///////////////////////// ESTE MÉTODO DARÁ DE ALTA EL DISPOSITIVO INDICADO,
	// ///////////////////////// COMPROBANDO QUE NO EXISTA.

	public void altaDispositivo() throws SQLException {
		comprobarDispositivo(this.iddispositivo);

		if (existe == true) {

			JOptionPane.showMessageDialog(null,
					"El ID de Dispositivo ya existe");

		} else {

			String sentencia = "INSERT INTO `dai2opengis`.`dispositivo` (`iddispositivo` ,`modelo` ,`numSerie` ,`disponible` ,`activo`) VALUES ('"
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

	// ///////////////////////// ESTE MÉTODO DARÁ DE BAJA EL DISPOSITIVO INDICADO,
	// ///////////////////////// COMPROBANDO QUE YA EXISTA,
	// ///////////////////////// Y MODIFICANDO SU CAMPO "ACTIVO" A FALSO.

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

}
