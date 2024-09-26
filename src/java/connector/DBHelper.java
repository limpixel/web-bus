package connector;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

    // Method untuk mendapatkan koneksi ke database
    public Connection getConnection() {
        Connection konek = null;
        try {
            // Muat driver MySQL dan buat koneksi ke database
            Class.forName("com.mysql.cj.jdbc.Driver");
            konek = DriverManager.getConnection("jdbc:mysql://localhost:8889/studyjsf_db", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return konek;
    }
}