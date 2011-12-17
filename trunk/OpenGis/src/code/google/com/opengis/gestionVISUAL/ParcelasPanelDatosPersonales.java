package code.google.com.opengis.gestionVISUAL;

import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

public class ParcelasPanelDatosPersonales extends GeneradorPanelPrincipal{

	private String dniUsuario;
	static Object[] columnas={"ID Parcela", "Alias", "Nº Provincia","Nº Población", "Nº Polígono", "Nº Parcela","Nº Partida","DNI del Propietario"};
	private ResultSet rs = null;
	private JButton bSigPac;
	
	public ParcelasPanelDatosPersonales(String dniUsuario) {
		
		super(false);
		super.add(getBSigPac(),null);
		this.dniUsuario = dniUsuario;
		super.txtCriterioBusqueda.setText(dniUsuario);
		super.txtCriterioBusqueda.setEnabled(false);
		buscar();
		
	}
	
	
	public void buscar(){
		
		ConectarDBA dba=null;
		
		try {
			ConectarDBA.acceder();
			modelo.setColumnCount(0);
			modelo.setRowCount(0);
			String Texto = "SELECT `idparcela`, `alias`, `provincia`, `poblacion`, `poligono`, `numero`, `partida`, `dni_propietario` FROM `parcela` WHERE dni_propietario LIKE '"+dniUsuario+"'";
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
	
	public void botonesActivar(){
		
		bSigPac.setEnabled(true);
		
	}
	
	private JButton getBSigPac() {
		if (bSigPac == null) {
			bSigPac = new JButton();
			bSigPac.setBounds(new Rectangle(725, 316, 55, 47));
			bSigPac.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/parcela.png"));
			bSigPac.setEnabled(false);
			bSigPac.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

				
				    NativeInterface.open();
				    	  
				    	  String url = "http://sigpac.mapa.es/fega/salidasgraficas/AspPrintLotProvider.aspx?layer=PARCELA&RCat="
								+getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(),2)+","+getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(),3)+
								",0,0,"+getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(),4)+
								","+getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(),5)+"&visibleLayers=PARCELA;RECINTO;ARBOLES&etiquetas=true";
							
				    	  
							PanelPDF pdf = new PanelPDF(url);
							
							VentanaPrincipal.añadirPestañaNueva("Información Parcela - " + getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(),1),pdf);

				}
			});
			
		}
		return bSigPac;
	}

}
