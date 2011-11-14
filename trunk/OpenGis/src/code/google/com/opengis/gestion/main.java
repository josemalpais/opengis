package code.google.com.opengis.gestion;

import java.sql.SQLException;


public class main {


	public static void main(String[] args) throws SQLException {
		
		
		
		/* Alta de Usuarios: Juan Carlos García. ¡¡¡¡¡ NO FUNCIONA EL MAIN !!!!!
		
		Usuarios a = new Usuarios("53762611-L","Juan Carlos","García del Canto","666673545","C/Albocasser 16,3","Torrent","04/05/1992");

		a.validarDatos();
		
		Boolean resp = a.getValido();
		
		if(resp==true){
			
			a.crearUsuario();
			
		}
		
		*/
		
		
		
		
		
		
		
		/*System.out.println("Soy Iván y estoy probando a insertar un nuevo dispositivo en la base de datos.");
		//Dispositivo disp1 = new Dispositivo("20503", "modelillo01", "00001", false, true);
		
		ConectarDBA dba = new ConectarDBA();
		dba.acceder();
		String sentencia = "INSERT INTO `dai2opengis`.`dispositivo` (`iddispositivo` ,`modelo` ,`num_serie` ,`disponible` ,`activo`) VALUES (`20202` ,`modellillo` ,`10001` ,`false` ,`true`)";
		dba.modificar(sentencia);
		sentencia = "SELECT * FROM dispositivo";
		ResultSet rs = dba.consulta(sentencia);
		while (rs.next()){
			System.out.println(rs.getString(1));
		}
		rs.close();
		dba.cerrarCon();
		*/
		
	
		//vp = new VentanaPrincipal();
		//Lo que está comentado es una consulta para probar la clase conexión. Att. Pepe.
		
		/***************************************
		ConectarDBA dba = new ConectarDBA();
		dba.acceder();
		String sentencia = "SELECT * FROM users";
		ResultSet rs = dba.consulta(sentencia);
		while (rs.next()){
			System.out.println(rs.getString(1));
		}
		rs.close();
		dba.cerrarCon();
		/**************************************/
		
		/*************************************
		ConectarDBA dba = new ConectarDBA();
		dba.acceder();
		String sentencia = "INSERT INTO `dai2opengis`.`usuario` (`dni` ,`nombre` ,`apellidos` ,`teléfono` ,`dirección` ,`población` ,`fecha_nacimiento`) VALUES ('53758355S', 'Jose Francisco', 'Lara Fortea', '659677695', 'Cami Reial 40 1', 'Catarroja', '1990-01-25')";
		dba.modificar(sentencia);
		dba.cerrarCon();
		/**************************************/
		//UsuariosDAO.borrarUsuario("53758352S");
		//UsuariosDAO.comprobarUsuario("53858355S");
	}
		

}

