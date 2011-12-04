package code.google.com.opengis.gestionVISUAL;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;

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
			int id = DispositivoDAO.calcularNuevoID();
			txtID = new JTextField();
			txtID.setText(id+"");
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
		}
		return bLimpiar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
