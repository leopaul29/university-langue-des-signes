
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Point - Tests unitaires des accesseurs
//
// Auteur : S. Nicoletti
//

package TUN_Math;
import Math.*;


public class T_Point_N2 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Point", "0.1.0");

         Tests.Design("Controle des accesseurs de consultation", 3); {

            Tests.Case("Consultation Abscisse"); {
            Point p1= new Point('A',1,2);
            Tests.Unit(1.0,  p1.x());
            }

            Tests.Case("Consultation Ordonnee"); {
            Point p1= new Point('A',1,2);
            Tests.Unit(2.0,  p1.y());
            }

            Tests.Case("Consultation Nom"); {
            Point p1= new Point('A',1,2);
            Tests.Unit('A',  p1.getNom());
            }
         }
      Tests.End();
   }
}
