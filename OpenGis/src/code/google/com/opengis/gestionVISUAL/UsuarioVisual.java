/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionVISUAL;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
* @author Juan Carlos García
* Clase que genera una ventana interior en el formulario MDI. Esta ventana es la encargada de gestionar los Usuarios.
*
*/

public class UsuarioVisual extends JInternalFrame {
	
	private JPanel panelUsuarios;
	
	/**
	 * Constructor de la clase UsuarioVisual. Se le pasarán los parametros necesarios para construir el alto y el ancho.
	 */

	public UsuarioVisual(int ancho, int alto){
		
		   panelUsuarios = new JPanel ();
		   panelUsuarios.setLayout(null);
		   this.add(panelUsuarios);
		   this.setBounds(0,0,ancho,alto);
		   this.setTitle("Usuarios");
		   nuevosObjetos();
		
		
	}
	
	
	/**
	 * Este método carga en el formulario los Objetos necesarios Ej: Cajas de Texto, Labels, etc...
	 * 
	 */
	
	public void nuevosObjetos(){
		
		
		
		
		
	}
	

}
