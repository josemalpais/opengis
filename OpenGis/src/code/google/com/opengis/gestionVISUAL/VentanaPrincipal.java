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
    	/**
    	 * This is the default constructor
    	 */
    	public VentanaPrincipal(char tipoDeUsuario){
    		super();
    		initialize();
            
           
            
        }

    	/**
    	 * This method initializes this
    	 * 
    	 * @return void
    	 */
    	private void initialize() {
    		this.setPreferredSize(new Dimension(800, 600));
    		this.setBounds(new Rectangle(0, 0, 888, 619));
    		this.setResizable(false);
    		this.setContentPane(getJContentPane());
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
    	 * This method initializes jContentPane
    	 * 
    	 * @return javax.swing.JPanel
    	 */
    	private JPanel getJContentPane() {
    		if (jContentPane == null) {
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
    			bUsuarios.setIcon(new ImageIcon("usuario.png"));
    			bUsuarios.setToolTipText("Gestión de Usuarios");
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
    			bParcelas.setIcon(new ImageIcon("parcela.png"));
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
    			bInformes.setIcon(new ImageIcon ("informes.png"));
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
    			bSalir.setIcon(new ImageIcon("Salir.png"));
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





    	
    	
    }  //  @jve:decl-index=0:visual-constraint="10,10"