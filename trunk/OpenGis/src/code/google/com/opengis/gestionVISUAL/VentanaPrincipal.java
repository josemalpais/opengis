		 /*****************************************************************************
		    * Copyright (c) 2011 [OpenGisTeam]                                           *
		    ******************************************************************************/
		package code.google.com.opengis.gestionVISUAL;
		
		
		
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
		private JButton bCerrarPestaña = null;
		private JButton bIdiomaCatalan = null;
		private JButton bIdiomaSpanish = null;
		private JButton bIdiomaEnglish = null;

    	
    	/**
    	 * Constructor de la VentanaPrincipal. Dependiendo del tipo de usuario cargará
    	 * unos botones u otros.
    	 */
    	public VentanaPrincipal(char tipoDeUsuario, String dni,String idiomaDefecto){
    		super();
    		new Idioma(idiomaDefecto);
    		initialize();
		
    		
    		/*
    		 * Dependiendo del tipo de usuario que sea cargaremos unas funciones u otras
    		 * 
    		 */
    		this.dniUsuario = dni;
    		
    		if (tipoDeUsuario == 'a'){ 
    			
    			this.tipoUsuario = 'a';
    			cargarAdministrador();
    			
    		}
    		
            
           
            
        }

    	/**
		 * This method initializes this
		 * 
		 */
		private void initialize() {
        this.setBounds(new Rectangle(0, 0, 888, 624));
        this.setContentPane(getJContentPaneAdministrador());
				
		}

		/**
    	 * Este método incializa la interfaz gráfica en modo administrador
    	 * 
    	 * @return void
    	 */
    	private void cargarAdministrador() {
    		this.setPreferredSize(new Dimension(800, 600));
    		this.setBounds(new Rectangle(0, 0, 888, 619));
    		this.setResizable(false);
    		this.setContentPane(getJContentPaneAdministrador());
    		this.setTitle(Idioma.getString("etMainWindow")); //$NON-NLS-1$
    		this.setLocationRelativeTo(null); // Centramos el formulario
    		this.setVisible(true);
    		
    		this.addWindowListener(new WindowAdapter(){ // Añadimos el Listener
                public void windowClosing(WindowEvent e) {              
                    dialog_salir();          
                }
            });
    		
    	}

    	/**
    	 * Este método inicializa el JContentPane del Administrador
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
    			jContentPane.add(getBCerrarPestaña(), null);
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
    					
    					
    				}
    			});
    			
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
    			bUsuarios.setToolTipText(Idioma.getString("etUsersMng")); //$NON-NLS-1$
    			bUsuarios.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					
    					bCerrarPestaña.setVisible(true);
    					
    					if (tipoUsuario == 'a'){
    						
    						int numPestañas = tabsPaneles.getTabCount();
    						
    						if (numPestañas <10) {
							
    							UsuariosPanelPrincipal panelNuevo = new UsuariosPanelPrincipal();
    						
    							tabsPaneles.addTab(Idioma.getString("etUsersMng"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPestañas);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
    						}
    							
    							
    					}else{
    						
    						// INSERTAR AQUI METODO PARA TRABAJADOR
    						
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
    					
    					bCerrarPestaña.setVisible(true);

    						System.out.println("aaaaaaaa"); //$NON-NLS-1$
    						int numPestañas = tabsPaneles.getTabCount();
    						
    						if (numPestañas <10) {
							
    							ParcelasPanelPrincipal panelNuevo = new ParcelasPanelPrincipal();
    						
    							tabsPaneles.addTab(Idioma.getString("etLotsMng"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPestañas);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
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
    					
    					bCerrarPestaña.setVisible(true);
    					
    					if (tipoUsuario == 'a'){
    						
    						int numPestañas = tabsPaneles.getTabCount();
    						
    						if (numPestañas <10) {
							
    							ProductoPanelPrincipal panelNuevo = new ProductoPanelPrincipal();
    						
    							tabsPaneles.addTab("Gestión de Productos",panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPestañas);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
    						}
    							
    							
    					}else{
    						
    						// INSERTAR AQUI METODO PARA TRABAJADOR
    						
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
    					bCerrarPestaña.setVisible(true);

    						
    						int numPestañas = tabsPaneles.getTabCount();
    						
    						if (numPestañas <10) {
							
    							AperosPanelPrincipal panelNuevo = new AperosPanelPrincipal();
    						
    							tabsPaneles.addTab(Idioma.getString("etImplementsMng"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPestañas);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
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
    					
    					bCerrarPestaña.setVisible(true);

    						
    						int numPestañas = tabsPaneles.getTabCount();
    						
    						if (numPestañas <10) {
							
    							DispositivosPanelPrincipal panelNuevo = new DispositivosPanelPrincipal();
    						
    							tabsPaneles.addTab(Idioma.getString("etDevicesMng"),panelNuevo); //$NON-NLS-1$
    							tabsPaneles.setSelectedIndex(numPestañas);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, Idioma.getString("msgManyTabs")); //$NON-NLS-1$
    							
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
    			bSalir.setBounds(new Rectangle(761, 33, 63, 58));
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
	 * This method initializes bCerrarPestaña	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBCerrarPestaña() {
		if (bCerrarPestaña == null) {
			bCerrarPestaña = new JButton();
			bCerrarPestaña.setIcon(new ImageIcon(getClass().getResource("/recursosVisuales/cerrar.png"))); //$NON-NLS-1$
			bCerrarPestaña.setBounds(new Rectangle(835, 193, 29, 28));
			bCerrarPestaña.setToolTipText(Idioma.getString("etActualTab")); //$NON-NLS-1$
			bCerrarPestaña.setVisible(false);
			bCerrarPestaña.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					int pestañaActual = tabsPaneles.getSelectedIndex();
					
					tabsPaneles.removeTabAt(pestañaActual);
					
					/*Una vez ha cerrado la seleccionada contamos el número de pestañas que hay. 
					 * En caso de que no queden pestañas. Ocultamos el botón.
					 */
					int numPestañas = tabsPaneles.getTabCount();
					
					if (numPestañas == 0){
				
						bCerrarPestaña.setVisible(false);
						
				
					}
					
					
				}
			});
		}
		return bCerrarPestaña;
	}


	public static void añadirPestañaNueva(String titulo,JPanel panel){ // Este método añade una pestaña nueva. 
		
		int numPestañas = tabsPaneles.getTabCount();
		
		if (numPestañas <10) {
		
			tabsPaneles.addTab(titulo,panel);
			tabsPaneles.setSelectedIndex(numPestañas);
			
			
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
			bIdiomaCatalan.setToolTipText("Catatlà/Valencià");
			bIdiomaCatalan.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					
					int resp = JOptionPane.showConfirmDialog(null,"El seu idioma per defecte serà català. Voldria canviar-ho?","",JOptionPane.YES_NO_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
					
					if(resp == 1){
						
					}else{
					
						String sentencia = "UPDATE `dai2opengis`.`usuario` SET idioma='catalan' WHERE dni='"+ dniUsuario +"'"; //$NON-NLS-1$ //$NON-NLS-2$
						
						try {
							
							ConectarDBA.acceder();
							ConectarDBA.modificar(sentencia);
							ConectarDBA.cerrarCon();
							
							JOptionPane.showMessageDialog(null, "El seu idioma ha canviat a català"); //$NON-NLS-1$
							tabsPaneles.removeAll();
							VentanaPrincipal.this.dispose();;
							LoginVisual lv = new LoginVisual();
							
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
			bIdiomaSpanish.setToolTipText("Español");
			bIdiomaSpanish.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					

					
					int resp = JOptionPane.showConfirmDialog(null,"Su idioma predeterminado será español.¿Desea cambiarlo?","",JOptionPane.YES_NO_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
					
					if(resp == 1){
						
					}else{
					
						String sentencia = "UPDATE `dai2opengis`.`usuario` SET idioma='español' WHERE dni='"+ dniUsuario +"'"; //$NON-NLS-1$ //$NON-NLS-2$
						
						try {
							
							ConectarDBA.acceder();
							ConectarDBA.modificar(sentencia);
							ConectarDBA.cerrarCon();
							
							JOptionPane.showMessageDialog(null, "Su idioma ha cambiado a Español"); //$NON-NLS-1$
							tabsPaneles.removeAll();
							VentanaPrincipal.this.dispose();
							LoginVisual lv = new LoginVisual();
							
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
			bIdiomaEnglish.setToolTipText("English");
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
							VentanaPrincipal.this.dispose();
							LoginVisual lv = new LoginVisual();
							
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