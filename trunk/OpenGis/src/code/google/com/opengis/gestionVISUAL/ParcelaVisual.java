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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

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
	private JButton btnBuscar;
	private JButton btnAlta;
	private JButton btnModi;
	private JButton btnDesactivar;
	private boolean b=false;
	
	private String[] columnas = {"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"};
	private Object[][]data={{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"},{"IdParcela", "Alias", "Provincia","Poblaci�n", "Pol�gono", "N�mero","Partida","DniPropietario"}};
	
	//PA
	private GridBagConstraints gbc;
	private JButton btnAltaPa=new JButton("Alta");
	private JButton btnVolverPa=new JButton("Volver");

	private JComboBox estadoCB,modeloCB;
	private JLabel tituloAlta=new JLabel("ALTA   PARCELA");
	private JLabel id=new JLabel("Id Parcela:");
    private JLabel alias=new JLabel("Alias:");
    private JLabel provincia=new JLabel("Provincia:");
    private JLabel poblacion=new JLabel("Poblaci�n");
    private JLabel poligono=new JLabel("Pol�gono:");
    private JLabel numero=new JLabel("N�mero:");
    private JLabel estado=new JLabel("Estado:");
    private JLabel dnip= new JLabel("DNI:");
    
    private JTextField textId=new JTextField();
    private JTextField textalias=new JTextField();
    private JTextField textpoblacion=new JTextField();
    private JTextField textpoligono=new JTextField();
    private JTextField textnumero=new JTextField();
	private JTextField textdnip= new JTextField();
    private DefaultComboBoxModel dc1,dc2;
	private GridBagLayout gbl;
	
	//PMODIFICACION
	private GridBagConstraints gbcm;
	private JButton btnModPa=new JButton("Modificar");
	private JButton btnVolverPam=new JButton("Volver");

	private JComboBox estadoCBm,modeloCBm;
	private JLabel tituloMod=new JLabel("MODIFICAR   PARCELA");
	private JLabel idm=new JLabel("Id Parcela:");
    private JLabel aliasm=new JLabel("Alias:");
    private JLabel provinciam=new JLabel("Provincia:");
    private JLabel poblacionm=new JLabel("Poblaci�n");
    private JLabel poligonom=new JLabel("Pol�gono:");
    private JLabel numerom=new JLabel("N�mero:");
    private JLabel estadom=new JLabel("Estado:");
    private JLabel dnipm= new JLabel("DNI:");
    
    private JTextField textIdm=new JTextField();
    private JTextField textaliasm=new JTextField();
    private JTextField textpoblacionm=new JTextField();
    private JTextField textpoligonom=new JTextField();
    private JTextField textnumerom=new JTextField();
	private JTextField textdnipm= new JTextField();
    private DefaultComboBoxModel dc1m,dc2m;
	private GridBagLayout gblm;
	
	/**
	 * �nico constructor con 2 parametros anchura y altura.
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
		this.add(panelGestion);	
	}
	
	//public void JTabbedPanePrincipal(){
	    //tabbed.add("GESTION PARCELA",panelGestion);
    	//tabbed.add("ALTA PARCELA",panelAlta);
	//}
	

	//PANEL GESTION
	/**
	 * Clase sin parametros que nos dibuja el contenido del Panel Gesti�n.
	 */
	private void dibujarPanelGestion() {
		resolucionOS();
		txtbuscar = new JTextField();
		scrollPane1 = new JScrollPane(tablabuscar,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tablabuscar = new JTable(data,columnas);
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

	        	
	    }});

		panelGestion.add(btnAlta);
		btnAlta.setBounds(columna1, fila1+alturaTabla+20, 65, 30);                  
		btnAlta.addActionListener(new java.awt.event.ActionListener() {	
        public void actionPerformed(java.awt.event.ActionEvent e) {                     	
        	panelGestion.setVisible(false);	
        	add(panelAlta);
        	panelAlta.setVisible(true);
        }});  
		
		panelGestion.add(btnModi);
		btnModi.setBounds(columna1+ 75,fila1+alturaTabla+20, 110, 30);
		btnModi.addActionListener(new java.awt.event.ActionListener() {	
	        public void actionPerformed(java.awt.event.ActionEvent e) {                     	
	        	panelGestion.setVisible(false);	
	        	add(panelModificacion);
	        	panelModificacion.setVisible(true);
	        	
	    }});
		
		panelGestion.add(btnDesactivar);
		btnDesactivar.setBounds(columna1+anchuraTabla-65, fila1+alturaTabla+20, 65, 30);
		btnDesactivar.addActionListener(new java.awt.event.ActionListener() {	
	        public void actionPerformed(java.awt.event.ActionEvent e) {                     	

	        	
	    }});
		
	}
	
	/**
	 * M�todo que nos calcula la resoluci�n del sistema Operativo.
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
	
	
	//Alta
	/**
	 * M�todo que nos dibuja el contenido del Panel ALta.
	 */
	public void dibujarPanelAlta(){
		panelAlta= new JPanel();
		this.add(panelAlta);
		panelAlta.setVisible(false);
		
		//COMBOBOX DE MODELO
		dc1 = new DefaultComboBoxModel();//POR HACER
		dc1.addElement("Valencia");
		dc1.addElement("Madrid");
		dc1.addElement("Barcelona");
		estadoCB=new JComboBox(dc1);
		
		dc2 = new DefaultComboBoxModel();
		dc2.addElement("Activo");
		dc2.addElement("Inactivo");
		modeloCB=new JComboBox(dc2);
		
		//DISE�O FUENTES
	    tituloAlta.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 26));
		id.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
		alias.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
		provincia.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
		poblacion.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
		poligono.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
		numero.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
		estado.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
		dnip.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
		
	    gbl = new GridBagLayout();
	    GridBagConstraints gbc=new GridBagConstraints();
	    panelAlta.setLayout(new GridBagLayout());
		
		textId.setColumns(150);
		
		
		  //dise�o gridBaglayout
		panelAlta.setLayout(gbl);
	    gbc.weighty = 0;
	    gbc.weightx = 0;
	//primera fila - t�tulo
	    gbc.anchor=GridBagConstraints.NORTH;
	    gbc.insets=new Insets(10,10,10,10);
	    gbc.gridwidth=GridBagConstraints.REMAINDER;
	    panelAlta.add(tituloAlta, gbc);
	    

		
		//FILA 1 ID
	    gbc.fill=GridBagConstraints.HORIZONTAL;
	    gbc.anchor=GridBagConstraints.EAST;
	    gbc.gridwidth=3;
	    gbc.ipadx = 100;
	    gbc.insets=new Insets(10,10,10,10);
	    panelAlta.add(id, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(textId, gbc);
	    
	    //FILA 2 ALIAS
	    gbc.gridwidth = 3;
	    panelAlta.add(alias, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(textalias, gbc);
	    
	    //FILA 3 PROVINCIA
	    gbc.gridwidth = 3;
	    panelAlta.add(provincia, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(estadoCB, gbc);

	    //FILA 4 POBLACI�N
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
	    
	    //FILA 7 ESTADO
	    gbc.gridwidth = 3;
	    panelAlta.add(estado, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(modeloCB, gbc); 
	    
	    //FILA 8 DNI
	    gbc.gridwidth = 3;
	    panelAlta.add(dnip, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(textdnip, gbc); 
	    
	    
	    //ULTIMA FILA BOTONES
	    gbc.gridwidth = 3;
	    panelAlta.add(btnAltaPa, gbc);
	    btnAltaPa.addActionListener(new java.awt.event.ActionListener() {	
	        public void actionPerformed(java.awt.event.ActionEvent e) {                     	

	    }});
		
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panelAlta.add(btnVolverPa, gbc);
	    btnVolverPa.addActionListener(new java.awt.event.ActionListener() {	
		     public void actionPerformed(java.awt.event.ActionEvent e) {                     
		        	panelAlta.setVisible(false);
		        	add(panelGestion);
		        	panelGestion.setVisible(true);
		}});    	  
		}
	
	//Moficacion
	/**
	 * M�todo que nos dibuja el contenido del Panel Modificaci�n.
	 */
		public void dibujarPanelModificacion(){
			panelModificacion= new JPanel();
			this.add(panelModificacion);
			panelModificacion.setVisible(false);
			
			//COMBOBOX DE MODELO
			dc1m = new DefaultComboBoxModel();//POR HACER
			dc1m.addElement("Valencia");
			dc1m.addElement("Madrid");
			dc1m.addElement("Barcelona");
			estadoCBm=new JComboBox(dc1);
			
			dc2m = new DefaultComboBoxModel();
			dc2m.addElement("Activo");
			dc2m.addElement("Inactivo");
			modeloCBm=new JComboBox(dc2m);
			
			//DISE�O FUENTES
		    tituloMod.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 26));
			idm.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
			aliasm.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
			provinciam.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
			poblacionm.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
			poligonom.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
			numerom.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
			estadom.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
			dnipm.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 16));
			
		    gblm = new GridBagLayout();
		    GridBagConstraints gbcm=new GridBagConstraints();
		    panelModificacion.setLayout(new GridBagLayout());
			
			textIdm.setColumns(150);
			
			
			  //dise�o gridBaglayout
			panelModificacion.setLayout(gblm);
		    gbcm.weighty = 0;
		    gbcm.weightx = 0;
		//primera fila - t�tulo
		    gbcm.anchor=GridBagConstraints.NORTH;
		    gbcm.insets=new Insets(10,10,10,10);
		    gbcm.gridwidth=GridBagConstraints.REMAINDER;
		    panelModificacion.add(tituloMod, gbcm);

			
			//FILA 1 ID
		    gbcm.fill=GridBagConstraints.HORIZONTAL;
		    gbcm.anchor=GridBagConstraints.EAST;
		    gbcm.gridwidth=3;
		    gbcm.ipadx = 100;
		    gbcm.insets=new Insets(10,10,10,10);
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

		    //FILA 4 POBLACI�N
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
		    
		    //FILA 7 ESTADO
		    gbcm.gridwidth = 3;
		    panelModificacion.add(estadom, gbcm);
		    gbcm.gridwidth = GridBagConstraints.REMAINDER;
		    panelModificacion.add(modeloCBm, gbcm); 
		    
		    //FILA 8 DNI
		    gbcm.gridwidth = 3;
		    panelModificacion.add(dnipm, gbcm);
		    gbcm.gridwidth = GridBagConstraints.REMAINDER;
		    panelModificacion.add(textdnipm, gbcm); 
		    
		    
		    //ULTIMA FILA BOTONES
		    gbcm.gridwidth = 3;
		    panelModificacion.add(btnModPa, gbcm);
		    btnModPa.addActionListener(new java.awt.event.ActionListener() {	
		        public void actionPerformed(java.awt.event.ActionEvent e) {                     	

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
	}
