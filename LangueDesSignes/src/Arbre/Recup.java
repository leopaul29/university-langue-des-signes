package Arbre;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import static com.googlecode.javacv.cpp.opencv_core.*;

import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_core.IplImage;


public abstract class Recup {
	
	public static String loadFile(String nom) {
		
		File f = new File(nom);
		  try {
		       BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
		       StringWriter out = new StringWriter();
		       int b;
		       while ((b=in.read()) != -1)
		           out.write(b);
		       out.flush();
		       out.close();
		       in.close();
		       return out.toString();
		    }
		    catch (IOException ie)
		    {
		         ie.printStackTrace(); 
		    }
			return null;
	}
	
	public static String cvmatToString(CvMat lacvMat){
		
		String str = "";
		
		for(int i = 0; i < lacvMat.rows() ; i ++){
			
			for(int j = 0; j < lacvMat.cols();j++){
				str+="|" +lacvMat.get(i, j);
			}
			str+="\n";
		}
		return str;
	}
	
	public static void myReshape(CvMat amodif, CvMat lamat){
		
		int col = lamat.cols();
		int ligne = lamat.rows();
		int y = 0;
	
		for (int i = 0; i<ligne ;i++){
			for (int j = 0; j <col;j++){
				amodif.put(0, y, lamat.get(i, j));
				y++;
			}
		}
	}
	
	static public IplImage redimImage (IplImage src, int largeur, int hauteur){
		
		if(src.width() == largeur && src.height() == hauteur)
			return src;
		
		IplImage imageNouvelle = cvCreateImage(new CvSize(largeur, hauteur),src.depth(),src.nChannels());
		cvResize(src,imageNouvelle);
		return imageNouvelle;
	}
	
	public static String cvmatToString(CvMat lacvMat, int lenombre, int laligne){
		
		String str = "";
		
		for(int i = 0; i < lenombre ; i++){
			str+="|" +lacvMat.get(laligne, i);
				
		}
		return str;
		
	}
	
	
	public static String ImageToString (BufferedImage image , char lalettre ) {
	
		 
		 String maChaine = lalettre + "," ;
		 	 
		 for (int y=0;y<image.getHeight();y++)
		 {
			
			 for (int x=0;x<image.getWidth();x++)
			 {
				 Color C = new Color (image.getRGB(x,y)) ;
				 if (C.getRed()<=20 && C.getGreen()<=20 && C.getBlue() <=20 )
				 {
					 maChaine=maChaine + "0," ;
				 }
				 else
				 {
					maChaine=maChaine + "1," ;
				 }

			 }
		 }
		 
		 return maChaine;
	}
	
	
	public static CvMat TxtToCvMat(String lettre_full){
	
		int [] tableau_for_cvmat = new int[ImageToCvMat.NBR_CARACTERE];

		CvMat mat_num = cvCreateMat(1, ImageToCvMat.NBR_CARACTERE, CV_32FC1);
		int j = 0;
		
		for (int i = 2 ; i < lettre_full.length() ; i++){
		//si c'est un numero, on mettra dans un tableau ce numero
			if(lettre_full.charAt(i) == '0' || lettre_full.charAt(i) == '1'){
				tableau_for_cvmat[j] = lettre_full.charAt(i)-48;
				j++;
			}
		}
		//pour tout les 0 et 1, on mettra dans la CvMat 
		for(j = 0; j < tableau_for_cvmat.length ; j++){
		int a = tableau_for_cvmat[j];
		mat_num.put(0,j,a);
		}

		return mat_num;
		}
}
