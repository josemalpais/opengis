package code.google.com.opengis.gestionVISUAL;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class JInternalFramePrincipal extends JInternalFrame {
		JPanel panelactual;
		int ancho;
		int alto;
		String nombre;
	
	public JInternalFramePrincipal(String nombre){
		this.add(panelactual);
		this.setBounds(0,0,ancho,alto);
		this.setTitle(nombre);
		this.setClosable(true);
	}
}
