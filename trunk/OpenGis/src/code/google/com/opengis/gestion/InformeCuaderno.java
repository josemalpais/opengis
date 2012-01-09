package code.google.com.opengis.gestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import sun.tools.jar.Main;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;



public class InformeCuaderno {
	static Calendar c = new GregorianCalendar();   
	private File ruta_destino = null;
	static Document mipdf = new Document (PageSize.LEGAL.rotate());
	static String espacio = "                           "; //$NON-NLS-1$
	static String espacio2 = "               "; //$NON-NLS-1$
	static  String year = Integer.toString(c.get(Calendar.YEAR));
	static String linea = "_____________________________________________"; //$NON-NLS-1$

	static String Dni;
	static ResultSet rs;
	

	static String nombre;
	static int x;
	static String consulta;
	static String consulta2;
	static String consulta3;
	static String consulta4;
	
	public InformeCuaderno(String dni) throws SQLException{
		Dni = dni;
		DatosUsuarioParcela(dni, "01/01/2011","31/12/2012" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		crear_PDF(Idioma.getString("_BookReport.Title"),Idioma.getString("_BookReport.Author"),"",""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		
		
	}
	
	public void DatosUsuarioParcela(String dni,String inicio,String fin) {
		String finicio = inicio;
		String ffin = fin; //implementar la consulta para elegir entre 2 fechas
		consulta = "SELECT `usuario`.`dni` , `usuario`.`nombre` , `usuario`.`apellidos` , `parcela`.`poblacion` , `parcela`.`poligono` , `parcela`.`numero` , `parcela_usuario`.`dni_usuario` , `parcela_usuario`.`id_parcela`FROM usuario, parcela, parcela_usuario WHERE ((`usuario`.`dni` LIKE '"+dni+"') AND (`parcela`.`idparcela` LIKE `parcela_usuario`.`id_parcela`))"; //$NON-NLS-1$ //$NON-NLS-2$
		consulta2 ="SELECT  `parcela`.`poblacion` , `parcela`.`poligono` , `parcela`.`alias` FROM usuario, parcela, parcela_usuario WHERE ((`usuario`.`dni` LIKE '"+dni+"') AND (`parcela`.`idparcela` LIKE `parcela_usuario`.`id_parcela`))"; //$NON-NLS-1$ //$NON-NLS-2$
		consulta3 = "SELECT `parcela`.`poligono` , `parcela`.`alias` , `tareas_realizadas`.`fecha_ini` FROM tareas_realizadas, parcela WHERE ((`tareas_realizadas`.`dni_usuario` LIKE '"+dni+"') AND (`tareas_realizadas`.`idtarea` LIKE '1') AND (`tareas_realizadas`.`idparcela` LIKE `parcela`.`idparcela`))"; //$NON-NLS-1$ //$NON-NLS-2$
		consulta4 = "SELECT  `parcela`.`alias`,`producto`.`nombre` ,`tareas_realizadas`.`dosis` , `tareas_realizadas`.`fecha_ini`,`producto`.`descripcion` FROM tareas_realizadas, parcela,producto WHERE ((`tareas_realizadas`.`dni_usuario` LIKE '"+dni+"') AND (`tareas_realizadas`.`idtarea` LIKE '5') AND (`tareas_realizadas`.`idparcela` LIKE `parcela`.`idparcela`) AND (`tareas_realizadas`.`idprod` LIKE `producto`.`idprod`))"; //$NON-NLS-1$ //$NON-NLS-2$

		ConectarDBA.acceder();
		
		try {
			
			rs = ConectarDBA.consulta(consulta);		
			if (rs.next()) {	
				Dni = rs.getString(1);
				nombre = rs.getString(2);
				nombre = nombre +" "+ rs.getString(3); //$NON-NLS-1$
				
				
		}
			
			
			


			
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void crear_PDF(String t, String a, String s, String k) throws SQLException{
       
        Colocar_Destino();
        
        if(this.ruta_destino!=null){
            try {
                // se crea instancia del documento
               // 
            	mipdf = new Document (PageSize.LEGAL.rotate());              
                PdfWriter.getInstance(mipdf, new FileOutputStream(this.ruta_destino + ".pdf")).setInitialLeading(6); //$NON-NLS-1$
		                mipdf.open();// se abre el documento
		                mipdf.addTitle(t); // se añade el titulo
		                mipdf.addAuthor(a); // se añade el autor del documento
		                mipdf.addSubject(s); //se añade el asunto del documento
		                mipdf.addKeywords(k); //Se agregan palabras claves 
		            Paragraph n = new Paragraph(Idioma.getString("_BookReport.Header"), FontFactory.getFont("arial",22,Font.BOLD));     //$NON-NLS-1$ //$NON-NLS-2$
		            n.setAlignment(Element.ALIGN_CENTER);
		            Paragraph n1 = new Paragraph(year, FontFactory.getFont("arial",30,Font.BOLD));     //$NON-NLS-1$
		            n1.setAlignment(Element.ALIGN_CENTER);
		            Paragraph n2 = new Paragraph(Idioma.getString("_BookReport.SubHeader"), FontFactory.getFont("arial",18,Font.BOLD));    //$NON-NLS-1$ //$NON-NLS-2$
		            n2.setAlignment(Element.ALIGN_CENTER);
		            Paragraph n3 = new Paragraph(Idioma.getString("_BookReport.SubHeader2"), FontFactory.getFont("arial",16,Font.BOLD));    //$NON-NLS-1$ //$NON-NLS-2$
		            n3.setAlignment(Element.ALIGN_CENTER);    
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));              //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(n);
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));              //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(n1);
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));              //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(n2);
		                 mipdf.add(n3);             
		                 
                 mipdf.newPage(); // segunda pagina
                 
                 
                 
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Section1"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.FullName")+nombre, FontFactory.getFont("arial",13,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.IDCard")+Dni, FontFactory.getFont("arial",13,Font.BOLD)));   //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Responsible")+linea, FontFactory.getFont("arial",13,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Cooperative")+ linea, FontFactory.getFont("arial",13,Font.BOLD)));   //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Section2"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ")); //$NON-NLS-1$
			                 PdfPTable tabla = new PdfPTable(8);
			                 tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
			                 tabla.addCell(Idioma.getString("_BookReport.OrderNum")); //$NON-NLS-1$
			                 tabla.addCell(Idioma.getString("_BookReport.Municipality")); //$NON-NLS-1$
			                 tabla.addCell(Idioma.getString("_BookReport.Area")); //$NON-NLS-1$
		                     tabla.addCell(Idioma.getString("_BookReport.Lot")); //$NON-NLS-1$
		                     tabla.addCell(Idioma.getString("_BookReport.Enclosure")); //$NON-NLS-1$
		                     tabla.addCell(Idioma.getString("_BookReport.Sown")); //$NON-NLS-1$
			                 tabla.addCell(Idioma.getString("_BookReport.Cultivation")); //$NON-NLS-1$
			                 tabla.addCell(Idioma.getString("_BookReport.Notes")); //$NON-NLS-1$
			                 int orden= 1;
			                 tabla.addCell(orden+""); //$NON-NLS-1$
			                 int con = 1;
			                 rs = ConectarDBA.consulta(consulta2);	
			                while(rs.next()){
			                	for(int i = 1; i <=3;i++){
			                		con++;
			                		tabla.addCell(rs.getString(i));
			                	}
			                	for(int aa = 1;aa<=4;aa++){
			                		tabla.addCell(""); //$NON-NLS-1$
			                		con++;
			                	}
			                	if(con == 8){
			                		orden ++;
			                		tabla.addCell(orden+""); //$NON-NLS-1$
			                		con = 1;
			                	}
			                }
			                
			                 
			                 
		                 mipdf.add(tabla);
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Append1"), FontFactory.getFont("arial",8,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 
                 mipdf.newPage(); // tercera pagina
                 
                 
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Section3"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
			                 PdfPTable tabla1 = new PdfPTable(7);
			                 tabla1.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla1.addCell(Idioma.getString("_BookReport.LotOrder")); //$NON-NLS-1$
			                 tabla1.addCell(Idioma.getString("_BookReport.WaterIncMonth")); //$NON-NLS-1$
			                 tabla1.addCell(Idioma.getString("_BookReport.WaterOutMonth")); //$NON-NLS-1$
			                 tabla1.addCell(" "); //$NON-NLS-1$
			                 tabla1.addCell(Idioma.getString("_BookReport.LotOrder")); //$NON-NLS-1$
			                 tabla1.addCell(Idioma.getString("_BookReport.WaterIncMonth")); //$NON-NLS-1$
			                 tabla1.addCell(Idioma.getString("_BookReport.WaterOutMonth")); //$NON-NLS-1$
			                 for(int o =0;o<=49;o++){
			                	 tabla1.addCell(" "); //$NON-NLS-1$
			                 }
			             mipdf.add(tabla1);
                 
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Section4"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.QuagmireOrMilling"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
			                 PdfPTable tabla2 = new PdfPTable(4);
			                 tabla2.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla2.addCell(Idioma.getString("_BookReport.Area")); //$NON-NLS-1$
			                 tabla2.addCell(Idioma.getString("_BookReport.Lot")); //$NON-NLS-1$
			                 tabla2.addCell(Idioma.getString("_BookReport.Date")); //$NON-NLS-1$
			                 tabla2.addCell(Idioma.getString("_BookReport.Notes")); //$NON-NLS-1$
			                 con =1;	
			                 rs =ConectarDBA.consulta(consulta3);
			                 while(rs.next()){
			                  for(int o = 1; o<=3;o++){
			                	  tabla2.addCell(rs.getString(o));
			                   if(con == 4){
			                	   tabla2.addCell(con+""); //$NON-NLS-1$
			                	   con =1;
			                   }
			                 }
			                 }
		                 mipdf.add(tabla2);
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Append2"), FontFactory.getFont("arial",8,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 
                 mipdf.newPage(); //cuarta pagina
                 
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.FarmWork"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
			                 PdfPTable tabla3 = new PdfPTable(4);
			                 tabla3.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla3.addCell(Idioma.getString("_BookReport.LotOrder")); //$NON-NLS-1$
			                 tabla3.addCell(Idioma.getString("_BookReport.WorkMade")); //$NON-NLS-1$
			                 tabla3.addCell(Idioma.getString("_BookReport.Dates")); //$NON-NLS-1$
			                 tabla3.addCell(Idioma.getString("_BookReport.Notes")); //$NON-NLS-1$
			                 for(int o =0;o<=36;o++){
			                	 tabla3.addCell(" "); //$NON-NLS-1$
			                 }
			             mipdf.add(tabla3);
                 
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Leveling"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
			                 PdfPTable tabla4 = new PdfPTable(7);
			                 tabla4.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla4.addCell(Idioma.getString("_BookReport.LotOrder")); //$NON-NLS-1$
			                 tabla4.addCell(Idioma.getString("_BookReport.Dates")); //$NON-NLS-1$
			                 tabla4.addCell(Idioma.getString("_BookReport.Notes")); //$NON-NLS-1$
			                 tabla4.addCell(" "); //$NON-NLS-1$
			                 tabla4.addCell(Idioma.getString("_BookReport.LotOrder")); //$NON-NLS-1$
			                 tabla4.addCell(Idioma.getString("_BookReport.Date")); //$NON-NLS-1$
			                 tabla4.addCell(Idioma.getString("_BookReport.Notes")); //$NON-NLS-1$
			                 for(int o =0;o<=49;o++){
			                	 tabla4.addCell(" "); //$NON-NLS-1$
			                 }
		                 mipdf.add(tabla4);
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Append2"), FontFactory.getFont("arial",8,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                
                 mipdf.newPage(); // quinta pagina 
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Section5"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.QuagmireOrMilling"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
			                 PdfPTable tabla5 = new PdfPTable(6);
			                 tabla5.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla5.addCell(Idioma.getString("_BookReport.LotOrder")); //$NON-NLS-1$
			                 tabla5.addCell(Idioma.getString("_BookReport.Variety")); //$NON-NLS-1$
			                 tabla5.addCell(Idioma.getString("_BookReport.SeedQuality")); //$NON-NLS-1$
			                 tabla5.addCell(Idioma.getString("_BookReport.DoseExtend")); //$NON-NLS-1$
			                 tabla5.addCell(Idioma.getString("_BookReport.SowingDate")); //$NON-NLS-1$
			                 tabla5.addCell(Idioma.getString("_BookReport.Notes")); //$NON-NLS-1$
			                
			                 for(int o =0;o<=60;o++){
			                	 tabla5.addCell(" "); //$NON-NLS-1$
			                 }
		                 mipdf.add(tabla5);
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Append2"), FontFactory.getFont("arial",8,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.AppendSeed"), FontFactory.getFont("arial",8,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Section6"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
			                 PdfPTable tabla6 = new PdfPTable(5);
			                 tabla6.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla6.addCell(Idioma.getString("_BookReport.LotOrder")); //$NON-NLS-1$
			                 tabla6.addCell(Idioma.getString("_BookReport.Product")); //$NON-NLS-1$
			                 tabla6.addCell(Idioma.getString("_BookReport.Dose")); //$NON-NLS-1$
			                 tabla6.addCell(Idioma.getString("_BookReport.Date")); //$NON-NLS-1$
			                 tabla6.addCell(Idioma.getString("_BookReport.Note")); //$NON-NLS-1$
			                 rs=ConectarDBA.consulta(consulta4);
			                 while(rs.next()){
			                	for(int o = 1;o <=5;o++){
			                		tabla6.addCell(rs.getString(o));
			                	} 
			                 }
			                 		                 
		                 mipdf.add(tabla6);
		                 
                 mipdf.newPage();//sexta pagina
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Section7"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
			                 PdfPTable tabla7 = new PdfPTable(7);
			                 tabla7.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla7.addCell(Idioma.getString("_BookReport.LotOrder")); //$NON-NLS-1$
			                 tabla7.addCell(Idioma.getString("_BookReport.FertilizingType")); //$NON-NLS-1$
			                 tabla7.addCell(Idioma.getString("_BookReport.Nitrate")); //$NON-NLS-1$
			                 tabla7.addCell(Idioma.getString("_BookReport.Phosphorus")); //$NON-NLS-1$
			                 tabla7.addCell(Idioma.getString("_BookReport.Potassium")); //$NON-NLS-1$
			                 tabla7.addCell(Idioma.getString("_BookReport.Date")); //$NON-NLS-1$
			                 tabla7.addCell(Idioma.getString("_BookReport.Note")); //$NON-NLS-1$
			                
			                 for(int o =0;o<=70;o++){
			                	 tabla7.addCell(" "); //$NON-NLS-1$
			                 }
		                 mipdf.add(tabla7);
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Append2"), FontFactory.getFont("arial",8,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Section8"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
			                 PdfPTable tabla8 = new PdfPTable(4);
			                 tabla8.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla8.addCell(Idioma.getString("_BookReport.LotOrder")); //$NON-NLS-1$
			                 tabla8.addCell(Idioma.getString("_BookReport.Date")); //$NON-NLS-1$
			                 tabla8.addCell(Idioma.getString("_BookReport.Performance")); //$NON-NLS-1$
			                 tabla8.addCell(Idioma.getString("_BookReport.Notes")); //$NON-NLS-1$
			                
			                 for(int o =0;o<=20;o++){
			                	 tabla8.addCell(" "); //$NON-NLS-1$
			                 }
		                mipdf.add(tabla8);
		                mipdf.add(new Paragraph(Idioma.getString("_BookReport.Append2"), FontFactory.getFont("arial",8,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 
                 mipdf.newPage();//septima pagina
                 
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Section9"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
			                 PdfPTable tabla9 = new PdfPTable(6);
			                 tabla9.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla9.addCell(Idioma.getString("_BookReport.AnalysisType")); //$NON-NLS-1$
			                 tabla9.addCell(Idioma.getString("_BookReport.Sample")); //$NON-NLS-1$
			                 tabla9.addCell(Idioma.getString("_BookReport.SampleDate")); //$NON-NLS-1$
			                 tabla9.addCell(Idioma.getString("_BookReport.ReferenceNum")); //$NON-NLS-1$
			                 tabla9.addCell(Idioma.getString("_BookReport.Result")); //$NON-NLS-1$
			                 tabla9.addCell(Idioma.getString("_BookReport.Notes")); //$NON-NLS-1$
			                
			                 for(int o =0;o<=26;o++){
			                	 if(o==0)
			                		 tabla9.addCell(Idioma.getString("_BookReport.Water"));  //$NON-NLS-1$
			                	 if(o==5)
			                		 tabla9.addCell(Idioma.getString("_BookReport.Ground")); //$NON-NLS-1$
			                	 if(o==10)
			                		 tabla9.addCell(Idioma.getString("_BookReport.Harvest")); //$NON-NLS-1$
			                	 tabla9.addCell(" "); //$NON-NLS-1$
			                 }
			                 
		                 mipdf.add(tabla9);
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Section10"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
			                 PdfPTable tabla10 = new PdfPTable(5);
			                 tabla10.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla10.addCell(Idioma.getString("_BookReport.Date")); //$NON-NLS-1$
			                 tabla10.addCell(Idioma.getString("_BookReport.Organization")); //$NON-NLS-1$
			                 tabla10.addCell(Idioma.getString("_BookReport.TechnicSign")); //$NON-NLS-1$
			                 tabla10.addCell(Idioma.getString("_BookReport.HolderSign")); //$NON-NLS-1$
			                 tabla10.addCell(Idioma.getString("_BookReport.Notes")); //$NON-NLS-1$
			                
			                 for(int o =0;o<=25;o++){
			                	 
			                	 tabla10.addCell(" "); //$NON-NLS-1$
			                 }
                 
			             mipdf.add(tabla10);
                 
                 mipdf.newPage();
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.Shut"), FontFactory.getFont("arial",14,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.ShutText1")+year, FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.ShutText2"), FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(espacio+espacio+espacio+espacio+espacio+Idioma.getString("_BookReport.SigningDate"), FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(Idioma.getString("_BookReport.StampText")+espacio+espacio+espacio+Idioma.getString("_BookReport.BookHolder"), FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$
		                 mipdf.add(new Paragraph(espacio+Idioma.getString("_BookReport.Stamp")+espacio+espacio+espacio+espacio+Idioma.getString("_BookReport.Signing"), FontFactory.getFont("arial",12,Font.BOLD))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		


                 mipdf.close(); //se cierra el PDF&
                JOptionPane.showMessageDialog(null,Idioma.getString("_BookReport.DocCreated")); //$NON-NLS-1$
            
            } catch (DocumentException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }        
    }
	
	 /* abre la ventana de dialogo GUARDAR*/
    public void Colocar_Destino(){
       FileNameExtensionFilter filter = new FileNameExtensionFilter(Idioma.getString("_BookReport.PDFFile"),"pdf","PDF"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
       JFileChooser fileChooser = new JFileChooser();       
       fileChooser.setFileFilter(filter);
       int result = fileChooser.showSaveDialog(null);
       if ( result == JFileChooser.APPROVE_OPTION ){   
           this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
        }
    } 
}
