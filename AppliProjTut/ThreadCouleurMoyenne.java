package ApplicationV1;



import  ApplicationV1.ReconPeau;



public class ThreadCouleurMoyenne implements Runnable {
	
	private ReconPeau couleurMoyenne = new ReconPeau();

	
	public ThreadCouleurMoyenne (ReconPeau couleur)
	{
		couleurMoyenne = couleur;
	}

	@Override
	public void run() {

		Fenetre couleur = new Fenetre(true);
		while(true){
			
			
			if(couleurMoyenne.getImage() != null)
    		 {
    			 //calcule de la couleur Moyenne
				
	    		 couleurMoyenne.ColorierImage(couleurMoyenne.getCouleurMoyen());
	    		 
	    		 //fenêtre
	    		 couleur.setImage(couleurMoyenne.getImage().getBufferedImage());
	    		 couleur.repaint();
	    		 
	    		 try {
		 				Thread.sleep(3000);
		 				
		 				
		 			} catch (InterruptedException e) {
		 				// TODO Auto-generated catch block
		 				e.printStackTrace();
		 			}

    		 }

			}

		}
}
