package Math;


// IUT de Nice / Departement informatique / Module APO Java
// Annee 2012_2013 - DUT/S2T
//
// Classe Fraction - Modelisation de l'ensemble ZxZ*
//
// Edition "Draft" : le point d'entree simule les tests unitaires
//
// + Version 0.2.0 : TP1
// + Version 0.3.0 : Ajout d'un nouveau constructeur. 
// + Version 0.4.0 : Ajout de la méthode sqrt
// + Version 1.0.0 : Ajout d'un compteur 

// Auteur : S. Nicoletti




public class Fraction {
private int m_numerateur;
private int m_denominateur;
private static int m_compteur;

   // ---                                               Constructeur par defaut

   public Fraction() {

      // Fixer les deux attributs de la fraction
      //
      this.m_numerateur = 0;
      this.m_denominateur = 1;
      m_compteur ++;

   }

   // ---                                           Premier constructeur normal

   public Fraction(int num, int den) throws Exception {

      // Controler la validite du denominateur
      //
      if (den==0) throw new Exception ();

      // Fixer les deux attributs de la fraction
      //
		this.m_numerateur =  num;
		this.m_denominateur = den;

      // Reduire la fraction resultante
      //
      	this.reduire();
      	m_compteur ++;
	 
  }

   // ---                                            Second constructeur normal

   public Fraction(int num) {

      // Fixer les deux attributs de la fraction
      //
      
      this.m_numerateur = num;
      this.m_denominateur = 1;
      m_compteur ++;

   }
   //												Constructeur a partir d'un double
   
      public Fraction (double op2) throws Exception
   {
 	int signe=1;
 	String s1;
 	if (op2<0)
 	{
 		signe=-1;
 		op2=Math.abs(op2);
 	}
 	
  	String s = String.valueOf(op2);
  	String[] tab = s.split("\\.");

 	int n1 = tab[1].length();
 
 	s1 = tab[1];
  	
  	if(n1>5) {
  		n1=5;s1=tab[1].substring(0,5);
  	}
  	
  	int x = Integer.parseInt(tab[0]);
  	int y = Integer.parseInt(s1);
  	
  	
 	//System.out.println(x + " " + y);
 	
 	double lx = x*Math.pow(10.0,n1)+y;
 	double ly = Math.pow(10,n1);
 	
 	//System.out.println(lx + " " + ly);
 	
  	if ((Math.abs(lx)>Integer.MAX_VALUE)||(Math.abs(ly)>Integer.MAX_VALUE)) throw new Exception();
  	
  	m_numerateur = (int)lx;
 	m_denominateur = (int)ly;
 	
 	//System.out.println(m_numerateur + " " + m_denominateur);
  	reduire();
  	if (signe==-1) m_numerateur=-m_numerateur;
  	
  	m_compteur ++;
}
   

	// 															ACCESSEUR 
	
	public int getNumerateur ()
	{
		return this.m_numerateur;
	}
	
	public int getDenominateur ()
	{
		return this.m_denominateur;
	}
	
	public double getValeur ()
	{
		return (double) this.getNumerateur() / this.getDenominateur();
	}
	
	public static int getCompteur ()
	{
		return m_compteur;
	}
	
	



   // ---                                                       Methode reduire

   private void reduire() 
   	{
      int signe, pgcd;

      // Traiter le cas particulier d'une fraction nulle
      //	
	  if (m_numerateur == 0)
		 return ;
		
      // Determiner le signe du resultat
      //
      if (m_numerateur>0)
         if (m_denominateur>0) signe= 1;
         else signe= -1;
      else if (m_denominateur>0) signe= -1;
         else signe= 1;

      // Calculer le PGCD des deux membres
      //
      pgcd = Arithm.pgcd( Math.abs(this.m_numerateur), Math.abs(this.m_denominateur));
			 
      // Diviser chaque membre par le PGCD
      //
      
     	this.m_numerateur = signe * (Math.abs(m_numerateur)/pgcd);
     	this.m_denominateur = (Math.abs(m_denominateur)/pgcd); 

   }
   
   // ---													Methode Oppose !!
   
   public Fraction oppose()
   {
   	  Fraction resultat=null;
   	  try
   	  {
   		resultat=new Fraction(-m_numerateur,m_denominateur);
   	  }
      catch(Exception e){}


      // Restituer le resultat
      //
	  return resultat;
   }
   
   

   // ---                                                      Methode toString

   public String toString() {

      // Traiter le cas particulier d'une fraction nulle
      //
      if (m_numerateur==0) return "0";

      // Traiter le cas particulier d'un entier
      //

      if (m_denominateur==1) return "" + m_numerateur;

      // Traiter le cas general
      //
      return m_numerateur + "/" + m_denominateur;
   }
   																
