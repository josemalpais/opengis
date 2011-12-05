package pruebasToni;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import code.google.com.opengis.gestionVISUAL.VentanaPrincipal;

public final class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		LoginVisual ln = new LoginVisual();
		//VentanaPrincipal p = new VentanaPrincipal('a');
	}
}
