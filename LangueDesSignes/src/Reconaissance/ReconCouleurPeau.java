package Reconaissance;

import static com.googlecode.javacv.cpp.opencv_core.CV_AA;
import static com.googlecode.javacv.cpp.opencv_core.CV_FILLED;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvRectangle;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;


import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class ReconCouleurPeau {

	/**
	 * @param args
	 */
	
	private BufferedImage monImage;
	
	private int[] couleurMoyen = new int [3];
	
	public static final int ROUGE = 0;
	public static final int VERT= 1;
	public static final int BLEU = 2;
	
	//private static final float tolerance = 0.30f;
	
//																Constructeur 
	public ReconCouleurPeau ()
	{
		this.monImage = null;
	}
	
	public ReconCouleurPeau (BufferedImage uneImage)
	{
		this.monImage = uneImage;
		this.moyenneCouleur(uneImage);  		  // On calcule automatiquement les moyennes. 
	}    
	
//																toString	
	
	public String toString ()
	{	
		return "Classe: la Couleur moyenne de l'image:\nRouge : " + this.couleurMoyen[ROUGE] + "\nVert : " 
				+ this.couleurMoyen[VERT]+ "\nBleu : "+ this.couleurMoyen[BLEU];
	}

//															Accesseurs de consultation
	public int [] getCouleurMoyen()
	{
		return this.couleurMoyen;
	}
	
	
	public BufferedImage getImage ()
	{
		return this.monImage;
	}
	
	
	public void setImage (BufferedImage uneImage)
	{
		this.monImage = uneImage;
		this.moyenneCouleur(uneImage);
		
	}

	
	public void moyenneCouleur (BufferedImage image)
	{

		int []rgb = new int [3];
		int R = 0, G = 0, B = 0;
		
		WritableRaster raster = image.getRaster();
		
		
		for( int y =0; y <image.getHeight(); y++)
		{
			for (int x = 0; x < image.getWidth(); x++)
			{
				raster.getPixel(x, y, rgb);
				
				R += rgb[0];
				G += rgb[1];
				B += rgb [2];
			}
		}
		
        
        //On modifie la couleur Moyenne
        couleurMoyen[ROUGE] = R / (image.getWidth()*image.getHeight());
        couleurMoyen[VERT] = G / (image.getWidth()*image.getHeight());
        couleurMoyen[BLEU] = B / (image.getWidth()*image.getHeight());
 
	}
	
	public void afficherCouleur (int[] couleur)
	{
		System.out.println("R: " + couleur[ROUGE] + " G: " +couleur[VERT]+ " B: " + couleur[BLEU]);
	}
	
	
	
	public void ColorierImage (int[] couleurM) //appliquer une couleur sur toute l'image
	{
		
		Graphics2D g2d =  this.monImage.createGraphics();
		g2d.setColor( new Color(couleurM[ROUGE] , couleurM[VERT], couleurM[BLEU]));
		g2d.fillRect(0, 0, this.monImage.getWidth(), this.monImage.getHeight());

		
	}
	
	public static void masquerObjet ( BufferedImage image, CvRect rect ) throws Exception
	{
		
			if (rect.x() < 0 || rect.y()<0 )
				throw new Exception ();
			
			CvRect rect2 = new CvRect(rect.x(), rect.y(), rect.width(), rect.height());
			
			if( (rect2.x() + rect.width()) > image.getWidth())
				rect2.width(image.getWidth()-rect2.x());
			
			if( (rect2.y() + rect.height()) > image.getHeight())
				rect2.height(image.getHeight()-rect2.y());
			
			
			Graphics2D g2d = image.createGraphics();
			g2d.setColor(Color.black);
			g2d.fillRect(rect2.x(), rect2.y(), rect2.width(), rect2.height());
	}
	
	
	//Elle permettra de masquer le visage.
	
	public void masquerObjet (ReconVisage objet, int [] couleurM) 
	{
		CvScalar couleur = new CvScalar();
		couleur.red(couleurM[ROUGE]);
		couleur.green(couleurM[VERT]);
		couleur.blue(couleurM[BLEU]);
		
		cvRectangle (objet.getImage(), cvPoint(objet.getRectangle().x(), objet.getRectangle().y()),
				cvPoint(objet.getRectangle().width()+objet.getRectangle().x(), objet.getRectangle().height()+objet.getRectangle().y()) ,couleur 
				, CV_FILLED, CV_AA, 0 );
		
		couleur = null;
	}
	
	
	
	//Elle permet de sauvegarder l'image de l'objet
	public void Sauvegarder(String nom, IplImage image)
	{
		cvSaveImage(nom, image);
	}
	
	
	
	public static float[] RGBtoHSV (int R, int G, int B) // transformer une couleur RGB en HSV
	{
		float nt_R =0;
		float nt_G = 0;
		float nt_B = 0;
		float nt_Min =0;
		float nt_Max = 0;
		
		float del_Max =0;
		float del_R =0;
		float del_G = 0;
		float del_B = 0;
		
		float H = 0;
		float S = 0;
		float V = 0;
		
		float HSV [] = new float [3];
		
		
				nt_R = ( (float)R / 255 );                      //RGB compris entre 0 et 255 
				nt_G = ( (float)G / 255 );
				nt_B = ( (float)B / 255 );

				nt_Min = Math.min( nt_R, nt_G );     //Min. RGB 
				nt_Min = Math.min(nt_Min, nt_B);
				
				
				nt_Max = Math.max( nt_R, nt_G) ;    //Max. RGB 
				nt_Max = Math.max(nt_Max, nt_B);
				
				del_Max = nt_Max - nt_Min    ;         //delta RGB 
				
				V = nt_Max;

				if ( del_Max == 0 )                      //Gris, pas de chroma... 
				{ 
				H  = 0;                                //HSV compris entre 0 et 1 
				S  = 0;
				} 
				else                                     //Valeurs chromatiques... 
				{ 
				S = del_Max / nt_Max;

				    del_R = ( ( ( nt_Max - nt_R ) / 6.0f ) + ( del_Max / 2.0f ) ) / del_Max;
				    del_G = ( ( ( nt_Max - nt_G ) / 6.0f ) + ( del_Max / 2.0f ) ) / del_Max;
				    del_B = ( ( ( nt_Max - nt_B ) / 6.0f ) + ( del_Max / 2.0f ) ) / del_Max;

				    if      ( nt_R == nt_Max ) H  = del_B - del_G;
				    else if ( nt_G == nt_Max )  H  = ( 1.0f / 3.0f ) + del_R - del_B; 
				    else if ( nt_B == nt_Max )  H  = ( 2.0f / 3.0f ) + del_G - del_R; 

				    if ( H  < 0 ) ; H  += 1;
				    if ( H  > 1 ) ; H  -= 1;
				} 
				
				HSV [0] = H;
				HSV [1] = S;
				HSV [2] = V;
			
				return HSV;
	}
	
}
