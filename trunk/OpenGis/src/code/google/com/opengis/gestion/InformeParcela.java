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

public class InformeParcela {


static ResultSet rs;
static Document mipdf = new Document(PageSize.LEGAL.rotate());
static String consulta;
static String consulta2;
static String dnidueño;
static String alias;
static String provincia;
static String poblacion;
static String poligono;
static String partida;


static Calendar c = new GregorianCalendar();
private File ruta_destino = null;
static Image imghead = null;
static String fechaini;
static String fechafin;

public InformeParcela(String parcela,String fechaini,String fechafin) throws SQLException {
	
	DatosUsuarioParcela(parcela,fechaini,fechafin); //$NON-NLS-1$ //$NON-NLS-2$
	
	crear_PDF(Idioma.getString("_LotReport.Header"), Idioma.getString("_LotReport.Author")); //$NON-NLS-1$ //$NON-NLS-2$

}
public void DatosUsuarioParcela(String parcela,String inicio,String fin) {
	fechaini = inicio;
	fechafin = fin; //implementar la consulta para elegir entre 2 fechas
	consulta = "SELECT `parcela`.`dni_propietario`, `parcela`.`alias`, `parcela`.`provincia`, `poblacion`.`poblacion`, `parcela`.`poligono`, `parcela`.`partida`  FROM usuario, tareas_realizadas, parcela, poblacion	WHERE ((`tareas_realizadas`.`idparcela` LIKE '"+parcela+"') AND (`usuario`.`dni` LIKE `tareas_realizadas`.`dni_usuario`) AND  (`parcela`.`idparcela` LIKE `tareas_realizadas`.`idparcela`) AND (`poblacion`.`idpoblacion` LIKE `parcela`.`poblacion`))"; //$NON-NLS-1$ //$NON-NLS-2$
	
	consulta2 = "SELECT `usuario`.`nombre`, `usuario`.`apellidos`, `usuario`.`dni`, `tareas_realizadas`.`idtarea`, `tareas_realizadas`.`fecha_ini`, `tareas_realizadas`.`fecha_final` FROM usuario, tareas_realizadas, parcela, poblacion WHERE ((`tareas_realizadas`.`fecha_ini` BETWEEN '"+fechaini+"' AND '"+fechafin+"') AND (`tareas_realizadas`.`idparcela` LIKE '"+parcela+"') AND (`usuario`.`dni` LIKE `tareas_realizadas`.`dni_usuario`) AND (`parcela`.`idparcela` LIKE `tareas_realizadas`.`idparcela`) AND (`poblacion`.`idpoblacion` LIKE `parcela`.`poblacion`))"; //$NON-NLS-1$ //$NON-NLS-2$

	ConectarDBA.acceder();
	
	try {
		
		rs = ConectarDBA.consulta(consulta);
		while (rs.next()) {	
			dnidueño = rs.getString(1);
			alias = rs.getString(2);
			provincia =  rs.getString(3);
			poblacion = rs.getString(4);
			poligono = rs.getString(5);
			partida =  rs.getString(6);
			
			
			}
		rs = ConectarDBA.consulta(consulta2);
		

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}






private void crear_PDF(String titulo, String n) {
	Colocar_Destino();
	if(this.ruta_destino!=null){
		try {	
			mipdf = new Document (PageSize.LEGAL.rotate());
			PdfWriter.getInstance(mipdf, new FileOutputStream(this.ruta_destino + ".pdf")).setInitialLeading(6); //$NON-NLS-1$
			mipdf.open();// se abre el documento
            mipdf.addTitle(titulo); // se añade el titulo
            mipdf.addAuthor(n); // se añade el autor del documento
            mipdf.addSubject(""); //se añade el asunto del documento //$NON-NLS-1$
            mipdf.addKeywords(""); //Se agregan palabras claves  //$NON-NLS-1$
            Paragraph nn = new Paragraph(Idioma.getString("_LotReport.Header"), FontFactory.getFont("arial",22,Font.BOLD));     //$NON-NLS-1$ //$NON-NLS-2$
            nn.setAlignment(Element.ALIGN_CENTER);
            mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));              //$NON-NLS-1$ //$NON-NLS-2$
            mipdf.add(nn);
           
          /*  Image foto = Image.getInstance("pingu.png");
            foto.scaleToFit(100, 100);
            foto.setAlignment(Chunk.ALIGN_MIDDLE);
            mipdf.add(foto); 
            */
            mipdf.add(new Paragraph(Idioma.getString("_LotReport.Alias")+ alias, FontFactory.getFont("arial",18,Font.BOLD)));              //$NON-NLS-1$ //$NON-NLS-2$
            mipdf.add(new Paragraph(Idioma.getString("_LotReport.Province")+ provincia, FontFactory.getFont("arial",18,Font.BOLD)));              //$NON-NLS-1$ //$NON-NLS-2$
            mipdf.add(new Paragraph(Idioma.getString("_LotReport.City")+ poblacion, FontFactory.getFont("arial",18,Font.BOLD)));              //$NON-NLS-1$ //$NON-NLS-2$
            mipdf.add(new Paragraph(Idioma.getString("_LotReport.Area")+ poligono, FontFactory.getFont("arial",18,Font.BOLD)));          //$NON-NLS-1$ //$NON-NLS-2$
            mipdf.add(new Paragraph(Idioma.getString("_LotReport.Entry")+ partida, FontFactory.getFont("arial",18,Font.BOLD)));              //$NON-NLS-1$ //$NON-NLS-2$
            mipdf.add(new Paragraph(Idioma.getString("_LotReport.IDOwner")+ dnidueño, FontFactory.getFont("arial",18,Font.BOLD)));              //$NON-NLS-1$ //$NON-NLS-2$
            mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",18,Font.BOLD)));              //$NON-NLS-1$ //$NON-NLS-2$

            mipdf.add(new Paragraph(Idioma.getString("_LotReport.WorkersBetween")+ fechaini +Idioma.getString("_LotReport.And")+fechafin, FontFactory.getFont("arial",18,Font.BOLD)));              //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));              //$NON-NLS-1$ //$NON-NLS-2$
            PdfPTable tabla = new PdfPTable(6);
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(Idioma.getString("_LotReport.WorkerName")); //$NON-NLS-1$
            tabla.addCell(Idioma.getString("_LotReport.LastName")); //$NON-NLS-1$
            tabla.addCell(Idioma.getString("_LotReport.IDCard")); //$NON-NLS-1$
            tabla.addCell(Idioma.getString("_LotReport.TaskTaken")); //$NON-NLS-1$
            tabla.addCell(Idioma.getString("_LotReport.IniDate")); //$NON-NLS-1$
            tabla.addCell(Idioma.getString("_LotReport.EndDate")); //$NON-NLS-1$
            try {
				while(rs.next())
				for(int o =1;o<=6;o++){
				 try {
					tabla.addCell(rs.getString(o));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            mipdf.add(tabla);
            mipdf.close(); //se cierra el PDF&
            JOptionPane.showMessageDialog(null,Idioma.getString("_LotReport.DocCreated")); //$NON-NLS-1$
            
            
		 } catch (DocumentException ex) {
         	JOptionPane.showMessageDialog(null,Idioma.getString("msgSaveError"));
         } catch (FileNotFoundException ex) {
             JOptionPane.showMessageDialog(null,Idioma.getString("msgSaveError"));            	
         }
                
	}
	
}

public void Colocar_Destino() {
	FileNameExtensionFilter filter = new FileNameExtensionFilter(
			Idioma.getString("_LotReport.PDFFile"), "pdf", "PDF"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setFileFilter(filter);
	int result = fileChooser.showSaveDialog(null);
	if (result == JFileChooser.APPROVE_OPTION) {
		this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
	}
}}




