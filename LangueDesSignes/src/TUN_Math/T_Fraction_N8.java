
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Fraction - Tests unitaires de inverse et oppose (cas nominaux)
//
// Auteur : N. Sebastien
//

package TUN_Math;
import Math.*;

public class T_Fraction_N8 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Fraction", "1.0.0");

         Tests.Design("Controle des methode inverse et oppose", 3); {

             Tests.Case("test de inverse");
            	{
					Fraction f1 = new Fraction (5,2);
					Fraction f2 = new Fraction (-5,2);
					Fraction f3 = new Fraction (5,-2);
					Fraction f4 = new Fraction (-5,-2);
					Fraction f5 = new Fraction (-5);


					Tests.Unit("2/5",  f1.inverse().toString());
					Tests.Unit("-2/5",  f2.inverse().toString());
					Tests.Unit("-2/5",  f3.inverse().toString());
					Tests.Unit("2/5",  f4.inverse().toString());
					Tests.Unit("-1/5",  f5.inverse().toString());
            	}


            Tests.Case("test de oppose");
            	{

            		Fraction f1 = new Fraction (5,2);
					Fraction f2 = new Fraction (-5,2);
					Fraction f3 = new Fraction (5,-2);
					Fraction f4 = new Fraction (-5,-2);
					Fraction f5 = new Fraction (-5);
					Fraction f6 = new Fraction (0,5);


					Tests.Unit("-5/2",  f1.oppose().toString());
					Tests.Unit("5/2",  f2.oppose().toString());
					Tests.Unit("5/2",  f3.oppose().toString());
					Tests.Unit("-5/2", f4.oppose().toString());
					Tests.Unit("5", f5.oppose().toString());
					Tests.Unit("0", f6.oppose().toString());

            	}


         }

      Tests.End();
   }
}
