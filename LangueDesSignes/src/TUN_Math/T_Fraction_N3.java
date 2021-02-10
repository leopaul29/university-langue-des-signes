
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Fraction - Tests unitaires de l'addition (cas nominaux)
//
// Auteur : S.Nicoletti
//
package TUN_Math;
import Math.*;


public class T_Fraction_N3 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Fraction", "1.0.0");

         Tests.Design("Controle de l'addition de fractions", 3); {

            Tests.Case("Cas du produit des denominateurs"); {
            Fraction f1= new Fraction(2,3),   f2=  new Fraction(5,4);
            Fraction f3= new Fraction(-2,-3), f4=  new Fraction(-5,-4);
            Fraction f5= new Fraction(2,-3),  f6=  new Fraction(5,4);
            Fraction f7= new Fraction(-2,-3), f8=  new Fraction(5,-4);
            Fraction f9= new Fraction(-2,3),  f10= new Fraction(-5,4);

               Tests.Unit("23/12",  f1.add(f2).toString());
               Tests.Unit("23/12",  f3.add(f4).toString());
               Tests.Unit("7/12",   f5.add(f6).toString());
               Tests.Unit("-7/12",  f7.add(f8).toString());
               Tests.Unit("-23/12", f9.add(f10).toString());
            }

            Tests.Case("Cas d'un PPCM different du produit"); {
            Fraction f1= new Fraction(1,10), f2=  new Fraction(1,20);
            Fraction f3= new Fraction(-2,5), f4=  new Fraction(-2,15);
            Fraction f5= new Fraction(2,-3), f6=  new Fraction(3,4);
            Fraction f7= new Fraction(7,-3), f8=  new Fraction(1);

               Tests.Unit("3/20",  f1.add(f2).toString());
               Tests.Unit("-8/15", f3.add(f4).toString());
               Tests.Unit("1/12",  f5.add(f6).toString());
               Tests.Unit("-4/3",  f7.add(f8).toString());
            }

            Tests.Case("Cas d'un resultat nul"); {
            Fraction f1= new Fraction(2,12),   f2=  new Fraction(1,-6);
            Fraction f3= new Fraction(-2, -3), f4=  new Fraction(4,-6);
            Fraction f5= new Fraction(12,-4),  f6=  new Fraction(3);
            Fraction f7= new Fraction(-0,10),  f8=  new Fraction(0);

               Tests.Unit("0", f1.add(f2).toString());
               Tests.Unit("0", f3.add(f4).toString());
               Tests.Unit("0", f5.add(f6).toString());
               Tests.Unit("0", f7.add(f8).toString());
            }

            Tests.Case("Cas d'un second operande entier"); {
            Fraction f1= new Fraction(-2,12),  f2=  new Fraction(1,-5);
            Fraction f3= new Fraction(1, 123), f4=  new Fraction(4,-16);

               Tests.Unit("-7/6", f1.add(-1).toString());
               Tests.Unit("4/5",  f2.add(1).toString());
               Tests.Unit("1/123",f3.add(0).toString());
               Tests.Unit("39/4", f4.add(10).toString());
            }
         }

      Tests.End();
   }
}
