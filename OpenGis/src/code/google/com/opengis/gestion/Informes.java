package code.google.com.opengis.gestion;


import java.util.HashMap;
import java.util.Map;

import code.google.com.opengis.gestionDAO.ConectarDBA;

import com.mysql.jdbc.Connection;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
public class Informes {

	  static Connection conn = null;
	  static String direccion = "C:\\report2.jrxml";
	  static String direccion2 = "C:\\prueba.pdf";
	public Informes(){
		generar();
		
	}
	
	
	
	public void generar(){
		try {
			 Map parameters = new HashMap();
		      parameters.put("OpenGis", "Informes");
		      parameters.put("Fecha", new java.util.Date());
			
		      JasperReport report = JasperCompileManager.compileReport(direccion);
			JasperPrint print = JasperFillManager.fillReport(report,parameters);
			JasperExportManager.exportReportToPdfFile(print,direccion2 );
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			
			e.printStackTrace();
		}
	}

}
