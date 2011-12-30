package code.google.com.opengis.gestionVISUAL;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import code.google.com.opengis.gestion.Dispositivo;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.DispositivoDAO;
import code.google.com.opengis.gestionDAO.Idioma;

public class DispositivosPanelGestion extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
		lblNumSerie.setText(Idioma.getString("etSerialNumber")); //$NON-NLS-1$
		lblModelo = new JLabel();
		lblModelo.setBounds(new Rectangle(62, 119, 107, 21));
		lblModelo.setText(Idioma.getString("etModel")); //$NON-NLS-1$
		lblIdDispositivo = new JLabel();
		lblIdDispositivo.setBounds(new Rectangle(62, 60, 107, 21));
		lblIdDispositivo.setText(Idioma.getString("etId")); //$NON-NLS-1$
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
			if(accion=="alta"){ //$NON-NLS-1$
				
				int id = DispositivoDAO.calcularNuevoID();
				txtID.setText(id+""); //$NON-NLS-1$
				
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
			bGuardar.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/Guardar.png"))); //$NON-NLS-1$
			bGuardar.setToolTipText(Idioma.getString("etSaveNewDevice")); //$NON-NLS-1$
			bGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				if(accion=="modificar"){ //$NON-NLS-1$
					if (Dispositivo.validarDatos(txtModelo.getText(),
							txtNumSerie.getText()) == true) {
						txtModelo.setText(quitarBlancosIzquierda(txtModelo.getText()));
						txtModelo.setText(quitarBlancosDerecha(txtModelo.getText()));
						txtNumSerie.setText(quitarBlancosIzquierda(txtNumSerie.getText()));
						txtNumSerie.setText(quitarBlancosDerecha(txtNumSerie.getText()));						
						if (txtModelo.getText().equals("")){
							JOptionPane.showMessageDialog(null, "El modelo no puede estar vacío ni tener espacios al principio o al final");
						}else{
						try {
							DispositivoDAO.modificarDispositivo(
									txtID.getText(),
									txtModelo.getText(), txtNumSerie.getText());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
					}}else {
						JOptionPane
						.showMessageDialog(
								null,
								Idioma.getString("msgSerialNumberError1")); //$NON-NLS-1$
			}	
				}else{
					
				
					boolean paso1; // booleano que me indicará si he podido
									// convertir el ID de Dispositivo a String
					boolean paso2; // booleano que me indicará si he podido
									// convertir el número de serie a String
					String numeroSerie = ""; // donde intentaré convertir el número //$NON-NLS-1$
												// de serie introducido a un String
					String numID = ""; // donde intentaré convertir el ID de //$NON-NLS-1$
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
											Idioma.getString("msgSerialNumberError2")); //$NON-NLS-1$
							paso1 = false;
						}
						// intento convertir el ID de Dispositivo a un String.
						try {
							numID = (txtID.getText()).toString();
							paso2 = true;
						} catch (Exception e1) {
							JOptionPane
									.showMessageDialog(null,
											Idioma.getString("msgIDError")); //$NON-NLS-1$
							paso2 = false;
						}
						if ((paso1 == true) && (paso2 == true)) {
							txtModelo.setText(quitarBlancosIzquierda(txtModelo.getText()));
							txtModelo.setText(quitarBlancosDerecha(txtModelo.getText()));
							if (txtModelo.getText().equals("")){
								JOptionPane.showMessageDialog(null, "El modelo no puede estar vacío ni tener espacios al principio o al final");
							}else{
							try {
								DispositivoDAO.altaDispositivo(numID,
										txtModelo.getText(), numeroSerie,0,0);
								String nuevoID = null;
								//El siguiente "try" actualiza el campo de ID de dispositivo 
								try {
									String sentencia = "SELECT MAX(iddispositivo) FROM `dispositivo`"; //$NON-NLS-1$
									ConectarDBA.acceder();
									ResultSet rs1 = ConectarDBA.consulta(sentencia);
									while (rs1.next()) {
										nuevoID=(rs1.getInt(1) + 1) + ""; //$NON-NLS-1$
									}
									txtID.setText(nuevoID);
								} catch (SQLException e1) {
									e1.printStackTrace();
									}
								}

							catch (SQLException e1) {
								e1.printStackTrace();
							}
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
			bLimpiar.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/limpiar.png"))); //$NON-NLS-1$
			bLimpiar.setToolTipText(Idioma.getString("etCleanFields")); //$NON-NLS-1$
			bLimpiar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					txtNumSerie.setText(""); //$NON-NLS-1$
					txtModelo.setText(""); //$NON-NLS-1$
					
				}
				});
		}
		return bLimpiar;
	}
	/**
	 * Método que quita los espacios en blanco por la parte izquierda de una cadena
	 * @param miCadena
	 * @return devuelve la cadena sin espacios a la izquierda
	 */
	public static String quitarBlancosIzquierda(String miCadena){
		if (miCadena.equals("")){
			return "";
		}else{
			String cadena = miCadena;
			boolean b = false;
			char c;
			for (int i = 0; i < cadena.length(); i++){
				if (b == false){
					c = cadena.charAt(i);
					if (c == ' '){
					}
					else{
						b = true;
						cadena = cadena.substring(i);
					}
				}
			}
			if (cadena.charAt(0) == ' '){
				return "";
			}else{
				return cadena;
			}
		}
	}
	/**
	 * Método que quita los espacios en blanco por la parte derecha de una cadena
	 * @param miCadena
	 * @return devuelve la cadena sin espacios a la derecha
	 */
	public static String quitarBlancosDerecha(String miCadena){
		if (miCadena.equals("")){
			return "";
		}else{
			String cadena = miCadena;
			boolean b = false;
			char c;
			for (int i = cadena.length()-1; i >= 0; i--){
				if (b == false){
					c = cadena.charAt(i);
					if (c == ' '){
					}
					else{
						b = true;
						cadena = cadena.substring(0, i+1);
					}
				}
			}
			return cadena;
		}
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
