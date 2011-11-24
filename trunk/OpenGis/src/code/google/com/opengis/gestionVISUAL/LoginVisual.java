package code.google.com.opengis.gestionVISUAL;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.*;

import code.google.com.opengis.gestion.EnviarMail;
import code.google.com.opengis.gestion.ValidarLogin;
import code.google.com.opengis.gestionDAO.LoginDao;

public class LoginVisual extends JFrame implements KeyListener{
	public LoginVisual(){
		JFrame ven = new JFrame();
		cargaObjetos();
		
	}
	
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JLabel lblUser;
	private JLabel lblPass;
	private JLabel lblRec;
	private JButton btnVal;
	private JButton btnBor;
	static String usuario;
	static String contraseña;

	
	public void pulsar(KeyEvent evento)
    {
      String g = "" + evento.getKeyText( evento.getKeyCode() );
       System.out.println(g);
    }
	/**
	 * Creamos los botones y demas objetos  el  grindlayout tiene4 filas y 2 columnas
	 * el boton validar saca la pass de estar encriptada a letras normales
	 * llama a la clase ValidarLogin
	 **/
	public void cargaObjetos(){

		setLayout(new GridLayout(4,2));
		JButton btnVal = new JButton("Validar");
		JButton btnBor = new JButton("Borrar");
		JLabel lblUser = new JLabel("Introduce usuario :");
		JLabel lblPass = new JLabel("Introduce la contraseña :");
		JButton btnRec = new JButton("recuperar contraseña");
		btnRec.setBorderPainted(false);
		final JTextField txtUser = new JTextField();
		final JPasswordField txtPass = new JPasswordField();
		add(lblUser);
		add(txtUser);
		add(lblPass);
		add(txtPass);
		add(btnVal);
		add(btnBor);
		add(new Label(""));
		add(btnRec);
		txtUser.addKeyListener(this);
		txtPass.addKeyListener(this);
		setSize(330,150);
		setTitle("Ventana Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	
		btnVal.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				//COPY PASTE
				
				String pass = new String(txtPass.getPassword());
				System.out.println(pass);
				 usuario = txtUser.getText();
				 try {
						new ValidarLogin(usuario,pass);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,"Error De Conexión");
						e1.printStackTrace();
					} 
				
				
			}
		});
		
		btnBor.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
			txtUser.setText("");
			txtPass.setText("");
			
			}
		});
		btnRec.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				usuario = txtUser.getText();
				
			     new EnviarMail(usuario);
				
					
			}
		});
			
		
		
		
		
}
	@Override
	public void keyPressed(KeyEvent evento) {
	/*	int l =  evento.getKeyCode();
		System.out.println(l);
		if(l == 10){

			String pass = new String(txtPass.getPassword());
			System.out.println(pass);
			 usuario = txtUser.getText();
			 try {
					new ValidarLogin(usuario,pass);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"Error De Conexión");
					e1.printStackTrace();
				} 
		
		}*/
		}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

    
	   //peta en el array char... XD


}
