package code.google.com.opengis.gestionVISUAL;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import code.google.com.opengis.gestion.Dispositivo;
import code.google.com.opengis.gestionDAO.DispositivoDAO;

/**
 * @author Iván Serrano y Jose Alapont
 * 		   Clase que genera una ventana interior en el formulario MDI. 
 * 		   Esta ventana es la encargada de gestionar los Dispositivos.
 * 
 */

public class DispositivoVisual extends JInternalFrame {

	/**
	 * 
	 */
	private static JPanel panelDispositivo;
	private static JPanel panelDispositivoCrear;
	private static JPanel panelDispositivoMod;

	//cajas de texto de datos de dispositivo
	private static JTextField txtIddispositivo = new JTextField();
	private static JTextField txtModelo = new JTextField();
	private static JTextField txtNumSerie = new JTextField();

	//sección buscar
	private static JTextField txtBuscar = new JTextField();
	private static String[] criterio = { "Iddispositivo", "Modelo", "NumSerie", "Disponible", "Activo" };
	private static JComboBox jCmbCriterio = new JComboBox(criterio);

	//tabla de dispositivos
	private static JScrollPane jScrollPaneTablaDispositivo = null;
	private static JTable jTablaDispositivo = null;
	static Object[] nombreColumna = { "Id Dispositivo", "Modelo", "Numero Serie", "Disponible", "Activo" };
	static DefaultTableModel modelo = new DefaultTableModel(nombreColumna, 0);

	//botones de gestión de dispositivos
	
	private JButton cmdLiberarDispositivo;
	private JButton cmdOcuparDispositivo;
	
	

	/**
	 * Constructor de la clase DisopsitivoVisual. Se le pasarán los parametros
	 * necesarios para construir el alto y el ancho.
	 */

	public DispositivoVisual(int ancho, int alto) {
		super("Dispositivo", true, true, true, true);
		super.setIconifiable(true); // Indicamos que se puede minimizar
		panelDispositivo = new JPanel();
		panelDispositivo.setLayout(null);
		panelDispositivoCrear = new JPanel();
		panelDispositivoCrear.setLayout(null);
		this.add(panelDispositivoCrear);
		panelDispositivoCrear.setVisible(false);
		panelDispositivoMod = new JPanel();
		panelDispositivoMod.setLayout(null);
		this.add(panelDispositivoMod);
		panelDispositivoMod.setVisible(false);
		this.add(panelDispositivo);
		this.setBounds(0, 0, ancho, alto);
		this.setTitle("Dispositivo");
		this.setClosable(true);
		this.setMaximizable(true);
		TitledBorder jb = new TitledBorder("Gestión de Dispositivo");
		TitledBorder jb1 = new TitledBorder("Añadir Dispositivo");
		TitledBorder jb2 = new TitledBorder("Modificar Dispositivo");
		panelDispositivo.setBorder(jb);
		panelDispositivoCrear.setBorder(jb1);
		panelDispositivoMod.setBorder(jb2);
		panelDispositivo.setBounds(new Rectangle(0, 0, ancho, alto));
		panelDispositivoCrear.setBounds(new Rectangle(0, 0, 800, 600));
		panelDispositivoMod.setBounds(new Rectangle(0, 0, 800, 600));

		this.getJScrollPaneTablaDispositivos();
		cargarDispositivosPrincipal(panelDispositivo);
		panelDispositivo.setVisible(true);
		cargarNuevoDispositivo(panelDispositivoCrear);
		cargarModDispositivo(panelDispositivoMod);

	}

