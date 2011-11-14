
/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestion;
import javax.swing.JOptionPane;

/**
* @author Juan Carlos García
* Clase que valida los datos de los usuarios que acabamos de insertar desde nuestra clase visual.
* En el caso de que algún dato sean incorrecto mostrará un error de forma visual y se dentendrá la ejecución.
* 
* Última modificación 14/11/11
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
	
	
	public Usuarios(String Dni,String Nombre,String Apellidos,String Telefono,String Direccion,String Poblacion, String fecha_nac){
		
		this.Dni = Dni;
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.Telefono = Telefono;
		this.Direccion = Direccion;
		this.Poblacion = Poblacion;
		this.Fecha_nac = fecha_nac;
		
	}
	
	public void validarDatos(String dni, String nombre, String apellidos, String telefono, String direccion, String poblacion, String fec_nac){
		
		
		if(dni.length() != 9){ // El DNI tiene que tener 9 caracteres
			
			JOptionPane.showMessageDialog(null, "Error. El DNI solo puede contener 9 caracteres");
			return false;
			
		}else{			
			
			Boolean r = isInteger(nombre);
			
			if(r.equals(true) || nombre.length() < 2){
				
				JOptionPane.showMessageDialog(null, "Error. El nombre no puede ser numerico ni estar vacío");
				return false;
				
			}else{
				
				r = isInteger(apellidos);
				
				if(r.equals(true) || apellidos.length() < 2){
					
					JOptionPane.showMessageDialog(null, "Error. Los apellidos no pueden ser numericos ni estar vacíos");
					return false;
					
					
				}else{
					
					if(telefono.length() != 9 ){
						
						JOptionPane.showMessageDialog(null, "Error. El número de telefono tiene que tener 9 dígitos");
						return false;
						
						
					}else{
						
						r = isInteger(direccion);
						
						if(r.equals(true) || direccion.length() < 2){
							
							JOptionPane.showMessageDialog(null, "Error. La dirección no puede ser numerica ni estar vacia");
							return false;
							
							
						}else{
							
							r = isInteger(poblacion);
							
							if(r.equals(true) || poblacion.length() < 2){
								
								JOptionPane.showMessageDialog(null, "Error. La población no puede ser numerica ni estar vacia");
								return false;
								
							}else{
								
								if(fec_nac.length() != 10){
									
									JOptionPane.showMessageDialog(null, "Error. El formato de la fecha es incorrecto");
									return false;
									
									
								}else{
									
									
									return true; // En el caso de que todos los datos sean correctos devolveremos True
									
									
								}
								
								
							}

						}
						
						
					}
					
					
				}
				
				
				
			}
			
			
		}

	}
	
	
	
	/* Este método comprueba si una cadena String introducida es un Número. Si es un número,
	 * devuelve true. 
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
