/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitas;

import java.io.Serializable;
public class Transaksi implements Serializable{
    private String id_transaksi;
    private String id_admin ;
    private String id_customer;
    private String id_kamar;
    private String cek_in;
    private String cek_out;
    private int lama_inap;
    private int total_biaya;
    private int jumlah;

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public String getId_kamar() {
        return id_kamar;
    }

    public void setId_kamar(String id_kamar) {
        this.id_kamar = id_kamar;
    }

    public String getCek_in() {
        return cek_in;
    }

    public void setCek_in(String cek_in) {
        this.cek_in = cek_in;
    }

    public String getCek_out() {
        return cek_out;
    }

    public void setCek_out(String cek_out) {
        this.cek_out = cek_out;
    }

    public int getLama_inap() {
        return lama_inap;
    }

    public void setLama_inap(int lama_inap) {
        this.lama_inap = lama_inap;
    }

    public int getTotal_biaya() {
        return total_biaya;
    }

    public void setTotal_biaya(int total_biaya) {
        this.total_biaya = total_biaya;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    } 

    public String getId_admin() {
        return id_admin;
    }

    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
    }
}
