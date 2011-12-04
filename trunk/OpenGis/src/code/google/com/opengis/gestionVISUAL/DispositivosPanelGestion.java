package code.google.com.opengis.gestionVISUAL;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import code.google.com.opengis.gestion.Dispositivo;
import code.google.com.opengis.gestionDAO.DispositivoDAO;

public class DispositivosPanelGestion extends JPanel {


	private String accion;
	private JLabel lblIdDispositivo = null;
	private JLabel lblModelo = null;
	private JLabel lblNumSerie = null;
	private JTextField txtID = null;
	private JTextField txtModelo = null;
	private JTextField txtNumSerie = null;
	private JButton bGuardar = null;
	private JButton bLimpiar = null;
	
	private String id;
	private String modelo;
	private String numserie;
	
	/**
	 * Constructor de la clase DispositivosPanelGestion. Si la acción es "altas", dará de alta usurios.
	 * En caso de ser "modificacion", accederá al panel para modificar.
	 */
	public DispositivosPanelGestion(String accion) {
		super();
		this.accion = accion;
		initialize();
	}
	
	/**
	 * Constructor de la clase DispositivosPanelGestion. Si la acción es "altas", dará de alta usurios.
	 * En caso de ser "modificacion", accederá al panel para modificar. Además, le pasamos los parametros necesarios
	 * para realizar las modificaciones
	 */
	public DispositivosPanelGestion(String accion, String id, String modelo, String numserie) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.numserie = numserie;
		this.accion = accion;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		lblNumSerie = new JLabel();
		lblNumSerie.setBounds(new Rectangle(62, 169, 107, 21));
		lblNumSerie.setText("Número de Serie:");
		lblModelo = new JLabel();
		lblModelo.setBounds(new Rectangle(62, 119, 107, 21));
		lblModelo.setText("Modelo:");
		lblIdDispositivo = new JLabel();
		lblIdDispositivo.setBounds(new Rectangle(62, 60, 107, 21));
		lblIdDispositivo.setText("ID:");
		this.setSize(793, 343);
		this.setLayout(null);
		this.add(lblIdDispositivo, null);
		this.add(lblModelo, null);
		this.add(lblNumSerie, null);
		this.add(getTxtID(), null);
		this.add(getTxtModelo(), null);
		this.add(getTxtNumSerie(), null);
		this.add(getBGuardar(), null);
		this.add(getBLimpiar(), null);
	}

	/**
	 * This method initializes txtID	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtID() {
		if (txtID == null) {
				txtID = new JTextField();
			if(accion=="alta"){
				
				int id = DispositivoDAO.calcularNuevoID();
				txtID.setText(id+"");
				
			}else{

				
				txtID.setText(this.id);
			}
			
			
			txtID.setBounds(new Rectangle(165, 56, 194, 28));
			txtID.setEnabled(false);
		}
		return txtID;
	}

	/**
	 * This method initializes txtModelo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtModelo() {
		if (txtModelo == null) {
			txtModelo = new JTextField();
			txtModelo.setBounds(new Rectangle(165, 115, 194, 28));
			txtModelo.setText(modelo);
		}
		return txtModelo;
	}

	/**
	 * This method initializes txtNumSerie	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNumSerie() {
		if (txtNumSerie == null) {
			txtNumSerie = new JTextField();
			txtNumSerie.setBounds(new Rectangle(165, 165, 194, 28));
			txtNumSerie.setText(numserie);
		}
		return txtNumSerie;
	}

	/**
	 * This method initializes bGuardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBGuardar() {
		if (bGuardar == null) {
			bGuardar = new JButton();
			bGuardar.setBounds(new Rectangle(60, 254, 53, 45));
			bGuardar.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/Guardar.png")));
			bGuardar.setToolTipText("Guardar Nuevo Dispositivo");
			bGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

				if(accion=="modificar"){
					
					if (Dispositivo.validarDatos(txtModelo.getText(),
							txtNumSerie.getText()) == true) {

						try {
							DispositivoDAO.modificarDispositivo(
									txtID.getText(),
									txtModelo.getText(), txtNumSerie.getText());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
					}else {
						JOptionPane
						.showMessageDialog(
								null,
								"Datos incorrectos. El número de Serie ha de tener entre 10 y 30 caracteres, y el modelo no ha de sobrepasar los 15 caracteres.");
			}
					
				}else{
					
				
					boolean paso1; // booleano que me indicará si he podido
									// convertir el ID de Dispositivo a String
					boolean paso2; // booleano que me indicará si he podido
									// convertir el número de serie a String
					String numeroSerie = ""; // donde intentaré convertir el número
												// de serie introducido a un String
					String numID = ""; // donde intentaré convertir el ID de
										// Dispositivo introducido a un String

					if (Dispositivo.validarDatos(txtModelo.getText(),
							txtNumSerie.getText()) == true) {
						// intento convertir el número de serie a un String.
						try {
							numeroSerie = (txtNumSerie.getText()).toString();
							paso1 = true;
						} catch (Exception e1) {
							JOptionPane
									.showMessageDialog(null,
											"Error aceptando el número de serie del dispositivo. Introduce datos válidos.");
							paso1 = false;
						}
						// intento convertir el ID de Dispositivo a un String.
						try {
							numID = (txtID.getText()).toString();
							paso2 = true;
						} catch (Exception e1) {
							JOptionPane
									.showMessageDialog(null,
											"Error convirtiendo el ID de dispositivo. Introduce datos válidos.");
							paso2 = false;
						}
						if ((paso1 == true) && (paso2 == true)) {
							try {
								DispositivoDAO.altaDispositivo(numID,
										txtModelo.getText(), numeroSerie,0,0);
								

							}

							catch (SQLException e1) {
								e1.printStackTrace();
							}
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
			bLimpiar.setBounds(new Rectangle(140, 254, 53, 45));
			bLimpiar.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/limpiar.png")));
			bLimpiar.setToolTipText("Limpiar todos los campos");
			bLimpiar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					txtNumSerie.setText("");
					txtModelo.setText("");
					
				}
				});
		}
		return bLimpiar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
