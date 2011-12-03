 /*****************************************************************************
    * Copyright (c) 2011 [OpenGisTeam]                                           *
    ******************************************************************************/
    package code.google.com.opengis.gestionVISUAL;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;


    public class VentanaPrincipal extends JFrame implements WindowListener {

    	private static final long serialVersionUID = 1L;
    	private JPanel jContentPane = null;
    	private JButton bPrestamos = null;
    	private JButton bUsuarios = null;
    	private JButton bParcelas = null;
    	private JButton bProductos = null;
    	private JButton bAperos = null;
    	private JButton bInformes = null;
    	private JButton bTareas = null;
    	private JButton bDispositivos = null;
    	private JButton bSalir = null;
		private JLabel lblPrestamos = null;
		private JLabel lblUsuarios = null;
		private JLabel lblInformes = null;
		private JLabel lblParcelas = null;
		private JLabel lblApero = null;
		private JLabel lblProductos = null;
		private JLabel lblTareas = null;
		private JLabel lblDispositivos = null;
		private JLabel lblSalir = null;
		private JTabbedPane tabsPaneles = null;
		
		private char tipoUsuario;

    	
    	/**
    	 * Constructor de la VentanaPrincipal. Dependiendo del tipo de usuario cargará
    	 * unos botones u otros.
    	 */
    	public VentanaPrincipal(char tipoDeUsuario){
    		super();
		initialize();
    		
    		/*
    		 * Dependiendo del tipo de usuario que sea cargaremos unas funciones u otras
    		 * 
    		 */
    		
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
        this.setBounds(new Rectangle(0, 0, 888, 616));
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
    		this.setTitle("Aplicación de Gestión - OPENGis");
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
    			lblSalir.setBounds(new Rectangle(800, 87, 29, 22));
    			lblSalir.setText("Salir");
    			lblDispositivos = new JLabel();
    			lblDispositivos.setBounds(new Rectangle(686, 87, 69, 22));
    			lblDispositivos.setText("Dispositivos");
    			lblTareas = new JLabel();
    			lblTareas.setBounds(new Rectangle(603, 87, 46, 22));
    			lblTareas.setText("Tareas");
    			lblProductos = new JLabel();
    			lblProductos.setBounds(new Rectangle(501, 87, 63, 22));
    			lblProductos.setText("Productos");
    			lblApero = new JLabel();
    			lblApero.setBounds(new Rectangle(418, 87, 41, 22));
    			lblApero.setText("Apero");
    			lblParcelas = new JLabel();
    			lblParcelas.setBounds(new Rectangle(317, 87, 54, 22));
    			lblParcelas.setText("Parcelas");
    			lblInformes = new JLabel();
    			lblInformes.setBounds(new Rectangle(223, 87, 54, 22));
    			lblInformes.setText("Informes");
    			lblUsuarios = new JLabel();
    			lblUsuarios.setBounds(new Rectangle(129, 87, 54, 22));
    			lblUsuarios.setText("Usuarios");
    			lblPrestamos = new JLabel();
    			lblPrestamos.setBounds(new Rectangle(31, 87, 63, 22));
    			lblPrestamos.setText("Prestamos");
    			jContentPane = new JPanel();
    			jContentPane.setLayout(null);
    			jContentPane.add(getBPrestamos(), null);
    			jContentPane.add(getBUsuarios(), null);
    			jContentPane.add(getBPacerlas(), null);
    			jContentPane.add(getBProductos(), null);
    			jContentPane.add(getBAperos(), null);
    			jContentPane.add(getBInformes(), null);
    			jContentPane.add(getBTareas(), null);
    			jContentPane.add(getBDispositivos(), null);
    			jContentPane.add(getBSalir(), null);
    			jContentPane.add(lblPrestamos, null);
    			jContentPane.add(lblUsuarios, null);
    			jContentPane.add(lblInformes, null);
    			jContentPane.add(lblParcelas, null);
    			jContentPane.add(lblApero, null);
    			jContentPane.add(lblProductos, null);
    			jContentPane.add(lblTareas, null);
    			jContentPane.add(lblDispositivos, null);
    			jContentPane.add(lblSalir, null);
    			jContentPane.add(getTabsPaneles(), null);
    			
    			
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
    			bPrestamos.setBounds(new Rectangle(31, 33, 63, 58));
    			bPrestamos.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/alquiler.png"));
    			bPrestamos.setToolTipText("Prestamos");
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
    			bUsuarios.setBounds(new Rectangle(125, 33,63,58));
    			bUsuarios.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/usuario.png"));
    			bUsuarios.setToolTipText("Gestión de Usuarios");
    			bUsuarios.addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent e) {
    					
    					if (tipoUsuario == 'a'){
    						
    						int numPestañas = tabsPaneles.getTabCount();
    						
    						if (numPestañas <10) {
							
    							GeneradorPanelPrincipal panelNuevo = new GeneradorPanelPrincipal();
    						
    							tabsPaneles.addTab("Gestión de Usuarios",panelNuevo);
    							
    						}else{
    							
    							JOptionPane.showMessageDialog(null, "No puede abrir más pestañas. Cierre las anteriores para continuar abriendo nuevas pestañas");
    							
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
    			bParcelas.setBounds(new Rectangle(313, 33, 63, 58));
    			bParcelas.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/parcela.png"));
    			bParcelas.setToolTipText("Gestión de Parcelas");
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
    			bProductos.setBounds(new Rectangle(501, 33, 63, 58));
    			bProductos.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/producto.png"));
    			bProductos.setToolTipText("Gestión de Productos");
    		
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
    			bAperos.setBounds(new Rectangle(407, 33, 63, 58));
    			bAperos.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/apero.png"));
    			bAperos.setToolTipText("Gestión de Aperos");
    		
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
    			bInformes.setBounds(new Rectangle(219, 33, 63, 58));
    			bInformes.setIcon(new ImageIcon ("OpenGis/src/recursosVisuales/informes.png"));
    			bInformes.setToolTipText("Generar Informes");
    		}
    		return bInformes;
    	}

    	/**
    	 * This method initializes bTareas	
    	 * 	
    	 * @return javax.swing.JButton	
    	 */
    	private JButton getBTareas() {
    		if (bTareas == null) {
    			bTareas = new JButton();
    			bTareas.setBounds(new Rectangle(595, 33, 63, 58));
    			bTareas.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/Tarea.png"));
    			bTareas.setToolTipText("Administrar Tareas");
    		}
    		return bTareas;
    	}

    	/**
    	 * This method initializes bDispositivos	
    	 * 	
    	 * @return javax.swing.JButton	
    	 */
    	private JButton getBDispositivos() {
    		if (bDispositivos == null) {
    			bDispositivos = new JButton();
    			bDispositivos.setBounds(new Rectangle(689, 33, 63, 58));
    			bDispositivos.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/dispositivo.png"));
    			bDispositivos.setToolTipText("Gestión de Dispositivos");
    		
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
    			bSalir.setBounds(new Rectangle(783, 33, 63, 58));
    			bSalir.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/Salir.png"));
    			bSalir.setToolTipText("Salir");
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
    	 
         int n = JOptionPane.showConfirmDialog(this, "Esto cerrará la sesión. ¿Está usted seguro?", "Cerrar sesión", JOptionPane.YES_NO_OPTION);
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
			tabsPaneles.setBounds(new Rectangle(31, 125, 811, 444));
		}
		return tabsPaneles;
	}





    	
    	
}  //  @jve:decl-index=0:visual-constraint="10,10"