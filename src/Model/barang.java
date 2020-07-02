/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Asus A456U
 */
public class barang {
    private Integer Id_barang;
    private jenis_barang jenis_barang;
    private String Nama_barang;
    private Integer Stock_barang;
    private Integer Harga_barang;

    public jenis_barang getJenis_barang() {
        return jenis_barang;
    }

    public void setJenis_barang(jenis_barang jenis_barang) {
        this.jenis_barang = jenis_barang;
    }

    public Integer getId_barang() {
        return Id_barang;
    }

    public void setId_barang(Integer Id_barang) {
        this.Id_barang = Id_barang;
    }

    public String getNama_barang() {
        return Nama_barang;
    }

    public void setNama_barang(String Nama_barang) {
        this.Nama_barang = Nama_barang;
    }

    public Integer getStock_barang() {
        return Stock_barang;
    }

    public void setStock_barang(Integer Stock_barang) {
        this.Stock_barang = Stock_barang;
    }

    public Integer getHarga_barang() {
        return Harga_barang;
    }

    public void setHarga_barang(Integer Harga_barang) {
        this.Harga_barang = Harga_barang;
    }
    
    
}
