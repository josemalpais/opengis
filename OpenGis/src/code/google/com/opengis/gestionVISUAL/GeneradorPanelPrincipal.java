package code.google.com.opengis.gestionVISUAL;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import code.google.com.opengis.gestion.Parcela;
import code.google.com.opengis.gestionDAO.Idioma;

public class GeneradorPanelPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	protected JTextField txtCriterioBusqueda = null;
	private JScrollPane jScrollPane = null;
	private JTable tablaPrincipal = null;
	protected JButton bNuevo = null;
	private JButton bModificar = null;
	private JButton bEliminar = null;
	public DefaultTableModel modelo = new DefaultTableModel();
	
	private boolean redimensionarTabla;
	
	/**
	 * This is the default constructor
	 */
	public GeneradorPanelPrincipal(boolean redimensionarTabla) {
		super();
		this.redimensionarTabla = redimensionarTabla;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(null);
		this.setBounds(new Rectangle(0, 0, 811, 402));
		this.add(getTxtCriterioBusqueda(), null);
		this.add(getJScrollPane(), null);
		this.add(getBNuevo(), null);
		this.add(getBModificar(), null);
		this.add(getBEliminar(), null);
	}

	/**
	 * This method initializes txtCriterioBusqueda	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtCriterioBusqueda() {
		if (txtCriterioBusqueda == null) {
			txtCriterioBusqueda = new JTextField(Idioma.getString("msgSearchCriteria")); //$NON-NLS-1$
			txtCriterioBusqueda.setBounds(new Rectangle(267, 48, 284, 32));
			txtCriterioBusqueda.setSelectedTextColor(new Color(204, 204, 204));
			txtCriterioBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					
					bEliminar.setEnabled(false);
					bModificar.setEnabled(false); // Desactivamos los botones
					
					txtCriterioBusqueda.setText(txtCriterioBusqueda.getText());
					
					buscar();
					
				}
			});
			
			/* Acción de ratón. Cuando clic nos elimina el texto que hay en la caja de texto
			 * Así podemos trabajar de una forma mucho más limpia y eficaz.
			 */
			
			txtCriterioBusqueda.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
					txtCriterioBusqueda.setText(""); //$NON-NLS-1$
					
					
				}
			});
		}
		return txtCriterioBusqueda;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(30, 101, 750, 204));
			jScrollPane.setViewportView(getTablaPrincipal());
		}
		return jScrollPane;
	}

	
	/**
	 * This method initializes tablaPrincipal	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public final JTable getTablaPrincipal() {
		if (tablaPrincipal == null) {
			tablaPrincipal = new JTable(modelo){
				
			    public boolean isCellEditable(int row, int column) {  // La tabla no será editable
				       return false;
				    }
			};
			
			if (redimensionarTabla==true){
			
				tablaPrincipal.setAutoResizeMode(0);
				
			}
			
			tablaPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {  // Cuando hagan clic...
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
					
					botonesActivar();
					
					
				}
			});
			
		}
		return tablaPrincipal;
	}

	/**
	 * This method initializes bNuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBNuevo() {
		if (bNuevo == null) {
			bNuevo = new JButton();
			bNuevo.setBounds(new Rectangle(33, 316, 55, 47));
			bNuevo.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/Nuevo.png")); //$NON-NLS-1$
			bNuevo.setToolTipText(Idioma.getString("etNew")); //$NON-NLS-1$
			
			bNuevo.setEnabled(true);
			bNuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					nuevo();
					
				}
			});
			
		}
		return bNuevo;
	}

	/**
	 * This method initializes bModificar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBModificar() {
		if (bModificar == null) {
			bModificar = new JButton();
			bModificar.setBounds(new Rectangle(109, 316, 55, 47));
			bModificar.setToolTipText(Idioma.getString("etModify")); //$NON-NLS-1$
			bModificar.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/Modificar.png")); //$NON-NLS-1$
			
			bModificar.setEnabled(false);
			bModificar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					modificar();
				}
			});
		}
		return bModificar;
	}

	/**
	 * This method initializes bEliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBEliminar() {
		if (bEliminar == null) {
			bEliminar = new JButton();
			bEliminar.setBounds(new Rectangle(186, 316, 55, 47));
			bEliminar.setToolTipText(Idioma.getString("etDelete")); //$NON-NLS-1$
			bEliminar.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/Eliminar.png")); //$NON-NLS-1$
			bEliminar.setEnabled(false);
			bEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {			
					eliminar();
				}
			});
		}
		return bEliminar;
	}
	
	
	/*
	 * Declaramos méotods para sustituirlos con polimorfismo en la clase que se herede
	 * 
	 */
	
	public void rellenarTabla(String criterio,String[]columnas, Object[]filax){
		
			try {
				int x=0;
				int y=0;
				modelo.setColumnCount(0);
					modelo.setRowCount(0);
					modelo.setColumnIdentifiers(columnas);
				
					ResultSet rs = Parcela.buscar(criterio);
					while (rs.next()) {
						Object[]fila =filax;
						modelo.addRow(fila);
					}
					rs.close();		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	
	
	public void buscar(){
		
	}
	
	public void nuevo(){
		
		
	}
	
	public void modificar(){
		
		
	}
	
	public void eliminar(){
		
		
	}
	
	
	public void botonesActivar(){
		
		bModificar.setEnabled(true);
		bEliminar.setEnabled(true);
		
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"