package code.google.com.opengis.gestion;

import javax.swing.JOptionPane;

public class Apero {
	public int idApero;
	public String nomApero;
	public double tamApero;
	public String descApero;
	public int idTarea_Apero;
	public boolean activ_Apero;
	public String idUser;
	
//CONSTRUCTOR
	public Apero(int id, String nombre,double tamaño,String descrip,int tarea, boolean activo) {
		
	} 
public Boolean validarDatos(String id, String nombre,String tamaño,String descrip,String tarea, String activo,String idUser){		
	
	if(idUser.length() != 9){ // El DNI del propietario debe tener el siguiente formato: ########-L
		JOptionPane.showMessageDialog(null, "El DNI del propietario debe tener el siguiente formato: ########-L");
		return false;
			
	}else{			
		Boolean r = isInteger(nombre);
		
		if(r.equals(true) || nombre.length() < 2){
			JOptionPane.showMessageDialog(null, "Error. El nombre del apero no puede ser numerico ni estar vacío");
			return false;	
		}else{
			if(!(esDecimal(tamaño))){
				JOptionPane.showMessageDialog(null, "Error. El tamaño del apero no puede ser alfabético o menor que 0");
			}
		}
	}
	
}
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
		    public boolean esDecimal(String cad){
		    	try{
		    		Double.parseDouble(cad);
		    		return true;
		    	}
		    	catch(NumberFormatException nfe){
		    		return false;
		    	}
		    }
}
