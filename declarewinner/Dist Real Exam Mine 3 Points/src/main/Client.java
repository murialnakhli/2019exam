package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Client {
	
	public static void main(String[] args) {
		
		// #1 Can't create a Socket without a try catch block
		try {
			Socket socket = new Socket("localhost", 12345);
			
			// #4 Sending the hand to the Server
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		
			Hand myHand = new Hand();
			oos.writeObject(myHand);
			
			// #7 Getting alternative hand and reading out
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Hand opponentsHand = (Hand) ois.readObject();
			
			System.out.println("My hand was: " + 
			myHand.getHand()  + " opponent's: " + opponentsHand.getHand());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
