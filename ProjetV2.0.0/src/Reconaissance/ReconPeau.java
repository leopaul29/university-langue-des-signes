package Reconaissance;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2HSV;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_MEDIAN;
import java.awt.Dimension;

import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_imgproc.CvMoments;

public class ReconPeau {
  private static int hueLowerR = 1;
  private static int hueUpperR = 20;
  
  private static int sLower = 0;
  private static int sUpper = 255;
  
  private static int vLower = 0;
  private static int vUpper = 255;

  private static final int INTERVALE_HUE = 5;
  private static final int INTERVALE_SV = 50;
    
    private int posX = 0;
    private int posY = 0;
    
    CvMoments moments = new CvMoments();
    
    private double momX100;
    private double momY01;
    private double area;
    
    private IplImage objetDetecte = new IplImage();    
    @SuppressWarnings("unused")
	private Dimension position = new Dimension();
    
    
    public IplImage getImage(){	return this.objetDetecte; }
    public int x(){return this.posX;}
    public int  y(){return this.posY;}

   public void setPositionObjet() {   
        
        cvMoments(this.objetDetecte, moments, 1);
        momX100 = cvGetSpatialMoment(moments, 1, 0); // (x,y)
        momY01 = cvGetSpatialMoment(moments, 0, 1);// (x,y)
        
        area = cvGetCentralMoment(moments, 0, 0);
         
        posX = (int) (momX100 / area);
        posY = (int) (momY01 / area);
        
       // System.out.println("x: " + posX + " y: " + posY);
        
        this.position = null;
        
        this.position = new Dimension(posX, posY);
    }

   public static void valeurParDefaut()
   {
	   hueLowerR = 1;
	   hueUpperR = 20;
	   
	   sLower = 0;
	   sUpper = 255;
	   
	   vLower = 0;
	   vUpper = 255;
   }
   
   //on detecte la couleur de la peau et on cree une image noir et blanc
    public  void DetectCouleur (IplImage orgImg) {
        
        IplImage imgHSV = orgImg.clone();
        cvCvtColor(orgImg, imgHSV, CV_BGR2HSV);
        // 8-bit 1- color = monochrome
        
        //allocation pour creer une image monocrhrome
        IplImage imgThreshold = cvCreateImage(cvGetSize(orgImg), 8, 1);
        
        cvInRangeS(imgHSV, cvScalar(hueLowerR, sLower, vLower, 0), cvScalar(hueUpperR, sUpper, vUpper, 0), imgThreshold);
        
        cvSmooth(imgThreshold, imgThreshold, CV_MEDIAN, 9);  
        
        this.objetDetecte = imgThreshold.clone();
 
        //on libere la memoire
        cvReleaseImage(imgThreshold);
        imgHSV = null;
        
    }
    
    
    public static void setIntervalleH (int h1)
    {
    //	if(h1 > -5 && h1 <20 )
    //	{
    		hueLowerR = h1 - INTERVALE_HUE;
    		hueUpperR = h1 + INTERVALE_HUE;
    //	}
    	
    	System.out.println("hueBas : " + hueLowerR + " hueHaut : " + hueUpperR);
 
    }
    
    public static void setIntervalleS (int s)
    {
    	if(s < 0 || s > 255)
    		return;
    	
    	
    	if( (s + INTERVALE_SV) > 255)
    		sUpper = 255;
    	else
    		sUpper = s + INTERVALE_SV;
    	
    	if( (s - INTERVALE_SV) < 0)
    		sLower = 0;
    	else
    		sLower = s - INTERVALE_SV;
    	
    	System.out.println("S min : "+ sLower + " S max : " +sUpper);
    	
    }
    
    
    public static void setIntervalleV (int v)
    {
    	if(v < 0 || v > 255)
    		return;
    	
    	if( (v + INTERVALE_SV) > 255)
    		vUpper = 255;
    	else
    		vUpper = v + INTERVALE_SV;
    	
    	if( (v - INTERVALE_SV) < 0)
    		vLower = 0;
    	else
    		vLower = v - INTERVALE_SV;
    	
    	System.out.println("V min : "+ vLower + " V max : " +vUpper);
    }
    
    public static int getMoyenneH () {return (hueLowerR + hueUpperR)/2 ;}
    public static int getMoyenneS () {return (sLower + sUpper)/2 ;}
    public static int getMoyenneV () {return (vLower + vUpper)/2 ;}
    
    
    
    // on cherche la masse de pixel blanc qui est la plus importante
    // en suppossant que cela soit la main
    public void recupMain (IplImage image)
    {
    	
    	IplImage zoneRecherche = image.clone();
    	CvRect rect = new CvRect(0,0,image.width(),image.height());
    	
    	this.DetectCouleur(zoneRecherche.clone());
		this.setPositionObjet();

		
		//on reduit la taille du rect
		//par rapport a la largeur
		if( (this.x()+rect.x()) < (int)image.width()/2 )
			rect.width(rect.width()/2);
		else
		{
			rect.x(rect.width()/2);
			rect.width(rect.width()/2);
		}
		
		zoneRecherche = IplImage.createFrom(ReconVisage.recupObjetBuffer(zoneRecherche.getBufferedImage(), rect));
	
		
		this.DetectCouleur(zoneRecherche);
		this.setPositionObjet();

		//par rapport a la hauteur
		if( (this.y()+rect.y()) < (int)image.height()/2 )
			rect.height(rect.height()/2);
		else
		{
			rect.y(rect.height()/2);
			rect.height(rect.height()/2);
		}
		
		zoneRecherche = IplImage.createFrom(ReconVisage.recupObjetBuffer(zoneRecherche.getBufferedImage(), rect));
		this.DetectCouleur(zoneRecherche);
		this.setPositionObjet();
		
		rect.x(this.x()+rect.x());
		rect.y(this.y()+rect.y());
		
		this.posX = rect.x();
		this.posY = rect.y();
		
		
    }
    
    // on suit la masse de pixel blanc
	public void suivreMain (IplImage image , CvRect rectangle)
    {	
       	//on creer un recatngle plus grand 
    	CvRect rect = new CvRect(rectangle.x(), rectangle.y(),rectangle.width(),rectangle.height());
       	
    	IplImage zoneRecherche = ReconVisage.recupObjet(image, rect);
    
    	this.DetectCouleur(zoneRecherche);
    	this.setPositionObjet();
    	
    	this.posX = rectangle.x() + (rect.x()+this.x() - rectangle.x());
    	this.posY = rectangle.y() + (rect.y()+this.y() - rectangle.y());
    	
    	
		
    }
	
	
    
    
    public void free()
    {
    	this.objetDetecte = null;
    	this.moments = null;
    	this.position = null;
    }


}