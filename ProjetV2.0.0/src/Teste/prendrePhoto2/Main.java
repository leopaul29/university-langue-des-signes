package Teste.prendrePhoto2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;


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
			
			try {
				ImageIO.write(thFenetre.getCapture(), "JPG", new File(chemin+i+".jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("IMAGE "+i+".jpg fait !!!!!");
			
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		

	}

}
