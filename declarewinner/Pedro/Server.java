import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Server implements Runnable{
	Socket clientSocket1;
	PrintWriter pw1;
	Scanner sc1;
	ObjectOutputStream oos1;
	ObjectInputStream ois1;
	
	Socket clientSocket2;
	PrintWriter pw2;
	Scanner sc2;
	ObjectOutputStream oos2;
	ObjectInputStream ois2;
	
	Object lock;
	
	public Server(Socket s1, Socket s2, Object lock) {
		this.lock = lock;
		this.clientSocket1 = s1;
		this.clientSocket2 = s2;
		
		try {
			this.sc1 = new Scanner(this.clientSocket1.getInputStream());
			this.pw1 = new PrintWriter(this.clientSocket1.getOutputStream());
			this.oos1 = new ObjectOutputStream(this.clientSocket1.getOutputStream());
			this.ois1 = new ObjectInputStream(this.clientSocket1.getInputStream());
			
			this.sc2 = new Scanner(this.clientSocket2.getInputStream());
			this.pw2 = new PrintWriter(this.clientSocket2.getOutputStream());
			this.oos2 = new ObjectOutputStream(this.clientSocket2.getOutputStream());
			this.ois2 = new ObjectInputStream(this.clientSocket2.getInputStream());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Created streams.");
		
	}
	
	@Override
	public void run() {
		try {
			
			Hand hand1 = (Hand)ois1.readObject();
			Hand hand2 = (Hand)ois2.readObject();
			
			System.out.println("Hand1: " + hand1.getHand());
			System.out.println("Hand2: " + hand2.getHand());
			
			synchronized(lock) {
				oos1.writeObject(hand2);
				oos1.flush();
				
				oos2.writeObject(hand1);
				oos2.flush();
			}
			
			System.out.println("Sent hands to players.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String receiveString(Scanner sc) {
		while(!sc.hasNext()) {}
		String s = sc.nextLine();
		return s;
	}
	
	public void sendString(String s, PrintWriter pw) {
		pw.println(s);
		pw.flush();
	}

}
