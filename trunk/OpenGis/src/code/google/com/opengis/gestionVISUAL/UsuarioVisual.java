/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionVISUAL;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import code.google.com.opengis.gestion.Usuarios;
import code.google.com.opengis.gestion.ValidarLogin;
import code.google.com.opengis.gestionDAO.UsuariosDAO;

/**
* @author Juan Carlos García
* Clase que genera una ventana interior en el formulario MDI. Esta ventana es la encargada de gestionar los Usuarios.
*
*/

public class UsuarioVisual extends JInternalFrame implements ActionListener {
	
	private JPanel panelUsuarios;
	private JPanel panelUsuariosmod;
	
	private JButton cmdNuevoUsuario;
	private JButton cmdBajaUsuario;
	private JButton cmdModificarUsuario;
	private JButton cmdGuardarUsuario;
	private JButton cmdBuscarUsuario;
	
	private JButton cmdAceptarMod;
	private JButton cmdCancelarMod;
	
	private JLabel lblDNI;
	private JLabel lblEntrada;
	private JLabel lblEntradaMod;
	private JLabel lblFotoMod;
	private JLabel lblDNIMod;
	
	private static JTextField txtDNI;
	private static JTextField txtNombre;
	private static JTextField txtApellidos;
	private static JTextField txtFNac;
	private static JTextField txtDir;
	private static JTextField txtPob;
	private static JTextField txtProv;
	private static JTextField txtCp;
	private static JTextField txtTlf;
	private static JTextField txtEmail;
	private static JTextField txtDNIMod;
	private static JTextField txtCon;
	private static JList jListTipo;
	private static String[] tipo = {"Administrador", "Usuario"} ;
	
	/**
	 * Constructor de la clase UsuarioVisual. Se le pasarán los parametros necesarios para construir el alto y el ancho.
	 */

	public UsuarioVisual(int ancho, int alto){
			super("Usuarios",false, true, true, true);
			panelUsuarios = new JPanel ();
			panelUsuarios.setLayout(null);
			this.add(panelUsuarios);
			this.setBounds(0,0,ancho,alto);
			this.setTitle("Usuario");
			this.setClosable(true);
		   
			nuevosObjetos(panelUsuarios);
		
		
	}
	
	
	/**
	 * Este método carga en el formulario los Objetos necesarios Ej: Cajas de Texto, Labels, etc...
	 * 
	 */
	
			
		final static boolean shouldFill = true;
	    final static boolean shouldWeightX = true;
	    final static boolean RIGHT_TO_LEFT = false;

