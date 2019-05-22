import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class DeclareWinner
  extends UnicastRemoteObject
  implements DeclareWinnerInterface
  {
    public DeclareWinner() throws RemoteException{}

    public String whoWon(char me, char theOther){                
        if(me==theOther)
          return "You draw";

        if((me=='s' && theOther=='p') || (me=='p' && theOther=='r') || (me=='r' && theOther=='s'))
          return "You won";

        return "You lost";
    }

}
