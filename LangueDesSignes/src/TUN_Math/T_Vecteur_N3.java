
//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2012_2013 - Package _Maths
//
// Classe Vecteur - Tests unitaires des methodes add et sub
//                  (cas nominaux)
//
// Auteur : S. Nicoletti
//


package TUN_Math;
import Math.*;

public class T_Vecteur_N3 {

   public static void main (String[] args) {

      Tests.Begin("_Maths.Vecteur", "1.1.0");

         Tests.Design("Controle des operations + et -", 3); {

            Tests.Case("Methode add"); {
            Vecteur v0= new Vecteur();
            Vecteur v1= new Vecteur (1, 2);
            Vecteur v2= new Vecteur (-1, 2.25);
            Vecteur v3= new Vecteur (-1, -2);

            Vecteur v = null;

               v= v0.add(v1);
               Tests.Unit("(1, 2)",  v.toString());

               v= v1.add(v3);
               Tests.Unit("(0, 0)",  v.toString());

               v= v1.add(v2);
               Tests.Unit("(0, 17/4)",  v.toString());
            }

            Tests.Case("Methode sub"); {
            Vecteur v0= new Vecteur();
            Vecteur v1= new Vecteur (1, 2);
            Vecteur v2= new Vecteur (-1, 2.25);
            Vecteur v3= new Vecteur (-1, -2);

            Vecteur v;

               v= v0.sub(v1);
               Tests.Unit("(-1, -2)",  v.toString());

               v= v1.sub(v3);
               Tests.Unit("(2, 4)",  v.toString());

               v= v1.sub(v2);
               Tests.Unit("(2, -1/4)",  v.toString());
            }
         }

      Tests.End();
   }
}
