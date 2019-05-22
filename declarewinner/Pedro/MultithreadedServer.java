import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedServer {

	public static void main(String[] args) {
		final int port = 12345;
		Object lock = new Object();
		ServerSocket ss;
		try {
			ss = new ServerSocket(port);
			
			while(true) {
				Socket s1 = ss.accept();
				System.out.println("First client was connected.");
				Socket s2 = ss.accept();
				System.out.println("Second client was connected.");
				
				new Thread (new Server(s1,s2, lock)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
