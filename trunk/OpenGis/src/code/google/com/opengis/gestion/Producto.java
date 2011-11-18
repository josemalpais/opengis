package code.google.com.opengis.gestion;

import javax.swing.JOptionPane;

public class Producto {
	public int idprod;
	public String nombre;
	public String descripcion;
	public int idtarea;
	public String tipo;
	public boolean activo;
	
	
//CONSTRUCTOR
	public Producto(int idprod, String nombre,String descripcion,int idtarea, String tipo, boolean activo) {
		
	this.idprod=idprod;
	this.nombre=nombre;
	this.descripcion=descripcion;
	this.idtarea=idtarea;
	this.tipo=tipo;
	this.activo=activo;
	
	}
	
//   G E T T E R    &    S E T T E R
	//IDPRODUCTO
	public int getIdprod() {
		return idprod;
	}

	public void setIdprod(int idprod) {
		String str= idprod+"";
		if((str.length()>1)&&(str.length()<9)){ //comprobamos que idprod tenga entre 1 y 8 digitos
			this.idprod = idprod;
		}else{
			JOptionPane.showMessageDialog(null, "El ID del producto debe tener entre 1 y 8 dígitos");
		}
	}
	//NOMBRE
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		
		if(nombre.length() < 1 || nombre.length()>20){
			JOptionPane.showMessageDialog(null, "Error. El nombre debe estar comprendido entre 1 y 20 carácteres");			
		}else{
			this.nombre = nombre;
		}
	}
	//DESCRIPCION
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if(descripcion.length()>100||descripcion.length()<=0){
			JOptionPane.showMessageDialog(null, "La descripcion del producto debe constar de entre 1 y 100 carácteres alfanuméricos");
		}else{
			this.descripcion = descripcion;
		}
	}
	
	//IDTAREA 
	public int getIdtarea() {
		return idtarea;
	}

	public void setIdtarea(int idtarea) {
		String str= idtarea+"";
		if((str.length()>1)&&(str.length()<4)){
			JOptionPane.showMessageDialog(null, "El producto debe estar asignado a una tarea con id entre 1 y 3 caracteres");
		}else{
			this.idtarea = idtarea;
		}
	}
	//ACTIVO
	public boolean isActiv_Apero() {
		return activo;
	}

	public void setActiv_Apero(boolean activo) {
		this.activo = activo;
	}
	
	
	
}
