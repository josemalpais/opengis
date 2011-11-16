/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionVISUAL;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
   
	
	private JDesktopPane panelMDI;
	private Insets insets;
	private JMenuBar menuBar;
	private JMenu menuArchivo;
	private JMenuItem menuObjeto;
	private JMenu menuUsuario;
	private JMenu menuPrestamos;
	private JMenu menuInformes;
	private JMenu menuParcelas;
	private JMenu menuAperos;
	private JMenu menuProductos;
	private JMenu menuTareas;
	private JMenu menuDispositivos;
	
	private UsuarioVisual frmUsuarios;

	
	
	
   /**
    * Constructor de la ventana sin par�metros, por defecto nos crear� una ventana con nombre OpenGis. Configura la ventana
    * para que no se pueda redimensionar y maximizada por defecto. Adem�s carga todos los formularios
    * que se van a utilzar, y los va mostrando dependiendo de lo que el usuario utilice.
    */
   public VentanaPrincipal(){
       this.setTitle("OpenGis");
       configVentana();
       cargarFormularios();
   }
   
   /**
    * Constructor de la ventana con par�metros.
    * @param h: Height/Altura: Elegiremos la cantidad de pixels de alto que tendr� la ventana.
    * @param w: Widht/Anchura: Elegiremos la cantidad de pixels de ancho que tendr� la ventana.
    * @param titulo: Elegiremos el titulo que aparecera en la parte superior de la ventana.
    */
   public VentanaPrincipal(int w,int h,String titulo){
	   this.setSize(w,h);
       this.setTitle(titulo);
       configVentana();
       cargarFormularios();
   }
   
   /**
    * M�todo que nos configura la ventana. Vamos a configurarla como un formulario MDI
    */
   private void configVentana(){
	   this.setVisible(true);
       this.setExtendedState(MAXIMIZED_BOTH); // Maximizada por completo
       this.setResizable(false); // No se puede redimensionar. Solo minimizar.
       
       panelMDI = new JDesktopPane();
       panelMDI.setLayout(null);
       insets = panelMDI.getInsets();
       this.add(panelMDI);
       
       /// A�adimos los Men�s Superiores Ej: Archivo, Editar, Usuarios, etc...
       
       menuBar = new JMenuBar(); // Barra de Men� principal
       
       menuArchivo = new JMenu("Archivo");
       menuBar.add(menuArchivo);
       menuObjeto = new JMenuItem("Cerrar Sesi�n");
       menuObjeto.addActionListener(new AccionesDeBoton());
       menuArchivo.add(menuObjeto);
       menuObjeto = new JMenuItem("Salir...");
       menuObjeto.addActionListener(new AccionesDeBoton());
       menuArchivo.add(menuObjeto);
       
       
       menuPrestamos = new JMenu("Prestamos");
       menuObjeto = new JMenuItem("Realizar Prestamo");
       menuObjeto.addActionListener(new AccionesDeBoton());
       menuPrestamos.add(menuObjeto);
       menuObjeto = new JMenuItem ("Devoluci�n");
       menuObjeto.addActionListener(new AccionesDeBoton ());
       menuPrestamos.add(menuObjeto);
       menuBar.add(menuPrestamos);
       
       
       
       menuUsuario = new JMenu("Usuarios");
       menuObjeto = new JMenuItem("Gestionar Usuarios");
       menuObjeto.addActionListener(new AccionesDeBoton());
       menuUsuario.add(menuObjeto);
       menuBar.add(menuUsuario);
       
      
       this.setJMenuBar(menuBar); // Mostramos el JMenuBar en nuestro Formulario MDI
       
       
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
			this.dispose();
		}else{
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
   }
   
   
   
   /**
    * Este m�todo se encarga de cargar todos los componentes de la aplicaci�n. Dependiendo de lo que el usuario 
    * seleccione posteriormente, se mostrar� un contenido u otro.
    * 
    */
   
   public void cargarFormularios(){
	   
	   frmUsuarios = new UsuarioVisual(panelMDI.getWidth() ,panelMDI.getHeight()); // Tama�o completo del Formulario
	   panelMDI.add(frmUsuarios);
	   
   }

   /**
    * 
    * @author Juan Carlos Garc�a
    *
    */
   
   class AccionesDeBoton implements ActionListener {
	   
	   public void actionPerformed(ActionEvent e) {
		   
		   JMenuItem M = (JMenuItem)e.getSource(); // Recogemos el item desde el cual ha sido llamado
		   
		   String texto = M.getText();
		   	
		   
		   if(texto.equals("Gestionar Usuarios")){
			   
			   frmUsuarios.dispose();
			   
			   cargarFormularios(); // Cargamos los formularios disponibles.
			   
			   frmUsuarios.setVisible(true);
			   
		   }
		   
		   
		   
		   if(texto.equals("Salir...")){
			   
			   VentanaPrincipal.this.dispose();
			   
		   }
		   
			   
		   
		   
	   }
	 }
   
}
   
   
