package code.google.com.opengis.gestion;

import javax.swing.JOptionPane;

/**
 * @author kAStRo!
 *Esta clase nos permite validar los datos de un Apero.
 *
 */


public class Apero {
	public int idApero;
	public String nomApero;
	public int tamApero;
	public String descApero;
	public int idTarea_Apero;
	public boolean activ_Apero;
	public String idUser;
	
////////////      C O N S T R U C T O R      ////////////////
	
	
	public Apero(int id, String nombre,int tamaño,String descrip,int tarea, boolean activo) {
		
	this.idApero=id;
	this.nomApero=nombre;
	this.tamApero=tamaño;
	this.descApero=descrip;
	this.idTarea_Apero=tarea;
	this.activ_Apero=activo;
	
	}

	
	
///////////   M E T O D O S    G E T T E R    A N D    S E T T E R    /////////
	
	//ID APERO
	public int getIdApero() {
		return idApero;
	}

	public void setIdApero(int idApero) {
		String str= idApero+"";
		if((str.length()>1)&&(str.length()<9)){ //comprobamos que idapero tenga entre 1 y 8 digitos
			this.idApero = idApero;
		}else{
			JOptionPane.showMessageDialog(null, "El ID del Apero debe tener entre 1 y 8 dígitos");
		}
	}
	
	//NOMBRE APERO
	public String getNomApero() {
		return nomApero;
	}

	public void setNomApero(String nomApero) {
		Boolean nm = isInteger(nomApero);
		if(nm.equals(true) || nomApero.length() < 2 || nomApero.length()>20){
			JOptionPane.showMessageDialog(null, "Error. El nombre del apero no puede ser numerico ni estar vacío");			
		}else{
			this.nomApero = nomApero;
		}
	}

	//TAMAÑO APERO
	public int getTamApero() {
		return tamApero;
	}

	public void setTamApero(int tamApero) {
		if(this.tamApero<=0||this.tamApero>15){
			JOptionPane.showMessageDialog(null, "Error. El tamaño del apero no puede ser alfabético o menor que 0");		
		}else{
			this.tamApero = tamApero;
		}
	}

	//DESCRIPCION APERO
	public String getDescApero() {
		return descApero;
	}

	public void setDescApero(String descApero) {
		if(descApero.length()>30||descApero.length()<=0){
			JOptionPane.showMessageDialog(null, "La descripcion del apero debe constar de entre 0 y 30 carácteres alfanuméricos");
		}else{
			this.descApero = descApero;
		}
	}

	//TAREA APERO
	public int getIdTarea_Apero() {
		return idTarea_Apero;
	}

	public void setIdTarea_Apero(int idTarea_Apero) {
		if(idTarea_Apero<1||idTarea_Apero>4){
			JOptionPane.showMessageDialog(null, "El apero debe estar asignado a una tarea con id entre 1 y 4");
		}else{
			this.idTarea_Apero = idTarea_Apero;
		}
	}

	//APERO ACTIVO
	public boolean isActiv_Apero() {
		return activ_Apero;
	}

	public void setActiv_Apero(boolean activ_Apero) {
		this.activ_Apero = activ_Apero;
	}

	//USUARIO APERO
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		if(idUser.length() != 9){ // El DNI del propietario debe tener el siguiente formato: ########L
			JOptionPane.showMessageDialog(null, "El DNI del propietario debe tener el siguiente formato: ########-L");
		}else{
			this.idUser = idUser;
		}
	}

	

/////////////    M E T O D O      D E     V A L I D A C I O N     //////////////////
	public Boolean validarDatos(String id, String nombre,String tamaño,String descrip,String tarea, String activo,String idUser){		
		
		if(idUser.length() != 9){ // El DNI del propietario debe tener el siguiente formato: ########-L
			JOptionPane.showMessageDialog(null, "El DNI del propietario debe tener el siguiente formato: ########-L");
			return false;
		}else{
			Boolean r = isInteger(nombre);
			
			if(r.equals(true) || nombre.length() < 2 || nombre.length()>20){
				JOptionPane.showMessageDialog(null, "Error. El nombre del apero no puede ser numerico ni estar vacío");
				return false;
				
			}else{
				int tam = Integer.parseInt(tamaño);
				
				if(!(isInteger(tamaño))||tam<=0||tam>15){
					JOptionPane.showMessageDialog(null, "Error. El tamaño del apero no puede ser alfabético o menor que 0");
					return false;
				
				}else{
					if(descrip.length()>30||descrip.length()<=0){
						JOptionPane.showMessageDialog(null, "La descripcion del apero debe constar de entre 0 y 30 carácteres alfanuméricos");
						return false;
					}else{
						int tar = Integer.parseInt(tarea);
						if(tar<1||tar>4){
							JOptionPane.showMessageDialog(null, "El apero debe estar asignado a una tarea");
							return false;
						}else{
							return true;
						}
					}
				}
			}
		}
	}
	
	
	
//////////////     M E T O D O S      A L T E R N A T I V O S       /////////////////
	
	public boolean isInteger( String input ){
		try{  
			Integer.parseInt( input );  
			return true;  
		}catch( Exception e2 ){  
			return false;  
		}  
	}
	

	public boolean esDecimal(String cad){
		try{
			Double.parseDouble(cad);
			return true;
		}catch(NumberFormatException nfe){
			return false;
		}
	}
}
