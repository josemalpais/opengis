package code.google.com.opengis.gestion;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.Data;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

import com.lowagie.text.*;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class InformeDispositivos {

	static ResultSet rs;
	static Document mipdf = new Document(PageSize.LEGAL.rotate());
	static String iddispositivo;
	static String consulta;
	static String consulta2;
	static String modelo;
	static String numSerie;

	static Calendar c = new GregorianCalendar();
	private File ruta_destino = null;
	static Image imghead = null;
	static String fechaini;
	static String fechafin;

	public InformeDispositivos(String id, String fechaini, String fechafin)
			throws SQLException {
		DatosUsuarioParcela(id, fechaini, fechafin); //$NON-NLS-1$ //$NON-NLS-2$

		crear_PDF(
				Idioma.getString("_DeviceReport.Header"), Idioma.getString("_DeviceReport.Author")); //$NON-NLS-1$ //$NON-NLS-2$

	}

	public void DatosUsuarioParcela(String id, String inicio, String fin) {
		fechaini = inicio;
		fechafin = fin; // implementar la consulta para elegir entre 2 fechas
		consulta = "SELECT `dispositivo`.`modelo` , `dispositivo`.`num_serie` , `dispositivo`.`iddispositivo` FROM dispositivo WHERE ((`dispositivo`.`iddispositivo` LIKE '" + id + "'))"; //$NON-NLS-1$ //$NON-NLS-2$
		consulta2 = "SELECT `usuario`.`nombre`, `usuario`.`apellidos`, `usuario`.`dni` , `prestamo`.`fecha_alquiler` , `prestamo`.`fecha_devol` FROM dispositivo, prestamo, usuario	WHERE ((`prestamo`.`fecha_alquiler` BETWEEN '"
				+ fechaini
				+ "' AND '"
				+ fechafin
				+ "') AND (`dispositivo`.`iddispositivo` LIKE '"
				+ id
				+ "') AND (`prestamo`.`iddispositivo` LIKE `dispositivo`.`iddispositivo`) AND (`usuario`.`dni` LIKE `prestamo`.`dni_usuario`))";
		ConectarDBA.acceder();
		System.out.println(consulta);
		System.out.println(consulta2);

		try {

			rs = ConectarDBA.consulta(consulta);
			if (rs.next()) {
				modelo = rs.getString(1);
				numSerie = rs.getString(2);
				iddispositivo = rs.getString(3);

			}
			rs = ConectarDBA.consulta(consulta2);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void crear_PDF(String titulo, String n) {
		Colocar_Destino();
		if (this.ruta_destino != null) {
			try {
				mipdf = new Document(PageSize.LEGAL.rotate());
				PdfWriter
						.getInstance(
								mipdf,
								new FileOutputStream(this.ruta_destino + ".pdf")).setInitialLeading(6); //$NON-NLS-1$
				mipdf.open();// se abre el documento
				mipdf.addTitle(titulo); // se añade el titulo
				mipdf.addAuthor(n); // se añade el autor del documento
				mipdf.addSubject(""); //se añade el asunto del documento //$NON-NLS-1$
				mipdf.addKeywords(""); //Se agregan palabras claves  //$NON-NLS-1$
				Paragraph nn = new Paragraph(
						Idioma.getString("_DeviceReport.Header"), FontFactory.getFont("arial", 22, Font.BOLD)); //$NON-NLS-1$ //$NON-NLS-2$
				nn.setAlignment(Element.ALIGN_CENTER);
				mipdf.add(new Paragraph(
						" ", FontFactory.getFont("arial", 22, Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
				mipdf.add(nn);

				/*
				 * Image foto = Image.getInstance("pingu.png");
				 * foto.scaleToFit(100, 100);
				 * foto.setAlignment(Chunk.ALIGN_MIDDLE); mipdf.add(foto);
				 */
				mipdf.add(new Paragraph(
						Idioma.getString("_DeviceReport.Model") + modelo, FontFactory.getFont("arial", 22, Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
				mipdf.add(new Paragraph(
						Idioma.getString("_DeviceReport.SerialNumber") + numSerie, FontFactory.getFont("arial", 22, Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
				mipdf.add(new Paragraph(
						Idioma.getString("_DeviceReport.DeviceID") + iddispositivo, FontFactory.getFont("arial", 22, Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$

				mipdf.add(new Paragraph(
						" ", FontFactory.getFont("arial", 22, Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$

				// mipdf.add(new Paragraph("Dispositivos usados entre "+
				// fechaini +" y "+fechafin,
				// FontFactory.getFont("arial",22,Font.BOLD)));
				mipdf.add(new Paragraph(
						" ", FontFactory.getFont("arial", 22, Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
				PdfPTable tabla = new PdfPTable(5);
				tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
				tabla.addCell(Idioma.getString("_DeviceReport.WorkerName")); //$NON-NLS-1$
				tabla.addCell(Idioma.getString("_DeviceReport.LastName")); //$NON-NLS-1$
				tabla.addCell(Idioma.getString("_DeviceReport.IDCard")); //$NON-NLS-1$
				tabla.addCell(Idioma.getString("_DeviceReport.IniDate")); //$NON-NLS-1$
				tabla.addCell(Idioma.getString("_DeviceReport.EndDate")); //$NON-NLS-1$

				try {
					while (rs.next()) {
						for (int o = 1; o <= 5; o++) {
							tabla.addCell(rs.getString(o));
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mipdf.add(tabla);
				mipdf.close(); // se cierra el PDF&
				JOptionPane.showMessageDialog(null,
						Idioma.getString("_DeviceReport.DocCreated")); //$NON-NLS-1$

			} catch (DocumentException ex) {
				JOptionPane.showMessageDialog(null,
						Idioma.getString("msgSaveError"));
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null,
						Idioma.getString("msgSaveError"));
			}

		}

	}

	public void Colocar_Destino(){
	       FileNameExtensionFilter filter = new FileNameExtensionFilter(Idioma.getString("_BookReport.PDFFile"),"pdf","PDF"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	       JFileChooser fileChooser = new JFileChooser();       
	       fileChooser.setFileFilter(filter);
	       Date fecha = new Date();
	       int dia = fecha.getDate();
	       int mes = fecha.getMonth()+1;
	       int anyo = fecha.getYear() + 1900;
	       String fechaFinal = dia +"-"+mes+"-"+anyo;
	       File f = new File(Idioma.getString("_DeviceReport.Header")+" "+fechaFinal);
	       fileChooser.setSelectedFile(f);
	       int result = fileChooser.showSaveDialog(fileChooser);
	       if ( result == JFileChooser.APPROVE_OPTION ){   
	           this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
	        }
	    }
}
