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

import code.google.com.opengis.gestion.Usuarios;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

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
	private JTextField txtMail = null;
	private JPasswordField txtPass = null;
	private JComboBox comboTipo = null;
	private JLabel lblObligtorios = null;
	private JComboBox comboPoblacion = null;
	private JLabel lblPass2 = null;
	private JPasswordField txtPass2 = null;
	private String accion;

	private String dni = ""; //$NON-NLS-1$
	private String nombre = ""; //$NON-NLS-1$
	private String apellidos = ""; //$NON-NLS-1$
	private String direccion = ""; //$NON-NLS-1$
	private String poblacion = " "; //$NON-NLS-1$
	private String provincia = ""; //$NON-NLS-1$
	private String cp = ""; //$NON-NLS-1$
	private String telefono = ""; //$NON-NLS-1$
	private String email = ""; //$NON-NLS-1$
	private String fechanac = ""; //$NON-NLS-1$

	/**
	 * Constructor del Panel de gestión de Usuarios. En caso de que la acción
	 * sea "modificar" el panel se utilizará para modificar. En caso de que la
	 * acción sea "alta" el panel se utilizará como altas.
	 */
	public UsuariosPanelNuevo(String accion, String dni, String nombre,
			String apellidos, String direccion, String poblacion,
			String provincia, String cp, String telefono, String email,
			String fechanac) {
		super();
		this.accion = accion;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.provincia = provincia;
		this.cp = cp;
		this.telefono = telefono;
		this.email = email;
		this.fechanac = fechanac;
		initialize();
	}

	public UsuariosPanelNuevo(String accion) {

		super();
		this.accion = accion;

		initialize();

	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		lblPass2 = new JLabel();
		lblPass2.setBounds(new Rectangle(274, 248, 116, 30));
		lblPass2.setText(Idioma.getString("etConfirmPassword")); //$NON-NLS-1$
		lblObligtorios = new JLabel();
		lblObligtorios.setBounds(new Rectangle(434, 334, 238, 25));
		lblObligtorios.setFont(new Font(
				Idioma.getString("Dialog"), Font.ITALIC, 12)); //$NON-NLS-1$
		lblObligtorios.setText(Idioma.getString("etAllFields")); //$NON-NLS-1$
		lblFechaNac = new JLabel();
		lblFechaNac.setBounds(new Rectangle(506, 83, 138, 30));
		lblFechaNac.setText(Idioma.getString("etBirthDate")); //$NON-NLS-1$
		lblTipo = new JLabel();
		lblTipo.setBounds(new Rectangle(509, 248, 102, 30));
		lblTipo.setText(Idioma.getString("etUserPermits")); //$NON-NLS-1$
		lblPass = new JLabel();
		lblPass.setBounds(new Rectangle(42, 248, 88, 30));
		lblPass.setText(Idioma.getString("etPassword")); //$NON-NLS-1$
		lblEmail = new JLabel();
		lblEmail.setBounds(new Rectangle(506, 191, 88, 30));
		lblEmail.setText(Idioma.getString("etMail")); //$NON-NLS-1$
		lblteléfono = new JLabel();
		lblteléfono.setBounds(new Rectangle(274, 192, 88, 30));
		lblteléfono.setText(Idioma.getString("etPhone")); //$NON-NLS-1$
		lblProvincia = new JLabel();
		lblProvincia.setBounds(new Rectangle(44, 192, 88, 30));
		lblProvincia.setText(Idioma.getString("etProvince")); //$NON-NLS-1$
		lblPoblacion = new JLabel();
		lblPoblacion.setBounds(new Rectangle(506, 136, 88, 30));
		lblPoblacion.setText(Idioma.getString("etCity")); //$NON-NLS-1$
		lblCodigoPostal = new JLabel();
		lblCodigoPostal.setBounds(new Rectangle(274, 137, 88, 30));
		lblCodigoPostal.setText(Idioma.getString("etPostalCode")); //$NON-NLS-1$
		lbldireccion = new JLabel();
		lbldireccion.setBounds(new Rectangle(42, 137, 88, 30));
		lbldireccion.setText(Idioma.getString("etAddress")); //$NON-NLS-1$
		lblDNI = new JLabel();
		lblDNI.setBounds(new Rectangle(42, 31, 88, 30));
		lblDNI.setText(Idioma.getString("etIdCard")); //$NON-NLS-1$
		lblApellidos = new JLabel();
		lblApellidos.setBounds(new Rectangle(274, 84, 88, 30));
		lblApellidos.setText(Idioma.getString("etLastName")); //$NON-NLS-1$
		lblNombre = new JLabel();
		lblNombre.setBounds(new Rectangle(42, 84, 88, 30));
		lblNombre.setText(Idioma.getString("etFirstName")); //$NON-NLS-1$
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
		this.add(lblPass2, null);
		this.add(getTxtPass2(), null);
		comboPoblacion.setEditable(false);
		txtProvincia.setEditable(false);

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
			bGuardar.setIcon(new ImageIcon(getClass().getResource(
					"/recursosVisuales/Guardar.png"))); //$NON-NLS-1$
			bGuardar.setToolTipText(Idioma.getString("etSaveNewUser")); //$NON-NLS-1$
			bGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					boolean valido = false;
					char[] contra = txtPass.getPassword();
					String pass = new String(contra);

					char[] contra2 = txtPass2.getPassword();
					String pass2 = new String(contra2);
					String[] campos = {
							txtDNI.getText(),
							txtNombre.getText(),
							txtApellidos.getText(),
							txtTelefono.getText(),
							txtDireccion.getText(),
							txtProvincia.getText(),
							txtCP.getText(),
							txtFechaNac.getText(),
							pass,
							comboTipo.getSelectedItem().toString()
									.toLowerCase(), txtMail.getText() };
					if (accion.equals("alta")) { //$NON-NLS-1$

						for (int i = 0; i < campos.length; i++) {
							String campo = campos[i];
							if (campo.length() == 0) {
								valido = false;
								break;
							} else {
								valido = true;
							}

						}

						if (valido == true) {
							if (pass.equals(pass2)) {

								Usuarios u = new Usuarios(txtDNI.getText()
										.trim(), txtNombre.getText().trim(),
										txtApellidos.getText().trim(),
										txtTelefono.getText().trim(),
										txtDireccion.getText().trim(),
										comboPoblacion.getSelectedItem()
												.toString(), txtProvincia
												.getText().trim(), txtCP
												.getText().trim(), txtFechaNac
												.getText().trim(), pass,
										comboTipo.getSelectedItem().toString()
												.toLowerCase(), txtMail
												.getText().trim());
								u.validarDatos();
								if (u.getValido()) {
									try {
										u.crearUsuario();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(
												null,
												Idioma.getString("msgIDAlreadyExists")); //$NON-NLS-1$
									}

								}

							} else {

								JOptionPane.showMessageDialog(null,
										Idioma.getString("msgPasswordUnmatch")); //$NON-NLS-1$

							}
						} else {
							JOptionPane.showMessageDialog(null,
									Idioma.getString("msgErrorField") //$NON-NLS-1$
											+ Idioma.getString("etAllFields")); //$NON-NLS-1$
						}
					} else {

						// Aquí entraremos cuando la acción declarada sea
						// modificar

						for (int i = 0; i < campos.length; i++) {
							String campo = campos[i];
							if (campo.equals("")) {
								JOptionPane.showMessageDialog(
										null,
										Idioma.getString("msgErrorField") //$NON-NLS-1$
												+ Idioma.getString("etAllFields")); //$NON-NLS-1$
								valido = false;
							}

						}
						if (valido == true) {

							if (pass.equals(pass2)) {
								Usuarios u = new Usuarios(txtDNI.getText(),
										txtNombre.getText(), txtApellidos
												.getText(), txtTelefono
												.getText(), txtDireccion
												.getText(), comboPoblacion
												.getSelectedItem().toString(),
										txtProvincia.getText(),
										txtCP.getText(), txtFechaNac.getText(),
										pass, comboTipo.getSelectedItem()
												.toString().toLowerCase(),
										txtMail.getText());
								u.validarDatos();

								if (u.getValido()) {

									try {
										u.modificarUsuario();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

								}
							} else {

								JOptionPane.showMessageDialog(null,
										Idioma.getString("msgPasswordUnmatch")); //$NON-NLS-1$

							}
						}

					}
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
			bRestablecer.setIcon(new ImageIcon(getClass().getResource(
					"/recursosVisuales/Limpiar.png"))); //$NON-NLS-1$
			bRestablecer.setToolTipText(Idioma.getString("etCleanFields")); //$NON-NLS-1$
			bRestablecer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					if (accion != "modificar") { //$NON-NLS-1$
						restablecerCampos(false);

					}
					restablecerCampos(true);

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
			txtDNI = new JTextField(dni);
			txtDNI.setBounds(new Rectangle(123, 33, 143, 27));

			if (accion == "modificar") { //$NON-NLS-1$

				txtDNI.setEnabled(false);
			}

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
			txtNombre = new JTextField(nombre);
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
			txtApellidos = new JTextField(apellidos);
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
			txtFechaNac = new JTextField(fechanac);
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
			txtDireccion = new JTextField(direccion);
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
			txtCP = new JTextField(cp);
			txtCP.setBounds(new Rectangle(358, 139, 115, 27));
			txtCP.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {

					if (txtCP.getText().length() > 0) {

						try {

							comboPoblacion.removeAllItems();

							String codigoPostal = txtCP.getText();
							String sentencia = "SELECT poblacion.poblacion, provincia.provincia FROM poblacion INNER JOIN provincia WHERE provincia.idprovincia = poblacion.idprovincia AND postal = '" + codigoPostal + "' LIMIT 0,30"; //$NON-NLS-1$ //$NON-NLS-2$

							ConectarDBA.acceder(); // Conectamos con la base de
													// datos

							ResultSet rs = ConectarDBA.consulta(sentencia);

							while (rs.next()) {

								comboPoblacion.addItem(rs.getString(1));
								txtProvincia.setText(rs.getString(2));

							}

							ConectarDBA.cerrarCon(); // Cerramos la conexion

						} catch (SQLException e1) {

							JOptionPane.showMessageDialog(null,
									Idioma.getString("etPostalCodeUnmatch")); //$NON-NLS-1$
						}

					} else {

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
			txtProvincia = new JTextField(provincia);
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
			txtTelefono = new JTextField(telefono);
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
		if (txtMail == null) {
			txtMail = new JTextField(email);
			txtMail.setBounds(new Rectangle(634, 194, 116, 27));
		}
		return txtMail;
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
			comboTipo.setBounds(new Rectangle(634, 250, 115, 27));

			comboTipo.addItem(Idioma.getString("etWorker")); //$NON-NLS-1$
			comboTipo.addItem(Idioma.getString("etAdmin")); //$NON-NLS-1$

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
			comboPoblacion.addItem(poblacion);
			comboPoblacion.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {

					if (txtCP.getText().length() == 0) {

						try {

							String poblacion = comboPoblacion.getSelectedItem()
									.toString();
							String sentencia = "SELECT poblacion.poblacion, provincia.provincia FROM poblacion INNER JOIN provincia WHERE provincia.idprovincia = poblacion.idprovincia AND poblacion = '" + poblacion + "' LIMIT 0,30"; //$NON-NLS-1$ //$NON-NLS-2$

							ConectarDBA.acceder(); // Conectamos con la base de
													// datos

							ResultSet rs = ConectarDBA.consulta(sentencia);

							while (rs.next()) {

								comboPoblacion.addItem(rs.getString(1));

								txtProvincia.setText(rs.getString(2));

							}

							ConectarDBA.cerrarCon(); // Cerramos la conexion

						} catch (SQLException e1) {

							JOptionPane.showMessageDialog(null,
									Idioma.getString("etPostalCodeUnmatch")); //$NON-NLS-1$
						}

					} else {

					}

				}
			});

		}
		return comboPoblacion;
	}

	/**
	 * This method initializes txtPass2
	 * 
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getTxtPass2() {
		if (txtPass2 == null) {
			txtPass2 = new JPasswordField();
			txtPass2.setBounds(new Rectangle(387, 250, 85, 27));
		}
		return txtPass2;
	}

	private void restablecerCampos(boolean modificar) {
		if (modificar == false) {
			txtDNI.setText(""); //$NON-NLS-1$
		}
		txtNombre.setText(""); //$NON-NLS-1$
		txtApellidos.setText(""); //$NON-NLS-1$
		txtFechaNac.setText(""); //$NON-NLS-1$
		txtDireccion.setText(""); //$NON-NLS-1$
		txtCP.setText(""); //$NON-NLS-1$
		comboPoblacion.removeAllItems();
		txtProvincia.setText(""); //$NON-NLS-1$
		txtTelefono.setText(""); //$NON-NLS-1$
		txtMail.setText(""); //$NON-NLS-1$
		txtPass.setText(""); //$NON-NLS-1$
		txtPass2.setText(""); //$NON-NLS-1$
	}

} // @jve:decl-index=0:visual-constraint="26,16"
