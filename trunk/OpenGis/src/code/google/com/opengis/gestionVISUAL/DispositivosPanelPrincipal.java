package code.google.com.opengis.gestionVISUAL;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.DispositivoDAO;
import code.google.com.opengis.gestionDAO.UsuariosDAO;



public class DispositivosPanelPrincipal extends GeneradorPanelPrincipal {
	

	static Object[] nombreColumna = { "ID", "Modelo", "Número de Serie",
		"Disponible", "Activo"};
	
	
	public DispositivosPanelPrincipal(){
		
		super(false);
		
	}


	
	public void buscar(){
		
		
		String texto = getTxtCriterioBusqueda().getText();
		
		try {
			
			modelo.setColumnCount(0);
			modelo.setRowCount(0);
			
			ResultSet rs = DispositivoDAO.buscarDispositivo(texto);
			int nColumnas = rs.getMetaData().getColumnCount();
			modelo.setColumnIdentifiers(nombreColumna);
			
			while (rs.next()) {
				
				Object[] registro = new Object[nColumnas];

				for (int i = 0; i < nColumnas; i++) {
					registro[i] = rs.getObject(i + 1); // Guardamos todos los registros
					
				}
				
				
				for (int i2 = 0; i2 < registro.length; i2++) {

					if (registro[i2].toString().equals("true")) {
						registro[i2] = "Inactivo";
					} else if (registro[i2].toString().equals("false")) {
						registro[i2] = "Activo";
					}
					//System.out.println(registro[i2]);
				}

				
				modelo.addRow(registro); // Añadimos el registro a la tabla

			}
			rs.close();
		} catch (SQLException e1) {
			System.out.println(e1);

		}
		
		
	}
	


	
	public void nuevo(){
		
		DispositivosPanelGestion panelNuevo = new DispositivosPanelGestion("alta");
		VentanaPrincipal.añadirPestañaNueva("Nuevo Dispositivo",panelNuevo); // Añadimos el panel a la pestaña
	}
	
	public void modificar(){
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rDisp = new String[5];
			for (int i = 0; i < rDisp.length; i++) {
				rDisp[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
			
		
		DispositivosPanelGestion panelNuevo = new DispositivosPanelGestion("modificar",rDisp[0].toString(),rDisp[1].toString(),rDisp[2].toString());
		VentanaPrincipal.añadirPestañaNueva("Modificar Dispositivo",panelNuevo); // Añadimos el panel a la pestaña
	
		}
		
	}
	
	public void eliminar(){
		
		
	}
	
	
	public void botonesActivar(){
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rDispo = new String[5];
			for (int i = 0; i < rDispo.length; i++) {
				rDispo[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
		
			
			if(rDispo[4].toString().equals("Inactivo")){
				
				int resp = JOptionPane.showConfirmDialog(this,"El apero con ID " + rDispo[0] + " está inactivo. ¿Desea activarlo?","",JOptionPane.YES_NO_OPTION);
				
				if(resp==0){
					
					try {
						DispositivoDAO.reactivarDispositivo(rDispo[0]);
						buscar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}else{
			
				getBModificar().setEnabled(true);
				getBEliminar().setEnabled(true);
			
			}
			
		}
	
}
	
}
