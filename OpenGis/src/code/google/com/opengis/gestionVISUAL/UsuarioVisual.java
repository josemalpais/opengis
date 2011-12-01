/*****************************************************************************
 * Copyright (c) 2011 [OpenGisTeam]                                           *
 ******************************************************************************/
package code.google.com.opengis.gestionVISUAL;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
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
import javax.swing.text.MaskFormatter;

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
	private Toolkit tk;
	private Dimension t;
	
	private static final long serialVersionUID = 1L;
	private static JPanel panelUsuarios;
	private static JPanel panelUsuariosCrear;
	private static JPanel panelUsuariosMod;
	private int anchoFrame, altoFrame;
	private int altura,anchura;
	private static JFormattedTextField txtDNI;
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
		resolucionOS();
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
		
		
		
		
		
		int altoFrame = 600;
		int anchoFrame = 800;
		
		int anchuraTabla = getAnchura();
		int alturaTabla=getAltura();
		System.out.println(anchuraTabla + " " + alturaTabla);
	    int fila1= (((getAltura()/2))-altoFrame+200); // Fila de inicio
	    int columna1=((getAnchura()/2)-anchoFrame/2);//columna de inicio
		panelUsuarios.setBorder(jb);
		panelUsuariosCrear.setBorder(jb1);
		panelUsuariosMod.setBorder(jb2);
		panelUsuarios.setBounds(new Rectangle(0, 0, ancho, alto));
		panelUsuariosCrear.setBounds(columna1, fila1, anchoFrame, altoFrame);
		panelUsuariosMod.setBounds(columna1, fila1, anchoFrame, altoFrame);
		txtFNac.setValue(new Date());
		jTablaUsuarios.removeEditor();
		panelUsuarios.setVisible(true);

	}
	
	/**
	 * Método que nos calcula la resolución del sistema Operativo.
	 */
	public void resolucionOS(){
		tk = Toolkit.getDefaultToolkit();
	    t = tk.getScreenSize();
	    altura= (int)Math.floor(t.getHeight());
	    anchura=(int)Math.floor(t.getWidth());
	}
	public int getAltura() {
		return altura;
	}
	public int getAnchura() {
		return anchura;
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

		// CARGAMOS LOS LABELS

		cLabels.insets = new Insets(8, 8, 8, 8); // top padding
		cLabels.weightx = 0.005;
		cLabels.anchor = GridBagConstraints.EAST;

		campolbl = new JLabel("Buscar por:");
		cLabels.gridx = 2;
		cLabels.gridy = 0;
		panelUsuarios.add(campolbl, cLabels);

		// CARGAMOS LOS JTEXTFIELD

		cText.anchor = GridBagConstraints.EAST;
		cText.weightx = 0.5;
		cText.ipadx = 100;

		cText.gridx = 0;
		cText.gridy = 0;
		cText.gridwidth = 2;
		txtBuscar.setText("");
		panelUsuarios.add(txtBuscar, cText);
		cText.gridwidth = 1;

		cText.anchor = GridBagConstraints.WEST;
		cText.gridx = 3;
		cText.gridy = 0;
		panelUsuarios.add(jCmbCriterio, cText);

		// CARGAMOS LOS BOTONES

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

		modelo  = new DefaultTableModel() {
			 @Override
			    public boolean isCellEditable(int row, int column) {
			       return false;
			    }
		};
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
					limpiarTabla(modelo.getRowCount(), modelo);

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

				limpiarTabla(modelo.getRowCount(), modelo);

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
		MaskFormatter mascDNI = null;
		txtDNI = new JFormattedTextField(mascDNI);
		txtDNI.setPreferredSize(tamañoCaja);
		try {
			mascDNI = new MaskFormatter("########U");
		} catch (ParseException e) {

		}
		mascDNI.setPlaceholderCharacter('_');
		mascDNI.setCommitsOnValidEdit(true);

		// Se crean 3 constraints, uno para cada uso.
		GridBagConstraints cNText = new GridBagConstraints(); // Cajas de texto
		GridBagConstraints cNButtons = new GridBagConstraints(); // Botones
		GridBagConstraints cNLabels = new GridBagConstraints(); // Labels

		// INSERTAMOS LOS LABELS

		cNLabels.insets = new Insets(8, 8, 8, 8);
		cNLabels.anchor = GridBagConstraints.EAST;

		campolbl = new JLabel("DNI:");
		cNLabels.weightx = 0.005;
		cNLabels.gridx = 0;
		cNLabels.gridy = 0;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("Nombre:");
		cNLabels.weightx = 0.005;
		cNLabels.gridx = 2;
		cNLabels.gridy = 0;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("Apellidos:");
		cNLabels.gridx = 4;
		cNLabels.gridy = 0;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("Fecha de Nacimiento:");
		cNLabels.gridx = 0;
		cNLabels.gridy = 1;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("Teléfono:");
		cNLabels.gridx = 2;
		cNLabels.gridy = 1;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("Dirección:");
		cNLabels.gridx = 4;
		cNLabels.gridy = 1;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("Provincia:");
		cNLabels.gridx = 2;
		cNLabels.gridy = 2;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("Población:");
		cNLabels.gridx = 0;
		cNLabels.gridy = 2;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("CP:");
		cNLabels.gridx = 4;
		cNLabels.gridy = 2;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("Email:");
		cNLabels.gridx = 0;
		cNLabels.gridy = 3;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("Contraseña:");
		cNLabels.gridx = 2;
		cNLabels.gridy = 3;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("Confirmar contraseña:");
		cNLabels.gridx = 4;
		cNLabels.gridy = 3;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("Tipo de usuario:");
		cNLabels.gridx = 4;
		cNLabels.gridy = 4;
		pane.add(campolbl, cNLabels);

		campolbl = new JLabel("* Todos los campos son obligatorios");
		cNLabels.gridx = 4;
		cNLabels.gridwidth = 2;
		cNLabels.gridy = 5;
		pane.add(campolbl, cNLabels);
		cNLabels.gridwidth = 1;

		// INSERTAMOS LOS JTEXTFIELD

		cNText.anchor = GridBagConstraints.WEST;
		cNText.weightx = 0.5;
		cNText.ipadx = 100;

		cNText.gridx = 1;
		cNText.gridy = 0;
		txtDNI.setEditable(true);
		pane.add(txtDNI, cNText);

		cNText.gridx = 3;
		cNText.gridy = 0;
		pane.add(txtNombre, cNText);

		cNText.gridx = 5;
		cNText.gridy = 0;
		pane.add(txtApellidos, cNText);

		cNText.gridx = 1;
		cNText.gridy = 1;
		pane.add(txtFNac, cNText);

		cNText.gridx = 3;
		cNText.gridy = 1;
		pane.add(txtTlf, cNText);

		cNText.gridx = 5;
		cNText.gridy = 1;
		pane.add(txtDir, cNText);

		cNText.gridx = 1;
		cNText.gridy = 2;
		pane.add(txtPob, cNText);

		cNText.gridx = 3;
		cNText.gridy = 2;
		pane.add(txtProv, cNText);

		cNText.gridx = 5;
		cNText.gridy = 2;
		pane.add(txtCp, cNText);

		cNText.gridx = 1;
		cNText.gridy = 3;
		pane.add(txtEmail, cNText);

		cNText.gridx = 3;
		cNText.gridy = 3;
		pane.add(txtCon, cNText);

		cNText.gridx = 5;
		cNText.gridy = 3;
		pane.add(txtConfCon, cNText);

		cNText.gridx = 5;
		cNText.gridy = 4;
		pane.add(jCmbTipo, cNText);

		// INSERTAMOS LOS JBUTTON

		if (modificar == false) {
			boton = new JButton("Guardar");
			cNButtons.fill = 0;
			cNButtons.anchor = GridBagConstraints.PAGE_END; // bottom of space
			cNButtons.insets = new Insets(15, 0, 0, 0); // top padding
			cNButtons.gridx = 0;
			cNButtons.gridy = 6;
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
						if (u.getValido()) {
							try {
								u.crearUsuario();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							restablecerCampos();
							txtDNI.setText("");
						}

					} else {

						JOptionPane.showMessageDialog(null,
								"Error. Las contraseñas no coinciden");

					}

				}
			});
			pane.add(boton, cNButtons);
			
			boton = new JButton("Limpiar");
			cNButtons.fill = 0;
			cNButtons.insets = new Insets(15, 15, 0, 0); // top padding
			cNButtons.gridx = 1;
			cNButtons.gridy = 6;
			cNButtons.anchor = GridBagConstraints.WEST;
			boton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					restablecerCampos();
					txtDNI.setText("");
					JOptionPane.showMessageDialog(null,
							"Los campos se han restablecido");

				}
			});
			pane.add(boton, cNButtons);
			
		} else if (modificar == true) {
			boton = new JButton("Modificar");
			cNButtons.fill = 0;
			cNButtons.anchor = GridBagConstraints.PAGE_END; // bottom of space
			cNButtons.insets = new Insets(15, 0, 0, 0); // top padding
			cNButtons.gridx = 0;
			cNButtons.gridy = 6;
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
			
			boton = new JButton("Limpiar");
			cNButtons.fill = 0;
			cNButtons.insets = new Insets(15, 15, 0, 0); // top padding
			cNButtons.gridx = 1;
			cNButtons.gridy = 6;
			cNButtons.anchor = GridBagConstraints.WEST;
			boton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					restablecerCampos();
					JOptionPane.showMessageDialog(null,
							"Los campos se han restablecido");

				}
			});
			pane.add(boton, cNButtons);
		}

		

		boton = new JButton("Volver");
		cNButtons.fill = 0;
		cNButtons.insets = new Insets(15, 15, 0, 0); // top padding
		cNButtons.gridx = 2;
		cNButtons.gridy = 6;
		cNButtons.anchor = GridBagConstraints.WEST;
		boton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pane.setVisible(false);
				panelUsuarios.setVisible(true);
				restablecerCampos();
			}
		});
		pane.add(boton, cNButtons);

	} // FIN DEL MÉTODO CARGARUSUARIO

	public String[] getTipo() {
		return tipo;
	}

	/**
	 * Carga los campos con el contenido del array que recibe por parámetro.
	 * 
	 * @param rUser
	 *            Array que contiene los datos que se van a cargar en los
	 *            jTextField
	 */
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

	/**
	 * Método que deja los campos de los JTextField en blanco.
	 */
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
		txtConfCon.setText("");
	}

	public void setTipo(String[] tipo) {
		this.tipo = tipo;
	}

	/**
	 * Método para limpiar las filas de la tabla.
	 * 
	 * @param filas
	 *            Indica la cantidad de filas que tiene la tabla, para que se
	 *            repita en el bucle for.
	 * @param tabla
	 *            Indica la tabla que queremos limpiar, se recibe como un
	 *            TableModel.
	 */
	public static void limpiarTabla(int filas, DefaultTableModel tabla) {
		for (int i = 0; i < filas; i++) {
			tabla.removeRow(0);
		}
	}

	/**
	 * Método que busca en la Base de Datos según el criterio que establezcamos
	 * en el campo de texto y nos lo carga en la tabla.
	 * 
	 * @Author Antonio Cambronero y Pepe Lara
	 */
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
					//System.out.println(registro[i]);

				}
				for (int i2 = 0; i2 < registro.length; i2++) {

					if (registro[i2].toString().equals("true")) {
						registro[i2] = "Inactivo";
					} else if (registro[i2].toString().equals("false")) {
						registro[i2] = "Activo";
					}
					//System.out.println(registro[i2]);
				}
				modelo.addRow(registro);

			}
			rs.close();
		} catch (SQLException e1) {
			System.out.println(e1);

		}

	}

	
}
