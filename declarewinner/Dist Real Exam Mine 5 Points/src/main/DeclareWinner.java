package main;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

// #9 Add the "extends UnicastRemoteObject" part
public class DeclareWinner extends UnicastRemoteObject implements DeclareWinnerInterface {
	
	// #10 Add the "throws RemoteException" part
    public DeclareWinner() throws RemoteException{
    	 
    }
    // #11 Add the "throws RemoteException" part and find that
    // it doesn't match the interface so see interface for #12
    public String whoWon(char me, char theOther) throws RemoteException {                
        if(me==theOther)
          return "You draw";

        if((me=='s' && theOther=='p') || (me=='p' && theOther=='r') || (me=='r' && theOther=='s'))
          return "You won";

        return "You lost";
    }

}
