package id.ac.unpas.modul07;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManajemenNilaiSiswaApp extends JFrame {

    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    public ManajemenNilaiSiswaApp() {
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        JPanel panelInput = createInputPanel();
        JPanel panelTabel = createTablePanel();

        tabbedPane.addTab("Input Data", panelInput);
        tabbedPane.addTab("Daftar Nilai", panelTabel);

        add(tabbedPane);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Input Nama
        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        // Input Matkul
        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {
                "Matematika Dasar",
                "Bahasa Indonesia",
                "Algoritma & Pemrograman I",
                "Praktikum Pemrograman II"
        };
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // Input Nilai
        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(btnSimpan);

        // Tombol Reset (Instruksi 4)
        JButton btnReset = new JButton("Reset");
        panel.add(btnReset);

        btnSimpan.addActionListener(e -> prosesSimpan());

        btnReset.addActionListener(e -> {
            txtNama.setText("");
            txtNilai.setText("");
            cmbMatkul.setSelectedIndex(0);
        });

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] kolom = {"Nama", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Tombol Hapus (Instruksi 2)
        JButton btnHapus = new JButton("Hapus Baris");
        panel.add(btnHapus, BorderLayout.SOUTH);

        btnHapus.addActionListener(e -> {
            int row = tableData.getSelectedRow();
            if (row > -1) {
                tableModel.removeRow(row);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Pilih baris yang ingin dihapus!",
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        return panel;
    }

    private void prosesSimpan() {
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();

        // Validasi 3 karakter (Instruksi 3)
        if (nama.trim().length() < 3) {
            JOptionPane.showMessageDialog(this,
                    "Nama minimal 3 karakter!",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int nilai;

        try {
            nilai = Integer.parseInt(strNilai);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Nilai harus berupa angka!",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (nilai < 0 || nilai > 100) {
            JOptionPane.showMessageDialog(this,
                    "Nilai harus antara 0 - 100!",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // SWITCH CASE Grade (Instruksi 1)
        String grade;
        switch (nilai / 10) {
            case 10:
            case 9:
            case 8:
                grade = "A";
                break;
            case 7:
                grade = "AB";
                break;
            case 6:
                grade = "B";
                break;
            case 5:
                grade = "BC";
                break;
            case 4:
                grade = "C";
                break;
            case 3:
                grade = "D";
                break;
            default:
                grade = "E";
        }

        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");

        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);

        tabbedPane.setSelectedIndex(1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManajemenNilaiSiswaApp().setVisible(true));
    }
}
