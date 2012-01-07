package code.google.com.opengis.gestionVISUAL;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JFrame;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class InformesVisual extends javax.swing.JPanel {
	private JButton btnCuaderno;
	private JButton btnTrabajador;
	private JButton btnGenerar;
	private JTable tbltabla;
	private JTextField txtBuscador;
	private JLabel jLabel1;
	private JButton btnDispositivos;
	private JButton btnParcela;
	public DefaultTableModel modelo = new DefaultTableModel();

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new InformesVisual());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public InformesVisual() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(811, 402));
			this.setLayout(null);
			{
				btnCuaderno = new JButton();
				this.add(btnCuaderno);
				btnCuaderno.setText("Cuaderno");
				btnCuaderno.setBounds(198, 63, 98, 31);
			}
			{
				btnTrabajador = new JButton();
				this.add(btnTrabajador);
				btnTrabajador.setText("Trabajador");
				btnTrabajador.setBounds(301, 63, 95, 31);
			}
			{
				btnParcela = new JButton();
				this.add(btnParcela);
				btnParcela.setText("Parcela");
				btnParcela.setBounds(401, 63, 90, 31);
			}
			{
				btnDispositivos = new JButton();
				this.add(btnDispositivos);
				btnDispositivos.setText("Dispositivos");
				btnDispositivos.setBounds(496, 63, 94, 31);
			}
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("Selecciona el tipo de informe que va a realizar");
				jLabel1.setBounds(273, 28, 276, 23);
			}
			{
				txtBuscador = new JTextField();
				this.add(txtBuscador);
				txtBuscador.setBounds(285, 115, 242, 33);
				txtBuscador.setEnabled(false);
				
			}
			{
				btnGenerar = new JButton();
				this.add(btnGenerar);
				btnGenerar.setText("Generar Informe");
				btnGenerar.setBounds(273, 360, 223, 31);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
