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
	private int idparcela; //identificador de la parcela
	private String alias; //nombre que el usuario asignara a la parcela para relacionarlo
	private String provincia;  //provincia de la parcela
	private String poblacion; //poblacion de la parcela
	private String poligono; //poligono de la parcela
	private  String numero; //numero de la parcela
	private int activo; //nos avisara si la parcela esta activa o no, a los ojos del usuario
	private String partida; //identificador de parcela a nivel localidad
	private String dniPropietario; //dni del propietario de la parcela
	private static String sentencia; //String en el cual almacenaremos las consultas.
	
	private static ConectarDBA dba; //Objeto clase Connector, para realizar las consultas
	private static String resultado[];
	private static boolean existe;
	private static boolean valido=true;
	


	//PARCELADAO
	
	public void comprobarParcela(int s) throws SQLException{
		ConectarDBA.acceder();
		sentencia = "SELECT `alias`, `activo` FROM `parcela` WHERE `alias` LIKE '"+s+"'";
		ResultSet rs = dba.consulta(sentencia);
		resultado = new String[2];
		activo = 0;
		while(rs.next()){
			System.out.println("Ejecuto el while");
				resultado[0] = rs.getString(1);
				System.out.println(resultado[0]);
				resultado[1] = rs.getString(2);
				System.out.println(resultado[1]);
		}
			System.out.println("Enviado: "+alias+" esperado: "+resultado[0].toString());
			if(resultado[0] == null){
				existe = false;
				System.out.println("El estado de existe es: "+existe);
			}else if (resultado[0].equals(alias)){
				existe = true;
				if (resultado[1].equals("0")){
					activo = 1;	
				}
				System.out.println("El estado de activo es: "+activo);
				System.out.println("El estado de existe es: "+existe);
			}
		rs.close();
	}
	
/**
 * Método que realiza las busquedas de parcela 
 * @param criterio: criterio con el que buscaremos en todas las columnas de la base de datos.
 * @return ResultSet con las tuplas encontradas
 * @throws SQLException
 */
public static ResultSet buscar(String criterio) throws SQLException{
 	ResultSet rs = null;
 	ConectarDBA.acceder();
	String sentencia = "SELECT `idparcela`, `alias`, `provincia`, `poblacion`, `poligono`, `numero`, `partida`, `dni_propietario` FROM `parcela` WHERE (`idparcela` = '"+criterio+"' Or `alias` = '"
			+criterio+"' Or `provincia` = '"+criterio+"' Or  `poblacion` = '"+criterio+"' Or `poligono` = '"
			+criterio+"' Or  `numero` = '"+criterio+"' Or  `partida` = '"
			+criterio+"' Or  `dni_propietario`= '"+criterio+"' ) AND  `activo` <> '0'";
	

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
	public static void altaParcela(Parcela p1) throws SQLException{
		ConectarDBA.acceder();
		existe = false;

		sentencia = "INSERT INTO `dai2opengis`.`parcela` (`idparcela`, `alias`, `provincia`, `poblacion`," +
				" `poligono`, `numero`, `activo`, `partida`, `dni_propietario`) VALUES (NULL,'" + p1.getAlias()  +
				"','" + p1.getProvincia() +"','" +p1.getPoblacion() +"','" + p1.getPoligono() +"','" + p1.getNumero() +"'," +
						"'"  + p1.isActivo() +"','"+ p1.getPartida() +"','"+ p1.getDniPropietario() +"')";
		
		if (existe == true){ 	
			 JOptionPane.showMessageDialog(null,"El Id Parcela que intenta introducir ya existe");
		}else{
			dba.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Parcela insertada correctamente");
		}
		dba.cerrarCon();
	
	}
	
	
	
	/**
	 * Método con el que relizaremos las Bajas de parcela
	 * @throws SQLException
	 */

	public void bajaParcela() throws SQLException{
			sentencia = "UPDATE `dai2opengis`.`parcela` SET `activo`= `"+this.activo+"` WHERE `idparcela` LIKE `"+this.idparcela+"`)";
			dba.modificar(sentencia);

			JOptionPane.showMessageDialog(null,"Parcela dada de baja correctamente");
			
		dba.cerrarCon();
	}
	
	/**
	 * Método con el que realizaremos las Modificaciones de parcela
	 * @throws SQLException
	 */
	public void modificarParcela() throws SQLException{	
			sentencia = "UPDATE `dai2opengis`.`parcela` SET `idparcela`= `"+this.idparcela+"`,`alias`=`"+this.alias+"`," +
					" `provincia`= `"+this.provincia+"`, `poblacion`= `"+this.poblacion+"`,`poligono`= `"+this.poligono+"`," +
							"`numero`= `"+this.numero+"`,`partida`= `"+this.partida+"`,`dni_propietario`= `"+this.dniPropietario+
							"`  WHERE `idparcela` LIKE `"+this.idparcela+"`)";
	
					dba.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Se ha modificado la parcela correctamente");
	}
	
	
	
	
	
	
	
	
	/*

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

*/		
}



