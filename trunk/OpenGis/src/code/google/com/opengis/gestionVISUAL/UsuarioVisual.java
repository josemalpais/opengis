/*****************************************************************************
 * Copyright (c) 2011 [OpenGisTeam]                                           *
 ******************************************************************************/
package code.google.com.opengis.gestionVISUAL;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import code.google.com.opengis.gestion.Usuarios;
import code.google.com.opengis.gestionDAO.UsuariosDAO;

/**
 * @author Juan Carlos García Clase que genera una ventana interior en el
 *         formulario MDI. Esta ventana es la encargada de gestionar los
 *         Usuarios.
 * 
 */

public class UsuarioVisual extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel panelUsuarios;
	private static JPanel panelUsuariosCrear;
	private static JPanel panelUsuariosMod;

	private static JTextField txtDNI = new JTextField();
	private static JTextField txtNombre = new JTextField();
	private static JTextField txtApellidos = new JTextField();
	private static DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormatter mascaraFecha = new DateFormatter(formato);
	private static JFormattedTextField txtFNac = new JFormattedTextField(
			mascaraFecha);
	private static JTextField txtDir = new JTextField();
	private static JTextField txtPob = new JTextField();
	private static JTextField txtProv = new JTextField();
	private static JTextField txtCp = new JTextField();
	private static JTextField txtTlf = new JTextField();
	private static JTextField txtEmail = new JTextField();
	private static JPasswordField txtCon = new JPasswordField();
	private static JPasswordField txtConfCon = new JPasswordField();

	private static String[] tipo = { "Trabajador", "Administrador" };
	private static JComboBox jCmbTipo = new JComboBox(tipo);

	private static JTextField txtBuscar = new JTextField();
	private static String[] criterio = { "DNI", "Nombre", "Apellidos" };
	private static JComboBox jCmbCriterio = new JComboBox(criterio);

	private static JScrollPane scrollUsuarios;
	private static JTable jTablaUsuarios = null;
	static Object[] nombreColumna = { "DNI", "Nombre", "Apellidos",
			"Dirección", "Poblacion", "Provincia", "Cod. Postal", "Teléfono",
			"Email", "Fecha Nacimiento", "Tipo", "Estado" };
	static DefaultTableModel modelo;

	/**
	 * Constructor de la clase UsuarioVisual. Se le pasarán los parametros
	 * necesarios para construir el alto y el ancho.
	 */

	public UsuarioVisual(int ancho, int alto) {
		super("Usuarios", true, true, true, true);
		super.setIconifiable(true); // Indicamos que se puede minimizar
		cargarUsuariosPrincipal();
		panelUsuariosCrear = new JPanel();
		panelUsuariosCrear.setLayout(null);
		this.add(panelUsuariosCrear);
		panelUsuariosCrear.setVisible(false);
		panelUsuariosMod = new JPanel();
		panelUsuariosMod.setLayout(null);
		this.add(panelUsuariosMod);
		panelUsuariosMod.setVisible(false);
		this.add(panelUsuarios);
		this.setBounds(0, 0, ancho, alto);
		this.setTitle("Usuario");
		this.setClosable(true);
		this.setMaximizable(true);
		TitledBorder jb = new TitledBorder("Gestión de usuarios");
		TitledBorder jb1 = new TitledBorder("Añadir usuarios");
		TitledBorder jb2 = new TitledBorder("Modificar usuarios");
		panelUsuarios.setBorder(jb);
		panelUsuariosCrear.setBorder(jb1);
		panelUsuariosMod.setBorder(jb2);
		panelUsuarios.setBounds(new Rectangle(0, 0, ancho, alto));
		panelUsuariosCrear.setBounds(new Rectangle(0, 0, 800, 600));
		panelUsuariosMod.setBounds(new Rectangle(0, 0, 800, 600));
		txtFNac.setValue(new Date());

		panelUsuarios.setVisible(true);

	}

	public static void cargarUsuariosPrincipal() {
		JButton boton;
		JLabel campolbl;
		panelUsuarios = new JPanel();
		panelUsuarios.setLayout(new GridBagLayout());

		txtBuscar.setPreferredSize(new Dimension(120, 20));

		// Se crean 3 constraints, uno para cada uso.
		GridBagConstraints cText = new GridBagConstraints(); // Cajas de texto
		GridBagConstraints cButtons = new GridBagConstraints(); // Botones
		GridBagConstraints cLabels = new GridBagConstraints(); // Labels
		GridBagConstraints cTabla = new GridBagConstraints();

		cLabels.insets = new Insets(8, 8, 8, 8); // top padding

		cLabels.weightx = 0.005;
		cLabels.anchor = GridBagConstraints.EAST;
		cText.anchor = GridBagConstraints.EAST;
		cText.weightx = 0.5;
		cText.ipadx = 100;

		cText.gridx = 0;
		cText.gridy = 0;
		cText.gridwidth = 2;
		txtBuscar.setText("");
		panelUsuarios.add(txtBuscar, cText);
		cText.gridwidth = 1;

		campolbl = new JLabel("Buscar por:");
		cLabels.gridx = 2;
		cLabels.gridy = 0;
		panelUsuarios.add(campolbl, cLabels);

		cText.anchor = GridBagConstraints.WEST;
		cText.gridx = 3;
		cText.gridy = 0;
		panelUsuarios.add(jCmbCriterio, cText);

		boton = new JButton("Buscar");
		cButtons.anchor = GridBagConstraints.WEST;
		cButtons.insets = new Insets(0, 0, 0, 0);
		cButtons.gridx = 4;
		cButtons.gridy = 0;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				buscar();

			}
		});
		panelUsuarios.add(boton, cButtons);

		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(nombreColumna);
		jTablaUsuarios = new JTable(modelo);
		scrollUsuarios = new JScrollPane(jTablaUsuarios);
		cTabla.gridx = 0;
		cTabla.gridy = 1;
		cTabla.gridwidth = 5;
		cTabla.gridheight = 5;
		cTabla.fill = GridBagConstraints.HORIZONTAL;
		panelUsuarios.add(scrollUsuarios, cTabla);

		boton = new JButton("Nuevo");
		cButtons.anchor = GridBagConstraints.EAST;
		cButtons.insets = new Insets(0, 0, 0, 0);
		cButtons.gridx = 0;
		cButtons.gridy = 7;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				panelUsuarios.setVisible(false);
				cargarNuevoUser(panelUsuariosCrear, false);
				panelUsuariosCrear.setVisible(true);
			}
		});
		panelUsuarios.add(boton, cButtons);

		boton = new JButton("Modificar");
		cButtons.anchor = GridBagConstraints.WEST;
		cButtons.insets = new Insets(0, 0, 0, 0);
		cButtons.gridx = 1;
		cButtons.gridy = 7;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				int fila = jTablaUsuarios.getSelectedRow();
				if (fila != -1) {
					String[] rUser = new String[10];
					for (int i = 0; i < rUser.length; i++) {
						rUser[i] = jTablaUsuarios.getValueAt(fila, i)
								.toString();
					}

					panelUsuarios.setVisible(false);
					cargarNuevoUser(panelUsuariosMod, true);
					restablecerCampos();
					setCampos(rUser);
					txtDNI.setEditable(false);
					panelUsuariosMod.setVisible(true);
					limpiarTabla(modelo.getRowCount());

				}

			}
		});
		panelUsuarios.add(boton, cButtons);

		boton = new JButton("Activar");
		cButtons.anchor = GridBagConstraints.LINE_END;
		cButtons.insets = new Insets(0, 0, 0, 0);
		cButtons.gridx = 2;
		cButtons.gridy = 7;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				int fila = jTablaUsuarios.getSelectedRow();
				if (fila != -1) {
					String[] rUser = new String[10];
					for (int i = 0; i < rUser.length; i++) {
						rUser[i] = jTablaUsuarios.getValueAt(fila, i)
								.toString();
					}
					try {
						UsuariosDAO.ActivarUsuario(rUser[0]);
						buscar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		panelUsuarios.add(boton, cButtons);

		boton = new JButton("Desactivar");
		cButtons.anchor = GridBagConstraints.LINE_START;
		cButtons.insets = new Insets(0, 0, 0, 0);
		cButtons.gridx = 3;
		cButtons.gridy = 7;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				int fila = jTablaUsuarios.getSelectedRow();
				if (fila != -1) {
					String[] rUser = new String[10];
					for (int i = 0; i < rUser.length; i++) {
						rUser[i] = jTablaUsuarios.getValueAt(fila, i)
								.toString();
					}
					try {
						UsuariosDAO.DesactivarUsuario(rUser[0]);
						buscar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		panelUsuarios.add(boton, cButtons);

		boton = new JButton("Limpiar Tabla");
		cButtons.anchor = GridBagConstraints.LINE_START;
		cButtons.insets = new Insets(0, 0, 0, 0);
		cButtons.gridx = 4;
		cButtons.gridy = 7;
		panelUsuarios.add(boton, cButtons);
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				txtBuscar.setText("");

				limpiarTabla(modelo.getRowCount());

			}
		});

	}

	/**
	 * Este método carga en el frame que le indiquemos los objetos para Crear
	 * Usuarios o Modificarlos.
	 * 
	 * @param pane
	 *            Indica el contenedor de todos
	 * @param modificar
	 *            Indica si vamos a modificar el Usuario (true) o vamos a crear
	 *            uno nuevo (false)
	 */
	public static void cargarNuevoUser(final Container pane, boolean modificar) {
		restablecerCampos();
		pane.removeAll();
		pane.repaint();
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

		// Se crean 3 constraints, uno para cada uso.
		GridBagConstraints cNText = new GridBagConstraints(); // Cajas de texto
		GridBagConstraints cNButtons = new GridBagConstraints(); // Botones
		GridBagConstraints cNLabels = new GridBagConstraints(); // Labels

		cNLabels.insets = new Insets(8, 8, 8, 8); // top padding
		cNLabels.anchor = GridBagConstraints.EAST;
		cNText.anchor = GridBagConstraints.WEST;
		cNText.weightx = 0.5;
		cNText.ipadx = 100;

		campolbl = new JLabel("DNI:");
		cNLabels.weightx = 0.005;
		cNLabels.gridx = 0;
		cNLabels.gridy = 0;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 1;
		cNText.gridy = 0;
		txtDNI.setEditable(true);
		pane.add(txtDNI, cNText);

		campolbl = new JLabel("Nombre:");
		cNLabels.weightx = 0.005;
		cNLabels.gridx = 2;
		cNLabels.gridy = 0;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 3;
		cNText.gridy = 0;
		pane.add(txtNombre, cNText);

		campolbl = new JLabel("Apellidos:");
		cNLabels.gridx = 4;
		cNLabels.gridy = 0;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 5;
		cNText.gridy = 0;
		pane.add(txtApellidos, cNText);

		campolbl = new JLabel("Fecha de Nacimiento:");
		cNLabels.gridx = 0;
		cNLabels.gridy = 1;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 1;
		cNText.gridy = 1;
		pane.add(txtFNac, cNText);

		// Añade JLabel de Teléfono y JTextField de teléfono
		campolbl = new JLabel("Teléfono:");
		cNLabels.gridx = 2;
		cNLabels.gridy = 1;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 3;
		cNText.gridy = 1;
		pane.add(txtTlf, cNText);

		campolbl = new JLabel("Dirección:");
		cNLabels.gridx = 4;
		cNLabels.gridy = 1;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 5;
		cNText.gridy = 1;
		pane.add(txtDir, cNText);

		campolbl = new JLabel("Población:");
		cNLabels.gridx = 0;
		cNLabels.gridy = 2;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 1;
		cNText.gridy = 2;
		pane.add(txtPob, cNText);

		campolbl = new JLabel("Provincia:");
		cNLabels.gridx = 2;
		cNLabels.gridy = 2;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 3;
		cNText.gridy = 2;
		pane.add(txtProv, cNText);

		campolbl = new JLabel("CP:");
		cNLabels.gridx = 4;
		cNLabels.gridy = 2;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 5;
		cNText.gridy = 2;
		pane.add(txtCp, cNText);

		campolbl = new JLabel("Email:");
		cNLabels.gridx = 0;
		cNLabels.gridy = 3;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 1;
		cNText.gridy = 3;
		pane.add(txtEmail, cNText);

		campolbl = new JLabel("Contraseña:");
		cNLabels.gridx = 2;
		cNLabels.gridy = 3;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 3;
		cNText.gridy = 3;
		pane.add(txtCon, cNText);

		campolbl = new JLabel("Confirmar contraseña:");
		cNLabels.gridx = 4;
		cNLabels.gridy = 3;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 5;
		cNText.gridy = 3;
		pane.add(txtConfCon, cNText);

		campolbl = new JLabel("Tipo de usuario");
		cNLabels.gridx = 4;
		cNLabels.gridy = 5;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 5;
		cNText.gridy = 5;
		pane.add(jCmbTipo, cNText);

		boton = new JButton("Guardar");
		cNButtons.fill = 0;
		cNButtons.anchor = GridBagConstraints.PAGE_END; // bottom of space
		cNButtons.insets = new Insets(15, 0, 0, 0); // top padding
		cNButtons.gridx = 0;
		cNButtons.gridy = 5;

		if (modificar == false) {
			boton = new JButton("Guardar");
			cNButtons.fill = 0;
			cNButtons.anchor = GridBagConstraints.PAGE_END; // bottom of space
			cNButtons.insets = new Insets(15, 0, 0, 0); // top padding
			cNButtons.gridx = 0;
			cNButtons.gridy = 5;
			boton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					char[] contra = txtCon.getPassword();
					String pass = new String(contra);

					char[] contra2 = txtConfCon.getPassword();
					String pass2 = new String(contra2);

					if (pass.equals(pass2)) {

						Usuarios u = new Usuarios(txtDNI.getText(), txtNombre
								.getText(), txtApellidos.getText(), txtTlf
								.getText(), txtDir.getText(), txtPob.getText(),
								txtProv.getText(), txtCp.getText(), txtFNac
										.getText(), pass, jCmbTipo
										.getSelectedItem().toString()
										.toLowerCase(), txtEmail.getText());
						u.validarDatos();
						System.out.println(jCmbTipo.getSelectedItem()
								.toString());
						if (u.getValido()) {
							u.crearUsuario();
							restablecerCampos();
						}

					} else {

						JOptionPane.showMessageDialog(null,
								"Error. Las contraseñas no coinciden");

					}

				}
			});
			pane.add(boton, cNButtons);
		} else if (modificar == true) {
			boton = new JButton("Modificar");
			cNButtons.fill = 0;
			cNButtons.anchor = GridBagConstraints.PAGE_END; // bottom of space
			cNButtons.insets = new Insets(15, 0, 0, 0); // top padding
			cNButtons.gridx = 0;
			cNButtons.gridy = 5;
			boton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					char[] contra = txtCon.getPassword();
					String pass = new String(contra);

					char[] contra2 = txtConfCon.getPassword();
					String pass2 = new String(contra2);

					if (pass.equals(pass2)) {
						Usuarios u = new Usuarios(txtDNI.getText(), txtNombre
								.getText(), txtApellidos.getText(), txtTlf
								.getText(), txtDir.getText(), txtPob.getText(),
								txtProv.getText(), txtCp.getText(), txtFNac
										.getText(), txtCon.getText(), jCmbTipo
										.getSelectedItem().toString()
										.toLowerCase(), txtEmail.getText());
						u.validarDatos();
						System.out.println(jCmbTipo.getSelectedItem()
								.toString());
						if (u.getValido()) {
							u.modificarUsuario();
							pane.setVisible(false);
							panelUsuarios.setVisible(true);
							restablecerCampos();
						}
					} else {

						JOptionPane.showMessageDialog(null,
								"Error. Las contraseñas no coinciden");

					}

				}
			});
			pane.add(boton, cNButtons);
		}

		boton = new JButton("Limpiar");
		cNButtons.fill = 0;
		cNButtons.insets = new Insets(15, 15, 0, 0); // top padding
		cNButtons.gridx = 1;
		cNButtons.gridy = 5;
		cNButtons.anchor = GridBagConstraints.WEST;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				restablecerCampos();
				JOptionPane.showMessageDialog(null,
						"Los campos se han restablecido");

			}
		});
		pane.add(boton, cNButtons);

		boton = new JButton("Volver");
		cNButtons.fill = 0;
		cNButtons.insets = new Insets(15, 15, 0, 0); // top padding
		cNButtons.gridx = 2;
		cNButtons.gridy = 5;
		cNButtons.anchor = GridBagConstraints.WEST;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pane.setVisible(false);
				panelUsuarios.setVisible(true);
				restablecerCampos();
			}
		});
		pane.add(boton, cNButtons);

	}

	public static void cargarModUser(final Container pane) {
		pane.removeAll();
		pane.repaint();
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

		// Se crean 3 constraints, uno para cada uso.
		GridBagConstraints cText = new GridBagConstraints(); // Cajas de texto
		GridBagConstraints cButtons = new GridBagConstraints(); // Botones
		GridBagConstraints cLabels = new GridBagConstraints(); // Labels

		cLabels.insets = new Insets(8, 8, 8, 8); // top padding
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
		txtDNI.setEditable(false);
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

		// Añade JLabel de Teléfono y JTextField de teléfono
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

		campolbl = new JLabel("Confirmar contraseña:");
		cLabels.gridx = 4;
		cLabels.gridy = 3;
		pane.add(campolbl, cLabels);

		cText.gridx = 5;
		cText.gridy = 3;
		pane.add(txtConfCon, cText);

		campolbl = new JLabel("Tipo de usuario");
		cLabels.gridx = 4;
		cLabels.gridy = 5;
		pane.add(campolbl, cLabels);

		cText.gridx = 5;
		cText.gridy = 5;
		pane.add(jCmbTipo, cText);

		boton = new JButton("Modificar");
		cButtons.fill = 0;
		cButtons.anchor = GridBagConstraints.PAGE_END; // bottom of space
		cButtons.insets = new Insets(15, 0, 0, 0); // top padding
		cButtons.gridx = 0;
		cButtons.gridy = 5;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				Usuarios u = new Usuarios(txtDNI.getText(),
						txtNombre.getText(), txtApellidos.getText(), txtTlf
								.getText(), txtDir.getText(), txtPob.getText(),
						txtProv.getText(), txtCp.getText(), txtFNac.getText(),
						txtCon.getText(), jCmbTipo.getSelectedItem().toString()
								.toLowerCase(), txtEmail.getText());
				u.validarDatos();
				System.out.println(jCmbTipo.getSelectedItem().toString());
				if (u.getValido()) {
					u.modificarUsuario();
				}

			}
		});
		pane.add(boton, cButtons);

		boton = new JButton("Limpiar");
		cButtons.fill = 0;
		cButtons.insets = new Insets(15, 15, 0, 0); // top padding
		cButtons.gridx = 1;
		cButtons.gridy = 5;
		cButtons.anchor = GridBagConstraints.WEST;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				restablecerCampos();

				JOptionPane.showMessageDialog(null,
						"Los campos se han restablecido");

			}
		});
		pane.add(boton, cButtons);

		boton = new JButton("Volver");
		cButtons.fill = 0;
		cButtons.insets = new Insets(15, 15, 0, 0); // top padding
		cButtons.gridx = 2;
		cButtons.gridy = 5;
		cButtons.anchor = GridBagConstraints.WEST;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pane.setVisible(false);
				panelUsuarios.setVisible(true);
				restablecerCampos();
			}
		});
		pane.add(boton, cButtons);

	}

	public String[] getTipo() {
		return tipo;
	}

	public static void setCampos(String[] rUser) {
		txtDNI.setText(rUser[0]);
		txtNombre.setText(rUser[1]);
		txtApellidos.setText(rUser[2]);
		txtDir.setText(rUser[3]);
		txtPob.setText(rUser[4]);
		txtProv.setText(rUser[5]);
		txtCp.setText(rUser[6]);
		txtTlf.setText(rUser[7]);
		txtEmail.setText(rUser[8]);
		txtFNac.setText(rUser[9]);
		txtCon.setText("");
		for (int i = 0; i < rUser.length; i++) {
			System.out.println(rUser[i]);
		}
	}

	public static void restablecerCampos() {
		
		
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
	}

	public void setTipo(String[] tipo) {
		this.tipo = tipo;
	}

	public static void limpiarTabla(int filas) {
		for (int i = 0; i < filas; i++) {
			modelo.removeRow(0);
		}
	}

	public static void buscar() {
		try {
			modelo.setColumnCount(0);
			modelo.setRowCount(0);
			ResultSet rs = UsuariosDAO.buscarUsuario(jCmbCriterio
					.getSelectedItem().toString(), txtBuscar.getText()
					.toLowerCase());
			int nColumnas = rs.getMetaData().getColumnCount();
			modelo.setColumnIdentifiers(nombreColumna);
			while (rs.next()) {
				Object[] registro = new Object[nColumnas];

				for (int i = 0; i < nColumnas; i++) {
					registro[i] = rs.getObject(i + 1);
					System.out.println(registro[i]);

				}
				for (int i2 = 0; i2 < registro.length; i2++) {

					if (registro[i2].toString().equals("true")) {
						registro[i2] = "Inactivo";
					} else if (registro[i2].toString().equals("false")) {
						registro[i2] = "Activo";
					}
					System.out.println(registro[i2]);
				}
				modelo.addRow(registro);

			}
			rs.close();
		} catch (SQLException e1) {
			System.out.println(e1);

		}

	}

}
