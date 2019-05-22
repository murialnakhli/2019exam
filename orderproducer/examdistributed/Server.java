/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examdistributed;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author samir
 */
public class Server {
    
public static void main(String[] args) {
		
	      try{
	    	  ServerSocket ss = new ServerSocket(12349);
	    	  System.out.println("Server initcreateRegistry initialized");

	    	  while (true){
	            Socket s = ss.accept();
	            new Thread(new ServerThread(s)).start();
	    	  }

	      }catch(Exception e){
	        System.out.println(e);
	      }
		
	}
}

class ServerThread extends Thread{
	   
	    Socket s;

	    public ServerThread(Socket s){
	     
	        this.s = s;
	    }

	    public void run(){
	      try{
	    	  
			  
			  
                  ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
	          ObjectInputStream  ois = new ObjectInputStream (s.getInputStream());
	          
	          
	          Order o = (Order) ois.readObject();
	          
	          System.out.println("Order Server = " + o);
                  
                 o.receiveOrder();
                          

	          
	         
		          oos.writeObject(o);
                          //lock is not needed. Because Order is independent object. There is no write operations on these objects. So there is no danger that clients can have read/'write problems.
	          
	          return;
	          
	        
	      }catch(Exception e){
	        System.out.println(e);
	      }
	}
}
