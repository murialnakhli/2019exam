import java.rmi.RemoteException;
import java.rmi.registry.*;

public class DeployRMI {

	public static void main(String[] args) {
		final int port = 12344;
		Registry r;
		try {
			r = LocateRegistry.createRegistry(port);
			r.rebind("verifyWinner", new DeclareWinner());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
