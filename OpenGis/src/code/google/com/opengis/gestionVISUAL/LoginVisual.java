package code.google.com.opengis.gestionVISUAL;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import code.google.com.opengis.gestion.EnviarMail;
import code.google.com.opengis.gestion.ValidarLogin;


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
		
		ven = this;
		setLayout(new GridBagLayout());
		 btnVal = new JButton("Validar");
		 btnBor = new JButton("Borrar");
		 lblUser = new JLabel("Introduce usuario :");
		 lblPass = new JLabel("Introduce la contraseña :");
		 btnRec = new JButton("Recuperar contraseña");
		 btnRec.setBorderPainted(true);
		 
		 
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(2,4, 2, 4);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		add(lblUser,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(txtUser,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(lblPass,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(txtPass,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(btnVal,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(btnBor,gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(new Label(""),gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(btnRec,gbc);
		txtUser.addKeyListener(this);
		txtPass.addKeyListener(this);
		setSize(350,170);
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
