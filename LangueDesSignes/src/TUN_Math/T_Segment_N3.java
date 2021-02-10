//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2012_2013 - Package _Maths
//
// Classe Segment - Tests unitaires des services (Lot 1)
//
// Auteur : A. Thuaire
//

package TUN_Math;
import Math.*;

public class T_Segment_N3 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Segment", "0.0.0");

         Tests.Design("Controle des services (Lot 1)", 3); {

            Tests.Case("Methode extremite"); {
            Point A= new Point ('A', 1, 2 );
            Point B= new Point ('B', 1.5, -2);
            Point C= new Point (-1.5, -2);
            Segment AB= new Segment(A, B);

               Tests.Unit(true,  AB.extremite(A));
               Tests.Unit(true,  AB.extremite(B));
               Tests.Unit(false, AB.extremite(C));
            }

            Tests.Case("Methode horizontal"); {
            Point A= new Point (1, 2);
            Point B= new Point (-1, 2);
            Point C= new Point (-1.5, -2);
            Segment AB= new Segment(A, B), BC= new Segment(B, C);;

               Tests.Unit(true,  AB.horizontal());
               Tests.Unit(false, BC.horizontal());
            }

            Tests.Case("Methode vertical"); {
            Point A= new Point (1, 2);
            Point B= new Point (1, -2);
            Point C= new Point (-1.5, -2);
            Segment AB= new Segment(A, B), BC= new Segment(B, C);;

               Tests.Unit(true,  AB.vertical());
               Tests.Unit(false, BC.vertical());
            }

            Tests.Case("Methode projX"); {
            Point A= new Point (1, 2);
            Point B= new Point (3, -4);
            Point C= new Point (-1.5, -2);
            Segment AB= new Segment(A, B), BC= new Segment(B, C);;
            Segment s1= AB.projX(), s2= BC.projX();

               Tests.Unit("[(1.0, 0.0), (3.0, 0.0)]",  s1.toString());
               Tests.Unit("[(3.0, 0.0), (-1.5, 0.0)]", s2.toString());
            }

            Tests.Case("Methode projY"); {
            Point A= new Point (1, 2);
            Point B= new Point (3, -4);
            Point C= new Point (-1.5, -2);
            Segment AB= new Segment(A, B), BC= new Segment(B, C);;
            Segment s1= AB.projY(), s2= BC.projY();

               Tests.Unit("[(0.0, 2.0), (0.0, -4.0)]",  s1.toString());
               Tests.Unit("[(0.0, -4.0), (0.0, -2.0)]", s2.toString());
            }
         }

      Tests.End();
   }
}
