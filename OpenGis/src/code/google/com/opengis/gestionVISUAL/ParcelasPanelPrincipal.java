package code.google.com.opengis.gestionVISUAL;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import code.google.com.opengis.gestion.Parcela;
import code.google.com.opengis.gestion.Usuarios;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection; 
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class ParcelasPanelPrincipal extends GeneradorPanelPrincipal {
	private JButton bSigPac = null;
	private int i = 0;
	
	static Object[] columnas={Idioma.getString("etIDLot"), Idioma.getString("etAlias"), Idioma.getString("etProvinceNum"),Idioma.getString("etCityNum"), Idioma.getString("etAreaNum"), Idioma.getString("etLotNum"),Idioma.getString("etEntryNum"),Idioma.getString("etIDOwner"), Idioma.getString("etStatus")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
	ResultSet rs = null;
	
	public ParcelasPanelPrincipal(){
		super(false);
		super.add(getBSigPac(),null);
		super.getBActualizar().setBounds(650, 316, 55, 47);
		getTxtCriterioBusqueda().setText("");
		buscar();
		getTxtCriterioBusqueda().setText("Inserte criterio de búsqueda...");
	}
	
	
	public void buscar(){
		ConectarDBA dba=null;
			
			try {
				ConectarDBA.acceder();
				modelo.setColumnCount(0);
				modelo.setRowCount(0);
				String Texto = "SELECT `idparcela`, `alias`, `provincia`, `poblacion`, `poligono`, `numero`, `partida`, `dni_propietario`, `activo` FROM `parcela` WHERE (idparcela LIKE '%"+getTxtCriterioBusqueda().getText()+"%' Or alias LIKE '%" //$NON-NLS-1$ //$NON-NLS-2$
						+getTxtCriterioBusqueda().getText()+"%' Or provincia LIKE '%"+getTxtCriterioBusqueda().getText()+"%' Or  poblacion LIKE '%"+getTxtCriterioBusqueda().getText()+"%' Or poligono LIKE '%" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						+getTxtCriterioBusqueda().getText()+"%' Or  numero LIKE '%"+getTxtCriterioBusqueda().getText()+"%' Or  partida LIKE '%" //$NON-NLS-1$ //$NON-NLS-2$
						+getTxtCriterioBusqueda().getText()+"%' Or  dni_propietario LIKE '%"+getTxtCriterioBusqueda().getText()+"%' )"; //$NON-NLS-1$ //$NON-NLS-2$
				try{
					System.out.println("Ejecutada sentencia "+ Texto); //$NON-NLS-1$
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
							registro[i2] = Idioma.getString("etActive"); //$NON-NLS-1$
						} else if (registro[i2].toString().equals("false")) { //$NON-NLS-1$
							registro[i2] = Idioma.getString("etInactive"); //$NON-NLS-1$
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
		ParcelasPanelNuevo p = new ParcelasPanelNuevo("alta");  //$NON-NLS-1$
		
		VentanaPrincipal.añadirPestañaNueva(Idioma.getString("etNewLot"),p); //$NON-NLS-1$
		
	}

	public void modificar(){
		
		
		// Recogemos todos los datos de la tabla
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rParcela = new String[9];
			for (int i = 0; i < rParcela.length; i++) {
				rParcela[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
			
		
		
		if(rParcela[8].toString().equals(Idioma.getString("etActive"))){
		

			
			ParcelasPanelNuevo p = new ParcelasPanelNuevo("modificar",rParcela[0].toString(),rParcela[1].toString(),rParcela[2].toString(),rParcela[3].toString(),rParcela[4].toString(),rParcela[5].toString(),rParcela[6].toString(),rParcela[7].toString()); // Creamos el panel de Alta de Parcelas //$NON-NLS-1$
			
			VentanaPrincipal.añadirPestañaNueva(Idioma.getString("etModifyLot")+"("+rParcela[1].toString()+")",p); //$NON-NLS-1$
			
			
		
		
		}else{
			
			
			Object[] opt = {Idioma.getString("etYes"), Idioma.getString("etNo") }; //$NON-NLS-1$ //$NON-NLS-2$
			 int resp = JOptionPane.showOptionDialog(this,Idioma.getString("etLotWithName") + rParcela[0] + Idioma.getString("msgIsInactive"), Idioma.getString("msgConfirmDialog"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opt, opt[0]); //$NON-NLS-1$ //$NON-NLS-2$
			 if (resp == 0) {
			 try {
			 Parcela.activarParcela(rParcela[0]);
			 txtCriterioBusqueda.setText("");
			 buscar();
			 } catch (SQLException e1) {
			 // TODO Auto-generated catch block
			 e1.printStackTrace();}
			 }	
			
			
		}
		
		}
	
		
	}
	
	public void eliminar(){
		
		// Recogemos todos los datos de la tabla
		
    	ConectarDBA dba = null;
    	String id=getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(), 0).toString();
    	String nombre=getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(), 1).toString();
    	ConectarDBA.acceder();
		
			
    	try {
    		Object [] opt = {Idioma.getString("etYes"),Idioma.getString("etNo")}; //$NON-NLS-1$
    		int confirmar=JOptionPane.showOptionDialog(null, Idioma.getString("msgDeleteConfirm")+" - " + nombre,"",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, opt, opt[0] ); //$NON-NLS-1$
    		if(JOptionPane.OK_OPTION==confirmar){
    			Parcela.bajaParcela(id);
    			txtCriterioBusqueda.setText("");
    			buscar();
				 getBModificar().setEnabled(false);
				 getBEliminar().setEnabled(false);
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
	
	
	
	/**
	 * This method initializes bNuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBSigPac() {
		if (bSigPac == null) {
			bSigPac = new JButton();
			bSigPac.setBounds(new Rectangle(725, 316, 55, 47));
			bSigPac.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/parcela.png")); //$NON-NLS-1$
			bSigPac.setEnabled(false);
			bSigPac.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

				
				    NativeInterface.open();
				    	  
				    	  String url = "http://sigpac.mapa.es/fega/salidasgraficas/AspPrintLotProvider.aspx?layer=PARCELA&RCat=" //$NON-NLS-1$
								+getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(),2)+","+getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(),3)+ //$NON-NLS-1$
								",0,0,"+getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(),4)+ //$NON-NLS-1$
								","+getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(),5)+"&visibleLayers=PARCELA;RECINTO;ARBOLES&etiquetas=true"; //$NON-NLS-1$ //$NON-NLS-2$
							
				    	  
							PanelPDF pdf = new PanelPDF(url);
							
							VentanaPrincipal.añadirPestañaNueva(Idioma.getString("etLotInfo") + getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(),1),pdf); //$NON-NLS-1$

				}
			});
			
		}
		return bSigPac;
	}
	public void botonesActivar(){
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rParcela = new String[9];
			for (int i = 0; i < rParcela.length; i++) {
				rParcela[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
		
			
			
			if(rParcela[8].toString().equals(Idioma.getString("etInactive"))){ //$NON-NLS-1$
				
				getBModificar().setEnabled(false);
				getBEliminar().setEnabled(false);
				getBSigPac().setEnabled(false);
				
				 Object[] opt = {Idioma.getString("etYes"), Idioma.getString("etNo") }; //$NON-NLS-1$ //$NON-NLS-2$
				 int resp = JOptionPane.showOptionDialog(this,Idioma.getString("etLotWithName") + rParcela[0] + Idioma.getString("msgIsInactive"), Idioma.getString("msgConfirmDialog"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opt, opt[0]); //$NON-NLS-1$ //$NON-NLS-2$
				 if (resp == 0) {
				 try {
				 Parcela.activarParcela(rParcela[0]);
				 txtCriterioBusqueda.setText("");
				 buscar();
				 } catch (SQLException e1) {
				 // TODO Auto-generated catch block
				 e1.printStackTrace();}
				 }	
				 }else{	
					 
				 getBModificar().setEnabled(true);
				 getBEliminar().setEnabled(true);
				 getBSigPac().setEnabled(true);

				 }
			
		}
		

		
		
	}


}