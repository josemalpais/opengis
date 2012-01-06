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

public class InformeTrabajador {


static ResultSet rs;
static Document mipdf = new Document(PageSize.LEGAL.rotate());
static String Dni;
static String consulta;
static String consulta2;
static String nombre;
static String apellidos;
static String ppoblacion;
static String ppoligono;
static String pnumero;


static Calendar c = new GregorianCalendar();
private File ruta_destino = null;
static Image imghead = null;
static String fechaini;
static String fechafin;

public InformeTrabajador(String dni) throws SQLException {
	DatosUsuarioParcela(dni,"2011/01/01","2011/12/31");
	
	crear_PDF("Informe Trabajador", "Opengis");

}
public void DatosUsuarioParcela(String dni,String inicio,String fin) {
	fechaini = inicio;
	fechafin = fin; //implementar la consulta para elegir entre 2 fechas
	consulta = "SELECT `usuario`.`dni` , `usuario`.`nombre` , `usuario`.`apellidos` FROM usuario, parcela, parcela_usuario WHERE ((`usuario`.`dni` LIKE '"+dni+"') AND (`parcela`.`idparcela` LIKE `parcela_usuario`.`id_parcela`))";
	consulta2 = "SELECT  `parcela`.`alias` ,`parcela`.`poblacion` , `parcela`.`poligono` , `parcela`.`dni_propietario` , `tareas_realizadas`.`idtarea` , `tareas_realizadas`.`fecha_ini`, `tareas_realizadas`.`fecha_final` FROM parcela, tareas_realizadas WHERE ((`parcela`.`idparcela` LIKE `tareas_realizadas`.`idparcela`)) ";
	ConectarDBA.acceder();
	try {
		
		rs = ConectarDBA.consulta(consulta);
		if (rs.next()) {	
			Dni = rs.getString(1);
			nombre = rs.getString(2);
			apellidos =  rs.getString(3);
			
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
            mipdf.addTitle(titulo); // se añade el titulo
            mipdf.addAuthor(n); // se añade el autor del documento
            mipdf.addSubject(""); //se añade el asunto del documento
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
            mipdf.add(new Paragraph("Nombre: "+nombre, FontFactory.getFont("arial",18,Font.BOLD)));             
            mipdf.add(new Paragraph("Apellidos: "+apellidos, FontFactory.getFont("arial",18,Font.BOLD)));             
            mipdf.add(new Paragraph("Dni: "+Dni, FontFactory.getFont("arial",18,Font.BOLD)));             
            mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));             
            mipdf.add(new Paragraph("Parcelas trabajadas entre "+ fechaini +" y "+fechafin, FontFactory.getFont("arial",18,Font.BOLD)));             
            mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));             
            PdfPTable tabla = new PdfPTable(7);
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell("Nº Parcela");
            tabla.addCell("Término municipal");
            tabla.addCell("Polígono");
            tabla.addCell("Dueño");
            tabla.addCell("Tarea Realizada");
            tabla.addCell("Fecha inicio");
            tabla.addCell("Fecha fin");
           
            try {
				while(rs.next())
				for(int o =1;o<=7;o++){
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
}




}