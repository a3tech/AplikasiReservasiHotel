/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteserver;
import entitas.Admin;
import entitas.Customer;
import entitas.Kamar;
import entitas.Transaksi;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteServerTransaksi extends Remote {
public boolean InsertAdmin(Admin admin)throws RemoteException;
public boolean UpdateAdmin(Admin update) throws RemoteException;
public boolean DeleteAdmin(String kode) throws RemoteException;
public List TampilAdmin() throws RemoteException;    

public boolean InsertCustomer(Customer customer)throws RemoteException;
public boolean UpdateCustomer(Customer update) throws RemoteException;
public boolean DeleteCustomer(String kode) throws RemoteException;
public List TampilCustomer() throws RemoteException;

public boolean InsertKamar(Kamar kamar)throws RemoteException;
public boolean UpdateKamar(Kamar update) throws RemoteException;
public boolean DeleteKamar(String kode) throws RemoteException;
public List TampilKamar() throws RemoteException;

public boolean InsertTransaksi(Transaksi transaksi)throws RemoteException;
public boolean UpdateTransaksi(Transaksi update) throws RemoteException;
public boolean DeleteTransaksi(String kode) throws RemoteException;
public List TampilTransaksi() throws RemoteException;   

}
