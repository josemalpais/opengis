package code.google.com.opengis.gestionVISUAL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;



import code.google.com.opengis.gestion.Producto;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.ProductoDAO;

 
public class ProductoVisual extends JInternalFrame {
	
		static String resultado;
		static ConectarDBA dba = new ConectarDBA();
		
		private int limit;
	    private boolean toUppercase = false;

		private int ancho;
		private int alto;
        private JPanel panelProducto;
        private JPanel panelProductoAlt;
        
        private JButton cmdAceptarAlt;
        private JButton cmdCancelarAlt;
        private JButton cmdCrear;
        private JButton cmdModificar;
        private JButton cmdDesactivar;
        
        private static JTable tblTabla = null;
        private static String[] columnNames = {"ID", "Nombre","Descripción", "Tipo","dosis"};
        private static JScrollPane scrollUsuarios;        
        static DefaultTableModel modelo;

        private static JTextField txtIdprod;
        private static JTextField txtNombre;
        private static JTextField txtDosis;
        private static JTextField txtBuscar;
        private static JTextField txtDni;
        
        private static JTextArea txtDescripcion;
        
        private static JLabel lblDosis;
        private static JComboBox cmbDosis;
        private static String[] tipo = {"Líquido", "Granulado", "Polvo"};
         private static JCheckBox chkActivo;
       
        
public ProductoVisual(){
	super("Productos", true, true, true, true);

        this.ancho = ancho;
        this.alto = alto;
        panelProducto = new JPanel ();
        panelProducto.setLayout(null);
        this.add(panelProducto);
        this.setBounds(0,0,ancho,alto);
        this.setTitle("Producto");
        this.setClosable(true);
        this.setMaximizable(true);
        TitledBorder jb = new TitledBorder("Gestion de productos");
        panelProducto.setBorder(jb);
        double ii = ancho/1.7;
        double aa = alto/1.7;
        panelProducto.setBounds(new Rectangle(0,0,(int)ii,(int)aa));
     
        principalProducto(panelProducto);
   
}

final static boolean shouldFill = true;
final static boolean shouldWeightX = true;
final static boolean RIGHT_TO_LEFT = false;

	public void cargarAlta(){
		

		    panelProductoAlt = new JPanel ();
	        panelProductoAlt.setLayout(null);
	        panelProductoAlt.getBounds(panelProducto.getBounds());
	        this.add(panelProductoAlt);
	        this.setBounds(0,0,ancho,alto);
	        this.setTitle("Producto");
	        this.setClosable(true);
	        this.setMaximizable(true);
	        TitledBorder jb2 = new TitledBorder("Crear producto");
	        panelProductoAlt.setBorder(jb2);
	        double ii2 = ancho/1.7;
	        double aa2 = alto/1.7;
	        panelProductoAlt.setOpaque(false);
	        panelProductoAlt.setBounds(new Rectangle(0,0,(int)ii2,(int)aa2));

	        
	        altas(panelProductoAlt);
	}

public void principalProducto(Container pane){
         if (RIGHT_TO_LEFT) {
         pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
     }

        
         cmdCrear =new JButton();
         cmdModificar= new JButton();
         cmdDesactivar= new JButton();
         tblTabla= new JTable();
     JLabel label;
     txtBuscar= new JTextField();
     
     pane.setLayout(new GridBagLayout());
     GridBagConstraints c = new GridBagConstraints();
     GridBagConstraints cTabla = new GridBagConstraints();
     if (shouldFill) {
     
     c.fill = GridBagConstraints.HORIZONTAL;
     }
     c.insets = new Insets(8,8,8,8);
    // button = new JButton("Button 1");
     if (shouldWeightX) {
     c.weightx = 0.5;
     } 
     c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 0;
         c.gridy = 0;
     
        label = new JLabel("Buscar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(label,c);
        
        txtBuscar = new JTextField(10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        cmdCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            buscarProducto();
            }
            });
        pane.add(txtBuscar, c);
        
        
        modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(columnNames);
		tblTabla = new JTable(modelo);
		scrollUsuarios = new JScrollPane(tblTabla);
        tblTabla.setModel(modelo);
        cTabla.fill = GridBagConstraints.HORIZONTAL;
        cTabla.gridx = 0;
        cTabla.gridy = 1;
        cTabla.gridwidth = 5;
        cTabla.gridheight = 5;
        pane.add(scrollUsuarios, cTabla);
        
