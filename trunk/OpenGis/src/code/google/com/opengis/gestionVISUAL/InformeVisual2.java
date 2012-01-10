package code.google.com.opengis.gestionVISUAL;
import info.clearthought.layout.TableLayout;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JFrame;

import code.google.com.opengis.gestion.InformeCuaderno;
import code.google.com.opengis.gestion.InformeDispositivos;
import code.google.com.opengis.gestion.InformeParcela;
import code.google.com.opengis.gestion.InformeTrabajador;
import code.google.com.opengis.gestion.Parcela;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;



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
public class InformeVisual2 extends javax.swing.JPanel  {
	private boolean redimensionarTabla;

	private JButton btnCuaderno;
	private JButton btnTrabajador;
	private JButton btnGenerar;
	
	private JTextField txtBuscador;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	
	private JScrollPane jScrollPane = null;
	static String id;
	private JButton btnDispositivos;
	private JButton btnParcela;
	private JTable tbaTabla = null;
	static Object[] columna1 = { Idioma.getString("etIdCard"),
		Idioma.getString("etName"), Idioma.getString("etLastName"),
		Idioma.getString("etAddress"), Idioma.getString("etCity"),
		Idioma.getString("etProvince"), Idioma.getString("etPostalCode"),
		Idioma.getString("etPhone"), Idioma.getString("etMail"),
		Idioma.getString("etBirthDate"), Idioma.getString("etType"),
		Idioma.getString("etActive") };
static Object[] columna2 = { Idioma.getString("etIdCard"),
		Idioma.getString("etName"), Idioma.getString("etLastName"),
		Idioma.getString("etAddress"), Idioma.getString("etCity"),
		Idioma.getString("etProvince"), Idioma.getString("etPostalCode"),
		Idioma.getString("etPhone"), Idioma.getString("etMail"),
		Idioma.getString("etBirthDate"), Idioma.getString("etType"),
		Idioma.getString("etActive") };
static Object[] columna3 = { Idioma.getString("etIDLot"),
		Idioma.getString("etAlias"), Idioma.getString("etProvince"),
		Idioma.getString("etCity"), Idioma.getString("etArea"),
		Idioma.getString("etNumber"), Idioma.getString("etEntry"),
		Idioma.getString("etIDOwner") };
static Object[] columna4 = { Idioma.getString("etIdCard"),
		Idioma.getString("etModel"), Idioma.getString("etSerialNumber"),
		Idioma.getString("etAvailabe"), Idioma.getString("etActive") };
	public DefaultTableModel modelo = new DefaultTableModel();
	private String dni = "";
	static int informe;
	static String lol;
	private JFormattedTextField JFecha1;
	private JFormattedTextField JFecha2;


	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new InformeVisual2());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public InformeVisual2() {
		super();
		initGUI();
	}
	
	public InformeVisual2(String dni) {
		super();
		this.dni = dni;
		initGUI();
	}
	
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(811, 402));
			this.setLayout(null);
			this.add(getJScrollPane(), null);
			{
				btnCuaderno = new JButton();
				this.add(btnCuaderno);
				btnCuaderno.setText(Idioma.getString("etBookLog"));
				btnCuaderno.setBounds(175, 29, 108, 29);
				

				btnCuaderno.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					String criterio = txtBuscador.getText();
    					
    					
    					try {
   		    			    
    						modelo.setColumnCount(0);
    						modelo.setRowCount(0);
    						String sentencia = "SELECT `dni`, `nombre`, `apellidos`, `dirección`, `población`, `provincia`, `cp`, `teléfono`, `email`, `fecha_nacimiento`, `tipo`, `activo` FROM `usuario` WHERE dni LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%' OR apellidos LIKE '%"+criterio+"%' OR dirección LIKE '%"+criterio+"%' OR población LIKE '%"+criterio+"%' OR provincia LIKE '%"+criterio+"%'";
    						ResultSet rs = ConectarDBA.buscar(sentencia);
    						int nColumnas = rs.getMetaData().getColumnCount();
    						modelo.setColumnIdentifiers(columna1);
    						
    						while (rs.next()) {
    							
    							Object[] registro = new Object[nColumnas];

    							for (int i = 0; i < nColumnas; i++) {
    								registro[i] = rs.getObject(i + 1); // Guardamos todos los registros
    								
    							}
    							
    							
    							for (int i2 = 0; i2 < registro.length; i2++) {

    								if (registro[i2].toString().equals("true")) { //$NON-NLS-1$
    									registro[i2] = Idioma.getString("etActive"); //$NON-NLS-1$
    								} else if (registro[i2].toString().equals("false")) { //$NON-NLS-1$
    									registro[i2] = Idioma.getString("etInactive"); //$NON-NLS-1$
    								}
    								//System.out.println(registro[i2]);
    							}

    							
    							modelo.addRow(registro); // Añadimos el registro a la tabla

    						}
    						rs.close();
    					} catch (SQLException e1) {
    						System.out.println(e1);

    					}
     					add(getTablaPrincipal());
     					//tbaTabla.setBounds(21, 180, 778, 162);
    					informe = 1;
    					}});
    				}{
    					MaskFormatter mascara = new MaskFormatter("##/##/####");
    					JFecha1 = new JFormattedTextField(mascara);
    					JFecha1.setBounds(476, 76, 91, 23);
    				}
    				{
    					MaskFormatter mascara = new MaskFormatter("##/##/####");
    					JFecha2 = new JFormattedTextField(mascara);
    					JFecha2.setBounds(326, 76, 91, 23);
    				}
    				{
    					jLabel2 = new JLabel();
    					jLabel2.setText("Y");
    					jLabel2.setBounds(429, 79, 10, 16);
    				}
    				 {
    						jLabel3 = new JLabel();
    						jLabel3.setText("Escoja la fecha del informe entre: ");
    						jLabel3.setBounds(103, 79, 216, 16);
    					}
			
			{
				btnTrabajador = new JButton();
				this.add(btnTrabajador);
				btnTrabajador.setText(Idioma.getString("etWorker"));
				btnTrabajador.setBounds(288, 28, 108, 30);
				btnTrabajador.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					String criterio = txtBuscador.getText();
    					
    					
    					try {
   		    			    
    						modelo.setColumnCount(0);
    						modelo.setRowCount(0);
    						String sentencia = "SELECT `dni`, `nombre`, `apellidos`, `dirección`, `población`, `provincia`, `cp`, `teléfono`, `email`, `fecha_nacimiento`, `tipo`, `activo` FROM `usuario` WHERE dni LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%' OR apellidos LIKE '%"+criterio+"%' OR dirección LIKE '%"+criterio+"%' OR población LIKE '%"+criterio+"%' OR provincia LIKE '%"+criterio+"%'";
    						ResultSet rs = ConectarDBA.buscar(sentencia);
    						int nColumnas = rs.getMetaData().getColumnCount();
    						modelo.setColumnIdentifiers(columna2);
    						
    						while (rs.next()) {
    							
    							Object[] registro = new Object[nColumnas];

    							for (int i = 0; i < nColumnas; i++) {
    								registro[i] = rs.getObject(i + 1); // Guardamos todos los registros
    								
    							}
    							
    							
    							for (int i2 = 0; i2 < registro.length; i2++) {

    								if (registro[i2].toString().equals("true")) { //$NON-NLS-1$
    									registro[i2] = Idioma.getString("etActive"); //$NON-NLS-1$
    								} else if (registro[i2].toString().equals("false")) { //$NON-NLS-1$
    									registro[i2] = Idioma.getString("etInactive"); //$NON-NLS-1$
    								}
    								//System.out.println(registro[i2]);
    							}

    							
    							modelo.addRow(registro); // Añadimos el registro a la tabla

    						}
    						rs.close();
    					} catch (SQLException e1) {
    						System.out.println(e1);

    					}
     					add(getTablaPrincipal());
     					//tbaTabla.setBounds(21, 180, 778, 162);
    					informe = 2;
    					}});
    				}
			
			{
				
				
				btnParcela = new JButton();
				this.add(btnParcela);
				btnParcela.setText(Idioma.getString("etLots"));
				btnParcela.setBounds(401, 28, 108, 31);

				btnParcela.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					String criterio = txtBuscador.getText();
    					
    					
    					try {
    			   			
    						modelo.setColumnCount(0);
    						modelo.setRowCount(0);
    						String sentencia = "SELECT `idparcela`, `alias`, `provincia`, `poblacion`, `poligono`, `numero`, `activo`, `partida`, `dni_propietario` FROM `parcela` WHERE idparcela LIKE '%"+criterio+"%' OR alias LIKE '%"+criterio+"%' OR provincia LIKE '%"+criterio+"%' OR poblacion LIKE '%"+criterio+"%' OR poligono LIKE '%"+criterio+"%' OR numero LIKE '%"+criterio+"%' OR activo LIKE '%"+criterio+"%' OR partida LIKE '%"+criterio+"%' OR dni_propietario LIKE '%"+criterio+"%'";
    						ResultSet rs = ConectarDBA.buscar(sentencia);
    						int nColumnas = rs.getMetaData().getColumnCount();
    						modelo.setColumnIdentifiers(columna3);
    						
    						while (rs.next()) {
    							
    							Object[] registro = new Object[nColumnas];

    							for (int i = 0; i < nColumnas; i++) {
    								registro[i] = rs.getObject(i + 1); // Guardamos todos los registros
    								
    							}
    							
    							
    							for (int i2 = 0; i2 < registro.length; i2++) {

    								if (registro[i2].toString().equals("true")) { //$NON-NLS-1$
    									registro[i2] = Idioma.getString("etActive"); //$NON-NLS-1$
    								} else if (registro[i2].toString().equals("false")) { //$NON-NLS-1$
    									registro[i2] = Idioma.getString("etInactive"); //$NON-NLS-1$
    								}
    								//System.out.println(registro[i2]);
    							}

    							
    							modelo.addRow(registro); // Añadimos el registro a la tabla

    						}
    						rs.close();
    					} catch (SQLException e1) {
    						System.out.println(e1);

    					}
     					add(getTablaPrincipal());
    					informe = 3;
    					}});
			}
			{
				btnDispositivos = new JButton();
				this.add(btnDispositivos);
				btnDispositivos.setText(Idioma.getString("etDevices"));
				btnDispositivos.setBounds(514, 28, 107, 31);
				btnDispositivos.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					String criterio = txtBuscador.getText();
    					
    					
    					try {
       	    			
    						modelo.setColumnCount(0);
    						modelo.setRowCount(0);
    						String sentencia = "SELECT `iddispositivo`, `modelo`, `num_serie`, `disponible`, `activo` FROM `dispositivo` WHERE iddispositivo LIKE '%"+criterio+"%' OR modelo LIKE '%"+criterio+"%' OR num_serie LIKE '%"+criterio+"%' OR disponible LIKE '%"+criterio+"%' OR activo LIKE '%"+criterio+"%'";
    						ResultSet rs = ConectarDBA.buscar(sentencia);
    						int nColumnas = rs.getMetaData().getColumnCount();
    						modelo.setColumnIdentifiers(columna4);

    						
    						while (rs.next()) {
    							
    							Object[] registro = new Object[nColumnas];

    							for (int i = 0; i < nColumnas; i++) {
    								registro[i] = rs.getObject(i + 1); // Guardamos todos los registros
    								
    							}
    							
    							
    							for (int i2 = 0; i2 < registro.length; i2++) {

    								if (registro[i2].toString().equals("true")) { //$NON-NLS-1$
    									registro[i2] = Idioma.getString("etActive"); //$NON-NLS-1$
    								} else if (registro[i2].toString().equals("false")) { //$NON-NLS-1$
    									registro[i2] = Idioma.getString("etInactive"); //$NON-NLS-1$
    								}
    								//System.out.println(registro[i2]);
    							}

    							
    							modelo.addRow(registro); // Añadimos el registro a la tabla

    						}
    						rs.close();
    					} catch (SQLException e1) {
    						System.out.println(e1);

    					}
     					add(getTablaPrincipal());
    					informe = 4;
    					}});
				
				if(dni!=""){
					
					btnDispositivos.setEnabled(false);
					btnParcela.setEnabled(false);
					
				}
			}
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText(Idioma.getString("msgReportType"));
				jLabel1.setBounds(271, 5, 276, 23);
			}
			{
				
				
				txtBuscador = new JTextField(""); //$NON-NLS-1$
				txtBuscador.setBounds(273, 136, 284, 32);
				txtBuscador.setSelectedTextColor(new Color(204, 204, 204));
				txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
					public void keyTyped(java.awt.event.KeyEvent e) {
						
						
						
						txtBuscador.setText(txtBuscador.getText());
						
						buscar();
						
					}});
				if(dni!=""){
					
					txtBuscador.setText(dni);
					
					txtBuscador.setEnabled(false);
					
					buscar();
					
				}
				
				
			}
			
			this.add(txtBuscador);
			
			{
				btnGenerar = new JButton();
				this.add(btnGenerar);
				btnGenerar.setText(Idioma.getString("etCreateReport"));
				btnGenerar.setBounds(273, 360, 223, 31);
				btnGenerar.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					
    					switch(informe){
    					case 1:
    					
    						try {
    							
								InformeCuaderno l1 = new InformeCuaderno(id);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
    						
    						break;
    					case 2:
    						try {
    						InformeTrabajador l2 = new InformeTrabajador(id,"2011/01/01","2013/01/01");
    						} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
    						break;
    					case 3:
    						try {
    							
    						InformeParcela l3 = new InformeParcela(id);
    					} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
    						break;
    					case 4:
    						try {
    						InformeDispositivos l4 = new InformeDispositivos(id);
    				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    						break;
    					}
    						
    					}});
			}
			{
				
			    tbaTabla = new JTable();
				//this.add(tbaTabla);
				this.add(getJFormattedTextField1());
				this.add(getJFormattedTextField2());
				this.add(getJLabel4());
				this.add(getJLabel4x());
				tbaTabla.setModel(modelo);
				//tbaTabla.setBounds(21, 180, 778, 162);
				tbaTabla.addMouseListener(new java.awt.event.MouseAdapter() {  // Cuando hagan clic...
					public void mouseClicked(java.awt.event.MouseEvent e) {
						
						
						int i = tbaTabla.getSelectedRow();
						System.out.println(i);
						if (i != -1) {
							id = tbaTabla.getValueAt(i, 0).toString();
									
						}
						
						
					}
				});
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	
	public void buscar(){
		

		
		String criterio = txtBuscador.getText();
		
		
		try {
			
			modelo.setColumnCount(0);
			modelo.setRowCount(0);
			if(criterio.equals("")){}
			else{
				System.out.println("entra y criterio es : "+criterio);
			String sentencia = "SELECT `dni`, `nombre`, `apellidos`, `dirección`, `población`, `provincia`, `cp`, `teléfono`, `email`, `fecha_nacimiento`, `tipo`, `activo` FROM `usuario` WHERE dni LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%' OR apellidos LIKE '%"+criterio+"%' OR dirección LIKE '%"+criterio+"%' OR población LIKE '%"+criterio+"%' OR provincia LIKE '%"+criterio+"%'";

			 
			ResultSet rs = ConectarDBA.buscar(sentencia);
			int nColumnas = rs.getMetaData().getColumnCount();
			//modelo.setColumnIdentifiers(nombreColumna);
			
			while (rs.next()) {
				
				Object[] registro = new Object[nColumnas];

				for (int i = 0; i < nColumnas; i++) {
					registro[i] = rs.getObject(i + 1); // Guardamos todos los registros
					
				}
				
				
				for (int i2 = 0; i2 < registro.length; i2++) {

					if (registro[i2].toString().equals("true")) { //$NON-NLS-1$
						registro[i2] = Idioma.getString("etActive"); //$NON-NLS-1$
					} else if (registro[i2].toString().equals("false")) { //$NON-NLS-1$
						registro[i2] = Idioma.getString("etInactive"); //$NON-NLS-1$
					}
					//System.out.println(registro[i2]);
				}

				
				modelo.addRow(registro); // Añadimos el registro a la tabla

			}
			rs.close();}
		} catch (SQLException e1) {
			System.out.println(e1);

		}
		
		
		
		
	}
	public final JTable getTablaPrincipal() {
		if (tbaTabla == null) {
			tbaTabla = new JTable(modelo)
			{
				
			    public boolean isCellEditable(int row, int column) {  // La tabla no será editable
				       return false;
				    }
			};
			
			if (redimensionarTabla==true){
			
				tbaTabla.setAutoResizeMode(0);
				
			}
			
			tbaTabla.addMouseListener(new java.awt.event.MouseAdapter() {  // Cuando hagan clic...
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
					
					int i = tbaTabla.getSelectedRow();
					System.out.println(i);
					if (i != -1) {
						id = tbaTabla.getValueAt(i, 0).toString();
								
					}
					
					
				}
			});
			
		}
		
		tbaTabla.setVisible(true);
		return tbaTabla;
	}
	
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(21, 180, 778, 162));
			jScrollPane.setViewportView(getTablaPrincipal());
		}
		return jScrollPane;
	}
	
	private JFormattedTextField getJFormattedTextField1() {
		if(JFecha1 == null) {
			JFecha1 = new JFormattedTextField();
			JFecha1.setText("01/01/2011");
			JFecha1.setBounds(267, 76, 117, 23);
		}
		return JFecha1;
	}
	
	private JFormattedTextField getJFormattedTextField2() {
		if(JFecha2 == null) {
			JFecha2 = new JFormattedTextField();
			JFecha2.setText("");
			JFecha2.setBounds(397, 76, 117, 23);
		}
		return JFecha2;
	}
	
	private JLabel getJLabel4() {
		if(jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Y");
			jLabel2.setBounds(401, 79, 18, 16);
		}
		return jLabel2;
	}
	
	private JLabel getJLabel4x() {
		if(jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Escoja la fecha del informe entre: ");
			jLabel3.setBounds(128, 79, 84, 16);
		}
		return jLabel3;
	}
}