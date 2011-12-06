package code.google.com.opengis.gestionVISUAL;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;

public class ProductoPanelGestion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblidprod = null;
	private JLabel lblNombre = null;
	private JLabel lblTipo = null;
	private JLabel lblDNI = null;
	private JLabel lblDosis = null;
	private JLabel lblDescripcion = null;
	private JTextField txtID = null;
	private JTextField txtNombreProd = null;
	private JTextField txtDNI = null;
	private JTextField txtDosis = null;
	private JTextArea txtDescripcion = null;
	private JLabel lblMedida = null;
	private JComboBox comboTipo = null;
	private JButton bGuardar = null;
	private JButton bLimpiar = null;
	private JLabel lblObligatorios = null;
	
	String accion;
	
	/**
	 * This is the default constructor
	 */
	public ProductoPanelGestion(String accion) {
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
		lblObligatorios = new JLabel();
		lblObligatorios.setBounds(new Rectangle(359, 305, 227, 20));
		lblObligatorios.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblObligatorios.setText("(*) Todos los campos son obligatorios");
		lblMedida = new JLabel();
		lblMedida.setBounds(new Rectangle(372, 136, 44, 25));
		lblMedida.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMedida.setText("(l/ha)");
		lblDescripcion = new JLabel();
		lblDescripcion.setBounds(new Rectangle(328, 180, 112, 25));
		lblDescripcion.setText("Descripción:");
		lblDosis = new JLabel();
		lblDosis.setBounds(new Rectangle(328, 136, 44, 25));
		lblDosis.setText("Dosis:");
		lblDNI = new JLabel();
		lblDNI.setBounds(new Rectangle(47, 180, 112, 25));
		lblDNI.setText("DNI");
		lblTipo = new JLabel();
		lblTipo.setBounds(new Rectangle(47, 136, 112, 25));
		lblTipo.setText("Tipo:");
		lblNombre = new JLabel();
		lblNombre.setBounds(new Rectangle(47, 91, 112, 25));
		lblNombre.setText("Nombre producto:");
		lblidprod = new JLabel();
		lblidprod.setBounds(new Rectangle(47, 48, 112, 25));
		lblidprod.setText("ID producto");
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
		this.add(getTxtDNI(), null);
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
		}
		return txtNombreProd;
	}

	/**
	 * This method initializes txtDNI	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setBounds(new Rectangle(160, 180, 149, 24));
		}
		return txtDNI;
	}

	/**
	 * This method initializes txtDosis	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDosis() {
		if (txtDosis == null) {
			txtDosis = new JTextField();
			txtDosis.setBounds(new Rectangle(420, 136, 149, 24));
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
			comboTipo.addItem("Líquido");
			comboTipo.addItem("Granulado");
			comboTipo.addItem("Polvo");
		}
		return comboTipo;
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
			bGuardar.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/Guardar.png")));
			bGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
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
			bLimpiar.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/Limpiar.png")));
			bLimpiar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return bLimpiar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
