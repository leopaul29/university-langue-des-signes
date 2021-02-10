	package ApplicationV1;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2HSV;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_MEDIAN;
import java.awt.Dimension;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_imgproc.CvMoments;

public class ObjectPositionDetect {
  private int hueLowerR = 1;
  private int hueUpperR = 10;

    
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

    
    /*public void setIntervalleH ( float hMoyen)
    {
    	
    	hueLowerR = (int)(hMoyen*180)-5 ;//tolerance de 5/160 0.03
    	hueUpperR = (int)(hMoyen*180)+5 ;
    }*/
    

   public void setPositionObjet() {
       
        
        cvMoments(this.objetDetecte, moments, 1);
        // cv Spatial moment : Mji=sumx,y(I(x,y)•xj•yi)
        // where I(x,y) is the intensity of the pixel (x, y).
      
        momX100 = cvGetSpatialMoment(moments, 1, 0); // (x,y)
        momY01 = cvGetSpatialMoment(moments, 0, 1);// (x,y)
        
        area = cvGetCentralMoment(moments, 0, 0);
         
        posX = (int) (momX100 / area);
        posY = (int) (momY01 / area);
        
       // System.out.println("x: " + posX + " y: " + posY);
        
        this.position = null;
        
        this.position = new Dimension(posX, posY);
    }

    public  void DetectCouleur (IplImage orgImg) {
        
        IplImage imgHSV = orgImg.clone();
        
        cvCvtColor(orgImg, imgHSV, CV_BGR2HSV);
        IplImage imgThreshold = cvCreateImage(cvGetSize(orgImg), 8, 1);
        
        cvInRangeS(imgHSV, cvScalar(hueLowerR, 50, 50, 0), cvScalar(hueUpperR, 255, 255, 0), imgThreshold);
        
        cvSmooth(imgThreshold, imgThreshold, CV_MEDIAN, 9);
        
        
        this.objetDetecte = imgThreshold.clone();
       
        cvReleaseImage(imgThreshold);
        imgHSV = null;
        
    }
    
    
    public void setIntervalleH (float h1 , float h2 , float hMoyen)
    {
    	
    	hueLowerR = (int)(hMoyen*160) -100;//tolerance de 5/160 0.03
    	hueUpperR = (int)(hMoyen*160) +100;
    	
    	if ( (int) (h1*360) > (int) (h2*360) )
    	{
    		hueUpperR = (int) (h1*180)  +15;
    		hueLowerR = (int) (h2*180) -15 ;
    	}
    	else
    	{
    		hueLowerR = (int) (h1*180)-15  ;
    		hueUpperR= (int) (h2*180)+15;
    	}
    	System.out.println("Valeur de hHaut : " + hueLowerR + " Valeur de hBas :" + hueUpperR + " Valeur de hMoyen: " + hMoyen*360);
    }
    
    
    public void recupMain (IplImage image)
    {
    	
    	ReconVisage reconMain = new ReconVisage(image);
    	ObjectPositionDetect detectMain = new ObjectPositionDetect();
    	CvRect rect = new CvRect(0,0,image.width(),image.height());
    	
    	detectMain.DetectCouleur(reconMain.getImage());
		detectMain.setPositionObjet();

		
		//on reduit la taille du rect
		//par rapport a la largeur
		if( (detectMain.x()+rect.x()) < (int)image.width()/2 )
			rect.width(rect.width()/2);
		else
		{
			rect.x(rect.width()/2);
			rect.width(rect.width()/2);
		}
		reconMain.recupObjet(rect);
		detectMain.DetectCouleur(reconMain.getImageDetecte());
		detectMain.setPositionObjet();

		//par rapport a la hauteur
		if((detectMain.y()+rect.y()) < (int)image.height()/2 )
			rect.height(rect.height()/2);
		else
		{
			rect.y(rect.height()/2);
			rect.height(rect.height()/2);
		}
		reconMain.recupObjet(rect);
		detectMain.DetectCouleur(reconMain.getImageDetecte());
		detectMain.setPositionObjet();
		
		rect.x(detectMain.x()+rect.x());
		rect.y(detectMain.y()+rect.y());
		
		this.posX = rect.x();
		this.posY = rect.y();
    }
    
    
    public void suivreMain (IplImage image , CvRect rectangle)
    {
    	
    	ReconVisage reconMain = new ReconVisage(image);
       	ObjectPositionDetect detectMain = new ObjectPositionDetect();
       
    	
       	//on creer un recatngle plus grand 
    	CvRect rect = new CvRect(rectangle.x(), rectangle.y(),rectangle.width(),rectangle.height());
       	
    	reconMain.recupObjet(rect);
    	
    	detectMain.DetectCouleur(reconMain.getImageDetecte());
    	detectMain.setPositionObjet();
    	
    	this.posX = rectangle.x() + (rect.x()+detectMain.x() - rectangle.x());
    	this.posY = rectangle.y() + (rect.y()+detectMain.y() - rectangle.y());
    }
    
    
    public void free()
    {
    	
    	this.objetDetecte.deallocate();
    	this.position = null;
    	this.moments.deallocate();
    }


}