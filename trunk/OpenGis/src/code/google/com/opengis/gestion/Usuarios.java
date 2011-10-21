package code.google.com.opengis.gestion;

import javax.swing.JOptionPane;

public class Usuarios {
	
	
	/* Recogemos todos los datos que hayamos introducido desde nuestra Interfaz y comprobamos
	 * que est� todo correctamente. En el caso de que todo est� correcto subiremos a la base de
	 * datos nuestro nuevo Usuario.
	*/
	
	public Boolean validarDatos(String dni, String nombre, String apellidos, String telefono, String direccion, String poblacion, String fec_nac){
		
		
		if(dni.length() != 9){ // El DNI tiene que tener 9 caracteres
			
			JOptionPane.showMessageDialog(null, "Error. El DNI solo puede contener 9 caracteres");
			return false;
			
		}else{			
			
			Boolean r = isInteger(nombre);
			
			if(r.equals(true) || nombre.length() < 2){
				
				JOptionPane.showMessageDialog(null, "Error. El nombre no puede ser numerico ni estar vac�o");
				return false;
				
			}else{
				
				r = isInteger(apellidos);
				
				if(r.equals(true) || apellidos.length() < 2){
					
					JOptionPane.showMessageDialog(null, "Error. Los apellidos no pueden ser numericos ni estar vac�os");
					return false;
					
					
				}else{
					
					if(telefono.length() != 9 ){
						
						JOptionPane.showMessageDialog(null, "Error. El n�mero de telefono tiene que tener 9 d�gitos");
						return false;
						
						
					}else{
						
						r = isInteger(direccion);
						
						if(r.equals(true) || direccion.length() < 2){
							
							JOptionPane.showMessageDialog(null, "Error. La direcci�n no puede ser numerica ni estar vacia");
							return false;
							
							
						}else{
							
							r = isInteger(poblacion);
							
							if(r.equals(true) || poblacion.length() < 2){
								
								JOptionPane.showMessageDialog(null, "Error. La poblaci�n no puede ser numerica ni estar vacia");
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
	
	
	
	/* Este m�todo comprueba si una cadena String introducida es un N�mero. Si es un n�mero,
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
