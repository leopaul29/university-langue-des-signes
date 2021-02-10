package ApplicationV1;



import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static com.googlecode.javacv.cpp.opencv_core.*;
import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_ml.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

//import org.opencv.core.MatOfFloat;

//-Xmx<500> [M]
public class arbre {

	public static void main(String[] args) {
		
		
		String filename_train = "D_Droite_(1).txt";
		int depth = 51;
		int cat = 11;
		int arbres = 71;
		double accuracy = Math.pow(10, -5);
		CvMat mf = null, rf = null;
		CvRTParams parametres_rf = new CvRTParams();
		CvRTrees forest = new CvRTrees();
		
		
		
		System.out.println("Main");
		mf = cvCreateMat(1, 1, CV_32FC1);
		rf = cvCreateMat(1, 1, CV_32FC1);
		RandomForest.csvToFloat( mf,  rf, filename_train);
		//rf = arbre.fichierToMatrice(filename_train);
		//mf.put(0, 0, 0.0f);
		//rf = RandomForest.csvToFloat_2(rf);
		
		 //Random Forests
		  parametres_rf.max_depth(depth) ;
	      parametres_rf.max_categories(cat);
	      parametres_rf.nactive_vars((int)Math.sqrt(rf.cols() - 1));
	      parametres_rf.term_crit().max_iter(arbres) ;
	      parametres_rf.term_crit().epsilon(accuracy) ;

	      
	      //Afficher la matrice
	      /*
	      int k=0;
	      for (int i =0; i<rf.cols() ; i++)
	      {
	    	  for (int j = 0; j<rf.rows(); j ++)
	    	  {
	    		  if(k == 10000)
	    		  {
	    			  k = 0;
	    			  System.out.print("\n" + i + " "+ j);
	    		  }
	    		  
	    		  k++;
	    		  System.out.print(rf.get(j, i) + " ");
	    		  
	    			  
	    	  }
	      }
	      
	      System.out.println(rf.cols() + " " + rf.rows()); //307201 1
	      //CvMat matrice = cvCreateMat(2, 2, CV_32FC1);
	    
	      //FloatPointer valeur = new FloatPointer();
	      
	     // System.out.println(matrice.type());
	      //matrice.type(1, 5);
	      
	      //!CV_IS_MAT(train_data) || CV_MAT_TYPE(train_data->type) != CV_32FC1
	      */
	    
	     
	     boolean sucess = forest.train(mf, 1, rf , null, null, null, null, parametres_rf);
	     // boolean sucess = forest.train(data, parametres_rf);
        if (!sucess) return;
        
        
        forest.save("arbre", "");
		System.out.println("Le fichier arbre a été créé");
		

	}
	
	////
	public static CvMat fichierToMatrice ( String file)
    {
    	
		CvMat response =null;
    	//on recupere tout le contenu du fichier
    	File fichier = new File(file);
    	if (!fichier.exists())
    	{	
    		System.out.println("le fichier "+file+" n'existe pas.");
    		return null;
    	}
    	if (!fichier.isFile()){
    		System.out.println("Ce n'est pas un fichier.");
    		return null;
    	}
    	
    	String contenu = new String ();
    	BufferedReader lecture = null;
		String testeContenu= null;
			try {
				lecture = new BufferedReader(new FileReader(file));
				while((testeContenu=lecture.readLine())!=null){
					if(testeContenu!= null)
						contenu=testeContenu;
				}
				lecture.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				
		    	try {
					lecture.close();
				} catch (IOException e) {e.printStackTrace();}
			}
			
			String[] values =  contenu.split(",");
			response = cvCreateMat(1,values.length,CV_32FC1);
			
			float valeur;
			//int k = 0;
			
			for(int i=1; i<values.length-1;i++)
			{
				
				valeur = Float.parseFloat(values[i]);
				
				if(valeur == 0 || valeur == 1)
				{
					
					response.put(0,i,valeur);
					//k++;
					//System.out.println(k);
				}
				
			}
			return response;
    }
	
	
	
	
	
	
	
	static public IplImage redimImage (IplImage src, int largeur, int hauteur)
	{
		
		IplImage imageNouvelle = cvCreateImage(new CvSize(largeur, hauteur),src.depth(),src.nChannels());
		cvResize(src,imageNouvelle);
		return imageNouvelle;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
