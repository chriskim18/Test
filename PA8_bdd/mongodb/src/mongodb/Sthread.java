package mongodb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Sthread implements Runnable {
    
    private ServerSocket socketserver;
    private Socket socket;
    private BufferedReader is = null;
	private PrintWriter out = null;
    public Sthread(ServerSocket s){
        socketserver = s;
    }
    
    public void run() {
        
        Serveur charg = new Serveur();
        
        try {     
        	System.out.println("Server launched");
            while(true){ 
                
                socket = socketserver.accept(); // Si un client se connecte on crée un socket 
                System.out.println("A client was connected");
                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream os = socket.getOutputStream();
                out = new PrintWriter(socket.getOutputStream());
        		Thread t3 = new Thread(new Reception(is,os,out));
        		t3.start();

            }
        } catch (IOException e) {e.printStackTrace();}
    }   
}
