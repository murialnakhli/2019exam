


import java.net.*;


public class Server { 
	public static void main(String[] args) { 
		Object lock = new Object();
		try {
			ServerSocket server = new ServerSocket(12345);
			AnimalDeck deck = new AnimalDeck();
			
			while (true) {
				Socket s = server.accept();
		
			  new Thread(new ServerThread(lock, s, deck)).start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}

class ServerThread extends Thread{
    Object lock;
    Socket s;
    AnimalDeck deck;

    public ServerThread(Object lock, Socket s, AnimalDeck deck){
        this.lock = lock;
        this.s = s;
        this.deck = deck;
    }
}



