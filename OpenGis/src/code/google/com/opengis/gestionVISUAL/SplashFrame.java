package code.google.com.opengis.gestionVISUAL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.ParseException;

import javax.swing.*;

public class SplashFrame extends JFrame {
	
	public static JProgressBar jpb = new JProgressBar();
	Image imagen = null;
	
	public SplashFrame() {

		setLayout(new GridBagLayout());
		ImageIcon bgimage = new ImageIcon("OpenGis/src/recursosVisuales/splash.png");
		JLabel img = new JLabel(bgimage); 
		this.setBackground(Color.WHITE);

		int width = 590; // 590 x 347
	    int height = 347;
	    
	    int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
	    int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
	    
	    setUndecorated(true);
	    setVisible(true);
		setBounds(x, y, width, height);
		
		jpb.setMinimum(0);
		jpb.setMaximum(100);
	    GridBagConstraints gbc = new GridBagConstraints();
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    this.add(img,gbc);

	    
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    
	    this.add(jpb,gbc);     
		setVisible(true);
		
		jpb.setValue(current());
		//  this.ContentPanel().setOpaque(false);	
	    //	add(new ContentPanel());
	    //  setSize(500, 300);   
  }

  public int current(){
 	  int i = 0;
	  for (i = 0 ; i <= 100; i++){
		  i = i + 20;
		  SplashFrame.jpb.setValue(i);
		  SplashFrame.jpb.repaint();
		  System.out.println(i);
		  try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  if (i >= 100){
		  this.dispose();
	  }
	  return i;
  }
  public void paint(Graphics g) {
	    g.drawImage(imagen, 1, 1, this.getWidth(),this.getHeight(),this);
	    super.paint(g);
	  }
 	}

