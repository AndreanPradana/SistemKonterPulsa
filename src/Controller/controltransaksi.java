/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.Koneksi;
import Model.barang;
import Model.detail_pemesanan;
import Model.jenis_barang;
import Model.transaksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus A456U
 */
public class controltransaksi {
    Koneksi koneksi;
    ArrayList<barang> arrBarang;
    ArrayList<transaksi> arrTransaksi;
    ArrayList<jenis_barang> arrJenisBarang;

    public controltransaksi() {
        this.koneksi = new Koneksi();
        this.arrBarang = new ArrayList<>();
        this.arrTransaksi = new ArrayList<>();
        this.arrJenisBarang = new ArrayList<>();
    }
    
    public ArrayList<barang> getDataBarang() throws SQLException{
        this.arrBarang.clear();
        
        ResultSet rs = koneksi.getData("SELECT * FROM BARANG JOIN JENIS_BARANG ON BARANG.ID_JENIS_BARANG = JENIS_BARANG.ID_JENIS_BARANG");
        while(rs.next()) {
            jenis_barang jenis_barang = new jenis_barang();
            jenis_barang.setId_jenis_barang(rs.getInt("ID_JENIS_BARANG"));
            jenis_barang.setNama_jenis_barang(rs.getString("NAMA_JENIS_BARANG"));
                
            barang b = new barang();
            b.setId_barang(rs.getInt("ID_BARANG"));
            b.setNama_barang(rs.getString("NAMA_BARANG"));
            b.setStock_barang(rs.getInt("STOCK_BARANG"));
            b.setHarga_barang(rs.getInt("HARGA_BARANG"));
            
            this.arrBarang.add(b);
        }
        
        return this.arrBarang;
    }
    
    public ArrayList<jenis_barang> getDataJenisBarang() throws SQLException{
     arrJenisBarang.clear();
     
     ResultSet rs = koneksi.getData("SELECT * FROM JENIS_BARANG");
        while(rs.next()){
            jenis_barang jenisbarang = new jenis_barang();
            jenisbarang.setId_jenis_barang(rs.getInt("ID_JENIS_BARANG"));
            jenisbarang.setNama_jenis_barang(rs.getString("NAMA_JENIS_BARANG"));
            
            this.arrJenisBarang.add(jenisbarang);
        }
        return this.arrJenisBarang;
    }
    
    public ArrayList<transaksi> getDataTransaksi() throws SQLException{
        this.arrTransaksi.clear();
        
        ResultSet rs = this.koneksi.getData("SELECT * FROM TRANSAKSI");
        while(rs.next()){
            
            transaksi t = new transaksi();
            t.setId_transaksi(rs.getInt("ID_TRANSAKSI"));
            t.setJumlah_barang(rs.getInt("JUMLAH_BARANG"));
            t.setTotal_harga(rs.getInt("TOTAL_HARGA"));
            t.setJumlah_pembayaran(rs.getInt("JUMLAH_PEMBAYARAN"));
            t.setJumlah_kembalian(rs.getInt("JUMLAH_KEMBALIAN"));
            
            ResultSet rsDetail_Transaksi = koneksi.getData("SELECT * FROM DETAIL_PEMESANAN JOIN BARANG ON DETAIL_PEMESANAN.ID_BARANG = BARANG.ID_BARANG JOIN JENIS_BARANG ON BARANG.ID_JENIS_BARANG = JENIS_BARANG.ID_JENIS_BARANG WHERE DETAIL_PEMESANAN.ID_TRANSAKSI = " + rs.getString("ID_TRANSAKSI"));
            ArrayList<detail_pemesanan> dp = new ArrayList<>();
            while(rsDetail_Transaksi.next()) {
                jenis_barang jenis = new jenis_barang();
                jenis.setId_jenis_barang(rsDetail_Transaksi.getInt("ID_JENIS_BARANG"));
                jenis.setNama_jenis_barang(rsDetail_Transaksi.getString("NAMA_JENIS_BARANG"));
                
                barang B = new barang();
                B.setId_barang(rsDetail_Transaksi.getInt("ID_BARANG"));
                B.setJenis_barang(jenis);
                B.setNama_barang(rsDetail_Transaksi.getString("NAMA_BARANG"));
                B.setStock_barang(rsDetail_Transaksi.getInt("STOCK_BARANG"));
                B.setHarga_barang(rsDetail_Transaksi.getInt("HARGA_BARANG"));
                
                detail_pemesanan detail_transaksi = new detail_pemesanan();
                detail_transaksi.setBarang(B);
                detail_transaksi.setJumlah_barang(rsDetail_Transaksi.getInt("JUMLAH_BARANG"));
                
                dp.add(detail_transaksi);
            }
            t.setarrDetail_pemesanan(dp);
            this.arrTransaksi.add(t);
        }
        
        return this.arrTransaksi;
    }
    
    public void insertTransaksi(transaksi transaksi) {
        try {
            this.koneksi.ManipulasiData("INSERT INTO TRANSAKSI VALUES (ID_TRANSAKSI.NEXTVAL," + transaksi.getJumlah_barang()+ "," + transaksi.getTotal_harga()+ ","+ transaksi.getJumlah_pembayaran() + "," + transaksi.getJumlah_kembalian()+ ")");
            ResultSet rs = this.koneksi.getData("SELECT ID_TRANSAKSI.CURRVAL FROM DUAL");

            rs.next();
            int Id_Transaksi = rs.getInt("CURRVAL");
            for(detail_pemesanan p : transaksi.getarrDetail_pemesanan()) {
                this.koneksi.ManipulasiData("INSERT INTO DETAIL_PEMESANAN VALUES (" + Id_Transaksi + "," + p.getBarang().getId_barang()+ "," + p.getJumlah_barang()+ ")");
                this.koneksi.ManipulasiData("UPDATE BARANG SET STOCK_BARANG = STOCK_BARANG - " + p.getJumlah_barang()+ "WHERE ID_BARANG = " + p.getBarang().getId_barang());
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void insertJensiBarang (jenis_barang jenisbarang){
        try {
            koneksi.ManipulasiData("INSERT INTO JENIS_BARANG VALUES (ID_JENIS_BARANG.NEXTVAL,'"+ jenisbarang.getNama_jenis_barang()+"')");
            ResultSet rs = this.koneksi.getData("SELECT ID_JENIS_BARANG.CURRVAL FROM DUAL");
            System.err.println(rs);
            
            rs.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(controltransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteJenisBarang (jenis_barang jenisbarang){
        koneksi.ManipulasiData("DELETE FROM JENIS_BARANG WHERE ID_JENIS_BARANG = " + jenisbarang.getId_jenis_barang());
    }
    
    public void insertBarang(barang barang){
        try {
            koneksi.ManipulasiData("INSERT INTO BARANG VALUES (ID_BARANG.NEXTVAL,'"+ barang.getNama_barang() + "'," + barang.getStock_barang() + "," + barang.getHarga_barang()+ "," + barang.getJenis_barang().getId_jenis_barang() + ")" );
            ResultSet rs = this.koneksi.getData("SELECT ID_BARANG.CURRVAL FROM DUAL");
            System.out.println(rs);
            rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(controltransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateBarang(barang barang, int Stock, int Harga){
        koneksi.ManipulasiData("UPDATE BARANG SET STOCK_BARANG = " +Stock+ ", HARGA_BARANG = "+Harga+ "WHERE ID_BARANG =" +barang.getId_barang());
        
    }
    
    public void deleteBarang (barang barang){
        koneksi.ManipulasiData("DELETE FROM BARANG WHERE ID_BARANG = " + barang.getId_barang());
    }
    
}