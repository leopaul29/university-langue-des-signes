
//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Fraction - Tests unitaires des constructeurs (cas nominaux)
//
// Auteur : S. Nicoletti
//

package TUN_Math;
import Math.*;

public class T_Fraction_N1 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Fraction", "1.0.0");

         Tests.Design("Controle de la construction", 3); {

            Tests.Case("Construction avec normalisation du signe"); {
            Fraction f1= new Fraction(2,3);
            Fraction f2= new Fraction(2,-3);
            Fraction f3= new Fraction(2,-1);
            Fraction f4= new Fraction(-2,-3);

               Tests.Unit("2/3",  f1.toString());
               Tests.Unit("-2/3", f2.toString());
               Tests.Unit("-2",   f3.toString());
               Tests.Unit("2/3",  f4.toString());
            }

            Tests.Case("Construction avec reduction"); {
            Fraction f1= new Fraction(3,36);
            Fraction f2= new Fraction(-15,-45);
            Fraction f3= new Fraction(36,-14);
            Fraction f4= new Fraction(-4,-10);

               Tests.Unit("1/12",  f1.toString());
               Tests.Unit("1/3",   f2.toString());
               Tests.Unit("-18/7", f3.toString());
               Tests.Unit("2/5",   f4.toString());
            }

            Tests.Case("Construction avec resultat entier"); {
            Fraction f1= new Fraction(12,2);
            Fraction f2= new Fraction(1,1);
            Fraction f3= new Fraction(60,3);
            Fraction f4= new Fraction(300,-300);

               Tests.Unit("6",   f1.toString());
               Tests.Unit("1",   f2.toString());
               Tests.Unit("20",  f3.toString());
               Tests.Unit("-1",  f4.toString());
            }

            Tests.Case("Construction avec resultat nul"); {
            Fraction f1= new Fraction(0,-2);
            Fraction f2= new Fraction(0,1);
            Fraction f3= new Fraction(0,-1);
            Fraction f4= new Fraction(-0,-3);

               Tests.Unit("0",  f1.toString());
               Tests.Unit("0",  f2.toString());
               Tests.Unit("0",  f3.toString());
               Tests.Unit("0",  f4.toString());
            }

            Tests.Case("Construction avec un double"); {
            Fraction f1= new Fraction(-2.0d);
            Fraction f2= new Fraction(1.0d);
            Fraction f3= new Fraction (-1.0d);
            Fraction f4= new Fraction (-3.0d);

               Tests.Unit("-2",  f1.toString());
               Tests.Unit("1",  f2.toString());
               Tests.Unit("-1",  f3.toString());
               Tests.Unit("-3",  f4.toString());
            }

         }

      Tests.End();
   }
}
