//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2012_2013 - Package _Maths
//
// Classe Segment - Tests unitaires des constructeurs
//                  (cas nominaux)
//
// Auteur : A. Thuaire
//

package TUN_Math;
import Math.*;

public class T_Segment_N1 {

   public static void main (String[] args) {

      Tests.Begin("_Maths.Segment", "0.0.0");

         Tests.Design("Controle des constructeurs", 3); {

            Tests.Case("Constructeur par defaut"); {
            Segment parDefaut= new Segment();

               Tests.Unit("[(0.0, 0.0), (1.0, 0.0)]", parDefaut.toString());
            }

            Tests.Case("Constructeur normal"); {
            Point A= new Point(0.25, -0.75);
            Point B= new Point(1, 2);
            Segment AB= new Segment(A, B);

               Tests.Unit("[(0.25, -0.75), (1.0, 2.0)]", AB.toString());
            }
         }

      Tests.End();
   }
}
