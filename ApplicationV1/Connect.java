package ApplicationV1;

//import java.awt.Image;
import java.sql.*;

//import javax.swing.ImageIcon;

//matsuya.dyndns.biz/projet_img/src/images_lettres/Droite/A/A_Droite_(1)

public class Connect {

	public static void main(String[] args) {
		// Fenetre uneFenetre= new Fenetre();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver O.K.");

			// brokerout.unice.fr
			// String url = "jdbc:mysql://matsuya.dyndns.biz:3306/Web";
			String url = "jdbc:mysql://lindmz.unice.fr";
			String user = "ls200202";
			String passwd = "ls200202";

			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");

			// Création d'un objet Statement
			Statement state = conn.createStatement();
			// L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state
					.executeQuery("SELECT E FROM Image WHERE A=1;");
			// On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			System.out.println("\n**********************************");
			// On affiche le nom des colonnes
			for (int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t"
						+ resultMeta.getColumnName(i).toUpperCase() + "\t *");
			System.out.println("\n**********************************");
			while (result.next()) {
				for (int i = 1; i <= resultMeta.getColumnCount(); i++)
					System.out.print("\t" + result.getObject(i).toString()
							+ "\t |");
				System.out.println("\n---------------------------------");
			}
			// result.beforeFirst();

			// System.out.println(resultMeta.toString());
			// System.out.println(result.getURL());

			// ImageIcon image = new ImageIcon(result.get);

			// uneFenetre.setImage(image.getImage());
			// uneFenetre.repaint();

			// result.close();
			// state.close();

		} catch (Exception e) {

			System.out.println("je suis dans l'exception.");
			e.printStackTrace();
		}

	}

}
