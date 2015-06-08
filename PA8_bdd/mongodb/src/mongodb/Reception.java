package mongodb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.CharBuffer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cedarsoftware.util.io.JsonWriter;
import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.DBCursor;

public class Reception implements Runnable {

	private BufferedReader  is;
	private static String message = null;
	DBCursor cursor = null;
	DBCursor cursor1 = null;
	int stop=0;
	boolean exist=false;
	private static OutputStream os=null;
	PrintWriter out=null;
	
	public Reception(BufferedReader  in,OutputStream os1,PrintWriter out1){
		
		this.is = in;
		this.os=os1;
		this.out=out1;
	}
	
	public void run() {
		
		while(true){
	        try {
	        	
	        	message= is.readLine(); 
	        	
		       System.out.println( message);
		
	        	
		       
		       if(message.equals("parc"))
		       {
		    	   MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
					DB db = mongoClient.getDB( "mydb" );
					DBCollection coll_parc = db.getCollection("Parcs");
					cursor = coll_parc.find();
					envoyerPOI(cursor,is,out);
		       }
		       else if(message.equals("wifi"))
		       {
		    	   MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
					DB db = mongoClient.getDB( "mydb" );
					DBCollection coll_parc = db.getCollection("Wifi_spots");
					cursor = coll_parc.find();
					envoyerPOI(cursor,is,out);
		       }
		       
		       else if(message.equals("musee"))
		       {
		    	   MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
					DB db = mongoClient.getDB( "mydb" );
					DBCollection coll_parc = db.getCollection("Musees");
					cursor = coll_parc.find();
					envoyerPOI(cursor,is,out);
		       }
		       else if(message.equals("itineraire"))
		       {
		    	   JSONArray ar=new JSONArray();
	
		    	    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
					DB db = mongoClient.getDB( "mydb" );
					DBCollection coll_parc = db.getCollection("Itineraire");
					cursor = coll_parc.find();
					try {
						 while(cursor.hasNext()) {
						      System.out.println(cursor.next());
						      String name= (String) cursor.curr().get("name");
						      ArrayList<String> list=(ArrayList<String>)cursor.curr().get("nom");
						      ArrayList<Double> list1=( ArrayList<Double>)cursor.curr().get("x");
						      ArrayList<Double> list2=( ArrayList<Double>)cursor.curr().get("y");
						      
						     
						      JSONObject jsonObj = new JSONObject();
								jsonObj.put("name", name);
						
						     
								ar.put(jsonObj);
						
						   }
						 System.out.println(ar.toString());
						 out.println(ar.toString());
						 out.flush();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  finally {
							 cursor.close();
					 }
		       }
		       else if(message.equals("carte"))
		       {
		    	   JSONArray ar=new JSONArray();
	
		    	    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
					DB db = mongoClient.getDB( "mydb" );
					DBCollection coll_parc = db.getCollection("Itineraire");
					cursor = coll_parc.find();
					
					
					String name= is.readLine();
					System.out.println(name);
					BasicDBObject query = new BasicDBObject("name", name);
					cursor = coll_parc.find(query);
					
					
					try {
					  
						String nom= (String) cursor.next().get("name");
						System.out.println(nom);
					      ArrayList<String> list=(ArrayList<String>)cursor.curr().get("nom");
					      ArrayList<Double> list1=( ArrayList<Double>)cursor.curr().get("x");
					      ArrayList<Double> list2=( ArrayList<Double>)cursor.curr().get("y");
					      
					      
					      JSONObject jsonObj = new JSONObject();
							jsonObj.put("nom", list);
							jsonObj.put("x", list1);
							jsonObj.put("y", list2);
					      
							ar.put(jsonObj);
						 System.out.println(ar.toString());
						 out.println(ar.toString());
						 out.flush();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  finally {
							 cursor.close();
					 }
				
					
		       }

		    } catch (IOException e) {
		    	System.err.println("Le client s'est deconnecte !");
				e.printStackTrace();

		    				}
	        
		}
	}

	

	 private double distance(double lat1, double lat2, double lon1, double lon2) {

		    final int R = 6371; // Radius of the earth

		    Double latDistance = deg2rad(lat2 - lat1);
		    Double lonDistance = deg2rad(lon2 - lon1);
		    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
		            + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
		            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		    double distance = R * c * 1000; // convert to meters

		    
		    distance = Math.pow(distance, 2) + Math.pow(0, 2);
		    return Math.sqrt(distance);
		}

		private double deg2rad(double deg) {
		    return (deg * Math.PI / 180.0);
		}
		
public void envoyerPOI(DBCursor cursor,BufferedReader  is,PrintWriter out) throws NumberFormatException, IOException{

		JSONArray ar=new JSONArray();
		   
		try {
		    double distance_max = Double.parseDouble(is.readLine());
		       double longitude_client= Double.parseDouble(is.readLine());
		       double latitude_client= Double.parseDouble(is.readLine());
		       System.out.println(distance_max);
		       System.out.println(longitude_client);
		       System.out.println(latitude_client);
		  do {
			
			  DBObject objet=cursor.next();
			  
			  double longitude= (double) objet.get("x");
			 
			  
			  double latitude= (double) objet.get("y");
			 
			  
			  double distance1= distance(latitude_client,latitude,longitude_client,longitude);
			  
			  
			  if(distance1<distance_max)
			  {
			      System.out.println(longitude);
				  System.out.println(latitude);
				  String result = String.valueOf(longitude);
				  String result1 = String.valueOf(latitude);
				  String m= (String) objet.get("name");
				  String m1= (String) objet.get("adresse");
		
			  	try {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("name", m);
					jsonObj.put("adresse", m1);
					jsonObj.put("x", result);
					jsonObj.put("y", result1);
					ar.put(jsonObj);
					exist=true;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			
		   }  while(cursor.hasNext());
	  if(exist=true)
		  {
		  
		  System.out.println(ar.toString());
		 out.println(ar.toString());
		 out.flush();
		 
		  }
		  else
		  {
			  //out.println("stop");
		  }
		} finally {
		   cursor.close();
		}
		 /*out.println("stop");
		System.out.println("stop");*/
   }
	}

	