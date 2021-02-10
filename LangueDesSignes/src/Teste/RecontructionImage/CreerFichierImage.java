package Teste.RecontructionImage;

import java.util.Scanner;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

import Arbre.Recup;
import Reconaissance.ReconPeau;

import com.googlecode.javacv.cpp.opencv_core.IplImage;


public class CreerFichierImage {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		
		Scanner scanf = new Scanner(System.in);
		String adresse = null;
		char lettre = '\0';
		
		IplImage image = null;
		
		System.out.println("Adresse: ");
		adresse = scanf.nextLine();
		
		System.out.println("lettre: ");
		lettre = scanf.nextLine().charAt(0);
		
		ReconPeau obj = new ReconPeau();
		
		
		
		image = cvLoadImage(adresse);
			
		obj.DetectCouleur(image);

		Recup.ImageToString(obj.getImage().getBufferedImage(), lettre);
		
		System.out.println("Termine...");
		
		

	}

}
