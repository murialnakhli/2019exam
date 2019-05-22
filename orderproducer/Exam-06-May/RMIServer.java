import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

	public static void main(String[] args) throws RemoteException {
		try{
            Registry r = LocateRegistry.createRegistry(12345);
            r.rebind("GenerateOrders", new OrderProducer());
        } catch (Exception e) {}
	}
}