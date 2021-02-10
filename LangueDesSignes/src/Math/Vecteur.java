package Math;

//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2012_2013 - DUT/S2T
//
// Classe Vecteur - Modelisation de l'espace vectoriel RxR
//
//
//    + Version 0.0.0	: Cours_3
//	  + Version 0.1.0   : Attribut de type Fraction
// 	  + Version 1.1.0   : Ajout des méthodes equals et clone
//
//
// Auteur : S.Nicoletti
//





public class Vecteur {
private Fraction m_x = null;
private Fraction m_y = null;
private final static double EPSILON= 1./10000;


// ---                                             Constructeur par defaut
	public Vecteur() {

      // Fixer les deux attributs de la fraction (element neutre)

      	this.m_x = new  Fraction();
      	this.m_y = new  Fraction();
   }

// ---                                                 Constructeur normal
	public Vecteur(double x, double y) {

      // Fixer les deux attributs du vecteur
      //

      try
      {
      	this.m_x = new  Fraction(x);
      	this.m_y = new  Fraction(y);
      }
      catch(Exception e)
      {}
   }
//													Constructeur Fraction
   	public Vecteur(Fraction x, Fraction y) {

      // Fixer les deux attributs du vecteur
      //
      this.m_x = x;
      this.m_y = y;
   }

// ---                                                    Methode toString
	public String toString() {return "(" + this.m_x.toString() + ", " + this.m_y.toString() + ")";}

	//														--Clone

	public Object clone()
	{
		Vecteur clone = null;
		try
   	  {
   		clone = new Vecteur(this.getAbscisse(),this.getOrdonnee());
   	  }
      catch(Exception e){}

      return clone;
	}

	//														--equals

	public boolean equals(Object v1)
	{

		Vecteur v2 = (Vecteur) v1;

	    return ( this.getAbscisse().equals(v2.getAbscisse())) && ( this.getOrdonnee().equals(v2.getOrdonnee()) );
	}


//  														ACCESSEURS :
//															getAbscisse

	public Fraction getAbscisse ()
	{
		return this.m_x;
	}
//															getOrdonnee

	public Fraction getOrdonnee ()
	{
		return this.m_y;
	}


//															getNorme

	public Fraction getNorme ()
	{
		Fraction Fract= null;
		Fraction Fract2 = null;
		Fraction Fract3 = null;
		Fraction Fract4 = null;

		Fract= this.getAbscisse().mul(this.getAbscisse());
		Fract2 = this.getOrdonnee().mul(this.getOrdonnee());
		Fract3 = Fract.add(Fract2);

 		try
 		{
			Fract4 = new Fraction ( Math.sqrt(Fract3.getValeur()));
 		}
 		catch(Exception e)
 		{}

		return Fract4;
	}


// ---                                                         Methode add
	public Vecteur add (Vecteur op2) {

      return new Vecteur (this.m_x.add(op2.getAbscisse() ), this.m_y.add(op2.getOrdonnee() ));
   }

// ---                                                         Methode sub
	public Vecteur sub (Vecteur op2) {

      return new Vecteur (this.m_x.sub(op2.getAbscisse() ), this.m_y.sub(op2.getOrdonnee() ));
   }

   public Vecteur sub () {

      return new Vecteur (this.m_x.sub(), this.m_y.sub() );
   }

// ---                                                         Methode mul
	public Vecteur mul (Vecteur op2) {

      return new Vecteur ( this.m_x.mul(op2.getAbscisse () ), this.m_y.mul(op2.getOrdonnee() ) );
   }

//																Methode Orthogonol

	public boolean orthogonal (Vecteur op2) {

		Fraction Fract1= null;
		Fraction Fract2= null;
		Fraction Fract3= null;


		Fract1 = this.m_x.mul(op2.getAbscisse());
		Fract2 = this.m_y.mul(op2.getOrdonnee());

		Fract3 = Fract1.add(Fract2);

		return (Fract3.getValeur() == 0);
	}

//																Methode Colineaire

	public boolean colineaire (Vecteur op2) {

		Fraction Fract1= null;
		Fraction Fract2= null;

		Fract1 = this.m_x.mul(op2.getOrdonnee()); //Fract1 == xy'
		Fract2 = this.m_y.mul(op2.getAbscisse()); //Frac2 == x'y

		return (Math.abs(Fract1.getValeur() - Fract2.getValeur()) <= EPSILON);   //|xy' - x'y| <= EPSILON
	}

 //																Methode produitScalaire
	public  Fraction produitScalaire (Vecteur op2)
	{
		Fraction Fract1= null;
		Fraction Fract2= null;

		Fract1 = this.m_x.mul(op2.getAbscisse());
		Fract2 = this.m_y.mul(op2.getOrdonnee());



		return Fract1.add(Fract2);
	}


	public  boolean memeSens (Vecteur op2)
	{
		return (this.colineaire(op2) && (this.getAbscisse().div(op2.getAbscisse()).getValeur()) > 0 );
	}

}
