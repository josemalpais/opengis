package code.google.com.opengis.gestionDAO;

import java.sql.*;

import javax.swing.JOptionPane;
import code.google.com.opengis.gestion.Producto;
import code.google.com.opengis.gestionVISUAL.ProductoVisual;

public class ProductoDAO {
	static boolean existe;
	static String resultado;
	static ConectarDBA dba = new ConectarDBA();
	
	private int idprod;
	private String nombre;
	private String descripcion;
	private String nomtarea;
	private String dosis;
	private String dni;
	
//CONSTRUCTOR
	public ProductoDAO(int idprod, String nombre, String descripcion, String nomtarea, String dosis, String dni){
	
	this.idprod=idprod;
	this.nombre=nombre;
	this.descripcion=descripcion;
	this.nomtarea =nomtarea;
	this.dosis=dosis;
	this.dni=dni;
	}

//METODOS
	
	
	//metodo modificar
	public void modificarProducto() throws SQLException{
		String sentencia = "UPDATE `producto` SET `nombre`='"+ this.nombre +"',`descripcion`='"+ this.descripcion +"',`nomtarea`='"+ this.nomtarea +"',`dosis`='"+ this.dosis +"',`dni`='"+this.dni+"' WHERE `idprod` = '"+ this.idprod + "'";
		ConectarDBA.modificar(sentencia);	
		JOptionPane.showMessageDialog(null,"Se ha dado de alta el nuevo producto");
		
	}
	
	//metodo para hacer la sentencia que crea productos
	public void altaProducto() throws SQLException{

			String sentencia = "INSERT INTO `dai2opengis`.`producto` (`idprod` ,`nombre` ,`descripcion` ,`nomtarea` ,`dosis`, `dni`) VALUES ('"+ this.idprod +"', '" + this.nombre + "','" + this.descripcion +"','" + this.nomtarea +"','" + this.dosis + "','"+this.dni+"')";
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Se ha dado de alta el nuevo producto");
		
		
	}
	
	//metodo para desactivar un producto
	public void desactivarProducto() throws SQLException {

			String sentencia = "UPDATE producto SET `activo` = '0' , WHERE `idprod` = '"+ this.idprod + "'";
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Producto dado de baja correctamente");

	}
	//metodo para activar un producto
	public void activarProducto() throws SQLException {

			String sentencia = "UPDATE producto SET `activo` = '1' , WHERE `idprod` = '"+ this.idprod + "'";
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Producto dado de alta correctamente");

	}
	
	public static ResultSet buscar(String salida, String criterio) {
        ResultSet rs = null;
        String sql = "SELECT " + salida + " FROM Asignaturas WHERE " + criterio;

        try {
            ConectarDBA.modificar(sql);
           
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            return rs;
        }

    }

	

	
	
	
	
	
	
	
	
	
	
}
