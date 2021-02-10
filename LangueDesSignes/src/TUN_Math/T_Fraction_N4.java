
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Fraction - Tests unitaires de la soustraction (cas nominaux)
//
// Auteur : S.Nicoletti
//

package TUN_Math;
import Math.*;

public class T_Fraction_N4 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Fraction", "1.0.0");

         Tests.Design("Controle de la soustraction de fractions", 3); {

            Tests.Case("Cas du produit des denominateurs"); {
            Fraction f1= new Fraction(2,3),   f2=  new Fraction(5,4);
            Fraction f3= new Fraction(-2,-3), f4=  new Fraction(-5,4);
            Fraction f5= new Fraction(2,-3),  f6=  new Fraction(5,4);
            Fraction f7= new Fraction(-2,-3), f8=  new Fraction(5,-2);
            Fraction f9= new Fraction(-2,5),  f10= new Fraction(-5,4);

               Tests.Unit("-7/12",  f1.sub(f2).toString());
               Tests.Unit("23/12",  f3.sub(f4).toString());
               Tests.Unit("-23/12", f5.sub(f6).toString());
               Tests.Unit("19/6",   f7.sub(f8).toString());
               Tests.Unit("17/20",  f9.sub(f10).toString());
            }

            Tests.Case("Cas d'un PPCM different du produit"); {
            Fraction f1= new Fraction(1,10), f2=  new Fraction(1,20);
            Fraction f3= new Fraction(-2,5), f4=  new Fraction(-2,15);
            Fraction f5= new Fraction(2,-3), f6=  new Fraction(7,6);
            Fraction f7= new Fraction(7,-3), f8=  new Fraction(1);

               Tests.Unit("1/20",  f1.sub(f2).toString());
               Tests.Unit("-4/15", f3.sub(f4).toString());
               Tests.Unit("-11/6", f5.sub(f6).toString());
               Tests.Unit("-10/3", f7.sub(f8).toString());
            }

            Tests.Case("Cas d'un resultat nul"); {
            Fraction f1= new Fraction(2,12),  f2=  new Fraction(1,6);
            Fraction f3= new Fraction(2, 3),  f4=  new Fraction(4,6);
            Fraction f5= new Fraction(12,4),  f6=  new Fraction(3);
            Fraction f7= new Fraction(-0,10), f8=  new Fraction(0);

               Tests.Unit("0", f1.sub(f2).toString());
               Tests.Unit("0", f3.sub(f4).toString());
               Tests.Unit("0", f5.sub(f6).toString());
               Tests.Unit("0", f7.sub(f8).toString());
            }

            Tests.Case("Cas d'un second operande entier"); {
            Fraction f1= new Fraction(-2,12),  f2=  new Fraction(1,-5);
            Fraction f3= new Fraction(1, 123), f4=  new Fraction(4,-16);

               Tests.Unit("-7/6",  f1.sub(1).toString());
               Tests.Unit("9/5",   f2.sub(-2).toString());
               Tests.Unit("1/123", f3.sub(0).toString());
               Tests.Unit("-41/4", f4.sub(10).toString());
            }
         }

      Tests.End();
   }
}
