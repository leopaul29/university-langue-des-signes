package Teste.TesteArbre;

import java.io.File;
import java.util.Scanner;

import Arbre.ImageToCvMat;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;

public class TesteArbre {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanf = new Scanner(System.in);
		String chemin = new String();
		
		IplImage image = null;
		
		File dossier = null ;
		
		ImageToCvMat arbre = new ImageToCvMat();
		arbre.getTree().load("arbre", "arbre");
		
		
		char lettre = '\0';
		int nbImage = 0;
		int testeJuste = 0;
		
		
		
		System.out.println("Entrée un chemin: ");
		
		chemin = scanf.nextLine();
		
		
		if(chemin.charAt(chemin.length()-1) != '/')
			chemin += '/';
		
		dossier = new File(chemin);
		
		nbImage = dossier.list().length;
		
		
		for(String nomFichier :  dossier.list())
		{
			image = cvLoadImage(chemin+ nomFichier);
			
			lettre = arbre.Tests(image);
			
			System.out.print(nomFichier);
			
			if(lettre == nomFichier.charAt(0) /*&& arbre.getTaux()>= 0.7f*/ )
			{
				testeJuste++;
				System.out.println(" " + lettre+ " ok!! ");
			}
			else
			{
				System.out.println(" " + lettre+ " FAUX.... ");
			}
			
			
			lettre= '\0';
			
		}
		
		
		System.out.println("Nb Photo: " + nbImage);
		System.out.println("Nb teste reussie : " + testeJuste);
		System.out.println( "Taux image juste en % : " + ( (double) ((double)testeJuste/(double)nbImage)*100) + "%");
	
		

	}

}
