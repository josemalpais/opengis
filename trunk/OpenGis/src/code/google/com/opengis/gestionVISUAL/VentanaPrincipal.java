/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionVISUAL;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



/**
* @author knals & Juan Carlos Garc�a
* Clase que genera la ventana principal del programa. Se generar� completamente maximizada y ser� un formulario MDI.
* 
* Ultima actualizaci�n 16/11/11
*
*/
public class VentanaPrincipal extends JFrame{
   
	
	private String tipo;
	private JPanel panelMDI;
	private JPanel panelFormularios;
	private AperoVisual frmAperos;
	private UsuarioVisual frmUsuarios;

	private JButton cmdPrestamos;
	private JButton cmdUsuarios;
	private JButton cmdInformes;
	private JButton cmdParcelas;
	private JButton cmdAperos;
	private JButton cmdProductos;
	private JButton cmdTareas;
	private JButton cmdDispositivos;
	
	private Dimension dimension = new Dimension(100,25);
	
   
   /**
    * Constructor de la ventana con par�metros.
    * @param h: Height/Altura: Elegiremos la cantidad de pixels de alto que tendr� la ventana.
    * @param w: Widht/Anchura: Elegiremos la cantidad de pixels de ancho que tendr� la ventana.
    * @param titulo: Elegiremos el titulo que aparecera en la parte superior de la ventana.
    */
   public VentanaPrincipal(char tipoDeUsuario){
       this.setTitle("OpenGis - Aplicaci�n de Gesti�n");
       this.tipo = tipoDeUsuario+"";
       
       if (tipo.equals("a")){
    	   
    	   configVentanaAdmin();
    	   
       }else{
       
       
	       if (tipo.equals("t")){
	    	   
	    	   configVentanaTrabajador();
	    	   
	       }else{
	    	   
	    	   if(tipo.equals("d")){
	    		   
	    		   
	    		   configVentanaDue�o();
	    		   
	    		   
	    	   }
	    	   
	    	   
	       }
       
       }
       
       cargarFormularios();
       
   }
   
   /**
    * M�todo que nos configura la ventana del Administrador. Vamos a configurarla como un formulario MDI
    */
   private void configVentanaAdmin(){
	   this.setVisible(true);
       this.setExtendedState(MAXIMIZED_BOTH); // Maximizada por completo
       this.setResizable(false); // No se puede redimensionar. Solo minimizar.
       
       
       panelMDI = new JPanel();
       panelMDI.setSize(1500,35);
       FlowLayout fl = new FlowLayout(); // Insertamos el Panel del MDI donde ir�n los botones
       panelMDI.setLayout(fl);
       this.add(panelMDI);
       
       panelFormularios = new JPanel();
       panelFormularios.setLayout(null);
       panelFormularios.setSize(getSize());
       panelFormularios.setLocation(100,100);
       this.add(panelFormularios);
       
       cmdPrestamos = new JButton("Prestamos");
       cmdPrestamos.addActionListener(new AccionDeBoton());
       cmdPrestamos.setSize(150, 25);

       
       cmdUsuarios = new JButton("Gestionar Usuarios");
       cmdUsuarios.addActionListener(new AccionDeBoton());
       cmdUsuarios.setSize(dimension);

       
       cmdInformes = new JButton("Generar Informes");
       cmdInformes.addActionListener(new AccionDeBoton());
       cmdInformes.setSize(dimension);
       
       cmdParcelas = new JButton("Gestionar Parcelas");
       cmdParcelas.addActionListener(new AccionDeBoton());
       cmdParcelas.setSize(dimension);
       
       cmdAperos = new JButton("Gestionar Aperos");
       cmdAperos.addActionListener(new AccionDeBoton());
       cmdAperos.setSize(dimension);
       
       cmdProductos = new JButton("Gestionar Productos");
       cmdProductos.addActionListener(new AccionDeBoton());
       cmdProductos.setSize(dimension);
       
       cmdTareas = new JButton("Gestionar Tareas");
       cmdTareas.addActionListener(new AccionDeBoton());
       cmdTareas.setSize(dimension);
       
       cmdDispositivos = new JButton("Gestionar Dispositivos");
       cmdDispositivos.addActionListener(new AccionDeBoton());
       cmdDispositivos.setSize(dimension);
       
       panelMDI.add(cmdPrestamos);
       panelMDI.add(cmdUsuarios);
       panelMDI.add(cmdInformes);
       panelMDI.add(cmdParcelas);
       panelMDI.add(cmdAperos);
       panelMDI.add(cmdProductos);
       panelMDI.add(cmdTareas);
       panelMDI.add(cmdDispositivos);
      
       
       
     this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     
    //Pregunta si quiere cerrar la sesi�n al darle a la "X"	       
    this.addWindowListener(new WindowAdapter(){
       	public void windowClosing(WindowEvent e) {       		
       		dialog_salir();      	 
       	}
       });
       }
   
   
   
