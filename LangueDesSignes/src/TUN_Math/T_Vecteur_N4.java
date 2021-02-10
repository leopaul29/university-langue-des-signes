
//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2012_2013 - Package _Maths
//
// Classe Vecteur - Tests unitaires de la methode mul
//                  (cas nominaux)
//
// Auteur : S.Nicoletti
//


package TUN_Math;
import Math.*;

public class T_Vecteur_N4 {

   public static void main (String[] args) {

      Tests.Begin("_Maths.Vecteur", "1.1.0");

         Tests.Design("Controle de l'operation * (produit par un scalaire)", 3); {

            Tests.Case("Methode mul"); {
            Vecteur v0= new Vecteur();
            Vecteur v1= new Vecteur (1, 2);
            Vecteur v2= new Vecteur (-1, 2.25);
            Vecteur v3 = new Vecteur(-2, -2);

            Vecteur v;

               v= v0.mul(v1);
               Tests.Unit("(0, 0)",  v.toString());

               v= v1.mul(v2);
               Tests.Unit("(-1, 9/2)",  v.toString());

               v= v2.mul(v3);
               Tests.Unit("(2, -9/2)",  v.toString());
            }
         }

      Tests.End();
   }
}
