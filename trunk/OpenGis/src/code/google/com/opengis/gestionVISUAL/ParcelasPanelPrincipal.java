package code.google.com.opengis.gestionVISUAL;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestion.Parcela;
import code.google.com.opengis.gestionDAO.Idioma;
import code.google.com.opengis.gestionDAO.UsuariosDAO;


public class ParcelasPanelPrincipal extends GeneradorPanelPrincipal {
	
	private int i = 0;
	
	static Object[] nombreColumna={"IdParcela", "Alias", "N�mero de Provincia","N�mero de Poblaci�n", "N�mero de Pol�gono", "N�mero de Parcela","N�mero de Partida","DNI del Propietario"};
	
	
	public ParcelasPanelPrincipal(){
		super(true);
		
	}
	
	
	public void buscar(){
		

			
			String texto = getTxtCriterioBusqueda().getText();
			
			
	}

}