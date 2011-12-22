package code.google.com.opengis.gestionVISUAL;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.Idioma;
import code.google.com.opengis.gestion.Producto;


public class ProductoPanelPrincipal extends GeneradorPanelPrincipal{

	static Object[] nombreColumna = {Idioma.getString("etId"),Idioma.getString("etName"),Idioma.getString("etDesc"),Idioma.getString("etType"),Idioma.getString("etAmount"),Idioma.getString("etPropietary"),Idioma.getString("etStatus")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
	
	
	
	public ProductoPanelPrincipal() {
		super(false);

	}
	
	public void buscar(){
		
		
		String texto = getTxtCriterioBusqueda().getText();
		
		try {
			
			modelo.setColumnCount(0);
			modelo.setRowCount(0);
			
			ResultSet rs = Producto.buscar(texto);
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
				}

				
				modelo.addRow(registro); // Añadimos el registro a la tabla

			}
			rs.close();
		} catch (SQLException e1) {
			System.out.println(e1);

		}
		
	}

	
	public void nuevo(){
		
	    ProductoPanelGestion p = new ProductoPanelGestion("alta"); // Creamos el panel de Alta de Usuarios //$NON-NLS-1$
		
		VentanaPrincipal.añadirPestañaNueva(Idioma.getString("etNewProduct"),p); // Añadimos el panel a la pestaña //$NON-NLS-1$
		
		
	}
	
	
	public void modificar(){
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rProducto = new String[7];
			for (int i = 0; i < rProducto.length; i++) {
				rProducto[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
			
			
			ProductoPanelGestion p = new ProductoPanelGestion("modificar",rProducto[0].toString(),rProducto[1].toString(),rProducto[2].toString(),rProducto[3].toString(),rProducto[4].toString(),rProducto[5].toString()); // Creamos el panel de Alta de Usuarios //$NON-NLS-1$
			
			VentanaPrincipal.añadirPestañaNueva(Idioma.getString("etModifyProduct")+"("+rProducto[1].toString()+")",p); // Añadimos el panel a la pestaña //$NON-NLS-1$
		}
		
	}
	
	public void eliminar(){
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rProducto = new String[7];
			for (int i = 0; i < rProducto.length; i++) {
				rProducto[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
		
			
			try {
				Producto.desactivarProducto(rProducto[0]);
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
			String[] rProducto = new String[7];
			for (int i = 0; i < rProducto.length; i++) {
				rProducto[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
		
			
			if(rProducto[6].equals(Idioma.getString("etInactive"))){ //$NON-NLS-1$
				
				int resp = JOptionPane.showConfirmDialog(this,Idioma.getString("msgProductWithID") + rProducto[0] + Idioma.getString("msgIsInactive"),"",JOptionPane.YES_NO_OPTION); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				
				if(resp==0){
					
					try {
						
						Producto.activarProducto(rProducto[0]);
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