   	//																Methode add
   	
   	public Fraction add (final Fraction op2)
   	{
   		Fraction resultat = null;
   		int num,den;
   		num=this.m_numerateur*op2.m_denominateur+this.m_denominateur*op2.m_numerateur;
   		den=this.m_denominateur*op2.m_denominateur;
   		try
   		{
   			resultat=new Fraction(num,den);
   		}
   		catch(Exception e){}
   		return resultat;
    }	

   public Fraction add (int op2) {

      // Fixer le numerateur du resultat
      //
      Fraction resultat = null;
   	  try
   	  {
   		resultat=new Fraction(this.m_numerateur+(this.m_denominateur*op2),this.m_denominateur);
   	  }
      catch(Exception e){}


      // Restituer le resultat
      //
	  return resultat;
   }

   // ---                                                           Methode sub

   public Fraction sub(final Fraction op2)
   	{
	  int num,den;
	  Fraction resultat = null;
      // Calculer le numérateur
      //
      num=this.m_numerateur*op2.m_denominateur-this.m_denominateur*op2.m_numerateur;
      


      // Calculer le dénominateur
      //
   	  den=this.m_denominateur*op2.m_denominateur;


      // Construire le resultat
      //
      try
   	  {
   		resultat=new Fraction(num,den);
   	  }
      catch(Exception e){}     


      // Restituer le resultat
      //
	  return resultat;
   }

   public Fraction sub (int op2) 
   	{
	  int num,den;
	  Fraction resultat = null;
      // Fixer le numerateur du resultat
      //
      num=this.m_numerateur-op2*this.m_denominateur;
      den=this.m_denominateur;
      // Construire le resultat
      //
      try
   	  {
   		resultat=new Fraction(num,den);
   	  }
      catch(Exception e){}     
      // Restituer le resultat
      //
	  return resultat;
   }

   public Fraction sub () {
	  int num,den;
	  Fraction resultat = null;
      // Fixer le numerateur du resultat
      //
	  num=this.m_numerateur;
	  den=this.m_denominateur;

      // Construire le resultat
      //
      try
   	  {
   		resultat=new Fraction(num,den);
   	  }
      catch(Exception e){} 

      // Restituer le resultat
      //
      return resultat;

   }
   
   //																METHODE INVERSE
   public Fraction inverse()
   {
   	Fraction Inverse=null;
   	int num,den;
   	num=this.m_denominateur;
   	den=this.m_numerateur;
      // Construire le resultat
      //
    try
   	{
   		Inverse=new Fraction(num,den);
   	}
    catch(Exception e){} 

      // Restituer le resultat
      //
    return Inverse;
   }
   
   
      	//																MULTIPLICATONN
   	
   	public Fraction mul (final Fraction op2)
   	{
   		Fraction resultat = null;
   		int num,den;
   		num=this.m_numerateur*op2.m_numerateur;
   		den=this.m_denominateur*op2.m_denominateur;
   		try
   		{
   			resultat=new Fraction(num,den);
   		}
   		catch(Exception e){}
   		return resultat;
    }	

   public Fraction mul (int op2) {

      // Fixer le numerateur du resultat
      //
      Fraction resultat = null;
      int num,den;
      num=this.m_numerateur*op2;
      den=this.m_denominateur;
   	  try
   	  {
   		resultat=new Fraction(num,den);
   	  }
      catch(Exception e){}


      // Restituer le resultat
      //
	  return resultat;
   }
   
	//															DIVISION
   	
   	public Fraction div (final Fraction op2)
   	{
   		Fraction resultat = null;
   		int num,den; 		
   		num=this.m_numerateur*op2.m_denominateur;
   		den=this.m_denominateur*op2.m_numerateur;
   		try
   		{
   			resultat=new Fraction(num,den);
   		}
   		catch(Exception e){}
   		return resultat;
    }	
   public Fraction div (int op2) {

      // Fixer le numerateur du resultat
      //
      Fraction resultat = null;
      int num,den;
      num=this.m_numerateur;
      den=this.m_denominateur*op2;
   	  try
   	  {
   		resultat=new Fraction(num,den);
   	  }
      catch(Exception e){}


      // Restituer le resultat
      //
	  return resultat;
   }
	
	
	public Fraction sqrt ()throws Exception 
	{
		
		if ( this.m_numerateur < 0 )
			throw new Exception ();
		
		Fraction maFraction = null;
		double a, b ,c;
		
		a = Math.sqrt(this.m_numerateur);
		b= Math.sqrt(this.m_denominateur);
		c= a/b;
		
		try
		{
			maFraction = new Fraction ( c );
		}
		catch (Exception e)
		{}
		
		return maFraction;
			
	}
   
   

}