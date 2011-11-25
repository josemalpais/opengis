/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import code.google.com.opengis.gestion.Parcela;

/**
 * Clase que hereda de Parcela y desde la que Agregaremos, modificaremos y borraremos registros de parcela.
 * @author knals
 *
 */
public class ParcelaDAO{
	private static String sentencia; //String en el cual almacenaremos las consultas.
	private static ConectarDBA dba; //Objeto clase Connector, para realizar las consultas
	private static String resultado[];
	private static boolean existe;
	private static int idparcela; //identificador de la parcela
	private static String alias; //nombre que el usuario asignara a la parcela para relacionarlo
	private static String provincia;  //provincia de la parcela
	private static  String poblacion; //poblacion de la parcela
	private static String poligono; //poligono de la parcela
	private static String numero; //numero de la parcela
	private static boolean activo; //nos avisara si la parcela esta activa o no, a los ojos del usuario
	private static String partida; //identificador de parcela a nivel localidad
	private static String dniPropietario; //dni del propietario de la parcela
	

/**
 * Método con el que comprobaremos que la Parcela existe en la base de datos
 * @param idp el identificador de parcela con el que haremos la comprobación
 * @throws SQLException
 */	
public static void comprobarParcela(String dni) throws SQLException{
		ConectarDBA.acceder();
		sentencia = "SELECT `dni`, `activo` FROM `parcela` WHERE `dni` LIKE '"+dni+"'";
		ResultSet rs = dba.consulta(sentencia);
		resultado = new String[2];
		activo = false;
		while(rs.next()){
			System.out.println("Ejecuto el while");
				resultado[0] = rs.getString(1);
				System.out.println(resultado[0]);
				resultado[1] = rs.getString(2);
				System.out.println(resultado[1]);
		}
			System.out.println("Enviado: "+dni+" esperado: "+resultado[0].toString());
			if(resultado[0] == null){
				existe = false;
				System.out.println("El estado de existe es: "+existe);
			}else if (resultado[0].equals(dni)){
				existe = true;
				if (resultado[1].equals("0")){
					activo = true;	
				}
				System.out.println("El estado de activo es: "+activo);
				System.out.println("El estado de existe es: "+existe);
			}
		rs.close();
	}
	
/**
 * Método que realiza las busquedas de parcela
 * @param campo:
 * @param criterio
 * @return
 * @throws SQLException
 */
public static ResultSet buscarParcela(String campo, String criterio) throws SQLException{
 	ResultSet rs = null;
 	ConectarDBA.acceder();
	String sentencia ="Select  `dai2opengis`.`parcela` (`idparcela` ,`alias` ,`provincia` ,`poblacion` ,`poligono` ,`numero` ,`activo`," +
	 		" `activo`) FROM ('"+ idparcela +"', '" + alias  + "','" + provincia +"','" +
			 poblacion +"','" + poligono +"','" + numero + "','" + activo + partida 
			 + dniPropietario +"', '0')";
	try{
		System.out.println("Ejecutada sentencia "+ sentencia);
	rs = dba.consulta(sentencia);
	}catch (SQLException e){
		System.out.println(e);
	}
	return rs;
	
}

	/**
	 * Método con el que realizaremos las Altas de parcela
	 * @throws SQLException
	 */
	public static void altaParcela() throws SQLException{
		ConectarDBA.acceder();
		comprobarParcela(idparcela+"");
		sentencia = "INSERT INTO `dai2opengis`.`parcela` (`idparcela` ,`alias` ,`provincia` ,`poblacion` ,`poligono` ,`numero` ,`activo`," +
		 		" `activo`) VALUES ('"+ idparcela +"', '" + alias  + "','" + provincia +"','" +
				 poblacion +"','" + poligono +"','" + numero + "','" + activo + partida 
				 + dniPropietario +"', '0')";
		if (existe == true){ 	
			 JOptionPane.showMessageDialog(null,"El Id Parcela que intenta introducir ya existe");
		}else{
			dba.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Parcela insertada correctamente");
		}
	}
	
	
	
	/**
	 * Método con el que relizaremos las Bajas de parcela
	 * @throws SQLException
	 */

	public static void bajaParcela() throws SQLException{
		comprobarParcela(idparcela+"");
		if (existe == true){ 
		
			String sentencia = "DELETE FROM `parcela` WHERE `idparcela` = '"+idparcela+"'";
			dba.modificar(sentencia);

			JOptionPane.showMessageDialog(null,"Parcela eliminada correctamente");
			
		}else{
			
			JOptionPane.showMessageDialog(null,"La parcela no existe");
		}
		dba.cerrarCon();
	}
	
	/**
	 * Método con el que realizaremos las Modificaciones de parcela
	 * @throws SQLException
	 */
	public static void MoficicarParcela() throws SQLException{
		comprobarParcela(idparcela+"");
		if (existe == true){
			sentencia = "UPDATO INTO `dai2opengis`.`parcela` (`idparcela` ,`alias` ,`provincia` ,`poblacion` ,`poligono` ,`numero` ,`activo`," +
			 		" `activo`) VALUES ('"+ idparcela +"', '" + alias  + "','" + provincia +"','" +
					 poblacion +"','" + poligono +"','" + numero + "','" + activo + partida 
					 + dniPropietario +"', '0')";
			dba.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Se ha modificado la parcela correctamente");
		}else{
			JOptionPane.showMessageDialog(null,"La Parcela no existe");
		}
	}

		
}



