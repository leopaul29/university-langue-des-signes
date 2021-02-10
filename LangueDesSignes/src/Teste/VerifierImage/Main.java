package Teste.VerifierImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import Reconaissance.ReconPeau;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import Arbre.Recup;

public class Main {

	/**
	 * @param args
	 */
	
	public static final int LARGEUR = 150;
	public static final int HAUTEUR = 100;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		String chemin = new String();
		Scanner scanf = new Scanner(System.in);
		
		//init image
		BufferedImage image = null;
		
		//init ImageNoirEtBlanc
		ReconPeau detectPeau =  new ReconPeau();
		
		//init dossier
		File folder = null;
		
		//init fenetre
		Fenetre maFenetre = new Fenetre(LARGEUR, HAUTEUR);
		
		
		System.out.println("Entrer un chemin: ");
		chemin = scanf.nextLine();
		
		if(chemin.charAt(chemin.length()-1) != '\\' )
			chemin += '\\';
		
		folder = new File(chemin);
		
		for(File fichier : folder.listFiles())
		{
			if(fichier.getName().contains(".jpg") || fichier.getName().contains(".JPG"))
			{
				try {
					image = ImageIO.read(fichier);
				} catch (IOException e) {
					System.out.println("Erreur de chargement du fichier: " + fichier.getAbsolutePath() );
					image = null;
					e.printStackTrace();
				}
				
				if(image != null)
				{
					detectPeau.DetectCouleur(Recup.redimImage(IplImage.createFrom(image), Teste.VerifierImage.Main.LARGEUR ,
							Teste.VerifierImage.Main.HAUTEUR) );
					maFenetre.setImage(detectPeau.getImage().getBufferedImage());
					maFenetre.setTitle(fichier.getName());
					maFenetre.repaint();
				}
					
			}
			
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		

	}

}
