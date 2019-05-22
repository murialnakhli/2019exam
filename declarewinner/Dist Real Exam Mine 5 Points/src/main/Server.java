package main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	
	
	public static void main(String[] args) {
		
		// #18 Create lock
		Object lock = new Object();
		
		// #2 Again, everything in try/catch cos fuck it
		try {
			ServerSocket ss = new ServerSocket(12345);
			
			// #25 Put stuff in while loop and we'll see
			while (true) {				
				// #3 For now just trying to do it in a weird way
				Socket s1 = ss.accept();
				Socket s2 = ss.accept();
				new Thread(new ServerThread(lock, s1, s2)).start();;
			}
//			
//			// #3 For now just trying to do it in a weird way
//			Socket s1 = ss.accept();
//			Socket s2 = ss.accept();
//			
//			
//			
//			// #5 Receiving hands from Clients
//			ObjectInputStream ois1 = new ObjectInputStream(s1.getInputStream());
//			ObjectInputStream ois2 = new ObjectInputStream(s2.getInputStream());
//			
//			Hand hand1 = (Hand) ois1.readObject();
//			Hand hand2 = (Hand) ois2.readObject();
//			
//			
//			System.out.println("Server got hand1: " + hand1.getHand());
//			System.out.println("Server got hand2: " + hand2.getHand());
//			
//			// #6 Sending the different hands to clients
//			ObjectOutputStream oss1 = new ObjectOutputStream(s1.getOutputStream());
//			ObjectOutputStream oss2 = new ObjectOutputStream(s2.getOutputStream());
//			
//			oss1.writeObject(hand2);
//			oss2.writeObject(hand1);

				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


// #19 Create class which extends Thread
class ServerThread extends Thread {
	
	// #20 Create local lock and socket
	// Since we need to create a thread every 2 clients maybe it's good to
	// have 2 clients, we'll see
	// *** This can also be done as a List<Socket>
	Object lock;
	Socket s1;
	Socket s2;
	
	// #21 Create Constructor which populates the lock and socket on 
	public ServerThread(Object lock, Socket s1, Socket s2) {
		this.lock = lock;
		this.s1 = s1;
		this.s2 = s2;
	}
	
	// #22 Create our run method
	public void run() {
	
		// #23 Try catch cos fuck it
		try {			
			
			// #24 Copy and pasted shit
			
			// #5 Receiving hands from Clients
			ObjectInputStream ois1 = new ObjectInputStream(s1.getInputStream());
			ObjectInputStream ois2 = new ObjectInputStream(s2.getInputStream());
			
			Hand hand1 = (Hand) ois1.readObject();
			Hand hand2 = (Hand) ois2.readObject();
			
			
			System.out.println("Server got hand1: " + hand1.getHand());
			System.out.println("Server got hand2: " + hand2.getHand());
			
			// #6 Sending the different hands to clients
			ObjectOutputStream oss1 = new ObjectOutputStream(s1.getOutputStream());
			ObjectOutputStream oss2 = new ObjectOutputStream(s2.getOutputStream());
			
			// Totally don't need a lock but still whateves
			synchronized (lock) {
				oss1.writeObject(hand2);
				oss2.writeObject(hand1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}







