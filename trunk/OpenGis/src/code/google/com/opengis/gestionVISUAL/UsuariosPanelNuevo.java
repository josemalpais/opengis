package code.google.com.opengis.gestionVISUAL;

import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

import code.google.com.opengis.gestionDAO.ConectarDBA;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosPanelNuevo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNombre = null;
	private JLabel lblApellidos = null;
	private JLabel lblDNI = null;
	private JLabel lbldireccion = null;
	private JLabel lblCodigoPostal = null;
	private JLabel lblPoblacion = null;
	private JLabel lblProvincia = null;
	private JLabel lblteléfono = null;
	private JLabel lblEmail = null;
	private JLabel lblPass = null;
	private JLabel lblTipo = null;
	private JLabel lblFechaNac = null;
	private JButton bGuardar = null;
	private JButton bRestablecer = null;
	private JTextField txtDNI = null;
	private JTextField txtNombre = null;
	private JTextField txtApellidos = null;
	private JTextField txtFechaNac = null;
	private JTextField txtDireccion = null;
	private JTextField txtCP = null;
	private JTextField txtProvincia = null;
	private JTextField txtTelefono = null;
	private JTextField txteMail = null;
	private JPasswordField txtPass = null;
	private JComboBox comboTipo = null;
	private JLabel lblObligtorios = null;
	private JComboBox comboPoblacion = null;
	/**
	 * This is the default constructor
	 */
	public UsuariosPanelNuevo() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		lblObligtorios = new JLabel();
		lblObligtorios.setBounds(new Rectangle(434, 334, 238, 25));
		lblObligtorios.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblObligtorios.setText("(*) Todos los campos son obligatorios");
		lblFechaNac = new JLabel();
		lblFechaNac.setBounds(new Rectangle(506, 83, 138, 30));
		lblFechaNac.setText("Fecha de Nacimiento:");
		lblTipo = new JLabel();
		lblTipo.setBounds(new Rectangle(274, 248, 102, 30));
		lblTipo.setText("Tipo de Usuario:");
		lblPass = new JLabel();
		lblPass.setBounds(new Rectangle(42, 248, 88, 30));
		lblPass.setText("Contraseña:");
		lblEmail = new JLabel();
		lblEmail.setBounds(new Rectangle(506, 191, 88, 30));
		lblEmail.setText("eMail:");
		lblteléfono = new JLabel();
		lblteléfono.setBounds(new Rectangle(274, 192, 88, 30));
		lblteléfono.setText("Teléfono:");
		lblProvincia = new JLabel();
		lblProvincia.setBounds(new Rectangle(44, 192, 88, 30));
		lblProvincia.setText("Provincia:");
		lblPoblacion = new JLabel();
		lblPoblacion.setBounds(new Rectangle(506, 136, 88, 30));
		lblPoblacion.setText("Población:");
		lblCodigoPostal = new JLabel();
		lblCodigoPostal.setBounds(new Rectangle(274, 137, 88, 30));
		lblCodigoPostal.setText("Código Postal:");
		lbldireccion = new JLabel();
		lbldireccion.setBounds(new Rectangle(42, 137, 88, 30));
		lbldireccion.setText("Dirección:");
		lblDNI = new JLabel();
		lblDNI.setBounds(new Rectangle(42, 31, 88, 30));
		lblDNI.setText("Dni:");
		lblApellidos = new JLabel();
		lblApellidos.setBounds(new Rectangle(274, 84, 88, 30));
		lblApellidos.setText("Apellidos:");
		lblNombre = new JLabel();
		lblNombre.setBounds(new Rectangle(42, 84, 88, 30));
		lblNombre.setText("Nombre:");
		this.setSize(782, 388);
		this.setLayout(null);
		this.add(lblNombre, null);
		this.add(lblApellidos, null);
		this.add(lblDNI, null);
		this.add(lbldireccion, null);
		this.add(lblCodigoPostal, null);
		this.add(lblPoblacion, null);
		this.add(lblProvincia, null);
		this.add(lblteléfono, null);
		this.add(lblEmail, null);
		this.add(lblPass, null);
		this.add(lblTipo, null);
		this.add(lblFechaNac, null);
		this.add(getBGuardar(), null);
		this.add(getBRestablecer(), null);
		this.add(getTxtDNI(), null);
		this.add(getTxtNombre(), null);
		this.add(getTxtApellidos(), null);
		this.add(getTxtFechaNac(), null);
		this.add(getTxtDireccion(), null);
		this.add(getTxtCP(), null);
		this.add(getTxtProvincia(), null);
		this.add(getTxtTelefono(), null);
		this.add(getTxteMail(), null);
		this.add(getTxtPass(), null);
		this.add(getComboTipo(), null);
		this.add(lblObligtorios, null);
		this.add(getComboPoblacion(), null);
	}

	/**
	 * This method initializes bGuardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBGuardar() {
		if (bGuardar == null) {
			bGuardar = new JButton();
			bGuardar.setBounds(new Rectangle(46, 314, 53, 45));
			bGuardar.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/Guardar.png")));
			bGuardar.setToolTipText("Guardar Nuevo Usuario");
			bGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return bGuardar;
	}

	/**
	 * This method initializes bRestablecer	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBRestablecer() {
		if (bRestablecer == null) {
			bRestablecer = new JButton();
			bRestablecer.setBounds(new Rectangle(122, 314, 53, 45));
			bRestablecer.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/Limpiar.png")));
			bRestablecer.setToolTipText("Limpiar todos los campos");
			bRestablecer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					txtDNI.setText("");
					txtNombre.setText("");
					txtApellidos.setText("");
					txtFechaNac.setText("");
					txtDireccion.setText("");
					txtCP.setText("");
					comboPoblacion.removeAll();
					txtProvincia.setText("");
					txtTelefono.setText("");
					txteMail.setText("");
					txtPass.setText("");
					
					
				}
			});
		}
		return bRestablecer;
	}

	/**
	 * This method initializes txtDNI	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setBounds(new Rectangle(123, 33, 143, 27));
		}
		return txtDNI;
	}

	/**
	 * This method initializes txtNombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(new Rectangle(123, 86, 143, 27));
		}
		return txtNombre;
	}

	/**
	 * This method initializes txtApellidos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setBounds(new Rectangle(358, 86, 115, 27));
		}
		return txtApellidos;
	}

	/**
	 * This method initializes txtFechaNac	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtFechaNac() {
		if (txtFechaNac == null) {
			txtFechaNac = new JTextField();
			txtFechaNac.setBounds(new Rectangle(634, 86, 116, 27));
		}
		return txtFechaNac;
	}

	/**
	 * This method initializes txtDireccion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDireccion() {
		if (txtDireccion == null) {
			txtDireccion = new JTextField();
			txtDireccion.setBounds(new Rectangle(123, 139, 143, 27));
		}
		return txtDireccion;
	}

	/**
	 * This method initializes txtCP	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCP() {
		if (txtCP == null) {
			txtCP = new JTextField();
			txtCP.setBounds(new Rectangle(358, 139, 115, 27));
			txtCP.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					
					if(txtCP.getText().length() > 0){
						
						try {
							
							String codigoPostal = txtCP.getText();
							String sentencia = "SELECT poblacion.poblacion, provincia.provincia FROM poblacion INNER JOIN provincia WHERE provincia.idprovincia = poblacion.idprovincia AND postal = '"+ codigoPostal +"' LIMIT 0,30";
							
							ConectarDBA.acceder(); // Conectamos con la base de datos
							
							ResultSet rs = ConectarDBA.consulta(sentencia);
							
							while(rs.next()){
							
							comboPoblacion.addItem(rs.getString(1));
							txtProvincia.setText(rs.getString(2));
							
							}
							
							ConectarDBA.cerrarCon(); // Cerramos la conexion
							
						} catch (SQLException e1) {
							
							JOptionPane.showMessageDialog(null,"El código postal no pertenece a ningún municipio");
						}

						
					}else{
						
												
					}
					
				}
			});
		}
		return txtCP;
	}

	/**
	 * This method initializes txtProvincia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtProvincia() {
		if (txtProvincia == null) {
			txtProvincia = new JTextField();
			txtProvincia.setBounds(new Rectangle(123, 194, 143, 27));
		}
		return txtProvincia;
	}

	/**
	 * This method initializes txtTelefono	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtTelefono() {
		if (txtTelefono == null) {
			txtTelefono = new JTextField();
			txtTelefono.setBounds(new Rectangle(358, 194, 115, 27));
		}
		return txtTelefono;
	}

	/**
	 * This method initializes txteMail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxteMail() {
		if (txteMail == null) {
			txteMail = new JTextField();
			txteMail.setBounds(new Rectangle(634, 194, 116, 27));
		}
		return txteMail;
	}

	/**
	 * This method initializes txtPass	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getTxtPass() {
		if (txtPass == null) {
			txtPass = new JPasswordField();
			txtPass.setBounds(new Rectangle(123, 250, 143, 27));
		}
		return txtPass;
	}

	/**
	 * This method initializes comboTipo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboTipo() {
		if (comboTipo == null) {
			comboTipo = new JComboBox();
			comboTipo.setBounds(new Rectangle(368, 253, 115, 22));
			
			comboTipo.addItem("Administrador");
			comboTipo.addItem("Trabajador");
			
		}
		return comboTipo;
	}

	/**
	 * This method initializes comboPoblacion	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboPoblacion() {
		if (comboPoblacion == null) {
			comboPoblacion = new JComboBox();
			comboPoblacion.setBounds(new Rectangle(634, 142, 116, 27));
			comboPoblacion.setEditable(true);
			comboPoblacion.setFocusable(true);
			comboPoblacion.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					
					if(txtCP.getText().length() == 0){
							
						
						try {
							
							String poblacion = comboPoblacion.getSelectedItem().toString();
							String sentencia = "SELECT poblacion.poblacion, provincia.provincia FROM poblacion INNER JOIN provincia WHERE provincia.idprovincia = poblacion.idprovincia AND poblacion = '"+ poblacion +"' LIMIT 0,30";
							
							ConectarDBA.acceder(); // Conectamos con la base de datos
							
							ResultSet rs = ConectarDBA.consulta(sentencia);
							
							while(rs.next()){
							
							comboPoblacion.addItem(rs.getString(1));
							txtProvincia.setText(rs.getString(2));
							
							}
							
							ConectarDBA.cerrarCon(); // Cerramos la conexion
							
						} catch (SQLException e1) {
							
							JOptionPane.showMessageDialog(null,"El código postal no pertenece a ningún municipio");
						}

						
					}else{
						
												
					}
					
				}
			});
			
		}
		return comboPoblacion;
	}

}  //  @jve:decl-index=0:visual-constraint="26,16"
