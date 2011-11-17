package code.google.com.opengis.gestionDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author kAStRo!
 * Clase que establece conexión con la base de datos y nos permite insertar, modificar y eliminar aperos.
 *
 */

public class AperoDAO {
	
	static boolean existe;
	static String resultado;
	static ConectarDBA dba = new ConectarDBA();
	
	public String idApero;
	public String nomApero;
	public String tamApero;
	public String descApero;
	public String idTarea_Apero;
	public String activ_Apero;
	public String idUser;
	
	
	
//////////////      C O N S T R U C T O R      ////////////////
	
	public AperoDAO(String id, String nombre,String tamaño,String descrip,String tarea, String activo) {
		
	this.idApero=id;
	this.nomApero=nombre;
	this.tamApero=tamaño;
	this.descApero=descrip;
	this.idTarea_Apero=tarea;
	this.activ_Apero=activo;
	
	}
	
	
	
//////////////        M E T O D O S      G E S T I O N     D E      B B D D        /////////////////
	
	
	//	Este metodo se encarga de comprobar si un apero ya existe en nuestra base de datos
	//	comparando la id de dicho apero con los de la base de datos
	
	public static void comprobarApero(String id) throws SQLException{		

		ConectarDBA.acceder();
		String sentencia = "SELECT * FROM `apero` WHERE `idapero` LIKE '"+id+"'";
		ResultSet rs = dba.consulta(sentencia);
		while(rs.next()){
			System.out.println("Ejecuto el while");
			resultado = rs.getString(1);
		}
		System.out.println("Enviado: "+id+" esperado: "+resultado);
		if(resultado == null){
			existe = false;
			System.out.println("El estado de existe es: "+existe);
		}else if (resultado.equals(id)){
			existe = true;
			System.out.println("El estado de existe es: "+existe);
		}
		rs.close();
	}
	
	
	//	Este metodo se encarga de dar de alta un nuevo apero
	//	comprobando antes si existe en la base de datos
	public void altaApero() throws SQLException{
		comprobarApero(this.idApero);
		if (existe == true){ 
			JOptionPane.showMessageDialog(null,"El Apero ya existe");
		}else{
			String sentencia = "INSERT INTO `dai2opengis`.`usuario` (`idapero` ,`nombre` ,`tamaño` ,`descripcion` ,`idtarea` ,`activo` ,`dni_usuario`) VALUES ('"+ this.idApero +"', '" + this.nomApero  + "','" + this.tamApero +"','" + this.descApero+"','" + this.idTarea_Apero +"','" + this.activ_Apero+ "','" + this.idUser  +"')";
			dba.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Se ha dado de alta el nuevo apero");
		}
		dba.cerrarCon();
	}
	

	//	Este metodo se encarga de dar de baja un nuevo apero
	//	comprobando antes si esta creado en la base de datos
	public void borrarApero() throws SQLException{
		comprobarApero(this.idApero);
		if (existe == true){ 
			String sentencia = "DELETE FROM `apero` WHERE `idapero` = '"+this.idApero+"'";
			dba.modificar(sentencia);
			JOptionPane.showMessageDialog(null,"Apero eliminado correctamente");
		}else{		
			JOptionPane.showMessageDialog(null,"El usuario no existe");
		}
		dba.cerrarCon();	
	}
}
