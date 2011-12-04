package code.google.com.opengis.gestionVISUAL;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.Idioma;
import code.google.com.opengis.gestionDAO.UsuariosDAO;


public class UsuariosPanelPrincipal extends GeneradorPanelPrincipal {
	
	private int i = 0;
	static Object[] nombreColumna = { "DNI", "Nombre", "Apellidos",
		"Dirección", "Poblacion", "Provincia", "Cod. Postal", "Teléfono",
		"Email", "Fecha Nacimiento", "Tipo", "Estado" };
	
	
	public UsuariosPanelPrincipal(){
		super(true);
		
	}
	
	
	public void buscar(){
		

			
			String texto = getTxtCriterioBusqueda().getText();
			
			
			try {
				
				modelo.setColumnCount(0);
				modelo.setRowCount(0);
				
				ResultSet rs = UsuariosDAO.buscarUsuario(texto);
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
		
		UsuariosPanelNuevo p = new UsuariosPanelNuevo("alta"); // Creamos el panel de Alta de Usuarios
		
		VentanaPrincipal.añadirPestañaNueva("Nuevo Usuario",p); // Añadimos el panel a la pestaña
		
		
	}
	
	public void modificar(){
		
		// Recogemos todos los datos de la tabla
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rUser = new String[10];
			for (int i = 0; i < rUser.length; i++) {
				rUser[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
			
			
			UsuariosPanelNuevo p = new UsuariosPanelNuevo("modificar",rUser[0].toString(),rUser[1].toString(),rUser[2].toString(),rUser[3].toString(),rUser[4].toString(),rUser[5].toString(),rUser[6].toString(),rUser[7].toString(),rUser[8].toString(),rUser[9].toString()); // Creamos el panel de Alta de Usuarios
			
			VentanaPrincipal.añadirPestañaNueva("Modificar Usuario",p); // Añadimos el panel a la pestaña
			
			
		}
		
	
		
	}
	
	public void eliminar(){
		
		// Recogemos todos los datos de la tabla
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rUser = new String[10];
			for (int i = 0; i < rUser.length; i++) {
				rUser[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
		
			
			try {
				UsuariosDAO.DesactivarUsuario(rUser[0]);
				buscar();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
	}
	
	public void botonesActivar(){
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rUser = new String[12];
			for (int i = 0; i < rUser.length; i++) {
				rUser[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
		
			
			if(rUser[11].toString().equals("Inactivo")){
				
				int resp = JOptionPane.showConfirmDialog(this,"El usuario con DNI " + rUser[0] + " está inactivo. ¿Desea activarlo?","",JOptionPane.YES_NO_OPTION);
				
				if(resp==0){
					
					try {
						UsuariosDAO.ActivarUsuario(rUser[0]);
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
