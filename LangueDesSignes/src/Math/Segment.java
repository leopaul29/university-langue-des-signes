package Math;
//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2012_2013 - DUT/S2T
//
// Classe Segment - Couple de points distincts du plan euclidien
//
// Edition A        : TD5
//
//    + Version 0.0.0	: version initiale
//	  + Version 0.1.0   : Ajout des methodes Projete Orthogonal
//
// Auteur : A. Thuaire
//



public class Segment {
private final Point m_A;
private final Point m_B;
private final static double EPSILON = 1./10000;

   // ---                                               Constructeur par defaut

   public Segment() {

      m_A= new Point(0, 0);
      m_B= new Point(1, 0);
   }

   // ---                                                   Constructeur normal

   public Segment(final Point p0, final Point p1) {

      m_A= (Point)p0.clone();
      m_B= (Point)p1.clone();
   }

   // ---                                            Accesseurs de consultation

   public String getNom() {return "" + m_A.getNom() + m_B.getNom();}

   public Point  getExtremite(int rang) throws Exception {

      if (rang == 1) return m_A;
      if (rang == 2) return m_B;

      throw new Exception();
   }

   public Point getMilieu() {
   double x= (m_A.x() + m_B.x()) / 2;
   double y= (m_A.y() + m_B.y()) / 2;

      return new Point(x, y);
   }

   public double getLongueur() {return m_A.distance(m_B); }

   // ---                                                      Methode toString

   public String toString() {
   double x1= m_A.x(), x2= m_B.x();
   double y1= m_A.y(), y2= m_B.y();

   	  return "[(" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ")]";
   }

   // ---                                                         Methode clone

   public Object clone() {return new Segment(m_A, m_B);}

   // ---                                                        Methode equals

   public boolean equals(Object s) {
   boolean b1= m_A.equals(((Segment)s).m_A);
   boolean b2= m_B.equals(((Segment)s).m_B);
   boolean b3= m_A.equals(((Segment)s).m_B);
   boolean b4= m_B.equals(((Segment)s).m_A);

      return (b1 && b2) || (b3 && b4);
   }

   // ---                                                     Methode extremite

   public boolean extremite(final Point p) {

      return p.equals(m_A) || p.equals(m_B);
   }

   // ---                                                    Methode horizontal

   public boolean horizontal() {return m_A.y() == m_B.y();}

   // ---                                                      Methode vertical

   public boolean vertical()   {return m_A.x() == m_B.x();}

   // ---                                                         Methode projX

   public Segment projX() {

      // Traiter le cas particulier d'un segment support vertical
      //
      if (vertical()) return null;

      // Restituer le resultat
      //
      return new Segment(m_A.projX(), m_B.projX());
   }

   // ---                                                         Methode projY

   public Segment projY() {

      // Traiter le cas particulier d'un segment support horizontal
      //
      if (horizontal()) return null;

      // Restituer le resultat
      //
      return new Segment(m_A.projY(), m_B.projY());
   }
   
      
   //															Methode aligne
   
   public boolean aligne (Point p) {
   		
   		Vecteur v1 =  new Vecteur ( this.m_B.x() - this.m_A.x() , this.m_B.y() - this.m_A.y() );
   		Vecteur v2 =  new Vecteur ( p.x() - this.m_A.x() , p.y() - this.m_A.y() );
   		
   		return v1.colineaire(v2);
   }
   
     //															Methode appartient
   
   public boolean appartient (Point p) {
   		return ( this.aligne(p) &&  this.m_B.distance(p) + this.m_A.distance(p) == this.m_A.distance(this.m_B) ) ;
   }
   
   // 															Methode projOrthogonal
   
   public Point projOrthogonal (Point pt)
   {
   		double x, y , b , c;
   		
   		if (Math.abs(this.m_B.x() - this.m_A.x()) < EPSILON)
   			b = -( this.m_B.y() - this.m_A.y() ) / EPSILON;
   		else
   			b =  -( this.m_B.y() - this.m_A.y() ) / (this.m_B.x() - this.m_A.x() );
   		
   		c =  (-1.) * this.m_A.y() - b * this. m_A.x();
   		
   		if ( Math.abs(( this.m_B.x() - this.m_A.x()) - b *  (this.m_B.y() - this.m_A.y())) < EPSILON )
   		{
   			x =  ( pt.x() * ( this.m_B.x() - this.m_A.x() ) + pt.y() * ( this.m_B.y() - this.m_A.y()) + c * ( this.m_B.y() - this.m_A.y()) )
   			 / EPSILON ;
   		}
   		else
   		{
   			x =  ( pt.x() * ( this.m_B.x() - this.m_A.x() ) + pt.y() * ( this.m_B.y() - this.m_A.y()) + c * ( this.m_B.y() - this.m_A.y()) )
   			 / ( ( this.m_B.x() - this.m_A.x()) - b *  (this.m_B.y() - this.m_A.y()) );
   		}
   			
   		

   			 
   		y = (-b * x) - c;
   		
   		return new Point ( x , y);
   		
   }
   	
   	/*
  	
  	b= -( yB – yA ) / ( xB – xA )
	c= – yA – bxA
   	
   	xH = [xP ( xB – xA ) + yP ( yB – yA ) + c( yB – yA )] / [( xB – xA ) - b ( yB – yA )]
	yH = -bxH - c*/
}