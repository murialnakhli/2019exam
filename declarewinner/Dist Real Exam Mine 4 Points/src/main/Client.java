package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.print.attribute.standard.MediaSize.Other;

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
			
			// #16 Use registered RMI to check who won
			Registry registry = LocateRegistry.getRegistry("localhost", 12344);
			
			// #17 Call remote method to check who won
			DeclareWinnerInterface declareWinner = (DeclareWinnerInterface) registry.lookup("verifyWinner");
			System.out.println(declareWinner.whoWon(myHand.getHand(), opponentsHand.getHand()));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
