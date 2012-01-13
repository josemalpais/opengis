package code.google.com.opengis.gestion;

import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import chrriis.dj.nativeswing.NativeSwing;

import code.google.com.opengis.gestionDAO.AperoDAO;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionVISUAL.LoginVisual;
import code.google.com.opengis.gestionVISUAL.VentanaPrincipal;


public class main {


	public static void main(String[] args) throws SQLException{
		
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
		try {
			LoginVisual ln = new LoginVisual();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// esto es para insertar ciudades de manera rapidilla, se borrará cuando acabemos con esto
		/*ConectarDBA.acceder();
		for(int i = 46000; i<46025;i++){
			String sentencia = "INSERT INTO `dai2opengis`.`poblacion`(`idprovincia`, `poblacion`, `poblacionseo`, `postal`, `latitud`, `longitud`) VALUES ('36','Valencia','valencia','"+(i+1)+"','39.470490','-0.378084')";
			ConectarDBA.modificar(sentencia);
			System.out.println("hola");
			
		}
		ConectarDBA.cerrarCon();
		System.out.println("adios");
		*/
}
	
	
	
	
}

