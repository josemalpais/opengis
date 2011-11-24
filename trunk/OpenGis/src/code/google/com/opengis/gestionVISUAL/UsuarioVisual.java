/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionVISUAL;



import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import code.google.com.opengis.gestion.Usuarios;
import code.google.com.opengis.gestion.ValidarLogin;
import code.google.com.opengis.gestionDAO.UsuariosDAO;

/**
* @author Juan Carlos García
* Clase que genera una ventana interior en el formulario MDI. Esta ventana es la encargada de gestionar los Usuarios.
*
*/

public class UsuarioVisual extends JInternalFrame{
	
	private JPanel panelUsuarios;
	private static JPanel panelUsuariosCrearMod;
	
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
	
	private static JTextField txtDNI = new JTextField();
	private static JTextField txtNombre = new JTextField();
	private static JTextField txtApellidos = new JTextField();
	private static JTextField txtFNac = new JTextField();
	private static JTextField txtDir = new JTextField();
	private static JTextField txtPob = new JTextField();
	private static JTextField txtProv = new JTextField();
	private static JTextField txtCp = new JTextField();
	private static JTextField txtTlf = new JTextField();
	private static JTextField txtEmail = new JTextField();
	private static JTextField txtDNIMod = new JTextField();
	private static JTextField txtCon = new JTextField();
	
	
	
	
	private static String[] tipo = {"Trabajador", "Administrador"} ;
	private static JComboBox jCmbTipo = new JComboBox(tipo);
	
	private static JTextField txtBuscar = new JTextField();
	private static String[] criterio = {"DNI", "Nombre", "Apellidos"};
	private static JComboBox jCmbCriterio = new JComboBox(criterio);
	
	private static JScrollPane jScrollPaneTablaUsuarios = null;
	private static JTable jTablaUsuarios = null;
	static Object[] nombreColumna = {"DNI", "Nombre", "Apellidos", "Dirección", "Poblacion", "Provincia", "Cod. Postal", "Teléfono", "Email","Fecha Nacimiento","Tipo"};
	static DefaultTableModel modelo = new DefaultTableModel(nombreColumna, 0);

	
	/**
	 * Constructor de la clase UsuarioVisual. Se le pasarán los parametros necesarios para construir el alto y el ancho.
	 */

	public UsuarioVisual(int ancho, int alto){
			super("Usuarios",true, true, true, true);
			super.setIconifiable(true); // Indicamos que se puede minimizar
			panelUsuarios = new JPanel ();
			panelUsuarios.setLayout(null);
			panelUsuariosCrearMod = new JPanel ();
			panelUsuariosCrearMod.setLayout(null);
			this.add(panelUsuariosCrearMod);
			panelUsuariosCrearMod.setVisible(false);
			this.add(panelUsuarios);
			this.setBounds(0,0,ancho,alto);
			this.setTitle("Usuario");
			this.setClosable(true);
			this.setMaximizable(true);
			TitledBorder jb = new TitledBorder("Gestión de usuarios");
			TitledBorder jb1 = new TitledBorder("Añadir / Modificar");
			panelUsuarios.setBorder(jb);
			panelUsuariosCrearMod.setBorder(jb1);
			double ii = ancho/1.7;
			double aa = alto/1.7;
			panelUsuarios.setBounds(new Rectangle(0,0,ancho,alto));
			panelUsuariosCrearMod.setBounds(new Rectangle(0,0,800,600));
		
			
			this.getJScrollPaneTablaUsuarios();
			cargarUsuariosPrincipal(panelUsuarios);
		
	}
	