        cmdCrear =new JButton("Nuevo Producto");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 7;
        cmdCrear.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {                     
                          		panelProducto.setVisible(false);
                                cargarAlta();                   
            try { 
           	 	ConectarDBA.acceder();
                ResultSet rs = ConectarDBA.consulta("SELECT MAX(idprod) FROM producto");
                Integer idprod;
                
                rs.next();
                idprod = rs.getInt(1);
                
                if(idprod==null){
               	 
               	 idprod = 0;
               	 
                }
                txtIdprod.setText(idprod+1+"");
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }                   
         }
        });
        pane.add(cmdCrear, c);
        
         cmdModificar= new JButton("Modificar Producto");
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 1;
         c.gridy = 7;
        cmdModificar.addActionListener(new java.awt.event.ActionListener() {
        	        public void actionPerformed(java.awt.event.ActionEvent e) {                     
        	                          		panelProducto.setVisible(false);
        	                                //cargarModif();                   
        	            
        	         }
        	        });
         pane.add(cmdModificar, c);
         
         cmdDesactivar= new JButton("Desactivar Producto");
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 2;
         c.gridy = 7;
         cmdDesactivar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {                     
                                                
                        
                                
                }
        });
         pane.add(cmdDesactivar, c);
        
        
}
 public void altas(Container pane){
         if (RIGHT_TO_LEFT) {
         pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
     }

     
         cmdAceptarAlt= new JButton();
         cmdCancelarAlt= new JButton();
     JLabel label;
         txtIdprod= new JTextField();
         txtNombre= new JTextField();
         txtDosis= new JTextField();
     txtDescripcion= new JTextArea();
     cmbDosis= new JComboBox();
   
    // chkActivo= new JCheckBox();
     
     pane.setLayout(new GridBagLayout());
     GridBagConstraints c = new GridBagConstraints();
     if (shouldFill) {
         c.fill = GridBagConstraints.HORIZONTAL;
     }
     c.insets = new Insets(8,8,8,8);
        // button = new JButton("Button 1");
     if (shouldWeightX) {
         c.weightx = 0.5;
     }
     c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        //pane.add(button, c);

        label = new JLabel("Producto ID:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(label,c);

        txtIdprod= new JTextField();
        txtIdprod.setEditable(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        pane.add(txtIdprod, c);

        label = new JLabel("Nombre del Producto:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        pane.add(label,c);

        txtNombre = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        pane.add(txtNombre, c);

        lblDosis = new JLabel("Dosis: (l/ha)");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(lblDosis,c);

        txtDosis = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        pane.add(txtDosis, c);

        label = new JLabel("Descripción:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        pane.add(label,c);

        txtDescripcion = new JTextArea("",10,15);
        txtDescripcion.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setPreferredSize(new Dimension(300, 20));
        
        c.fill = GridBagConstraints.NONE;
        c.gridx = 2;
        c.gridy = 4;
        pane.add(txtDescripcion, c);
        

        label = new JLabel ("Tarea:");
        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy= 0;
        pane.add(label,c);
 
        
        cmbDosis= new JComboBox(tipo);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx= 4;
        c.gridy= 0;
        cmbDosis.addActionListener(new ActionListener(){
        	public void actionPerformed (ActionEvent e){
        		int selIndex = cmbDosis.getSelectedIndex();
        			switch (selIndex){
        			case 0:
        				lblDosis.setText("Dosis (l/ha):");
        			break;
        			case 1:
        				lblDosis.setText("Dosis (gr/ha):");
        			break;
        			case 2:
        				lblDosis.setText("Dosis (kg/ha)");
        			break;
        			}
        	}
        });
        pane.add(cmbDosis, c);
 
        label= new JLabel("Activo");
        c.fill=GridBagConstraints.HORIZONTAL ;
        c.gridx=3;
        c.gridy=1;
        pane.add(label,c);
 
        chkActivo =new JCheckBox ();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy= 1;
        pane.add(chkActivo,c);
 
        cmdAceptarAlt = new JButton("Confirmar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        cmdAceptarAlt.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {     
                       int variableint;
					if (chkActivo.isSelected()){
                        	variableint=0;
                        }else{
                        	variableint=1;
                        }
                        Producto p = new Producto(Integer.parseInt(txtIdprod.getText()), txtNombre.getText(), txtDescripcion.getText(), cmbDosis.getSelectedItem().toString(), txtDosis.getText(), txtDni.getText(), variableint);        
                        p.validarDatos();
                        if (p.getCorrecto()) {
							p.crearProducto();
							 try {	
					                ResultSet rs = ConectarDBA.consulta("SELECT MAX(idprod) FROM producto");
					                Integer idprod; 
					                rs.next();
					                idprod = rs.getInt(1); 
					                if(idprod==null){	 
					               	 idprod = 0;
					                }
					                txtIdprod.setText(idprod+1+"");
							 } catch (SQLException e2) {
					                System.out.println(e2.getMessage());
					            }
                        }  
                }
        });
        pane.add(cmdAceptarAlt, c);
        
        cmdCancelarAlt = new JButton("Cancelar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 5;
        cmdCancelarAlt.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {                     
                        try {
							ConectarDBA.cerrarCon();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                		panelProducto.setVisible(true);
                        panelProductoAlt.setVisible(false);
                }
        });
        pane.add(cmdCancelarAlt, c);
 }
 public void buscarProducto() {
     try {
         modelo.setColumnCount(0);
         modelo.setRowCount(0);
         String salida = "*";
         String criterio = "nombre like '%" + txtNombre.getText() + "%'";
         //String criterio=txtNombre.getText();
         //criterio=criterio.equals("*")?"Nombre like"+criterio:"Nombre like'"+criterio+"'";
         ResultSet rs = ProductoDAO.buscar(salida, criterio);
         int nColumnas = rs.getMetaData().getColumnCount();
         Object[] etiquetas = new Object[nColumnas];
         for (int i = 0; i
                 < nColumnas; i++) {
             etiquetas[i] = rs.getMetaData().getColumnLabel(i + 1);
         }
         modelo.setColumnIdentifiers(etiquetas);
         while (rs.next()) {
             Object[] registro = new Object[nColumnas];
             for (int i = 0; i
                     < nColumnas; i++) {
                 registro[i] = rs.getObject(i + 1);
             }
             modelo.addRow(registro);
         }
     } catch (SQLException e) {
         System.out.println(e);
     }
 }
     
 }
