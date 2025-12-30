/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.view.Developer;

import app.Controller.GameController;
import app.Controller.GenreController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author user
 */
public class GameView extends JFrame {
    private JTextField txtId, txtNama, txtHarga;
    private JComboBox<String> cmbGenre;
    private JTable tableGame;
    private DefaultTableModel tableModel;

    private GameController gameController;
    private GenreController genreController;

    // untuk mapping genre
    private int[] genreIds;

    public GameView() {
        gameController = new GameController();
        genreController = new GenreController();

        setTitle("Dashboard Developer - Manajemen Game");
        setSize(750, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));
        add(createFormPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);

        loadGenre();
        loadTable();
    }

    // ================= FORM =================
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 4, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Form Game"));

        txtId = new JTextField();
        txtId.setEditable(false);

        txtNama = new JTextField();
        txtHarga = new JTextField();

        cmbGenre = new JComboBox<>();

        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");

        panel.add(new JLabel("ID"));
        panel.add(txtId);
        panel.add(new JLabel("Nama Game"));
        panel.add(txtNama);

        panel.add(new JLabel("Genre"));
        panel.add(cmbGenre);
        panel.add(new JLabel("Harga"));
        panel.add(txtHarga);

        panel.add(btnTambah);
        panel.add(btnUpdate);
        panel.add(btnHapus);
        panel.add(btnReset);

        btnTambah.addActionListener(e -> tambahGame());
        btnUpdate.addActionListener(e -> updateGame());
        btnHapus.addActionListener(e -> hapusGame());
        btnReset.addActionListener(e -> resetForm());

        return panel;
    }

    // ================= TABEL =================
    private JScrollPane createTablePanel() {
        String[] kolom = {"ID", "Nama Game", "Genre", "Harga"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableGame = new JTable(tableModel);

        tableGame.getSelectionModel().addListSelectionListener(e -> {
            int row = tableGame.getSelectedRow();
            if (row > -1) {
                txtId.setText(tableModel.getValueAt(row, 0).toString());
                txtNama.setText(tableModel.getValueAt(row, 1).toString());
                txtHarga.setText(tableModel.getValueAt(row, 3).toString());
                cmbGenre.setSelectedItem(tableModel.getValueAt(row, 2).toString());
            }
        });

        return new JScrollPane(tableGame);
    }

    // ================= LOGIKA =================
    private void loadTable() {
        tableModel.setRowCount(0);
        List<Object[]> data = gameController.getAllGames();
        if (data != null) {
            for (Object[] row : data) {
                tableModel.addRow(row);
            }
        }
    }

    private void loadGenre() {
        List<Object[]> data = genreController.getAllGenres();
        if (data != null) {
            genreIds = new int[data.size()];
            cmbGenre.removeAllItems();

            for (int i = 0; i < data.size(); i++) {
                genreIds[i] = (int) data.get(i)[0];
                cmbGenre.addItem(data.get(i)[1].toString());
            }
        }
    }

    private void tambahGame() {
        String nama = txtNama.getText().trim();
        String hargaStr = txtHarga.getText().trim();

        if (nama.length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama game minimal 3 karakter");
            return;
        }

        int harga;
        try {
            harga = Integer.parseInt(hargaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga harus angka");
            return;
        }

        int genreIndex = cmbGenre.getSelectedIndex();
        int genreId = genreIds[genreIndex];
        int developerId = 1; // asumsi developer login

        gameController.addGame(nama, genreId, developerId, harga);
        loadTable();
        resetForm();
    }

    private void updateGame() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih game yang ingin diupdate");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        String nama = txtNama.getText().trim();
        int harga = Integer.parseInt(txtHarga.getText());

        gameController.updateGame(id, nama, harga);
        loadTable();
        resetForm();
    }

    private void hapusGame() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih game yang ingin dihapus");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        gameController.deleteGame(id);
        loadTable();
        resetForm();
    }

    private void resetForm() {
        txtId.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        tableGame.clearSelection();
    }
}

    
    

