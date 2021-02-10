
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Point - Tests unitaires des methodes de projection
//
// Auteur : S. Nicoletti
//


package TUN_Math;
import Math.*;

public class T_Point_N4 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Point", "0.1.0");

         Tests.Design("Controle des methodes de projection", 3); {

             Tests.Case("Sur l'axe des abscisses"); {
             Point p1= new Point('A',0,2);
             Point p2= new Point('B',1,0);
             Point p3= new Point('C',2,1);

             Tests.Unit("_ (0.0, 0.0)",  p1.projX().toString());
             Tests.Unit("_ (1.0, 0.0)",  p2.projX().toString());
             Tests.Unit("_ (2.0, 0.0)",  p3.projX().toString());
             }

             Tests.Case("Sur l'axe des ordonnées"); {
             Point p1= new Point('A',2,0);
             Point p2= new Point('B',0,1);
             Point p3= new Point('C',1,2);

             Tests.Unit("_ (0.0, 0.0)",  p1.projY().toString());
             Tests.Unit("_ (0.0, 1.0)",  p2.projY().toString());
             Tests.Unit("_ (0.0, 2.0)",  p3.projY().toString());
             }
         }
      Tests.End();
   }
}
