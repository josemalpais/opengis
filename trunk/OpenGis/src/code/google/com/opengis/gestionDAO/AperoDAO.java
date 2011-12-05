package code.google.com.opengis.gestionDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author kAStRo!
 * Clase que establece conexi�n con la base de datos y nos permite insertar, modificar y eliminar aperos.
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
	
	public AperoDAO(String id, String nombre,String tama�o,String descrip,String tarea, String activo,String idUser) {
		
	this.idApero=id;
	this.nomApero=nombre;
	this.tamApero=tama�o;
	this.descApero=descrip;
	this.idTarea_Apero=tarea;
	this.activ_Apero=activo;
	this.idUser=idUser;
	
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
			String sentencia = "INSERT INTO `dai2opengis`.`apero` (`idapero` ,`nombre` ,`tama�o` ,`descripcion` ,`idtarea` ,`activo` ,`dni_usuario`) VALUES ('"+ this.idApero +"', '" + this.nomApero  + "','" + this.tamApero +"','" + this.descApero+"','" + this.idTarea_Apero +"','" + this.activ_Apero+ "','" + this.idUser  +"')";
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
	 public static ResultSet buscarApero(String campo, String criterio) throws SQLException{
		 	ResultSet rs = null;
		 	ConectarDBA.acceder();
			String sentencia = "SELECT `idapero` ,`nombre` ,`tama�o` ,`descripcion` ,`idtarea` ,`activo` ,`dni_usuario` FROM `apero` WHERE  `"+campo+"` LIKE '%"+criterio+"%'";
			try{
			rs = dba.consulta(sentencia);
			}catch (SQLException e){
				System.out.println(e);
			}	
			return rs;
				
	 }
		public static void DesactivarApero(String id) throws SQLException{
			comprobarApero(id);
			if (existe == true){
				String sentencia = "UPDATE `apero` SET `activo` = '1' WHERE `idapero` LIKE '"+id+"'";
				dba.modificar(sentencia);
				JOptionPane.showMessageDialog(null,"Se ha desactivado el usuario");
			}
		}
		
		public void MoficicarApero() throws SQLException{
			comprobarApero(this.idApero);
			if (existe == true){
				//String sentencia = "UPDATE INTO `dai2opengis`.`usuario` (`dni` ,`nombre` ,`apellidos` ,`tel�fono` ,`direcci�n` ,`poblaci�n` ,`fecha_nacimiento`, `activo`) VALUES ('"+ this.Dni +"', '" + this.Nombre  + "','" + this.Apellidos +"','" + this.Telefono +"','" + this.Direccion +"','" + this.Poblacion + "','" + this.Fecha_nac  +"', '0')";
				String sentencia = "UPDATE `dai2opengis`.`apero` SET `nombre` = '"+this.nomApero+"', `tama�o` = '"+this.tamApero+"', `descripcion` = '"+this.descApero+"',`idtarea` = '"+this.idTarea_Apero+"',`activo` = '"+this.activ_Apero+"',`dni_usuario` = '"+this.idUser+"' WHERE `idapero` LIKE '"+this.idApero+"'";
				System.out.println("por ahora funciona");
				dba.modificar(sentencia);
				JOptionPane.showMessageDialog(null,"Se ha modificado el Apero");
				
				
			}else{
				
				JOptionPane.showMessageDialog(null,"El ID de Apero no existe");
				
			}
			dba.cerrarCon();
		}
		public static ResultSet buscarApero(String criterio)
				throws SQLException {
			ResultSet rs = null;
			ConectarDBA.acceder();
			String sentencia = "SELECT `idapero` ,`nombre` ,`tama�o` ,`descripcion` ,`idtarea` ,`activo` ,`dni_usuario` FROM `apero` WHERE idapero LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%' OR tama�o LIKE '%"+criterio+"%' OR descripcion LIKE '%"+criterio+"%' OR idtarea LIKE '%"+criterio+"%' OR dni_usuario LIKE '%"+criterio+"%'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
			try {
				rs = ConectarDBA.consulta(sentencia);
			} catch (SQLException e) {
				System.out.println(e);
			}
			return rs;

			// System.out.println("Ejecutada sentencia "+sentencia);

		}
}
