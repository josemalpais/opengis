package pruebasToni;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import code.google.com.opengis.gestion.ValidarLogin;
import code.google.com.opengis.gestionVISUAL.VentanaPrincipal;


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
	private JFrame lv=this;
	//private ImageIcon icono;


	public LoginVisual(){
		super("Registro de entrada");
		setLayout(new GridBagLayout());
		lblLogo=new JLabel( new ImageIcon("OpenGis/src/recursosVisuales/logo.png"));
		lblUser=new JLabel("Usuario:");
		txtUser = new JTextField("Introduzca el DNI ...");
		lblPass=new JLabel("Contraseña:");
		txtPass = new JPasswordField();
		btnRec = new JButton("Recuperar contraseña");
		btnVal = new JButton("Validar");
		btnBor = new JButton("Borrar");
		
		KeyListener kl = null;

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=4;
		add(lblLogo,gbc);
		
		//gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth=1;
		gbc.anchor = GridBagConstraints.EAST;
		add(lblUser,gbc);

		gbc.gridy = 2;
		add(lblPass,gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth=3;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		add(txtUser,gbc);

		gbc.gridy = 2;
		add(txtPass,gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth=2;
		add(btnRec,gbc);

		gbc.gridx = 2;
		gbc.gridwidth=1;
		add(btnVal,gbc);

		gbc.gridx = 3;
		add(btnBor,gbc);

		setIconImage(new ImageIcon("OpenGis/src/recursosVisuales/openGIS.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,256);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		btnRec.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				System.out.println("Botón recuperar clave");
			}
		});

		btnVal.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				System.out.println("Botón validar clave");
				try {
					char t=validarLogin();
					System.out.println(t);
					if ( t!=0)
					{
						VentanaPrincipal v=new VentanaPrincipal(t);
						lv.dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				System.out.println(l);
				usuario = txtUser.getText();		
				pass = new String(txtPass.getPassword());

				if(l == 10)
				{
					try 
					{
						System.out.println("Validar Login. Usuario: "+usuario+" , Clave : "+pass);
						//validarLogin();
						char t=validarLogin();
						System.out.println(t);
						if ( t!=0)
						{
							VentanaPrincipal v=new VentanaPrincipal(t);
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

	public char validarLogin() throws SQLException
	{
		ResultSet rs;
		char t = 0;
		usuario=txtUser.getText();
		pass=new String(txtPass.getPassword());
		//rs=AccesoBBDD.buscar("SELECT tipo FROM usuario WHERE dni = '" + usuario + "' AND password LIKE '" + pass + "' AND 'activo' LIKE '0'");
		rs=AccesoBBDD.buscar("SELECT tipo FROM usuario WHERE dni LIKE '" + usuario + "'AND password LIKE '" + pass + "' AND `activo` = '0'");
		//System.out.println(rs.next());
		//while (rs.next())
		if(rs.next())
		{
			t=rs.getString(1).charAt(0);
			System.out.println("Tipo de usuario: "+t);
		}
		return t;	
	}

}
