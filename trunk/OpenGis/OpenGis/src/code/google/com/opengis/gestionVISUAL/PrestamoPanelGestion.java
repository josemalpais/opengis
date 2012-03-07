package code.google.com.opengis.gestionVISUAL;

import java.awt.HeadlessException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;

import code.google.com.opengis.gestion.Prestamo;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;


public class PrestamoPanelGestion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblidprestamo = null;
	private JLabel lblIddispositivo = null;
	private JLabel lbldni_usuario = null;
	private JTextField txtIDprestamo = null;
	private JComboBox txtIDdispositivo = null;
	JComboBox comboUser = null;
	private JButton bGuardar = null;
	private JButton bLimpiar = null;
	private JLabel lblObligatorios = null;
	private String letrasEscritas = "";
	
	private String accion;
	private String id_prestamo;
	private String id_dispositivo;
	private String dni_usuario;
	
	private boolean encontrado;
	private String auxdisp;
	
	/**
	 * This is the default constructor
	 */
	public PrestamoPanelGestion(String accion) {
		super();
		
		this.accion = accion;
		
		try {
			initialize();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public PrestamoPanelGestion(String accion, String id_prestamo, String id_dispositivo,String dni_usuario, String aux) {
		super();
		
		this.accion = accion;
		this.id_prestamo = id_prestamo;
		this.id_dispositivo = id_dispositivo;
		this.dni_usuario = dni_usuario;
		this.auxdisp = aux;
		
		try {
			initialize();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		lblObligatorios = new JLabel();
		lblObligatorios.setBounds(new Rectangle(359, 305, 227, 20));
		lblObligatorios.setFont(new Font("Dialog", Font.ITALIC, 12)); //$NON-NLS-1$
		lblObligatorios.setText(Idioma.getString("etAllFields")); //$NON-NLS-1$
		lbldni_usuario = new JLabel();
		lbldni_usuario.setBounds(new Rectangle(47, 136, 112, 25));
		lbldni_usuario.setText(Idioma.getString("etIdCard")); //$NON-NLS-1$
		lblIddispositivo = new JLabel();
		lblIddispositivo.setBounds(new Rectangle(47, 91, 112, 25));
		lblIddispositivo.setText(Idioma.getString("etDeviceId")); //$NON-NLS-1$
		lblidprestamo = new JLabel();
		lblidprestamo.setBounds(new Rectangle(47, 48, 112, 25));
		lblidprestamo.setText(Idioma.getString("etLoanId")); //$NON-NLS-1$
		this.setSize(774, 357);
		this.setLayout(null);
		this.add(lblidprestamo, null);
		this.add(lblIddispositivo, null);
		this.add(lbldni_usuario, null);
		this.add(getTxtIDprestamo(), null);
		this.add(getTxtIDdispositivo(), null);
		this.add(getComboUser(), null);
		this.add(getBGuardar(), null);
		this.add(getBLimpiar(), null);
		this.add(lblObligatorios, null);
	}

	/**
	 * This method initializes txtID	
	 * 	
	 * @return javax.swing.JTextField	
	 * @throws ParseException 
	 */
	private JTextField getTxtIDprestamo() throws ParseException {
		if (txtIDprestamo == null) {
			txtIDprestamo =new JTextField();	
			txtIDprestamo.setBounds(new Rectangle(157, 48, 149, 24));
			txtIDprestamo.setEnabled(false);
			
			if(accion=="alta"){ //$NON-NLS-1$
				
				ConectarDBA.acceder();
			
				try {
					
					String sentencia = "SELECT MAX(id_prestamo) FROM prestamo"; //$NON-NLS-1$
					
					ResultSet rs = ConectarDBA.consulta(sentencia);
					
					rs.next();
					
					int nuevoid = rs.getInt(1) + 1;
					
					txtIDprestamo.setText(nuevoid+""); //$NON-NLS-1$
					
					ConectarDBA.cerrarCon();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				

				
			}else{
				
				txtIDprestamo.setText(id_prestamo);
				
			}
			
		}
		return txtIDprestamo;
	}

	/**
	 * This method initializes txtIDdispositivo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JComboBox getTxtIDdispositivo() {
		if (txtIDdispositivo == null) {
			txtIDdispositivo = new JComboBox();
			txtIDdispositivo.setBounds(new Rectangle(160, 91, 149, 24));
			
			cargarDispositivos();
			
			if(accion=="modificar"){ //$NON-NLS-1$
				int x=0;
				String seleccionado;
				String dispositivo;
				//Este for recorre los ítems de la lista de dispositivos 
				//para elegir el que toca a la hora de modificar
				for(int i=0; i<txtIDdispositivo.getItemCount()-1;i++){
					txtIDdispositivo.setSelectedIndex(i);
					seleccionado = txtIDdispositivo.getSelectedItem().toString();
					dispositivo = seleccionado.substring(0,2);
					if (Prestamo.isInteger(dispositivo)){
					}else{
						dispositivo = dispositivo.substring(0,1);
					}
					if(dispositivo.equals(this.id_dispositivo)){
						x=i;
					}
				}
				txtIDdispositivo.setSelectedIndex(x);
				//txtIDdispositivo.setEnabled(false);
				
			}
			
		}
		return txtIDdispositivo;
	}

	/**
	 * This method initializes txtDNI	
	 * 	
	 * @return javax.swing.JTextField	
	 * @throws ParseException 
	 */
	private JComboBox getComboUser() throws ParseException {
		if (comboUser == null) {

			comboUser = new JComboBox();
			comboUser.setEditable(true);
			comboUser.setBounds(new Rectangle(160, 136, 149, 24));
			
			if(!accion.equals("modificar")){
				
			
			comboUser.getEditor().getEditorComponent()
					.addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent arg0) {
							// TODO Auto-generated method stub
							Integer l = arg0.getKeyCode();
							if (l == 8) {
								String letrasEscritas2 = "";
								for (int i = 0; i < letrasEscritas.length() - 1; i++) {
									letrasEscritas2 = letrasEscritas2
											+ letrasEscritas.charAt(i);
								}
								letrasEscritas = letrasEscritas2;
							}
							if (l > 47 && l < 123) {
								letrasEscritas = letrasEscritas
										+ arg0.getKeyChar();
							}
							if (l == 10) {
								buscar();
								letrasEscritas = "";
							}
						}

						@Override
						public void keyReleased(KeyEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void keyTyped(KeyEvent arg0) {
							// TODO Auto-generated method stub

						}

					});
			
			}else{
				
				comboUser.addItem(dni_usuario);
				
				
			}
		}
		return comboUser;
	}

	/**
	 * This method initializes bGuardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBGuardar() {
		if (bGuardar == null) {
			bGuardar = new JButton();
			bGuardar.setBounds(new Rectangle(48, 283, 53, 45));
			bGuardar.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/Guardar.png"))); //$NON-NLS-1$
			bGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

				String seleccionado = txtIDdispositivo.getSelectedItem().toString();
				String dispositivo = seleccionado.substring(0,2);
				if (Prestamo.isInteger(dispositivo)){
					
				}else{
					dispositivo = dispositivo.substring(0,1);
				}
					
			
					
						
						encontrado = false;
						ConectarDBA.acceder();
						
						String consulta = "SELECT dni from usuario where dni = '"+ comboUser.getSelectedItem().toString() +"'"; //$NON-NLS-1$ //$NON-NLS-2$
						
						try {
							ResultSet rs = ConectarDBA.consulta(consulta);
							
							while(rs.next()){
								
								encontrado = true;
								
							}
							

							
							if(encontrado == false){
								
								JOptionPane.showMessageDialog(null,Idioma.getString("msgErrorIDUnmatchUser")); //$NON-NLS-1$
								comboUser.removeAllItems(); //$NON-NLS-1$
								
							}
							
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
						if(encontrado == false){
	
						}else{
							if(accion.equals("alta")){ //$NON-NLS-1$
									
						
							try {
								ConectarDBA.cerrarCon();
								
								
								boolean b = false;
								b = Prestamo.validarDatos(dispositivo,comboUser.getSelectedItem().toString());
								if(b==true){
									
									Prestamo.crearPrestamo(dispositivo,comboUser.getSelectedItem().toString());
									try {//Actualizamos el ID de préstamo
										
										String sentencia = "SELECT MAX(id_prestamo) FROM prestamo"; //$NON-NLS-1$
										
										ConectarDBA.acceder();
										ResultSet rs = ConectarDBA.consulta(sentencia);
										
										rs.next();
										
										int nuevoid = rs.getInt(1) + 1;
										
										txtIDprestamo.setText(nuevoid+""); //$NON-NLS-1$
										
										comboUser.removeAllItems();
										txtIDdispositivo.setSelectedItem(0);
										
										ConectarDBA.cerrarCon();
										
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								}
							} catch (HeadlessException e1) {
								e1.printStackTrace();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}						
						}
					
					if(accion.equals("modificar")){ //$NON-NLS-1$
						//try{
						//auxdisp = txtIDdispositivo.getSelectedItem().toString();
							Prestamo.modificarPrestamo(txtIDprestamo.getText(), dispositivo, comboUser.getSelectedItem().toString(),auxdisp);
							auxdisp = dispositivo;  
							//}
					}			
				}
				}
			}
			);
		}
		return bGuardar;
	}

	/**
	 * This method initializes bLimpiar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBLimpiar() {
		if (bLimpiar == null) {
			bLimpiar = new JButton();
			bLimpiar.setBounds(new Rectangle(149, 283, 53, 45));
			bLimpiar.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/Limpiar.png"))); //$NON-NLS-1$
			bLimpiar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					txtIDdispositivo.setSelectedIndex(1); //$NON-NLS-1$
					comboUser.removeAllItems(); //$NON-NLS-1$
					
					
				}
			});
		}
		return bLimpiar;
	}
	
	
	public void cargarDispositivos(){
		
		ConectarDBA.acceder();
		
		//String consulta = "SELECT iddispositivo,modelo From dispositivo where disponible='0' AND activo='0'";
		String consulta = "SELECT iddispositivo,modelo From dispositivo"; //$NON-NLS-1$
		
		try {
			
			
			
			ResultSet rs = ConectarDBA.consulta(consulta);
			
			while(rs.next()){
				
				String dispositivo = rs.getString(1) + " - " + rs.getString(2); //$NON-NLS-1$
				
				txtIDdispositivo.addItem(dispositivo);
				
				
			}
			
			ConectarDBA.cerrarCon();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void buscar() {
		comboUser.removeAllItems();
		String sentencia = "SELECT `dni` FROM `usuario` WHERE dni LIKE '"
				+ letrasEscritas + "%'";
		ResultSet rs;
		try {
			rs = ConectarDBA.buscar(sentencia);
			String usuario = new String();

			 // Guardamos todos los registros

			
			while (rs.next()) {
				usuario = rs.getString(1);
				System.out.println(usuario);
				comboUser.addItem(usuario); // Añadimos el registro a la tabla
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	

} 
