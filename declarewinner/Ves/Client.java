

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	public static void main(String[] args) throws NotBoundException {
		
		try {
			Socket client = new Socket("localhost", 12345);
			Hand hand = new Hand();
			
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			
			oos.writeObject(hand);
			
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
			
			Hand receivedHand = (Hand) ois.readObject();
			
			System.out.println("My hand: " + hand.getHand());
			System.out.println("Received hand: " + receivedHand.getHand());
			
			Registry register= LocateRegistry.getRegistry("localhost", 12344);
			
			DeclareWinnerInterface dwi = (DeclareWinnerInterface) register.lookup("verifyWinner");
			
			String res = dwi.whoWon(hand.getHand(), receivedHand.getHand());
			
			System.out.println(res);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
