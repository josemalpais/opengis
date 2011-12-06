package code.google.com.opengis.gestionDAO;

import java.sql.*;

import javax.swing.JOptionPane;
import code.google.com.opengis.gestion.Producto;

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
	private int activo;
	
//CONSTRUCTOR
	public ProductoDAO(int idprod, String nombre, String descripcion, String nomtarea, String dosis, String dni, int activo){
	
	this.idprod=idprod;
	this.nombre=nombre;
	this.descripcion=descripcion;
	this.nomtarea =nomtarea;
	this.dosis=dosis;
	this.dni=dni;
	this.activo=activo;
	}

//METODOS
	
	
	//metodo modificar
	public void modificarProducto() throws SQLException{
		
		ConectarDBA.acceder();
		String sentencia = "UPDATE `producto` SET `nombre`='"+ this.nombre +"',`descripcion`='"+ this.descripcion +"',`nomtarea`='"+ this.nomtarea +"',`dosis`='"+ this.dosis +"',`dni`='"+this.dni+"', `activo`='"+this.activo+"' WHERE `idprod` = '"+ this.idprod + "'";
		ConectarDBA.modificar(sentencia);	
		ConectarDBA.cerrarCon();
		JOptionPane.showMessageDialog(null,"Se ha modificado el producto correctamente");
		
	}
	
	//metodo para hacer la sentencia que crea productos
	public void altaProducto() throws SQLException{

			ConectarDBA.acceder();
			String sentencia = "INSERT INTO `dai2opengis`.`producto` (`idprod` ,`nombre` ,`descripcion` ,`nomtarea` ,`dosis`, `dni`, `activo`) VALUES ('"+ this.idprod +"', '" + this.nombre + "','" + this.descripcion +"','" + this.nomtarea +"','" + this.dosis + "','"+this.dni+"',"+this.activo+")";
			ConectarDBA.modificar(sentencia);
			ConectarDBA.cerrarCon();
			JOptionPane.showMessageDialog(null,"Se ha dado de alta el nuevo producto");
		
		
	}
	
	//metodo para desactivar un producto
	public static void desactivarProducto(String id) throws SQLException {

			String sentencia = "UPDATE producto SET `activo` = '1' WHERE `idprod` = '"+ id + "'";
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Producto dado de baja correctamente");

	}
	//metodo para activar un producto
	public static void activarProducto(String id) throws SQLException {

			String sentencia = "UPDATE producto SET `activo` = '0'  WHERE `idprod` = '"+id+"'";
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"El producto se ha activado correctamente");

	}
	
	public static ResultSet buscar(String criterio) {
		
		ConectarDBA.acceder();
        ResultSet rs = null;
        String sql = "SELECT * FROM `producto` WHERE idprod LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%' OR descripcion LIKE '%"+criterio+"%' OR nomtarea LIKE '%"+criterio+"%' OR dosis LIKE '%"+criterio+"%' OR dni LIKE '%"+criterio+"%'";

        try {
        	
           rs = ConectarDBA.consulta(sql);
           
        } catch (SQLException e) {
            System.out.println(e);
        } 
        
        return rs;

    }

	

	
	
	
	
	
	
	
	
	
	
}
