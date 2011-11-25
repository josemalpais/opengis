package code.google.com.opengis.gestionDAO;

import java.sql.*;

import javax.swing.JOptionPane;

public class ProductoDAO {
	static boolean existe;
	static String resultado;
	static ConectarDBA dba = new ConectarDBA();
	
	public String idprod;
	public String nombre;
	public String descripcion;
	public String nomtarea;
	public String tipo;
	public boolean activo;
	
//CONSTRUCTOR
	public ProductoDAO(String idprod, String nombre, String descripcion, String nomtarea, String tipo, boolean activo){
	
	this.idprod=idprod;
	this.nombre=nombre;
	this.descripcion=descripcion;
	this.nomtarea =nomtarea;
	this.tipo=tipo;
	this.activo=activo;
	}

//METODOS
	public static void comprobarProducto(String idprod) throws SQLException{		

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
	
	
	public void altaProducto() throws SQLException{
		comprobarProducto(this.idprod);
		if (existe == true){ 
			JOptionPane.showMessageDialog(null,"El producto ya existe");
		}else{
			String sentencia = "INSERT INTO `dai2opengis`.`producto` (`idprod` ,`nombre` ,`descripcion` ,`nomtarea` ,`tipo` ,`activo`) VALUES ('"+ this.idprod +"', '" + this.nombre + "','" + this.descripcion +"','" + this.nomtarea +"','" + this.tipo +"','" + this.activo + "')";
			dba.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Se ha dado de alta el nuevo producto");
		}
		dba.cerrarCon();
	}
	
	
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
	
	
    public static String genCodigo() {
        ResultSet rs = buscar(" idprod", "idprod LIKE '%' ORDER BY idprod");
        String idprod = "";
        try {
            rs.last();
            idprod = rs.getString(1).substring(1);
            int num=Integer.parseInt(idprod);
            num++;
            idprod="P"+num;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return idprod;
        }
    }
    public static  ResultSet buscar(String salida, String criterio) {
    	ConectarDBA.acceder();
    	ResultSet rs = null;
        String sentencia = "SELECT " + salida + " FROM producto WHERE " + criterio;
       
        try {
           rs = dba.consulta(sentencia);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            return rs;
        }

    }
	
	
	
	
	
	
	
	
	
	
	
	
}
