/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Asus A456U
 */
public class transaksi {
    private Integer Id_transaksi;
    private Integer Jumlah_barang;
    private Integer Total_harga;
    private Integer Jumlah_pembayaran;
    private Integer Jumlah_kembalian;
    private ArrayList<detail_pemesanan> arrdetail_pemesanan;

    public Integer getId_transaksi() {
        return Id_transaksi;
    }

    public void setId_transaksi(Integer Id_transaksi) {
        this.Id_transaksi = Id_transaksi;
    }

    public Integer getJumlah_barang() {
        return Jumlah_barang;
    }

    public void setJumlah_barang(Integer Jumlah_barang) {
        this.Jumlah_barang = Jumlah_barang;
    }

    public Integer getTotal_harga() {
        return Total_harga;
    }

    public void setTotal_harga(Integer Total_harga) {
        this.Total_harga = Total_harga;
    }

    public Integer getJumlah_pembayaran() {
        return Jumlah_pembayaran;
    }

    public void setJumlah_pembayaran(Integer Jumlah_pembayaran) {
        this.Jumlah_pembayaran = Jumlah_pembayaran;
    }

    public Integer getJumlah_kembalian() {
        return Jumlah_kembalian;
    }

    public void setJumlah_kembalian(Integer Jumlah_kembalian) {
        this.Jumlah_kembalian = Jumlah_kembalian;
    }

    public ArrayList<detail_pemesanan> getarrDetail_pemesanan() {
        return arrdetail_pemesanan;
    }

    public void setarrDetail_pemesanan(ArrayList<detail_pemesanan> detail_pemesanan) {
        this.arrdetail_pemesanan = detail_pemesanan;
    }
    
    
}
