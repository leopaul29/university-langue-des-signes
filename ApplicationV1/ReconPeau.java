package ApplicationV1;

import static com.googlecode.javacv.cpp.opencv_core.CV_AA;
import static com.googlecode.javacv.cpp.opencv_core.CV_FILLED;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvRectangle;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvShowImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class ReconPeau {

	/**
	 * @param args
	 */
	
	private IplImage monImage;
	private  BufferedImage buffer; // il va nous permettre de recupérer chaque pixel. 
	
	private int[] couleurMoyen = new int [3];
	private int[] couleurMoyenHaut = new int [3];
	private int[] couleurMoyenBas = new int [3];
	
	public static final int ROUGE = 0;
	public static final int VERT= 1;
	public static final int BLEU = 2;
	
	//private static final float tolerance = 0.30f;
	
//																Constructeur 
	public ReconPeau ()
	{
		this.monImage = null;
		this.buffer = null;
	}
	
	public ReconPeau (IplImage uneImage)
	{
		this.monImage = uneImage.clone(); // j'envoie une copie de l'image.
		setBufferImage ( this.monImage);  // On construit le buffer en fct de la nouvelle Image
		this.setCouleurMoyen();   		  // On calcule automatiquement les moyennes. 
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
	
	public int [] getCouleurMoyenHaut()
	{
		return this.couleurMoyenHaut;
	}
	
	public int[] getCouleurMoyenBas()
	{
		return this.couleurMoyenBas;
	}
	
	public int getMoyenRouge()
	{
		return this.couleurMoyen[ROUGE];
	}
	
	public int getMoyenVert()
	{
		return this.couleurMoyen[VERT];
	}
	
	public int getMoyenBleu()
	{
		return this.couleurMoyen[BLEU];
	}
	
	public int getMoyenBasRouge()
	{
		return this.couleurMoyenBas[ROUGE];
	}
	
	public int getMoyenBasVert()
	{
		return this.couleurMoyenBas[VERT];
	}
	
	public int getMoyenBasBleu()
	{
		return this.couleurMoyenBas[BLEU];
	}
	
	public int getMoyenHautRouge()
	{
		return this.couleurMoyenHaut[ROUGE];
	}
	
	public int getMoyenHautVert()
	{
		return this.couleurMoyenHaut[VERT];
	}
	
	public int getMoyenHautBleu()
	{
		return this.couleurMoyenHaut[BLEU];
	}
	
	public IplImage getImage ()
	{
		return this.monImage;
	}
	
	public BufferedImage getBufferImage ()
	{
		return this.buffer;
	}
	
//															Accesseur de modification
	
	public void setBufferImage (BufferedImage buffer)
	{
		this.buffer = buffer;
	}
	
	public void setBufferImage (IplImage image)
	{
		this.buffer = image.getBufferedImage();
	}
	
	public void setImage (IplImage uneImage)
	{
		this.monImage = uneImage.clone();
		setBufferImage(uneImage);
		this.setCouleurMoyen();
		
	}
	
// 															Méthode interne
	private void setCouleurMoyen()
	{
		this.MoyenneCouleur(); //on calcule la couleur Moyenne
		//this.setCouleurMoyenHaut();// on calcule la couleur MoyenneHaut
		//this.setCouleurMoyenBas();// on calcule la couleur MoyenneBas
	}
	

/*	private void setCouleurMoyenHaut()
	{
		
		//on fait un calcule de pourcentage en fct de la tolérance.
		this.couleurMoyen[ROUGE] =  (int) ((double) this.couleurMoyen[ROUGE] * ( 1.0d + (double) tolerance ));
		this.couleurMoyen[VERT] =  (int) ((double) this.couleurMoyen[VERT] * ( 1.0d + (double) tolerance ));
		this.couleurMoyen[BLEU] =  (int) ((double) this.couleurMoyen[BLEU] * ( 1.0d + (double) tolerance ));

	}
	
	private void setCouleurMoyenBas()
	{
		this.couleurMoyen[ROUGE] =  (int) ((double) this.couleurMoyen[ROUGE] * ( 1.0d - (double) tolerance ));
		this.couleurMoyen[VERT] =  (int) ((double) this.couleurMoyen[VERT] * ( 1.0d - (double) tolerance ));
		this.couleurMoyen[BLEU] =  (int) ((double) this.couleurMoyen[BLEU] * ( 1.0d - (double) tolerance ));
	}*/

	
	public void MoyenneCouleur ()
	{

		int RGB [][][] = new int [256][256][256]; // On crée un tableau à 3 dimensions
		
		int R = 0;
		int G = 0;
		int B = 0;
		
		int[] rgb = new int[3]; // Se tableau va nous permettre de Récupérer les valeurs RGB pour chaque pixel.

        Raster trame = this.getBufferImage().getRaster(); // on envoie l'image dane la trame
        // La trame vas nous permettre de récupérer les pixels 
        
        for (int y=0; y<this.getBufferImage().getHeight(); y++) //on parcour toute la trame qui contient l'image.
        {
           for (int x=0; x<this.getBufferImage().getWidth(); x++)
           {
               // Pour chaque pixel:
        	   trame.getPixel(x, y, rgb); //on recuper les valeur RGB dans le tableau rgb[]
               RGB [rgb[0]][rgb[1]][rgb[2]]++; // on incremente la  valeur se trouvant a l'indice RGB recupéré précédement.
              //Rappel: On récupére la fréquence 	
               
               //On récupére les indices où se trouve la plus grande fréquence.
               //Les indices vont correspondre aux valeurs RGB
            if (RGB[R][G][B] < RGB [rgb[0]][rgb[1]][rgb[2]])
   			{
   				
   				R=rgb[0];
   				G=rgb[1];
   				B=rgb[2];
   			}
               
           }   
           
        }
        
        //On modifie la couleur Moyenne
        this.couleurMoyen[ROUGE] = R;
        this.couleurMoyen[VERT] = G;
        this.couleurMoyen[BLEU] = B;

 
	}
	
	public void afficherCouleur (int[] couleur)
	{
		System.out.println("R: " + couleur[ROUGE] + " G: " +couleur[VERT]+ " B: " + couleur[BLEU]);
	}
	
	public void afficherImage ()
	{
		cvShowImage("Image",getImage());
		cvWaitKey();
	}
	
	
	public void ColorierImage (int[] couleurM) //appliquer une couleur sur toute l'image
	{
		CvScalar couleur = new CvScalar();
		couleur.red(couleurM[ROUGE]);
		couleur.green(couleurM[VERT]);
		couleur.blue(couleurM[BLEU]);
		
		cvRectangle(this.getImage(),  cvPoint(0,0),cvPoint(this.getImage().width(),this.getImage().height()), couleur, CV_FILLED, CV_AA, 0 );
		
		couleur = null;
		
	}
	
	
	//															Méthode Masquer Objet
	
	// elle permettra de masquer le visage en appliquant un rectangle de couleurs sur l'objet
	
	
	//elle modifie directement l'image de l'objet
	public void masquerObjet ( int [] couleurM, int x, int y , int largeur, int hauteur )
	{
		if (x < 0 || y<0 || largeur < 0 || hauteur<0 )
			return;
		
		CvScalar couleur = new CvScalar();
		couleur.red(couleurM[ROUGE]);
		couleur.green(couleurM[VERT]);
		couleur.blue(couleurM[BLEU]);
		
		cvRectangle(this.getImage(),  cvPoint(x,y),cvPoint(x+largeur,y+hauteur), couleur, CV_FILLED, CV_AA, 0 );
		
		couleur = null;
	}
	
	
	// Méthode static. 
	public static IplImage masquerObjet ( IplImage image, int [] couleurM, int x, int y , int largeur, int hauteur )
	{
		if (x < 0 || y<0 || largeur < 0 || hauteur<0 )
			return null;
		
		CvScalar couleur = new CvScalar();
		couleur.red(couleurM[ROUGE]);
		couleur.green(couleurM[VERT]);
		couleur.blue(couleurM[BLEU]);
		
		cvRectangle(image ,  cvPoint(x,y),cvPoint(x+largeur,y+hauteur), couleur, CV_FILLED, CV_AA, 0 );
		couleur = null;
		return image;
	}
	
	
	
	// Méthode static a partire d'un rectangle
	public static IplImage masquerObjet ( IplImage image, int[] couleurM, CvRect rect ) throws Exception
	{
			if (rect.x() < 0 || rect.y()<0 || rect.width() < 0 || rect.height()<0 )
				throw new Exception ();
			CvScalar couleur = new CvScalar();
			couleur.red(couleurM[ROUGE]);
			couleur.green(couleurM[VERT]);
			couleur.blue(couleurM[BLEU]);
			
			cvRectangle(image ,  cvPoint(rect.x(),rect.y()),cvPoint(rect.x()+rect.width(),rect.y()+rect.height()), couleur, CV_FILLED, CV_AA, 0 );
			
			couleur = null;
			return image;
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
	
	public void free ()
	{
		this.monImage.deallocate();
		this.buffer.flush();
	}
	

	

}
