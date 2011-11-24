package code.google.com.opengis.gestionVISUAL;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import code.google.com.opengis.gestion.Producto;


 
public class ProductoVisual {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
 
    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
 
        JButton button;
        JLabel label;
        JTextField texto;
        JTextArea textarea;
        JComboBox combo;
        JCheckBox check;
        
    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    if (shouldFill) {
    //natural height, maximum width
    c.fill = GridBagConstraints.HORIZONTAL;
    }
    c.insets = new Insets(8,8,8,8);
   // button = new JButton("Button 1");
    if (shouldWeightX) {
    c.weightx = 0.5;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    //pane.add(button, c);
   
    label = new JLabel("Producto ID:");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 0;
    pane.add(label,c);
   
    texto = new JTextField(10);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 0;
    pane.add(texto, c);
 
    label = new JLabel("Nombre del Producto:");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 1;
    pane.add(label,c);
   
    texto = new JTextField();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 1;
    pane.add(texto, c);

    label = new JLabel("Tipo:");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 2;
    pane.add(label,c);
   
    texto = new JTextField();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 2;
    pane.add(texto, c);
   
    label = new JLabel("Descripción:");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 4;
    pane.add(label,c);
   
    textarea = new JTextArea("",5,10);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 4;
    pane.add(textarea, c);

    label = new JLabel ("Tarea:");
    c.fill= GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy= 0;
    pane.add(label,c);
    
    String[] petStrings = { "Arar", "Abonar", "Sembrar"};
    combo= new JComboBox(petStrings);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx= 4;
    c.gridy= 0;
    pane.add(combo, c);
    
    label= new JLabel("Activo");
    c.fill=GridBagConstraints.HORIZONTAL ;
    c.gridx=3;
    c.gridy=1;
    pane.add(label,c);
    
    check =new JCheckBox ();
    c.fill=GridBagConstraints.HORIZONTAL;
    c.gridx = 4;
    c.gridy= 1;
    pane.add(check,c);
    
    button = new JButton("Confirmar");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 5;
    pane.add(button, c);
    
    button = new JButton("Cancelar");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 5;
    pane.add(button, c);
    }
   /* button = new JButton("5");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0;       //reset to default
    c.weighty = 1.0;   //request any extra vertical space
    c.anchor = GridBagConstraints.PAGE_END; //bottom of space
    c.insets = new Insets(10,0,0,0);  //top padding
    c.gridx = 1;       //aligned with button 2
    c.gridwidth = 2;   //2 columns wide
    c.gridy = 2;       //third row
    pane.add(button, c);
    }*/
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
