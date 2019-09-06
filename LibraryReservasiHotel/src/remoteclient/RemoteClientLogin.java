/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteclient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author atik
 */
public interface RemoteClientLogin extends Remote{
    public void setClient (int i) throws RemoteException;
}
