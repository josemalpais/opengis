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
    final JWebBrowser webBrowser = new JWebBrowser();   
    webBrowser.navigate(url);   
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);   
    add(webBrowserPanel, BorderLayout.CENTER);   
    // Create an additional bar allowing to show/hide the menu bar of the web browser.   
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));   
    JCheckBox menuBarCheckBox = new JCheckBox("Menu Bar", webBrowser.isMenuBarVisible());   
    menuBarCheckBox.addItemListener(new ItemListener() {   
      public void itemStateChanged(ItemEvent e) {   
        webBrowser.setMenuBarVisible(e.getStateChange() == ItemEvent.SELECTED);   
      }   
    });   
    buttonPanel.add(menuBarCheckBox);   
    add(buttonPanel, BorderLayout.SOUTH);   
  }   
  


}