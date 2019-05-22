package main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	
	
	public static void main(String[] args) {
		
		// #2 Again, everything in try/catch cos fuck it
		try {
			ServerSocket ss = new ServerSocket(12345);
			
			
			// #3 For now just trying to do it in a weird way
			Socket s1 = ss.accept();
			Socket s2 = ss.accept();
			
			// #5 Receiving hands from Clients
			ObjectInputStream ois1 = new ObjectInputStream(s1.getInputStream());
			ObjectInputStream ois2 = new ObjectInputStream(s2.getInputStream());
			
			Hand hand1 = (Hand) ois1.readObject();
			Hand hand2 = (Hand) ois2.readObject();
			
			
			System.out.println("Server got hand1:" + hand1.getHand());
			System.out.println("Server got hand2:" + hand2.getHand());
			
			// #6 Sending the different hands to clients
			ObjectOutputStream oss1 = new ObjectOutputStream(s1.getOutputStream());
			ObjectOutputStream oss2 = new ObjectOutputStream(s2.getOutputStream());
			
			oss1.writeObject(hand2);
			oss2.writeObject(hand1);

			
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
