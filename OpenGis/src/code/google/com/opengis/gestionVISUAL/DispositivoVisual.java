package code.google.com.opengis.gestionVISUAL;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import code.google.com.opengis.gestion.Dispositivo;
import code.google.com.opengis.gestionDAO.DispositivoDAO;


public class DispositivoVisual extends JInternalFrame implements ActionListener {

	private JPanel panelDispositivo;
	private JPanel panelDispositivoMod;
	
	private JButton cmdNuevoDispositivo;
	private JButton cmdBajaDispositivo;
	private JButton cmdModificarDispositivo;
	private JButton cmdGuardarDispositivo;
	private JButton cmdBuscarDispositivo;
	
	private JButton cmdAceptarMod;
	private JButton cmdCancelarMod;
	private JButton cmdElegirDispMod;
	private JButton cmdCancelarAlta;
	
	private JLabel lblIddispositivo;
	private JLabel lblIddispositivoMod;
	private JLabel lblModelo;
	private JLabel lblModeloMod;
	private JLabel lblNumSerie;
	private JLabel lblNumSerieMod;
	
	
	private JTextField txtIddispositivo;
	private JTextField txtIddispositivoMod;
	private JTextField txtModelo;
	private JTextField txtModeloMod;
	private JTextField txtNumSerie;
	private JTextField txtNumSerieMod;
	

	
	
	/**
	 * Constructor de la clase UsuarioVisual. Se le pasarán los parametros necesarios para construir el alto y el ancho.
	 */

	public DispositivoVisual(int ancho, int alto){
			super("Dispositivos",false, true, true, true);
			panelDispositivo = new JPanel ();
			panelDispositivo.setLayout(null);
			this.add(panelDispositivo);
			this.setBounds(0,0,ancho,alto);
			this.setTitle("Dispositivo");
			this.setClosable(true);
		   
			nuevosObjetos();
			//this.setVisible(true);
			cmdBajaDispositivo.addActionListener(this);
		
		
	}
	
	
	/**
	 * Este método carga en el formulario los Objetos necesarios Ej: Cajas de Texto, Labels, etc...
	 * 
	 */
	
