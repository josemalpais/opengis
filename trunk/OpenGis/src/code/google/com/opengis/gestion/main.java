package code.google.com.opengis.gestion;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import chrriis.dj.nativeswing.NativeSwing;

import code.google.com.opengis.gestionDAO.AperoDAO;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionVISUAL.LoginVisual;
import code.google.com.opengis.gestionVISUAL.VentanaPrincipal;


public class main {


	public static void main(String[] args){
		
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
		
}
	
	
	
	
}

