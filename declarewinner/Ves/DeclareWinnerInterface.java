

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DeclareWinnerInterface extends Remote{
	  public String whoWon(char me, char theOther) throws RemoteException;
}
