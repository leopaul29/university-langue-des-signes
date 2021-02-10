
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Point - Tests unitaires des methodes points confondus
//
// Auteur : S. Nicoletti
//

package TUN_Math;
import Math.*;

public class T_Point_N3 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Point", "0.1.0");

         Tests.Design("Controle de la methode confondus", 3); {

            Tests.Case("Deux pts confondus"); {
            Point p1= new Point('A',1,2);
            Point p2= new Point('B',1,2);
            Point p3= new Point(1,2.00000000000012);
            Point p4= new Point(1,2);


            Tests.Unit(true,  p1.confondus(p2));
            Tests.Unit(true,  p2.confondus(p1));
            Tests.Unit(true,  p3.confondus(p4));
            }

            Tests.Case("Deux pts non confondus"); {
            Point p1= new Point('A',1,2);
            Point p2= new Point('B',0,2);
            Point p3= new Point('C',1,0);
            Point p4= new Point(1,2.0012);
            Point p5= new Point(1,2);

            Tests.Unit(false,  p1.confondus(p2));
            Tests.Unit(false,  p2.confondus(p1));
            Tests.Unit(false,  p1.confondus(p3));
            Tests.Unit(false,  p2.confondus(p3));
            Tests.Unit(false,  p4.confondus(p5));
            }
         }
      Tests.End();
   }
}
