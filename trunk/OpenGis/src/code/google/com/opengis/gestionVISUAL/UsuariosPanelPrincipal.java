package code.google.com.opengis.gestionVISUAL;

import java.sql.ResultSet;
import java.sql.SQLException;

import code.google.com.opengis.gestionDAO.UsuariosDAO;


public class UsuariosPanelPrincipal extends GeneradorPanelPrincipal {
	
	private int i = 0;
	static Object[] nombreColumna = { "DNI", "Nombre", "Apellidos",
		"Dirección", "Poblacion", "Provincia", "Cod. Postal", "Teléfono",
		"Email", "Fecha Nacimiento", "Tipo", "Estado" };
	
	
	public UsuariosPanelPrincipal(){
		super();
		
	}
	
	
	public void buscar(){
		

		if (i == 0){
			
			i = i + 1; // Ahora la primera ya no és null
			
		}else{
			
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
		
	}
	
	
	public void nuevo(){
		
		
	}
	
	public void modificar(){
		
		
	}
	
	public void eliminar(){
		
		
	}
	

}
