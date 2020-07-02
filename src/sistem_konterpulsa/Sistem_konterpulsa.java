/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem_konterpulsa;

import Database.Koneksi;
import View.loginview;
import View.ownerview;
import View.transaksiview;
import View.updatebarang;
import View.updatejenis_barang;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus A456U
 */
public class Sistem_konterpulsa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException  {
       new loginview().show();
    }
    
}
