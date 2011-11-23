package code.google.com.opengis.gestionVISUAL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ProductoVisual extends JInternalFrame implements ActionListener  {

	private JPanel panelProducto;
	private JPanel panelAltModifProducto;
		
	private JLabel lblIdprod;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JLabel lblIdtarea;
	private JLabel lblTipo;
	private JLabel lblActivo;
		
	private JButton cmdAltas;
	private JButton cmdBajas;
	private JButton cmdModificar;
	private JButton cmdConfirmar;
	private JButton cmdCancelar;

	private JTextField txtIdprod;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtIdtarea;
	private JTextField txtTipo;
    //private JTextField txtActivo;
	private JTextField txtBuscar;

public ProductoVisual(int alto, int ancho){
	super("Usuarios",false, true, true, true);
	panelProducto =new JPanel();
	panelProducto.setLayout(null);
	this.add(panelProducto);
	
	
	
}
public void a(){
	
//   TEXT FIELD
	txtIdprod= new JTextField ();
	txtIdprod.setVisible(true);
	txtIdprod.setBounds(0,0,0,0);
	panelAltModifProducto.add(txtIdprod);
	
	txtNombre= new JTextField ();
	txtNombre.setVisible(true);
	txtNombre.setBounds(0,0,0,0);
	panelAltModifProducto.add(txtNombre);
	
	txtDescripcion= new JTextField ();
	txtDescripcion.setVisible(true);
	txtDescripcion.setBounds(0,0,0,0);
	panelAltModifProducto.add(txtDescripcion);
	
	txtIdtarea= new JTextField ();
	txtIdtarea.setVisible(true);
	txtIdtarea.setBounds(0,0,0,0);
	panelAltModifProducto.add(txtIdtarea);
	
	txtTipo= new JTextField ();
	txtTipo.setVisible(true);
	txtTipo.setBounds(0,0,0,0);
	panelAltModifProducto.add(txtTipo);
	
	txtBuscar= new JTextField ();
	txtBuscar.setVisible(true);
	txtBuscar.setBounds(0,0,0,0);
	panelProducto.add(txtBuscar);
	
// BUTTON
	cmdAltas= new JButton ("Crear");
	cmdAltas.setVisible(true);
	cmdAltas.setBounds(0,0,0,0);
	panelProducto.add(cmdAltas);
	
	cmdBajas= new JButton ("Desactivar");
	cmdBajas.setVisible(true);
	cmdBajas.setBounds(0,0,0,0);
	panelProducto.add(cmdBajas);
	
	cmdModificar= new JButton ("Modificar");
	cmdModificar.setVisible(true);
	cmdModificar.setBounds(0,0,0,0);
	panelProducto.add(cmdModificar);
	
	cmdConfirmar= new JButton ("Confirmar");
	cmdConfirmar.setVisible(true);
	cmdConfirmar.setBounds(0,0,0,0);
	panelAltModifProducto.add(cmdConfirmar);
	
	cmdCancelar= new JButton ("Cancelar");
	cmdCancelar.setVisible(true);
	cmdCancelar.setBounds(0,0,0,0);
	panelAltModifProducto.add(cmdCancelar);
	
// LABEL
	lblIdprod = new JLabel ("ID Producto");
	lblIdprod.setVisible(true);
	lblIdprod.setBounds(0,0,0,0);
	panelAltModifProducto.add(lblIdprod);
	
	lblNombre = new JLabel ("Nombre");
	lblNombre.setVisible(true);
	lblNombre.setBounds(0,0,0,0);
	panelAltModifProducto.add(lblNombre);
	
	lblDescripcion = new JLabel ("Descripcion");
	lblDescripcion.setVisible(true);
	lblDescripcion.setBounds(0,0,0,0);
	panelAltModifProducto.add(lblDescripcion);
	
	lblIdtarea = new JLabel ("ID Tarea");
	lblIdtarea.setVisible(true);
	lblIdtarea.setBounds(0,0,0,0);
	panelAltModifProducto.add(lblIdtarea);
	
	lblTipo = new JLabel ("Tipo");
	lblTipo.setVisible(true);
	lblTipo.setBounds(0,0,0,0);
	panelAltModifProducto.add(lblTipo);
	
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

}
