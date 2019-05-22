
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Ark { 
	private ArrayList <String> animals;

    public Ark() throws RemoteException{
    	
        animals  = new ArrayList<String>();

    }

    public void addAnimal(String s){
        animals.add(s);
    }

    public String listAnimals(){
      String s = "";
      for(String a : animals){
        s += a+"\n";
      }
      return s;
    }

}
