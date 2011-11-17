/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionVISUAL;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
* @author Juan Carlos Garc�a
* Clase que genera una ventana interior en el formulario MDI. Esta ventana es la encargada de gestionar los Usuarios.
*
*/

public class UsuarioVisual extends JInternalFrame {
	
	private JPanel panelUsuarios;
	
	private JButton cmdNuevoUsuario;
	private JButton cmdBajaUsuario;
	private JButton cmdModificarUsuario;
	private JButton cmdGuardarUsuario;
	private JButton cmdBuscarUsuario;
	
	private JLabel lblDNI;
	private JLabel lblEntrada;
	
	private JTextField txtDNI;
	
	/**
	 * Constructor de la clase UsuarioVisual. Se le pasar�n los parametros necesarios para construir el alto y el ancho.
	 */

	public UsuarioVisual(int ancho, int alto){
		
		   panelUsuarios = new JPanel ();
		   panelUsuarios.setLayout(null);
		   this.add(panelUsuarios);
		   this.setBounds(0,0,ancho,alto);
		   this.setTitle("Usuario");
		   this.setClosable(true);
		   
		   nuevosObjetos();
		
		
	}
	
	
	/**
	 * Este m�todo carga en el formulario los Objetos necesarios Ej: Cajas de Texto, Labels, etc...
	 * 
	 */
	
	public void nuevosObjetos(){
		
		lblEntrada = new JLabel("Desde esta secci�n puede gestionar los Usuarios que utilizar�n nuestro sistema OpenGis");
		lblEntrada.setVisible(true);
		lblEntrada.setBounds(425,25, 800, 50);
		panelUsuarios.add(lblEntrada);
		
		lblDNI = new JLabel("DNI:");
		lblDNI.setVisible(true);
		lblDNI.setBounds(500,100,100,25);
		panelUsuarios.add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setVisible(true);
		txtDNI.setBounds(535,100,250,25);
		panelUsuarios.add(txtDNI);
		
		cmdBuscarUsuario = new JButton("Buscar");
		cmdBuscarUsuario.setVisible(true);
		cmdBuscarUsuario.setBounds(810,97,110,30);
		panelUsuarios.add(cmdBuscarUsuario);
		
		cmdNuevoUsuario = new JButton("Nuevo");
		cmdNuevoUsuario.setVisible(true);
		cmdNuevoUsuario.setBounds(this.getWidth() - (this.getWidth() - 450) , this.getHeight() - 150, 110, 30);
		panelUsuarios.add(cmdNuevoUsuario);
		
		cmdBajaUsuario = new JButton("Baja");
		cmdBajaUsuario.setVisible(true);
		cmdBajaUsuario.setBounds(this.getWidth() - (this.getWidth() - 575) , this.getHeight() - 150, 110, 30);
		panelUsuarios.add(cmdBajaUsuario);
		
		cmdModificarUsuario = new JButton("Modificar");
		cmdModificarUsuario.setVisible(true);
		cmdModificarUsuario.setBounds(this.getWidth() - (this.getWidth() - 700) , this.getHeight() - 150, 110, 30);
		panelUsuarios.add(cmdModificarUsuario);
		
		cmdGuardarUsuario = new JButton("Guardar");
		cmdGuardarUsuario.setVisible(true);
		cmdGuardarUsuario.setBounds(this.getWidth() - (this.getWidth() - 825) , this.getHeight() - 150, 110, 30);
		panelUsuarios.add(cmdGuardarUsuario);
		
		
	}
	

}