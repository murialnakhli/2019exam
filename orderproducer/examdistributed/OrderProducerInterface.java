/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examdistributed;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author samir
 */
public interface OrderProducerInterface extends Remote{
  public String listToppings() throws RemoteException;
  public Order newRandomOrder()throws RemoteException;

}
