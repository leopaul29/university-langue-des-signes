package Teste.RecontructionImage;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Teste.VerifierImage.Fenetre;

public class ReconstructionImage {


	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		
		int LARGEUR = 150;
		int HAUTEUR = 100;
		
		BufferedImage image = new BufferedImage( LARGEUR, HAUTEUR, BufferedImage.TYPE_INT_RGB);
		WritableRaster trame = image.getRaster();
		
		int blanc [] = {255,255,255};
		int noir [] = {0,0,0};
		
		Scanner scanf = new Scanner(System.in);
		String chemin = null;
		String contenuFichier = null;
		
		System.out.println("Entrer un chemin: ");
		chemin = scanf.nextLine();
		
		if(chemin.charAt(chemin.length()-1) != '/')
			chemin += '/';
		
		contenuFichier = LireFichier(chemin);
		
		contenuFichier = contenuFichier.substring(1);
		contenuFichier = contenuFichier.replace(",", "");
		System.out.println(contenuFichier.length());
		
		for(int y=0; y<HAUTEUR; y++)
		{
			for(int x = 0; x <LARGEUR; x++)
			{
				if(contenuFichier.charAt(y*LARGEUR + x) == '0')
					trame.setPixel(x, y, noir);
				else
					trame.setPixel(x, y, blanc);
			}
		}
		
		Teste.VerifierImage.Fenetre fenetre = new Fenetre(LARGEUR, HAUTEUR);
		
		fenetre.setImage(image);
		

	}
	
	
	
	public static  String LireFichier (String nomFichier) 
	{
		String contenuFichier = new String();
		File fichier = new File(nomFichier);
		
		if (!fichier.exists()) return null; //si il existe pas il retourn null
		if (!fichier.isFile()) return null;//idem si ce n'est pas un fichier
		
		
		try {
			//on ouvre un flux de lecture
			BufferedReader lecture = new BufferedReader(new FileReader(nomFichier));
			String s = new String();
		
			while((s=lecture.readLine())!=null)
			{
				if(s!=null);
					contenuFichier += s+"\n";//on recupére le contenu
			}
			lecture.close();// on ferme le flux
		} catch (IOException e) {
			System.out.println("Erreur sur la lecture du fichier: "+ nomFichier);
			System.out.println(e);
			return null;
		}
		
		return contenuFichier;
	}

}
