package code.google.com.opengis.gestionVISUAL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.bcel.internal.generic.DDIV;

import code.google.com.opengis.gestionDAO.ConectarDBA;

public class AperoVisual extends JInternalFrame implements ActionListener {

	private static final String border = null;
	private JTextField txtBuscar, txtIdApero, txtNomApero, txtTamApero,
			txtDescApero;
	private JComboBox jcbTareaApero;
	private JButton btnBuscar, btnCrear, btnModificar, btnDesactivar,
			btnConfirmar, btnCancelar;
	private JLabel lblBuscar, lblId, lblNom, lblTam, lblDesc, lblTarea;
	private DefaultTableModel dtm;
	private JTable aperos;
	private JScrollPane scroll;
	private JPanel jp;
	private final String[] titulos = { "ID", "Nombre", "Tamaño", "Descripcion",
			"Tarea" };
	ConectarDBA dba = new ConectarDBA();
	private JPanel panelBorde;

	/**
	 * C O N S T R U C T O R
	 */
	public AperoVisual(int ancho, int alto) {
		super("Aperos", false, true, true, true);
		// super("Gestion de Academia");
		// System.out.println("Creado Apero Visual");
		this.setBounds(new Rectangle(0, 2, ancho, alto));
		this.contenido();
		panelBorde.setVisible(false);

	}

