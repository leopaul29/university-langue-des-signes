package ApplicationV1;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

 
public class Fenetre extends JFrame {
 
	private static final long serialVersionUID = 1L;

	  private JPanel boutonPane = new JPanel();
	  private JButton bouton = new JButton("Démarrer");
	  private JButton bouton2 = new JButton("Arréter");
	  private JButton bouton3 = new JButton("Quitter");
	  private PanneauVideo  animation = new PanneauVideo();
	
	  CardLayout cl = new CardLayout();
	  JPanel content = new JPanel();
  
	   private Boolean continuerWebcam = true;
	   private Boolean continuerAppli = true;
 
  public Fenetre(boolean valeur){
    this.setTitle("Fenetre");
    this.setSize(600, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
 
 
   
    
    //Définition de l'action du bouton
    bouton.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent event){
    	continuerWebcam  = true;
      }
    });
         
    
    //Définition de l'action du bouton2
    bouton2.addActionListener(new ActionListener(){
     
    	public void actionPerformed(ActionEvent event){               
    	continuerWebcam  = false;
      }
    });
    
    //Définition de l'action du bouton3
    bouton3.addActionListener(new ActionListener(){
     
    	public void actionPerformed(ActionEvent event){               
    	continuerAppli = false;
      }
    });
         
    //Ajout des bouttons dans le panel
    boutonPane.add(bouton);
    boutonPane.add(bouton2);
    boutonPane.add(bouton3);
    
    //On définit le layout
    content.setLayout(cl);
    
    //on rajoute tous les container dans la fenêtre
    this.getContentPane().add(boutonPane, BorderLayout.NORTH);
    this.getContentPane().add(content, BorderLayout.CENTER);
    this.getContentPane().add(animation, BorderLayout.CENTER);
   
    this.setVisible(valeur);
  } 
  
  public void repaintFenetre ()
  {

	  this.repaint();
	  this.bouton.repaint();
	  this.bouton2.repaint();
  }
  
  public void setImage (Image image)
  {
	  if (animation.getImage() == null)
		  animation.setImage(image);
	  else
	  {
		  animation.getImage().flush();
		  animation.setImage(image);
	  }
  }
  
  public boolean getContinuerWebcam()
  {
	  return this.continuerWebcam ;
  }
  
  public boolean getContinuerAppli()
  {
	  return this.continuerAppli ;
  }
  
  public void setContinuerWebcam(boolean valeur)
  {
	   this.continuerWebcam = true ;
  }
  
  
  
}