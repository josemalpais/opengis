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
	static String espacio = "                           ";
	static String espacio2 = "               ";
	static  String year = Integer.toString(c.get(Calendar.YEAR));
	static String linea = "_____________________________________________";

	static String Dni;
	static ResultSet rs;
	static ResultSet rs2;
	static ResultSet rs3;
	static ResultSet rs4;

	static String nombre;
	static int x;
	static String consulta;
	static String consulta2;
	static String consulta3;
	static String consulta4;
	
	public InformeCuaderno(String dni) throws SQLException{
		Dni = dni;
		DatosUsuarioParcela("44876647j", "01/01/2011","31/12/2012" );
		crear_PDF("Informe Cuaderno","Opengis","","");
		
		
	}
	
	public void DatosUsuarioParcela(String dni,String inicio,String fin) {
		String finicio = inicio;
		String ffin = fin; //implementar la consulta para elegir entre 2 fechas
		consulta = "SELECT `usuario`.`dni` , `usuario`.`nombre` , `usuario`.`apellidos` , `parcela`.`poblacion` , `parcela`.`poligono` , `parcela`.`numero` , `parcela_usuario`.`dni_usuario` , `parcela_usuario`.`id_parcela`FROM usuario, parcela, parcela_usuario WHERE ((`usuario`.`dni` LIKE '"+dni+"') AND (`parcela`.`idparcela` LIKE `parcela_usuario`.`id_parcela`))";
		consulta2 ="SELECT  `parcela`.`poblacion` , `parcela`.`poligono` , `parcela`.`alias` FROM usuario, parcela, parcela_usuario WHERE ((`usuario`.`dni` LIKE '"+dni+"') AND (`parcela`.`idparcela` LIKE `parcela_usuario`.`id_parcela`))";
		consulta3 = "SELECT `parcela`.`poligono` , `parcela`.`alias` , `tareas_realizadas`.`fecha_ini` FROM tareas_realizadas, parcela WHERE ((`tareas_realizadas`.`dni_usuario` LIKE '"+dni+"') AND (`tareas_realizadas`.`idtarea` LIKE '1') AND (`tareas_realizadas`.`idparcela` LIKE `parcela`.`idparcela`))";
		consulta4 = "SELECT  `parcela`.`alias`,`producto`.`nombre` ,`tareas_realizadas`.`dosis` , `tareas_realizadas`.`fecha_ini`,`producto`.`descripcion` FROM tareas_realizadas, parcela,producto WHERE ((`tareas_realizadas`.`dni_usuario` LIKE '"+dni+"') AND (`tareas_realizadas`.`idtarea` LIKE '5') AND (`tareas_realizadas`.`idparcela` LIKE `parcela`.`idparcela`) AND (`tareas_realizadas`.`idprod` LIKE `producto`.`idprod`))";

		ConectarDBA.acceder();
		
		try {
			
			rs = ConectarDBA.consulta(consulta);		
			if (rs.next()) {	
				Dni = rs.getString(1);
				nombre = rs.getString(2);
				nombre = nombre +" "+ rs.getString(3);
				
				
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
                PdfWriter.getInstance(mipdf, new FileOutputStream(this.ruta_destino + ".pdf")).setInitialLeading(6);
		                mipdf.open();// se abre el documento
		                mipdf.addTitle(t); // se añade el titulo
		                mipdf.addAuthor(a); // se añade el autor del documento
		                mipdf.addSubject(s); //se añade el asunto del documento
		                mipdf.addKeywords(k); //Se agregan palabras claves 
		            Paragraph n = new Paragraph("OPENGIS", FontFactory.getFont("arial",22,Font.BOLD));    
		            n.setAlignment(Element.ALIGN_CENTER);
		            Paragraph n1 = new Paragraph(year, FontFactory.getFont("arial",30,Font.BOLD));    
		            n1.setAlignment(Element.ALIGN_CENTER);
		            Paragraph n2 = new Paragraph("CUADERNO DE EXPLOTACIÓN PARA CULTIVO SOSTENIBLE ", FontFactory.getFont("arial",18,Font.BOLD));   
		            n2.setAlignment(Element.ALIGN_CENTER);
		            Paragraph n3 = new Paragraph("REGLAMENTO(CEE)Nº 1698/2005", FontFactory.getFont("arial",16,Font.BOLD));   
		            n3.setAlignment(Element.ALIGN_CENTER);    
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));             
		                 mipdf.add(n);
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));             
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
		                 mipdf.add(n1);
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));             
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
		                 mipdf.add(n2);
		                 mipdf.add(n3);             
		                 
                 mipdf.newPage(); // segunda pagina
                 
                 
                 
		                 mipdf.add(new Paragraph("1. IDENTIFICACIÓN DEL PRODUCTOR", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph("Nombre y Apellidos/Empresa: "+nombre, FontFactory.getFont("arial",13,Font.BOLD)));
		                 mipdf.add(new Paragraph("D.N.I./N.I.F.:"+Dni, FontFactory.getFont("arial",13,Font.BOLD)));  
		                 mipdf.add(new Paragraph("Identificación del Responsable Técnico:"+linea, FontFactory.getFont("arial",13,Font.BOLD)));
		                 mipdf.add(new Paragraph("Cooperativa o SAT de la que forma parte:"+ linea, FontFactory.getFont("arial",13,Font.BOLD)));  
		                 mipdf.add(new Paragraph("2. IDENTIFICACIÓN DE LAS PARCELAS", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph(" "));
			                 PdfPTable tabla = new PdfPTable(8);
			                 tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
			                 tabla.addCell("Nº Orden");
			                 tabla.addCell("Término municipal");
			                 tabla.addCell("Polígono");
		                     tabla.addCell("Parcela");
		                     tabla.addCell("sup. Recínto (1)");
		                     tabla.addCell("sup. Sembrada (1)");
			                 tabla.addCell("Cultivo y Variedad");
			                 tabla.addCell("Observaciones");
			                 int orden= 1;
			                 tabla.addCell(orden+"");
			                 int con = 1;
			                 rs = ConectarDBA.consulta(consulta2);	
			                while(rs.next()){
			                	for(int i = 1; i <=3;i++){
			                		con++;
			                		tabla.addCell(rs.getString(i));
			                	}
			                	for(int aa = 1;aa<=4;aa++){
			                		tabla.addCell("");
			                		con++;
			                	}
			                	if(con == 8){
			                		orden ++;
			                		tabla.addCell(orden+"");
			                		con = 1;
			                	}
			                }
			                
			                 
			                 
		                 mipdf.add(tabla);
		                 mipdf.add(new Paragraph("(1) La superficie se reflejará en hectáreas con dos decimales.", FontFactory.getFont("arial",8,Font.BOLD)));
		                 
                 mipdf.newPage(); // tercera pagina
                 
                 
		                 mipdf.add(new Paragraph("3. PERIODO DE INUNDACIÓN ANTES DE LA SIEMBRA", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
			                 PdfPTable tabla1 = new PdfPTable(7);
			                 tabla1.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla1.addCell("Nº Orden parcela(2)");
			                 tabla1.addCell("Mes entrada agua");
			                 tabla1.addCell("Mes retirada agua");
			                 tabla1.addCell(" ");
			                 tabla1.addCell("Nº Orden parcela(2)");
			                 tabla1.addCell("Mes entrada agua");
			                 tabla1.addCell("Mes retirada agua");
			                 for(int o =0;o<=49;o++){
			                	 tabla1.addCell(" ");
			                 }
			             mipdf.add(tabla1);
                 
		                 mipdf.add(new Paragraph("4. PREPARACION DEL TERRENO", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph("Fangueado o fresadora", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
			                 PdfPTable tabla2 = new PdfPTable(4);
			                 tabla2.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla2.addCell("Poligono");
			                 tabla2.addCell("Parcela");
			                 tabla2.addCell("Fecha");
			                 tabla2.addCell("Observaciones");
			                 con =1;	
			                 rs =ConectarDBA.consulta(consulta3);
			                 while(rs.next()){
			                  for(int o = 1; o<=3;o++){
			                	  tabla2.addCell(rs.getString(o));
			                   if(con == 4){
			                	   tabla2.addCell(con+"");
			                	   con =1;
			                   }
			                 }
			                 }
		                 mipdf.add(tabla2);
		                 mipdf.add(new Paragraph("(2) Hacer constar el nº de orden de todas las parcelas si se agrupan.", FontFactory.getFont("arial",8,Font.BOLD)));
		                 
                 mipdf.newPage(); //cuarta pagina
                 
		                 mipdf.add(new Paragraph("Laboreo", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
			                 PdfPTable tabla3 = new PdfPTable(4);
			                 tabla3.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla3.addCell("Nº Orden parcela(2)");
			                 tabla3.addCell("Labor efectuada");
			                 tabla3.addCell("Fecha");
			                 tabla3.addCell("Observaciones");
			                 for(int o =0;o<=36;o++){
			                	 tabla3.addCell(" ");
			                 }
			             mipdf.add(tabla3);
                 
		                 mipdf.add(new Paragraph("Nivelación (en su caso)", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
			                 PdfPTable tabla4 = new PdfPTable(7);
			                 tabla4.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla4.addCell("Nº Orden parcela(2)");
			                 tabla4.addCell("Fecha");
			                 tabla4.addCell("Observaciones");
			                 tabla4.addCell(" ");
			                 tabla4.addCell("Nº Orden parcela(2)");
			                 tabla4.addCell("Fecha");
			                 tabla4.addCell("Observaciones");
			                 for(int o =0;o<=49;o++){
			                	 tabla4.addCell(" ");
			                 }
		                 mipdf.add(tabla4);
		                 mipdf.add(new Paragraph("(2) Hacer constar el nº de orden de todas las parcelas si se agrupan.", FontFactory.getFont("arial",8,Font.BOLD)));
		                
                 mipdf.newPage(); // quinta pagina 
		                 mipdf.add(new Paragraph("5. SIEMBRA", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph("Fangueado o fresadora", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
			                 PdfPTable tabla5 = new PdfPTable(6);
			                 tabla5.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla5.addCell("Nº Orden parcela(2)");
			                 tabla5.addCell("Variedad");
			                 tabla5.addCell("Calidad semilla(*)");
			                 tabla5.addCell("Dosis(kg/hectárea)");
			                 tabla5.addCell("Fecha siembra");
			                 tabla5.addCell("Observaciones");
			                
			                 for(int o =0;o<=60;o++){
			                	 tabla5.addCell(" ");
			                 }
		                 mipdf.add(tabla5);
		                 mipdf.add(new Paragraph("(2) Hacer constar el nº de orden de todas las parcelas si se agrupan.", FontFactory.getFont("arial",8,Font.BOLD)));
		                 mipdf.add(new Paragraph("(*) Especificar si se trata de semilla CERTIFICADA O SIN CERTIFICAR", FontFactory.getFont("arial",8,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
		                 mipdf.add(new Paragraph("6. TRATAMIENTOS FITOSANITARIOS(Polvirizar)", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
			                 PdfPTable tabla6 = new PdfPTable(5);
			                 tabla6.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla6.addCell("Nº Orden parcela(2)");
			                 tabla6.addCell("Producto(nombre comercial o materia activa)");
			                 tabla6.addCell("Dosis");
			                 tabla6.addCell("Fecha");
			                 tabla6.addCell("Observaciones");
			                 rs=ConectarDBA.consulta(consulta4);
			                 while(rs.next()){
			                	for(int o = 1;o <=5;o++){
			                		tabla6.addCell(rs.getString(o));
			                	} 
			                 }
			                 		                 
		                 mipdf.add(tabla6);
		                 
                 mipdf.newPage();//sexta pagina
		                 mipdf.add(new Paragraph("7. ABONADO", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
			                 PdfPTable tabla7 = new PdfPTable(7);
			                 tabla7.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla7.addCell("Nº Orden parcela(2)");
			                 tabla7.addCell("Tipo de Fertilizante");
			                 tabla7.addCell("Nitrato (UF/hectérea)");
			                 tabla7.addCell("Fosforo (UF/hectérea)");
			                 tabla7.addCell("Potasio (UF/hectérea)");
			                 tabla7.addCell("Fecha");
			                 tabla7.addCell("Observaciones");
			                
			                 for(int o =0;o<=70;o++){
			                	 tabla7.addCell(" ");
			                 }
		                 mipdf.add(tabla7);
		                 mipdf.add(new Paragraph("(2) Hacer constar el nº de orden de todas las parcelas si se agrupan.", FontFactory.getFont("arial",8,Font.BOLD)));
		                 mipdf.add(new Paragraph("8. COSECHA", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
			                 PdfPTable tabla8 = new PdfPTable(4);
			                 tabla8.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla8.addCell("Nº Orden parcela(2)");
			                 tabla8.addCell("Fecha");
			                 tabla8.addCell("Rendimiento(kg/hectérea)");
			                 tabla8.addCell("Observaciones");
			                
			                 for(int o =0;o<=20;o++){
			                	 tabla8.addCell(" ");
			                 }
		                mipdf.add(tabla8);
		                mipdf.add(new Paragraph("(2) Hacer constar el nº de orden de todas las parcelas si se agrupan.", FontFactory.getFont("arial",8,Font.BOLD)));
		                 
                 mipdf.newPage();//septima pagina
                 
		                 mipdf.add(new Paragraph("9.DETERMINACIONES ANALÍTICAS", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
			                 PdfPTable tabla9 = new PdfPTable(6);
			                 tabla9.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla9.addCell("Tipo de análisis");
			                 tabla9.addCell("Origen de la muestra");
			                 tabla9.addCell("Fecha de la toma");
			                 tabla9.addCell("Nº de referencia");
			                 tabla9.addCell("Resultados");
			                 tabla9.addCell("Observaciones");
			                
			                 for(int o =0;o<=26;o++){
			                	 if(o==0)
			                		 tabla9.addCell("Agua"); 
			                	 if(o==5)
			                		 tabla9.addCell("Suelo");
			                	 if(o==10)
			                		 tabla9.addCell("Cosecha");
			                	 tabla9.addCell(" ");
			                 }
			                 
		                 mipdf.add(tabla9);
		                 mipdf.add(new Paragraph("10.VISITAS TÉCNICAS", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",22,Font.BOLD)));
			                 PdfPTable tabla10 = new PdfPTable(5);
			                 tabla10.setHorizontalAlignment(Element.ALIGN_LEFT);
			                 tabla10.addCell("Fecha");
			                 tabla10.addCell("Organismo");
			                 tabla10.addCell("Firma técnico");
			                 tabla10.addCell("Firma titular");
			                 tabla10.addCell("Observaciones");
			                
			                 for(int o =0;o<=25;o++){
			                	 
			                	 tabla10.addCell(" ");
			                 }
                 
			             mipdf.add(tabla10);
                 
                 mipdf.newPage();
		                 mipdf.add(new Paragraph("11. CIERRE", FontFactory.getFont("arial",14,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph("En el presente Cuaderno de 'Cultivo sostenible' se han reflejado los datos de las operaciones de cultivo realizadas durante el periodo comprendido entre su apertura y el final de la campaña "+year, FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph("El titular del mismo da fe de que los datos reflejados son ciertos.", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(espacio+espacio+espacio+espacio+espacio+"..............................,a...............de....................................de.............", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph("SELLO OCAPA/DIRECCIÓN TERRITORIAL"+espacio+espacio+espacio+"El titular del cuaderno", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(" ", FontFactory.getFont("arial",12,Font.BOLD)));
		                 mipdf.add(new Paragraph(espacio+"(Sello)"+espacio+espacio+espacio+espacio+"Fdo.  .....................................", FontFactory.getFont("arial",12,Font.BOLD)));
		


                 mipdf.close(); //se cierra el PDF&
                JOptionPane.showMessageDialog(null,"Documento PDF creado");
            
            } catch (DocumentException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }        
    }
	
	 /* abre la ventana de dialogo GUARDAR*/
    public void Colocar_Destino(){
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF","pdf","PDF");
       JFileChooser fileChooser = new JFileChooser();       
       fileChooser.setFileFilter(filter);
       int result = fileChooser.showSaveDialog(null);
       if ( result == JFileChooser.APPROVE_OPTION ){   
           this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
        }
    } 
}
