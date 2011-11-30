package code.google.com.opengis.gestionVISUAL;
import javax.swing.UIManager.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.*;

import code.google.com.opengis.gestion.EnviarMail;
import code.google.com.opengis.gestion.ValidarLogin;
import code.google.com.opengis.gestionDAO.LoginDao;

public class LoginVisual extends JFrame implements KeyListener{
	
	public JFrame ven ;
	private String pass;
	public JTextField txtUser = new JTextField();
	public JPasswordField txtPass = new JPasswordField();
	private JLabel lblUser;
	private JLabel lblPass;
	private JButton btnVal;
	private JButton btnRec;
	private JButton btnBor;
	private String usuario;
	
	
	public LoginVisual(){
		
		
		cargaObjetos();
		
		
	}

	/**
	 * Creamos los botones y demas objetos  el  grindlayout tiene4 filas y 2 columnas
	 * el boton validar saca la pass de estar encriptada a letras normales
	 * llama a la clase ValidarLogin
	 **/
	public void cargaObjetos(){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		ven = this;
		setLayout(new GridLayout(4,2));
		 btnVal = new JButton("Validar");
		 btnBor = new JButton("Borrar");
		 lblUser = new JLabel("Introduce usuario :");
		 lblPass = new JLabel("Introduce la contraseña :");
		 btnRec = new JButton("Recuperar contraseña");
		 btnRec.setBorderPainted(true);
		
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
				pass = new String(txtPass.getPassword());
				usuario = txtUser.getText();
				 
				//System.out.println(usuario+pass);
				 
				 try {
						new ValidarLogin(ven,usuario,pass);
						
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
		
		
		btnRec.addMouseListener(new java.awt.event.MouseListener() {

			////// NO TE ASUSTES DANI! xDD
			///// Le he puesto MouseListener para cuando le cliques cambie el texto del botón a Enviando... y que el usuario no crea que se ha quedado pillado...
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btnRec.setText("Enviando...");
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			
				
				usuario = txtUser.getText();
				
			     new EnviarMail(usuario);
			     
				
				btnRec.setText("Recuperar contraseña");
				
				
			}
		});
			
		
		
		
}
	@Override
	public void keyPressed(KeyEvent evento) {
	Integer l =  evento.getKeyCode();
		//System.out.println(l);
usuario = txtUser.getText();		
pass = new String(txtPass.getPassword());

		if(l == 10){
			 try {
				 
					new ValidarLogin(ven,usuario,pass);
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"Error De Conexión");
					e1.printStackTrace();
				} 
		
		
		}
		}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

    
	   //peta en el array char... XD


}
