//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2012_2013 - Package _Maths
//
// Classe Segment - Tests unitaires des services (Lot 1)
//
// Auteur : Nicoletti S
//

package TUN_Math;
import Math.*;

public class T_Segment_N4 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Segment", "0.1.0");

         Tests.Design("Controle des services (Lot 1)", 3); {

            Tests.Case("Methode Aligne"); {
            	Point A = new Point (1.0 , 2.0);
            	Point B = new Point (1.0 , 5.0);
            	Point C = new Point (1.0 , -2.0);
            	Point D = new Point (0.0 , -2.0);
            	Segment s1 = new Segment (A,C);
            	Segment s2 = new Segment (B,C);
            	Segment s3 = new Segment (A,D);
            	
            	Tests.Unit(true,  s1.aligne(B));
            	Tests.Unit(true,  s2.aligne(A));
            	Tests.Unit(false,  s3.aligne(C));
            }
            Tests.Case("Methode Appartient"); {
            	
            	Point A = new Point (1.0 , 2.0);
            	Point B = new Point (1.0 , 5.0);
            	Point C = new Point (1.0 , -2.0);
            	Segment s1 = new Segment (C,B);
            	Segment s2 = new Segment (A,B);
            	
            	Tests.Unit(true,  s1.appartient(A));
            	Tests.Unit(false,  s2.appartient(C));
            }
            
            
             Tests.Case("Methode projete Orthogonal"); {
            	
            	Point A = new Point (1.0 , 0.0);
            	Point B = new Point (3.0 , 0.0);	Point C = new Point (2.0 , 2.0);
            	
            	Point D = new Point (1.0, 4.0);
            	Point E = new Point (3.0, 4.0);
            	
            	
            	Segment s1 = new Segment (A,B);
            	Segment s2 = new Segment (D,E);
            	Segment s3 = new Segment (E, B);
            	
            	Tests.Unit( "_ (2.0, 0.0)",  s1.projOrthogonal(C).toString() );
            	Tests.Unit( "_ (2.0, 4.0)",  s2.projOrthogonal(C).toString() );
            	Tests.Unit( "_ (3.00005, 2.0)",  s3.projOrthogonal(C).toString() );

            }

         }

      Tests.End();
   }
}
