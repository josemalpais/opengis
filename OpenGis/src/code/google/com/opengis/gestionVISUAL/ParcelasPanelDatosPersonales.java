package code.google.com.opengis.gestionVISUAL;

import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import code.google.com.opengis.gestion.Parcela;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

public class ParcelasPanelDatosPersonales extends GeneradorPanelPrincipal{

	private String dniUsuario;
	static Object[] columnas={Idioma.getString("etIDLot"), Idioma.getString("etAlias"), Idioma.getString("etProvinceNum"),Idioma.getString("etCityNum"), Idioma.getString("etAreaNum"), Idioma.getString("etLotNum"),Idioma.getString("etEntryNum"),Idioma.getString("etIDOwner"),Idioma.getString("etStatus")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
	private ResultSet rs = null;
	private JButton bSigPac;
	
	public ParcelasPanelDatosPersonales(String dniUsuario) {
		
		super(false);
		super.add(getBSigPac(),null);
		super.getBActualizar().setBounds(650, 316, 55, 47);
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
			String Texto = "SELECT `idparcela`, `alias`, `provincia`, `poblacion`, `poligono`, `numero`, `partida`, `dni_propietario`, `activo` FROM `parcela` WHERE dni_propietario LIKE '"+dniUsuario+"'"; //$NON-NLS-1$ //$NON-NLS-2$
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
				
				int resp = JOptionPane.showConfirmDialog(this,"La parcela con Nombre: " + rParcela[1] + Idioma.getString("msgIsInactive"),"",JOptionPane.YES_NO_OPTION); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				
				if(resp==0){
					
					try {
						Parcela.activarParcela(rParcela[0]);
						buscar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}else{
			
				getBModificar().setEnabled(true);
				getBEliminar().setEnabled(true);
				bSigPac.setEnabled(true);
			
			}
			
		}
		

		
		
	}
	
	
	public void nuevo(){
		
		ParcelasPanelNuevo p = new ParcelasPanelNuevo("alta");  //$NON-NLS-1$
		p.txtDniPropietario.setText(dniUsuario);
		p.txtDniPropietario.setEnabled(false);
		VentanaPrincipal.añadirPestañaNueva(Idioma.getString("etNewLot"),p); //$NON-NLS-1$

	}
	
	public void modificar(){
		
		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rParcela = new String[8];
			for (int i = 0; i < rParcela.length; i++) {
				rParcela[i] = getTablaPrincipal().getValueAt(fila, i)
						.toString();
			}
			
			
			ParcelasPanelNuevo p = new ParcelasPanelNuevo("modificar",rParcela[0].toString(),rParcela[1].toString(),rParcela[2].toString(),rParcela[3].toString(),rParcela[4].toString(),rParcela[5].toString(),rParcela[6].toString(),rParcela[7].toString()); // Creamos el panel de Alta de Parcelas //$NON-NLS-1$
			p.txtDniPropietario.setEnabled(false);
			VentanaPrincipal.añadirPestañaNueva("Modificar "+rParcela[1].toString(),p); //$NON-NLS-1$
			
			
		}
		
	
	}
	
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
	
public void eliminar(){
		
		// Recogemos todos los datos de la tabla
		
    	ConectarDBA dba = null;
    	String id=getTablaPrincipal().getValueAt(getTablaPrincipal().getSelectedRow(), 0).toString();
    	ConectarDBA.acceder();
		
			
    	try {
    		int confirmar=JOptionPane.showConfirmDialog(null, Idioma.getString("msgDeleteConfirm")+id); //$NON-NLS-1$
    		if(JOptionPane.OK_OPTION==confirmar){
    			Parcela.bajaParcela(id);
    			buscar();
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

}