	public void nuevosObjetos(){
		
		lblIddispositivo = new JLabel("Id de Dispositivo:");
		lblIddispositivo.setVisible(true);
		lblIddispositivo.setBounds(500,100,100,25);
		panelDispositivo.add(lblIddispositivo);
		
		txtIddispositivo = new JTextField();
		txtIddispositivo.setVisible(true);
		txtIddispositivo.setBounds(610,100,100,25);
		panelDispositivo.add(txtIddispositivo);
		
		
		
		cmdBuscarDispositivo = new JButton("Buscar");
		cmdBuscarDispositivo.setVisible(true);
		cmdBuscarDispositivo.setBounds(725,97,110,30);
		panelDispositivo.add(cmdBuscarDispositivo);
		
		cmdNuevoDispositivo = new JButton("Nuevo");
		cmdNuevoDispositivo.setVisible(true);
		cmdNuevoDispositivo.setBounds(this.getWidth() - (this.getWidth() - 450) , this.getHeight() - 150, 110, 30);
		panelDispositivo.add(cmdNuevoDispositivo);
		
		cmdBajaDispositivo = new JButton("Baja");
		cmdBajaDispositivo.setVisible(true);
		cmdBajaDispositivo.setBounds(this.getWidth() - (this.getWidth() - 575) , this.getHeight() - 150, 110, 30);
		panelDispositivo.add(cmdBajaDispositivo);
		
		cmdModificarDispositivo = new JButton("Modificar");
		cmdModificarDispositivo.setVisible(true);
		cmdModificarDispositivo.setBounds(this.getWidth() - (this.getWidth() - 700) , this.getHeight() - 150, 110, 30);
		panelDispositivo.add(cmdModificarDispositivo);
		cmdModificarDispositivo.addActionListener(this);
		
		cmdGuardarDispositivo = new JButton("Guardar");
		cmdGuardarDispositivo.setVisible(true);
		cmdGuardarDispositivo.setBounds(this.getWidth() - (this.getWidth() - 825) , this.getHeight() - 150, 110, 30);
		panelDispositivo.add(cmdGuardarDispositivo);
		
		cmdBajaDispositivo.addActionListener(this);
		cmdNuevoDispositivo.addActionListener(this);
		
	}
	/**
	 *  Este metodo hace que cuando clickes el boton de modificar te lleve a la interface de modificar usuario
	 */
	
	
	public void ModificarDispositivo()
	{
		
			panelDispositivo.setVisible(false);
		  	panelDispositivoMod = new JPanel ();
			panelDispositivoMod.setLayout(null);			
			this.setBounds(0,0,this.getWidth(),this.getHeight());
			this.add(panelDispositivoMod);
			this.setTitle("Modificar Dispositivo");
			this.setClosable(true);
			
			cmdAceptarMod = new JButton("Aceptar");
			cmdAceptarMod.setVisible(true);
			cmdAceptarMod.setBounds(this.getWidth() - (this.getWidth() - 800) , this.getHeight() - 150, 110, 30);
			panelDispositivoMod.add(cmdAceptarMod);
    		   	
			cmdCancelarMod = new JButton("Cancelar");
			cmdCancelarMod.setVisible(true);
			cmdCancelarMod.setBounds(this.getWidth() - (this.getWidth() - 1000) , this.getHeight() - 150, 110, 30);
			panelDispositivoMod.add(cmdCancelarMod);
			
			cmdElegirDispMod = new JButton("Elegir Dispositivo");
			cmdElegirDispMod.setVisible(true);
			cmdElegirDispMod.setBounds(725,97,150,30);
			panelDispositivoMod.add(cmdElegirDispMod);
			
			lblIddispositivoMod = new JLabel("ID de Dispositivo:");
			lblIddispositivoMod.setVisible(true);
			lblIddispositivoMod.setBounds(500,100,100,25);
			panelDispositivoMod.add(lblIddispositivoMod);
			
			txtIddispositivoMod = new JTextField();
			txtIddispositivoMod.setVisible(true);
			txtIddispositivoMod.setBounds(610,100,100,25);
			panelDispositivoMod.add(txtIddispositivoMod);
			
			lblModeloMod = new JLabel("Modelo:");
			lblModeloMod.setVisible(false);
			lblModeloMod.setBounds(500,135,100,25);
			panelDispositivoMod.add(lblModeloMod);
			
			txtModeloMod = new JTextField();
			txtModeloMod.setVisible(false);
			txtModeloMod.setBounds(610,135,100,25);
			panelDispositivoMod.add(txtModeloMod);
			
			lblNumSerieMod = new JLabel("Número de Serie:");
			lblNumSerieMod.setVisible(false);
			lblNumSerieMod.setBounds(500,170,100,25);
			panelDispositivoMod.add(lblNumSerieMod);
			
			txtNumSerieMod= new JTextField();
			txtNumSerieMod.setVisible(false);
			txtNumSerieMod.setBounds(610,170,100,25);
			panelDispositivoMod.add(txtNumSerieMod);
			
			cmdCancelarMod.addActionListener(this);
			cmdAceptarMod.addActionListener(this);
			cmdElegirDispMod.addActionListener(this);
	}
	
