package main;

import java.rmi.*;

// #8 - ... for 3 points
// #8 Adapt interface so it can be used
public interface DeclareWinnerInterface extends Remote{
	// #12 Add throws RemoteException
  public String whoWon(char me, char theOther) throws RemoteException;
}
