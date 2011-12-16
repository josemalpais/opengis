package code.google.com.opengis.gestionVISUAL;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.DispositivoDAO;
import code.google.com.opengis.gestionDAO.Idioma;




public class DispositivosPanelPrincipal extends GeneradorPanelPrincipal {
	

	static Object[] nombreColumna = { Idioma.getString("etId"), Idioma.getString("etModel"), Idioma.getString("etSerialNumber"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Idioma.getString("etAvailabe"), Idioma.getString("etActive")}; //$NON-NLS-1$ //$NON-NLS-2$
	
	
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

					if (registro[i2].toString().equals("true")) { //$NON-NLS-1$
						registro[i2] = Idioma.getString("etInactive"); //$NON-NLS-1$
					} else if (registro[i2].toString().equals("false")) { //$NON-NLS-1$
						registro[i2] = Idioma.getString("etActive"); //$NON-NLS-1$
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
		
		DispositivosPanelGestion panelNuevo = new DispositivosPanelGestion("alta"); //$NON-NLS-1$
		VentanaPrincipal.añadirPestañaNueva(Idioma.getString("etNewDevice"),panelNuevo); // Añadimos el panel a la pestaña //$NON-NLS-1$
	}
	
	public void modificar(){
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rDisp = new String[5];
			for (int i = 0; i < rDisp.length; i++) {
				rDisp[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
			
		
		DispositivosPanelGestion panelNuevo = new DispositivosPanelGestion("modificar",rDisp[0].toString(),rDisp[1].toString(),rDisp[2].toString()); //$NON-NLS-1$
		VentanaPrincipal.añadirPestañaNueva(Idioma.getString("etModDevice")+"("+rDisp[1].toString()+")",panelNuevo); // Añadimos el panel a la pestaña //$NON-NLS-1$
	
		}
		
	}
	
	public void eliminar(){
		
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rDisp = new String[5];

			for (int i = 0; i < rDisp.length; i++) {
				rDisp[i] = getTablaPrincipal()
						.getValueAt(fila, i).toString();
			}
			try {
				DispositivoDAO.borrarDispositivo(rDisp[0]);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
		
		
	}
	
	
	public void botonesActivar(){
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rDispo = new String[5];
			for (int i = 0; i < rDispo.length; i++) {
				rDispo[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
		
			
			if(rDispo[4].toString().equals(Idioma.getString("etInactive"))){ //$NON-NLS-1$
				
				int resp = JOptionPane.showConfirmDialog(this,Idioma.getString("msgDeviceWithID") + rDispo[0] + Idioma.getString("msgIsInactive"),"",JOptionPane.YES_NO_OPTION); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				
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
