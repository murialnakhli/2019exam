/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examdistributed;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author samir
 */
public class Client {
    
   public static void main(String[] args) {
		
		try{
	          Socket s = new Socket("localhost", 12349);
	          System.out.println("Client initialized");
                  
                  
                  Registry registry = LocateRegistry.getRegistry("localhost", 12331);
                  OrderProducerInterface reference = (OrderProducerInterface) registry.lookup("GenerateOrders");
	          
	          System.out.println("List toppings = " + reference.listToppings());
                  
                  Order order = reference.newRandomOrder();
                  
                  
                  //send order object
                  while(true) {
		          ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		          ObjectInputStream ois = new ObjectInputStream (s.getInputStream());
		        
		          
		          oos.writeObject(order);

		          
		          order = (Order) ois.readObject();
		          System.out.println("Client hand recived = " + order);
		          break;
		          
		          
	          
	          }
                  
                  //For each call from a RMI client the RMI server will execute the call in a new thread by deafult.
                  
                          
	        }catch(Exception e){
	        	
	          System.out.println(e);
	}
    
   }
}
