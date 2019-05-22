import java.rmi.*;

public interface DeclareWinnerInterface extends Remote{
  public String whoWon(char me, char theOther) throws RemoteException;
}
