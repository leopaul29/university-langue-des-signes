
//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Fraction - Tests unitaires des racines carrées (cas nominaux)
//
// Auteur : N. Sebastien
//

package TUN_Math;
import Math.*;

public class T_Fraction_N7 {

   public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Fraction", "1.0.0");

         Tests.Design("Controle de la racine carre de fraction", 3); {

             Tests.Case("test de numerateur > 0");
            	{

            		Fraction f1= new Fraction( 36, 9 ), f2 = new Fraction (3,9);
          			Fraction resultatF1 = null, resultatF2 = null;

			        try
			        {
			           	resultatF1 = f1.sqrt();
			           	resultatF2 = f2.sqrt();
			        }
			        catch ( Exception e){}

			        Tests.Unit("2",  resultatF1.toString());
			        Tests.Unit("11547/20000",  resultatF2.toString());
            	}


            Tests.Case("test de numerateur = 0 ");
            	{

            		Fraction f1= new Fraction( 0, 20 ), f2 = new Fraction (0,9);
          			Fraction resultatF1 = null, resultatF2 = null;

			        try
			        {
			           	resultatF1 = f1.sqrt();
			           	resultatF2 = f2.sqrt();
			        }
			        catch ( Exception e){}

			        Tests.Unit("0",  resultatF1.toString());
			        Tests.Unit("0",  resultatF2.toString());
            	}


         }

      Tests.End();
   }
}
