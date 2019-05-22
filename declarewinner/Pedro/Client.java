import java.util.*;
import java.net.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;

public class Client {
	Socket internalSocket;
	PrintWriter pw;
	Scanner sc;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	public static void main(String[] args) {
		final int port = 12345;
		final String url = "localhost";
		
		// Use: java Client -[option]
		char option = args[0].charAt(1);
		
		try {
			Socket s = new Socket(url,port);
			new Client(s, option);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public Client(Socket s, char option) {
		this.internalSocket = s;
		try {
			this.pw = new PrintWriter(s.getOutputStream());
			this.sc = new Scanner(s.getInputStream());
			this.oos = new ObjectOutputStream(s.getOutputStream());
			this.ois = new ObjectInputStream(s.getInputStream());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Created streams.");
		
		try {
			oos.writeObject(new Hand(option));
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Sent hand.");
		
		try {
			Hand opponentHand = (Hand) ois.readObject();
			System.out.println("Opponent Hand: "  + opponentHand.getHand());
			
			Registry regClient = LocateRegistry.getRegistry("localhost", 12344);
			
			DeclareWinnerInterface d = (DeclareWinnerInterface) regClient.lookup("verifyWinner");
			
			System.out.println("Result:" + d.whoWon(option, opponentHand.getHand()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String receiveString() {
		while(!sc.hasNext()) {}
		String s = sc.nextLine();
		return s;
	}
	
	public void sendString(String s) {
		pw.println(s);
		pw.flush();
	}

}
