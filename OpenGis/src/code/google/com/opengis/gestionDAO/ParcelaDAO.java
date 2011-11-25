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
public class ParcelaDAO extends Parcela{
	private static String consulta; //String en el cual almacenaremos las consultas.
	private static ConectarDBA cdba; //Objeto clase Connector, para realizar las consultas
	private static String resultado;
	private static boolean existe;
	/**
	 * Constructor de parcela, en el que introduciremos el total de atributos por parámetro.
	 * @param idparcela : identificador de la parcela
	 * @param alias : nombre que el usuario asignara a la parcela para relacionarlo
	 * @param provincia : provincia de la parcela
	 * @param poblacion : poblacion de la parcela
	 * @param poligono : poligono de la parcela
	 * @param numero : numero de la parcela
	 * @param activo : nos avisara si la parcela esta activa o no, a los ojos del usuario
	 * @param partida : identificador de parcela a nivel localidad
	 * @param dnipropietario : dni del propietario de la parcela
	 */
	public ParcelaDAO(int idparcela, String alias, String provincia,
			String poblacion, String poligono, String numero, boolean activo,
			String partida, String dnipropietario) {
		super(idparcela, alias, provincia, poblacion, poligono, numero, activo,
				partida, dnipropietario);
	}
/**
 * Método con el que comprobaremos que la Parcela existe en la base de datos
 * @param idp el identificador de parcela con el que haremos la comprobación
 * @throws SQLException
 */
	public void comprobar(int idp) throws SQLException{
		ConectarDBA.acceder();
		consulta = "SELECT * FROM `parcela` WHERE `dni` LIKE '"+this.getIdParcela()+"'";
		ResultSet rs = cdba.consulta(consulta);
		while(rs.next()){
			System.out.println("Ejecuto el while");
			resultado = rs.getString(1);
		}	
		System.out.println("Enviado: "+this.getIdParcela()+" esperado: "+resultado);
		if(resultado == null){
			existe = false;
			System.out.println("El estado de existe es: "+existe);
		}else if (resultado.equals(this.getIdParcela())){
			existe = true;
			System.out.println("El estado de existe es: "+existe);
		}
		rs.close();
	}
	/**
	 * Método con el que realizaremos las Altas de parcela
	 * @throws SQLException
	 */
	public void altaParcela() throws SQLException{
		ConectarDBA.acceder();
		comprobar(this.getIdParcela());
		consulta = "INSERT INTO `dai2opengis`.`parcela` (`idparcela` ,`alias` ,`provincia` ,`poblacion` ,`poligono` ,`numero` ,`activo`," +
		 		" `activo`) VALUES ('"+ this.getIdParcela() +"', '" + this.getAlias()  + "','" + this.getProvincia() +"','" +
				 this.getPoblacion() +"','" + this.getPoligono() +"','" + this.getNumero() + "','" + this.isActivo() + this.getPartida() 
				 + this.getDniPropietario() +"', '0')";
		if (existe == true){ 	
			 JOptionPane.showMessageDialog(null,"El Id Parcela que intenta introducir ya existe");
		}else{
			cdba.modificar(consulta);
			JOptionPane.showMessageDialog(null,"Parcela insertada correctamente");
		}
	}
	/**
	 * Método con el que relizaremos las Bajas de parcela
	 * @throws SQLException
	 */
	public void bajaParcela() throws SQLException{
		comprobar(this.getIdParcela());
		if (existe == true){ 
		
			String sentencia = "DELETE FROM `parcela` WHERE `idparcela` = '"+this.getIdParcela()+"'";
			cdba.modificar(sentencia);

			JOptionPane.showMessageDialog(null,"Parcela eliminada correctamente");
			
		}else{
			
			JOptionPane.showMessageDialog(null,"La parcela no existe");
		}
		cdba.cerrarCon();
	}
	/**
	 * Método con el que realizaremos las Modificaciones de parcela
	 * @throws SQLException
	 */
	public void MoficicarParcela() throws SQLException{
		comprobar(this.getIdParcela());
		if (existe == true){
			consulta = "UPDATE INTO `dai2opengis`.`parcela` (`idparcela` ,`alias` ,`provincia` ,`poblacion` ,`poligono` ,`numero` ,`activo`," +
			 		" `activo`) VALUES ('"+ this.getIdParcela() +"', '" + this.getAlias()  + "','" + this.getProvincia() +"','" +
					 this.getPoblacion() +"','" + this.getPoligono() +"','" + this.getNumero() + "','" + this.isActivo() + this.getPartida() 
					 + this.getDniPropietario() +"', '0')";
			cdba.modificar(consulta);
			JOptionPane.showMessageDialog(null,"Se ha modificado la parcela correctamente");
		}else{
			JOptionPane.showMessageDialog(null,"La Parcela no existe");
		}
	}
	
}
