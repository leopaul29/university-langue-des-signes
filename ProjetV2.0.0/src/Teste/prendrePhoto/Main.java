package Teste.prendrePhoto;

import java.util.Scanner;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import com.googlecode.javacv.cpp.opencv_core.IplImage;


public class Main {


	
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in);
		
		String chemin = new String();
		
		int nbPhoto = 0;
		boolean recommence = false; 
		
		threadMain thFenetre = new threadMain();
		
		Thread thread = new Thread(thFenetre);
		thread.start();
		
		IplImage image;
		
		
		do
		{
			recommence = false;
			System.out.println("Combiens de photos voulez vous? ");
			
			try
			{
				nbPhoto = scan.nextInt();
			}catch(Exception e){
				recommence = true;
				scan.nextLine();
			}
			
		}while(recommence);
		
		scan.nextLine();
		
		do
		{
			recommence = false;
			System.out.println("Le chemin : ");
			
			try
			{
				chemin = scan.nextLine();
			}catch(Exception e){
				recommence = true;
				scan.nextLine();
			}
			
		}while(recommence);
		
		System.out.println("Appuyer sur une touche quand vous êtes près... ");
		scan.nextLine();
		
		
		for(int i=0; i<nbPhoto; i++)
		{
			
			image = thFenetre.getCapture();
			cvSaveImage(chemin+i+".jpg", image);
			
			System.out.println("IMAGE "+i+".jpg fait !!!!!");
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		

	}

}
