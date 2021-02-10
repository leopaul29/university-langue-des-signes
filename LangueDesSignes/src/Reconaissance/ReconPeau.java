package Reconaissance;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import Math.Point;
import Math.Segment;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_imgproc.CvMoments;

public class ReconPeau {
  private static int hueLowerR = 5;
  private static int hueUpperR = 15;
  
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
	
	
	
	public static CvRect adaptRectangleObjet (IplImage imageNoir, CvRect rect)
	{
		CvRect result = null;
		
		
		if( rect.x() < 0|| rect.y() < 0|| rect.x()+rect.width()>= imageNoir.width() || rect.y()+rect.height() >= imageNoir.height())
		{
			result = reposRectangleImage(imageNoir, rect);
		}
		else
			result =  new CvRect(rect.x(), rect.y(), rect.width(), rect.height());
		
		
		int DECALAGE = 10;
		BufferedImage img = imageNoir.getBufferedImage();
		
		//on cree un plus petit rectangle pour teste si on a besoin de reduire le rectangle
		CvRect petitRect = new CvRect(result.x()+DECALAGE, result.y()+DECALAGE, result.width()-DECALAGE*2, result.height()-DECALAGE*2);
		
		//point
		Point A = new Point(petitRect.x(), petitRect.y());
		Point B = new Point(petitRect.x()+petitRect.width(), petitRect.y());
		Point C = new Point(petitRect.x()+petitRect.width(), petitRect.y()+petitRect.height());
		Point D = new Point(petitRect.x(), petitRect.y()+petitRect.height());
		
		//System.out.println("PETIT RECT!!!! " + petitRect.x() + " "+ petitRect.width());
		
		/*
		System.out.println(A.toString());
		System.out.println(B.toString());
		System.out.println(C.toString());
		System.out.println(D.toString());
		*/
		//cote Horizontale du Rectangle
		if( segmentCoupeObjet(img, new Segment(A,B)) == 1 || segmentCoupeObjet(img, new Segment(D,C)) == 1  ){
			
			if (result.y()-DECALAGE > 0 && result.y()-DECALAGE + result.height()+DECALAGE*2 < imageNoir.height()){
				result.y(result.y()-DECALAGE);
				result.height(result.height()+DECALAGE*2);
			}
		}
		else{
			if(result.y()+DECALAGE < imageNoir.height() && result.height()-DECALAGE*2>0 ){
				result.y(result.y()+DECALAGE);
				result.height(result.height()-DECALAGE*2);
			}
		}
		
		//cote Verticale du Rectangle
		
		if( segmentCoupeObjet(img, new Segment(A,D)) == 1 || segmentCoupeObjet(img, new Segment(B,C)) == 1  ){
			
			if (result.x()-DECALAGE > 0 && result.x()-DECALAGE + result.width()+DECALAGE*2 < imageNoir.width()){
				result.x(result.x()-DECALAGE);
				result.width(result.width()+DECALAGE*2);
			}
		}
		else{
			if(result.x()+DECALAGE < imageNoir.width() && (result.width()-DECALAGE*2) >0 ){
				result.x(result.x()+DECALAGE);
				result.width(result.width()-DECALAGE*2);
			}
		}
		
		return result;
		
	}
	
	//on Teste nos points du premier point du segment au second
	private static int segmentCoupeObjet (BufferedImage img , Segment seg)
	{
		int BLANC = 255;
		
		WritableRaster trame = img.getRaster();
		int []pixel = new int [1];
		
		if(seg.horizontal()){
			for(int x = 0; x < seg.getLongueur(); x++)
			{
				try {
					trame.getPixel( (int)seg.getExtremite(1).x() + x, (int)seg.getExtremite(1).y(), pixel);
				} catch (Exception e) {e.printStackTrace();}
				
				if(pixel[0] == BLANC)
					return 1;
			}
			
		}
		else if (seg.vertical()){
			for(int y = 0; y < seg.getLongueur(); y++)
			{
				try {
					trame.getPixel( (int)seg.getExtremite(1).x(), (int)seg.getExtremite(1).y()+y, pixel);
				} catch (Exception e) {e.printStackTrace();}
				
				if(pixel[0] == BLANC)
					return 1;
			}
		}
		else
			return -1;
			
		return 0;
	}
	
	
	private static CvRect reposRectangleImage(IplImage image, CvRect rect)
	{
		CvRect result = new CvRect(rect.x(), rect.y(), rect.width(), rect.height());
		
		if(rect.x() + rect.width() > image.width() )
			result.x(image.width()-rect.width()-1);
		if(rect.y() + rect.height() > image.height() )
			result.y(image.height()- rect.height()-1);
		if(rect.x() <0)
			result.x(0);
		if(rect.y() <0)
			result.y(0);
		
		
		return result;
	}	
	
    
    
    public void free()
    {
    	this.objetDetecte = null;
    	this.moments = null;
    	this.position = null;
    }


}