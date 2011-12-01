package code.google.com.opengis.gestion;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ProductoDAO;


public class Producto {
	private int idprod;
	private String nombre;
	private String descripcion;
	private String nomtarea;
	private String dosis;
	private ProductoDAO x;
	private Boolean correcto;
//CONSTRUCTOR
	public Producto(int idprod, String nombre,String descripcion,String nomtarea, String dosis) {
		
	this.idprod=idprod;
	this.nombre=nombre;
	this.descripcion=descripcion;
	this.nomtarea=nomtarea;
	this.dosis=dosis;
	this.correcto = false;
	}
	
//   G E T T E R    &    S E T T E R
	//IDPRODUCTO
	public int getIdprod() {
		return idprod;
	}

	public void setIdprod(int idprod) {
		
			this.idprod = idprod;
	}
	//NOMBRE
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {

			this.nombre = nombre;
	}
	//DESCRIPCION
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		
			this.descripcion = descripcion;
	}
	
	//NomTarea
	public String getNomtarea() {
		return nomtarea;
	}

	public void setNomtarea(String nomtarea) {
			this.nomtarea = nomtarea;
	}
	//Dosis
	public String getDosis() {
		return nomtarea;
	}

	public void setDosis(String dosis) {
			this.dosis = dosis;
	}
//Datos correctos
	public Boolean getCorrecto(){
		
		return correcto;
		
	}
//Enlazar Producto con ProductoDAO, cadena de metodos.	
	public void crearProducto() {
	ProductoDAO	x = new ProductoDAO(this.idprod,this.nombre,this.descripcion,this.nomtarea,this.dosis);
		try {
			x.altaProducto();			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al dar de alta el nuevo producto");
		}
		
	}
//Valida datos	
public void validarDatos(){

		if(this.nombre.length() <1 || this.nombre.length()>40){ 
			
			JOptionPane.showMessageDialog(null, "Error. El nombre no puede estar vacio ni ser superior a 40 carácteres");
			this.correcto = false;
			
		}else{			
			
			Boolean r = isInteger(this.nombre);
			
			if(r.equals(true)){
				
				JOptionPane.showMessageDialog(null, "Error. El nombre no puede ser numérico");
				this.correcto = false;
				
			}else{
				
				r = isInteger(this.dosis);
				
				if(r.equals(false) || this.dosis.length() <1 || this.dosis.length()>3){
					
					JOptionPane.showMessageDialog(null, "Error. La dosis debe de ser numérica, no estar vacia ni ser superior a 3 carácteres.");
					this.correcto = false;
					
				}else{
					r = isInteger(this.descripcion);
					if(r.equals(true) || this.descripcion.length() <1  || this.descripcion.length()>1000){
						
						JOptionPane.showMessageDialog(null, "Error. La descripcion no puede ser numérica, estar vacia ni ser superior a 1000 carácteres.");
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
