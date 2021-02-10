package TUN_Math;


// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2011_2012 - Package _Maths
//
// Classe Fraction - Tests d'anomalie (cas nominaux)
//
// Auteur : N. Sebastien
//

import Math.*;


public class T_Fraction_A1 {

   @SuppressWarnings("unused")
public static void main (String[] args) throws Exception {

      Tests.Begin("_Maths.Fraction", "1.0.0");

         Tests.Design("Teste d'anomalie", 3); {

             Tests.Case("Division par 0");
            	{

            		try
            		{
            			Fraction f1 = new Fraction (5,0);
            		}
            		catch(Exception e)
            		{
            			System.out.println(e);
            		}
            	}


            Tests.Case("inverse d'une fraction avec un numerateur null");
            	{

            		Fraction f1 = new Fraction (0,5);
					Fraction f2 = new Fraction (0,-5);

					f1.inverse();
					f2.inverse();

            	}

			Tests.Case("inverse d'une fraction avec un numerateur null");
            	{

            		Fraction f1 = new Fraction (0,5);
					Fraction f2 = new Fraction (0,-5);

					f1.inverse();
					f2.inverse();

            	}

			Tests.Case("Racine carre d'une fraction négatif");
			{
				Fraction f1 = new Fraction (-4,25);
				Fraction f2 = new Fraction (25,-4);


				try
				{
					f1.sqrt();
					f2.sqrt();

				}
				catch(Exception e)
				{}

			}


         }

      Tests.End();
   }
}
