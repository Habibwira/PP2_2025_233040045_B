/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.view.Developer;

import app.Controller.GenreController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author user
 */
public class GenreView extends JFrame {
    private JTextField txtId, txtNama;
    private JTable tableGenre;
    private DefaultTableModel tableModel;
    private GenreController controller;

    public GenreView() {
        controller = new GenreController();

        setTitle("Dashboard Developer - Manajemen Genre");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));
        add(createFormPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);

        loadTable();
    }

    // ================= PANEL FORM =================
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 4, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Form Genre"));

        txtId = new JTextField();
        txtId.setEditable(false);

        txtNama = new JTextField();

        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");

        panel.add(new JLabel("ID"));
        panel.add(txtId);
        panel.add(new JLabel("Nama Genre"));
        panel.add(txtNama);

        panel.add(btnTambah);
        panel.add(btnUpdate);
        panel.add(btnHapus);
        panel.add(btnReset);

        btnTambah.addActionListener(e -> tambahGenre());
        btnUpdate.addActionListener(e -> updateGenre());
        btnHapus.addActionListener(e -> hapusGenre());
        btnReset.addActionListener(e -> resetForm());

        return panel;
    }

    // ================= PANEL TABEL =================
    private JScrollPane createTablePanel() {
        String[] kolom = {"ID", "Nama Genre"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableGenre = new JTable(tableModel);

        tableGenre.getSelectionModel().addListSelectionListener(e -> {
            int row = tableGenre.getSelectedRow();
            if (row > -1) {
                txtId.setText(tableModel.getValueAt(row, 0).toString());
                txtNama.setText(tableModel.getValueAt(row, 1).toString());
            }
        });

        return new JScrollPane(tableGenre);
    }

    // ================= LOGIKA =================
    private void loadTable() {
        tableModel.setRowCount(0);
        List<Object[]> data = controller.getAllGenres();
        if (data != null) {
            for (Object[] row : data) {
                tableModel.addRow(row);
            }
        }
    }

    private void tambahGenre() {
        String nama = txtNama.getText().trim();

        if (nama.length() < 3) {
            JOptionPane.showMessageDialog(this,
                    "Nama genre minimal 3 karakter",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        controller.addGenre(nama);
        loadTable();
        resetForm();
    }

    private void updateGenre() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diupdate");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        String nama = txtNama.getText().trim();

        controller.updateGenre(id, nama);
        loadTable();
        resetForm();
    }

    private void hapusGenre() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        controller.deleteGenre(id);
        loadTable();
        resetForm();
    }

    private void resetForm() {
        txtId.setText("");
        txtNama.setText("");
        tableGenre.clearSelection();
    }
}
    

