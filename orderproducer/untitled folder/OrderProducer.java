package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OrderProducer extends UnicastRemoteObject implements OrderProducerInterface{

    public OrderProducer() throws RemoteException{}

    public Order newOrder(String flavour) throws RemoteException{
        return new Order(flavour);
    }

    public Order newRandomOrder() throws RemoteException {
        return new Order();
    }

    public String listToppings() throws RemoteException {
      return " TODAY'S TOPPINGS: " + String.join(",", Order.getToppings());
    }

}