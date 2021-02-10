package ApplicationV1;

import static com.googlecode.javacv.cpp.opencv_core.CV_AA;
import static com.googlecode.javacv.cpp.opencv_core.cvEllipse;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSeqElem;
import static com.googlecode.javacv.cpp.opencv_core.cvLoad;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_objdetect.cvHaarDetectObjects;
import com.googlecode.javacv.cpp.opencv_core;
import com.googlecode.javacv.cpp.opencv_objdetect;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class ReconVisage {
	
	
		private static final String CASCADE_FILE =  "C:\\opencv\\data\\haarcascades\\haarcascade_frontalface_alt.xml";
		private static final opencv_objdetect.CvHaarClassifierCascade cascade
		=new opencv_objdetect.CvHaarClassifierCascade(cvLoad(CASCADE_FILE)); 
		
		private IplImage Image = new IplImage(); // L'image ou l'on veut y retrouver la tête
		private IplImage ImageGris = new IplImage(); // "Image" en gris pour retrouver la silhouette de la tête
		private IplImage  visageDetecte = new IplImage(); // L'image du visage detecté
		
		private opencv_core.CvMemStorage storage; // un block de mémoire
	
		private opencv_core.CvSeq visages = new opencv_core.CvSeq(); //
		public CvRect rectangle = new CvRect();
		
		public void free()
		{
			this.Image.deallocate();
			this.ImageGris.deallocate();
			this.visageDetecte.deallocate();
			this.storage.deallocate();
			this.visages.deallocate();
		}
		
		
		//													Constructeur
		
		public ReconVisage ()
		{
			this.Image = null;
			this.ImageGris = null;
		}
		
		public  ReconVisage (IplImage Image )
		{
			this.Image =  Image;
			this.transImageGris();
		}
		
		//													Accesseur de consultation
		public IplImage getImage ()
		{
			return this.Image;
		}
		
		public IplImage getImageGris ()
		{
			return this.ImageGris;
		}
		
		public IplImage getImageDetecte ()
		{
			return this.visageDetecte;
		}
		
		public CvRect getRectangle ()
		{
			return this.rectangle;
		}
		
		//													Accesseur de modification
		public void setImage (IplImage image)
		{
			this.Image = null;
			this.Image = image.clone();
			this.transImageGris(); 
		}
		
		
		//														Méthode transImageGris
		
		private void transImageGris () 	//methode pour transformer une image en monochromatique (noir/blanc)
		{
			if(this.ImageGris != null)
				this.ImageGris.deallocate();
			
			//On créer l'espace nécéssaire pour stoker 'ImageGris' 
			this.ImageGris= opencv_core.IplImage.create(this.getImage().width(), this.getImage().height(), opencv_core.IPL_DEPTH_8U, 1);
			
			//On transforme l'image en gris
			cvCvtColor(this.getImage(), this.getImageGris(), CV_BGR2GRAY);

		}
		
		//														Méthode detecterLesVisages
		
		public IplImage detecterLesVisages ()
		{
			// On créé un buffer
			this.storage = CvMemStorage.create(0);

			//On détecte le visage 
			this.visages = cvHaarDetectObjects(getImageGris(), cascade,storage,5.0,2	,1);
			    
			 for (int i = 0; i<visages.total(); i++)
			 {
			    	//creation du rectangle
			    	this.rectangle = new opencv_core.CvRect(cvGetSeqElem(visages, i));
			    	this.tracerContour();  	
			  }
			 
			 this.storage.deallocate();
 
			    return this.Image;
			
		}

		//														Méthode recupTete
		
		public IplImage recupObjet ()
		{
			
			try
			{
				this.visageDetecte = IplImage.createFrom(this.Image.getBufferedImage().getSubimage(this.rectangle.x(), 
					this.rectangle.y(), this.rectangle.width(), this.rectangle.height()));
			}
			catch (Exception e){
				System.out.println(e);
			}
			return this.visageDetecte;
		}
		
		public IplImage recupObjet (CvRect rect)
		{
			CvRect rect2 = rect;
			
			if (rect2.x()+rect2.width() > this.Image.width())
			{
				rect2.x(this.Image.width()-rect2.width());
			}
			
			if (rect2.y()+rect2.height() > this.Image.height())
			{
				rect2.y(this.Image.height()-rect2.height() );
			}
			
			if (rect2.x()<0)
			{
				rect2.x(0);
			}
			
			if (rect2.y()<0)
			{
				rect2.y(0);
			}
	
			try
			{
				this.visageDetecte = IplImage.createFrom(this.Image.getBufferedImage().getSubimage(rect2.x(), 
						rect2.y(), rect2.width(), rect2.height()));
			}
			catch (Exception e){
				System.out.println(e);
			}
			return this.visageDetecte;
		}
		
		public int getX(){
			return getRectangle().x() + getRectangle().width() - 50;
		}
		
		public int getY(){
			return getRectangle().y() - 80 + getRectangle().height();
		}
		
		public int getHauteur(){
			return getRectangle().width() -50 + getRectangle().x() - 70;
		}
		
		public int getLargeur(){
			return getRectangle().height() + getRectangle().y() - 100;
		}
		
		
		
		//														Méthode tracerContour
		private void tracerContour()
		{
			//on reduit la largeur de l'ellipse
	    	this.rectangle.x(this.getRectangle().x()+ (this.getRectangle().width()*10)/100);
	    	this.rectangle.width(this.getRectangle().width()- (this.getRectangle().width()*20)/100);
	    	
	    	//on reduit la hauteur de l'ellipse
	    	this.rectangle.y(this.getRectangle().y()+ (this.getRectangle().height()*5)/100);
	    	this.rectangle.height(this.getRectangle().height()- (this.getRectangle().height()*10)/100);
	    	
	    	
	    	//creation de l'ellipse en fct du rectangle. On dessine l'ellipse sur l'image.
	    	cvEllipse(getImage(),cvPoint((getRectangle().width()/2)+this.getRectangle().x(), getRectangle().height()/2+getRectangle().y()) 
	    	, cvSize(this.getRectangle().width()/2,this.getRectangle().height()/2), 360, 0f, 2*360, opencv_core.CvScalar.BLUE, 1, CV_AA, 0);

		}
	
		public boolean estNull()
		{
			return (this.Image == null || this.ImageGris == null || this.visageDetecte == null || this.rectangle.isNull());
		}
		

}
