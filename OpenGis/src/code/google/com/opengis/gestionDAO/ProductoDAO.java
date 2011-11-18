package code.google.com.opengis.gestionDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ProductoDAO {
	static boolean existe;
	static String resultado;
	static ConectarDBA dba = new ConectarDBA();
	
	public int idprod;
	public String nombre;
	public String descripcion;
	public int idtarea;
	public String tipo;
	public boolean activo;
	
//CONSTRUCTOR
	public ProductoDAO(int idprod, String nombre, String descripcion, int idtarea, String tipo, boolean activo){
	
	this.idprod=idprod;
	this.nombre=nombre;
	this.descripcion=descripcion;
	this.idtarea=idtarea;
	this.tipo=tipo;
	this.activo=activo;
	}

//METODOS
	public static void ComprobarProducto(int idprod) throws SQLException{		

		ConectarDBA.acceder();
		String sentencia = "SELECT * FROM `producto` WHERE `idprod` LIKE '"+idprod+"'";
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
	
	
	public void AltaProducto() throws SQLException{
		ComprobarProducto(this.idprod);
		if (existe == true){ 
			JOptionPane.showMessageDialog(null,"El producto ya existe");
		}else{
			String sentencia = "INSERT INTO `dai2opengis`.`usuario` (`idprod` ,`nombre` ,`descripcion` ,`idtarea` ,`tipo` ,`activo`) VALUES ('"+ this.idprod +"', '" + this.nombre + "','" + this.descripcion +"','" + this.idtarea +"','" + this.tipo +"','" + this.activo + "')";
			dba.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Se ha dado de alta el nuevo producto");
		}
		dba.cerrarCon();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
