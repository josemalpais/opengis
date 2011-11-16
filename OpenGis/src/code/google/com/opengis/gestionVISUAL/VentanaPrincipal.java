/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestionVISUAL;

import Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;



/**
* @author knals
* Clase que genera la ventana principal del programa.
*
*/
public class VentanaPrincipal extends JFrame{
   
   /**
    * Constructor de la ventana sin parámetros, por defecto nos creará un 800x600 con nombre OpenGis.
    */
   public VentanaPrincipal(){
       this.setSize(800,600);
       this.setTitle("OpenGis");
       configVentana();
   }
   
   /**
    * Constructor de la ventana con parámetros.
    * @param h: Height/Altura: Elegiremos la cantidad de pixels de alto que tendrá la ventana.
    * @param w: Widht/Anchura: Elegiremos la cantidad de pixels de ancho que tendrá la ventana.
    * @param titulo: Elegiremos el titulo que aparecera en la parte superior de la ventana.
    */
   public VentanaPrincipal(int w,int h,String titulo){
       this.setSize(w,h);
       this.setTitle(titulo);
       configVentana();
   }
   
   /**
    * Método que nos configura la ventana.
    */
   private void configVentana(){
       this.setVisible(true);
       this.addWindowListener(new WindowAdapter(){
       	@Override
       	public void windowClosing(WindowEvent e) {
     /**
      * dispose();
      * Insertar aqui el Login Visual para que nos mande allí cuando le hagamos clic en el boton cerrar del JFrame
      */     		
       	}
       });
   }

}