package code.google.com.opengis.gestionVISUAL;

import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import code.google.com.opengis.gestion.Producto;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
     private static String[] tipoproducto = {"Líquido", "Granulado", "Polvo"};
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
	private Icon activo;
 	 
public ProductoPanelGestion (int idprod, String nombre, String descripcion, String tipo, String dosis, String dni, Icon activo, String accion){
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
	lblDescripcion.setText("Descripcion"); //$NON-NLS-1$
	lblDni = new JLabel();
	lblDni.setBounds(new Rectangle(44, 192, 88, 30));
	lblDni.setText("Dni"); //$NON-NLS-1$
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
	this.add(getBRestablecer(), null);
	
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
	//	bGuardar.setToolTipText(Idioma.getString("etSaveNewUser")); //$NON-NLS-1$
		bGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				   int variableint;
					if (chkActivo.isSelected()){
                       	variableint=0;
                       }else{
                       	variableint=1;
                       }
                       Producto p = new Producto(Integer.parseInt(txtIdprod.getText()), txtNombre.getText(), txtareaDescripcion.getText(), comboTipo.getSelectedItem().toString(), txtDosis.getText(), txtDni.getText(), variableint);        
                       p.validarDatos();
                       if (p.getCorrecto()) {
							p.crearProducto();
							 try {	
					                ResultSet rs = ConectarDBA.consulta("SELECT MAX(idprod) FROM producto");
					                Integer idprod; 
					                rs.next();
					                idprod = rs.getInt(1); 
					                if(idprod==null){	 
					               	 idprod = 0;
					                }
					                txtIdprod.setText(idprod+1+"");
							 } catch (SQLException e2) {
					                System.out.println(e2.getMessage());
							 }
                       }
                   }
		});
	}
	return bGuardar;
}

private JButton getBRestablecer() {
	if (bRestablecer == null) {
		bRestablecer = new JButton();
		bRestablecer.setBounds(new Rectangle(122, 314, 53, 45));
		bRestablecer.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/Limpiar.png"))); 
		bRestablecer.setToolTipText(Idioma.getString("etCleanFields")); 
		bRestablecer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				txtNombre.setText(""); //$NON-NLS-1$
				txtDni.setText(""); //$NON-NLS-1$
				txtDosis.setText(""); //$NON-NLS-1$
				txtareaDescripcion.setText(""); //$NON-NLS-1$
				chkActivo.setSelected(false); //$NON-NLS-1$
				comboTipo.removeAllItems();
				try { 
	           	 	ConectarDBA.acceder();
	                ResultSet rs = ConectarDBA.consulta("SELECT MAX(idprod) FROM producto");
	                Integer idprod;
	                
	                rs.next();
	                idprod = rs.getInt(1);
	                
	                if(idprod==null){
	               	 
	               	 idprod = 0;
	               	 
	                }
	                txtIdprod.setText(idprod+1+"");
	            } catch (SQLException e2) {
	                System.out.println(e2.getMessage());
	            }
			}
		});
	}
	return bRestablecer;
}



private JTextField gettxtIdprod() {
	if (txtIdprod == null) {
		txtIdprod = new JTextField(dni);
		txtIdprod.setBounds(new Rectangle(123, 33, 143, 27));
		
		if(accion=="modificar"){ 
			
			txtIdprod.setEnabled(false);
		}
		
	}
	return txtIdprod;
}
private JTextField gettxtNombre() {
	if (txtNombre == null) {
		txtNombre = new JTextField(nombre);
		txtNombre.setBounds(new Rectangle(123, 86, 143, 27));
	}
	return txtNombre;
}

private JCheckBox getchkActivo() {
	if (chkActivo == null) {
		chkActivo = new JCheckBox(activo);
		chkActivo.setBounds(new Rectangle(358, 86, 115, 27));
	}
	return chkActivo;
}
private JComboBox getcomboTipo() {
	if (comboTipo == null) {
		comboTipo = new JComboBox(tipoproducto);
		comboTipo.setBounds(new Rectangle(123, 139, 143, 27));
		
		//comboTipo.addItem(Idioma.getString("etAdmin")); //$NON-NLS-1$
		//comboTipo.addItem(Idioma.getString("etWorker")); //$NON-NLS-1$
		comboTipo.addActionListener(new ActionListener(){
        	public void actionPerformed (ActionEvent e){
        		int selIndex = comboTipo.getSelectedIndex();
        			switch (selIndex){
        			case 0:
        				lblDosis.setText("Dosis (l/ha):");
        			break;
        			case 1:
        				lblDosis.setText("Dosis (gr/ha):");
        			break;
        			case 2:
        				lblDosis.setText("Dosis (kg/ha)");
        			break;
        			}
        	}
        });
		
	}
	return comboTipo;
}
private JTextField gettxtDosis() {
	if (txtDosis == null) {
		txtDosis = new JTextField(dosis);
		txtDosis.setBounds(new Rectangle(358, 139, 115, 27));
	}
	return txtDosis;
}

private JTextField gettxtDni() {
	if (txtDni == null) {
		txtDni = new JTextField(dni);
		txtDni.setBounds(new Rectangle(123, 194, 143, 27));
	}
	return txtDni;
}
private JTextArea gettxtareaDescripcion() {
	if (txtareaDescripcion == null) {
		txtareaDescripcion = new JTextArea(descripcion);
		txtareaDescripcion.setBounds(new Rectangle(358, 194, 115, 27));
	}
	return txtareaDescripcion;
}

}





















