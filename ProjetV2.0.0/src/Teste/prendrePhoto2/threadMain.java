package Teste.prendrePhoto2;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Arbre.Recup;
import FluxVideo.FluxVideo;
import Reconaissance.ReconPeau;
import Reconaissance.ReconVisage;
import Teste.VerifierImage.Fenetre;

import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class threadMain implements Runnable {

	
	Fenetre fenetreCAM = null;
	Fenetre fenetreNoirBlanc = null;
	
	
	FluxVideo flux = null;
	
	IplImage imageCapture = null;
	BufferedImage imageBuffer = null;
	
	ReconPeau objDetect = null;
	
	CvRect rect = new CvRect(0, 0, 275, 325);
	
	public threadMain()
	{
		fenetreCAM = new Fenetre(500, 500);
		
		fenetreNoirBlanc  = new Fenetre(Teste.VerifierImage.Main.LARGEUR, Teste.VerifierImage.Main.HAUTEUR);
		fenetreNoirBlanc.setLocation(fenetreCAM.getX()+fenetreCAM.getWidth(),0);
		
		flux = new FluxVideo();
		objDetect = new ReconPeau();
		
	}
	
	public void run (){
		
		while(true)
		{
			
			try {
				
				 imageCapture = flux.getFluxVideo().grab().clone();
				 imageBuffer = imageCapture.getBufferedImage();
				 
				 objDetect.DetectCouleur(flux.getImageFlux().clone());
				 objDetect.suivreMain(imageCapture, rect);
				 this.rect.x(objDetect.x()-rect.width()/2);
				 this.rect.y(objDetect.y()-rect.height()/2);
				 
				 Graphics2D g2d = imageBuffer.createGraphics();
				 g2d.setColor(Color.red);
				 g2d.drawRect(rect.x(),rect.y(), rect.width(), rect.height());
				 
				 fenetreCAM.setImage(imageBuffer);
				 fenetreCAM.repaint();
				 
				 fenetreNoirBlanc.setImage(
						 Recup.redimImage(IplImage.createFrom(ReconVisage.recupObjetBuffer(objDetect.getImage().getBufferedImage()
						 , rect) ),150,100).getBufferedImage());
				 fenetreNoirBlanc.repaint();
				 
				 Thread.sleep(10);

		  } catch (Exception | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
			
			

		}	
	}
	
	
	public BufferedImage getCapture (){ 
		return  Recup.redimImage(IplImage.createFrom(ReconVisage.recupObjetBuffer(fenetreCAM.getImage()
			 , rect) ),150,100).getBufferedImage();
			}

}
