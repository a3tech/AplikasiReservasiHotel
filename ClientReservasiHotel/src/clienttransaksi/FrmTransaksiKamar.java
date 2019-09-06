/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttransaksi;


import entitas.Admin;
import entitas.Kamar;
import entitas.Customer;
import entitas.Transaksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import remoteserver.RemoteServerLogin;
import remoteserver.RemoteServerTransaksi;


public class FrmTransaksiKamar extends javax.swing.JFrame {
 private String namaOp;
 private RemoteServerTransaksi rst;
 private RemoteServerLogin rsl;
 private int iFrm;
 private DefaultTableModel dtmTransaksi;
 private Kamar DtKamar = new Kamar();
 private final FrmCariKamar formKamar = new FrmCariKamar();
 private Customer DtCustomer = new Customer();
 private final FrmCariCustomer formCustomer = new FrmCariCustomer();
 private boolean statusProses;
 DecimalFormat dfo = new DecimalFormat("#,##0");
 
   private String tanggal(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date ();
        return df.format(d);
    }
        private void aturButton (boolean status){
        btnAdd.setEnabled(status);
        btnSave.setEnabled(status);
        if (status == true){
            btnExit.setText("Keluar");
        }else{
            btnExit.setText("Batal");
        }
    }
    
    private void aturTeks(boolean status){
        txtIdCustomer.setEditable(status);
        txtNama.setEditable(status);
        txtJenkel.setEditable(status);
        txtAreaAlamat.setEditable(status);
        txtNoTelp.setEditable(status);
        txtNoKamar.setEditable(status);
        txtTipe.setEditable(status);
        txtTarif.setEditable(status);
        txtLamaInap.setEditable(status);
        txtBayar.setEditable(status);
        txtCheckin.setEditable(status);
        txtCheckout.setEditable(status);
        txtJumlah.setEditable(status);
        txtTanggal.setText(tanggal()); }
      private void teksKosong(){
        txtIdCustomer.setText("");
        txtNama.setText("");
        txtJenkel.setText("");
        txtAreaAlamat.setText("");
        txtNoTelp.setText("");
        txtNoKamar.setText("");
        txtTipe.setText("");
        txtTarif.setText("");
        txtCheckin.setText("");
        txtCheckout.setText("");
        txtLamaInap.setText("");
        txtJumlah.setText("");
        txtBiayaKamar.setText("");
    }
 public void setRst(RemoteServerTransaksi rst){
     this.rst= rst;
 }
 
 public void setRsl(RemoteServerLogin rsl){
     this.rsl=rsl;
 }
 
 public void setiFrm(int iFrm, String nama){
     this.iFrm = iFrm;
     setNamaOp(nama);
 }
 
