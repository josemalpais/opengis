package code.google.com.opengis.gestionVISUAL;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestion.Parcela;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;
import code.google.com.opengis.gestionDAO.UsuariosDAO;


public class ParcelasPanelPrincipal extends GeneradorPanelPrincipal {
	
	private int i = 0;
	
	static Object[] columnas={"ID Parcela", "Alias", "Nº Provincia","Nº Población", "Nº Polígono", "Nº Parcela","Nº Partida","DNI del Propietario"};
	ResultSet rs = null;
	
	public ParcelasPanelPrincipal(){
		super(false);
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
						+getTxtCriterioBusqueda().getText()+"%' Or  dni_propietario LIKE '%"+getTxtCriterioBusqueda().getText()+"%' ) AND  activo <> '0'";
				try{
					System.out.println("Ejecutada sentencia "+ Texto);
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
	
	public void nuevo(){
		ParcelasPanelNuevo p = new ParcelasPanelNuevo("alta"); 
		
		VentanaPrincipal.añadirPestañaNueva("Nueva Parcela",p);
		
	}

	public void modificar(){
		
		// Recogemos todos los datos de la tabla
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rParcela = new String[8];
			for (int i = 0; i < rParcela.length; i++) {
				rParcela[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
			
			
			ParcelasPanelNuevo p = new ParcelasPanelNuevo("modificar",rParcela[0].toString(),rParcela[1].toString(),rParcela[2].toString(),rParcela[3].toString(),rParcela[4].toString(),rParcela[5].toString(),rParcela[6].toString(),rParcela[7].toString()); // Creamos el panel de Alta de Parcelas
			
			VentanaPrincipal.añadirPestañaNueva("Nueva Parcela",p);
			
			
		}
		
	
		
	}
	
	public void eliminar(){
		
		// Recogemos todos los datos de la tabla
		
    	ConectarDBA dba = null;
    	String id=getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(), 0).toString();
    	ConectarDBA.acceder();
		
			
    	try {
    		int confirmar=JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el registro "+id);
    		if(JOptionPane.OK_OPTION==confirmar){
			dba.modificar("UPDATE `dai2opengis`.`parcela` SET `activo`= 0 WHERE `idparcela` LIKE "+id);
			JOptionPane.showMessageDialog(null,"El numero de registro "+id+" ha sido desactivado correctamente.");
    		}
    	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
    	try {
			dba.cerrarCon();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	

	/**
	 * Método que nos permitira rellenar la tabla con los registros que correspondan
	 * @param criterio: Criterio de busqueda para la consulta sql
	 */
	public void llenar(String criterio){
		try {
			int x=0;
			int y=0;
				modelo.setColumnCount(0);
				modelo.setRowCount(0);
				modelo.setColumnIdentifiers(columnas);
			
				ResultSet rs = Parcela.buscar(criterio);
				while (rs.next()) {
					Object[]fila = {rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5),
						rs.getObject(6), rs.getObject(7), rs.getObject(8)};
					modelo.addRow(fila);
				}
				rs.close();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}