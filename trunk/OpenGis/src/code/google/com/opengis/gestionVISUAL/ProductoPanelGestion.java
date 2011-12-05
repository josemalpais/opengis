package code.google.com.opengis.gestionVISUAL;

import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

import code.google.com.opengis.gestion.Usuarios;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoPanelGestion extends JPanel {
	
	 private JButton bGuardar;
     private JButton bRestablecer;
     
     private static JTextField txtIdprod;
     private static JTextField txtNombre;
     private static JTextField txtDosis;
     private static JTextField txtDni;
     private JComboBox comboTipo;
     private JCheckBox chkActivo;
     private JTextArea txtareaDescripcion;
     
     private JLabel lblIdprod;
 	 private JLabel lblNombre;
 	 private JLabel lblDescripcion;
     private JLabel lblTipo;
 	 private JLabel lblDosis;
 	 private JLabel lblDni;
 	 private JLabel lblActivo;
 	 private JLabel lblObligtorios;
 	 
 	private String accion;
 	 
 	private int idprod;
	private String nombre;
	private String descripcion;
	private String tipo;
	private String dosis;
	private String dni;
	private int activo;
 	 
public ProductoPanelGestion (int idprod, String nombre, String descripcion, String tipo, String dosis, String dni, int activo, String accion){
	super();
	this.idprod=idprod;
	this.nombre=nombre;
	this.descripcion=descripcion;
	this.tipo =tipo;
	this.dosis=dosis;
	this.dni=dni;
	this.activo=activo;
	this.accion = accion;
	initialize();
}

public ProductoPanelGestion(String accion){
	
	super();
	this.accion = accion;
	
	initialize();
	
}
private void initialize() {

	lblObligtorios = new JLabel();
	lblObligtorios.setBounds(new Rectangle(434, 334, 238, 25));
	lblObligtorios.setFont(new Font(Idioma.getString("Dialog"), Font.ITALIC, 12)); //$NON-NLS-1$
	lblObligtorios.setText(Idioma.getString("etAllFields")); //$NON-NLS-1$

	lblDescripcion = new JLabel();
	lblDescripcion.setBounds(new Rectangle(274, 192, 88, 30));
	lblDescripcion.setText(Idioma.getString("etPhone")); //$NON-NLS-1$
	lblDni = new JLabel();
	lblDni.setBounds(new Rectangle(44, 192, 88, 30));
	lblDni.setText(Idioma.getString("etProvince")); //$NON-NLS-1$
	lblDosis = new JLabel();
	lblDosis.setBounds(new Rectangle(274, 137, 88, 30));
	lblDosis.setText("Dosis"); //$NON-NLS-1$
	lblTipo = new JLabel();
	lblTipo.setBounds(new Rectangle(42, 137, 88, 30));
	lblTipo.setText("Tipo"); //$NON-NLS-1$
	lblIdprod = new JLabel();
	lblIdprod.setBounds(new Rectangle(42, 31, 88, 30));
	lblIdprod.setText("ID producto");
	lblActivo = new JLabel();
	lblActivo.setBounds(new Rectangle(274, 84, 88, 30));
	lblActivo.setText("Activo"); //$NON-NLS-1$
	lblNombre = new JLabel();
	lblNombre.setBounds(new Rectangle(42, 84, 88, 30));
	lblNombre.setText("Nombre producto"); //$NON-NLS-1$
	this.setSize(782, 388);
	this.setLayout(null);
	
	this.add(lblIdprod, null);
	this.add(lblNombre, null);
	this.add(lblTipo, null);
	this.add(lblDosis, null);
	this.add(lblDni, null);
	this.add(lblActivo, null);
	this.add(lblDescripcion, null);
	this.add(lblObligtorios, null);
	
	this.add(getBGuardar(), null);
	/this.add(getBRestablecer(), null);
	
	this.add(gettxtIdprod(), null);
	this.add(gettxtNombre(), null);
	this.add(gettxtDosis(), null);
	this.add(gettxtDni(), null);
	this.add(gettxtareaDescripcion(), null);
	this.add(getchkActivo(), null);
	this.add(getcomboTipo(), null);
	
}
private JButton getBGuardar() {
	if (bGuardar == null) {
		bGuardar = new JButton();
		bGuardar.setBounds(new Rectangle(46, 314, 53, 45));
		bGuardar.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/Guardar.png"))); //$NON-NLS-1$
		bGuardar.setToolTipText(Idioma.getString("etSaveNewUser")); //$NON-NLS-1$
		bGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
			
			}

		});
	}
	return bGuardar;
}











}





















