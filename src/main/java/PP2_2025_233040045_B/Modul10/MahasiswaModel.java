/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PP2_2025_233040045_B.Modul10;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class MahasiswaModel {

    // ===== LOAD DATA (READ) =====
    public void loadData(DefaultTableModel model) {
        model.setRowCount(0);
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM mahasiswa");

            int no = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    no++,
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal load data: " + e.getMessage());
        }
    }

    // ===== CEK NIM DUPLIKAT (FIX ERROR DI SINI) =====
    public boolean isNIMExists(String nim) {
        try {
            String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // TRUE jika sudah ada
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal cek NIM: " + e.getMessage());
        }
        return false; // ⬅️ PENTING: JANGAN TRUE
    }

    // ===== SIMPAN DATA (CREATE) =====
    public boolean tambahData(String nama, String nim, String jurusan) {
        try {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, nim);
            pst.setString(3, jurusan);

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal simpan data: " + e.getMessage());
            return false;
        }
    }

    // ===== UPDATE DATA =====
    public boolean ubahData(String nama, String jurusan, String nim) {
        try {
            String sql = "UPDATE mahasiswa SET nama=?, jurusan=? WHERE nim=?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, jurusan);
            pst.setString(3, nim);

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal update data: " + e.getMessage());
            return false;
        }
    }

    // ===== DELETE DATA =====
    public boolean hapusData(String nim) {
        try {
            String sql = "DELETE FROM mahasiswa WHERE nim=?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nim);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal hapus data: " + e.getMessage());
            return false;
        }
    }

    // ===== SEARCH DATA =====
    public void cariData(DefaultTableModel model, String keyword) {
        model.setRowCount(0);
        try {
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");

            ResultSet rs = pst.executeQuery();
            int no = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    no++,
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal cari data: " + e.getMessage());
        }
    }
}
