
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Fraction - Tests unitaires de la division (cas nominaux)
//
// Auteur : S.Nicoletti
//

package TUN_Math;
import Math.*;

public class T_Fraction_N6 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Fraction", "1.0.0");

         Tests.Design("Controle de la division de fractions", 3); {

            Tests.Case("Cas de l'absence de reduction du resultat"); {
            Fraction f1= new Fraction(2,3),   f2=  new Fraction(7,5);
            Fraction f3= new Fraction(-2,-3), f4=  new Fraction(-9,5);
            Fraction f5= new Fraction(2,-3),  f6=  new Fraction(3,5);
            Fraction f7= new Fraction(1,3),   f8=  new Fraction(2,-5);
            Fraction f9= new Fraction(-1,5),  f10= new Fraction(4,-7);

               Tests.Unit("10/21",  f1.div(f2).toString());
               Tests.Unit("-10/27", f3.div(f4).toString());
               Tests.Unit("-10/9",  f5.div(f6).toString());
               Tests.Unit("-5/6",   f7.div(f8).toString());
               Tests.Unit("7/20",   f9.div(f10).toString());
            }

            Tests.Case("Cas d'une reduction apres division"); {
            Fraction f1= new Fraction(7,10),  f2=  new Fraction(7,2);
            Fraction f3= new Fraction(-3, 5), f4=  new Fraction(-2,15);
            Fraction f5= new Fraction(2,-3),  f6=  new Fraction(7,6);
            Fraction f7= new Fraction(7,-3),  f8=  new Fraction(14, 6);

               Tests.Unit("1/5",  f1.div(f2).toString());
               Tests.Unit("9/2",  f3.div(f4).toString());
               Tests.Unit("-4/7", f5.div(f6).toString());
               Tests.Unit("-1",   f7.div(f8).toString());
            }

            Tests.Case("Cas d'un resultat entier"); {
            Fraction f1= new Fraction(2,12),  f2=  new Fraction(1, 24);
            Fraction f3= new Fraction(2, 3),  f4=  new Fraction(4,6);
            Fraction f5= new Fraction(9,4),  f6=  new Fraction(-3, 16);
            Fraction f7= new Fraction(-0,10), f8=  new Fraction(-1);

               Tests.Unit("4",   f1.div(f2).toString());
               Tests.Unit("1",   f3.div(f4).toString());
               Tests.Unit("-12", f5.div(f6).toString());
               Tests.Unit("0",   f7.div(f8).toString());
            }

            Tests.Case("Cas d'un second operande entier"); {
            Fraction f1= new Fraction(-2,12),  f2=  new Fraction(1,-5);
            Fraction f3= new Fraction(1, 123), f4=  new Fraction(1,-16);

               Tests.Unit("-1/6",  f1.div(1).toString());
               Tests.Unit("1/10",  f2.div(-2).toString());
               Tests.Unit("1/246", f3.div(2).toString());
               Tests.Unit("-1/80", f4.div(5).toString());
            }
         }

      Tests.End();
   }
}
