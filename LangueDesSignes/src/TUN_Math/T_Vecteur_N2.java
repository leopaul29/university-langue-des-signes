
//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Vecteur - Tests unitaires des accesseurs
//
// Auteur : N.Sebastien
//


package TUN_Math;
import Math.*;

public class T_Vecteur_N2 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Vecteur", "1.1.0");

         Tests.Design("Controle des accesseurs de consultation", 3); {

            Tests.Case("Consultation Abscisse"); {
            Vecteur v1= new Vecteur(1,2);
            Vecteur v2= new Vecteur(-1,2);

            Tests.Unit("1",  v1.getAbscisse().toString());
            Tests.Unit("-1",  v2.getAbscisse().toString());

            }

            Tests.Case("Consultation Ordonnee"); {
            Vecteur v1= new Vecteur(1,2);
            Vecteur v2= new Vecteur(1,-2);

            Tests.Unit("2",  v1.getOrdonnee().toString());
            Tests.Unit("-2",  v2.getOrdonnee().toString());

            }

            Tests.Case("Consultation Norme"); {
          	Vecteur v1= new Vecteur(0,1);
          	Vecteur v2= new Vecteur(-1,0);
            Vecteur v3= new Vecteur(4,3);
            Vecteur v4= new Vecteur(3,-4);

            Tests.Unit("1",  v1.getNorme().toString());
            Tests.Unit("1",  v2.getNorme().toString());
            Tests.Unit("5",  v3.getNorme().toString());
            Tests.Unit("5",  v4.getNorme().toString());
            }
         }
      Tests.End();
   }
}
