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
static String n�serie;



static Calendar c = new GregorianCalendar();
private File ruta_destino = null;
static Image imghead = null;
static String fechaini;
static String fechafin;

public InformeDispositivos(String id) throws SQLException {
	DatosUsuarioParcela(id,"01/01/2011","31/12/2011");
	
	crear_PDF("Informe Trabajador", "Opengis");

}
public void DatosUsuarioParcela(String id,String inicio,String fin) {
	fechaini = inicio;
	fechafin = fin; //implementar la consulta para elegir entre 2 fechas
	consulta = "SELECT `dispositivo`.`modelo` , `dispositivo`.`num_serie` , `dispositivo`.`iddispositivo` FROM dispositivo WHERE ((`dispositivo`.`iddispositivo` LIKE '"+id+"'))";
	consulta2 = "SELECT `usuario`.`nombre`, `usuario`.`apellidos`, `usuario`.`dni` , `prestamo`.`fecha_alquiler` , `prestamo`.`fecha_devol` FROM dispositivo, prestamo, usuario	WHERE ((`dispositivo`.`iddispositivo` LIKE '"+id+"') AND (`prestamo`.`iddispositivo` LIKE `dispositivo`.`iddispositivo`) AND (`usuario`.`dni` LIKE `prestamo`.`dni_usuario`))";
			 	ConectarDBA.acceder();
	
	try {
		
		rs = ConectarDBA.consulta(consulta);
		if (rs.next()) {	
			modelo = rs.getString(1);
			n�serie = rs.getString(2);
			iddispositivo =  rs.getString(3);
			
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
			PdfWriter.getInstance(mipdf, new FileOutputStream(this.ruta_destino + ".pdf")).setInitialLeading(6);
			mipdf.open();// se abre el documento
            mipdf.addTitle(titulo); // se a�ade el titulo
            mipdf.addAuthor(n); // se a�ade el autor del documento
            mipdf.addSubject(""); //se a�ade el asunto del documento
            mipdf.addKeywords(""); //Se agregan palabras claves 
            Paragraph nn = new Paragraph("Informe Trabajador", FontFactory.getFont("arial",22,Font.BOLD));    
            nn.setAlignment(Element.ALIGN_CENTER);
            mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));             
            mipdf.add(nn);
           
          /*  Image foto = Image.getInstance("pingu.png");
            foto.scaleToFit(100, 100);
            foto.setAlignment(Chunk.ALIGN_MIDDLE);
            mipdf.add(foto); 
            */
            mipdf.add(new Paragraph("Modelo: "+ modelo, FontFactory.getFont("arial",22,Font.BOLD)));             
            mipdf.add(new Paragraph("Numero de Serie: "+ n�serie, FontFactory.getFont("arial",22,Font.BOLD)));             
            mipdf.add(new Paragraph("ID dispositivo: "+ iddispositivo, FontFactory.getFont("arial",22,Font.BOLD)));             
                 
            mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));             

            //mipdf.add(new Paragraph("Dispositivos usados entre "+ fechaini +" y "+fechafin, FontFactory.getFont("arial",22,Font.BOLD)));             
            mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));             
            PdfPTable tabla = new PdfPTable(5);
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell("Nombre trabajador");
            tabla.addCell("Apellidos");
            tabla.addCell("Dni");
            tabla.addCell("Fecha inicio");
            tabla.addCell("Fecha final");
            
            try {
				while(rs.next()){
				for(int o =1;o<=5;o++){
				 tabla.addCell(rs.getString(o));
				}}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            mipdf.add(tabla);
            mipdf.close(); //se cierra el PDF&
            JOptionPane.showMessageDialog(null,"Documento PDF creado");
            
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
	}
	
}

public void Colocar_Destino() {
	FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Archivo PDF", "pdf", "PDF");
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setFileFilter(filter);
	int result = fileChooser.showSaveDialog(null);
	if (result == JFileChooser.APPROVE_OPTION) {
		this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
	}
}}



