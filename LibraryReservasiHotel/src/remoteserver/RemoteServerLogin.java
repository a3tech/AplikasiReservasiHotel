/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import remoteclient.RemoteClientLogin;

/**
 *
 * @author atik
 */
public interface RemoteServerLogin extends Remote {
    public int daftarClient(RemoteClientLogin rcl, String nama) 
            throws RemoteException;
    public void hapusClient (int i, String nama) throws RemoteException;
}
