/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverreservasihotel;

import entitas.Admin;
import entitas.Customer;
import entitas.Kamar;
import entitas.Transaksi;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import remoteclient.RemoteClientLogin;
import remoteserver.RemoteServerTransaksi;
import remoteserver.RemoteServerLogin;
public class ServerTransaksi extends UnicastRemoteObject
implements RemoteServerTransaksi, RemoteServerLogin{
 private KoneksiDB konDB = new KoneksiDB();
 private String strQuery;
 private boolean statusEksekusi;
 private ResultSet rs;
 private List list = new ArrayList();
 
 public ServerTransaksi() throws RemoteException{
     konDB.getKoneksi();
 }
 
    public static void main(String[] args)throws RemoteException {
        Registry reg = LocateRegistry.createRegistry(1234);
        ServerTransaksi st = new ServerTransaksi();
        reg.rebind("ServerTransaksi", st);
        System.out.println("Server Berhasil Berjalan");
    }

    @Override
    public boolean InsertAdmin(Admin admin) throws RemoteException {
    strQuery = "SPInsertAdmin'"+admin.getUsername()+"','"
    +admin.getPassword()+"','"+admin.getNama_admin()+"','"
    +admin.getJenkel()+"','"+admin.getAlamat()+"','"+admin.getNo_hp()
    +"','"+admin.getJabatan()+"'";
    konDB.getKoneksi();
    statusEksekusi = konDB.eksekusiNonQuery(strQuery);
    return statusEksekusi;
    }

    @Override
    public boolean UpdateAdmin(Admin update) throws RemoteException {
  strQuery = "SPUpdateAdmin'"+update.getId_admin()+"','"+update.getUsername()+"','"
    +update.getPassword()+"','"+update.getNama_admin()+"','"
    +update.getJenkel()+"','"+update.getAlamat()+"','"+update.getNo_hp()+"','"+
            update.getJabatan()+"'";
    konDB.getKoneksi();
    rs = konDB.eksekusiQuery(strQuery);
    return statusEksekusi;
    }

    @Override
    public boolean DeleteAdmin(String kode) throws RemoteException {
    strQuery = "SPHapusAdmin'"+kode+"'";
    konDB.getKoneksi();
    rs = konDB.eksekusiQuery(strQuery);
    return statusEksekusi;
    }

    @Override
    public List TampilAdmin() throws RemoteException {
    strQuery = "SPTampilAdmin";
    konDB.getKoneksi();
    rs = konDB.eksekusiQuery(strQuery);
    List listkaryawan = new ArrayList<Admin>();
    try{
        while(rs.next()){
            Admin data = new Admin();
            data.setId_admin(rs.getString(1));
            data.setUsername(rs.getString(2));
            data.setPassword(rs.getString(3));
            data.setNama_admin(rs.getString(4));
            data.setJenkel(rs.getString(5));
            data.setAlamat(rs.getString(6));
            data.setNo_hp(rs.getString(7));
            data.setJabatan(rs.getString(8));
            listkaryawan.add(data);
        }
        rs.close();
        return listkaryawan;
    }catch(SQLException se){
        System.out.println("Eksekusi query error : "+se);
        return null;
    }
    }

    @Override
    public boolean InsertCustomer(Customer customer) throws RemoteException {
    strQuery = "SPInsertCustomer'"+customer.getNama_customer()+"','"
    +customer.getJenkel()+"','"+customer.getAlamat()+"','"
    +customer.getNo_hp()+"'";
    konDB.getKoneksi();
    statusEksekusi = konDB.eksekusiNonQuery(strQuery);
    return statusEksekusi;
    }

    @Override
    public boolean UpdateCustomer(Customer update) throws RemoteException {
  strQuery = "SPUpdateCustomer'"+update.getId_customer()+"','"+update.getNama_customer()+"','"
    +update.getJenkel()+"','"+update.getAlamat()+"','"
    +update.getNo_hp()+"'";
    konDB.getKoneksi();
    rs = konDB.eksekusiQuery(strQuery);
    return statusEksekusi;
    }

    @Override
    public boolean DeleteCustomer(String kode) throws RemoteException {
   strQuery = "SPHapusCustomer'"+kode+"'";
    konDB.getKoneksi();
    rs = konDB.eksekusiQuery(strQuery);
    return statusEksekusi;
    }

    @Override
    public List TampilCustomer() throws RemoteException {
    strQuery = "SPTampilCustomer";
    konDB.getKoneksi();
    rs = konDB.eksekusiQuery(strQuery);
    List listcustomer = new ArrayList<Customer>();
    try{
        while(rs.next()){
            Customer data = new Customer();
            data.setId_customer(rs.getString(1));
            data.setNama_customer(rs.getString(2));
            data.setJenkel(rs.getString(3));
            data.setAlamat(rs.getString(4));
            data.setNo_hp(rs.getString(5));
            listcustomer.add(data);
        }
        rs.close();
        return listcustomer;
    }catch(SQLException se){
        System.out.println("Eksekusi query error : "+se);
        return null;
    }
    }

    @Override
    public boolean InsertKamar(Kamar kamar) throws RemoteException {
    strQuery = "SPInsertKamar'"+kamar.getId_admin()+"','"
    +kamar.getTipe_kamar()+"','"+kamar.getTarif()+"','"
    +kamar.getKetersediaan()+"'";
    konDB.getKoneksi();
    statusEksekusi = konDB.eksekusiNonQuery(strQuery);
    return statusEksekusi;
    }

    @Override
    public boolean UpdateKamar(Kamar update) throws RemoteException {
    strQuery = "SPUpdateKamar'"+update.getId_kamar()+"','"+update.getId_admin()+"','"
    +update.getTipe_kamar()+"','"+update.getTarif()+"','"+update.getKetersediaan()+"'";
    konDB.getKoneksi();
    rs = konDB.eksekusiQuery(strQuery);
    return statusEksekusi;
    }

    @Override
    public boolean DeleteKamar(String kode) throws RemoteException {
    strQuery = "SPHapusKamar'"+kode+"'";
    konDB.getKoneksi();
    rs = konDB.eksekusiQuery(strQuery);
    return statusEksekusi;
    }

    @Override
    public List TampilKamar() throws RemoteException {
    strQuery = "SPTampilKamar";
    konDB.getKoneksi();
    rs = konDB.eksekusiQuery(strQuery);
    List listkamar = new ArrayList<Kamar>();
    try{
        while(rs.next()){
            Kamar data = new Kamar();
            data.setId_kamar(rs.getString(1));
            data.setId_admin(rs.getString(2));
            data.setTipe_kamar(rs.getString(3));
            data.setTarif(rs.getInt(4));
            data.setKetersediaan(rs.getInt(5));
            listkamar.add(data);
        }
        rs.close();
        return listkamar;
    }catch(SQLException se){
        System.out.println("Eksekusi query error : "+se);
        return null;
    }
    }

    @Override
    public boolean InsertTransaksi(Transaksi transaksi) throws RemoteException {
    strQuery = "SPInsertTransaksi'"+transaksi.getId_admin()+"','"
    +transaksi.getId_customer()+"','"+transaksi.getId_kamar()+"','"
  +transaksi.getCek_in()+"','"+transaksi.getCek_out()+"','"+transaksi.getLama_inap()+"','"
    +transaksi.getJumlah()+"','"+transaksi.getTotal_biaya()+"'";
    konDB.getKoneksi();
    statusEksekusi = konDB.eksekusiNonQuery(strQuery);
    return statusEksekusi;
    }

    @Override
    public boolean UpdateTransaksi(Transaksi update) throws RemoteException {
    strQuery = "SPUpdateTransaksi'"+update.getId_transaksi()+"','"+update.getCek_in()+"','"
    +update.getCek_out()+"','"+update.getLama_inap()+"','"+update.getJumlah()+"','"
    +update.getTotal_biaya()+"'";
    konDB.getKoneksi();
    rs = konDB.eksekusiQuery(strQuery);
    return statusEksekusi;
    }

    @Override
    public boolean DeleteTransaksi(String kode) throws RemoteException {
    strQuery = "SPHapusTransaksi'"+kode+"'";
    konDB.getKoneksi();
    rs = konDB.eksekusiQuery(strQuery);
    return statusEksekusi;
    }

    @Override
    public List TampilTransaksi() throws RemoteException {
    strQuery = "SPTampilTransaksi";
    konDB.getKoneksi();
    rs = konDB.eksekusiQuery(strQuery);
    List listtransaksi = new ArrayList<Transaksi>();
    try{
        while(rs.next()){
            Transaksi data = new Transaksi();
            data.setId_transaksi(rs.getString(1));
            data.setId_admin(rs.getString(2));
            data.setId_customer(rs.getString(3));
            data.setId_kamar(rs.getString(4));
            data.setCek_in(rs.getString(5));
            data.setCek_out(rs.getString(6));
            data.setLama_inap(rs.getInt(7));
            data.setJumlah(rs.getInt(8));
            data.setTotal_biaya(rs.getInt(9));
            listtransaksi.add(data);
        }
        rs.close();
        return listtransaksi;
    }catch(SQLException se){
        System.out.println("Eksekusi query error : "+se);
        return null;
    }
    }

    @Override
    public int daftarClient(RemoteClientLogin rcl, String nama) throws RemoteException {
       int i = 0;
        System.out.println(nama+"login");
        list.add(rcl);
        i = list.indexOf(rcl);
        System.out.println("Jumlah Client :"+list.size());
        return i;
    }

    @Override
    public void hapusClient(int i, String nama) throws RemoteException {
      System.out.println(nama+"logout");
        list.remove(i);
        for(int j = i; j < list.size(); j++){
            RemoteClientLogin rcl = (RemoteClientLogin) list.get(j);
            if(rcl !=null){
                rcl.setClient(i);
            }
        }
        System.out.println("Jumlah Client :"+list.size());
    }
    
}

