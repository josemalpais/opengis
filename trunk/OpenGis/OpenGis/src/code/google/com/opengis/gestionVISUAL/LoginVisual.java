package code.google.com.opengis.gestionVISUAL;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


import code.google.com.opengis.gestion.EnviarMail;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

/**
 * Ventana del registro de entrada en la aplicación de gestión
 * Se encarga de realizar una consulta contra la base de datos y
 * comprueba que los datos de identificación son correctos.
 * Abre la ventana principal de la aplicación según los privilegios del usuario
 * 
 * @author Toni
 */

public class LoginVisual extends JFrame
{
	private String usuario;
	private String pass;
	private JLabel lblLogo;
	private JLabel lblUser;
	private JFormattedTextField txtUser;
	private JLabel lblPass;
	private JPasswordField txtPass;
	private JButton btnVal;
	private JButton btnRec;
	private JButton btnBor;
	private JButton bIdiomaCatalan;
	private JButton bIdiomaSpanish;
	private JButton bIdiomaEnglish;
	private JPanel pnlBanderas;


	private JFrame lv=this; //declaramos lv para poder hacer uso de dispose
	private final String rutaIcono="OpenGis/src/recursosVisuales/"; //$NON-NLS-1$
	private static String lang = "resources.lang_es_ES"; //$NON-NLS-1$

