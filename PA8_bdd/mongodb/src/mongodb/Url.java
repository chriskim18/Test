package mongodb;


import java.io.File;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;

 
import java.io.File;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Timer;

import java.util.TimerTask;

import org.apache.commons.io.FileUtils;
 
public class Url {
 
	private String fichier;
	  private String url;
	  URL dl = null;
      File fl = null;
	  public static void main( String[] args )
	    {
		  Timer timer = new Timer();
		  timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  Url wifi= new Url("wifi.csv","http://opendata.paris.fr/explore/dataset/liste_des_sites_des_hotspots_paris_wifi/download/?format=csv&timezone=Europe/Berlin");
				  Url parc= new Url("parcs.csv","http://opendata.paris.fr/explore/dataset/parcsetjardinsparis2010/download?format=csv");
			  }
			}, 0,60*1000); //60*1000 = 1000ms *60 = 1 minute-> telechargement des fichiers toutes les 1 minutes
	    }
 
	  public Url(String fichier, String url) {
 
	        try {
//Ici je vais télécharger mon fichier directement dans le répertoire du projet
	        	
	            fl = new File(fichier);
	        	dl = new URL(url);
	        	FileUtils.copyURLToFile(dl, fl);
	        } catch (Exception e) {
	            System.out.println(e);
	        }
 
		 } 
	  
		 public String getFichier(){
			 return fichier;
		 }
 }