/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.view.Developer;

import app.Controller.UserController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author user
 */
public class UserView extends JFrame {
    private JTextField txtId, txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbRole;
    private JTable tableUser;
    private DefaultTableModel tableModel;
    private UserController controller;

    public UserView() {
        controller = new UserController();

        setTitle("Dashboard Developer - Manajemen User");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));
        add(createFormPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);

        loadTable();
    }

    // ================= PANEL FORM =================
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Form User"));

        txtId = new JTextField();
        txtId.setEditable(false);

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        cmbRole = new JComboBox<>(new String[]{"USER", "DEVELOPER"});

        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");

        panel.add(new JLabel("ID"));
        panel.add(txtId);
        panel.add(new JLabel("Username"));
        panel.add(txtUsername);

        panel.add(new JLabel("Password"));
        panel.add(txtPassword);
        panel.add(new JLabel("Role"));
        panel.add(cmbRole);

        panel.add(btnTambah);
        panel.add(btnUpdate);
        panel.add(btnHapus);
        panel.add(btnReset);

        btnTambah.addActionListener(e -> tambahUser());
        btnUpdate.addActionListener(e -> updateUser());
        btnHapus.addActionListener(e -> hapusUser());
        btnReset.addActionListener(e -> resetForm());

        return panel;
    }

    // ================= PANEL TABEL =================
    private JScrollPane createTablePanel() {
        String[] kolom = {"ID", "Username", "Role"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableUser = new JTable(tableModel);

        tableUser.getSelectionModel().addListSelectionListener(e -> {
            int row = tableUser.getSelectedRow();
            if (row > -1) {
                txtId.setText(tableModel.getValueAt(row, 0).toString());
                txtUsername.setText(tableModel.getValueAt(row, 1).toString());
                cmbRole.setSelectedItem(tableModel.getValueAt(row, 2).toString());
                txtPassword.setText(""); // password tidak ditampilkan
            }
        });

        return new JScrollPane(tableUser);
    }

    // ================= LOGIKA =================
    private void loadTable() {
        tableModel.setRowCount(0);
        List<Object[]> data = controller.getAllUsers();
        if (data != null) {
            for (Object[] row : data) {
                tableModel.addRow(row);
            }
        }
    }

    private void tambahUser() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String role = cmbRole.getSelectedItem().toString();

        if (username.length() < 3 || password.length() < 3) {
            JOptionPane.showMessageDialog(this,
                    "Username dan Password minimal 3 karakter",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        controller.addUser(username, password, role);
        loadTable();
        resetForm();
    }

    private void updateUser() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih user yang ingin diupdate");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        String username = txtUsername.getText().trim();
        String role = cmbRole.getSelectedItem().toString();

        if (username.length() < 3) {
            JOptionPane.showMessageDialog(this, "Username minimal 3 karakter");
            return;
        }

        controller.updateUser(id, username, role);
        loadTable();
        resetForm();
    }

    private void hapusUser() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih user yang ingin dihapus");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        controller.deleteUser(id);
        loadTable();
        resetForm();
    }

    private void resetForm() {
        txtId.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        cmbRole.setSelectedIndex(0);
        tableUser.clearSelection();
    }
}
