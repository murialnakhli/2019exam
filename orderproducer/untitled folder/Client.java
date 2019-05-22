package main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	public static void main(String[] args) {
		
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 12343);
			
			OrderProducerInterface orderProducer = (OrderProducerInterface) registry.lookup("GenerateOrders");
			
			Order myOrder = orderProducer.newOrder("Strawberry");
			System.out.println("The order is: " + myOrder.toString());
		
			
			
			Socket socket = new Socket("localhost", 12345);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
			oos.writeObject(myOrder);
			
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			
			Order recievedOrder = (Order) ois.readObject();
			
			System.out.println(recievedOrder.toString());
			
			
		} catch (Exception e ) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
}
