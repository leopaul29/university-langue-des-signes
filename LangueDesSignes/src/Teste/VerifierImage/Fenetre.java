package Teste.VerifierImage;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Fenetre.PanneauLettre;

public class Fenetre extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@SuppressWarnings("unused")
	private BufferedImage image = null;
	private JPanel pan = null;
	
	public Fenetre (int largeur, int hauteur)
	{
		
		
		this.setLayout(null);
		this.setSize(largeur+15, hauteur+35);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		pan = new PanneauLettre();
		pan.setBounds(0, 0,largeur, hauteur);
		
		this.getContentPane().add(pan);
		
		this.setVisible(true);
		
	}
	
	public void setImage (BufferedImage image){ ((PanneauLettre)pan).setImage(image); }
	public BufferedImage getImage(){ return ((PanneauLettre)pan).getImage();}
	

}
