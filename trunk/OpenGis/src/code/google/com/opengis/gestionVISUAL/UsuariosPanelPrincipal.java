package code.google.com.opengis.gestionVISUAL;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestion.Usuarios;
import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

public class UsuariosPanelPrincipal extends GeneradorPanelPrincipal {

	static Object[] nombreColumna = {
			Idioma.getString("etIdCard"), Idioma.getString("etFirstName"), Idioma.getString("etLastName"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			Idioma.getString("etAddress"), Idioma.getString("etCity"), Idioma.getString("etProvince"), Idioma.getString("etPostalCode"), Idioma.getString("etPhone"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			Idioma.getString("etMail"), Idioma.getString("etBirthDate"), Idioma.getString("etAccType"), Idioma.getString("etStatus") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	public UsuariosPanelPrincipal() {
		super(true);
		getTxtCriterioBusqueda().setText("");
		buscar();
		getTxtCriterioBusqueda().setText("Inserte criterio de búsqueda...");

	}

	public void buscar() {

		String criterio = getTxtCriterioBusqueda().getText();

		try {

			modelo.setColumnCount(0);
			modelo.setRowCount(0);
			String sentencia = "SELECT `dni`, `nombre`, `apellidos`, `dirección`, `población`, `provincia`, `cp`, `teléfono`, `email`, `fecha_nacimiento`, `tipo`, `activo` FROM `usuario` WHERE dni LIKE '%"
					+ criterio
					+ "%' OR nombre LIKE '%"
					+ criterio
					+ "%' OR apellidos LIKE '%"
					+ criterio
					+ "%' OR dirección LIKE '%"
					+ criterio
					+ "%' OR población LIKE '%"
					+ criterio
					+ "%' OR provincia LIKE '%" + criterio + "%'";
			ResultSet rs = ConectarDBA.buscar(sentencia);
			int nColumnas = rs.getMetaData().getColumnCount();
			modelo.setColumnIdentifiers(nombreColumna);

			while (rs.next()) {

				Object[] registro = new Object[nColumnas];

				for (int i = 0; i < nColumnas; i++) {
					registro[i] = rs.getObject(i + 1); // Guardamos todos los
														// registros

				}

				for (int i2 = 0; i2 < registro.length; i2++) {

					if (registro[i2].toString().equals("true")) { //$NON-NLS-1$
						registro[i2] = Idioma.getString("etActive"); //$NON-NLS-1$
					} else if (registro[i2].toString().equals("false")) { //$NON-NLS-1$
						registro[i2] = Idioma.getString("etInactive"); //$NON-NLS-1$
					}
					// System.out.println(registro[i2]);
				}

				modelo.addRow(registro); // Añadimos el registro a la tabla

			}
			rs.close();
		} catch (SQLException e1) {
			System.out.println(e1);

		}

	}

	public void nuevo() {

		UsuariosPanelNuevo p = new UsuariosPanelNuevo("alta"); // Creamos el panel de Alta de Usuarios //$NON-NLS-1$

		VentanaPrincipal.añadirPestañaNueva(Idioma.getString("etNewUser"), p); // Añadimos el panel a la pestaña //$NON-NLS-1$

	}

	public void modificar() {

		// Recogemos todos los datos de la tabla

		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rUser = new String[10];
			for (int i = 0; i < rUser.length; i++) {
				rUser[i] = getTablaPrincipal().getValueAt(fila, i).toString();
			}

			UsuariosPanelNuevo p = new UsuariosPanelNuevo(
					"modificar", rUser[0].toString(), rUser[1].toString(), rUser[2].toString(), rUser[3].toString(), rUser[4].toString(), rUser[5].toString(), rUser[6].toString(), rUser[7].toString(), rUser[8].toString(), rUser[9].toString()); // Creamos el panel de Alta de Usuarios //$NON-NLS-1$

			VentanaPrincipal
					.añadirPestañaNueva(
							Idioma.getString("etChangeUser") + "(" + rUser[1].toString() + ")", p); // Añadimos el panel a la pestaña //$NON-NLS-1$

		}

	}

	public void eliminar() {

		// Recogemos todos los datos de la tabla

		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rUser = new String[10];
			for (int i = 0; i < rUser.length; i++) {
				rUser[i] = getTablaPrincipal().getValueAt(fila, i).toString();
			}

			try {
				Usuarios.desactivarUsuario(rUser[0]);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public void botonesActivar() {

		int fila = getTablaPrincipal().getSelectedRow();
		if (fila != -1) {
			String[] rUser = new String[12];
			for (int i = 0; i < rUser.length; i++) {
				rUser[i] = getTablaPrincipal().getValueAt(fila, i).toString();
			}

			if (rUser[11].toString().equals(Idioma.getString("etInactive"))) { //$NON-NLS-1$
				Object[] opt = {
						Idioma.getString("etYes"), Idioma.getString("etNo") }; //$NON-NLS-1$ //$NON-NLS-2$
				int resp = JOptionPane
						.showOptionDialog(
								this,
								Idioma.getString("msgUserWithID") + rUser[0] + Idioma.getString("msgIsInactive"), Idioma.getString("msgConfirmDialog"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opt, opt[0]); //$NON-NLS-1$ //$NON-NLS-2$

				if (resp == 0) {

					try {
						Usuarios.activarUsuario(rUser[0]);
						buscar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			} else {

				getBModificar().setEnabled(true);
				getBEliminar().setEnabled(true);

			}

		}

	}

}
