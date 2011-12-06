package code.google.com.opengis.gestionVISUAL;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


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
	private JTextField txtUser;
	private JLabel lblPass;
	private JPasswordField txtPass;
	private JButton btnVal;
	private JButton btnRec;
	private JButton btnBor;
	private JFrame lv=this; //declaramos lv para poder hacer uso de dispose
	private final String rutaIcono="OpenGis/src/recursosVisuales/";


	public LoginVisual(){
		super("Registro de entrada"); //Título de la ventana
		setLayout(new GridBagLayout());
		lblLogo=new JLabel( new ImageIcon(rutaIcono+"logo.png")); //Icono de la aplicación
		lblUser=new JLabel("Usuario:");
		txtUser = new JTextField("Introduzca el DNI ...");
		txtUser.selectAll(); //El texto de la caja seleccionado
		lblPass=new JLabel("Contraseña:");
		txtPass = new JPasswordField();
		btnRec = new JButton("Recuperar contraseña");
		btnVal = new JButton("Validar");
		btnBor = new JButton("Borrar");
		
		KeyListener kl = null;

		GridBagConstraints gbc = new GridBagConstraints();
		
		//Distribución de los elementos

		gbc.gridx=0; //Celda para el logo fila 1, 4 col de ancho
		gbc.gridy=0;
		gbc.gridwidth=4;
		add(lblLogo,gbc);
		
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

		setIconImage(new ImageIcon(rutaIcono+"openGIS.png").getImage()); //icono de la barra de título
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,256);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Monitores de actividad

		btnRec.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				System.out.println("Botón recuperar clave");
				usuario = txtUser.getText();
				new EnviarMail(usuario);
			}
		});

		btnVal.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)	{
				usuario = txtUser.getText();		
				pass = new String(txtPass.getPassword());
				
				validarLogin();
			}
		}); 

		btnBor.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				txtUser.setText("");
				txtPass.setText("");
			}
		}); 
		
		kl=new KeyListener()
		{
			public void keyPressed(KeyEvent evento) {
				Integer l =  evento.getKeyCode();
				//System.out.println(l);
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
	}
	
	private void validarLogin(){
		try 
		{
			char t=ConectarDBA.validarLogin(usuario,pass);
			if ( t!=0)
			{
				
				String idioma = ConectarDBA.idiomaDefecto(usuario);
				
				VentanaPrincipal v=new VentanaPrincipal(t,usuario,idioma);
			
				lv.dispose();
			}
		} 
		catch (SQLException e1) 
		{
			JOptionPane.showMessageDialog(null,"Error De Conexión");
			e1.printStackTrace();
		} 
	}
}
