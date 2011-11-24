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
     
    import javax.swing.BoxLayout;
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
    * @author knals & Juan Carlos García
    * Clase que genera la ventana principal del programa. Se generará completamente maximizada y será un formulario MDI.
    *
    * Ultima actualización 16/11/11
    *
    */
    public class VentanaPrincipal extends JFrame{
       
           
            private String tipo;
            private JPanel panelMDI;
            private JPanel panelFormularios;
            private AperoVisual frmAperos;
            private UsuarioVisual frmUsuarios;
            private ProductoVisual frmProductos;
     
            private JButton cmdPrestamos;
            private JButton cmdUsuarios;
            private JButton cmdInformes;
            private JButton cmdParcelas;
            private JButton cmdAperos;
            private JButton cmdProductos;
            private JButton cmdTareas;
            private JButton cmdDispositivos;
           
            private Dimension dimension = new Dimension(105,25);
           
       
       /**
        * Constructor de la ventana con parámetros.
        * @param h: Height/Altura: Elegiremos la cantidad de pixels de alto que tendrá la ventana.
        * @param w: Widht/Anchura: Elegiremos la cantidad de pixels de ancho que tendrá la ventana.
        * @param titulo: Elegiremos el titulo que aparecera en la parte superior de la ventana.
        */
       public VentanaPrincipal(char tipoDeUsuario){
           this.setTitle("OpenGis - Aplicación de Gestión");
           this.tipo = tipoDeUsuario+"";
           
           if (tipo.equals("a")){
               
               configVentanaAdmin();
               
           }else{
           
           
                   if (tipo.equals("t")){
                       
                       configVentanaTrabajador();
                       
                   }else{
                       
                       if(tipo.equals("d")){
                               
                               
                               configVentanaDueño();
                               
                               
                       }
                       
                       
                   }
           
           }
           
           cargarFormularios();
           
       }
       
       /**
        * Método que nos configura la ventana del Administrador. Vamos a configurarla como un formulario MDI
        */
       private void configVentanaAdmin(){
           this.setVisible(true);
           this.setExtendedState(MAXIMIZED_BOTH); // Maximizada por completo
           this.setResizable(false); // No se puede redimensionar. Solo minimizar.
         
           JPanel pComun = new JPanel();
           pComun.setLayout(new BoxLayout(pComun,BoxLayout.Y_AXIS));
         
         
           panelMDI = new JPanel();
           Dimension panelMDI_maxd = new Dimension(getWidth(),30);
           panelMDI.setMaximumSize(panelMDI_maxd);
         
         
           FlowLayout fl = new FlowLayout(); // Insertamos el Panel del MDI donde irán los botones
           panelMDI.setLayout(fl);
           pComun.add(panelMDI);
         
           panelFormularios = new JPanel();
           panelFormularios.setLayout(null);
           panelFormularios.setSize(getSize());
           panelFormularios.setLocation(100,100);
           pComun.add(panelFormularios);
         
           cmdPrestamos = new JButton("Prestamos");
           cmdPrestamos.addActionListener(new AccionDeBoton());
           cmdPrestamos.setPreferredSize(dimension);
     
         
           cmdUsuarios = new JButton("Usuarios");
           cmdUsuarios.addActionListener(new AccionDeBoton());
           cmdUsuarios.setPreferredSize(dimension);
     
         
           cmdInformes = new JButton("Informes");
           cmdInformes.addActionListener(new AccionDeBoton());
           cmdInformes.setPreferredSize(dimension);
         
           cmdParcelas = new JButton("Parcelas");
           cmdParcelas.addActionListener(new AccionDeBoton());
           cmdParcelas.setPreferredSize(dimension);
         
           cmdAperos = new JButton("Aperos");
           cmdAperos.addActionListener(new AccionDeBoton());
           cmdAperos.setPreferredSize(dimension);
         
           cmdProductos = new JButton("Productos");
           cmdProductos.addActionListener(new AccionDeBoton());
           cmdProductos.setPreferredSize(dimension);
         
           cmdTareas = new JButton("Tareas");
           cmdTareas.addActionListener(new AccionDeBoton());
           cmdTareas.setPreferredSize(dimension);
         
           cmdDispositivos = new JButton("Dispositivos");
           cmdDispositivos.addActionListener(new AccionDeBoton());
           cmdDispositivos.setPreferredSize(dimension);
         
           panelMDI.add(cmdPrestamos);
           panelMDI.add(cmdUsuarios);
           panelMDI.add(cmdInformes);
           panelMDI.add(cmdParcelas);
           panelMDI.add(cmdAperos);
           panelMDI.add(cmdProductos);
           panelMDI.add(cmdTareas);
           panelMDI.add(cmdDispositivos);
         
         
         this.add(pComun);
         
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
       
        //Pregunta si quiere cerrar la sesión al darle a la "X"          
        this.addWindowListener(new WindowAdapter(){
               public void windowClosing(WindowEvent e) {              
                   dialog_salir();          
               }
           });
           }
       
       
       
       /**
        * Método que nos configura la ventana del Trabajador. Vamos a configurarla como un formulario MDI
        */
       private void configVentanaTrabajador(){
               this.setVisible(true);
           this.setExtendedState(MAXIMIZED_BOTH); // Maximizada por completo
           this.setResizable(false); // No se puede redimensionar. Solo minimizar.
           
           panelMDI = new JPanel();
           panelMDI.setLayout(null);
           this.add(panelMDI);
       
           
           this.setDefaultCloseOperation(EXIT_ON_CLOSE);
           
           //Pregunta si quiere cerrar la sesión al darle a la "X"        
           this.addWindowListener(new WindowAdapter(){
                    public void windowClosing(WindowEvent e) {                     
                            dialog_salir();          
                    }
              });
      }
       
       
       
       /**
        * Método que nos configura la ventana del Trabajador. Vamos a configurarla como un formulario MDI
        */
       private void configVentanaDueño(){
               this.setVisible(true);
           this.setExtendedState(MAXIMIZED_BOTH); // Maximizada por completo
           this.setResizable(false); // No se puede redimensionar. Solo minimizar.
           
           panelMDI = new JPanel();
           panelMDI.setLayout(null);
           this.add(panelMDI);
       
           
           this.setDefaultCloseOperation(EXIT_ON_CLOSE);
           
           //Pregunta si quiere cerrar la sesión al darle a la "X"        
           this.addWindowListener(new WindowAdapter(){
                    public void windowClosing(WindowEvent e) {                     
                            dialog_salir();          
                    }
              });
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
        * Este método se encarga de cargar todos los componentes de la aplicación. Dependiendo de lo que el usuario
        * seleccione posteriormente, se mostrará un contenido u otro.
        *
        */
       
       public void cargarFormularios(){
               
               frmUsuarios = new UsuarioVisual(panelFormularios.getWidth() ,panelFormularios.getHeight()); // Tamaño completo del Formulario
               panelFormularios.add(frmUsuarios);
               
               frmAperos = new AperoVisual();
               panelFormularios.add(frmAperos);
               
               frmProductos = new ProductoVisual(panelFormularios.getWidth(),panelFormularios.getHeight());
               panelFormularios.add(frmProductos);
               
               
       }
     
       /**
        *
        * @author Juan Carlos García
        *
        */
       
       public class AccionDeBoton implements ActionListener {
               
               public void actionPerformed(ActionEvent e) {
                       
                      String comando =  e.getActionCommand(); // Recogemos el valor de el botón pulsado
                     
                      System.out.println(comando);
                     
                      if(comando.equals("Usuarios")){
                             
                              frmUsuarios.setVisible(true);
                             
                      }
                     
                      if(comando.equals("Productos")){
                             
                             
                              frmProductos.setVisible(true);
                             
                             
                      }
                     
                     
                     
                     
                       
                       
               }
             }
       
    }
       
       
     
     
