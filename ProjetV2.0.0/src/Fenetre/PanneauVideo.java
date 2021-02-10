package Fenetre;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import FluxVideo.threadFluxVideo;
import Observer.Observateur;


public class PanneauVideo extends JPanel implements KeyListener {

	private static final long serialVersionUID = -231394428684493014L;
	
	BufferedImage image = null;
	threadFluxVideo thFlux = null;
	
	
	CvRect rectVisage = null;
	CvRect rectMain = new CvRect(0,0,300,300);
	
	
	public PanneauVideo ()
	{
		thFlux = new threadFluxVideo();
		
		//le panneau video observe le thread Flux*
		
		thFlux.addObservateur(new Observateur() {
			
			@Override
			public void update() {
				image = thFlux.getCapture();//on recup l'image
				repaint();// on actualise le panneau
			}
		});
		
		
		Thread th = new Thread(thFlux);
		th.start();
		
		
		
		
	}


	public void paintComponent(Graphics g){

		Graphics2D g2d = (Graphics2D)g;
		
		if(this.rectVisage != null)
			tracerEllipse(new CvRect(rectVisage.x(), rectVisage.y(), rectVisage.width(), rectVisage.height()));
		
		if( this.image != null)
			this.tracerRectMain();
		
		g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		
		
		rectVisage = null;
	
	}
	
	public void setImage(BufferedImage image){ this.image = image;}
	public BufferedImage getImage(){ return this.image;}
	public IplImage getImageIpl(){ return this.thFlux.getCaptureIpl();}
	
	public void setBoutonQuitter(boolean bool){ 
		thFlux.setContinuer(!bool);
		
	}
	public void setBoutonArreter(boolean bool){ 
		thFlux.setPause(bool);
	}
	
	
	public void setRectangleVisage (CvRect rect) { this.rectVisage = rect;}
	
	public void setPosRectangleMain (int x, int y) { 
		this.rectMain.x(x);
		this.rectMain.y(y);
	}
	
	public CvRect getRectMain (){ return this.rectMain;}
 	
	
	private void tracerEllipse(CvRect rect)
	{		
		//on reduit la largeur de l'ellipse
    	this.rectVisage.x(this.rectVisage.x()+ (this.rectVisage.width()*10)/100);
    	this.rectVisage.width(this.rectVisage.width()- (this.rectVisage.width()*20)/100);
    	
    	//on reduit la hauteur de l'ellipse
    	this.rectVisage.y(this.rectVisage.y()+ (this.rectVisage.height()*5)/100);
    	this.rectVisage.height(this.rectVisage.height()- (this.rectVisage.height()*10)/100);
    	
    	
    	Graphics2D g2d = this.image.createGraphics();
    	g2d.setColor(Color.blue);
    	g2d.drawOval(rectVisage.x(), rectVisage.y(), rectVisage.width(),
    			rectVisage.height());

	}
	
	private void tracerRectMain()
	{	
		 
		 Graphics2D g2d = this.image.createGraphics();
		 g2d.setColor(Color.red);
		 g2d.drawRect(rectMain.x(),rectMain.y(), rectMain.width(),
				 rectMain.height());

	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		

		switch (e.getKeyCode())
		{
		case 107 : // touche +
			if(rectMain.width()+10 < this.image.getWidth() && rectMain.height()+10 < this.image.getHeight())
			{
				this.rectMain.width(rectMain.width()+10);
				this.rectMain.height(rectMain.height()+10);
			}
			break;
			
		case 109: // touche -
			if(rectMain.width()-10 > 50 && rectMain.height()-10 > 50)
			{
				this.rectMain.width(rectMain.width()-10);
				this.rectMain.height(rectMain.height()-10);
			}
			break;
			
		case 37: // fleche gauche
			
			if(rectMain.width() -10 > 50)
				this.rectMain.width(rectMain.width()-10);
			
			break;
			
		case 38: //fleche haut
			if( rectMain.height()+10 < this.image.getHeight())
				this.rectMain.height(rectMain.height()+10);
			break;
			
		case 39: //fleche droite
			if(rectMain.width()+10 < this.image.getWidth())
				this.rectMain.width(rectMain.width()+10);
			break;
		
		case 40: //fleche bas
			if( rectMain.height()-10 > 50)
				this.rectMain.height(rectMain.height()-10);
			break;
		
		default:
				System.out.println(e.getKeyCode());
		}
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	@SuppressWarnings("unused")
	public static void main (String [] args)
	{
		FenetrePrincipale fen = new FenetrePrincipale(0, 0, new PanneauVideo());
	}
	

}