	private void contenido() {

		// D E C L A R A C I O N D E C O M P O N E N T E S
		// LABELS
		lblBuscar = new JLabel();
		lblBuscar.setText("Buscar:");
		lblId = new JLabel();
		lblId.setText("ID:");
		lblNom = new JLabel();
		lblNom.setText("Nombre:");
		lblDesc = new JLabel();
		lblDesc.setText("Descripcion:");
		lblTam = new JLabel();
		lblTam.setText("Tamaño:");
		lblTarea = new JLabel();
		lblTarea.setText("Tarea:");

		// COMBOBOX TAREA
		jcbTareaApero = new JComboBox();
		dba.acceder();
		String senten = new String("SELECT * FROM tareas");
		ResultSet rs;
		try {
			rs = dba.consulta(senten);
			while (rs.next()) {
				jcbTareaApero.addItem("" + rs.getObject(1) + " - "
						+ rs.getObject(2) + " " + rs.getObject(3) + " "
						+ rs.getObject(4));
			}
			rs.close();
			dba.cerrarCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * @author kAStRo Aqui debe haber un metodo que rellene el combobox
		 */

		// BUTTONS
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(this);
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnDesactivar = new JButton("Desactivar");
		btnDesactivar.addActionListener(this);
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(this);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);

		// TEXTFIELDS
		txtBuscar = new JTextField();
		txtIdApero = new JTextField();
		txtNomApero = new JTextField();
		txtTamApero = new JTextField();
		txtDescApero = new JTextField();
		txtDescApero.setAlignmentY(javax.swing.SwingConstants.BOTTOM);

		// BORDE AÑADIR / MODIFICAR
		panelBorde = new JPanel();
		panelBorde.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		panelBorde.add(lblId, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		panelBorde.add(lblNom, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		panelBorde.add(lblDesc, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panelBorde.add(lblTam, gbc);

		gbc.gridx = 3;
		gbc.gridy = 3;
		panelBorde.add(lblTarea, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		Dimension tamtxt = new Dimension(100, 20);
		txtIdApero.setPreferredSize(tamtxt);
		panelBorde.add(txtIdApero, gbc);

		gbc.gridx = 4;
		gbc.gridy = 1;
		tamtxt = new Dimension(150, 20);
		txtNomApero.setPreferredSize(tamtxt);
		panelBorde.add(txtNomApero, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		tamtxt = new Dimension(100, 20);
		txtTamApero.setPreferredSize(tamtxt);
		panelBorde.add(txtTamApero, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 4;
		gbc.gridheight = 2;
		tamtxt = new Dimension(300, 60);
		txtDescApero.setPreferredSize(tamtxt);
		panelBorde.add(txtDescApero, gbc);

		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		tamtxt = new Dimension(150, 20);
		jcbTareaApero.setPreferredSize(tamtxt);
		panelBorde.add(jcbTareaApero, gbc);

		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 3;
		gbc.gridy = 7;
		tamtxt = new Dimension(100, 20);
		btnConfirmar.setPreferredSize(tamtxt);
		panelBorde.add(btnConfirmar, gbc);

		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 4;
		gbc.gridy = 7;
		btnCancelar.setPreferredSize(tamtxt);
		panelBorde.add(btnCancelar, gbc);

		// PANEL PRINCIPAL
		jp = new JPanel();
		jp.setLayout(new GridBagLayout());
		GridBagConstraints gbc2 = new GridBagConstraints();

		gbc2.gridx = 1;
		gbc2.gridy = 1;
		gbc2.gridwidth = 1;
		gbc2.gridheight = 1;
		gbc2.weightx = 1.0;
		gbc2.weighty = 1.0;
		Dimension tamtxt2 = new Dimension(100, 20);
		lblBuscar.setPreferredSize(tamtxt2);
		gbc2.anchor = GridBagConstraints.EAST;
		jp.add(lblBuscar, gbc2);

		gbc2.gridx = 2;
		gbc2.gridy = 1;
		gbc2.gridwidth = 3;
		gbc2.gridheight = 1;
		tamtxt2 = new Dimension(400, 20);
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		txtBuscar.setPreferredSize(tamtxt2);
		jp.add(txtBuscar, gbc2);

		gbc2.gridx = 5;
		gbc2.gridy = 1;
		gbc2.gridwidth = 1;
		gbc2.gridheight = 1;
		tamtxt2 = new Dimension(100, 20);
		gbc2.anchor = GridBagConstraints.WEST;
		gbc2.fill = GridBagConstraints.NONE;
		btnBuscar.setPreferredSize(tamtxt2);
		jp.add(btnBuscar, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 8;
		gbc2.gridwidth = 1;
		gbc2.gridheight = 1;
		gbc2.anchor = GridBagConstraints.EAST;
		btnCrear.setPreferredSize(tamtxt2);
		jp.add(btnCrear, gbc2);

		gbc2.gridx = 2;
		gbc2.gridy = 8;
		gbc2.gridwidth = 1;
		gbc2.gridheight = 1;
		gbc2.anchor = GridBagConstraints.WEST;
		btnModificar.setPreferredSize(tamtxt2);
		jp.add(btnModificar, gbc2);

		gbc2.gridx = 4;
		gbc2.gridy = 8;
		gbc2.gridwidth = 1;
		gbc2.gridheight = 1;
		gbc2.anchor = GridBagConstraints.EAST;
		btnDesactivar.setPreferredSize(tamtxt2);
		jp.add(btnDesactivar, gbc2);

		TitledBorder jb = new TitledBorder("Añadir / Modificar");
		int ancho = jp.getWidth();
		int alto = jp.getHeight();
		panelBorde.setBorder(jb);

		gbc2.gridx = 1;
		gbc2.gridy = 10;
		gbc2.gridwidth = 5;
		gbc2.gridheight = 5;
		gbc2.anchor = GridBagConstraints.CENTER;
		gbc2.fill = GridBagConstraints.BOTH;
		gbc2.insets = new Insets(0, 50, 10, 50);
		jp.add(panelBorde, gbc2);

		// TABLA DE DATOS
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(titulos);
		aperos = new JTable(dtm);
		// jp.add(aperos);
		aperos.setBounds(70, 120, 500, 150);
		scroll = new JScrollPane(aperos);
		scroll.setBounds(70, 120, 650, 200);
		aperos.setEnabled(false);
		gbc2.gridx = 1;
		gbc2.gridy = 3;
		gbc2.gridwidth = 5;
		gbc2.gridheight = 5;
		jp.add(scroll, gbc2);
		this.add(jp);
		// llenar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCrear) {
			panelBorde.setVisible(true);
		} else if (e.getSource() == btnCancelar) {
			panelBorde.setVisible(false);
		} else if (e.getSource() == btnModificar) {
			panelBorde.setVisible(true);
		} else if (e.getSource() == btnConfirmar) {

		} else if (e.getSource() == btnDesactivar) {

		} else if (e.getSource() == btnBuscar) {

		}

	}
}