	    public static void nuevosObjetos(Container pane) {
	    	if (RIGHT_TO_LEFT) {
				pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			}
			JButton boton;
			txtDNI = new JTextField();
			txtNombre = new JTextField();
			txtApellidos = new JTextField();
			txtFNac = new JTextField();
			txtDir = new JTextField();
			txtPob = new JTextField();
			txtProv = new JTextField();
			txtCp = new JTextField();
			txtTlf = new JTextField();
			txtEmail = new JTextField();
			txtCon = new JTextField();
			jListTipo = new JList(tipo);
			JLabel campolbl;
			pane.setLayout(new GridBagLayout());
			
			
			
			//Se crean 3 constraints, uno para cada uso.
			GridBagConstraints cText = new GridBagConstraints(); 
			GridBagConstraints cButtons = new GridBagConstraints();
			GridBagConstraints cLabels = new GridBagConstraints();
			
			
			if (shouldFill){
				cLabels.fill = GridBagConstraints.HORIZONTAL;
			}
			cLabels.insets = new Insets(8,8,8,8);  //top padding
			campolbl = new JLabel("DNI:");
			if (shouldWeightX){
				cLabels.weightx = 0.005;
			}
			cLabels.fill = GridBagConstraints.HORIZONTAL;
			cLabels.gridx = 0;
			cLabels.gridy = 0;
			pane.add(campolbl, cLabels);
			
			
			cText.weightx = 0.5;
			cText.ipadx = 100;
			cText.fill = GridBagConstraints.HORIZONTAL;
			cText.gridx = 1;
			cText.gridy = 0;
			txtDNI.setText("");
			pane.add(txtDNI, cText);

			
			campolbl = new JLabel("Nombre:");
			cLabels.weightx = 0.005;
			cLabels.gridx = 2;
			cLabels.gridy = 0;
			pane.add(campolbl, cLabels);
						
			cText.gridx = 3;
			cText.gridy = 0;
			pane.add(txtNombre, cText);
			
			
			
			campolbl = new JLabel("Apellidos:");
			cLabels.gridx = 4;
			cLabels.gridy = 0;
			pane.add(campolbl, cLabels);
			
			
			
			cText.gridx = 5;
			cText.gridy = 0;
			pane.add(txtApellidos, cText);
			
			campolbl = new JLabel("Fecha de Nacimiento:");
			cLabels.gridx = 0;
			cLabels.gridy = 1;
			pane.add(campolbl, cLabels);
			
			
			cText.gridx = 1;
			cText.gridy = 1;
			pane.add(txtFNac, cText);
			
			//Añade JLabel de Teléfono y JTextField de teléfono
			campolbl = new JLabel("Teléfono:");
			cLabels.gridx = 2;
			cLabels.gridy = 1;
			pane.add(campolbl, cLabels);
			
			cText.gridx = 3;
			cText.gridy = 1;
			pane.add(txtTlf, cText);
			
			campolbl = new JLabel("Dirección:");
			cLabels.gridx = 4;
			cLabels.gridy = 1;
			pane.add(campolbl, cLabels);
									
			cText.gridx = 5;
			cText.gridy = 1;
			pane.add(txtDir, cText);

					
			
			campolbl = new JLabel("Población:");
			cLabels.gridx = 0;
			cLabels.gridy = 2;
			pane.add(campolbl, cLabels);
			
			cText.gridx = 1;
			cText.gridy = 2;
			pane.add(txtPob, cText);
			
			
			
			campolbl = new JLabel("Provincia:");
			cLabels.gridx = 2;
			cLabels.gridy = 2;
			pane.add(campolbl, cLabels);
			
			
			cText.gridx = 3;
			cText.gridy = 2;
			pane.add(txtProv, cText);
			
			campolbl = new JLabel("CP:");
			cLabels.gridx = 4;
			cLabels.gridy = 2;
			pane.add(campolbl, cLabels);
			
			
			cText.gridx = 5;
			cText.gridy = 2;
			pane.add(txtCp, cText);
			
			campolbl = new JLabel("Email:");
			cLabels.gridx = 0;
			cLabels.gridy = 3;
			pane.add(campolbl, cLabels);
			
			cText.gridx = 1;
			cText.gridy = 3;
			pane.add(txtEmail, cText);
			
			
			
			
			campolbl = new JLabel("Contraseña:");
			cLabels.gridx = 2;
			cLabels.gridy = 3;
			pane.add(campolbl, cLabels);
			
			cText.gridx = 3;
			cText.gridy = 3;
			pane.add(txtCon, cText);
			
			campolbl = new JLabel("Tipo de usuario");
			cLabels.gridx = 4;
			cLabels.gridy = 3;
			pane.add(campolbl, cLabels);
			
			cText.gridx = 5;
			cText.gridy = 3;
			pane.add(jListTipo,cText);
			
			boton = new JButton("Guardar");
			cButtons.fill = 0;
			cButtons.anchor = GridBagConstraints.PAGE_END; //bottom of space
			cButtons.insets = new Insets(15,0,0,0);  //top padding
			cButtons.gridx = 0;
			cButtons.gridy = 5;
			boton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {			
									
					Usuarios u = new Usuarios(txtDNI.getText(), txtNombre.getText(), txtApellidos.getText(), txtTlf.getText(), txtDir.getText(), txtPob.getText(), txtFNac.getText());
					u.validarDatos();
					if (u.getValido()) {
						u.crearUsuario();
					}else{
						JOptionPane.showMessageDialog(null,"Error al introducir los datos, compruebe los campos");
					}
					
					
				}
			});
			pane.add(boton, cButtons);
			
			
			
			
			boton = new JButton("Limpiar");
			cButtons.fill = 0;
			cButtons.insets = new Insets(15,15,0,0);  //top padding
			cButtons.gridx = 1;
			cButtons.gridy = 5;
			cButtons.anchor = GridBagConstraints.WEST;
			boton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {			
									
					txtDNI.setText("");
					txtNombre.setText("");
					txtApellidos.setText("");
					txtFNac.setText("");
					txtDir.setText("");
					txtPob.setText("");
					txtProv.setText("");
					txtCp.setText("");
					txtTlf.setText("");
					txtEmail.setText("");
					txtCon.setText("");
					
					JOptionPane.showMessageDialog(null,"Los campos se han restablecido");
					
					
				}
			});
			pane.add(boton, cButtons);
			
			
	    
		
		/*lblEntrada = new JLabel("Desde esta sección puede gestionar los Usuarios que utilizarán nuestro sistema OpenGis");
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
		cmdModificarUsuario.addActionListener(this);
		
		cmdGuardarUsuario = new JButton("Guardar");
		cmdGuardarUsuario.setVisible(true);
		cmdGuardarUsuario.setBounds(this.getWidth() - (this.getWidth() - 825) , this.getHeight() - 150, 110, 30);
		panelUsuarios.add(cmdGuardarUsuario);
		
		*/
	}
	/**
	 *  Este metodo hace que cuando clickes el boton de modificar te lleve a la interface de modificar usuario
	 */
	
	
	public void ModificarUsuario()
	{
		
			panelUsuarios.setVisible(false);
		  	panelUsuariosmod = new JPanel ();
			panelUsuariosmod.setLayout(null);			
			this.setBounds(0,0,this.getWidth(),this.getHeight());
			this.add(panelUsuariosmod);
			this.setTitle("Modificar Usuario");
			//this.setClosable(true);
			
			cmdAceptarMod = new JButton("Aceptar");
			cmdAceptarMod.setVisible(true);
			cmdAceptarMod.setBounds(this.getWidth() - (this.getWidth() - 800) , this.getHeight() - 150, 110, 30);
			panelUsuariosmod.add(cmdAceptarMod);
    		   	
			cmdCancelarMod = new JButton("Cancelar");
			cmdCancelarMod.setVisible(true);
			cmdCancelarMod.setBounds(this.getWidth() - (this.getWidth() - 1000) , this.getHeight() - 150, 110, 30);
			panelUsuariosmod.add(cmdCancelarMod);			

			lblEntradaMod = new JLabel("Desde esta sección puedes modificar un usuario");
			lblEntradaMod.setVisible(true);
			lblEntradaMod.setBounds(425,25, 800, 50);
			panelUsuariosmod.add(lblEntradaMod);		
			
			lblFotoMod = new JLabel();
			lblFotoMod.setVisible(true);
			lblFotoMod.setBounds(50,25,100,100);
			lblFotoMod.setBorder(getBorder());
			panelUsuariosmod.add(lblFotoMod);
			
			lblDNIMod = new JLabel("DNI Usuario");
			lblDNIMod.setVisible(true);
			lblDNIMod.setBounds(425,75, 80, 25);
			panelUsuariosmod.add(lblDNIMod);
			
			txtDNIMod = new JTextField();
			txtDNIMod.setVisible(true);
			txtDNIMod.setBounds(525,75,80,25);
			panelUsuariosmod.add(txtDNIMod);
		
	}
	
	
	public void actionPerformed( ActionEvent evento )
	      {
	    	  if ( cmdModificarUsuario == evento.getSource())
	    	  {

	    		  	ModificarUsuario();
	    		  	
	    	  }
	      }
	public String[] getTipo() {
		return tipo;
	}
	public void setTipo(String[] tipo) {
		this.tipo = tipo;
	}

}
