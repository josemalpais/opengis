
/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestion;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.UsuariosDAO;

/**
* @author Juan Carlos Garc�a
* Clase que valida los datos de los usuarios que acabamos de insertar desde nuestra clase visual. Adem�s, realiza las llamadas
* a los m�todos del paquete DAO.
* 
* �ltima modificaci�n 14/11/11
* 
*/

public class Usuarios {
	
	private String Dni;
	private String Nombre;
	private String Apellidos;
	private String Telefono;
	private String Direccion;
	private String Poblacion;
	private String Fecha_nac;
	private Boolean valido;
	private UsuariosDAO enlace;
	
	
	/** Constructor de la clase Usuarios
	 * 
	 * En el momento que llamamos a la clase Usuario debemos pasarle los siguientes parametros
	 * 
	 * @param Dni El DNI del usuario que vamos a introducir
	 * @param Nombre El Nombre del Usuario que vamos a introducir
	 * @param Apellidos Los Apellidos del Usuario que vamos a introducir
	 * @param Telefono El Telefono del usuario que vamos a introducir
	 * @param Direccion La direcci�n del Usuario que vamos a introducir
	 * @param Poblacion La poblaci�n del Usuario que vamos a introducir
	 * @param fecha_nac La fecha de Nacimiento del Usuario que vamos a introducir
	 */
	
	public Usuarios(String Dni,String Nombre,String Apellidos,String Telefono,String Direccion,String Poblacion, String fecha_nac){
		
		this.Dni = Dni;
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.Telefono = Telefono;
		this.Direccion = Direccion;
		this.Poblacion = Poblacion;
		this.Fecha_nac = fecha_nac;
		this.valido = false;
		
	}
	
	/**
	 * 	Getter de la clase Usuario.
	 * 
	 * @return Devuelve True si los datos son correctos. Devuelve False si los datos son incorrectos.
	 */
	
	
	public Boolean getValido(){
		
		return valido;
		
	}
	
	
	/**
	 * Este m�todo validar� los datos que el usuario introduzca en la clase visual. En el caso de que alg�n dato no sea correcto
	 * mostrar� un mensaje de error y cargar� valido con False.
	 * A la hora de llamar los siguientes m�todos comprobaremos si este nos ha dado FALSE, en ese caso no podremos hacer nada 
	 * hasta que no corrijamos el error.
	 * 
	 */

	
	
	public void validarDatos(){
		
		
		if(this.Dni.length() != 9){ // El DNI tiene que tener 9 caracteres
			
			JOptionPane.showMessageDialog(null, "Error. El DNI solo puede contener 9 caracteres");
			this.valido = false;
			
		}else{			
			
			Boolean r = isInteger(this.Nombre);
			
			if(r.equals(true) || this.Nombre.length() < 2){
				
				JOptionPane.showMessageDialog(null, "Error. El nombre no puede ser numerico ni estar vac�o");
				this.valido = false;
				
			}else{
				
				r = isInteger(this.Apellidos);
				
				if(r.equals(true) || this.Apellidos.length() < 2){
					
					JOptionPane.showMessageDialog(null, "Error. Los apellidos no pueden ser numericos ni estar vac�os");
					this.valido = false;
					
				}else{
					
					if(this.Telefono.length() != 9 ){
						
						JOptionPane.showMessageDialog(null, "Error. El n�mero de telefono tiene que tener 9 d�gitos");
						this.valido = false;
						
					}else{
						
						r = isInteger(this.Direccion);
						
						if(r.equals(true) || this.Direccion.length() < 2){
							
							JOptionPane.showMessageDialog(null, "Error. La direcci�n no puede ser num�rica ni estar vac�a");
							this.valido = false;
							
						}else{
							
							r = isInteger(this.Poblacion);
							
							if(r.equals(true) || this.Poblacion.length() < 2){
								
								JOptionPane.showMessageDialog(null, "Error. La poblaci�n no puede ser numerica ni estar vacia");
								this.valido = false;
								
							}else{
								
								if(this.Fecha_nac.length() != 10){
									
									JOptionPane.showMessageDialog(null, "Error. El formato de la fecha es incorrecto");
									this.valido = false;
									
								}else{
									
									
									this.valido = true; // En el caso de que todos los datos sean correctos devolveremos True
									
									
								}
								
								
							}

						}
						
						
					}
					
					
				}
				
				
				
			}
			
			
		}

	}
	
	/**
	 * Este m�todo utiliza la clase UsuariosDAO, creando un objeto del mismo con los parametros indicados en su constructor.
	 * Despu�s, ejecuta el m�todo crear Usuario, definido en la clase UsuariosDAO.
	 * 
	 */
	
	
	public void crearUsuario(){
		

		enlace = new UsuariosDAO(this.Dni,this.Nombre,this.Apellidos,this.Telefono,this.Direccion,this.Poblacion,this.Fecha_nac);
		try {
			
			enlace.altaUsuario();
			
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null,"Error al dar de alta el nuevo usuario");
		}
		
	}
	
	/**
	 * Este m�todo utiliza la clase UsuariosDAO, creando un objeto del mismo con los parametros indicados en su constructor.
	 * Despu�s, ejecuta el m�todo borrar Usuario, definido en la clase UsuariosDAO.
	 * 
	 */
	
	
	public void borrarUsuario(){
		

		enlace = new UsuariosDAO(this.Dni,this.Nombre,this.Apellidos,this.Telefono,this.Direccion,this.Poblacion,this.Fecha_nac);
		
		try {
			
			enlace.borrarUsuario();
			
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null,"Error al eliminar el nuevo usuario");
		}
		
	}
	
	public void modificarUsuario(){
		enlace = new UsuariosDAO(this.Dni,this.Nombre,this.Apellidos,this.Telefono,this.Direccion,this.Poblacion,this.Fecha_nac);
		try {
			
			enlace.MoficicarUsuario();
			
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null,"Error al eliminar el nuevo usuario");
		}
			
		}
		
	
	
	
	
	/**
	 * Este m�todo comprueba si una cadena String introducida es un N�mero. Si es un n�mero,
	 * devuelve true. 
	 * 
	 * @param input Cadena de texto que queremos comprobar si es un n�mero
	 * @return Devolvemos True si es un n�mero, devolvemos False si no lo �s.
	 * 
	 */
	
    public boolean isInteger( String input )  
    {  
       try  
       {  
          Integer.parseInt( input );  
          return true;  
       }  
       catch( Exception e2 )  
       {  
          return false;  
       }  
       
    }  

}
