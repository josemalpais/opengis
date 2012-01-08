/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionVISUAL;
		
		
		
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

/**
 * 
 * 
 * @author Juan Carlos Garc�a del Canto
 *
 */

    public class VentanaPrincipal extends JFrame implements WindowListener {

    	private static final long serialVersionUID = 1L;
    	private JPanel jContentPane = null;
    	private JButton bPrestamos = null;
    	private JButton bUsuarios = null;
    	private JButton bParcelas = null;
    	private JButton bProductos = null;
    	private JButton bAperos = null;
    	private JButton bInformes = null;
    	private JButton bDispositivos = null;
    	private JButton bSalir = null;
		private JLabel lblPrestamos = null;
		private JLabel lblUsuarios = null;
		private JLabel lblInformes = null;
		private JLabel lblParcelas = null;
		private JLabel lblApero = null;
		private JLabel lblProductos = null;
		private JLabel lblDispositivos = null;
		private JLabel lblSalir = null;
		private static JTabbedPane tabsPaneles = null;
		
		private String dniUsuario;
		private char tipoUsuario;
		private JButton bCerrarPesta�a = null;
		private JButton bIdiomaCatalan = null;
		private JButton bIdiomaSpanish = null;
		private JButton bIdiomaEnglish = null;

    	
    	/**
    	 * Constructor de la VentanaPrincipal. Dependiendo del tipo de usuario cargar�
    	 * unos botones u otros.
    	 */
    	public VentanaPrincipal(char tipoDeUsuario, String dni,String idiomaDefecto){
    		super();
    		new Idioma(idiomaDefecto);
		
    		/*
    		 * Dependiendo del tipo de usuario que sea cargaremos unas funciones u otras
    		 * 
    		 */
    		this.dniUsuario = dni;
    		
    		if (tipoDeUsuario == 'a'){ 
    			
    			this.tipoUsuario = 'a';
    			initializeAdministrador();
    			cargarAdministrador();
    			
    		}
    		
    		if (tipoDeUsuario == 't'){ 
    			
    			this.tipoUsuario = 't';
    			initializeUsuarios();
    			cargarUsuarios();
    			
    		}
    		
    		
            
           
            
        }

    	/**
		 * Este m�todo inicializa el panel en Modo Administrador
		 * 
		 */
		private void initializeAdministrador() {
			
	        this.setBounds(new Rectangle(0, 0, 888, 624));
	        this.setContentPane(getJContentPaneAdministrador());
				
		}

		/**
    	 * Este m�todo incializa la interfaz gr�fica en modo administrador
    	 * 
    	 * @return void
    	 */
    	private void cargarAdministrador() {
    		this.setPreferredSize(new Dimension(800, 600));
    		this.setBounds(new Rectangle(0, 0, 888, 619));
    		this.setResizable(false);
    		this.setContentPane(getJContentPaneAdministrador());
    		this.setTitle(Idioma.getString("etMainWindow")+ dniUsuario); //$NON-NLS-1$
    		this.setLocationRelativeTo(null); // Centramos el formulario
    		this.setVisible(true);
    		
    		this.addWindowListener(new WindowAdapter(){ // A�adimos el Listener
                public void windowClosing(WindowEvent e) {              
                    dialog_salir();          
                }
            });
    		
    	}

    	/**
    	 * Este m�todo inicializa el JContentPane del Administrador
    	 * 
    	 * @return javax.swing.JPanel
    	 */
    	private JPanel getJContentPaneAdministrador() {
    		if (jContentPane == null) {
    			lblSalir = new JLabel();
    			lblSalir.setBounds(new Rectangle(773, 87, 76, 22));
    			lblSalir.setText(Idioma.getString("etExit")); //$NON-NLS-1$
    			lblDispositivos = new JLabel();
    			lblDispositivos.setBounds(new Rectangle(652, 87, 103, 22));
    			lblDispositivos.setText(Idioma.getString("etDevices")); //$NON-NLS-1$
    			lblProductos = new JLabel();
    			lblProductos.setBounds(new Rectangle(555, 87, 94, 22));
    			lblProductos.setText(Idioma.getString("etProducts")); //$NON-NLS-1$
    			lblApero = new JLabel();
    			lblApero.setBounds(new Rectangle(462, 88, 70, 22));
    			lblApero.setText(Idioma.getString("etImplements")); //$NON-NLS-1$
    			lblParcelas = new JLabel();
    			lblParcelas.setBounds(new Rectangle(352, 87, 85, 22));
    			lblParcelas.setText(Idioma.getString("etLots")); //$NON-NLS-1$
    			lblInformes = new JLabel();
    			lblInformes.setBounds(new Rectangle(249, 87, 84, 22));
    			lblInformes.setText(Idioma.getString("etReports")); //$NON-NLS-1$
    			lblUsuarios = new JLabel();
    			lblUsuarios.setBounds(new Rectangle(147, 87, 79, 22));
    			lblUsuarios.setText(Idioma.getString("etUsers")); //$NON-NLS-1$
    			lblPrestamos = new JLabel();
    			lblPrestamos.setBounds(new Rectangle(38, 87, 93, 22));
    			lblPrestamos.setText(Idioma.getString("etLoans")); //$NON-NLS-1$
    			jContentPane = new JPanel();
    			jContentPane.setLayout(null);
    			jContentPane.add(getBPrestamos(), null);
    			jContentPane.add(getBUsuarios(), null);
    			jContentPane.add(getBPacerlas(), null);
    			jContentPane.add(getBProductos(), null);
    			jContentPane.add(getBAperos(), null);
    			jContentPane.add(getBInformes(), null);
    			jContentPane.add(getBDispositivos(), null);
    			jContentPane.add(getBSalir(), null);
    			jContentPane.add(lblPrestamos, null);
    			jContentPane.add(lblUsuarios, null);
    			jContentPane.add(lblInformes, null);
    			jContentPane.add(lblParcelas, null);
    			jContentPane.add(lblApero, null);
    			jContentPane.add(lblProductos, null);
    			jContentPane.add(lblDispositivos, null);
    			jContentPane.add(lblSalir, null);
    			jContentPane.add(getTabsPaneles(), null);
    			jContentPane.add(getBCerrarPesta�a(), null);
    			jContentPane.add(getBIdiomaCatalan(), null);
    			jContentPane.add(getBIdiomaSpanish(), null);
    			jContentPane.add(getBIdiomaEnglish(), null);
    			
    			
    		}
    		return jContentPane;
    	}
    	
    	
    	/**
    	 * Icializa la aplicaci�n seg�n el tipo usuario
    	 * 
    	 */
    	
    	private void initializeUsuarios() {
	
	        this.setBounds(new Rectangle(0, 0, 888, 624));
	        this.setContentPane(getJContentPaneUsuario());
				
		}

		/**
    	 * Este m�todo incializa la interfaz gr�fica en modo usuario
    	 * 
    	 * @return void
    	 */
    	private void cargarUsuarios() {
    		this.setPreferredSize(new Dimension(800, 600));
    		this.setBounds(new Rectangle(0, 0, 888, 619));
    		this.setResizable(false);
    		this.setContentPane(getJContentPaneUsuario());
    		this.setTitle(Idioma.getString("etMainWindow")+ dniUsuario); //$NON-NLS-1$
    		this.setLocationRelativeTo(null); // Centramos el formulario
    		this.setVisible(true);
    		
    		this.addWindowListener(new WindowAdapter(){ // A�adimos el Listener
                public void windowClosing(WindowEvent e) {              
                    dialog_salir();          
                }
            });
    		
    	}

    	/**
    	 * Este m�todo inicializa el JContentPane del Usuario
    	 * 
    	 * @return javax.swing.JPanel
    	 */
    	private JPanel getJContentPaneUsuario() {
    		if (jContentPane == null) {
    			lblSalir = new JLabel();
    			lblSalir.setBounds(new Rectangle(676, 87, 103, 22));
    			lblSalir.setText(Idioma.getString("etExit")); //$NON-NLS-1$
    			lblProductos = new JLabel();
    			lblProductos.setBounds(new Rectangle(555, 87, 94, 22));
    			lblProductos.setText(Idioma.getString("etMyProducts")); //$NON-NLS-1$
    			lblApero = new JLabel();
    			lblApero.setBounds(new Rectangle(462, 88, 70, 22));
    			lblApero.setText(Idioma.getString("etMyImplements")); //$NON-NLS-1$
    			lblParcelas = new JLabel();
    			lblParcelas.setBounds(new Rectangle(352, 87, 85, 22));
    			lblParcelas.setText(Idioma.getString("etMyLots")); //$NON-NLS-1$
    			lblInformes = new JLabel();
    			lblInformes.setBounds(new Rectangle(249, 87, 84, 22));
    			lblInformes.setText(Idioma.getString("etReports")); //$NON-NLS-1$
    			lblUsuarios = new JLabel();
    			lblUsuarios.setBounds(new Rectangle(147, 87, 79, 22));
    			lblUsuarios.setText(Idioma.getString("etMyData")); //$NON-NLS-1$
    			jContentPane = new JPanel();
    			jContentPane.setLayout(null);
    			jContentPane.add(getBUsuarios(), null);
    			jContentPane.add(getBPacerlas(), null);
    			jContentPane.add(getBProductos(), null);
    			jContentPane.add(getBAperos(), null);
    			jContentPane.add(getBInformes(), null);
    			jContentPane.add(getBSalir(), null);
    			jContentPane.add(lblUsuarios, null);
    			jContentPane.add(lblInformes, null);
    			jContentPane.add(lblParcelas, null);
    			jContentPane.add(lblApero, null);
    			jContentPane.add(lblProductos, null);
    			jContentPane.add(lblSalir, null);
    			jContentPane.add(getTabsPaneles(), null);
    			jContentPane.add(getBCerrarPesta�a(), null);
    			jContentPane.add(getBIdiomaCatalan(), null);
    			jContentPane.add(getBIdiomaSpanish(), null);
    			jContentPane.add(getBIdiomaEnglish(), null);
    			
    			
    		}
    		return jContentPane;
    	}
    	
    	
    	
    	

    	/**
    	 * This method initializes bPrestamos	
    	 * 	
    	 * @return javax.swing.JButton	
    	 */
    	private JButton getBPrestamos() {
    		if (bPrestamos == null) {
    			bPrestamos = new JButton();
    			bPrestamos.setBounds(new Rectangle(40, 33, 63, 58));
    			bPrestamos.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/alquiler.png")); //$NON-NLS-1$
    			bPrestamos.setToolTipText(Idioma.getString("etLoansMsg")); //$NON-NLS-1$
    			bPrestamos.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					bCerrarPesta�a.setVisible(true);
    					
    					if (tipoUsuario == 'a'){
    						
    						int numPesta�as = tabsPaneles.getTabCount();
    						
    						if (numPesta�as <10) {
							
    							PrestamoPanelPrincipal panelNuevo = new PrestamoPanelPrincipal();
    						
    							tabsPaneles.addTab(Idioma.getString("etLoansMng"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPesta�as);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
    						}
    							
    							
    					}else{
    						
    						// INSERTAR AQUI METODO PARA TRABAJADOR
    					
    					}
    				}
    			}
    					);
    			
    		}
    		return bPrestamos;
    	}

    	/**
    	 * This method initializes bUsuarios	
    	 * 	
    	 * @return javax.swing.JButton	
    	 */
    	private JButton getBUsuarios() {
    		if (bUsuarios == null) {
    			bUsuarios = new JButton();
    			bUsuarios.setBounds(new Rectangle(143, 33, 63, 58));
    			bUsuarios.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/usuario.png")); //$NON-NLS-1$
    			if(tipoUsuario == 't'){
    				
    				bUsuarios.setToolTipText(Idioma.getString("etMyData")); //$NON-NLS-1$
    				
    			}else{
    				
    				bUsuarios.setToolTipText(Idioma.getString("etUsersMng")); //$NON-NLS-1$
    				
    			}
 
    			bUsuarios.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					
    					bCerrarPesta�a.setVisible(true);
    					
    					if (tipoUsuario == 'a'){
    						
    						int numPesta�as = tabsPaneles.getTabCount();
    						
    						if (numPesta�as <10) {
							
    							UsuariosPanelPrincipal panelNuevo = new UsuariosPanelPrincipal();
    						
    							tabsPaneles.addTab(Idioma.getString("etUsersMng"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPesta�as);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
    						}
    							
    							
    					}else{
    						
    						// INSERTAR AQUI METODO PARA TRABAJADOR
    						
        						
        						int numPesta�as = tabsPaneles.getTabCount();
        						
        						if (numPesta�as <10) {
        							
        							ConectarDBA.acceder();
        							
        							ResultSet rs;
									try {
										
										rs = ConectarDBA.consulta("SELECT dni,nombre,apellidos,email,password,tel�fono,direcci�n,poblaci�n,provincia,cp,fecha_nacimiento FROM usuario WHERE dni='"+ dniUsuario +"'");
	        							
	        							String datos[] = new String[11];
	        							
	        							rs.next();
	        								
	        							datos[0] = rs.getString(1);
	        							datos[1] = rs.getString(2); 
	        							datos[2] = rs.getString(3); 
	        							datos[3] = rs.getString(4); 
	        							datos[4] = rs.getString(5); 
	        							datos[5] = rs.getString(6); 
	        							datos[6] = rs.getString(7); 
	        							datos[7] = rs.getString(8); 
	        							datos[8] = rs.getString(9); 
	        							datos[9] = rs.getString(10); 
	        							datos[10] = rs.getString(11);

	        							
	        							UsuariosPanelDatosPersonales panelDatos = new UsuariosPanelDatosPersonales(datos[0],datos[1],datos[2],datos[6],datos[7],datos[8],datos[9],datos[5],datos[3],datos[10],datos[4]);
	            						
	        							tabsPaneles.addTab(Idioma.getString("etMyPersonalData"),panelDatos); //$NON-NLS-1$
	        							tabsPaneles.setSelectedIndex(numPesta�as);
									
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
        							
        						}else{
        							
        							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
        							
        						}
    						
    					}
    					
    				}
    			});
    		}
    		return bUsuarios;
    	}

    	/**
    	 * This method initializes bPacerlas	
    	 * 	
    	 * @return javax.swing.JButton	
    	 */
    	private JButton getBPacerlas() {
    		if (bParcelas == null) {
    			bParcelas = new JButton();
    			bParcelas.setBounds(new Rectangle(349, 33, 63, 58));
    			bParcelas.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/parcela.png")); //$NON-NLS-1$
    			bParcelas.setToolTipText(Idioma.getString("etLotsMng")); //$NON-NLS-1$
    			bParcelas.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					
    					bCerrarPesta�a.setVisible(true);

    					if(tipoUsuario=='a'){
    						
    						int numPesta�as = tabsPaneles.getTabCount();
    						
    						if (numPesta�as <10) {
							
    							ParcelasPanelPrincipal panelNuevo = new ParcelasPanelPrincipal();
    						
    							tabsPaneles.addTab(Idioma.getString("etLotsMng"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPesta�as);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
    						}
    						
    					}else{
    						
    						
    						// AQU� LA ACCI�N DEL TRABAJADOR
    						
    						int numPesta�as = tabsPaneles.getTabCount();
    						
    						if (numPesta�as <10) {
							
    							ParcelasPanelDatosPersonales panelNuevo = new ParcelasPanelDatosPersonales(dniUsuario);
    						
    							tabsPaneles.addTab(Idioma.getString("etMyLots"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPesta�as);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
    						}
    						
    						
    					}
    						
    							
    					
    				}
    			});
    		
    		}
    		return bParcelas;
    	}

    	/**
    	 * This method initializes bProductos	
    	 * 	
    	 * @return javax.swing.JButton	
    	 */
    	private JButton getBProductos() {
    		if (bProductos == null) {
    			bProductos = new JButton();
    			bProductos.setBounds(new Rectangle(555, 33, 63, 58));
    			bProductos.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/producto.png")); //$NON-NLS-1$
    			bProductos.setToolTipText(Idioma.getString("etProductsMng")); //$NON-NLS-1$
    			bProductos.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					
    					bCerrarPesta�a.setVisible(true);
    					
    					if (tipoUsuario == 'a'){
    						
    						int numPesta�as = tabsPaneles.getTabCount();
    						
    						if (numPesta�as <10) {
							
    							ProductoPanelPrincipal panelNuevo = new ProductoPanelPrincipal();
    						
    							tabsPaneles.addTab(Idioma.getString("etProductsMng"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPesta�as);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
    						}
    							
    							
    					}else{
    						
    						// INSERTAR AQUI METODO PARA TRABAJADOR
    						
    						int numPesta�as = tabsPaneles.getTabCount();
    						
    						if (numPesta�as <10) {
							
    							ProductoPanelDatosPersonales panelNuevo = new ProductoPanelDatosPersonales(dniUsuario);
    						
    							tabsPaneles.addTab(Idioma.getString("etMyProducts"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPesta�as);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
    						}
    						
    					}
    					
    				}
    			});
    		}
    		return bProductos;
    	}

    	/**
    	 * This method initializes bAperos	
    	 * 	
    	 * @return javax.swing.JButton	
    	 */
    	private JButton getBAperos() {
    		if (bAperos == null) {
    			bAperos = new JButton();
    			bAperos.setBounds(new Rectangle(452, 33, 63, 58));
    			bAperos.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/apero.png")); //$NON-NLS-1$
    			bAperos.setToolTipText(Idioma.getString("etImplementsMng")); //$NON-NLS-1$
    			bAperos.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					bCerrarPesta�a.setVisible(true);

    					if(tipoUsuario == 'a'){
    						
    						int numPesta�as = tabsPaneles.getTabCount();
    						
    						if (numPesta�as <10) {
							
    							AperosPanelPrincipal panelNuevo = new AperosPanelPrincipal();
    						
    							tabsPaneles.addTab(Idioma.getString("etImplementsMng"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPesta�as);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
    						}
							
    						
    					}else{
    						
    						
    						// INSERTAR AQU� FUNCIONALIDAD DE TRABAJADOR
    						
    						
    						int numPesta�as = tabsPaneles.getTabCount();
    						
    						if (numPesta�as <10) {
							
    							AperosPanelPrincipalUsuarios panelNuevo = new AperosPanelPrincipalUsuarios(dniUsuario);
    						
    							tabsPaneles.addTab(Idioma.getString("etMyImplements"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPesta�as);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
    						}
							
    						
    					}
    						
    						
        					
    				}
    			});
    		
    		}
    		return bAperos;
    	}

    	/**
    	 * This method initializes bInformes	
    	 * 	
    	 * @return javax.swing.JButton	
    	 */
    	private JButton getBInformes() {
    		if (bInformes == null) {
    			bInformes = new JButton();
    			bInformes.setBounds(new Rectangle(246, 33, 63, 58));
    			bInformes.setIcon(new ImageIcon ("OpenGis/src/recursosVisuales/informes.png")); //$NON-NLS-1$
    			bInformes.setToolTipText(Idioma.getString("etReportsMng")); //$NON-NLS-1$
    			bInformes.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					bCerrarPesta�a.setVisible(true);

    					if(tipoUsuario == 'a'){
    						
    						int numPesta�as = tabsPaneles.getTabCount();
    						
    						if (numPesta�as <10) {
							
    							InformeVisual2 panelNuevo = new InformeVisual2();
    						
    							tabsPaneles.addTab(Idioma.getString("etImplementsMng"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPesta�as);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
    						}
							
    						
    					}else{
    						
    						
    						// INSERTAR AQU� FUNCIONALIDAD DE TRABAJADOR
    						
    					}
    						
    						
        					
    				}
    			});
    		}
    		return bInformes;
    	}

    	/**
    	 * This method initializes bDispositivos	
    	 * 	
    	 * @return javax.swing.JButton	
    	 */
    	private JButton getBDispositivos() {
    		if (bDispositivos == null) {
    			bDispositivos = new JButton();
    			bDispositivos.setBounds(new Rectangle(658, 33, 63, 58));
    			bDispositivos.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/dispositivo.png")); //$NON-NLS-1$
    			bDispositivos.setToolTipText(Idioma.getString("etDevicesMng")); //$NON-NLS-1$
    			bDispositivos.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					
    					bCerrarPesta�a.setVisible(true);

    					if(tipoUsuario=='a'){
    						
    						int numPesta�as = tabsPaneles.getTabCount();
    						
    						if (numPesta�as <10) {
							
    							DispositivosPanelPrincipal panelNuevo = new DispositivosPanelPrincipal();
    						
    							tabsPaneles.addTab(Idioma.getString("etDevicesMng"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPesta�as);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
    						}
    						
    					} else{
    						
    						
    						// INSERTAR AQU� ACCI�N DEL TRABAJADOR
    						
    						
    					}
    							
    					
    				}
    			});
    		
    		}
    		return bDispositivos;
    	}

    	/**
    	 * This method initializes bSalir	
    	 * 	
    	 * @return javax.swing.JButton	
    	 */
    	private JButton getBSalir() {
    		if (bSalir == null) {
    			bSalir = new JButton();
    			
    			if(tipoUsuario=='a'){
    				
    				bSalir.setBounds(new Rectangle(761, 33, 63, 58));
    				
    			}else{
    				
    				
    				bSalir.setBounds(new Rectangle(658, 33, 63, 58));
    				
    			}
    			
    			bSalir.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/Salir.png")); //$NON-NLS-1$
    			bSalir.setToolTipText(Idioma.getString("etExit")); //$NON-NLS-1$
    			bSalir.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					
    					dialog_salir();
    				}
    			});
    		}
    		return bSalir;
    	}

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    	
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

     public void dialog_salir(){
    	 Object [] opt = {Idioma.getString("etYes"),Idioma.getString("etNo")}; //$NON-NLS-1$ //$NON-NLS-2$
         int n = JOptionPane.showOptionDialog(this, Idioma.getString("msgExit"), Idioma.getString("msgEndSession"), JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, opt, opt[0] ); //$NON-NLS-1$ //$NON-NLS-2$
         if (n == JOptionPane.YES_OPTION){
               
                 this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                 this.dispose();
                 tabsPaneles.removeAll();
                 LoginVisual l = new LoginVisual(); // Si dice que si, volvemos al Login del Programa
                 l.setVisible(true);                                             
         }else{
               
                 this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // Si dice que no no hacemos nada
         }
    }

	/**
	 * This method initializes tabsPaneles	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabsPaneles() {
		if (tabsPaneles == null) {
			tabsPaneles = new JTabbedPane();
			tabsPaneles.setBounds(new Rectangle(30, 139, 805, 444));
		}
		return tabsPaneles;
	}

	/**
	 * This method initializes bCerrarPesta�a	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBCerrarPesta�a() {
		if (bCerrarPesta�a == null) {
			bCerrarPesta�a = new JButton();
			bCerrarPesta�a.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/cerrar.png"))); //$NON-NLS-1$
			bCerrarPesta�a.setBounds(new Rectangle(835, 193, 29, 28));
			bCerrarPesta�a.setToolTipText(Idioma.getString("etActualTab")); //$NON-NLS-1$
			bCerrarPesta�a.setVisible(false);
			bCerrarPesta�a.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					int pesta�aActual = tabsPaneles.getSelectedIndex();
					
					tabsPaneles.removeTabAt(pesta�aActual);
					
					/*Una vez ha cerrado la seleccionada contamos el n�mero de pesta�as que hay. 
					 * En caso de que no queden pesta�as. Ocultamos el bot�n.
					 */
					int numPesta�as = tabsPaneles.getTabCount();
					
					if (numPesta�as == 0){
				
						bCerrarPesta�a.setVisible(false);
						
				
					}
					
					
				}
			});
		}
		return bCerrarPesta�a;
	}


	public static void a�adirPesta�aNueva(String titulo,JPanel panel){ // Este m�todo a�ade una pesta�a nueva. 
		
		int numPesta�as = tabsPaneles.getTabCount();
		
		if (numPesta�as <10) {
		
			tabsPaneles.addTab(titulo,panel);
			tabsPaneles.setSelectedIndex(numPesta�as);
			
			
		}else{
			
			JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
			
		}
		
		
		
		
	}

	/**
	 * This method initializes bIdiomaCatalan	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBIdiomaCatalan() {
		if (bIdiomaCatalan == null) {
			bIdiomaCatalan = new JButton();
			bIdiomaCatalan.setBounds(new Rectangle(806, 7, 18, 11));
			bIdiomaCatalan.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/catalan.png"))); //$NON-NLS-1$
			bIdiomaCatalan.setToolTipText("Catal�/Valenci�"); //$NON-NLS-1$
			bIdiomaCatalan.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					
					int resp = JOptionPane.showConfirmDialog(null,"El seu idioma per defecte ser� catal�. Voldria canviar-ho?","",JOptionPane.YES_NO_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
					
					if(resp == 1){
						
					}else{
					
						String sentencia = "UPDATE `dai2opengis`.`usuario` SET idioma='catalan' WHERE dni='"+ dniUsuario +"'"; //$NON-NLS-1$ //$NON-NLS-2$
						
						try {
							
							ConectarDBA.acceder();
							ConectarDBA.modificar(sentencia);
							ConectarDBA.cerrarCon();
							
							JOptionPane.showMessageDialog(null, "El seu idioma ha canviat a catal�"); //$NON-NLS-1$
							tabsPaneles.removeAll();
							System.exit(0);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
					
				
					}
				
					
					
				}
			});
		}
		return bIdiomaCatalan;
	}

	/**
	 * This method initializes bIdiomaSpanish	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBIdiomaSpanish() {
		if (bIdiomaSpanish == null) {
			bIdiomaSpanish = new JButton();
			bIdiomaSpanish.setBounds(new Rectangle(775, 7, 18, 11));
			bIdiomaSpanish.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/Spanish.png"))); //$NON-NLS-1$
			bIdiomaSpanish.setToolTipText("Espa�ol"); //$NON-NLS-1$
			bIdiomaSpanish.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					

					
					int resp = JOptionPane.showConfirmDialog(null,"Su idioma predeterminado ser� espa�ol.�Desea cambiarlo?","",JOptionPane.YES_NO_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
					
					if(resp == 1){
						
					}else{
					
						String sentencia = "UPDATE `dai2opengis`.`usuario` SET idioma='espa�ol' WHERE dni='"+ dniUsuario +"'"; //$NON-NLS-1$ //$NON-NLS-2$
						
						try {
							
							ConectarDBA.acceder();
							ConectarDBA.modificar(sentencia);
							ConectarDBA.cerrarCon();
							
							JOptionPane.showMessageDialog(null, "Su idioma ha cambiado a Espa�ol"); //$NON-NLS-1$
							tabsPaneles.removeAll();
							System.exit(0);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					
					}
					
				}
			});
		}
		return bIdiomaSpanish;
	}

	/**
	 * This method initializes bIdiomaEnglish	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBIdiomaEnglish() {
		if (bIdiomaEnglish == null) {
			bIdiomaEnglish = new JButton();
			bIdiomaEnglish.setBounds(new Rectangle(836, 7, 18, 11));
			bIdiomaEnglish.setToolTipText("English"); //$NON-NLS-1$
			bIdiomaEnglish.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/english.png"))); //$NON-NLS-1$
			bIdiomaEnglish.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					int resp = JOptionPane.showConfirmDialog(null,"Your default language will be English. Do you want to change it?","",JOptionPane.YES_NO_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
					
					if(resp == 1){
						
					}else{
					
						String sentencia = "UPDATE `dai2opengis`.`usuario` SET idioma='english' WHERE dni='"+ dniUsuario +"'"; //$NON-NLS-1$ //$NON-NLS-2$
						
						try {
							
							ConectarDBA.acceder();
							ConectarDBA.modificar(sentencia);
							ConectarDBA.cerrarCon();
							
							JOptionPane.showMessageDialog(null, "Your language has been changed to English"); //$NON-NLS-1$
							tabsPaneles.removeAll();
							System.exit(0);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
			});
		}
		return bIdiomaEnglish;
	}


 }  //  @jve:decl-index=0:visual-constraint="10,10"