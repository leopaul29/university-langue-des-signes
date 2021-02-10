package Arbre;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import static com.googlecode.javacv.cpp.opencv_core.CV_32FC1;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateMat;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import Reconaissance.ReconPeau;

import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_ml.CvRTParams;
import com.googlecode.javacv.cpp.opencv_ml.CvRTrees;
import static com.googlecode.javacv.cpp.opencv_ml.*;


public class ImageToCvMat {
	
	//constante 
			public final static int NBR_CARACTERE = 15000;  //Représente le nombre de caractère dans le fichier 
											  //obligatoire pour parcourir tout le fichier
											  //il s'obtient en multipliant l*L    ex : 16384 = 128*128  || 4096 = 64*64
			
			final static int NBR_PHOTO = 960; 		  //Représente le nombre total de photo disponible dans le dossier arbre
											  //obligatoire pour délimité les cvMat
			
			final static int DIM_HAUTEUR = 100;	  //Représente les dimensions de l'image redimensioné 
			final static int DIM_LARGEUR = 150;	  //nous pouvons utilisé une largeur et une hateur différente
			final static int DIMENSION = 100;		  //ou utilisé les mêmes avec DIMENSION
			
			
			//Déclaration des multiples variables 
			Scanner sc1 = new Scanner(System.in);
			String  ledossier ="", filename_train = "";
			
			int [] tableau_for_cvmat = null;
			
			//valeur  pour les cvMAT		 ligne,colone,cste 
			CvMat mat_lettre = cvCreateMat(NBR_PHOTO, 1, CV_32FC1);
			CvMat mat_num = cvCreateMat(NBR_PHOTO, NBR_CARACTERE, CV_32FC1);		//=64*64
			
			
			
			//parametre arbre
			
			CvRTParams parametres_rf = null;
			CvRTrees forest = null ;
			final static int depth = 9; //max 25   
			final static int cat = 15; //max 15
			final static int arbres = 58;//50
			final static double accuracy = Math.pow(10, -3);
			
			
			ReconPeau detectPeau = null;
			
			  
		      
	public ImageToCvMat ()
	{
		 parametres_rf = new CvRTParams();     
	     
		 parametres_rf.max_depth(depth) ;
	     parametres_rf.max_categories(cat);
	     parametres_rf.nactive_vars((int)Math.sqrt(mat_num.cols() - 1));//racine du nombre de colonne dans la cvmat 0,1,0,1..
	     parametres_rf.term_crit().max_iter(arbres) ;
	     parametres_rf.term_crit().epsilon(accuracy) ;
	     parametres_rf.calc_var_importance(true); 
	     parametres_rf.nactive_vars(4);
	     
	     detectPeau = new ReconPeau();
	     forest = new CvRTrees();
	}
		      
		      
		      
	public void Apprentissage(){
		
		
		//initialisation des variables
		IplImage imageCharge = null;
		IplImage monImageNoiretBlanc = null;
		
		String lettre_full = new String();
		int lettre_int, i = 0, j = 0, ligne = 0;
		
		char laLettre = '\0';
		
		//debut
		System.out.print("Dossier source : ");
		ledossier = sc1.nextLine();
		
		File dossierImage = new File(ledossier);
		
		System.out.println("\n\n***Début de l'apprentissage***\n");

	
		for (File fichier : dossierImage.listFiles()){
			
			//Si le fichier est bien une photo pouvant être appris
			if( fichier.isFile() && fichier.getName().contains(".jpg")){
				
				//chaque fichier est mis dans source
				imageCharge = cvLoadImage(fichier.getAbsolutePath());
				 
				detectPeau.DetectCouleur(imageCharge);
				monImageNoiretBlanc  = detectPeau.getImage().clone();

				 
				System.out.println("Image : " + fichier.getName() +" en cours.. ");
				laLettre = fichier.getName().charAt(0);
				
				
				//Redimension de l'image logique (image a redimensionner, largeur_nouvelle, hauteur_nouvelle)
				monImageNoiretBlanc = Recup.redimImage(monImageNoiretBlanc, DIM_LARGEUR, DIM_HAUTEUR);
				
				
				//Traduction de la BufferedImage en String
				lettre_full = Recup.ImageToString(monImageNoiretBlanc.getBufferedImage(),laLettre);
				
				//La premiere lettre cast en int (A = 65)
				lettre_int = lettre_full.charAt(0);
				
				//ajout de la lettre à la cvMat des lettres 
				mat_lettre.put(ligne,0,lettre_int);
						
				//Tableau de la taille de la longueur de .txt, => on pourrai diviser par 3 a voir 
				tableau_for_cvmat = new int[NBR_CARACTERE];
				
				//pour toute la taille de notre fichier 
				//i represente le nb de caractere 
				//j le nb de valeur
				for ( i = 2 , j=0 ; i < lettre_full.length() ; i++){
					//si c'est un numero, on mettra dans un tableau ce numero
					if(lettre_full.charAt(i) == '0' || lettre_full.charAt(i) == '1'){
						tableau_for_cvmat[j] = lettre_full.charAt(i)-48;
						j++;
					}
					
				}
				
				//pour tout les 0 et 1, on mettra dans la CvMat 
				//on place le tableau dans la matrice
				for(j = 0; j < tableau_for_cvmat.length  ; j++){
					mat_num.put(ligne,j, tableau_for_cvmat[j]);
				}
				
				
				ligne++;
				System.out.println("Image : " + fichier.getName()+" fini et ajouté dans la cvMat");
				System.out.println("ligne : "+ligne + "\n");
			}
			
		}
          
      boolean sucess = forest.train(mat_num,CV_ROW_SAMPLE, mat_lettre , null, null, null, null, parametres_rf);
      
      if(sucess == false)
    	  return ;
     
      forest.save("arbre", "arbre");
        
}
	
	//on envoi l'image de la main 
	public char Tests (IplImage image)
	{
		IplImage monImageNoiretBlanc = null;
		if (image == null)
			return '\0';
			
		//on transforme l'image en noir et blanc 
		detectPeau.DetectCouleur(image.clone());
		monImageNoiretBlanc  = detectPeau.getImage();
		
		//on redimensionne l'image
		BufferedImage imageRedim = Recup.redimImage(monImageNoiretBlanc, DIM_LARGEUR, DIM_HAUTEUR).getBufferedImage();
			
		//on transforme l'image en String
		String test = Recup.ImageToString(imageRedim,'C');
			
		//on cree la matrice
		CvMat a = cvCreateMat(NBR_CARACTERE, 1, CV_32FC1);
		a = Recup.TxtToCvMat(test);
			
		
		//on libere la memoire
		if(monImageNoiretBlanc != null)
		{
			monImageNoiretBlanc.deallocate();
			monImageNoiretBlanc = null;
		}
		
		//on Teste la matrice dans la foret
		return (char) forest.predict(a,null);
	}
	

	public CvRTrees getTree (){ return this.forest; }
	
	
	public static void main(String[] args)  {
		
		ImageToCvMat arbre = new ImageToCvMat();
		
		arbre.Apprentissage();
		System.out.println("Le fichier arbre a été créé");
		
	}

	
	
}

	