	public LoginVisual() throws ParseException{
		super(); //Título de la ventana //$NON-NLS-1$
		new Idioma(lang);
		setTitle(Idioma.getString("etLoginWindow"));
		setLayout(new GridBagLayout());
		lblLogo=new JLabel( new ImageIcon(rutaIcono+"logo.png")); //Icono de la aplicación //$NON-NLS-1$
		lblUser=new JLabel(Idioma.getString("etUserLogin")); //$NON-NLS-1$
		MaskFormatter mascara = new MaskFormatter("########L");
		txtUser = new JFormattedTextField(mascara); //$NON-NLS-1$
		txtUser.setText(Idioma.getString("msgInsertIdCard"));
		lblPass=new JLabel(Idioma.getString("etPassword")); //$NON-NLS-1$
		txtPass = new JPasswordField();
		btnRec = new JButton(Idioma.getString("etRecovery")); //$NON-NLS-1$
		btnVal = new JButton(Idioma.getString("etOK")); //$NON-NLS-1$
		btnBor = new JButton(Idioma.getString("etClear")); //$NON-NLS-1$

		bIdiomaCatalan=new JButton();
		bIdiomaCatalan.setPreferredSize(new Dimension(18, 11));
		bIdiomaCatalan.setIcon(new ImageIcon(rutaIcono+"catalan.png")); //$NON-NLS-1$
		bIdiomaCatalan.setToolTipText("Català/Valencià"); //$NON-NLS-1$
		
		bIdiomaSpanish=new JButton();
		bIdiomaSpanish.setPreferredSize(new Dimension(18,11));
		bIdiomaSpanish.setIcon(new ImageIcon(rutaIcono+"Spanish.png")); //$NON-NLS-1$
		bIdiomaSpanish.setToolTipText("Español"); //$NON-NLS-1$
		
		bIdiomaEnglish = new JButton();
		bIdiomaEnglish.setPreferredSize(new Dimension(18,11));
		bIdiomaEnglish.setToolTipText("English"); //$NON-NLS-1$
		bIdiomaEnglish.setIcon(new ImageIcon(rutaIcono+"english.png")); //$NON-NLS-1$

		pnlBanderas=new JPanel(new FlowLayout(FlowLayout.CENTER,7,0));
		pnlBanderas.add(bIdiomaCatalan);
		pnlBanderas.add(bIdiomaEnglish);
		pnlBanderas.add(bIdiomaSpanish);

		KeyListener kl = null;

		GridBagConstraints gbc = new GridBagConstraints();
		
		//Distribución de los elementos

		gbc.gridx=0; //Celda para el logo fila 1, 4 col de ancho
		gbc.gridy=0;
		gbc.gridwidth=4;
		add(lblLogo,gbc);
		
		gbc.gridx=3;
		gbc.gridwidth=2;
		gbc.insets= new Insets(0,7,0,7);
		gbc.anchor = GridBagConstraints.NORTH;
		add(pnlBanderas,gbc);
		
//		gbc.gridx=3;
//		add(bIdiomaEnglish,gbc);
		
//		gbc.gridx=4;
//		add(bIdiomaSpanish,gbc);
		
		gbc.insets= new Insets(0,0,0,0);
		gbc.gridx=0;
		gbc.gridy = 1; //fila 2, col 1, etiqueta usuario alineada a la derecha
		gbc.gridwidth=1;
		gbc.anchor = GridBagConstraints.EAST;
		add(lblUser,gbc);

		gbc.gridy = 2; //fila 3, col 1, 
		add(lblPass,gbc);

		gbc.gridx = 1; //fila 2, col 2, 3 col de ancho
		gbc.gridy = 1;
		gbc.gridwidth=3;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		add(txtUser,gbc);

		gbc.gridy = 2; //fila 3, col 2
		add(txtPass,gbc);

		gbc.gridx = 0; //fila 4, col 1, 2 col de ancho
		gbc.gridy = 3;
		gbc.gridwidth=2;
		add(btnRec,gbc);

		gbc.gridx = 2;
		gbc.gridwidth=1;
		add(btnVal,gbc);

		gbc.gridx = 3;
		add(btnBor,gbc);
		
		//Parámetros de la ventana

		setIconImage(new ImageIcon(rutaIcono+"openGIS.png").getImage()); //icono de la barra de título //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,256);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		txtUser.requestFocus();
		txtUser.selectAll(); //El texto de la caja seleccionado
		
		//Monitores de actividad

		btnRec.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				System.out.println("Botón recuperar clave"); //$NON-NLS-1$
				usuario = txtUser.getText();
				new EnviarMail(usuario, lang);
			}
		});

		btnVal.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)	{
				usuario = txtUser.getText();		
				pass = new String(txtPass.getPassword());
				System.out.println("llamo a validar login"); //$NON-NLS-1$
				validarLogin();
			}
		}); 

		btnBor.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				txtUser.setText(""); //$NON-NLS-1$
				txtPass.setText(""); //$NON-NLS-1$
			}
		}); 
		
		bIdiomaCatalan.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				lang = "resources.lang_ca_ES"; //$NON-NLS-1$
				lv.dispose();
				try {
					new LoginVisual();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		bIdiomaSpanish.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				lang = "resources.lang_es_ES"; //$NON-NLS-1$
				lv.dispose();
				try {
					new LoginVisual();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		bIdiomaEnglish.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				lang = "resources.lang_en_US"; //$NON-NLS-1$
				lv.dispose();
				try {
					new LoginVisual();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		kl=new KeyListener()
		{
			public void keyPressed(KeyEvent evento) {
				
				Integer l =  evento.getKeyCode();
			
				usuario = txtUser.getText();		
				pass = new String(txtPass.getPassword());

				if(l == 10)
				{
					
					validarLogin();
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		};
		
		txtUser.addKeyListener(kl);
		txtPass.addKeyListener(kl);
		
		txtUser.addMouseListener(new java.awt.event.MouseAdapter() { 
			public void mouseClicked(java.awt.event.MouseEvent e) {
				
				txtUser.setText(""); //$NON-NLS-1$
				
				
			}
		});
	}
	
	private void validarLogin(){
		
		if(!validarPass()){
			JOptionPane.showMessageDialog(null,"Contraseña invalida");
			txtPass.setText("");
		}else{
		try 
		{
			
			char t=ConectarDBA.validarLogin(usuario,pass);
			if ( t!=0)
			{
				
				String idioma = ConectarDBA.idiomaDefecto(usuario);
				
				VentanaPrincipal v=new VentanaPrincipal(t,usuario,idioma);
			
				lv.dispose();
			}else{
				JOptionPane.showMessageDialog(null,"Error al introducir el Usuario o Contraseña");
			}
				
		} 
		catch (SQLException e1) 
		{
			JOptionPane.showMessageDialog(null,Idioma.getString("msgConnectionError")); //$NON-NLS-1$
			e1.printStackTrace();
		} 
	}}
	
	private boolean validarPass(){
		String validar = new String(txtPass.getPassword());
		String lol;
		for(int i=0;i<=validar.length()-1;i++){
				System.out.println(validar.substring(i, i+1));
				lol =validar.substring(i, i+1);
			if(lol.equals("'")){
			
				return false;
			}
		}
		return true;
	}
	
}
