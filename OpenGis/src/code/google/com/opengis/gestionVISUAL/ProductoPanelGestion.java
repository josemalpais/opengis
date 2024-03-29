package code.google.com.opengis.gestionVISUAL;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;

import code.google.com.opengis.gestion.Producto;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class ProductoPanelGestion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblidprod = null;
	private JLabel lblNombre = null;
	private JLabel lblTipo = null;
	private JLabel lblDNI = null;
	private JLabel lblDosis = null;
	private JLabel lblDescripcion = null;
	private JLabel lblActivo=null;
	private JLabel lblObligatorios = null;
	
	private JTextField txtID = null;
	private JTextField txtNombreProd = null;
	private JTextField txtDosis = null;
	private JTextArea txtDescripcion = null;
	private JLabel lblMedida = null;
	
	private JComboBox comboTipo = null;
	JComboBox comboUser = null;
	
	private JButton bGuardar = null;
	private JButton bLimpiar = null;
	
	private String accion;
	private String id;
	private String nombre;
	private String descripcion;
	private String tipo;
	private String dosis;
	private String dni;
	private String letrasEscritas = "";
	
	boolean encontrado;
	
	/**
	 * This is the default constructor
	 */
	public ProductoPanelGestion(String accion) {
		super();
		
		this.accion = accion;
		
		try {
			initialize();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public ProductoPanelGestion(String accion, String id, String nombre,String descripcion,String tipo,String dosis, String dni) {
		super();
		
		this.accion = accion;
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.dosis = dosis;
		this.dni = dni;
		
		encontrado = true;
		
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
		lblObligatorios.setFont(new Font("Dialog", Font.ITALIC, 12)); 
		lblObligatorios.setText(Idioma.getString("etAllFields")); 
		lblMedida = new JLabel();
		lblMedida.setBounds(new Rectangle(372, 136, 44, 25));
		lblMedida.setFont(new Font("Dialog", Font.PLAIN, 12)); 
		lblMedida.setText("(l/ha)"); 
		lblDescripcion = new JLabel();
		lblDescripcion.setBounds(new Rectangle(328, 180, 112, 25));
		lblDescripcion.setText(Idioma.getString("etDesc")); 
		lblDosis = new JLabel();
		lblDosis.setBounds(new Rectangle(328, 136, 44, 25));
		lblDosis.setText(Idioma.getString("etAmount")); 
		lblDNI = new JLabel();
		lblDNI.setBounds(new Rectangle(47, 180, 112, 25));
		lblDNI.setText(Idioma.getString("etIdCard")); 
		lblTipo = new JLabel();
		lblTipo.setBounds(new Rectangle(47, 136, 112, 25));
		lblTipo.setText(Idioma.getString("etType")); 
		lblNombre = new JLabel();
		lblNombre.setBounds(new Rectangle(47, 91, 112, 25));
		lblNombre.setText(Idioma.getString("etProductName")); 
		lblidprod = new JLabel();
		lblidprod.setBounds(new Rectangle(47, 48, 112, 25));
		lblidprod.setText(Idioma.getString("etProductId")); 
		this.setSize(774, 357);
		this.setLayout(null);
		this.add(lblidprod, null);
		this.add(lblNombre, null);
		this.add(lblTipo, null);
		this.add(lblDNI, null);
		this.add(lblDosis, null);
		this.add(lblDescripcion, null);
		this.add(getTxtID(), null);
		this.add(getTxtNombreProd(), null);
		this.add(getComboUser(), null);
		this.add(getTxtDosis(), null);
		this.add(getTxtDescripcion(), null);
		this.add(lblMedida, null);
		this.add(getComboTipo(), null);
		this.add(getBGuardar(), null);
		this.add(getBLimpiar(), null);
		this.add(lblObligatorios, null);
	}

	/**
	 * This method initializes txtID	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtID() {
		if (txtID == null) {
			txtID = new JTextField();
			txtID.setBounds(new Rectangle(157, 48, 149, 24));
			txtID.setEnabled(false);
			
			if(accion=="alta"){ 
				
				ConectarDBA.acceder();
			
				try {
					
					String sql = "SELECT MAX(idprod) FROM producto"; 					
					ResultSet rs = ConectarDBA.consulta(sql);
					
					rs.next();
					
					int nuevoid = rs.getInt(1) + 1;
					
					txtID.setText(nuevoid+""); 
					
					ConectarDBA.cerrarCon();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}								
			}else{
				
				txtID.setText(id);
				
			}
			
		}
		return txtID;
	}

	/**
	 * This method initializes txtNombreProd	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNombreProd() {
		if (txtNombreProd == null) {
			txtNombreProd = new JTextField();
			txtNombreProd.setBounds(new Rectangle(160, 91, 149, 24));
			
			if(accion=="modificar"){ //$NON-NLS-1$
				
				txtNombreProd.setText(nombre);
				
			}
			
		}
		return txtNombreProd;
	}

	

	/**
	 * This method initializes txtDosis	
	 * 	
	 * @return javax.swing.JTextField	
	 * @throws ParseException 
	 */
	private JTextField getTxtDosis() throws ParseException {
		if (txtDosis == null) {
		
			txtDosis = new JTextField(""); 
			txtDosis.setBounds(new Rectangle(420, 136, 149, 24));
			if(accion=="modificar"){ //$NON-NLS-1$
				
				txtDosis.setText(dosis);
				
			}
			
		}
		return txtDosis;
	}

	/**
	 * This method initializes txtDescripcion	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDescripcion() {
		if (txtDescripcion == null) {
			txtDescripcion = new JTextArea();
			txtDescripcion.setBounds(new Rectangle(422, 180, 161, 100));
			txtDescripcion.setLineWrap(true);
			
			if(accion=="modificar"){ //$NON-NLS-1$
				
				txtDescripcion.setText(descripcion);				
			}			
		}
		return txtDescripcion;
	}

	/**
	 * This method initializes comboTipo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboTipo() {
		if (comboTipo == null) {
			comboTipo = new JComboBox();
			comboTipo.setBounds(new Rectangle(162, 136, 149, 24));
			comboTipo.addItem(Idioma.getString("etLiquid")); //$NON-NLS-1$
			comboTipo.addItem(Idioma.getString("etGranulated")); //$NON-NLS-1$
			comboTipo.addItem(Idioma.getString("etPowder")); //$NON-NLS-1$
			
			if(accion=="modificar"){ //$NON-NLS-1$
				
				comboTipo.setSelectedItem(tipo);
				
			}
			comboTipo.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
				
				String tipo = comboTipo.getSelectedItem().toString();
				
				
				if(tipo.equals(Idioma.getString("etLiquid"))){ //$NON-NLS-1$
					
					lblMedida.setText("(l/ha)"); //$NON-NLS-1$
					
				}
				
				
				if(tipo.equals(Idioma.getString("etGranulated"))){ //$NON-NLS-1$
					
					lblMedida.setText("(g/ha)"); //$NON-NLS-1$
					
				}
				
				if(tipo.equals(Idioma.getString("etPowder"))){ //$NON-NLS-1$
					
					lblMedida.setText("(Kg/ha)"); //$NON-NLS-1$
					
				}
				
			}
			
			});
				
		}
		return comboTipo;
	}
	
	private JComboBox getComboUser() throws ParseException {
		if (comboUser == null) {

			comboUser = new JComboBox();
			comboUser.setEditable(true);
			comboUser.setBounds(new Rectangle(160, 180, 149, 24));
			
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
				
				comboUser.addItem(dni);
				
				
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
										
					ConectarDBA.acceder();
					
					String sql = "SELECT dni from usuario where dni = '"+ comboUser.getSelectedItem().toString() +"'"; 
					
					try {
						ResultSet rs = ConectarDBA.consulta(sql);
						
						while(rs.next()){
							
							encontrado = true;					
						}
												
						if(encontrado == false){
							
							JOptionPane.showMessageDialog(null, Idioma.getString("msgErrorIDUnmatchUser"));
							comboUser.removeAllItems(); //$NON-NLS-1$						
						}
						
						ConectarDBA.cerrarCon();
												
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					
					if(accion.equals("alta")){ //$NON-NLS-1$
						
						
						
						if(encontrado == false){
							
						
						}else{
							
						if(txtNombreProd.getText()=="" || txtDosis.getText()=="" || comboUser.getSelectedItem().toString()==""){
							JOptionPane.showMessageDialog(null, Idioma.getString("etAllFields")); 
						}else{
							
							Producto prod = new Producto(Integer.parseInt(txtID.getText()),txtNombreProd.getText().trim(),txtDescripcion.getText(), comboTipo.getSelectedItem().toString(),txtDosis.getText(),comboUser.getSelectedItem().toString(),0);
						
							prod.validarDatos();
						
							if (prod.getCorrecto()==true){
							
							try{

								prod.crearProducto();
								
								ConectarDBA.acceder();
								
								String consulta;
								
								consulta="SELECT MAX(idprod) FROM `producto`";
								
								ResultSet resul = ConectarDBA.consulta(consulta);
								
								resul.next();
								
								int masId = resul.getInt(1) + 1;
								
								txtDescripcion.setText("");
								comboUser.removeAllItems();
								txtDosis.setText("");
								txtID.setText(masId+"");
								txtNombreProd.setText("");
								
								
								}catch(SQLException e1) {
									JOptionPane.showMessageDialog(null, Idioma.getString("msgErrorInsert")); //$NON-NLS-1$
									 
									
								}
							
						}
							
						}
						
						}
						
					}else{
						
						if(encontrado == false){
			
						}else{
						
							Producto prod = new Producto(Integer.parseInt(txtID.getText()),txtNombreProd.getText(),txtDescripcion.getText(), comboTipo.getSelectedItem().toString(),txtDosis.getText(),comboUser.getSelectedItem().toString(),0);
							
							prod.validarDatos();
							
							if (prod.getCorrecto()==true){
								
								prod.editarProducto();								
							}													
						}												
					}									
				}
				
			});
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
					
					
					if(accion.equals("alta")){
						
					
					txtNombreProd.setText(""); //$NON-NLS-1$
					txtDosis.setText(""); //$NON-NLS-1$
					txtDescripcion.setText(""); //$NON-NLS-1$
					
					if(comboUser.isEnabled() == true){
						
						comboUser.removeAllItems();
						
					}
					
					ConectarDBA.acceder();					
					try {					
						String sql = "SELECT MAX(idprod) FROM producto"; 					
						ResultSet rs = ConectarDBA.consulta(sql);						
						rs.next();						
						int nuevoid = rs.getInt(1) + 1;					
						txtID.setText(nuevoid+""); 						
						ConectarDBA.cerrarCon();						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
				}else{
					
					txtNombreProd.setText(""); //$NON-NLS-1$
					txtDosis.setText(""); //$NON-NLS-1$
					txtDescripcion.setText(""); //$NON-NLS-1$
					
					
				}
				}
			});
		}
		return bLimpiar;
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
				comboUser.addItem(usuario); // A�adimos el registro a la tabla
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
