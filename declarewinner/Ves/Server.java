
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static void main(String[] args) {

		try {
			ServerSocket server = new ServerSocket(12345);
			while (true) {
				Socket socket = server.accept();
				new Thread(new ServerThread(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class ServerThread extends Thread {

	static List<Socket> socket = new ArrayList<Socket>();

	public ServerThread(Socket s) {
		socket.add(s);
	}

	public void run() {

		try {

			if (socket.size() == 2) {
				ObjectInputStream ois1 = new ObjectInputStream(socket.get(0).getInputStream());
				ObjectOutputStream oos1 = new ObjectOutputStream(socket.get(0).getOutputStream());

				ObjectInputStream ois2 = new ObjectInputStream(socket.get(1).getInputStream());
				ObjectOutputStream oos2 = new ObjectOutputStream(socket.get(1).getOutputStream());

				Hand hand1 = (Hand) ois1.readObject();
				Hand hand2 = (Hand) ois2.readObject();

				oos1.writeObject(hand2);
				oos2.writeObject(hand1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
