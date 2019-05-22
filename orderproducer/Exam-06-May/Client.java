import java.rmi.*;
import java.rmi.registry.*;

public class Client{
    public static void main(String args[]) throws Exception{
        try {
            Registry r = LocateRegistry.getRegistry("localhost", 12345);
            OrderProducerInterface opi = (OrderProducerInterface) r.lookup("GenerateOrders");
            Order o = opi.newOrder("Strawberry");
            System.out.println(o.toString());
        } catch (Exception e){}
    }
}