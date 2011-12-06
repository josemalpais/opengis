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
		String sentencia = "UPDATE `producto` SET `nombre`='"+ this.nombre +"',`descripcion`='"+ this.descripcion +"',`nomtarea`='"+ this.nomtarea +"',`dosis`='"+ this.dosis +"',`dni`='"+this.dni+"', `activo`='"+this.activo+"' WHERE `idprod` = '"+ this.idprod + "'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
		ConectarDBA.modificar(sentencia);	
		ConectarDBA.cerrarCon();
		JOptionPane.showMessageDialog(null,Idioma.getString("msgProductModSuccess")); //$NON-NLS-1$
		
	}
	
	//metodo para hacer la sentencia que crea productos
	public void altaProducto() throws SQLException{

			ConectarDBA.acceder();
			String sentencia = "INSERT INTO `dai2opengis`.`producto` (`idprod` ,`nombre` ,`descripcion` ,`nomtarea` ,`dosis`, `dni`, `activo`) VALUES ('"+ this.idprod +"', '" + this.nombre + "','" + this.descripcion +"','" + this.nomtarea +"','" + this.dosis + "','"+this.dni+"',"+this.activo+")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			ConectarDBA.modificar(sentencia);
			ConectarDBA.cerrarCon();
			JOptionPane.showMessageDialog(null,Idioma.getString("msgNewProductAdded")); //$NON-NLS-1$
		
		
	}
	
	//metodo para desactivar un producto
	public static void desactivarProducto(String id) throws SQLException {

			String sentencia = "UPDATE producto SET `activo` = '1' WHERE `idprod` = '"+ id + "'"; //$NON-NLS-1$ //$NON-NLS-2$
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,Idioma.getString("msgProductInactive")); //$NON-NLS-1$

	}
	//metodo para activar un producto
	public static void activarProducto(String id) throws SQLException {

			String sentencia = "UPDATE producto SET `activo` = '0'  WHERE `idprod` = '"+id+"'"; //$NON-NLS-1$ //$NON-NLS-2$
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,Idioma.getString("msgProductEnabled")); //$NON-NLS-1$

	}
	
	public static ResultSet buscar(String criterio) {
		
		ConectarDBA.acceder();
        ResultSet rs = null;
        String sql = "SELECT * FROM `producto` WHERE idprod LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%' OR descripcion LIKE '%"+criterio+"%' OR nomtarea LIKE '%"+criterio+"%' OR dosis LIKE '%"+criterio+"%' OR dni LIKE '%"+criterio+"%'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

        try {
        	
           rs = ConectarDBA.consulta(sql);
           
        } catch (SQLException e) {
            System.out.println(e);
        } 
        
        return rs;

    }

	

	
	
	
	
	
	
	
	
	
	
}