	public static void cargarUsuariosPrincipal(final Container pane){
		JButton boton;
		JLabel campolbl;
		pane.setLayout(new GridBagLayout());
		txtBuscar.setPreferredSize(new Dimension(120,20));
		
		//Se crean 3 constraints, uno para cada uso.
		GridBagConstraints cText = new GridBagConstraints(); //Cajas de texto
		GridBagConstraints cButtons = new GridBagConstraints(); //Botones
		GridBagConstraints cLabels = new GridBagConstraints(); //Labels
		GridBagConstraints cTabla = new GridBagConstraints();
		
		cLabels.insets = new Insets(8,8,8,8);  //top padding
		
		cLabels.weightx = 0.005;
		cLabels.anchor = GridBagConstraints.EAST;
		cText.anchor = GridBagConstraints.EAST;
		cText.weightx = 0.5;
		cText.ipadx = 100;
		
		
		cText.gridx = 0;
		cText.gridy = 0;
		cText.gridwidth = 2;
		txtBuscar.setText("Introduzca aquí el criterio");
		pane.add(txtBuscar, cText);
		cText.gridwidth = 1;
		
		campolbl = new JLabel("Buscar por:");
		cLabels.gridx = 2;
		cLabels.gridy = 0;
		pane.add(campolbl, cLabels);
		
		cText.anchor = GridBagConstraints.WEST;
		cText.gridx = 3;
		cText.gridy = 0;
		pane.add(jCmbCriterio, cText);
		
		boton = new JButton("Buscar");
		cButtons.anchor = GridBagConstraints.WEST;
		cButtons.insets = new Insets(0,0,0,0);
		cButtons.gridx = 4;
		cButtons.gridy = 0;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {			
				 try {
			            modelo.setColumnCount(0);
			            modelo.setRowCount(0);
			            ResultSet rs = UsuariosDAO.buscarUsuario(jCmbCriterio.getSelectedItem().toString(),txtBuscar.getText().toLowerCase());
			            int nColumnas = rs.getMetaData().getColumnCount();
			            modelo.setColumnIdentifiers(nombreColumna);
			            while (rs.next()) {
			            	Object[] registro = new Object[nColumnas];


			            	for (int i = 0; i
			            			< nColumnas; i++) {
			            		registro[i] = rs.getObject(i + 1);

			            		}
			            	modelo.addRow(registro);


			            }
			            rs.close();
				   } catch (SQLException e1) {
				        System.out.println(e1);


				    }
				 
			}
		});
		


        

        


        

 
		pane.add(boton, cButtons);
		
		
		
		
		jTablaUsuarios.setModel(modelo);
		cTabla.gridx = 0;
		cTabla.gridy = 1;
		cTabla.gridwidth = 5;
		cTabla.gridheight = 5;
		cTabla.fill = GridBagConstraints.HORIZONTAL;
		pane.add(jScrollPaneTablaUsuarios, cTabla);
		
		
		
		
		
		
		boton = new JButton("Nuevo");
		cButtons.anchor = GridBagConstraints.EAST;
		cButtons.insets = new Insets(0,0,0,0);
		cButtons.gridx = 0;
		cButtons.gridy = 7;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {			
				pane.setVisible(false);
				cargarNuevoModificar(panelUsuariosCrearMod,false);
				panelUsuariosCrearMod.setVisible(true);
			}
		});
		pane.add(boton, cButtons);
		
		boton = new JButton("Modificar");
		cButtons.anchor = GridBagConstraints.WEST;
		cButtons.insets = new Insets(0,0,0,0);
		cButtons.gridx = 1;
		cButtons.gridy = 7;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {			
		 
		        int fila = jTablaUsuarios.getSelectedRow();
		        if (fila != -1) {
		            String[] rUser = new String[10];
		            for (int i = 0; i < 4; i++) {
		                rUser[i] = jTablaUsuarios.getValueAt(fila, i).toString();
		            }
		            setCampos(rUser);
		            pane.setVisible(false);
					cargarNuevoModificar(panelUsuariosCrearMod,true);
					panelUsuariosCrearMod.setVisible(true);
		        }
				
			}
		});
		pane.add(boton, cButtons);
		
		boton = new JButton("Desactivar");
		cButtons.anchor = GridBagConstraints.LINE_START;
		cButtons.insets = new Insets(0,0,0,0);
		cButtons.gridx = 3;
		cButtons.gridy = 7;
		pane.add(boton, cButtons);
		
			
		

		
		
	}
	
