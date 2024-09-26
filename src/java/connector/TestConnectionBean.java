// File: TestConnectionBean.java
// Package: connector

package connector;

import java.sql.Connection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class TestConnectionBean implements Serializable {
    
    private String message;

    // Method untuk menguji koneksi database
    public void testConnection() {
        DBHelper dbHelper = new DBHelper();  // Membuat instance dari DBHelper
        Connection connection = null;
        
        try {
            connection = dbHelper.getConnection();  // Mendapatkan koneksi dari DBHelper
            
            if (connection != null) {
                message = "Connection to the database was successful!";
            } else {
                message = "Failed to make connection!";
            }
        } catch (Exception e) {
            message = "Error: " + e.getMessage();
        } finally {
            try {
                if (connection != null) {
                    connection.close();  // Tutup koneksi setelah pengujian
                }
            } catch (Exception e) {
                message = "Error closing connection: " + e.getMessage();
            }
        }
    }

    // Getter untuk message yang akan ditampilkan di halaman JSF
    public String getMessage() {
        return message;
    }
}
