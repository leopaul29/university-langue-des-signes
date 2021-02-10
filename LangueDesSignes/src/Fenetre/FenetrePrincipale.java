package Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetrePrincipale extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final int LARGEUR = 450;
	public static final int HAUTEUR = 550;
	
	private PanneauVideo paneau = null;
	
	private JButton boutonDemarrer = null;
	private JButton boutonArreter = null;
	private JButton boutonQuitter = null;
	
	boolean arreter = false;
	boolean quitter = false;
	
	
	
	 public FenetrePrincipale(int x, int y, JPanel pan) {
		
		this.paneau = (PanneauVideo) pan;
		
		this.setLocation(x, y);
		this.setTitle("Langue Des Signes");
		this.setSize(LARGEUR, HAUTEUR);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		
		//preparation des Bouttons
		boutonDemarrer = new JButton("Démarrer");
		boutonArreter = new JButton("Arréter");
		boutonQuitter = new JButton("Quitter");
		
		
		boutonDemarrer.setBounds(50, 20, 100, 30);
		boutonArreter.setBounds(boutonDemarrer.getX() + boutonDemarrer.getWidth() + 20, boutonDemarrer.getY(), 100, 30);
		boutonQuitter.setBounds(boutonArreter.getX() + boutonArreter.getWidth() + 20, boutonDemarrer.getY(), 100, 30);
		
		
		boutonDemarrer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paneau.setBoutonArreter(false);
				arreter = false;
			}
		});
		
		
		boutonArreter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paneau.setBoutonArreter(true);
				arreter = true;
			}
		});
		
		boutonQuitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paneau.setBoutonQuitter(true);
				quitter = true;
				dispose();
			}
		});
		
		this.add(boutonDemarrer);
		this.add(boutonArreter);
		this.add(boutonQuitter);
		
		//Preparation du panel
		
		this.paneau.setBounds(0, boutonDemarrer.getY()+boutonDemarrer.getHeight() + 20,
				this.getWidth(), FenetrePrincipale.HAUTEUR- (boutonDemarrer.getY()+20) );
		this.add(paneau);
		
		this.addKeyListener(paneau);
		
		this.setFocusable(true);
		this.setVisible(true);
	}
	 
	 
	 public FenetrePrincipale(int x, int y, PanneauImageNoirEtBlanc pan){
		
		 	PanneauImageNoirEtBlanc panNoirEtBlanc = pan;
		 
		 	this.setLocation(x, y);
			this.setTitle("Image Noir et Blanc");
			this.setSize(LARGEUR, HAUTEUR);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLayout(null);
			
			//Preparation du panel
			
			panNoirEtBlanc.setBounds(0,0,FenetrePrincipale.LARGEUR, FenetrePrincipale.HAUTEUR );
			this.add(panNoirEtBlanc);
			
			this.addKeyListener(panNoirEtBlanc);
			
			this.setVisible(true);
	 }
	 
	 public FenetrePrincipale(int x, int y, PanneauLettre pan){
			
		 	PanneauLettre panneau = pan;
		 
		 	this.setLocation(x, y);
			this.setTitle("Image Lettre");
			this.setSize(LARGEUR, HAUTEUR);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLayout(null);
			
			//Preparation du panel
			
			panneau.setBounds(0,0,FenetrePrincipale.LARGEUR, FenetrePrincipale.HAUTEUR );
			this.add(panneau);
			
			this.setVisible(true);
	 }
	 
	 public boolean appliEstArrete () { return this.arreter;}
	 public boolean appliEstQuitter () {return this.quitter;}
	 
	
	

}
