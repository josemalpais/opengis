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
import javax.swing.JPanel;



/**
* @author knals & Juan Carlos García
* Clase que genera la ventana principal del programa. Se generará completamente maximizada y será un formulario MDI.
* 
* Ultima actualización 16/11/11
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
    * Constructor de la ventana sin parámetros, por defecto nos creará una ventana con nombre OpenGis. Configura la ventana
    * para que no se pueda redimensionar y maximizada por defecto. Además carga todos los formularios
    * que se van a utilzar, y los va mostrando dependiendo de lo que el usuario utilice.
    */
   public VentanaPrincipal(){
       this.setTitle("OpenGis");
       configVentana();
       cargarFormularios(); 
   }
   
   /**
    * Constructor de la ventana con parámetros.
    * @param h: Height/Altura: Elegiremos la cantidad de pixels de alto que tendrá la ventana.
    * @param w: Widht/Anchura: Elegiremos la cantidad de pixels de ancho que tendrá la ventana.
    * @param titulo: Elegiremos el titulo que aparecera en la parte superior de la ventana.
    */
   public VentanaPrincipal(int w,int h,String titulo){
       this.setSize(w,h);
       this.setTitle(titulo);
       configVentana();
       cargarFormularios();
   }
   
   /**
    * Método que nos configura la ventana. Vamos a configurarla como un formulario MDI
    */
   private void configVentana(){
       this.setVisible(true);
       this.setExtendedState(MAXIMIZED_BOTH); // Maximizada por completo
       this.setResizable(false); // No se puede redimensionar. Solo minimizar.
       
       panelMDI = new JDesktopPane();
       panelMDI.setLayout(null);
       insets = panelMDI.getInsets();
       this.add(panelMDI);
       
       /// Añadimos los Menús Superiores Ej: Archivo, Editar, Usuarios, etc...
       
       menuBar = new JMenuBar(); // Barra de Menú principal
       
       menuArchivo = new JMenu("Archivo");
       menuBar.add(menuArchivo);
       menuObjeto = new JMenuItem("Cerrar Sesión");
       menuObjeto.addActionListener(new AccionesDeBoton());
       menuArchivo.add(menuObjeto);
       menuObjeto = new JMenuItem("Salir...");
       menuObjeto.addActionListener(new AccionesDeBoton());
       menuArchivo.add(menuObjeto);
       
       
       menuPrestamos = new JMenu("Prestamos");
       menuObjeto = new JMenuItem("Realizar Prestamo");
       menuObjeto.addActionListener(new AccionesDeBoton());
       menuPrestamos.add(menuObjeto);
       menuObjeto = new JMenuItem ("Devolución");
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

       this.addWindowListener(new WindowAdapter(){
       	@Override
       	public void windowClosing(WindowEvent e) {
     /**
      * dispose();
      * Insertar aqui el Login Visual para que nos mande allí cuando le hagamos clic en el boton cerrar del JFrame
      */     		
       	}
       });
   }
   
   /**
    * Este método se encarga de cargar todos los componentes de la aplicación. Dependiendo de lo que el usuario 
    * seleccione posteriormente, se mostrará un contenido u otro.
    * 
    */
   
   public void cargarFormularios(){
	   
	   frmUsuarios = new UsuarioVisual(panelMDI.getWidth() ,panelMDI.getHeight()); // Tamaño completo del Formulario
	   panelMDI.add(frmUsuarios);
	   //frmUsuarios.setVisible(true); // Este se tiene que quitar, es solo para ver si aparece

	   
   }

   /**
    * 
    * @author Juan Carlos García
    *
    */
   
   class AccionesDeBoton implements ActionListener {
	   
	   public void actionPerformed(ActionEvent e) {
		   
		   JMenuItem M = (JMenuItem)e.getSource(); // Recogemos el item desde el cual ha sido llamado
		   
		   String texto = M.getText();
		   
		   	
		   if(texto.equals("Gestionar Usuarios")){
			   
			   frmUsuarios.setVisible(true);
			   
		   }
		   
		   if(texto.equals("Salir...")){
			   
			   VentanaPrincipal.this.dispose();
			   
		   }
		   
			   
		   
		   
	   }
	 }
   
}
   
   
