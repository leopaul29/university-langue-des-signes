package ApplicationV1;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;


public class PanneauVideo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Image image = null;
	
    
    public void setImage (Image image)
    {
    	this.image = image;
    }
    
    public Image getImage ()
    {
    	return this.image;
    }
    
    
	public void paintComponent (Graphics g) 
	{
		
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
		
	}
	
	
   
	
	
	

}
