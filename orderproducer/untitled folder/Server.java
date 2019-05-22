package main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		// The lock object would be instantiated here
		// The key parts of the code which MIGHT (this depends on the
		// implementation of the code) need to be in a lock are those 
		// when objects are being read or written to on the output/input
		// streams. The lock would be needed to prevent undesired ordering
		// of command execution. (ie. there need to be for synchronisation)
		// I really want a 5...
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			
			Socket socket = serverSocket.accept();
			
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			
			Order hisOrder = (Order) ois.readObject(); // This would be in a lock
			hisOrder.receiveOrder();
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(hisOrder); // This would be in a lock
			
			
		} catch (Exception e) {
			
			e.printStackTrace();		
		}
		
	}

}
