package Reconaissance;

import static com.googlecode.javacv.cpp.opencv_core.CV_AA;
import static com.googlecode.javacv.cpp.opencv_core.cvEllipse;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSeqElem;
import static com.googlecode.javacv.cpp.opencv_core.cvLoad;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_objdetect.cvHaarDetectObjects;
import java.awt.image.BufferedImage;
import com.googlecode.javacv.cpp.opencv_core;
import com.googlecode.javacv.cpp.opencv_objdetect;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
public class ReconVisage {
	
	
		private static final String CASCADE_FILE =  "C:\\opencv\\data\\haarcascades\\haarcascade_frontalface_alt.xml";
		private opencv_objdetect.CvHaarClassifierCascade cascade = null;
		
		
		private IplImage image = null; // L'image ou l'on veut y retrouver la tête
		private IplImage imageGris = null;
		
		private opencv_core.CvMemStorage storage = null; // un block de mémoire
	
		private opencv_core.CvSeq visages = null; //
		private CvRect rectangle = null;
		
		
		public ReconVisage ()
		{
			cascade = new opencv_objdetect.CvHaarClassifierCascade(cvLoad(CASCADE_FILE)); 
			storage = CvMemStorage.create(0);
			visages = new opencv_core.CvSeq(); 
			rectangle = new CvRect();
		}
		
		//													Constructeur
		
		public  ReconVisage (IplImage image )
		{
			cascade = new opencv_objdetect.CvHaarClassifierCascade(cvLoad(CASCADE_FILE)); 
			storage = CvMemStorage.create(0);
			visages = new opencv_core.CvSeq(); 
			rectangle = new CvRect();
			this.image =  image;
		}
		
		
		
		public void free()
		{
			
			
			this.image = null;
			
			if(this.storage != null)
			{
				this.storage.free_space();
				this.storage = null;
			}
			
			if(this.visages != null)
			{
				this.visages.free_blocks();
				this.visages = null;
			}
			
			this.imageGris = null;
			this.rectangle = null;
			
			
		}
		
		

		//													Accesseur de consultation
		public IplImage getImage (){return this.image;}
		
		public CvRect getRectangle (){
			if(this.rectangle != null)
				return new CvRect(rectangle.x(), rectangle.y(), rectangle.width(), rectangle.height());
			else
				return null;
		}
		
		public void setRectangle (CvRect rect){ this.rectangle = rect;}
		
		//													Accesseur de modification
		public void setImage (IplImage image)
		{
			if(this.image != null && image != null)
				this.image = null;
			
			this.image = image;
		}
		
		//														Méthode transImageGris
		
		public IplImage transImageGris (IplImage image) 	//methode pour transformer une image en monochromatique (noir/blanc)
		{
			
			//On créer l'espace nécéssaire pour stoker 'ImageGris' 
			this.imageGris = IplImage.create(image.width(), image.height(), opencv_core.IPL_DEPTH_8U, 1);
			
			//On transforme l'image en gris
			cvCvtColor(image, imageGris, CV_BGR2GRAY);
			
			
			
			return imageGris;
		}
		
		//														Méthode detecterLesVisages
		
		public IplImage detecterLesVisages ()
		{
			
			
			//On détecte le visage 
			this.visages = cvHaarDetectObjects(transImageGris(image), cascade,storage,1.8,1,1);
			
			for (int i = 0; i<visages.total(); i++)
			    	this.rectangle = new opencv_core.CvRect(cvGetSeqElem(visages, i));
		
 
			 return this.image;
			
		}

		//														Méthode recupObjet
		// On recupere la zone de l'image par rapport a l'objet rectangle de cette classe
		public IplImage recupObjet ()
		{
			IplImage image = null;
			
			try
			{
				image= IplImage.createFrom(this.image.getBufferedImage().getSubimage(this.rectangle.x(), 
					this.rectangle.y(), this.rectangle.width(), this.rectangle.height()));
			}
			catch (Exception e){
				System.out.println(e);
			}
			
			return image;
		}
		
		// idem que la methode precedente
		public static IplImage recupObjet (IplImage imageSrc, CvRect rect)
		{
			CvRect rect2 = rect;
			IplImage image = null;
			
			if (rect2.x()+rect2.width() >= imageSrc.width())
				rect2.x(imageSrc.width()-rect2.width()-1);
			
			if (rect2.y()+rect2.height() >= imageSrc.height())
				rect2.y(imageSrc.height()-rect2.height()-1 );
			
			if (rect2.x()<0)
				rect2.x(0);
			
			if (rect2.y()<0)
				rect2.y(0);
			
			
			try
			{
				image = IplImage.createFrom(imageSrc.getBufferedImage().getSubimage(rect2.x(), 
						rect2.y(), rect2.width(), rect2.height()));
			}
			catch (Exception e){
				System.out.println(e);
			}
			return image;
		}
		
		
		public BufferedImage recupObjetBuffer ()
		{
			CvRect rect2 = new CvRect(this.rectangle.x(), rectangle.y(), rectangle.width(), rectangle.height());
			
			
			//on verifie sur rectangle est bien encadre dans l'image
			//dans le cas contraire on le recadre a l'interieure
			if (rect2.x()+rect2.width() > this.image.width())
			{
				if(rect2.width()> this.image.width())
				{
					rect2.x(0);
					rect2.width(this.image.width());
				}
				else
					rect2.x(this.image.width()- (rect2.width()+1) );
				
			}
			
			if (rect2.y()+rect2.height() > this.image.height())
			{
				if(rect2.height()> this.image.height())
				{
					rect2.y(0);
					rect2.height(this.image.height());
				}
				else
					rect2.y(this.image.height()- (rect2.height()+1) );
			}
			
			if (rect2.x()<0)
				rect2.x(0);
			
			if (rect2.y()<0)
				rect2.y(0);
			
			if(rect2.width() <= 0 )
				rect2.width(1);
			
			if(rect2.height() <= 0 )
				rect2.height(1);
			
			return this.image.getBufferedImage().getSubimage(rect2.x(), rect2.y(), rect2.width(), rect2.height());
		}
		
		public static BufferedImage recupObjetBuffer (BufferedImage image , CvRect rect)
		{
			CvRect rect2 = new CvRect(rect.x(), rect.y(), rect.width(), rect.height());
			
			
			//on verifie sur rectangle est bien encadre dans l'image
			//dans le cas contraire on le recadre a l'interieure
			if (rect2.x()+rect2.width() > image.getWidth())
			{
				if(rect2.width()> image.getWidth())
				{
					rect2.x(0);
					rect2.width(image.getWidth());
				}
				else
					rect2.x(image.getWidth() - (rect2.width()+1) );
				
			}
			
			if (rect2.y()+rect2.height() > image.getHeight())
			{
				if(rect2.height()> image.getHeight())
				{
					rect2.y(0);
					rect2.height(image.getHeight());
				}
				else
					rect2.y(image.getHeight()- (rect2.height()+1) );
			}
			
			if (rect2.x()<0)
			{
				rect2.x(0);
			}
			
			if (rect2.y()<0)
			{
				rect2.y(0);
			}
			
			if(rect2.width() <= 0 )
				rect2.width(1);
			
			if(rect2.height() <= 0 )
				rect2.height(1);
			
			return image.getSubimage(rect2.x(), rect2.y(), rect2.width(), rect2.height());
		}
		
		
		
		
		//														Méthode tracerContour
		@SuppressWarnings("unused")
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
			return (this.image == null  || this.rectangle.isNull());
		}
		

}
