package code.google.com.opengis.gestionVISUAL;

import javax.swing.JEditorPane;
import javax.swing.JPanel;


import java.awt.BorderLayout;   
import java.awt.FlowLayout;   
import java.awt.event.ItemEvent;   
import java.awt.event.ItemListener;   
  
import javax.swing.BorderFactory;   
import javax.swing.JCheckBox;   
import javax.swing.JFrame;   
import javax.swing.JPanel;   
import javax.swing.SwingUtilities;   
  
import chrriis.common.UIUtils;   
import chrriis.dj.nativeswing.swtimpl.NativeInterface;   
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;   
  
/**  
 * @author Christopher Deckers  
 */  
public class PanelPDF extends JPanel {   
  
  public PanelPDF(String url) {   
    super(new BorderLayout());   
    JPanel webBrowserPanel = new JPanel(new BorderLayout());   
    webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Visor")); 
    System.out.println("HA ENTRADO");
    final JWebBrowser webBrowser = new JWebBrowser();   
    System.out.println("HA PASADO");
    webBrowser.navigate(url);   
    webBrowser.setLocationBarVisible(false);
    webBrowser.setButtonBarVisible(false);
    webBrowser.setMenuBarVisible(false);
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);   
    add(webBrowserPanel, BorderLayout.CENTER);   
   
      
  }   
  


}