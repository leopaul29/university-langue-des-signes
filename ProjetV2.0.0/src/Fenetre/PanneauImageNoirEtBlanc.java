package Fenetre;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import Reconaissance.*;

public class PanneauImageNoirEtBlanc extends JPanel  implements KeyListener{

	private static final long serialVersionUID = -231394428684493014L;
	
	IplImage image = null;
	
	ReconPeau detectPeau = null;
	
	public PanneauImageNoirEtBlanc ()
	{
		detectPeau = new ReconPeau();
	}


	public void paintComponent(Graphics g){

		Graphics2D g2d = (Graphics2D)g;
		
		if(this.image != null)
		{
			detectPeau.DetectCouleur(this.image);
			g2d.drawImage(detectPeau.getImage().getBufferedImage(), 0, 0, this.getWidth(), this.getHeight(), this);
			detectPeau.free();
		}
			
	
	}
	
	public void setImage(IplImage image){ 
		
		this.image = image;
		
		this.repaint();
	}
	
	public IplImage getImage() { return this.image;}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
		switch(arg0.getKeyChar())
		{
		case '+':
			ReconPeau.setIntervalleH(ReconPeau.getMoyenneH() + 1);
				break;
		case '-':
			ReconPeau.setIntervalleH(ReconPeau.getMoyenneH() - 1);
			break;
		
		case 'a':
			ReconPeau.setIntervalleS(ReconPeau.getMoyenneS() + 10);
			break;
			
		case 'z':
			ReconPeau.setIntervalleS(ReconPeau.getMoyenneS() - 10);
			break;
			
		case 'q':
			ReconPeau.setIntervalleV(ReconPeau.getMoyenneV() + 10);
			break;
			
		case 's':
			ReconPeau.setIntervalleV(ReconPeau.getMoyenneV() - 10);
			break;
		
		case 'w':
			ReconPeau.valeurParDefaut();
			break;
			
		default:
			System.out.println(arg0.getKeyChar());
			
				
		}
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
