package ApplicationV1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static com.googlecode.javacv.cpp.opencv_core.*;
import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_ml.CvRTrees;
import com.googlecode.javacv.cpp.opencv_ml.CvRTParams;
public  class MainForest{
	
	

	public static void main(String[] args) {

   
		String filename_train = "D_Droite_(1).txt";
		int depth = 51;
		int cat = 11;
		int arbres = 71;
		double accuracy = Math.pow(10, -5);
		 CvMat mf = null, rf = null;
		 CvRTParams parametres_rf = new CvRTParams();

        System.out.println("Maiiiiiiiiiiiiiiiiin ");
        
        //Random Forests
       // RandomForest.csvToFloat( mf,  rf, filename_train);
        rf = MainForest.fichierToMatrice(filename_train);

        
        parametres_rf.max_depth(depth) ;
        parametres_rf.max_categories(cat);
        parametres_rf.nactive_vars((int)Math.sqrt(rf.cols() - 1));
        parametres_rf.term_crit().max_iter(arbres) ;
        parametres_rf.term_crit().epsilon(accuracy) ;
        
        CvRTrees arbre = new CvRTrees();
        

        boolean sucess = arbre.train(mf, 1, rf, null, null, null, null, parametres_rf);
        if (!sucess) return;
        
        //#region command code
        /*for (int i = 0; i < mf.Rows; i++)
        {
            for (int j = 0; j < mf.Cols; j++)
            {
                Console.WriteLine(mf[i, j]);
            }
        }*/

       // #endregion
       // InitializeComponent();

    }
	
	@SuppressWarnings("unused")
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
    	String mot = new String();
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
			

			response = cvCreateMat(1,contenu.split(",").length,CV_32FC1);
			
			int valeur;
			
			for(int i=1; i<contenu.length();i++)
			{
	
				valeur = contenu.charAt(i) - 48;
				
				if(valeur == 0 || valeur == 1)
					response.put(0,1,valeur);
				
			}
			return response;
    }
	
	
   }