package code.google.com.opengis.gestionVISUAL;

import java.sql.ResultSet;
import java.sql.SQLException;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

public class ParcelasPanelDatosPersonales extends GeneradorPanelPrincipal{

	private String dniUsuario;
	static Object[] columnas={"ID Parcela", "Alias", "Nº Provincia","Nº Población", "Nº Polígono", "Nº Parcela","Nº Partida","DNI del Propietario"};
	ResultSet rs = null;
	
	public ParcelasPanelDatosPersonales(String dniUsuario) {
		
		super(false);
		this.dniUsuario = dniUsuario;
		super.txtCriterioBusqueda.setText(dniUsuario);
		super.txtCriterioBusqueda.setEnabled(false);
		super.buscar();
		
	}
	
	
	public void buscar(){
		
		ConectarDBA dba=null;
		
		try {
			ConectarDBA.acceder();
			modelo.setColumnCount(0);
			modelo.setRowCount(0);
			String Texto = "SELECT `idparcela`, `alias`, `provincia`, `poblacion`, `poligono`, `numero`, `partida`, `dni_propietario` FROM `parcela` WHERE (idparcela LIKE '%"+getTxtCriterioBusqueda().getText()+"%' Or alias LIKE '%"
					+getTxtCriterioBusqueda().getText()+"%' Or provincia LIKE '%"+getTxtCriterioBusqueda().getText()+"%' Or  poblacion LIKE '%"+getTxtCriterioBusqueda().getText()+"%' Or poligono LIKE '%"
					+getTxtCriterioBusqueda().getText()+"%' Or  numero LIKE '%"+getTxtCriterioBusqueda().getText()+"%' Or  partida LIKE '%"
					+getTxtCriterioBusqueda().getText()+"%' AND  dni_propietario LIKE '"+ dniUsuario +"' )";
			try{

				rs = dba.consulta(Texto);
			}catch (SQLException e){
				System.out.println(e);
			}
			
			
			int nColumnas = rs.getMetaData().getColumnCount();
			modelo.setColumnIdentifiers(columnas);
			
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
				}

				
				modelo.addRow(registro); // Añadimos el registro a la tabla

			}
			rs.close();
		} catch (SQLException e1) {
			System.out.println(e1);

		}
		
		
	}

}
