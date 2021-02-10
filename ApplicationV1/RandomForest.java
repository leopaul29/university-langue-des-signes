package ApplicationV1;

import static com.googlecode.javacv.cpp.opencv_core.CV_32FC1;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateMat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;


import com.googlecode.javacv.cpp.opencv_core.CvMat;
//import java.nio.file.Files;

public  class RandomForest{

    public static void record_donnees(String filename, Double[] donnees)
    {
    	
    	File fichier = new File(filename);
    	PrintWriter ecriture = null;
		
    	try {
			ecriture = new PrintWriter(new BufferedWriter(new FileWriter(fichier)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String contenu = new String ("");
    	
    	for (int i = 0; i < donnees.length; i++)
        {
            contenu += donnees[i] + ";";

        }
    	
    	ecriture.println(contenu);
    	ecriture.close();
    	
    }


	public static void csvToTableau( CvMat tableau, CvMat response, String file)
    {
    	
    	//on recupere tout le contenu du fichier
    	File fichier = new File(file);
    	if (!fichier.exists())
    	{	
    		System.out.println("le fichier "+file+" n'existe pas.");
    		return;
    	}
    	if (!fichier.isFile()){
    		System.out.println("Ce n'est pas un fichier.");
    		return;
    	}
    	
    	//String contenu = new String ();
    String contenu = null;
    	//String mot = new String();
    	BufferedReader lecture = null;
		String testeContenu= null;
			try {
				lecture = new BufferedReader(new FileReader(file));
				while((testeContenu=lecture.readLine())!=null){
					if(testeContenu!= null)
						contenu += testeContenu;
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
			
	
			String[] values = contenu.split(",");
		     int varCount = values.length;
		     
		     response.rows(1);
		     response.cols(1);
		     
			
		     tableau.rows(1);
		     tableau.cols(varCount - 1);
		    
		     
		  
			
		/*	for(int i=1; i<contenu.size();i++)
			{
				Integer valeur = new Integer(contenu.charAt(i));
				valeur -= 48;
				//System.out.println(valeur);
				
				
			}*/
			
//			 for (int j = 0; j <= contenu.size()-1; j++)
//	            {
	                String[] valeurs = contenu.split(",");
	                for (int i = 1; i < valeurs.length; i++)
	                {
	                    float res;
	                   
	                    
	                   res = Float.parseFloat(valeurs[varCount -1]);
	                    
	                    response.put(0,0,res);
	                    String v = valeurs[i];
	                    //v = v.replace(',', '.');
	                    float d;
	                    d = Float.parseFloat(v);
	                    tableau.put(0, i-1,d);
	                    

	                }
	        //    }
			
			System.out.println("");

    }

    /*
     * 
     */ 
    public static void stringToTableau_1(String str,  Double []str_t,  Double str_r){
        String[] valeurs = str.split(";");
        int varCount = valeurs.length;
        str_t = new Double[varCount];
        str_r = null;
        for (int i = 0; i < valeurs.length; i++)
        {
        	Double valeur = new Double(valeurs[varCount - 1]);
        	str_r= valeur.doubleValue();
            
        	String v = valeurs[i];
            
        	valeur = new Double(v);
            str_t[i] = valeur.doubleValue();
        }
    }

 
    public static void stringToTableau_2(String str,  Double[] str_t)
    {
        String[] valeurs = str.split(";");
        int varCount = valeurs.length;
        str_t = new Double[varCount-1];
        for (int i = 0; i < varCount - 1; i++)
        {
            String v = valeurs[i];
            Double d = new Double(v);
            str_t[i] = d;
        }
    }

    
    
    /*
     * Convertit les lignes du fichier file en ligne d'une matrice
     * */
    @SuppressWarnings("null")
	public static void csvToTableau_2( CvMat tableau, CvMat response, String file)
    {
    	int i=0;
    	//on recupere tout le contenu du fichier
    	File fichier = new File(file);
    	if (!fichier.exists()) return;
    	if (!fichier.isFile()) return;
    	
    	String []rows = null;
    	BufferedReader lecture = null;
		try {
			lecture = new BufferedReader(new FileReader(file));

    	
		    	while(lecture.readLine()!=null){
		    		rows[i]=lecture.readLine();
		    	}

    	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			try {
				lecture.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
        int varCount = rows[0].split(";").length;
        tableau =  cvCreateMat( rows.length - 1, varCount, CV_32FC1);
        response = cvCreateMat( rows.length - 1, 1, CV_32FC1);
        

        for (int j = 1; j < rows.length; j++)
        {
            String[] valeurs = rows[j].split(";");
            for ( i = 0; i < valeurs.length; i++)
            {
                Double res = new Double(valeurs[varCount - 1]);
                
                response.put(j-1, 0, res.doubleValue());
                String v = valeurs[i];
               
                Double d = new Double(v);
                tableau.put(j-1, i, d.doubleValue());
            }
        }
    }

    
    
    /*
     * Convertit du type Matrix<double> au type tableau [,]
     * */
    public static void matrixToTableau(CvMat m,  Double[][] t)
    {

        t = new Double[m.rows()][ m.cols()];

        for (int i = 0; i < m.rows(); i++)
        {
            for (int j = 0; j < m.cols(); j++)
            {
                t[i][j] = m.get(i, j);
            }
        }
    }


    /*
     * Cette fonction récupère la ligne @i du tableau @m et le copie dans le tableau @ligne
     * */
    public static void getRow(Double[][] m,  Double[] ligne, int i)
    {
        ligne = new Double[m[0].length];

        for (int j = 0; j < m[0].length; j++)
        {
            ligne[j] = m[i][j];
        }
    }

    /*
     * Cette fonction convertit le fichier @filename en Matrix<float> 
     * sans prendre en compte les lignes du fichier dont la dernière colonne est nulle 
     * */
    @SuppressWarnings("null")
	public static void csvToFloat_( CvMat d, CvMat r, String filename)
    {
        CvMat data=null, response=null;

       csvToTableau( data, response, filename);

        LinkedList<Integer> liste = new LinkedList<Integer>() ;
       
        for (int i = 0; i < response.rows(); i++)
        {
            for (int j = 0; j < response.cols(); j++)
            {
                if (response.get(i, j) != 0)
                {
                    liste.add(i);
                }
            }
        }
        
        d = new CvMat();
        d.rows(liste.size());
        d.cols(data.cols());
        
        r = new CvMat();
        r.rows(liste.size());
        r.cols(response.cols());

        //Console.WriteLine(d.Rows);
        //Console.WriteLine(d.Cols);

        for (int i = 0; i < liste.size(); i++)
        {
        	
        	r.put(i, 0, (float)response.get(liste.get(i), 0) );
            for (int j = 0; j < data.cols(); j++)
            {
            	d.put(i,j,(float) data.get(liste.get(i), j));
            }

        }

    }


    public static CvMat csvToFloat_2(CvMat /*Float*/ mat)
    {
    	CvMat resultat = cvCreateMat(mat.rows(), mat.cols(), CV_32FC1);
       // mat = new Matrix<float>(d.Cols, d.Rows);
        
        //Console.WriteLine(mat.Rows);
        //Console.WriteLine(mat.Cols);
        for (int i = 0; i < mat.rows(); i++)
        {
            for (int j = 0; j < mat.cols(); j++)
            {
                resultat.put(i, j, (float)mat.get(i, j));
            }

        }
        
        return resultat;

    }

    /*
     * 
     */ 
    public static void csvToFloat(CvMat d /*float*/,  CvMat r /*float*/, String filename)
    {
       CvMat data= null, response= null;//double
       
	     response = cvCreateMat(1, 1, CV_32FC1);
	     data = cvCreateMat(1, 1, CV_32FC1);

        csvToTableau( data, response, filename);
        
       // d = new CvMat();
        d.rows(data.rows());
        d.cols(data.cols());
  
        
      //  r = new CvMat();
        r.rows(response.rows());
        r.cols(response.cols());
        
        
        /*d = cvCreateMat(data.cols(), data.rows(), CV_32FC1);
        r =cvCreateMat(response.cols(), response.rows(), CV_32FC1);*/
        //Console.WriteLine(d.Rows);
        //Console.WriteLine(d.Cols);
        
        for (int i = 0; i < data.rows(); i++)
        {

            r.put(i, 0, (float)response.get(i, 0));
            for (int j = 0; j < data.cols(); j++)
            {
                d.put(i, j,(float)data.get(i, j)); 
            }

        }
        
        //System.out.println(d);
        //System.out.println(r);
    }

    /*
     * Cette fonction calcule le nombre d'éléments de la classe v dans la base d'apprentissage
     * */
    public static double moyenne(CvMat r, float v)
    {
        double moy = 0;
        for (int i = 0; i < r.rows(); i++)
        {
            for (int j = 0; j < r.cols(); j++)
            {
                if (r.get(i, j) == v)
                {
                    moy++;
                }
            }
        }

        return moy;

    }   
	
}
