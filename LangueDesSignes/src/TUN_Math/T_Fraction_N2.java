
//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Fraction - Tests unitaires des accesseurs (cas nominaux)
//
// Auteur : S. Nicoletti
//

package TUN_Math;
import Math.*;


public class T_Fraction_N2 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Fraction", "1.0.0");

         Tests.Design("Controle des accesseurs", 3); {

            Tests.Case("getNumerateur");
            {
	            Fraction f1= new Fraction(2,3);
	            Fraction f2= new Fraction(2,-3);
	            Fraction f3= new Fraction(2,-1);
	            Fraction f4= new Fraction(-2,-3);

               Tests.Unit(2,  f1.getNumerateur());
               Tests.Unit(-2, f2.getNumerateur());
               Tests.Unit(-2, f3.getNumerateur());
               Tests.Unit(2,  f4.getNumerateur());
            }




            Tests.Case("getDenominateur");
            {
	            Fraction f1= new Fraction(2,3);
	            Fraction f2= new Fraction(2,-3);
	            Fraction f3= new Fraction(2,-1);
	            Fraction f4= new Fraction(-2,-3);

               Tests.Unit(3,  f1.getDenominateur());
               Tests.Unit(3,  f2.getDenominateur());
               Tests.Unit(1,  f3.getDenominateur());
               Tests.Unit(3,  f4.getDenominateur());
            }

            Tests.Case("getValeur");
            {
	            Fraction f1= new Fraction(2,3);
	            Fraction f2= new Fraction(2,-3);
	            Fraction f3= new Fraction(2,-1);
	            Fraction f4= new Fraction(-2,-3);

               Tests.Unit(0.66,  f1.getValeur());
               Tests.Unit(-0.66, f2.getValeur());
               Tests.Unit(-2.00, f3.getValeur());
               Tests.Unit(0.66,  f4.getValeur());
           	 }

         }
      Tests.End();

	}
}
