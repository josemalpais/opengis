package code.google.com.opengis.gestionVISUAL;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

import code.google.com.opengis.gestion.ValidarLogin;

public class LoginVisual extends JFrame{
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
		JLabel lblRec = new JLabel("RECUPERAR CONTRASEÑA");
		final JTextField txtUser = new JTextField();
		final JPasswordField txtPass = new JPasswordField();
		
		add(lblUser);
		add(txtUser);
		add(lblPass);
		add(txtPass);
		add(btnVal);
		add(btnBor);
		add(new Label(""));
		add(lblRec);
		setSize(330,150);
		setTitle("Ventana Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	
		btnVal.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String pass;
				String user;
				char passArray[] = txtPass.getPassword();
		        for (int i = 0; i < passArray.length; i++) {
		            char c = passArray[i];
				
					}
		         pass = new String(passArray);
				 user = txtUser.getText();

		        
				try {
					new ValidarLogin(user,pass);
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
			
}
	}
