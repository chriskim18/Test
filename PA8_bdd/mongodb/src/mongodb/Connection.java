package mongodb;




import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.DBCursor;














import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;














import com.mongodb.QueryBuilder;

import jxl.BooleanCell;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;






















import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;






























import static java.util.concurrent.TimeUnit.SECONDS;






























import  java.io.IOException;




















import org.bson.BSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Connection {
	
	 public static void main( String[] args )
	    {

		MongoClient mongoClient;
		
		
		try {
			
			// Connexion à la bdd
			mongoClient = new MongoClient( "localhost" , 27017 );
			DB db = mongoClient.getDB( "mydb" );
			
			//Recuperation des collection
			DBCollection coll_parc = db.getCollection("Parcs");
			DBCollection coll_wifi = db.getCollection("Wifi_spots");
			DBCollection coll_musees = db.getCollection("Musees");
			DBCollection coll_itineraire = db.getCollection("Itineraire");
			//DBparc(coll_parc);
			//DBwifi(coll_wifi);
			//DBitineraire(coll_itineraire);
			
		/*	try {
				//DBmusees(coll_musees);
				recupAdresse();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	//DBCursor cursor = null;
	
	Afficher(coll_itineraire);
	//Vider(coll_itineraire);
	//Afficher(coll_musees);
	/*BasicDBObject query = new BasicDBObject("CP", "75017");
	cursor = coll_wifi.find(query);
	*/
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    }
	 private static void DBparc(DBCollection coll) {
			
			

			try {
		
	            String s = null;
	            String x=null;
	            String y=null;
	            Double x1=null;
	            Double y1=null;
	            String s1=null;
				String s2 =null;
				String s3 =null;
				String s4 =null;
				String s5 =null;
	        
	    		BufferedReader br = new BufferedReader (new FileReader("parcs.csv"));
            	String ligne = null;
            	ligne = br.readLine();
            	
            	//Lecture du fichier open data
            	while ((ligne = br.readLine()) != null)
            	{   
					

            		String row [] = ligne.split(";");
					
						s1=row[5];
						s2 =row[7];
						s3 =row[8];
						s4 =row[9];
						s5 =row[10];
						
						s =row[0];
						 int inSup = s.indexOf(",");
						 if(inSup>0)
						 {
					
						 x = s.substring(0,inSup);
						 y=s.substring(inSup+2 );
						 x1 = Double.parseDouble(x);
						 y1 = Double.parseDouble(y);
						 }
				//Remplissage de la collection avec les donnees lues		 
						BasicDBObject doc = new BasicDBObject("name", s1)
						.append("adresse",s2 +" "+s3)
						.append("CP",s4)
						.append("etat",s5)
						.append("x",x1)
						.append("y",y1);
						coll.insert(doc);
	
				}
							Afficher(coll);
						 	 br.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			} 
			
	 }
	 private static void DBwifi(DBCollection coll) {
			
	            String x=null;
	            String y=null;
	            Double x1=null;
	            Double y1=null;
	            
	         
	            JSONArray ar=new JSONArray();
	            
	            try{
					BufferedReader br = new BufferedReader (new FileReader("wifi.csv"));
                	String ligne = null;
                	ligne = br.readLine();
                
                	while ((ligne = br.readLine()) != null)
                	{   
						

                		String row [] = ligne.split(";");
								 
							    String s1=row[0];
								String s2 =row[1];
								String s3 =row[2];
							
								String s =row[5];
								 int inSup = s.indexOf(",");
								 if(inSup>0)
								 {
								 x = s.substring(0,inSup);
								 y=s.substring(inSup+2 );
								 x1 = Double.parseDouble(x);
								 y1 = Double.parseDouble(y);
								 }
								 
								BasicDBObject doc = new BasicDBObject("name", s1)
								.append("adresse", s2)
								.append("CP", s3)
								.append("x",x1)
								.append("y",y1);
								coll.insert(doc);
								
								
					
								 
								try {
									JSONObject jsonObj = new JSONObject();
									jsonObj.put("name", s1);
									jsonObj.put("adresse", s2);
									jsonObj.put("x", x1);
									jsonObj.put("y", y1);
									ar.put(jsonObj);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								 
				
							      
			                }
      		      String cheminDuFichier = "C:/Users/Alip/workspace/mongodb/test.txt";
				
					String jsonContent = ar.toString();
					File file = new File(cheminDuFichier);
					JSONArray ja = new JSONArray(jsonContent);
		            for(int i=0;i<ja.length();i++)
		            {
		                JSONObject curr = ja.getJSONObject(i);
		                String name = curr.getString("name");
		                double longitude1 =  curr.getDouble("x");
		                double latitude1 =curr.getDouble("y");
		                System.out.println(name+" "+longitude1+" "+latitude1);
		           
		            }
					try {
						
						FileWriter writer = new FileWriter(file);
						writer.write(jsonContent);
						writer.flush();
						writer.close();
					} catch (IOException e) {
						System.out.println("Erreur: impossible de créer le fichier '"
								+ cheminDuFichier + "'");
					}
				
									//Afficher(coll);
								 	 br.close();
}catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
} 

			
	 }
		private static void DBmusees(DBCollection coll) throws JSONException {

			Workbook workbook = null;
			try {
				/* Récupération du classeur Excel (en lecture) */
				workbook = Workbook.getWorkbook(new File("musees.xls"));

				int fin = 1;

				/*
				 * Un fichier excel est composé de plusieurs feuilles, on y accède
				 * de la manière suivante
				 */
				Sheet sheet = workbook.getSheet(0);
				// System.out.println(sheet.getColumn(0).length);
				// System.out.println("lat long coucou1:"+x1+y1);
				while (fin < sheet.getColumn(0).length) {
					/*
					 * On accède aux cellules avec la méthode getCell(indiceColonne,
					 * indiceLigne)
					 */
					Cell c1 = sheet.getCell(1, fin);
					String s0 = c1.getContents();
					if (s0.compareTo("PARIS") == 0) {
						Cell c = sheet.getCell(4, fin);

						String s1 = c.getContents();

						c = sheet.getCell(5, fin);
						String s2 = c.getContents();

						c = sheet.getCell(7, fin);
						String s3 = c.getContents();

						c = sheet.getCell(6, fin);
						String s4 = c.getContents();

						// c = sheet.getCell(10, fin);
						String s5 = null;// =c.getContents();

						double o=0;
						BasicDBObject doc = new BasicDBObject("name", s1)
								.append("adresse", s2).append("ville", s3)
								.append("CP", s4).append("etat", s5).append("x", o)
								.append("y", o);
						coll.insert(doc);

					}

					fin++;

				}

			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (workbook != null) {
					/* On ferme le worbook pour libérer la mémoire */
					workbook.close();
				}
			}

		}
		
		private static void DBitineraire(DBCollection coll) {
	
			List<Object> xDBList = new BasicDBList();
			List<Object> yDBList = new BasicDBList();
			List<Object> nameDBList = new BasicDBList();
			nameDBList.add("Le Pantheon");
			xDBList.add((double) 48.846378);
			yDBList.add((double)2.346287);
			
			nameDBList.add("Tour Monparnasse");
			xDBList.add((double) 48.842367);
			yDBList.add((double) 2.321912);
			
			nameDBList.add("Statue de la liberté");
			xDBList.add((double) 48.850186);
			yDBList.add((double) 2.279657);
			
		/*	nameDBList.add("Tour Eiffel");
			xDBList.add((double) 48.858536);
			yDBList.add((double)2.294470);
			
			nameDBList.add("Arc de Triomphe");
			xDBList.add((double) 48.873993);
			yDBList.add((double) 2.295017);
			
			nameDBList.add("Cathedrale Notre-Dame de Paris");
			xDBList.add((double) 48.853169);
			yDBList.add((double)2.349838);*/
		
			BasicDBObject doc = new BasicDBObject("name", "itineraire2")
			.append("nom", nameDBList)
			.append("x", xDBList)
			.append("y", yDBList);
	coll.insert(doc);
	Afficher(coll);
	
	 DBCursor cursor = coll.find();
		try {
			 while(cursor.hasNext()) {
			      System.out.println(cursor.next());
			      String name= (String) cursor.curr().get("name");
			      ArrayList<String> list=(ArrayList<String>)cursor.curr().get("nom");
			      ArrayList<Double> list1=( ArrayList<Double>)cursor.curr().get("x");
			      ArrayList<Double> list2=( ArrayList<Double>)cursor.curr().get("y");
			      
			    
			      for(int i=0;i<list.size();i++)
			      {
			    	  System.out.println(list.get(i)+"  "+list1.get(i)+"  "+list2.get(i));
			      }
			
			   }
			}  finally {
				 cursor.close();
		 }
	

		}
	 private static void Afficher(DBCollection coll) {
		 DBCursor cursor = coll.find();
			try {
				 while(cursor.hasNext()) {
				      System.out.println(cursor.next());
				
				   }
				} 
				 finally {
						 cursor.close();
				 }
	 }
	 private static void Vider(DBCollection coll) {
		 DBCursor cursor = coll.find();
			try {
				 while(cursor.hasNext()) {
				   
				     coll.remove(cursor.next());
				   }
				} 
				 finally {
						 cursor.close();
				 }
	 }
	 private static void updateMusee(DBCollection coll, String name, double x1,
				double y1, String country) {
			/*
			 * BasicDBObject doc = new BasicDBObject("name", name)
			 * .append("adresse", adresse).append("ville", ville) .append("CP",
			 * cp).append("etat", etat).append("x", x1) .append("y", y1);
			 * coll.insert(doc);
			 */
			if (x1 == 0 || y1 == 0) {
				System.out.println("Geoding error for : " + name);
			}
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.append("$set", new BasicDBObject().append("x", x1));
			BasicDBObject searchQuery = new BasicDBObject().append("name", name);
			coll.update(searchQuery, newDocument);

			newDocument = new BasicDBObject();
			newDocument.append("$set", new BasicDBObject().append("y", y1));
			searchQuery = new BasicDBObject().append("name", name);
			coll.update(searchQuery, newDocument);

			newDocument = new BasicDBObject();
			newDocument.append("$set", new BasicDBObject().append("etat", country));
			searchQuery = new BasicDBObject().append("name", name);
			coll.update(searchQuery, newDocument);

		}
	 public static void recupAdresse() throws IOException, JSONException {

			double x1 = 0;
			double y1 = 0;

			MongoClient mongoClient;
			mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("mydb");

			// Recuperation des collection

			DBCollection coll_musees = db.getCollection("Musees");

			DBObject query = QueryBuilder.start("ville").is("PARIS").get();
			Pattern regex = Pattern.compile("PARIS*");
			query = QueryBuilder.start("ville").regex(regex).get();

			// System.out.println("\nRequête de recherche:");
			// System.out.println(query);

			// System.out.println("\nRésultat:");
			DBCursor cursor = coll_musees.find(query);
			if (cursor.count() == 0) {
				// System.out.println("<vide>");
			} else {
				while (cursor.hasNext()) {

					// System.out.println(cursor.next().toString());

					// On parse le fichier json généré par notre BDD pour ne
					// récupérer que l'adresse
					JSONObject obj = new JSONObject(cursor.next().toString());
					// System.out.println(obj);
					String adresse = obj.getString("adresse");
					String CP = obj.getString("CP");
					String ville = obj.getString("ville");
					String name = obj.getString("name");

					//System.out.println("Voici ce que je mets dans le geocoding :"
					//		+ name + adresse + ", " + ville + ", " + CP);
					Geocoding g = new Geocoding();
					// System.out.println(g.getJSONByGoogle(adresse+", "
					// +ville+", "+CP));

					// On parse le fichier de geocoding pour récupérer que la
					// lat/long
					JSONObject obj2 = new JSONObject(g.getJSONByGoogle(
							adresse + ", " + ville + ", " + CP).toString());

					if (obj2.getJSONArray("results").length() > 0) {
						JSONObject obj3 = obj2.getJSONArray("results")
								.getJSONObject(0);
						Double latitude = obj3.getJSONObject("geometry")
								.getJSONObject("location").getDouble("lat");
						Double longitude = obj3.getJSONObject("geometry")
								.getJSONObject("location").getDouble("lng");
						String country = "";
						for (int i = 0; i < obj3.getJSONArray("address_components")
								.length(); i++) {
							if (obj3.getJSONArray("address_components")
									.getJSONObject(i).getJSONArray("types")
									.toString().contains("country")) {
								country = obj3.getJSONArray("address_components")
										.getJSONObject(i).getString("long_name");
								break;
							}
						}
						// System.out.println(latitude);
						// System.out.println(longitude);
						x1 = latitude;
						y1 = longitude;
						// System.out.println("j envoie la methode bdd");
						updateMusee(coll_musees, name, x1, y1, country);
					}

					// System.out.println("c est envoye a la bdd");
				}
			}
			cursor.close();

		}

}

	 
