
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Fraction - Tests unitaires du compteur (cas nominaux)
//
// Auteur : N. Sebastien
//

package TUN_Math;
import Math.*;

public class T_Fraction_N9 {

   @SuppressWarnings("unused")
public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Fraction", "1.0.0");

         Tests.Design("Controle des methode méthodes compteurs", 3); {

             Tests.Case("test de getCompteur");
            	{
					Fraction f1 = new Fraction (5,2);
					Tests.Unit(1,  Fraction.getCompteur());

					Fraction f2 = new Fraction (2.5);
					Tests.Unit(2,  Fraction.getCompteur());

					Fraction f3 = new Fraction (2);
					Tests.Unit(3,  Fraction.getCompteur());

					Fraction f4 = new Fraction ();
					Tests.Unit(4,  Fraction.getCompteur());

            	}




         }

      Tests.End();
   }
}
