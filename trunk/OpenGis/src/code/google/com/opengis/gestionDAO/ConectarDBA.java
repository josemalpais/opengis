package code.google.com.opengis.gestionDAO;
import java.sql.*;

public class ConectarDBA {
	Connection conexion = null;
	private String userdba ;
	private String passworddba;
	private String url;
	



	public void acceder() throws SQLException{
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			
		    Connection conexion = DriverManager.getConnection(url,userdba,passworddba);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	      
	  
	      
	}
	public ResultSet consulta(String sentenciaSQL) throws SQLException
	{
	//   Creamos un tipo Statement que maneja las consultas 
	   Statement s = this.conexion.createStatement();
	//   Retorno la consulta especifica...
	    return  s.executeQuery (sentenciaSQL);
	 
	}
	
	public void cerrarCon() throws SQLException{
		this.conexion.close();
			
	}
}
