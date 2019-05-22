/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examdistributed;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author samir
 */
public class RMIServer {
    
    public static void main(String[] args) throws RemoteException {
		 Registry r = LocateRegistry.createRegistry(12331);
		 r.rebind("GenerateOrders", new OrderProducer());
		 System.out.println("Service is deployed");
	}
    
}
