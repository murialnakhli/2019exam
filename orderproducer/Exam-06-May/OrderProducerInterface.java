import java.rmi.*;

public interface OrderProducerInterface extends Remote{
    public Order newOrder(String flavour) throws RemoteException;
    public Order newRandomOrder() throws RemoteException;
    public String listToppings() throws RemoteException;
}