/**
 * Este método carga en el frame que le indiquemos los objetos para Crear Usuarios o Modificarlos.		
 * @param pane Indica el contenedor de todos
 * @param modificar Indica si vamos a modificar el Usuario (true) o vamos a crear uno nuevo (false)
 */
	    public static void cargarNuevoModificar(Container pane,boolean modificar) {
	    	//pane.removeAll();
	    	//pane.repaint();
			JButton boton;
			JLabel campolbl;
			pane.setLayout(new GridBagLayout());
			Dimension tamañoCaja = new Dimension(100, 20);
			txtDNI.setPreferredSize(tamañoCaja);
			txtNombre.setPreferredSize(tamañoCaja);
			txtApellidos.setPreferredSize(tamañoCaja);
			txtFNac.setPreferredSize(tamañoCaja);
			txtDir.setPreferredSize(tamañoCaja);
			txtPob.setPreferredSize(tamañoCaja);
			txtProv.setPreferredSize(tamañoCaja);
			txtCp.setPreferredSize(tamañoCaja);
			txtCon.setPreferredSize(tamañoCaja);
			txtTlf.setPreferredSize(tamañoCaja);
			txtEmail.setPreferredSize(tamañoCaja);
			
			//Se crean 3 constraints, uno para cada uso.
			GridBagConstraints cText = new GridBagConstraints(); //Cajas de texto
			GridBagConstraints cButtons = new GridBagConstraints(); //Botones
			GridBagConstraints cLabels = new GridBagConstraints(); //Labels
			
			
			
						
			cLabels.insets = new Insets(8,8,8,8);  //top padding
			cLabels.anchor = GridBagConstraints.EAST;
			cText.anchor = GridBagConstraints.WEST;
			cText.weightx = 0.5;
			cText.ipadx = 100;	
			
			campolbl = new JLabel("DNI:");
			cLabels.weightx = 0.005;
			cLabels.gridx = 0;
			cLabels.gridy = 0;
			pane.add(campolbl, cLabels);
									
			cText.gridx = 1;
			cText.gridy = 0;
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
			pane.add(jCmbTipo,cText);
			
			
			if (modificar != true){
				boton = new JButton("Guardar");
				cButtons.fill = 0;
				cButtons.anchor = GridBagConstraints.PAGE_END; //bottom of space
				cButtons.insets = new Insets(15,0,0,0);  //top padding
				cButtons.gridx = 0;
				cButtons.gridy = 5;
				boton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {			
									
						Usuarios u = new Usuarios(txtDNI.getText(), txtNombre.getText(), txtApellidos.getText(), txtTlf.getText(), txtDir.getText(), txtPob.getText(),txtProv.getText(), txtCp.getText(), txtFNac.getText(), txtCon.getText(), jCmbTipo.getSelectedItem().toString().toLowerCase(), txtEmail.getText());
						u.validarDatos();
						System.out.println(jCmbTipo.getSelectedItem().toString());
						if (u.getValido()) {
							u.crearUsuario();
						}					
					}
				});
				pane.add(boton, cButtons);
			}else{
				boton = new JButton("Modificar");
				cButtons.fill = 0;
				cButtons.anchor = GridBagConstraints.PAGE_END; //bottom of space
				cButtons.insets = new Insets(15,0,0,0);  //top padding
				cButtons.gridx = 0;
				cButtons.gridy = 5;
				boton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {			
										
						Usuarios u = new Usuarios(txtDNI.getText(), txtNombre.getText(), txtApellidos.getText(), txtTlf.getText(), txtDir.getText(), txtPob.getText(),txtProv.getText(), txtCp.getText(), txtFNac.getText(), txtCon.getText(), jCmbTipo.getSelectedItem().toString().toLowerCase(), txtEmail.getText());
						u.validarDatos();
						System.out.println(jCmbTipo.getSelectedItem().toString());
						if (u.getValido()) {
							u.modificarUsuario();
						}
						
						
					}
				});
				pane.add(boton, cButtons);
			}
			
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
			
			boton = new JButton("Volver");
			cButtons.fill = 0;
			cButtons.insets = new Insets(15,15,0,0);  //top padding
			cButtons.gridx = 1;
			cButtons.gridy = 5;
			cButtons.anchor = GridBagConstraints.WEST;
			boton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {			
									
								
					JOptionPane.showMessageDialog(null,"Los campos se han restablecido");
					
					
				}
			});
			pane.add(boton, cButtons);
			

	}
	    
	    
	    
	    
	    
	    
	    
	    
	/**
	 *  Este metodo hace que cuando clickes el boton de modificar te lleve a la interface de modificar usuario
	 */
	
	
	/*public void ModificarUsuario()
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
	*/
	
	public String[] getTipo() {
		return tipo;
	}
	
	public static void setCampos(String[] rUser){
		
	}
	public void setTipo(String[] tipo) {
		this.tipo = tipo;
	}
	
	private JScrollPane getJScrollPaneTablaUsuarios() {
		if (jScrollPaneTablaUsuarios == null) {
			jScrollPaneTablaUsuarios = new JScrollPane();
			//jScrollPaneTablaUsuarios.setBounds(new Rectangle(9, 73, 638, 117));
			jScrollPaneTablaUsuarios.setViewportView(getJTablaUsuarios());
		}
		return jScrollPaneTablaUsuarios;
	}

	/**
	 * This method initializes jTableAsignaturas	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTablaUsuarios() {
		
			
			jTablaUsuarios = new JTable();
			jTablaUsuarios.setModel(modelo);
					
			
			
		
		return jTablaUsuarios;
	}

}


