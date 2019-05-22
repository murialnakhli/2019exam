
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;

public class Client {

	public static void main(String[] args) {
	try {
		Socket client = new Socket("localhost", 12345);
		 PrintWriter print = new PrintWriter(client.getOutputStream());
         print.println("ready");
         print.flush();
		
	} catch (UnknownHostException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	
	
	}
	
}
