


//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2012_2013 - Package _Maths
//
// Classe Vecteur - Tests unitaires des constructeurs
//                  (cas nominaux)
//
// Auteur : S.Nicoletti
//


package TUN_Math;
import Math.*;

public class T_Vecteur_N1 {

   public static void main (String[] args) {

      Tests.Begin("_Maths.Vecteur", "1.1.0");

         Tests.Design("Controle des constructeurs", 3); {

            Tests.Case("Constructeur par defaut"); {
            Vecteur v0= new Vecteur();

               Tests.Unit("(0, 0)",  v0.toString());
            }

            Tests.Case("Constructeur normal"); {
            Vecteur v1= new Vecteur (1, 2);
            Vecteur v2= new Vecteur (1.5, -2);
            Vecteur v3= new Vecteur (-1, 2.25);
            Vecteur v4= new Vecteur (-1, -2);
            Vecteur v5= new Vecteur (-7.25, 8.45);

               Tests.Unit("(1, 2)",    v1.toString());
               Tests.Unit("(3/2, -2)",   v2.toString());
               Tests.Unit("(-1, 9/4)",  v3.toString());
               Tests.Unit("(-1, -2)",  v4.toString());
               Tests.Unit("(-29/4, 169/20)", v5.toString());
            }
        }

            Tests.Design("Controle des constructeurs", 3); {

                Tests.Case("clone" ); {

                    Fraction f1 = null;
                    Fraction f2 = null;
                    Fraction f3 = null;
                    Fraction f4 = null;

                    try
                    {
                        f1 = new Fraction(2.0);
                        f2 = new Fraction(1.0);
                        f3 = new Fraction(-5.0);
                        f4 = new Fraction(2.5);

                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }

                    Vecteur v1= new Vecteur(f1,f2);
                    Vecteur v2= null;

                    Vecteur v3= new Vecteur(f1,f3);
                    Vecteur v4= null;

                    Vecteur v5= new Vecteur(f3,f4);
                    Vecteur v6= null;

                    v2= (Vecteur) v1.clone();
                    v4= (Vecteur) v3.clone();
                    v6= (Vecteur) v5.clone();

                         Tests.Unit("(2, 1)",  v2.toString());
                         Tests.Unit("(2, -5)",  v4.toString());
                         Tests.Unit("(-5, 5/2)",  v6.toString());

                }


                Tests.Case("equals"); {

                    Fraction f1 = null;
                    Fraction f2 = null;
                    Fraction f3 = null;
                    Fraction f4 = null;

                    try
                    {
                        f1 = new Fraction(2.0);
                        f2 = new Fraction(1.0);
                        f3 = new Fraction(-5.0);
                        f4 = new Fraction(2.5);

                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }

                    Vecteur v1= new Vecteur(f1,f2);
                    Vecteur v2= null;

                    Vecteur v3= new Vecteur(f1,f3);
                    Vecteur v4= null;

                    Vecteur v5= new Vecteur(f3,f4);
                    Vecteur v6= null;

                    v2= (Vecteur) v1.clone();
                    v4= (Vecteur) v3.clone();
                    v6= (Vecteur) v5.clone();



                        Tests.Unit(true,v1.equals(v2) );
                        Tests.Unit(true,v2.equals(v1) );
                        Tests.Unit(true,v3.equals(v4) );

                        Tests.Unit(false,v1.equals(v3) );
                        Tests.Unit(false,v3.equals(v1) );
                        Tests.Unit(false,v2.equals(v3) );

                        Tests.Unit(true,v6.equals(v5) );

                }

         }

      Tests.End();
   }
 }

