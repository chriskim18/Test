package mongodb;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.URL;

import org.apache.commons.io.IOUtils;//telecharger "commons-io-1.3.1.jar"


public class Geocoding {

	
	
	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json"; 
	public String getJSONByGoogle(String fullAddress) throws IOException {

		
		
		//"address,city,state,zipcode". Here address means "street number + route" .
URL url = new URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8")+ "&sensor=false");

//Open the Connection
URLConnection conn = url.openConnection();
ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
IOUtils.copy(conn.getInputStream(), output);
//close the byte array output stream now.
output.close();


return output.toString(); // This returned String is JSON string from which you can retrieve all key value pair and can save it in POJO.
		
	}
	 public static void main( String[] args ) throws IOException{
		 Geocoding g = new Geocoding();
	//	 System.out.println(g.getJSONByGoogle("31 boulevard de grenelle,Paris,France,75015"));


	
	

		 
	 }
	
}
