/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionDAO;

import java.sql.SQLException;
import code.google.com.opengis.gestion.Parcela;

/**
 * Clase que hereda de Parcela y desde la que Agregaremos, modificaremos y borraremos registros de parcela.
 * @author knals
 *
 */
public class ParcelaDAO extends Parcela{
	private String consulta; //String en el cual almacenaremos las consultas.
	private ConectarDBA cdba; //Objeto clase Connector, para realizar las consultas
	
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
	 * (FALTA VALIDAR)
	 * @throws SQLException
	 */
	public void altaParcela() throws SQLException{
		ConectarDBA.acceder();
		 consulta = "INSERT INTO `dai2opengis`.`parcela` (`idparcela` ,`alias` ,`provincia` ,`poblacion` ,`poligono` ,`numero` ,`activo`," +
		 		" `activo`) VALUES ('"+ this.getIdParcela() +"', '" + this.getAlias()  + "','" + this.getProvincia() +"','" +
				 this.getPoblacion() +"','" + this.getPoligono() +"','" + this.getNumero() + "','" + this.isActivo() + this.getPartida() 
				 + this.getDniPropietario() +"', '0')";
		 cdba.modificar(consulta);
		
		
	}

}
