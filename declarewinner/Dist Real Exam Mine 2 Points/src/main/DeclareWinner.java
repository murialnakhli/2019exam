package main;

import java.rmi.*;

public class DeclareWinner
  implements DeclareWinnerInterface
  {
    public DeclareWinner(){}

    public String whoWon(char me, char theOther){                
        if(me==theOther)
          return "You draw";

        if((me=='s' && theOther=='p') || (me=='p' && theOther=='r') || (me=='r' && theOther=='s'))
          return "You won";

        return "You lost";
    }

}