 public void setNamaOp(String namaOp){
     this.namaOp = namaOp;
 }
 public void aturTblTransaksi(){
     dtmTransaksi = (DefaultTableModel) tblTransaksi.getModel();
     dtmTransaksi.setRowCount(0);
 }
    public FrmTransaksiKamar() {
        initComponents();
        txtIDKaryawan.setText("A-002");
        txtKembali.setEditable(false);
        txtBiayaKamar.setEditable(false);
        txtTotal.setEditable(false);
        txtIDKaryawan.setEditable(false);
        txtTanggal.setEditable(false);
        aturButton(true);
         aturTeks(false);
    aturTblTransaksi();
    formKamar.getbtnPilih().addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent ae) {
   if(formKamar.gettblKamar().getRowCount() > 0){
   int barisKe = formKamar.gettblKamar().getSelectedRow();
       DtKamar.setId_kamar(formKamar.gettblKamar().getModel().getValueAt(
               barisKe, 0).toString());
      DtKamar.setTipe_kamar(formKamar.gettblKamar().getModel().getValueAt(
               barisKe, 2).toString());
DtKamar.setTarif(Integer.parseInt(formKamar.gettblKamar().getModel().
        getValueAt(barisKe, 3).toString()));
       txtNoKamar.setText(DtKamar.getId_kamar());
       txtTipe.setText(DtKamar.getTipe_kamar());
       txtTarif.setText(String.valueOf(DtKamar.getTarif()));
       txtJumlah.requestFocus();
       formKamar.dispose();   
   } } });
    
   formCustomer.getbtnPilih().addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent ae) {
   if(formCustomer.gettblCustomer().getRowCount() > 0){
       int barisKe = formCustomer.gettblCustomer().getSelectedRow();
   DtCustomer.setId_customer(formCustomer.gettblCustomer().getModel().getValueAt(
               barisKe, 0).toString());
       DtCustomer.setNama_customer(formCustomer.gettblCustomer().getModel().getValueAt(
               barisKe, 1).toString());
       DtCustomer.setJenkel(formCustomer.gettblCustomer().getModel().getValueAt(
               barisKe, 2).toString());
       DtCustomer.setAlamat(formCustomer.gettblCustomer().getModel().getValueAt(
               barisKe, 3).toString());
       DtCustomer.setNo_hp(formCustomer.gettblCustomer().getModel().getValueAt(
               barisKe, 4).toString());
       txtIdCustomer.setText(DtCustomer.getId_customer());
       txtNama.setText(String.valueOf(DtCustomer.getNama_customer()));
       txtJenkel.setText(DtCustomer.getJenkel());
       txtAreaAlamat.setText(DtCustomer.getAlamat());
       txtNoTelp.setText(DtCustomer.getNo_hp());
       txtJumlah.requestFocus();
       formKamar.dispose();   
   } } });
    
    }
    
    
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtJenkel = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaAlamat = new javax.swing.JTextArea();
        txtNoTelp = new javax.swing.JTextField();
        txtIdCustomer = new javax.swing.JTextField();
        btnCariCustomer = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTipe = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCheckin = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCheckout = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtLamaInap = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTarif = new javax.swing.JTextField();
        txtBiayaKamar = new javax.swing.JTextField();
        txtNoKamar = new javax.swing.JTextField();
        btnCariKamar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnHitungTotal = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        txtKembali = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        txtTanggal = new javax.swing.JTextField();
        lblTanggal = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        txtIDKaryawan = new javax.swing.JTextField();
        btnBatal = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORM TRANSAKSI PEMBAYARAN RESERVASI");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM INPUT TRANSAKSI PEMBAYARAN KAMAR HOTEL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("ID Karyawan");

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("ID Customer");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nama");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Jenis Kelamin");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Alamat");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("No Telp");

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        txtAreaAlamat.setColumns(20);
        txtAreaAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAreaAlamat);

        btnCariCustomer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCariCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Search_16x16.png"))); // NOI18N
        btnCariCustomer.setText("Cari");
        btnCariCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJenkel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtIdCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCariCustomer)))
                        .addGap(0, 103, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIdCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariCustomer))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtJenkel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("ID Kamar");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Tipe Kamar");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Check In");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Check Out");

        txtCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckoutActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Lama Inap");

        txtLamaInap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLamaInapMouseClicked(evt);
            }
        });
        txtLamaInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLamaInapActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Tarif Kamar");

        btnCariKamar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCariKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Search_16x16.png"))); // NOI18N
        btnCariKamar.setText("Cari");
        btnCariKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariKamarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Biaya Inap");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Jumlah");

        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJumlahKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel9))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBiayaKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(txtNoKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnCariKamar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(txtTipe, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtLamaInap)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNoKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariKamar))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtLamaInap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtBiayaKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Bayar");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Kembali");

        btnHitungTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHitungTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bayarr.png"))); // NOI18N
        btnHitungTotal.setText("Proses");
        btnHitungTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungTotalActionPerformed(evt);
            }
        });

        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });

        txtKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKembaliActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add.png"))); // NOI18N
        btnAdd.setText("Tambah");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Save_16x16.png"))); // NOI18N
        btnSave.setText("Simpan");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/1300682129_stock_exit.png"))); // NOI18N
        btnExit.setText("Keluar");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        txtTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTanggalActionPerformed(evt);
            }
        });

        lblTanggal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTanggal.setText("Tanggal Transaksi");

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID KAMAR", "CEK IN", "CEK OUT", "LAMA INAP", "JUMLAH KAMAR", "TARIF KAMAR"
            }
        ));
        jScrollPane2.setViewportView(tblTransaksi);

        btnBatal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete16.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Total Biaya");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtIDKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTanggal)
                                .addGap(18, 18, 18)
                                .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatal)
                        .addGap(68, 68, 68)
                        .addComponent(btnExit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHitungTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(38, 38, 38)
                                .addComponent(txtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTanggal)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(btnHitungTotal))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btnSave)
                        .addComponent(btnExit)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void btnCariCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariCustomerActionPerformed
      try{
          formCustomer.aturTableCustomer(rst.TampilCustomer());
          formCustomer.setVisible(true);
      }catch(RemoteException re){
   Logger.getLogger(FrmTransaksiKamar.class.getName()).log(Level.SEVERE, null, re);
      }
    }//GEN-LAST:event_btnCariCustomerActionPerformed

    private void btnCariKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariKamarActionPerformed
         try{
          formKamar.aturTableKamar(rst.TampilKamar());
          formKamar.setVisible(true);
      }catch(RemoteException re){
   Logger.getLogger(FrmTransaksiKamar.class.getName()).log(Level.SEVERE, null, re);
      }
    }//GEN-LAST:event_btnCariKamarActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
     if (btnExit.getText().equals("Keluar")){
    try{
    ClientMenuKaryawan client = new ClientMenuKaryawan(
        btnExit.getText().trim());
           dispose();
    }catch(NotBoundException nbe){
        
    }catch(RemoteException ex){
        Logger.getLogger(MenuUtamaAdmin.class.getName()).log(Level.SEVERE, null ,ex);
    }
    }else {
        aturTeks(true);
        aturButton(true);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    Transaksi transaksi = new Transaksi();
    transaksi.setId_admin(txtIDKaryawan.getText());
    transaksi.setId_customer(txtIdCustomer.getText());
    transaksi.setId_kamar(txtNoKamar.getText());
    transaksi.setCek_in(txtCheckin.getText());
    transaksi.setCek_out(txtCheckout.getText());
    transaksi.setLama_inap(Integer.parseInt(txtLamaInap.getText()));
    transaksi.setJumlah(Integer.parseInt(txtJumlah.getText()));
    transaksi.setTotal_biaya(Integer.parseInt(txtTotal.getText()));  
        try{
            statusProses = rst.InsertTransaksi(transaksi);
        }catch(RemoteException re){
        statusProses = false;
     Logger.getLogger(FrmTransaksiKamar.class.getName()).log(Level.SEVERE, null, re); }
        if(statusProses){
            JOptionPane.showMessageDialog(rootPane, "Data Transaksi Tersimpan",
                    "Info",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(rootPane, "Proses Gagal",
                    "Error", JOptionPane.ERROR_MESSAGE); }
            teksKosong();
            int row = dtmTransaksi.getRowCount();
        for (int i = 0; i < row; i++) {
            dtmTransaksi.removeRow(0);}
            txtTotal.setText("");
            txtJumlah.setText("");
            txtBayar.setText("");
            txtKembali.setText("");
            teksKosong();
            aturButton(true);
            btnHitungTotal.setEnabled(true);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        btnSave.setEnabled(true);
        btnAdd.setEnabled(false);
        aturTeks(true);
        aturButton(false);
        setLocationRelativeTo(this);
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed
        // TODO add your handling code here:
        int kembali;
    kembali = Integer.parseInt(txtBayar.getText()) - Integer.parseInt(txtTotal.getText());
        txtKembali.setText(String.valueOf(kembali));
        if(kembali<0){
   JOptionPane.showMessageDialog(null, "Maaf Uang Pembayaran Kurang", "Informasi", 
        JOptionPane.INFORMATION_MESSAGE);
            txtBayar.requestFocus();     
        }else{
            JOptionPane.showMessageDialog(null, "Kembali Rp "+kembali);
            aturButton(true);
        }
    }//GEN-LAST:event_txtBayarActionPerformed

    private void txtLamaInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLamaInapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLamaInapActionPerformed

    private void txtCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCheckoutActionPerformed

    private void txtLamaInapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLamaInapMouseClicked
        // TODO add your handling code here:
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try{

            //Konversi dari string ke tanggal
            Date TanggalMasuk =df.parse(txtCheckin.getText());
            Date TanggalKeluar = df.parse(txtCheckout.getText());

            //Tgl di konversi ke milidetik
            long Checkin = TanggalMasuk.getTime();
            long Checkout = TanggalKeluar.getTime();
            long diff = Checkout - Checkin;
            long Lama = diff / (24 * 60 * 60 * 1000);

            txtLamaInap.setText(Long.toString(Lama));
            } catch (ParseException e)
            {
            e.printStackTrace();
            }
    }//GEN-LAST:event_txtLamaInapMouseClicked

    private void btnHitungTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungTotalActionPerformed
        // TODO add your handling code here:
        int total = 0;
        int biaya_kamar = 0;

     biaya_kamar = Integer.parseInt(txtTarif.getText()) * Integer.parseInt(txtLamaInap.
             getText());
        txtBiayaKamar.setText(String.valueOf(biaya_kamar));
        total =  biaya_kamar*Integer.parseInt(txtJumlah.getText());
        txtTotal.setText(String.valueOf(total));
        dtmTransaksi.addRow(new Object[]{txtNoKamar.getText(),
  txtCheckin.getText(),txtCheckout.getText(), txtLamaInap.getText(),txtJumlah.getText(),
            txtTarif.getText()});
        btnHitungTotal.setEnabled(false);
    }//GEN-LAST:event_btnHitungTotalActionPerformed

    private void txtJumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyTyped
    try{
        Integer.valueOf(String.valueOf(evt.getKeyChar()));
    }catch(NumberFormatException e){
        evt.consume();
        getToolkit().beep();
    }
    }//GEN-LAST:event_txtJumlahKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    try{
        rsl.hapusClient(iFrm, namaOp);
    }catch(RemoteException re){
   Logger.getLogger(FrmTransaksiKamar.class.getName()).log(Level.SEVERE, null, re);
    }
    }//GEN-LAST:event_formWindowClosing

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
      if(tblTransaksi.getSelectedRow() >= 0){
            dtmTransaksi.removeRow(tblTransaksi.getSelectedRow());
            txtBiayaKamar.setText(null);
            txtTotal.setText(null);
        }
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txtTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTanggalActionPerformed

    private void txtKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKembaliActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmTransaksiKamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmTransaksiKamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmTransaksiKamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmTransaksiKamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmTransaksiKamar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCariCustomer;
    private javax.swing.JButton btnCariKamar;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHitungTotal;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextArea txtAreaAlamat;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtBiayaKamar;
    private javax.swing.JTextField txtCheckin;
    private javax.swing.JTextField txtCheckout;
    private javax.swing.JTextField txtIDKaryawan;
    private javax.swing.JTextField txtIdCustomer;
    private javax.swing.JTextField txtJenkel;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKembali;
    private javax.swing.JTextField txtLamaInap;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoKamar;
    private javax.swing.JTextField txtNoTelp;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTarif;
    private javax.swing.JTextField txtTipe;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
