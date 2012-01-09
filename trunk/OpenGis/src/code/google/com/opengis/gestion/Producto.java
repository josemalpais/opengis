package code.google.com.opengis.gestion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;



public class Producto {
	private int idprod;
	private String nombre;
	private String descripcion;
	private String nomtarea;
	private String dosis;
	private String dni;
	private int activo;
	private Producto x;
	private Boolean correcto;
	
//CONSTRUCTOR
	public Producto(int idprod, String nombre,String descripcion,String nomtarea, String dosis, String dni, int activo) {
		
	this.idprod=idprod;
	this.nombre=nombre;
	this.descripcion=descripcion;
	this.nomtarea=nomtarea;
	this.dosis=dosis;
	this.dni=dni;
	this.activo=activo;
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
	//Dni
	public String getDni(){
		return dni;
	}
	public void setDni(String dni){
		this.dni=dni;
	}
	//activo
	public int getActivo(){
		return activo;
	}
	public void setActivo(int activo){
		this.activo=activo;
	}
//Datos correctos
	public Boolean getCorrecto(){
		
		return correcto;
		
	}
//Enlazar Producto con ProductoDAO, cadena de metodos.	
	public void crearProducto() {
	Producto	x = new Producto(this.idprod,this.nombre,this.descripcion,this.nomtarea,this.dosis, this.dni, this.activo);
		try {
			x.altaProducto();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,Idioma.getString("msgErrorNewProduct")); 
		}
		
	}
	public void altaProducto() throws SQLException{

		ConectarDBA.acceder();
		String sentencia = "INSERT INTO `dai2opengis`.`producto` (`idprod` ,`nombre` ,`descripcion` ,`nomtarea` ,`dosis`, `dni`, `activo`) VALUES ('"+ this.idprod +"', '" + this.nombre + "','" + this.descripcion +"','" + this.nomtarea +"','" + this.dosis + "','"+this.dni+"',"+this.activo+")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
		ConectarDBA.modificar(sentencia);
		ConectarDBA.cerrarCon();
		JOptionPane.showMessageDialog(null,Idioma.getString("msgNewProductAdded")); 
	
	
}
//modif
	
	public void editarProducto() {
		Producto	x = new Producto(this.idprod,this.nombre,this.descripcion,this.nomtarea,this.dosis, this.dni, this.activo);
			try {
				x.modificarProducto();			
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,Idioma.getString("msgErrorNewProduct"));
			}
			
		}
	public void modificarProducto() throws SQLException{
		
		ConectarDBA.acceder();
		String sentencia = "UPDATE `producto` SET `nombre`='"+ this.nombre +"',`descripcion`='"+ this.descripcion +"',`nomtarea`='"+ this.nomtarea +"',`dosis`='"+ this.dosis +"',`dni`='"+this.dni+"', `activo`='"+this.activo+"' WHERE `idprod` = '"+ this.idprod + "'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
		ConectarDBA.modificar(sentencia);	
		ConectarDBA.cerrarCon();
		JOptionPane.showMessageDialog(null,Idioma.getString("msgProductModSuccess")); 
		
	}
//bajas 
/*	public void bajasProducto(){
		ProductoDAO	x = new ProductoDAO(this.idprod,this.nombre,this.descripcion,this.nomtarea,this.dosis, this.dni, this.activo);
		try {
			x.desactivarProducto();			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al dar de alta el nuevo producto");
		}
	}*/
	
	//metodo para desactivar un producto
	public static void desactivarProducto(String id) throws SQLException {

			String sentencia = "UPDATE producto SET `activo` = '1' WHERE `idprod` = '"+ id + "'"; //$NON-NLS-1$ //$NON-NLS-2$
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,Idioma.getString("msgProductInactive")); //$NON-NLS-1$

	}
	//metodo para activar un producto
	public static void activarProducto(String id) throws SQLException {

			String sentencia = "UPDATE producto SET `activo` = '0'  WHERE `idprod` = '"+id+"'"; //$NON-NLS-1$ //$NON-NLS-2$
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,Idioma.getString("msgProductEnabled")); //$NON-NLS-1$

	}
	
	public static ResultSet buscar(String criterio) {
		
		ConectarDBA.acceder();
        ResultSet rs = null;
        String sql = "SELECT * FROM `producto` WHERE idprod LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%' OR descripcion LIKE '%"+criterio+"%' OR nomtarea LIKE '%"+criterio+"%' OR dosis LIKE '%"+criterio+"%' OR dni LIKE '%"+criterio+"%'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

        try {
        	
           rs = ConectarDBA.consulta(sql);
           
        } catch (SQLException e) {
            System.out.println(e);
        } 
        
        return rs;

    }

	