	public static void cargarDispositivosPrincipal(final Container pane) {
		
		JButton cmdBuscar;
		JButton cmdNuevoDispositivo;
		JButton cmdModificarDispositivo;
		JButton cmdBajaDispositivo;
		JButton cmdLimpiar;
		
		JLabel campolbl;
		pane.setLayout(new GridBagLayout());
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

		cmdBuscar = new JButton("Buscar");
		cButtons.anchor = GridBagConstraints.WEST;
		cButtons.insets = new Insets(0, 0, 0, 0);
		cButtons.gridx = 4;
		cButtons.gridy = 0;
		cmdBuscar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					modelo.setColumnCount(0);
					modelo.setRowCount(0);
					ResultSet rs = DispositivoDAO.buscarDispositivo(jCmbCriterio.getSelectedItem().toString(), txtBuscar.getText().toLowerCase());
					int nColumnas = rs.getMetaData().getColumnCount();
					modelo.setColumnIdentifiers(nombreColumna);
					while (rs.next()) {
						Object[] registro = new Object[nColumnas];

						for (int i = 0; i < nColumnas; i++) {
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
		pane.add(cmdBuscar, cButtons);

		jTablaDispositivo.setModel(modelo);
		cTabla.gridx = 0;
		cTabla.gridy = 1;
		cTabla.gridwidth = 5;
		cTabla.gridheight = 5;
		cTabla.fill = GridBagConstraints.HORIZONTAL;
		pane.add(jScrollPaneTablaDispositivo, cTabla);

		cmdNuevoDispositivo = new JButton("Nuevo");
		cButtons.anchor = GridBagConstraints.EAST;
		cButtons.insets = new Insets(0, 0, 0, 0);
		cButtons.gridx = 0;
		cButtons.gridy = 7;
		cmdNuevoDispositivo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pane.setVisible(false);
				cargarNuevoDispositivo(panelDispositivoCrear);
				panelDispositivoCrear.setVisible(true);
			}
		});
		pane.add(cmdNuevoDispositivo, cButtons);

		
		cmdModificarDispositivo = new JButton("Modificar");
		cButtons.anchor = GridBagConstraints.WEST;
		cButtons.insets = new Insets(0, 0, 0, 0);
		cButtons.gridx = 1;
		cButtons.gridy = 7;
		cmdModificarDispositivo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int fila = jTablaDispositivo.getSelectedRow();
				if (fila != -1) {
					String[] rDispositivo = new String[10];
					int o = 0;
					for (int i = 0; i < rDispositivo.length; i++) {
						System.out.println("i = "+i);
						rDispositivo[i] = jTablaDispositivo.getValueAt(fila, i)
								.toString();
						o=i;
					}
					o=o+1;
					System.out.println("o = "+o);

					pane.setVisible(false);
					o = o+1;
					System.out.println("o = "+o);
					cargarModDispositivo(panelDispositivoMod);
					o = o+1;
					System.out.println("o = "+o);
					setCampos(rDispositivo);
					o = o+1;
					System.out.println("o = "+o);
					panelDispositivoMod.setVisible(true);
					o = o+1;
					System.out.println("o = "+o);
					limpiarTabla(modelo.getRowCount());
					o = o+1;
					System.out.println("o = "+o);
					panelDispositivoMod.setVisible(true);
					o = o+1;
					System.out.println("o = "+o);
				}

			}
		});
		pane.add(cmdModificarDispositivo, cButtons);

		
		cmdBajaDispositivo = new JButton("Desactivar");
		cButtons.anchor = GridBagConstraints.LINE_START;
		cButtons.insets = new Insets(0, 0, 0, 0);
		cButtons.gridx = 3;
		cButtons.gridy = 7;
		cmdBajaDispositivo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				/*DispositivoDAO a = new DispositivoDAO(tabl
				try {
					a.borrarDispositivo();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}*/
			}
		});
		
		pane.add(cmdBajaDispositivo, cButtons);

		
		cmdLimpiar = new JButton("Limpiar Tabla");
		cButtons.anchor = GridBagConstraints.LINE_START;
		cButtons.insets = new Insets(0, 0, 0, 0);
		cButtons.gridx = 4;
		cButtons.gridy = 7;
		pane.add(cmdLimpiar, cButtons);
		cmdLimpiar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				limpiarTabla(modelo.getRowCount());

			}
		});

	}

	/**
	 * Este método carga en el frame que le indiquemos los objetos para Crear
	 * Dispositivos o Modificarlos.
	 * 
	 * @param pane
	 *            Indica el contenedor de todos
	 * @param modificar
	 *            Indica si vamos a modificar el dispositivo (true) o vamos a crear
	 *            uno nuevo (false)
	 */
	public static void cargarNuevoDispositivo(final Container pane) {
		pane.removeAll();
		pane.repaint();
		
		//Botones para la alta de dispositivos
		JButton cmdAceptarAlta;
		JButton cmdCancelarAlta;
		JButton cmdLimpiar;
		
		JLabel campolbl;
		pane.setLayout(new GridBagLayout());
		Dimension tamañoCaja = new Dimension(100, 20);
		txtIddispositivo.setPreferredSize(tamañoCaja);
		txtModelo.setPreferredSize(tamañoCaja);
		txtNumSerie.setPreferredSize(tamañoCaja);

		// Se crean 3 constraints, uno para cada uso.
		GridBagConstraints cNText = new GridBagConstraints(); // Cajas de texto
		GridBagConstraints cNButtons = new GridBagConstraints(); // Botones
		GridBagConstraints cNLabels = new GridBagConstraints(); // Labels

		cNLabels.insets = new Insets(8, 8, 8, 8); // top padding
		cNLabels.anchor = GridBagConstraints.EAST;
		cNText.anchor = GridBagConstraints.WEST;
		cNText.weightx = 0.5;
		cNText.ipadx = 100;

		campolbl = new JLabel("ID de Dispositivo:");
		cNLabels.weightx = 0.005;
		cNLabels.gridx = 0;
		cNLabels.gridy = 0;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 1;
		cNText.gridy = 0;
		txtIddispositivo.setEditable(true);
		pane.add(txtIddispositivo, cNText);

		campolbl = new JLabel("Modelo:");
		cNLabels.weightx = 0.005;
		cNLabels.gridx = 2;
		cNLabels.gridy = 0;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 3;
		cNText.gridy = 0;
		pane.add(txtModelo, cNText);

		campolbl = new JLabel("Numero de Serie:");
		cNLabels.gridx = 4;
		cNLabels.gridy = 0;
		pane.add(campolbl, cNLabels);

		cNText.gridx = 5;
		cNText.gridy = 0;
		pane.add(txtNumSerie, cNText);

		
		cNText.gridx = 5;
		cNText.gridy = 3;

		
		//activo el botón "Aceptar" de la alta de dispositivo
		cmdAceptarAlta = new JButton("Guardar");
		cNButtons.fill = 0;
		cNButtons.anchor = GridBagConstraints.PAGE_END; // bottom of space
		cNButtons.insets = new Insets(15, 0, 0, 0); // top padding
		cNButtons.gridx = 0;
		cNButtons.gridy = 5;
		
		cmdAceptarAlta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

					boolean paso1; //booleano que me indicará si he podido convertir el ID de Dispositivo a números enteros
					boolean paso2; //booleano que me indicará si he podido convertir el número de serie a números enteros
					int numeroSerie = 0; //donde intentaré convertir el número de serie introducido a un número entero
					int numID = 0; //donde intentaré convertir el ID de Dispositivo introducido a un número entero
					
					if (Dispositivo.validarDatos(txtIddispositivo.getText(), txtModelo.getText(), txtNumSerie.getText()) == true){
						//intento convertir el número de serie a un número entero.
						try {
							numeroSerie = Integer.parseInt(txtNumSerie.getText());
							paso1 = true;
						}
						catch (Exception e1){
							JOptionPane.showMessageDialog(null,"Error convirtiendo el número de serie del dispositivo. Introduce sólo números.");
							paso1 = false;
						}
						//intento convertir el ID de Dispositivo a un número entero.
						try {
							numID = Integer.parseInt(txtIddispositivo.getText());
							paso2 = true;
						}
						catch (Exception e1){
							JOptionPane.showMessageDialog(null,"Error convirtiendo el ID de dispositivo. Introduce sólo números.");
							paso2 = false;
						}
						if ((paso1==true) && paso2 == true){
							DispositivoDAO a = new DispositivoDAO(numID+"", txtModelo.getText(), numeroSerie+"");
							try {
								a.altaDispositivo();
								}
							
							catch (SQLException e1) {
								e1.printStackTrace();
								}
							}
						}
					}
			});
		
		pane.add(cmdAceptarAlta, cNButtons);

		
		//activo el botón limpiar campos de alta de dispositivo
		cmdLimpiar = new JButton("Limpiar");
		cNButtons.fill = 0;
		cNButtons.insets = new Insets(15, 15, 0, 0); // top padding
		cNButtons.gridx = 1;
		cNButtons.gridy = 5;
		cNButtons.anchor = GridBagConstraints.WEST;
		cmdLimpiar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				restablecerCampos();
				JOptionPane.showMessageDialog(null,
						"Los campos se han restablecido");

			}
		});
		
		pane.add(cmdLimpiar, cNButtons);

		
		//activo el botón "Volver" de la alta de dispositivo
		cmdCancelarAlta = new JButton("Volver");
		cNButtons.fill = 0;
		cNButtons.insets = new Insets(15, 15, 0, 0); // top padding
		cNButtons.gridx = 2;
		cNButtons.gridy = 5;
		cNButtons.anchor = GridBagConstraints.WEST;
		cmdCancelarAlta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pane.setVisible(false);
				panelDispositivo.setVisible(true);
				restablecerCampos();
			}
		});
		pane.add(cmdCancelarAlta, cNButtons);

	}

	public static void cargarModDispositivo(final Container pane) {
		pane.removeAll();
		pane.repaint();
		
		JButton cmdAceptarMod;
		JButton cmdCancelarMod;
		JButton cmdLimpiar;

		JLabel campolbl;
		pane.setLayout(new GridBagLayout());
		Dimension tamañoCaja = new Dimension(100, 20);
		txtIddispositivo.setPreferredSize(tamañoCaja);
		txtModelo.setPreferredSize(tamañoCaja);
		txtNumSerie.setPreferredSize(tamañoCaja);


		// Se crean 3 constraints, uno para cada uso.
		GridBagConstraints cText = new GridBagConstraints(); // Cajas de texto
		GridBagConstraints cButtons = new GridBagConstraints(); // Botones
		GridBagConstraints cLabels = new GridBagConstraints(); // Labels

		cLabels.insets = new Insets(8, 8, 8, 8); // top padding
		cLabels.anchor = GridBagConstraints.EAST;
		cText.anchor = GridBagConstraints.WEST;
		cText.weightx = 0.5;
		cText.ipadx = 100;

		//creamos el Label y el Campo de texto del "ID de dispositivo"
		campolbl = new JLabel("ID de Dispositivo:");
		cLabels.weightx = 0.005;
		cLabels.gridx = 0;
		cLabels.gridy = 0;
		pane.add(campolbl, cLabels);

		cText.gridx = 1;
		cText.gridy = 0;
		txtIddispositivo.setEditable(false);
		pane.add(txtIddispositivo, cText);

		//creamos el Label y el Campo de texto del "Modelo de dispositivo"
		campolbl = new JLabel("Modelo:");
		cLabels.weightx = 0.005;
		cLabels.gridx = 2;
		cLabels.gridy = 0;
		pane.add(campolbl, cLabels);

		cText.gridx = 3;
		cText.gridy = 0;
		pane.add(txtModelo, cText);

		//creamos el Label y el Campo de texto del "Número de Serie de dispositivo"
		campolbl = new JLabel("Número de Serie:");
		cLabels.gridx = 4;
		cLabels.gridy = 0;
		pane.add(campolbl, cLabels);

		cText.gridx = 5;
		cText.gridy = 0;
		pane.add(txtNumSerie, cText);

		//activamos el botón que aceptará los cambios en el Dispositivo
		cmdAceptarMod = new JButton("Modificar");
		cButtons.fill = 0;
		cButtons.anchor = GridBagConstraints.PAGE_END; // bottom of space
		cButtons.insets = new Insets(15, 0, 0, 0); // top padding
		cButtons.gridx = 0;
		cButtons.gridy = 5;
		cmdAceptarMod.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				if (Dispositivo.validarDatos(txtIddispositivo.getText(),txtModelo.getText(), txtNumSerie.getText()) == true)
				{
					DispositivoDAO disp1 = new DispositivoDAO(txtIddispositivo.getText(),txtModelo.getText(), txtNumSerie.getText());
					try {
						disp1.modificarDispositivo(txtModelo.getText(), txtNumSerie.getText());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Datos incorrectos. El número de Serie ha de ser de 5 cifras, y el modelo no ha de sobrepasar los 15 caracteres.");
				}
				

			}
		});
		pane.add(cmdAceptarMod, cButtons);

		//activamos el botón que limpiará los campos de texto
		cmdLimpiar = new JButton("Limpiar");
		cButtons.fill = 0;
		cButtons.insets = new Insets(15, 15, 0, 0); // top padding
		cButtons.gridx = 1;
		cButtons.gridy = 5;
		cButtons.anchor = GridBagConstraints.WEST;
		cmdLimpiar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				restablecerCampos();
				JOptionPane.showMessageDialog(null,
						"Los campos se han restablecido");

			}
		});
		pane.add(cmdLimpiar, cButtons);

		//activamos el botón que Cancelará la modificación de dispositivo
		cmdCancelarMod = new JButton("Volver");
		cButtons.fill = 0;
		cButtons.insets = new Insets(15, 15, 0, 0); // top padding
		cButtons.gridx = 2;
		cButtons.gridy = 5;
		cButtons.anchor = GridBagConstraints.WEST;
		cmdCancelarMod.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pane.setVisible(false);
				panelDispositivo.setVisible(true);
				restablecerCampos();
			}
		});
		pane.add(cmdCancelarMod, cButtons);
	}

	public static void setCampos(String[] rUser) {
		txtIddispositivo.setText(rUser[0]);
		txtModelo.setText(rUser[1]);
		txtNumSerie.setText(rUser[2]);
		for (int i = 0; i < rUser.length; i++) {
			System.out.println(rUser[i]);
		}
	}

	public static void restablecerCampos() {
		txtIddispositivo.setText("");
		txtModelo.setText("");
		txtNumSerie.setText("");
	}

	public static void limpiarTabla(int filas) {
		for (int i = 0; i < filas; i++) {
			modelo.removeRow(0);
		}
	}

	private JScrollPane getJScrollPaneTablaDispositivos() {
		if (jScrollPaneTablaDispositivo == null) {
			jScrollPaneTablaDispositivo = new JScrollPane(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jScrollPaneTablaDispositivo.setViewportView(getJTablaDispositivos());
		}
		return jScrollPaneTablaDispositivo;
	}

	/**
	 * This method initializes jTableAsignaturas
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTablaDispositivos() {

		jTablaDispositivo = new JTable();
		jTablaDispositivo.setModel(modelo);

		return jTablaDispositivo;
	}

}
