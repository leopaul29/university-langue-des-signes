package Fenetre;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PanneauLettre extends JPanel  {

	private static final long serialVersionUID = -231394428684493014L;
	
	BufferedImage image = null;
	

	public void paintComponent(Graphics g){

		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);


	}
	
	public void setImage(BufferedImage image){ 
		
		if(this.image != null)
			this.image.flush();
		
		this.image = image;
		this.repaint();
	}
	
	public BufferedImage getImage(){ return this.image;}
	
	

}