	public void NuevoDispositivo() {

		cmdGuardarDispositivo = new JButton("Aceptar Alta");
		cmdGuardarDispositivo.setVisible(true);
		cmdGuardarDispositivo.setBounds(this.getWidth() - (this.getWidth() - 800) , this.getHeight() - 200, 110, 30);
		panelDispositivo.add(cmdGuardarDispositivo);
		   	
		cmdCancelarAlta = new JButton("Cancelar");
		cmdCancelarAlta.setVisible(true);
		cmdCancelarAlta.setBounds(this.getWidth() - (this.getWidth() - 1000) , this.getHeight() - 200, 110, 30);
		panelDispositivo.add(cmdCancelarAlta);			
		
		cmdBuscarDispositivo.setVisible(false);
		
		//txtIddispositivo.setBounds(610,100,100,25);
		
		lblModelo = new JLabel("Modelo:");
		lblModelo.setVisible(true);
		lblModelo.setBounds(500, 135, 100, 25);
		panelDispositivo.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setVisible(true);
		txtModelo.setBounds(610,135,100,25);
		panelDispositivo.add(txtModelo);
		
		lblNumSerie = new JLabel("Número de Serie:");
		lblNumSerie.setVisible(true);
		lblNumSerie.setBounds(500, 175, 100, 25);
		panelDispositivo.add(lblNumSerie);	
		
		txtNumSerie = new JTextField();
		txtNumSerie.setVisible(true);
		txtNumSerie.setBounds(610,175,100,25);
		panelDispositivo.add(txtNumSerie);
		
		panelDispositivo.repaint();
		
		cmdGuardarDispositivo.addActionListener(this);
		cmdCancelarAlta.addActionListener(this);
		
	}
	
	public void RellenarDispositivoMod() {
		
		lblModeloMod.setVisible(true);

		txtModeloMod.setVisible(true);
	
		lblNumSerieMod.setVisible(true);
	
		txtNumSerieMod.setVisible(true);
	
		
	}

	

	public void actionPerformed( ActionEvent evento )
	      {
		
		if ( cmdModificarDispositivo == evento.getSource())
		{
			
			ModificarDispositivo();
			
		}
		
		if(cmdCancelarMod == evento.getSource())
		{	 
			
			panelDispositivoMod.setVisible(false);
			panelDispositivo.setVisible(true);
			
		}
		
		if(cmdElegirDispMod == evento.getSource())
		{
			
			if (Dispositivo.validarDatos(txtIddispositivoMod.getText()) == true){
					try {
						if(DispositivoDAO.comprobarDispositivo(txtIddispositivoMod.getText())==true){
							txtIddispositivoMod.setEnabled(false);
							RellenarDispositivoMod();
							//txtModeloMod.setText();
						}
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
						}
					
			}
		}
			if ( cmdBajaDispositivo == evento.getSource())
			{
				
				
				DispositivoDAO a = new DispositivoDAO(txtIddispositivo.getText(),null,null);
				try {
					a.borrarDispositivo();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			if ( cmdNuevoDispositivo == evento.getSource())
			{
				NuevoDispositivo();
			}
			if ( cmdGuardarDispositivo==evento.getSource())
			{
				boolean paso; //booleano que me indicará si he podido convertir el número de serie y el ID de Dispositivo a números enteros
				int numeroSerie = 0; //donde intentaré convertir el número de serie introducido a un número entero
				int numID = 0; //donde intentaré convertir el ID de Dispositivo introducido a un número entero
				
				if (Dispositivo.validarDatos(txtIddispositivo.getText(), txtModelo.getText(), txtNumSerie.getText()) == true){
					//intento convertir el número de serie a un número entero.
					try {
						numeroSerie = Integer.parseInt(txtNumSerie.getText());
						paso = true;
					}
					catch (Exception e1){
						JOptionPane.showMessageDialog(null,"Error convirtiendo el número de serie del dispositivo. Introduce sólo números.");
						paso = false;
					}
					//intento convertir el ID de Dispositivo a un número entero.
					try {
						numID = Integer.parseInt(txtIddispositivo.getText());
						paso = true;
					}
					catch (Exception e1){
						JOptionPane.showMessageDialog(null,"Error convirtiendo el ID de dispositivo. Introduce sólo números.");
						paso = false;
					}
					if (paso==true){
						DispositivoDAO a = new DispositivoDAO(numID+"", txtModelo.getText(), numeroSerie+"");
						try {
							a.altaDispositivo();
							}
						
						catch (SQLException e) {
							e.printStackTrace();
							}
						}
					}
				}
			}
	}





	
	

