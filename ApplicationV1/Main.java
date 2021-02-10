

package ApplicationV1;

import static com.googlecode.javacv.cpp.opencv_core.CV_AA;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvRectangle;

import java.awt.image.BufferedImage;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;



public class Main {
			
	private static int [] NOIR  = new int [3];
			
	@SuppressWarnings("unused")
	public static void main(String[] args) {
				
		
		 FluxVideo video = new FluxVideo();
		 ReconVisage detectVisage = new ReconVisage();
		 ReconVisage detectMain = new ReconVisage();
		 ObjectPositionDetect detect = new ObjectPositionDetect();
		 CvRect rect = new CvRect(0,0,200,200);
		 CvRect rectRecherche = new CvRect(0,0,100,100);
		 ObjectPositionDetect objetDetecte = new ObjectPositionDetect();
		 CvRect masqueVisage = new CvRect(0,0,100,100);
		 int x = 0,y = 0,hauteur = 0,largeur = 0;
		 CvRect rectColorAvg = new CvRect(x, y, hauteur, largeur);	 
		 IplImage imageColorAvg;
		 Fenetre f1 = new Fenetre(false);
		 ReconPeau r1 = new ReconPeau();
		 float[] HSV; 
		 
		 
		 Fenetre fenetreMain = new Fenetre(true);
		 
		 
				 
		 IplImage imageParalele = new IplImage();
				 
		   	  
		   try {
					video.initFluxVideo();
		   } catch (com.googlecode.javacv.FrameGrabber.Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		    	  
		    	 
		    // initialisation des fenetre 
		     BufferedImage buffer= null;
		     Fenetre fenetre = new Fenetre(true); 
		     Fenetre ImageDetecte = new Fenetre(true);
		     Fenetre detectFenetre = new Fenetre(true);
		    	  
		     int compteur = 20;

		     while(fenetre.getContinuerAppli())
		     {
		    		  
		    		  while(fenetre.getContinuerWebcam())
		    		  {
    			  
		    			  
		    			  while(  compteur>0  &&  fenetre.getContinuerAppli() && fenetre.getContinuerWebcam() )
		    			  {
		    				  //on recupere une image du flux
				    		  try {
				    				 video.setImageFlux(video.getFluxVideo().grab());
				
				    		  } catch (Exception e1) {
				    				// TODO Auto-generated catch block
				    				e1.printStackTrace();
				    		  }
			    			  
		    				  //on detecte le visage
		    	    		  detectVisage.setImage(video.getImageFlux());
		    	    		  detectVisage.detecterLesVisages();
		    	    		  	
		    	    		  if(detectVisage.getRectangle() != null){
		    			    		x = detectVisage.getX();
		    		   		  		y = detectVisage.getY();
		    		   		  		largeur = detectVisage.getLargeur();
		    		   		  		hauteur = detectVisage.getHauteur();
		    			   		  	rectColorAvg.width(20);
		    			   		  	rectColorAvg.height(20);
		    			   		  	rectColorAvg.x(x);
		    			   		  	rectColorAvg.y(y);
		    	    			
		    			   		  	imageColorAvg = detectVisage.recupObjet(rectColorAvg);
		    			   		  	if(imageColorAvg.getBufferedImage() != null){
		    		    				r1.setImage(imageColorAvg);
		    		    				r1.MoyenneCouleur();
		    		    				//System.out.println("rouge :"+r1.getCouleurMoyen()[0]+" vert:"+r1.getCouleurMoyen()[1]+" Bleu: " + r1.getCouleurMoyen()[2]);
		    		    				HSV  = ReconPeau.RGBtoHSV(r1.getCouleurMoyen()[0], r1.getCouleurMoyen()[1], r1.getCouleurMoyen()[2]);
		    		    				objetDetecte.setIntervalleH(0,0, HSV[0]);
		    		    				
		    			   		  	}
		    			    	}
		    	    		  
		    	    		  //si on detect la tete 
		    	    		  if (detectVisage.getRectangle() != null && detectVisage.getImage() != null)
		    		    		 {
		    	    			  
		    		    			
		    		    			 // on l'isole
		    		    			 masqueVisage.height( (int) (detectVisage.getRectangle().height()*1.70) );
		    		    			 masqueVisage.width( (int) (detectVisage.getRectangle().width()*1.70) );
		    		    			 masqueVisage.x(  detectVisage.getRectangle().x()  - (int) (detectVisage.getRectangle().width()/2) );
		    		    			 masqueVisage.y( detectVisage.getRectangle().y() - (int) (detectVisage.getRectangle().height()/1.70) );
		    		    			 
		    		    			 imageParalele= video.getImageFlux().clone();
		    		    			 detectMain.setImage(video.getImageFlux());
		    		    			 
		    		    			 
		    		    			//appli du masque
		    							try {
		    								ReconPeau.masquerObjet(imageParalele, NOIR, masqueVisage);
		    							} catch (Exception e) {
		    								// TODO Auto-generated catch block
		    								System.out.println(e);
		    							}
		    							
		    							
		    							//on cherche la main sur l'image parallele
		    							 objetDetecte.recupMain(imageParalele);
		    		    				 
		    							 //on dessine le rectangle de la main 
		    							 rect.x(objetDetecte.x());
		    		    				 rect.y(objetDetecte.y());

		    		    				 cvRectangle(detectVisage.getImage() ,  cvPoint(rect.x()-rect.width()/2,rect.y()-rect.height()/2),cvPoint(rect.x()+rect.width()/2,rect.y()+rect.height()/2),
		    		    		 				  CvScalar.BLUE, 0, CV_AA, 0 );
	 
		    		    				 
		    		    				 //on affiche l'image noir et blanc
		    		    				 objetDetecte.DetectCouleur(imageParalele);
		    		    				 
		    		    				 detectFenetre.setImage(objetDetecte.getImage().getBufferedImage());
		    		    				 detectFenetre.repaintFenetre();
		    		    				 
		    		    				 ImageDetecte.setImage(detectVisage.getImage().getBufferedImage());
			    						 ImageDetecte.repaint();
			    						 
			    						 if ( fenetre.getContinuerAppli() == false)// si on demande de quitter
			    			    			 fenetre.setContinuerWebcam(false);
			    						
			    						 
		    		    		 }
		    	    		  compteur--;
		    	    		  //System.out.println(compteur);
		    			  }
		    			  
		    			  
		    			  //on libere la mémoire pour la seconde phase
		    			  try
		    			  {
		    				  detectVisage.free();
		    				  detectMain.free();
		    				  imageParalele.deallocate();
		    				  masqueVisage = null; // a enlever si on utilise le masque
		    				  objetDetecte.free();
		    				  
		    				  detectFenetre.setImage(null);
		    				  ImageDetecte.setImage(null);
		    			  }
		    			  catch(Exception e)
		    			  {
		    				  System.out.println(e);
		    			  }
		    			  
		    			  ImageDetecte.dispose();
		    			  detectFenetre.getFocusListeners();
		    			  
		    			  //Seconde phase du prog
		    			  while (  fenetre.getContinuerAppli() && fenetre.getContinuerWebcam())
		    			  {
		    				  try {
				    				 video.setImageFlux(video.getFluxVideo().grab());
				
				    		  } catch (Exception e1) {e1.printStackTrace();}
		    				  
		    				  objetDetecte.suivreMain(video.getImageFlux(), rect);
		    				  
 							 	//on dessine le rectangle de la main 
 								 rect.x(objetDetecte.x()-(rect.width()/2));
 								 rect.y(objetDetecte.y()-(rect.height()/2));

 		    				 cvRectangle(video.getImageFlux() ,  cvPoint(rect.x(),rect.y()),cvPoint(rect.x()+rect.width(),rect.y()+rect.height()),
 		    		 				  CvScalar.BLUE, 0, CV_AA, 0 );
 		    				
 		    				 
 		    				 detectMain.setImage(video.getImageFlux());
 		    				 cvSaveImage("Main.jpg",detectMain.recupObjet(rect));
 		    				 
 		    				 fenetreMain.setImage(detectMain.recupObjet(rect).getBufferedImage());
 		    				 fenetreMain.repaint();
		    				  
		    		    		
		    		    	 //on change l'image de la fenêtre
		    		    	 fenetre.setImage(video.getImageFlux().getBufferedImage());
		    		    		
		    		    	 //on redessine la fenêtre principale
		    		    	 fenetre.repaintFenetre();	
		    		    		 
		    		    		 
		    		    		//on affiche l'image noir et blanc
    		    			objetDetecte.DetectCouleur(video.getImageFlux());
    		    			detectFenetre.setImage(objetDetecte.getImage().getBufferedImage());
    		    				 detectFenetre.repaintFenetre();
    		    				 
    		    				 
    		    				 //liberation de la memoire
    		    				 
    		    				 try
    		    				 {
    		    					 objetDetecte.free();
    		    					 
    		    				 }
    		    				 catch (Exception e)
    		    				 {
    		    					 System.out.println(e);
    		    				 }
    		    				 
    		    				 
    		    				 if ( fenetre.getContinuerAppli() == false)// si on demande de quitter
    		    	    			 fenetre.setContinuerWebcam(false);
	
		    			  }
		    			  
		    			  fenetre.setImage(null);
		        		  fenetre.repaintFenetre();
		    		  }
		    		  
		    	  }	  
		    	  
		    	  fenetre.dispose();
		    	  
			}		
	}
