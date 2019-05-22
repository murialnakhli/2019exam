package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// #13 - #17 for 4 points
// Here we just register shit (run this first, before server)
// #13 Make class
public class DeployDeclareWinner {
	
	// #14 Make main method and state that it throws "Exception"
	public static void main(String[] args) throws Exception{
		
		// #15 Create Registry, bind to "verifyWinner" 
		Registry r = LocateRegistry.createRegistry(12344);
		r.bind("verifyWinner", new DeclareWinner());
	}
}
