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
	private String tipo;
	private int activo;
	
//CONSTRUCTOR
	public ProductoDAO(int idprod, String nombre, String descripcion, String nomtarea, String tipo, int activo){
	
	this.idprod=idprod;
	this.nombre=nombre;
	this.descripcion=descripcion;
	this.nomtarea =nomtarea;
	this.tipo=tipo;
	this.activo=activo;
	}

//METODOS
	//metodo comprobar para modificar
	public static void comprobarProducto(int idprod) throws SQLException{		

		ConectarDBA.acceder();
		String sentencia = "SELECT * FROM `producto` WHERE `idprod` LIKE '"+ idprod +"'";
		ResultSet rs = dba.consulta(sentencia);
		while(rs.next()){
			resultado = rs.getString(1);
		}
		if(resultado == null){
			existe = false;//no existe el id buscado	
		}else if (resultado.equals(idprod)){
			existe = true;//existe el id buscado
		}
		rs.close();
	}
	
	//metodo para hacer la sentencia que crea productos
	public void altaProducto() throws SQLException{
		
		
			
			String sentencia = "INSERT INTO `dai2opengis`.`producto` (`idprod` ,`nombre` ,`descripcion` ,`nomtarea` ,`tipo` ,`activo`) VALUES ('"+ this.idprod +"', '" + this.nombre + "','" + this.descripcion +"','" + this.nomtarea +"','" + this.tipo +"','" + this.activo + "')";
			dba.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Se ha dado de alta el nuevo producto");
		
		
	}
	
	//metodo para hacer la sentencia que oculta
	public void borrarProducto() throws SQLException {
		comprobarProducto(this.idprod);
		if (existe == true) {

			String sentencia = "UPDATE producto SET `activo` = '0' , WHERE `idprod` = '"
					+ this.idprod + "'";
			dba.modificar(sentencia);

			JOptionPane.showMessageDialog(null,
					"Producto dado de baja correctamente");

		} else {

			JOptionPane.showMessageDialog(null, "El producto no existe");
		}

		dba.cerrarCon();

	}
	
	//metodos en pruebas, generacion automática de código.


	
////////////////////////
	
	
	
	
	
	
	
	
	
	
}