//Valida datos	
/*public void validarDatos(){

		if(this.nombre.length() <1 || this.nombre.length()>40){ 
			
			JOptionPane.showMessageDialog(null, Idioma.getString("msgProductNameNotNull")); //$NON-NLS-1$
			this.correcto = false;
			
		}else{			
			
			Boolean r = isInteger(this.nombre);
			
			if(r.equals(true)){
				
				JOptionPane.showMessageDialog(null, Idioma.getString("msgProductNameNotNumeric")); //$NON-NLS-1$
				this.correcto = false;
				
			}else{
				
				r = isInteger(this.dosis);
				
				if(r.equals(false) || this.dosis.length() <1 || this.dosis.length()>4){
					
					JOptionPane.showMessageDialog(null, Idioma.getString("msgProductDoseMustNumeric")); //$NON-NLS-1$
					this.correcto = false;
					
				}else{
					r = isInteger(this.descripcion);
					if(r.equals(true) || this.descripcion.length() <1  || this.descripcion.length()>100){
						
						JOptionPane.showMessageDialog(null, Idioma.getString("msgDescNotNull")); //$NON-NLS-1$
						this.correcto = false;
						
					}else{
						

						
					    this.correcto = true; // En el caso de que todos los datos sean correctos devolveremos True
												
							}			
						}
					}
			}
	}*/
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
public boolean validarTexto(String texto, String nombreCampo) {
	Boolean r = isInteger(texto);

	for (int i = 0; i < texto.length(); i++) {
		if (Character.isLetter(texto.charAt(i)) == false
				&& texto.charAt(i) != (' ')) {
			JOptionPane.showMessageDialog(
					null,
					Idioma.getString("msgErrorField") 
							+ nombreCampo
							+ Idioma.getString("msgErrorNotSpecialChar"));
			this.correcto = false;

			return false;

		}
		if (texto.charAt(i) == ' ' && texto.charAt(i - 1) == ' ') {

			JOptionPane.showMessageDialog(null,Idioma.getString("msgErrorField") + nombreCampo
							+ Idioma.getString("msgErrorBlankSpace")); 
			return false;
		}
	}

	if (r.equals(true) || texto.length() < 2) {
		JOptionPane.showMessageDialog(
				null,
				Idioma.getString("msgErrorField")
						+ nombreCampo
						+ Idioma.getString("msgErrorEmptyNorNumeric")); 
		this.correcto = false;

		return false;

	} else {

		return true;

	}
}

public boolean validarTextoEspecial(String texto, String nombreCampo) {
	Boolean r = isInteger(texto);

	for (int i = 0; i < texto.length(); i++) {

		if (texto.charAt(i) == ' ' && texto.charAt(i - 1) == ' ') {

			JOptionPane.showMessageDialog(
					null,
					Idioma.getString("msgErrorField")
							+ nombreCampo
							+ Idioma.getString("msgErrorBlankSpace")); 
			return false;
		}
	}

	if (r.equals(true) || texto.length() < 2) {
		JOptionPane.showMessageDialog(
				null,
				Idioma.getString("msgErrorField") 
						+ nombreCampo
						+ Idioma.getString("msgErrorEmptyNorNumeric")); 
		this.correcto = false;

		return false;

	} else {

		return true;

	}

}

public void validarDatos() {
	this.correcto = true;
	
	Boolean r = isInteger(this.nombre);
		if (validarTexto(this.nombre, Idioma.getString("etFirstName")) == false) { 
			this.correcto = false;
		} else {

			if (validarTextoEspecial(this.descripcion, Idioma.getString("etDescription")) == false) { 
				this.correcto = false;
			} else {	
				r = isInteger(this.dosis);

				if (r.equals(false) || this.dosis.length() <1 || this.dosis.length()>3) {
						JOptionPane.showMessageDialog(null, Idioma.getString("msgDoseNotNullOrTooLong")); 					
				}else{
					r = isInteger(this.descripcion);
					if(r.equals(true) || this.descripcion.length() <1  || this.descripcion.length()>100){
						
						JOptionPane.showMessageDialog(null, Idioma.getString("msgDescNotNullOrTooLong")); 
						this.correcto = false;
						
					}else{

										this.correcto = true; 

									}
					}
				}
			}

	}
}


	

