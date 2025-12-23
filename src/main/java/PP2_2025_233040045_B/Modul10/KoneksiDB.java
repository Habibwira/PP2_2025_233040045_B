package PP2_2025_233040045_B.Modul10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDB {

    private static Connection koneksi;

    public static Connection configDB() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/db_mahasiswa?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String pass = "asalole"; // ⬅️ WAJIB DIISI

            koneksi = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new SQLException("Koneksi gagal: " + e.getMessage());
        }
        return koneksi;
    }
}
