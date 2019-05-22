package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
	// THIS IS BY DEFAULT MULTITHREADED!!!!
	// No I will not forget lol
	public static void main(String[] args) throws Exception {
		Registry r = LocateRegistry.createRegistry(12343);
		r.rebind("GenerateOrders", new OrderProducer());
		System.out.println("Test");
	}
	
}
