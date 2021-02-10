package Math;
//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2012_2013 - DUT/S2T
//
// Classe Arithm - Services pgcd et ppcm
//
// Auteur : Nicoletti Sebastien
//




public abstract class Arithm {

// ---                                                          Methode pgcd

   public static int pgcd (int n, int m) {

      // Controler la validite des parametres
      //


      if ( n < 0 || m < 0)
      {
      	return -1;
      }


      // Traiter le cas particulier de l'identite
      //
      if ( n == 1 || m == 1)
      {
      	return 1;
      }



      // Traiter le cas particulier de nombres egaux
      //
      if (n == m)
      {
      	return n;
      }


      // Traiter le cas general par appel recursif
      //
      if (n > m)
      {
      	return pgcd(m,n-m);
      }
      else
      {
      	return pgcd(n,m-n);
      }

   }

// ---                                                          Methode ppcm

   public static int ppcm (int n, int m) {

      // Controler la validite des parametres
      //
      if ( n < 0 || m < 0)
      {
      	return -1;
      }


      // Traiter le cas general
      //
      return (n*m)/pgcd(n,m);

   }

   public static void main (String[] args) {

      // Calculer plusieurs pgcd
      //

      System.out.println("PGCD (36, 1)= " + pgcd(36, 1));
      System.out.println("PGCD (36, 36)= " + pgcd(36, 36));
      System.out.println("PGCD (36, 42)= " + pgcd(36, 42));
      System.out.println("PGCD (37, 43)= " + pgcd(37, 43));
      System.out.println();

      // Calculer plusieurs ppcm
      //
      System.out.println("PPCM (6, 7)  = "  + ppcm(6, 7));
      System.out.println("PPCM (6, 12) = "  + ppcm(6, 12));
      System.out.println("PPCM (12, 18)= "  + ppcm(12, 18));
      System.out.println();
   }
}
