package code.google.com.opengis.gestionVISUAL;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GeneradorPanelPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCriterioBusqueda = null;
	private JScrollPane jScrollPane = null;
	private JTable tablaPrincipal = null;
	private JButton bNuevo = null;
	private JButton bModificar = null;
	private JButton bEliminar = null;
	public DefaultTableModel modelo = new DefaultTableModel();
	
	
	/**
	 * This is the default constructor
	 */
	public GeneradorPanelPrincipal() {
		super();
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
			txtCriterioBusqueda = new JTextField("Inserte critério de búsqueda...");
			txtCriterioBusqueda.setBounds(new Rectangle(267, 48, 284, 32));
			txtCriterioBusqueda.setSelectedTextColor(new Color(204, 204, 204));
			txtCriterioBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					
					bEliminar.setEnabled(false);
					bModificar.setEnabled(false); // Desactivamos los botones
					
					buscar();
					
				}
			});
			
			/* Acción de ratón. Cuando clic nos elimina el texto que hay en la caja de texto
			 * Así podemos trabajar de una forma mucho más limpia y eficaz.
			 */
			
			txtCriterioBusqueda.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
					txtCriterioBusqueda.setText("");
					
					
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
			
			tablaPrincipal.setAutoResizeMode(0);
			
			tablaPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {  // Cuando hagan clic...
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
					bModificar.setEnabled(true);
					bEliminar.setEnabled(true);
					
					
					
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
			bNuevo.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/Nuevo.png"));
			bNuevo.setToolTipText("Nuevo");
			
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
	private JButton getBModificar() {
		if (bModificar == null) {
			bModificar = new JButton();
			bModificar.setBounds(new Rectangle(109, 316, 55, 47));
			bModificar.setToolTipText("Modificar");
			bModificar.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/Modificar.png"));
			
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
	private JButton getBEliminar() {
		if (bEliminar == null) {
			bEliminar = new JButton();
			bEliminar.setBounds(new Rectangle(186, 316, 55, 47));
			bEliminar.setToolTipText("Eliminar");
			bEliminar.setIcon(new ImageIcon("OpenGis/src/recursosVisuales/Eliminar.png"));
			
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
	
	public void buscar(){
		
		
		
	}
	
	public void nuevo(){
		
		
	}
	
	public void modificar(){
		
		
	}
	
	public void eliminar(){
		
		
	}
	
	
	

}  //  @jve:decl-index=0:visual-constraint="10,10"