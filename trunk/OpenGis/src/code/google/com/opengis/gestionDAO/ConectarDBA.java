/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionDAO;
import java.sql.*;

import javax.swing.JOptionPane;

/**
* @author Danico & Pipepito
* Clase que establece conexión con la base de datos y realiza las diferentes funciones con esta
* Ya sea Querys o Updates
* Última modificación 11/11/11
* 
*/

public class ConectarDBA {

/** Objeto del tipo conexión */
static Connection conexion;
/** Objeto del tipo Statement */
static Statement st;
	


/** 
 * Método que nos conecta con la base de datos
*/ 	
	public static void acceder() {
		 
        // Asignamos a variables todos los datos requeridos para la conexion a
        // la base de datos.
        String nombreDriver = "com.mysql.jdbc.Driver";
        //String nombreServidor = "db4free.net";
        String nombreServidor = "79.108.245.167";
        //String nombreServidor = "10.2.1.43";
        String numeroPuerto = "3306";
        String miBaseDatos = "dai2opengis";
        String url = "jdbc:mysql://" + nombreServidor + ":" + numeroPuerto
                        + "/" + miBaseDatos;
        //String dbuser = "ivanserrano";
        //String dbpwd = "dai20112012";
        String dbuser = "dai2proyecto";
        String dbpwd = "dai20112012";

        try {
                Class.forName(nombreDriver).newInstance();
                conexion = DriverManager.getConnection(url, dbuser, dbpwd);
                st = conexion.createStatement();
                System.out.println("conectaBD");
        } catch (IllegalAccessException e) {
			JOptionPane.showMessageDialog(null,"Error De Conexión");

                System.err.println(e.getMessage());
        } catch (InstantiationException e) {
			JOptionPane.showMessageDialog(null,"Error De Conexión");

                System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Error De Conexión");

                System.err.println(e.getMessage());    
        } catch (SQLException e) {
                System.out.println("Aquí falla");
				JOptionPane.showMessageDialog(null,"Error De Conexión");

                System.err.println(e.getMessage());
                
        }
}
/**
 * 	Método para realizar consultas a la base de datos.
 * @param sentenciaSQL String consulta que vamos a ejecutar
 * @return ResultSet devuelve el resultado de la consulta.
 * @throws SQLException devuelve un error en caso de producirse
 */
	public ResultSet consulta(String sentenciaSQL) throws SQLException
	{
	//   Creamos un tipo Statement que maneja las consultas 
	   
	//   Retorno la consulta especifica...
	    return  st.executeQuery (sentenciaSQL);
	 
	}
	/**
	 * 
	 * @param sentenciaSQL String con el insert/update que vamos a ejecutar
	 * @throws SQLException devuelve un error en caso de producirse
	 */
	public void modificar(String sentenciaSQL) throws SQLException
	{
	//   Creamos un tipo Statement que maneja las consultas 
	   
	//   Retorno la consulta especifica...
		st.executeUpdate (sentenciaSQL);
	 
	}
	/**
	 * Método que cierra la conexión con la base de datos y lo statements.
	 * @throws SQLException
	 */
	public void cerrarCon() throws SQLException{
		st.close();
		conexion.close();
		System.out.println("Conexión cerrada");
			
	}
}
