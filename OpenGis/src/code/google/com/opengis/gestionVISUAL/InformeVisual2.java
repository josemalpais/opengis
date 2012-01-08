package code.google.com.opengis.gestionVISUAL;
import info.clearthought.layout.TableLayout;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JFrame;

import code.google.com.opengis.gestion.InformeCuaderno;
import code.google.com.opengis.gestion.InformeDispositivos;
import code.google.com.opengis.gestion.InformeParcela;
import code.google.com.opengis.gestion.InformeTrabajador;
import code.google.com.opengis.gestion.Parcela;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class InformeVisual2 extends javax.swing.JPanel {
	private JButton btnCuaderno;
	private JButton btnTrabajador;
	private JButton btnGenerar;
	private JTable tbltabla;
	private JTextField txtBuscador;
	private JLabel jLabel1;
	private JButton btnDispositivos;
	private JButton btnParcela;
	private JTable tbaTabla;
	public DefaultTableModel modelo = new DefaultTableModel();
	static int informe;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new InformesVisual());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public InformeVisual2() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(811, 402));
			this.setLayout(null);
			{
				btnCuaderno = new JButton();
				this.add(btnCuaderno);
				btnCuaderno.setText("Cuaderno");
				btnCuaderno.setBounds(177, 63, 108, 29);
				btnCuaderno.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					txtBuscador.setEnabled(true);
    				
    	    			 String[] columnas = {"Dni", "Nombre", "Apellidos","Direccion", "Poblacion", "Provincia","CodigoPostal","Telefono"};

     					modelo = new DefaultTableModel();
     					modelo.setColumnIdentifiers(columnas);
     					tbaTabla = new JTable(modelo);
     					add(tbaTabla);
     					tbaTabla.setBounds(21, 180, 778, 162);
    					informe = 1;
    					}});
    				}
			
			{
				btnTrabajador = new JButton();
				this.add(btnTrabajador);
				btnTrabajador.setText("Trabajador");
				btnTrabajador.setBounds(290, 63, 108, 30);
				btnTrabajador.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					txtBuscador.setEnabled(true);
    					
    					
    					String[] columnas = {"Dni", "Nombre", "Apellidos","Direccion", "Poblacion", "Provincia","CodigoPostal","Telefono"};

    					modelo = new DefaultTableModel();
    					modelo.setColumnIdentifiers(columnas);
    					tbaTabla = new JTable(modelo);
    					add(tbaTabla);
    					tbaTabla.setBounds(21, 180, 778, 162);
    					informe  =2;
    					}});
			}
			{
				btnParcela = new JButton();
				this.add(btnParcela);
				btnParcela.setText("Parcela");
				btnParcela.setBounds(403, 62, 108, 31);
				btnParcela.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    	    			 tbaTabla.removeAll();
    			 String[] columnas = {"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"};

    					txtBuscador.setEnabled(true);

    					modelo = new DefaultTableModel();
    					modelo.setColumnIdentifiers(columnas);
    					tbaTabla = new JTable(modelo);
    					add(tbaTabla);
    					tbaTabla.setBounds(21, 180, 778, 162);
    					informe = 3;
    					}});
			}
			{
				btnDispositivos = new JButton();
				this.add(btnDispositivos);
				btnDispositivos.setText("Dispositivos");
				btnDispositivos.setBounds(516, 62, 107, 31);
				btnDispositivos.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					
    	    			 String[] columnas = {"Dni", "Modelo", "Número de Serie","Disponible", "Activo"};

    					txtBuscador.setEnabled(true);
    					
    					modelo = new DefaultTableModel();
    					modelo.setColumnIdentifiers(columnas);
    					tbaTabla = new JTable(modelo);
    					add(tbaTabla);
    					tbaTabla.setBounds(21, 180, 778, 162);
    					
    					informe = 4;
    					}});
			}
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("Selecciona el tipo de informe que va a realizar");
				jLabel1.setBounds(273, 28, 276, 23);
			}
			{
				txtBuscador = new JTextField();
				this.add(txtBuscador);
				txtBuscador.setBounds(285, 115, 242, 33);
				txtBuscador.setEnabled(false);

			}
			
			{
				btnGenerar = new JButton();
				this.add(btnGenerar);
				btnGenerar.setText("Generar Informe");
				btnGenerar.setBounds(273, 360, 223, 31);
				btnGenerar.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					switch(informe){
    					case 1:
    						InformeCuaderno l1 = new InformeCuaderno(id);
    						break;
    					case 2:
    						InformeTrabajador l2 = new InformeTrabajador(id);
    						break;
    					case 3:
    						InformeParcela l3 = new InformeParcela(id);
    						break;
    					case 4:
    						InformeDispositivos l4 = new InformeDispositivos(id);
    					}
    						
    					}});
			}
			{
				
			 /*   tbaTabla = new JTable();
				this.add(tbaTabla);
				tbaTabla.setModel(modelo);
				tbaTabla.setBounds(21, 180, 778, 162);*/
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void rellenarTabla(String criterio,String[]columnas, Object[]filax){
		
		try {
			int x=0;
			int y=0;
			modelo.setColumnCount(0);
				modelo.setRowCount(0);
				modelo.setColumnIdentifiers(columnas);
			
				ResultSet rs = Parcela.buscar(criterio);
				while (rs.next()) {
					Object[]fila =filax;
					modelo.addRow(fila);
				}
				rs.close();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
