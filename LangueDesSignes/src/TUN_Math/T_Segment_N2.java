//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2012_2013 - Package _Maths
//
// Classe Segment - Tests unitaires des accesseurs
//
// Auteur : A. Thuaire
//

package TUN_Math;
import Math.*;

public class T_Segment_N2 {

   public static void main (String[] args) throws Throwable {

      Tests.Begin("_Maths.Segment", "0.0.0");

         Tests.Design("Controle des accesseurs", 3); {

            Tests.Case("Methode getNom"); {
            Point A= new Point ('A', 1, 2 );
            Point B= new Point ('B', 1.5, -2);
            Segment AB= new Segment(A, B);

               Tests.Unit("AB", AB.getNom());
            }

            Tests.Case("Methode getExtremite"); {
            Point A= new Point ('A', 1, 2 );
            Point B= new Point ('B', 1.5, -2);
            Segment AB= new Segment(A, B);

               Tests.Unit(A.toString(), AB.getExtremite(1).toString());
               Tests.Unit(B.toString(), AB.getExtremite(2).toString());
            }

            Tests.Case("Methode getMilieu"); {
            Point A= new Point(1, 0),  B= new Point(3, 2);
            Point C= new Point(-1, 0), D= new Point(0.5, -0.5);
            Segment AB= new Segment(A, B), BC= new Segment(B, C);
            Segment AC= new Segment(A, C), BD= new Segment(B, D);

               Tests.Unit("_ (2.0, 1.0)",   AB.getMilieu().toString());
               Tests.Unit("_ (1.0, 1.0)",   BC.getMilieu().toString());
               Tests.Unit("_ (0.0, 0.0)",   AC.getMilieu().toString());
               Tests.Unit("_ (1.75, 0.75)", BD.getMilieu().toString());
            }

            Tests.Case("Methode getLongueur"); {
            Point A= new Point(1, 0),  B= new Point(3, 2);
            Point C= new Point(-1, 0);
            Segment AB= new Segment(A, B), BC= new Segment(B, C);
            Segment AC= new Segment(A, C);

               Tests.Unit(2.8284271247461903, AB.getLongueur());
               Tests.Unit(4.47213595499958,   BC.getLongueur());
               Tests.Unit(2.0,                AC.getLongueur());
            }
         }

         Tests.Design("Controle des methodes heritees de Object", 3); {

            Tests.Case("Methode clone"); {
            Point A= new Point(1, 0),  B= new Point(3, 2), C= new Point(-1, 0);
            Segment AB= new Segment(A, B), BC= new Segment(B, C);
            Segment _AB= (Segment)AB.clone(), _BC= (Segment)BC.clone();

               Tests.Unit(AB.toString(), _AB.toString());
               Tests.Unit(BC.toString(), _BC.toString());
            }

            Tests.Case("Methode equals"); {
            Point A= new Point(1, 0),  B= new Point(3, 2);
            Segment AB= new Segment(A, B), BA= new Segment(B, A);

               Tests.Unit(true, AB.equals(BA));
            }
         }

      Tests.End();
   }
}
