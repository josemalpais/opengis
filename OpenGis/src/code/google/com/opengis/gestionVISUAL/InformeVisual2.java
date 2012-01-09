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
	static String id;
	private JButton btnDispositivos;
	private JButton btnParcela;
	private JTable tbaTabla;
	public DefaultTableModel modelo = new DefaultTableModel();
	private String dni = "";
	static int informe;
	
	

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
			{
				btnCuaderno = new JButton();
				this.add(btnCuaderno);
				btnCuaderno.setText("Cuaderno");
				btnCuaderno.setBounds(177, 63, 108, 29);
				btnCuaderno.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					txtBuscador.setEnabled(true);
    					String criterio = txtBuscador.getText();
    					
    					
    					try {
   		    			 String[] columnas = {"DNI", "Nombre", "Apellidos","Direccion", "Poblacion", "Provincia","CP","Telefono","Email","Fecha Nacimiento","Tipo", "Activo"};

    						modelo.setColumnCount(0);
    						modelo.setRowCount(0);
    						String sentencia = "SELECT `dni`, `nombre`, `apellidos`, `dirección`, `población`, `provincia`, `cp`, `teléfono`, `email`, `fecha_nacimiento`, `tipo`, `activo` FROM `usuario` WHERE dni LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%' OR apellidos LIKE '%"+criterio+"%' OR dirección LIKE '%"+criterio+"%' OR población LIKE '%"+criterio+"%' OR provincia LIKE '%"+criterio+"%'";
    						ResultSet rs = ConectarDBA.buscar(sentencia);
    						int nColumnas = rs.getMetaData().getColumnCount();
    						modelo.setColumnIdentifiers(columnas);
    						
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
    				}
			
			{
				btnTrabajador = new JButton();
				this.add(btnTrabajador);
				btnTrabajador.setText("Trabajador");
				btnTrabajador.setBounds(290, 63, 108, 30);
				btnTrabajador.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					txtBuscador.setEnabled(true);
    					String criterio = txtBuscador.getText();
    					
    					
    					try {
   		    			 String[] columnas = {"DNI", "Nombre", "Apellidos","Direccion", "Poblacion", "Provincia","CP","Telefono","Email","Fecha Nacimiento","Tipo", "Activo"};

    						modelo.setColumnCount(0);
    						modelo.setRowCount(0);
    						String sentencia = "SELECT `dni`, `nombre`, `apellidos`, `dirección`, `población`, `provincia`, `cp`, `teléfono`, `email`, `fecha_nacimiento`, `tipo`, `activo` FROM `usuario` WHERE dni LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%' OR apellidos LIKE '%"+criterio+"%' OR dirección LIKE '%"+criterio+"%' OR población LIKE '%"+criterio+"%' OR provincia LIKE '%"+criterio+"%'";
    						ResultSet rs = ConectarDBA.buscar(sentencia);
    						int nColumnas = rs.getMetaData().getColumnCount();
    						modelo.setColumnIdentifiers(columnas);
    						
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
				btnParcela.setText("Parcela");
				btnParcela.setBounds(403, 62, 108, 31);
				btnParcela.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    			 String[] columnas = {"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"};

    					txtBuscador.setEnabled(true);

    					add(getTablaPrincipal());
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
				jLabel1.setText("Selecciona el tipo de informe que va a realizar");
				jLabel1.setBounds(273, 28, 276, 23);
			}
			{
				
			
				txtBuscador = new JTextField(Idioma.getString("msgSearchCriteria")); //$NON-NLS-1$
				txtBuscador.setBounds(new Rectangle(267,110, 284, 32));
				txtBuscador.setSelectedTextColor(new Color(204, 204, 204));
				
				if(dni!=""){
					
					txtBuscador.setText(dni);
					
					txtBuscador.setEnabled(false);
					
					buscar();
					
				}
				
				
				txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
					public void keyTyped(java.awt.event.KeyEvent e) {
						
						
						
						txtBuscador.setText(txtBuscador.getText());
						
						buscar();
						
					}

			
					
			
				

			});
				
				
				txtBuscador.addActionListener(new java.awt.event.ActionListener() {

				

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						
						txtBuscador.setText("");
						
					}

			
					
			
				

			});
				
				
				
				
			}
			
			this.add(txtBuscador);
			
			{
				btnGenerar = new JButton();
				this.add(btnGenerar);
				btnGenerar.setText("Generar Informe");
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
			/*{
				
			    tbaTabla = new JTable();
				this.add(tbaTabla);
				tbaTabla.setModel(modelo);
				tbaTabla.setBounds(21, 180, 778, 162);
				tbaTabla.addMouseListener(new java.awt.event.MouseAdapter() {  // Cuando hagan clic...
					public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("entra");	
						int i = tbaTabla.getSelectedRow();
						System.out.println(i);
						if (i != -1) {
							id = tbaTabla.getValueAt(i, 0).toString();
									System.out.println(id);
						}
						
						
						
					}
				});
				
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	
	public void buscar(){
		

		
		String criterio = txtBuscador.getText();
		
		
		try {
			
			modelo.setColumnCount(0);
			modelo.setRowCount(0);
			
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
			rs.close();
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
					if (i != -1) {
						id = tbaTabla.getValueAt(i, 0).toString();
								
					}
					
					
				}
			});
			
		}
		tbaTabla.setBounds(21, 180, 778, 162);
		tbaTabla.setVisible(true);
		return tbaTabla;
	}
}
