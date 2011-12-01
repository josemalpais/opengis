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
	
//CONSTRUCTOR
	public ProductoDAO(int idprod, String nombre, String descripcion, String nomtarea, String dosis){
	
	this.idprod=idprod;
	this.nombre=nombre;
	this.descripcion=descripcion;
	this.nomtarea =nomtarea;
	this.dosis=dosis;
	}

//METODOS
	
	
	//metodo modificar
	public void modificarProducto() throws SQLException{
		String sentencia = "UPDATE `producto` SET `nombre`=["+ this.nombre +"],`descripcion`=["+ this.descripcion +"],`nomtarea`=["+ this.nomtarea +"],`dosis`=["+ this.dosis +"] WHERE `idprod` = '"+ this.idprod + "'";
		ConectarDBA.modificar(sentencia);	
		JOptionPane.showMessageDialog(null,"Se ha dado de alta el nuevo producto");
		
	}
	
	//metodo para hacer la sentencia que crea productos
	public void altaProducto() throws SQLException{

			String sentencia = "INSERT INTO `dai2opengis`.`producto` (`idprod` ,`nombre` ,`descripcion` ,`nomtarea` ,`dosis`) VALUES ('"+ this.idprod +"', '" + this.nombre + "','" + this.descripcion +"','" + this.nomtarea +"','" + this.dosis + "')";
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Se ha dado de alta el nuevo producto");
		
		
	}
	
	//metodo para hacer la sentencia que oculta
	public void desactivarProducto() throws SQLException {

			String sentencia = "UPDATE producto SET `activo` = '0' , WHERE `idprod` = '"+ this.idprod + "'";
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Producto dado de baja correctamente");

	}
	
	


	

	
	
	
	
	
	
	
	
	
	
}