   /**
    * M�todo que nos configura la ventana del Trabajador. Vamos a configurarla como un formulario MDI
    */
   private void configVentanaTrabajador(){
	   this.setVisible(true);
       this.setExtendedState(MAXIMIZED_BOTH); // Maximizada por completo
       this.setResizable(false); // No se puede redimensionar. Solo minimizar.
       
       panelMDI = new JPanel();
       panelMDI.setLayout(null);
       this.add(panelMDI);
   
       
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
       //Pregunta si quiere cerrar la sesi�n al darle a la "X"	       
       this.addWindowListener(new WindowAdapter(){
          	public void windowClosing(WindowEvent e) {       		
          		dialog_salir();      	 
          	}
          });
  }
   
   
   
   /**
    * M�todo que nos configura la ventana del Trabajador. Vamos a configurarla como un formulario MDI
    */
   private void configVentanaDue�o(){
	   this.setVisible(true);
       this.setExtendedState(MAXIMIZED_BOTH); // Maximizada por completo
       this.setResizable(false); // No se puede redimensionar. Solo minimizar.
       
       panelMDI = new JPanel();
       panelMDI.setLayout(null);
       this.add(panelMDI);
   
       
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
       //Pregunta si quiere cerrar la sesi�n al darle a la "X"	       
       this.addWindowListener(new WindowAdapter(){
          	public void windowClosing(WindowEvent e) {       		
          		dialog_salir();      	 
          	}
          });
  }
   

   public void dialog_salir(){
		int n = JOptionPane.showConfirmDialog(this, "Esto cerrar� la sesi�n. �Est� usted seguro?", "Cerrar sesi�n", JOptionPane.YES_NO_OPTION);
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
    * Este m�todo se encarga de cargar todos los componentes de la aplicaci�n. Dependiendo de lo que el usuario 
    * seleccione posteriormente, se mostrar� un contenido u otro.
    * 
    */
   
   public void cargarFormularios(){
	   
	   frmUsuarios = new UsuarioVisual(panelFormularios.getWidth() ,panelFormularios.getHeight()); // Tama�o completo del Formulario
	   panelFormularios.setLocation(100,100);
	   panelFormularios.add(frmUsuarios);
	   
	   frmAperos = new AperoVisual();
	   panelFormularios.add(frmAperos);
	   
   }

   /**
    * 
    * @author Juan Carlos Garc�a
    *
    */
   
   public class AccionDeBoton implements ActionListener {
	   
	   public void actionPerformed(ActionEvent e) {
		   
		  String comando =  e.getActionCommand(); // Recogemos el valor de el bot�n pulsado
		  
		  if(comando.equals("Gestionar Usuarios")){
			  
			  frmUsuarios.setVisible(true);
			  
		  }
		  
		  
		  
		  
		   
		   
	   }
	 }
   
}
   
   
