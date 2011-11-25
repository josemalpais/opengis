package code.google.com.opengis.gestion;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ProductoDAO;
import code.google.com.opengis.gestionDAO.UsuariosDAO;

public class Producto {
	private String idprod;
	private String nombre;
	private String descripcion;
	private String nomtarea;
	private String tipo;
	private boolean activo;
	private ProductoDAO x;
	private Boolean correcto;
//CONSTRUCTOR
	public Producto(String idprod, String nombre,String descripcion,String nomtarea, String tipo, boolean activo) {
		
	this.idprod=idprod;
	this.nombre=nombre;
	this.descripcion=descripcion;
	this.nomtarea=nomtarea;
	this.tipo=tipo;
	this.activo=activo;
	this.correcto = false;
	}
	
//   G E T T E R    &    S E T T E R
	//IDPRODUCTO
	public String getIdprod() {
		return idprod;
	}

	public void setIdprod(String idprod) {
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
	public String getNomtarea() {
		return nomtarea;
	}

	public void setNomtarea(String nomtarea) {
		String str= nomtarea+"";
		if((str.length()>1)&&(str.length()<4)){
			JOptionPane.showMessageDialog(null, "El producto debe estar asignado a una tarea con id entre 1 y 3 caracteres");
		}else{
			this.nomtarea = nomtarea;
		}
	}
	//ACTIVO
	public boolean isActiv_Apero() {
		return activo;
	}

	public void setActiv_Apero(boolean activo) {
		this.activo = activo;
	}
//Datos correctos
	public Boolean getCorrecto(){
		
		return correcto;
		
	}
//Enlazar Producto con ProductoDAO, cadena de metodos.	
	public void crearProducto() {
		x = new ProductoDAO(this.idprod,this.nombre,this.descripcion,this.nomtarea,this.tipo,this.activo);
		try {
			
			x.altaProducto();
			
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null,"Error al dar de alta el nuevo producto");
		}
		
	}
//Valida datos	
public void validarDatos(){
		
		
		if(this.nombre.length() <1){ 
			
			JOptionPane.showMessageDialog(null, "Error. El nombre no puede estar vacio ");
			this.correcto = false;
			
		}else{			
			
			Boolean r = isInteger(this.nombre);
			
			if(r.equals(true) || this.nombre.length() < 2){
				
				JOptionPane.showMessageDialog(null, "Error. El nombre no puede ser numerico");
				this.correcto = false;
				
			}else{
				
				r = isInteger(this.tipo);
				
				if(r.equals(true) || this.tipo.length() < 2){
					
					JOptionPane.showMessageDialog(null, "Error. El tipo no pueden ser numerico ni estar vacio");
					this.correcto = false;
					
				}else{
					r = isInteger(this.descripcion);
					if(r.equals(true) || this.descripcion.length() <2  ){
						
						JOptionPane.showMessageDialog(null, "Error. La descripcion no puede ser numerica ni estar vacia");
						this.correcto = false;
						
					}else{
						
					    this.correcto = true; // En el caso de que todos los datos sean correctos devolveremos True
												
							}			
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
	
	
}
