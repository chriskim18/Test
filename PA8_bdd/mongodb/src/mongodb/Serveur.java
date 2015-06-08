package mongodb;

import java.io.IOException;
import java.net.ServerSocket;



public class Serveur {

 
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try
        {
            ServerSocket socketserver;
            socketserver = new ServerSocket(2556);  // Creation serversocket avec numero de port 
            Thread t = new Thread(new Sthread(socketserver));  // nouveau thread
            t.start();
        } catch (IOException e) {		
            e.printStackTrace();
        }
    }   
}
