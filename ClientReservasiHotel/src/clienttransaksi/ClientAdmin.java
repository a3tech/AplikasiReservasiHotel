/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttransaksi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import remoteclient.RemoteClientLogin;
import remoteserver.RemoteServerLogin;
import remoteserver.RemoteServerTransaksi;
public class ClientAdmin extends UnicastRemoteObject
implements RemoteClientLogin{
    Registry registry;
    RemoteServerLogin rsl;
    RemoteServerTransaksi rst;
    FrmAdmin form = new FrmAdmin();
    int iClient = 0;
    String namaOperator;
    public ClientAdmin(String namaOp) throws RemoteException,
            NotBoundException{
        super();
        namaOperator = namaOp;
        registry = LocateRegistry.getRegistry("localhost", 1234);
        rst = (RemoteServerTransaksi) registry.lookup("ServerTransaksi");
        rsl = (RemoteServerLogin) registry.lookup("ServerTransaksi");
        form.setVisible(true);
        form.setiFrm(rsl.daftarClient(this, namaOp), namaOp);
        form.setRsl(rsl);
        form.setRst(rst); }
    @Override
    public void setClient(int i) throws RemoteException {
    form.setiFrm(i+1, namaOperator);  }
    
}
