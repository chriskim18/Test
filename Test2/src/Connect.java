import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.DBCursor;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Connect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// To connect to mongodb server
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("localhost", 27017);

			// mongoClient.dropDatabase("AAC");
			// get database
			// if databa se doesn't exists, mongodb will create it for you
			DB db = mongoClient.getDB("AAC");
			// get collection
			// if collection doesn't exists, mongodb will create it for you
			DBCollection utilisateur = db.getCollection("Utilisateur");
			DBCollection etat_sante = db.getCollection("Etat_Sante");
			DBCollection activite = db.getCollection("Activite");
/*
			// on précise les différents chemin des fichiers à utiliser
			 String cheminDuFichierUtilisateur =
			 "C:/Users/Christian Kim/workspace/Test2/Utilisateur.JSON";
			 String cheminDuFichierEtatSante =
			 "C:/Users/Christian Kim/workspace/Test2/Etat_Sante.JSON";
			 String cheminDuFichierActivite =
			 "C:/Users/Christian Kim/workspace/Test2/Activite.JSON";

			// On va les importer dans la base de données avec l'aide de la
			// foncrtion ImportJSONFileToDB
			 importJSONFileToDB(cheminDuFichierUtilisateur, db,
			 "Utilisateur");
			 importJSONFileToDB(cheminDuFichierEtatSante, db, "Etat_Sante");
			 importJSONFileToDB(cheminDuFichierActivite, db, "Activite");
*/
			System.out
					.println("=============================  Page d'accueil  ===========================");
			System.out.println("1. s'inscrire");
			System.out.println("2. Se connecter");

			Scanner oki = new Scanner(System.in);
			int choix = oki.nextInt();

			if (choix == 1) {
				inscription(utilisateur);
			}
			if (choix == 2) { // Veuillez vous identifier
				System.out.println("Identifiez vous");
				// Saisie du login
				System.out.println("Login");
				Scanner log = new Scanner(System.in);
				String login = log.nextLine();
				// Saisie du mdp
				System.out.println("Password");
				Scanner pass = new Scanner(System.in);
				String password = pass.nextLine();

				// Verification dans la base de données
				BasicDBObject login_verif = new BasicDBObject("Login", login);
				BasicDBObject password_verif = new BasicDBObject("Mot_de_passe", password);

				DBCursor curseur = utilisateur.find(login_verif);
				DBCursor curseurVerif = utilisateur.find(login_verif);
				DBCursor curseur2 = utilisateur.find(password_verif);

				
				String userID = curseur.next().get("_id").toString();
				System.out.println(userID);
				String lelogin = curseurVerif.next().get("Login").toString();
				System.out.println(lelogin);
				String id_2 = curseur2.next().get("_id").toString();
				System.out.println( id_2);
				if ( userID.equals(id_2) && lelogin.equals(login) )
				{
					curseur.close();
					curseurVerif.close();
					curseur2.close();
					
					// Menu temporaire
					// Que voulez-vous faire ?
					System.out.println("=================== Arismore Activity Challenge ====================");
					System.out.println("1. Ajouter des infos/Modification ");
					System.out.println("2. Supprimer une infos");
					System.out.println("3. Récupérer une infos");

					// Saisie utilisateur
					Scanner sc = new Scanner(System.in);
					int choix2 = sc.nextInt();
					//  Ajout Modification des infos
					if (choix2 == 1) {

						System.out.println("1. Ajout information");
						System.out.println("2. Modification");

						Scanner scann = new Scanner(System.in);
						int choix2_1 = scann.nextInt();

						if (choix2_1 == 1) {
						

								ajouterInformation(etat_sante,activite, userID);
							
						}
					}
						// Suppression des infos
						if (choix2 == 2) {
							System.out.println("lololol");
						}

						// Recuperation des infos
						if (choix2 == 3) {

							// Que voulez vous faire
							System.out.println("1. Mon activité");
							System.out.println("2. Ma santé");

							// Saisie utilisateur
							Scanner sc1 = new Scanner(System.in);
							int choix3 = sc.nextInt();

							// Voir son activité
							if (choix3 == 1) {
								// Que voulez vous faire
								System.out.println("1. Information par jour");
								System.out.println("2. Information par semaine");
								System.out.println("3. Information par mois");
								Scanner sc2 = new Scanner(System.in);
								int choix3_4 = sc.nextInt();

								if (choix3_4 == 1) {
									//A compléter avec les aggrégations
								}

								if (choix3_4 == 2) {
									//A compléter avec les aggrégations
								}

								if (choix3_4 == 3) {
									//A compléter avec les aggrégations
								} else {
									System.out.println("Saisie Invalide");
								}

							}

							// Voir sa santé
							if (choix3 == 2) {
								// Que voulez vous faire
								System.out.println("1. Voir son poids");
								System.out.println("2. Voir Calorie consommé");
								System.out.println("3. Voir la tension");
								System.out.println("3. Voir la fréquenece cardiaque");
								//  saisie utilisateur
								Scanner sc2 = new Scanner(System.in);
								int choix3_5 = sc.nextInt();

								// Voir son poids
								if (choix3_5 == 1) {


								
									

								}
								// VOir les calories consommé
								if (choix3_5 == 2) {


									
								}
								// Voir sa tension
								if (choix3_5 == 3) {
									

								
								}
								// Voir sa fréquence cardiaque
								if (choix3_5 == 4) {


									
								} else {
									System.out.println("Saisie pour la sante invalide");
								}

							} else {
								System.out.println("Saisie pour sante invalide");
							}
						} else {
							System.out.println("Saisie Invalide");
						}
				}

					else {
						System.out.println("Mauvaise saisie");
					}
				}
				else{
					System.out.println("erreur");
				}

			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void importJSONFileToDB(String pathToFile, DB db,
			String collectionName) {
		// open file
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(pathToFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Fichier introuvable");
			return;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		// read it line by line
		String strLine;
		DBCollection newColl = db.getCollection(collectionName);
		try {
			do {
				String insertion = br.readLine();

				// On parse le contenu du fichier JSON en un DBOject
				DBObject dbObject = (DBObject) JSON.parse(insertion);
				// Insertion du fichier dans la collection
				try {
					newColl.insert(dbObject);
				} catch (MongoException e) {
					// duplicate key
					e.printStackTrace();
				}

			} while ((strLine = br.readLine()) != null);

			br.close();
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}

	}

	public static void inscription(DBCollection collectionName) {
		System.out.println("1. Entrez votre Prenom");
		Scanner temp1 = new Scanner(System.in);
		String prenom = temp1.nextLine();
		System.out.println("2. Entrez votre Nom");
		Scanner temp2 = new Scanner(System.in);
		String Nom = temp2.nextLine();
		System.out.println("3. Entrez votre Login");
		Scanner temp3 = new Scanner(System.in);
		String Login = temp3.nextLine();
		System.out.println("4. Entrez votre Mot de passe");
		Scanner temp4 = new Scanner(System.in);
		String Mdp = temp4.nextLine();
		System.out.println("5. Entrez votre organisation");
		Scanner temp5 = new Scanner(System.in);
		String organisation = temp5.nextLine();
		System.out.println("6. Entrez votre Adresse");
		Scanner temp6 = new Scanner(System.in);
		String adresse = temp6.nextLine();
		System.out.println("7. Entrez votre Telephone");
		Scanner temp7 = new Scanner(System.in);
		String telephone = temp7.nextLine();
		System.out.println("8. Entrez votre Pays");
		Scanner temp8 = new Scanner(System.in);
		String pays = temp8.nextLine();
		System.out.println("9. Entrez votre Poids");
		Scanner temp9 = new Scanner(System.in);
		String Poids = temp9.nextLine();
		System.out.println("10. Entrez votre Taille");
		Scanner temp10 = new Scanner(System.in);
		String taille = temp10.nextLine();
		System.out.println("11. Entrez votre Taille Idéale");
		Scanner temp11 = new Scanner(System.in);
		String tailleIdeale = temp11.nextLine();
/*
		JSONObject countryObj = new JSONObject();
		countryObj.put("First_name", prenom);
		countryObj.put("Last_name", Nom);
		countryObj.put("Login", Login);
		countryObj.put("Mot_de_passe", Mdp);
		countryObj.put("organisation", organisation);
		countryObj.put("Adresse", adresse);
		countryObj.put("Phone", telephone);
		countryObj.put("Country", pays);
		countryObj.put("Weight", Poids);
		countryObj.put("Height", taille);
		countryObj.put("Ideal_Weigth", tailleIdeale);
*/
		//try {

			// Writing to a file
			/*File file = new File(
					"C:/Users/Christian Kim/workspace/Test2/Utilisateur.JSON");
			FileWriter fileWriter = new FileWriter(file, true);
			System.out.println("Writing JSON object to file");
			System.out.println("-----------------------");
			System.out.print(countryObj);
*/
			BasicDBObject document = new BasicDBObject();
			document.put("First_name", prenom);
			document.put("Last_name", Nom);
			document.put("Login", Login);
			document.put("Mot_de_passe", Mdp);
			document.put("organisation", organisation);
			document.put("Adresse", adresse);
			document.put("Phone", telephone);
			document.put("Country", pays);
			document.put("Weight", Poids);
			document.put("Height", taille);
			document.put("Ideal_Weigth", tailleIdeale);
			collectionName.insert(document);

		/*} catch (IOException e) {
			e.printStackTrace();
		}*/

	}

	public static void ajouterInformation(DBCollection collectionName, DBCollection collectionName2, String Id)
	{
		System.out.println("1. Entrez votre Poids");
		Scanner temp1 = new Scanner(System.in);
		String Weight = temp1.nextLine();
		System.out.println("2. Entrez votre Frequence cardiaque (moyenne/heure)");
		Scanner temp2 = new Scanner(System.in);
		String Hfrequency = temp2.nextLine();
		System.out.println("3. Entrez votre pression sanguine");
		Scanner temp3 = new Scanner(System.in);
		String Bpressure = temp3.nextLine();
		System.out.println("4. Entrez les calories consomme(mangé)");
		Scanner temp5 = new Scanner(System.in);
		String Kal = temp5.nextLine();
		System.out.println("5. Entrez la date et l'heure de saisie");
		Scanner temp6 = new Scanner(System.in);
		String Date = temp6.nextLine();
		System.out.println("5. Entrez le nombre de pas");
		Scanner temp7 = new Scanner(System.in);
		String NbrPas = temp7.nextLine();
		System.out.println("5. Entrez la distance parcourue");
		Scanner temp8 = new Scanner(System.in);
		String Distance = temp8.nextLine();
		System.out.println("5. Entrez type d'activité");
		Scanner temp9 = new Scanner(System.in);
		String Type = temp9.nextLine();
	
		BasicDBObject document = new BasicDBObject();
		document.put("Weight", Weight);
		document.put("Heart_frequency", Hfrequency);
		document.put("Blood_pressure", Bpressure);
		document.put("Kal", Kal);
		document.put("Kal", Date);
		document.put("Utilisateur_id", Id);
		collectionName.insert(document);
		
		BasicDBObject document1 = new BasicDBObject();
		document1.put("nombre_pas", NbrPas);
		document1.put("distance_parcourue", Distance);
		document1.put("type", Type);
		collectionName2.insert(document1);
	}
	
	public static void RecupererInformation(String userID, String santeType, DBObject collectionName)
	{

		BasicDBObject query = new BasicDBObject("Utilisateur_id", userID);
		System.out.println(userID);
		DBCursor findWeight = ((DBCollection) collectionName).find(query).sort(new BasicDBObject("_id",1));
		if(findWeight.count() != 0 )
		{
				System.out.println(findWeight.next().get(santeType));
		}
		
		else{
			System.out.println("Pas d'information disponible pour " + santeType);
		}
		
	}
}
