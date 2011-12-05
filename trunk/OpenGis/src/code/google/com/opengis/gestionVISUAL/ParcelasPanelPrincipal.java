package code.google.com.opengis.gestionVISUAL;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestion.Parcela;
import code.google.com.opengis.gestionDAO.Idioma;
import code.google.com.opengis.gestionDAO.UsuariosDAO;


public class ParcelasPanelPrincipal extends GeneradorPanelPrincipal {
	
	private int i = 0;
	
	static Object[] nombreColumna={"IdParcela", "Alias", "Número de Provincia","Número de Población", "Número de Polígono", "Número de Parcela","Número de Partida","DNI del Propietario"};
	
	
	public ParcelasPanelPrincipal(){
		super(true);
		
	}
	
	
	public void buscar(){
		

			
			String texto = getTxtCriterioBusqueda().getText();
			
			
	}

}