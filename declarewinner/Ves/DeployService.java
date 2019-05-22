

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DeployService {

	public static void main(String[] args) throws RemoteException {
		Registry r = LocateRegistry.createRegistry(12344);
		r.rebind("verifyWinner", new DeclareWinner());
	}

}
