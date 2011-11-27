/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionVISUAL;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import code.google.com.opengis.gestion.Parcela;
import code.google.com.opengis.gestionDAO.AperoDAO;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.ProductoDAO;
/**
 * Clase Visual de Parcela, con la que realizaremos altas, bajas y modificaciones.
 * @author stanfermo
 *
 */
public class ParcelaVisual extends JInternalFrame {
	private JPanel panelAlta,panelModificacion,panelGestion;
	//private JTabbedPane tabbed;
	
	//PG
	
	private Toolkit tk;
	private Dimension t;
	private int altura,anchura;
	private JLabel titulo=new JLabel("P A R C E L A S");
	private JTextField txtbuscar;
	private JScrollPane scrollPane1;
	private JTable tablabuscar;
	private DefaultTableModel dtmbuscar;
	private JButton btnBuscar;
	private JButton btnAlta;
	private JButton btnModi;
	private JButton btnDesactivar;
	private boolean b=false;
	private Font miFuente= new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16);
	
	
	private String[] columnas = {"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"};
	//private Object[][]data={{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Población", "Polígono", "Número","Partida","DniPropietario"}};
	
	//PA
	private GridBagConstraints gbc;
	private JButton btnAltaPa=new JButton("Alta");
	private JButton btnVolverPa=new JButton("Volver");

	private JComboBox provinciaCB;
	private JComboBox modeloCB;
	private JLabel tituloAlta=new JLabel("ALTA   PARCELA");
	private JLabel id=new JLabel("Id Parcela:");
    private JLabel alias=new JLabel("Alias:");
    private JLabel provincia=new JLabel("Provincia:");
    private JLabel poblacion=new JLabel("Población");
    private JLabel poligono=new JLabel("Polígono:");
    private JLabel partida=new JLabel("Partida:");
    private JLabel numero=new JLabel("Número:");
    private JLabel estado=new JLabel("Estado:");
    private JLabel dnip= new JLabel("DNI:");
    
    private JTextField textId=new JTextField();
    private JTextField textalias=new JTextField();
    private JTextField textpoblacion=new JTextField();
    private JTextField textpoligono=new JTextField();
    private JTextField textpartida=new JTextField();
    private JTextField textnumero=new JTextField();
	private JTextField textdnip= new JTextField();
    private DefaultComboBoxModel dc1;
	private GridBagLayout gbl;
	
	//PMODIFICACION
	private GridBagConstraints gbcm;
	private JButton btnModPa=new JButton("Modificar");
	private JButton btnVolverPam=new JButton("Volver");

	private JComboBox estadoCBm,provinciaCBm;
	private JLabel tituloMod=new JLabel("MODIFICAR   PARCELA");
	private JLabel idm=new JLabel("Id Parcela:");
    private JLabel aliasm=new JLabel("Alias:");
    private JLabel provinciam=new JLabel("Provincia:");
    private JLabel poblacionm=new JLabel("Población");
    private JLabel poligonom=new JLabel("Polígono:");
    private JLabel numerom=new JLabel("Número:");
    private JLabel estadom=new JLabel("Estado:");
    private JLabel partidam=new JLabel("Partida:");
    private JLabel dnipm= new JLabel("DNI:");
    private JCheckBox chkActivo=new JCheckBox("Activo",true);
    
    private JTextField textIdm=new JTextField("");
    private JTextField textaliasm=new JTextField("");
    private JTextField textpoblacionm=new JTextField("");
    private JTextField textpoligonom=new JTextField("");
    private JTextField textnumerom=new JTextField("");
    private JTextField textpartidam=new JTextField();
	private JTextField textdnipm= new JTextField("");
    private DefaultComboBoxModel dc1m,dc2m;
    private JCheckBox chkActivom=new JCheckBox("Activo",true);
	private GridBagLayout gblm;
	
	/**
	 * Único constructor con 2 parametros anchura y altura.
	 * @param ancho : Anchura en formato entero.
	 * @param alto : Altura en formato entero.
	 */
	public ParcelaVisual(int ancho,int alto){
		super("Parcela",true, true, true, true);
		this.setBounds(0,0,ancho,alto);
		this.setClosable(true);
		//tabbed= new JTabbedPane();
		//this.add(tabbed);
		panelGestion= new JPanel();
		//this.JTabbedPanePrincipal();
		dibujarPanelAlta();
		dibujarPanelModificacion();
		dibujarPanelGestion();
	}
	//public void JTabbedPanePrincipal(){
	    //tabbed.add("GESTION PARCELA",panelGestion);
    	//tabbed.add("ALTA PARCELA",panelAlta);
	//}
	
	/**
	 * Vaciar el contenido de los campos del formulario
	 */
	public void limpiarFormMod(){
    	textIdm.setText("");
    	textaliasm.setText("");
    	textpoblacionm.setText("");
    	textpoligonom.setText("");
    	textnumerom.setText("");
    	textdnipm.setText("");
	}
	public void limpiarFormAltas(){
    	textId.setText("");
    	textalias.setText("");
    	textpoblacion.setText("");
    	textpoligono.setText("");
    	textnumero.setText("");
    	textdnip.setText("");
	}
	/**
	 * Método que nos calcula la resolución del sistema Operativo.
	 */
	public void resolucionOS(){
		tk = Toolkit.getDefaultToolkit();
	    t = tk.getScreenSize();
	    altura= (int) Math.floor(t.getHeight());
	    anchura=(int)Math.floor(t.getWidth());
	}
	public int getAltura() {
		return altura;
	}
	public int getAnchura() {
		return anchura;
	}
	
	/**
	 * Método que nos dibuja el contenido del Panel ALta.
	 */
	public void dibujarPanelAlta(){
		panelAlta= new JPanel();
		this.add(panelAlta);
		//panelAlta.setVisible(false);
		
		//COMBOBOX DE MODELO
		
		dc1 = new DefaultComboBoxModel();
		dc1.addElement("Álava");
		dc1.addElement("Albacete");
		dc1.addElement("Alicante");
		dc1.addElement("Almería");
		dc1.addElement("Asturias");
		dc1.addElement("Avila");
		dc1.addElement("Badajoz");
		dc1.addElement("Barcelona");
		dc1.addElement("Burgos");
		dc1.addElement("Cáceres");
		dc1.addElement("Cadiz");
		dc1.addElement("Cantabria");
		dc1.addElement("Castellón");
		dc1.addElement("Ceuta");
		dc1.addElement("Ciudad Real");
		dc1.addElement("Córdoba");
		dc1.addElement("Cuenca");
		dc1.addElement("Gerona");
		dc1.addElement("Granada");
		dc1.addElement("Guadalajara");
		dc1.addElement("Guipúzcoa");
		dc1.addElement("Huelva");
		dc1.addElement("Huesca");
		dc1.addElement("Islas Baleares");
		dc1.addElement("Jaén");
		dc1.addElement("La Coruña");
		dc1.addElement("La Rioja");
		dc1.addElement("Las Palmas");
		dc1.addElement("León");
		dc1.addElement("Lérida");
		dc1.addElement("Lugo");
		dc1.addElement("Madrid");
		dc1.addElement("Málaga");
		dc1.addElement("Melilla");
		dc1.addElement("Murcia");
		dc1.addElement("Navarra");
		dc1.addElement("Orense");
		dc1.addElement("Palencia");
		dc1.addElement("Pontevedra");
		dc1.addElement("Tenerife");
		dc1.addElement("Salamanca");
		dc1.addElement("Segovia");
		dc1.addElement("Sevilla");
		dc1.addElement("Soria");
		dc1.addElement("Tarragona");
		dc1.addElement("Teruel");
		dc1.addElement("Toledo");
		dc1.addElement("Valencia");
		dc1.addElement("Valladolid");
		dc1.addElement("Vizcaya");
		dc1.addElement("Zamora");
		dc1.addElement("Zaragoza");
		
		
		provinciaCB=new JComboBox(dc1);
		
		//DISEÑO FUENTES
	    tituloAlta.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 26));
		id.setFont(miFuente);
		alias.setFont(miFuente);
		provincia.setFont(miFuente);
		poblacion.setFont(miFuente);
		poligono.setFont(miFuente);
		numero.setFont(miFuente);
		partida.setFont(miFuente);
		estado.setFont(miFuente);
		dnip.setFont(miFuente);
		
	    gbl = new GridBagLayout();
	    GridBagConstraints gbc=new GridBagConstraints();
	    panelAlta.setLayout(new GridBagLayout());
		
		textId.setColumns(150);
		
		
		  //diseño gridBaglayout
		panelAlta.setLayout(gbl);
	    gbc.weighty = 0;
	    gbc.weightx = 0;
	//primera fila - título
	    gbc.anchor=GridBagConstraints.NORTH;
	    gbc.insets=new Insets(10,10,10,10);
	    gbc.gridwidth=GridBagConstraints.REMAINDER;
	    panelAlta.add(tituloAlta, gbc);
	    
	    
	    //gbc.anchor=GridBagConstraints.SOUTH;
		//FILA 1 ID
	    gbc.fill=GridBagConstraints.HORIZONTAL;
	    gbc.anchor=GridBagConstraints.EAST;
	    gbc.gridwidth=3;
	    gbc.ipadx = 100;
	    //gbc.insets=new Insets(10,10,10,10);
	    //panelAlta.add(id, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    //panelAlta.add(textId, gbc);
	    
	    //FILA 2 ALIAS
	    gbc.gridwidth = 3;
	    panelAlta.add(alias, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(textalias, gbc);
	    
	    //FILA 3 PROVINCIA
	    gbc.gridwidth = 3;
	    panelAlta.add(provincia, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(provinciaCB, gbc);

	    //FILA 4 POBLACIÓN
	    gbc.gridwidth = 3;
	    panelAlta.add(poblacion, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(textpoblacion, gbc);
	    
	    //FILA 5 POLIGONO
	    gbc.gridwidth = 3;
	    panelAlta.add(poligono, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(textpoligono, gbc);

	    //FILA 6 NUMERO
	    gbc.gridwidth = 3;
	    panelAlta.add(numero, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(textnumero, gbc);
	    
	    //FILA 6 NUMERO
	    gbc.gridwidth = 3;
	    panelAlta.add(partida, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(textpartida, gbc);
	    
	    //FILA 7 ESTADO
	    gbc.gridwidth = 3;
	    panelAlta.add(dnip, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER; 
	    panelAlta.add(textdnip, gbc); 
	    
	    //FILA 8 DNI
	    gbc.gridwidth = 3;
	    panelAlta.add(estado, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(chkActivo, gbc);
	    chkActivo.setEnabled(false);
	    
	    
	    //ULTIMA FILA BOTONES
	    gbc.gridwidth = 3;
	    panelAlta.add(btnAltaPa, gbc);
	    btnAltaPa.addActionListener(new java.awt.event.ActionListener() {	
	        public void actionPerformed(java.awt.event.ActionEvent e) {                 
	        		int intbol=0;
	        		if(chkActivo.isSelected()==true){
	        			intbol=1;
	        		}
	        		
					Parcela p1=new Parcela(0,textalias.getText(),(String) provinciaCB.getSelectedItem(),textpoblacion.getText(),
							textpoligono.getText(),textnumero.getText(),intbol,textpartida.getText(),textdnip.getText());
					
					if(Parcela.isValido()==true){
						try {
							p1.altaParcela();
							System.out.println("CONSULTA INSERTADA CORRECTAMENTE");
						} catch (SQLException e1) {
							System.out.println("ERROR DE INSERCION");
							 //TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
	    }});
	
	    
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(btnVolverPa, gbc);
	    btnVolverPa.addActionListener(new java.awt.event.ActionListener() {	
		     public void actionPerformed(java.awt.event.ActionEvent e) {                     
		        	panelAlta.setVisible(false);
		        	add(panelGestion);
		        	panelGestion.setVisible(true);
		}});    	  
		}//CIERRA PANEL ALTAS
	
	//Moficacion
	/**
	 * Método que nos dibuja el contenido del Panel Modificación.
	 */
		public void dibujarPanelModificacion(){
			panelModificacion= new JPanel();
			this.add(panelModificacion);
			panelModificacion.setVisible(false);
			
			//COMBOBOX DE MODELO
			dc1m = new DefaultComboBoxModel();//POR HACER
			dc1.addElement("Álava");
			dc1.addElement("Albacete");
			dc1.addElement("Alicante");
			dc1.addElement("Almería");
			dc1.addElement("Asturias");
			dc1.addElement("Avila");
			dc1.addElement("Badajoz");
			dc1.addElement("Barcelona");
			dc1.addElement("Burgos");
			dc1.addElement("Cáceres");
			dc1.addElement("Cadiz");
			dc1.addElement("Cantabria");
			dc1.addElement("Castellón");
			dc1.addElement("Ceuta");
			dc1.addElement("Ciudad Real");
			dc1.addElement("Córdoba");
			dc1.addElement("Cuenca");
			dc1.addElement("Gerona");
			dc1.addElement("Granada");
			dc1.addElement("Guadalajara");
			dc1.addElement("Guipúzcoa");
			dc1.addElement("Huelva");
			dc1.addElement("Huesca");
			dc1.addElement("Islas Baleares");
			dc1.addElement("Jaén");
			dc1.addElement("La Coruña");
			dc1.addElement("La Rioja");
			dc1.addElement("Las Palmas");
			dc1.addElement("León");
			dc1.addElement("Lérida");
			dc1.addElement("Lugo");
			dc1.addElement("Madrid");
			dc1.addElement("Málaga");
			dc1.addElement("Melilla");
			dc1.addElement("Murcia");
			dc1.addElement("Navarra");
			dc1.addElement("Orense");
			dc1.addElement("Palencia");
			dc1.addElement("Pontevedra");
			dc1.addElement("Tenerife");
			dc1.addElement("Salamanca");
			dc1.addElement("Segovia");
			dc1.addElement("Sevilla");
			dc1.addElement("Soria");
			dc1.addElement("Tarragona");
			dc1.addElement("Teruel");
			dc1.addElement("Toledo");
			dc1.addElement("Valencia");
			dc1.addElement("Valladolid");
			dc1.addElement("Vizcaya");
			dc1.addElement("Zamora");
			dc1.addElement("Zaragoza");
			estadoCBm=new JComboBox(dc1);
			
			
			//DISEÑO FUENTES
		    tituloMod.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 26));
			idm.setFont(miFuente);
			aliasm.setFont(miFuente);
			provinciam.setFont(miFuente);
			poblacionm.setFont(miFuente);
			poligonom.setFont(miFuente);
			numerom.setFont(miFuente);
			partidam.setFont(miFuente);
			estadom.setFont(miFuente);
			dnipm.setFont(miFuente);
			
		    gblm = new GridBagLayout();
		    GridBagConstraints gbcm=new GridBagConstraints();
		    panelModificacion.setLayout(new GridBagLayout());
			
			textIdm.setColumns(150);
			
			
			  //diseño gridBaglayout
			panelModificacion.setLayout(gblm);
		    gbcm.weighty = 0;
		    gbcm.weightx = 0;
		//primera fila - título
		    gbcm.anchor=GridBagConstraints.NORTH;
		    gbcm.insets=new Insets(10,10,10,10);
		    gbcm.gridwidth=GridBagConstraints.REMAINDER;
		    panelModificacion.add(tituloMod, gbcm);

			
			//FILA 1 ID
		    gbcm.fill=GridBagConstraints.HORIZONTAL;
		    gbcm.anchor=GridBagConstraints.EAST;
		    gbcm.gridwidth=3;
		    gbcm.ipadx = 100;
		    //gbc.insets=new Insets(10,10,10,10);
		    panelModificacion.add(idm, gbcm);
		    gbcm.gridwidth = GridBagConstraints.REMAINDER;
		    panelModificacion.add(textIdm, gbcm);
		    
		    //FILA 2 ALIAS
		    gbcm.gridwidth = 3;
		    panelModificacion.add(aliasm, gbcm);
		    gbcm.gridwidth = GridBagConstraints.REMAINDER;
		    panelModificacion.add(textaliasm, gbcm);
		    
		    //FILA 3 PROVINCIA
		    gbcm.gridwidth = 3;
		    panelModificacion.add(provinciam, gbcm);
		    gbcm.gridwidth = GridBagConstraints.REMAINDER;
		    panelModificacion.add(estadoCBm, gbcm);

		    //FILA 4 POBLACIÓN
		    gbcm.gridwidth = 3;
		    panelModificacion.add(poblacionm, gbcm);
		    gbcm.gridwidth = GridBagConstraints.REMAINDER;
		    panelModificacion.add(textpoblacionm, gbcm);
		    
		    //FILA 5 POLIGONO
		    gbcm.gridwidth = 3;
		    panelModificacion.add(poligonom, gbcm);
		    gbcm.gridwidth = GridBagConstraints.REMAINDER;
		    panelModificacion.add(textpoligonom, gbcm);

		    //FILA 6 NUMERO
		    gbcm.gridwidth = 3;
		    panelModificacion.add(numerom, gbcm);
		    gbcm.gridwidth = GridBagConstraints.REMAINDER;
		    panelModificacion.add(textnumerom, gbcm);
		    
		    //FILA 6 NUMERO
		    gbcm.gridwidth = 3;
		    panelModificacion.add(partidam, gbcm);
		    gbcm.gridwidth = GridBagConstraints.REMAINDER;
		    panelModificacion.add(textpartidam, gbcm);
		    
		    //FILA 7 ESTADO
		    gbcm.gridwidth = 3;
		    panelModificacion.add(dnipm, gbcm);
		    gbcm.gridwidth = GridBagConstraints.REMAINDER; 
		    panelModificacion.add(textdnipm, gbcm); 
		    
		    //FILA 8 DNI
		    gbcm.gridwidth = 3;
		    panelModificacion.add(estadom, gbcm);
		    gbcm.gridwidth = GridBagConstraints.REMAINDER;
		    panelModificacion.add(chkActivom, gbcm);
		    chkActivom.setEnabled(false);
		    
		    
		    //ULTIMA FILA BOTONES
		    gbcm.gridwidth = 3;
		    panelModificacion.add(btnModPa, gbcm);
		    btnModPa.addActionListener(new java.awt.event.ActionListener() {	
		        public void actionPerformed(java.awt.event.ActionEvent e) {
	        		// CONSULTA MODIFICAR

					Parcela p1=new Parcela(Integer.parseInt(textIdm.getText()),textaliasm.getText(),provinciaCB.getSelectedItem().toString(),textpoblacionm.getText(),
							textpoligonom.getText(),textnumerom.getText(),1,textpartidam.getText(),textdnipm.getText());
					
					JOptionPane.showMessageDialog(null, Integer.parseInt(textIdm.getText())+" "+textaliasm.getText()+""+provinciaCB.getSelectedItem().toString()+" "+textpoblacionm.getText()+
							textpoligonom.getText()+" "+textnumerom.getText()+" "+1+" "+textpartidam.getText()+" "+textdnipm.getText() );
					
					if(Parcela.isValido()==true){
						try {
							p1.modificarParcela();
							System.out.println("PARCELA MODIFICADA CORRECTAMENTE");
						} catch (SQLException e1) {
							System.out.println("ERROR DE INSERCION");
							 //TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

		    }});
			
		    gbcm.gridwidth = GridBagConstraints.REMAINDER;
		    panelModificacion.add(btnVolverPam, gbcm);
		    btnVolverPam.addActionListener(new java.awt.event.ActionListener() {	
			     public void actionPerformed(java.awt.event.ActionEvent e) {                     
			    	 	panelModificacion.setVisible(false);
			        	add(panelGestion);
			        	panelGestion.setVisible(true);
			}});    	  
			}
	
		//PANEL GESTION
		/**
		 * Clase sin parametros que nos dibuja el contenido del Panel Gestión.
		 */
		private void dibujarPanelGestion() {
			this.add(panelGestion);
			resolucionOS();
			tablabuscar = new JTable(dtmbuscar);
			tablabuscar.getTableHeader().setReorderingAllowed(true);
			
			txtbuscar = new JTextField();
			scrollPane1 = new JScrollPane(tablabuscar,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			
			
			dtmbuscar = new DefaultTableModel();
			dtmbuscar.setColumnIdentifiers(columnas);
			tablabuscar = new JTable(dtmbuscar);
			btnBuscar = new JButton("BUSCAR");
			btnAlta = new JButton("ALTA");
			btnModi = new JButton("MODIFICACION");
			btnDesactivar = new JButton("BAJA");
			
			
			int anchuraTabla = getAnchura()-300;
			int alturaTabla=getAltura()-500;
			int anchuraTxtBuscar=360;
		    int fila1= (((getAltura())/2)-(alturaTabla/2)); // Fila de inicio
		    int columna1=((getAnchura()/2)-(anchuraTabla/2));//columna de inicio

		    panelGestion.setLayout(null);
		    
		  //CARGAMOS LOS COMPONENTES
		    panelGestion.add(titulo);
			titulo.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 26));
			titulo.setBounds(getAnchura()/2-70 ,getAnchura()-getAnchura()+20, 770, 20);

			panelGestion.add(txtbuscar);
			txtbuscar.setBounds(columna1, fila1-20-30, anchuraTxtBuscar, 30);
			
			scrollPane1.setViewportView(tablabuscar);
			panelGestion.add(scrollPane1);
			scrollPane1.setBounds(columna1, fila1, anchuraTabla, alturaTabla);
			
			panelGestion.add(btnBuscar);
			btnBuscar.setBounds(columna1+anchuraTabla-100, fila1-20-30, 100, 30);
			btnBuscar.addActionListener(new java.awt.event.ActionListener() {	
		        public void actionPerformed(java.awt.event.ActionEvent e) {
					//vaciamos la tabla de datos al realizar una nueva busqueda
					for (int i = tablabuscar.getRowCount() -1; i >= 0; i--){
						dtmbuscar.removeRow(i);
						} 
		        	llenar( txtbuscar.getText());
		        		
		        	
		        	
		    }});

			panelGestion.add(btnAlta);
			btnAlta.setBounds(columna1, fila1+alturaTabla+20, 65, 30);                  
			btnAlta.addActionListener(new java.awt.event.ActionListener() {	
	        public void actionPerformed(java.awt.event.ActionEvent e) {                     	
	        	panelGestion.setVisible(false);	
	        	add(panelAlta);
	        	panelAlta.setVisible(true);
	        	limpiarFormAltas();
	        }});  
			
			panelGestion.add(btnModi);
			btnModi.setBounds(columna1+ 75,fila1+alturaTabla+20, 110, 30);
			btnModi.addActionListener(new java.awt.event.ActionListener() {	
		        public void actionPerformed(java.awt.event.ActionEvent e) {                     	
		        	
		        	
		        		panelGestion.setVisible(false);	
		        		add(panelModificacion);
		        		panelModificacion.setVisible(true);

		        		textIdm.setText((tablabuscar.getValueAt(tablabuscar.getSelectedRow(), 0)).toString());
		            	textaliasm.setText((String) tablabuscar.getValueAt(tablabuscar.getSelectedRow(), 1) );
		            
		            	 //Recorremos provinciaCB y tomamos el que sea equivalente
		            	for(int x=0;x<provinciaCB.getItemCount();x++){
		            		if (provinciaCB.getItemAt(x).equals(
		            				(String) tablabuscar.getValueAt(tablabuscar.getSelectedRow(), 2) 
		            				)){
		            			provinciaCB.setSelectedIndex(x);
		            		}
		            	}
		            	textpoblacionm.setText((String) tablabuscar.getValueAt(tablabuscar.getSelectedRow(), 3) );
		            	textpoligonom.setText((String) tablabuscar.getValueAt(tablabuscar.getSelectedRow(), 4) );
		            	textnumerom.setText((String) tablabuscar.getValueAt(tablabuscar.getSelectedRow(), 5) ); 
		            	textpartidam.setText((String) tablabuscar.getValueAt(tablabuscar.getSelectedRow(), 6) );
		            	textdnipm.setText((String) tablabuscar.getValueAt(tablabuscar.getSelectedRow(), 7) );

		        	
		    }});
			
			panelGestion.add(btnDesactivar);
			btnDesactivar.setBounds(columna1+anchuraTabla-65, fila1+alturaTabla+20, 65, 30);
			btnDesactivar.addActionListener(new java.awt.event.ActionListener() {	
		        public void actionPerformed(java.awt.event.ActionEvent e) {
		        	ConectarDBA dba = null;
		        	String id=tablabuscar.getValueAt(tablabuscar.getSelectedRow(), 0).toString();
		        	ConectarDBA.acceder();
		        	try {
		        		int confirmar=JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el registro "+id);
		        		if(JOptionPane.OK_OPTION==confirmar){
						dba.modificar("UPDATE `dai2opengis`.`parcela` SET `activo`= 0 WHERE `idparcela` LIKE "+id);
						JOptionPane.showMessageDialog(null,"El numero de registro "+id+" ha sido desactivado correctamente.");
		        		}
		        	} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
		    }});
	
		}
		

		/**
		 * Método que nos permitira rellenar la tabla con los registros que correspondan
		 * @param criterio: Criterio de busqueda para la consulta sql
		 */
		public void llenar(String criterio){
			try {
				int x=0;
				int y=0;
					dtmbuscar.setColumnCount(0);
					dtmbuscar.setRowCount(0);
					dtmbuscar.setColumnIdentifiers(columnas);
				
					ResultSet rs = Parcela.buscar(criterio);
					while (rs.next()) {
						Object[]fila = {rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5),
							rs.getObject(6), rs.getObject(7), rs.getObject(8)};
						dtmbuscar.addRow(fila);
					}
					rs.close();		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void tomarDatosTabla(){
			
		}
}
