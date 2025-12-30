/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.view.Auth;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author user
 */
public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbRole;

    public LoginView() {
        setTitle("Game Store - Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(createFormPanel());
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        cmbRole = new JComboBox<>(new String[]{"USER", "DEVELOPER"});

        JButton btnLogin = new JButton("Login");
        JButton btnReset = new JButton("Reset");

        panel.add(new JLabel("Username"));
        panel.add(txtUsername);

        panel.add(new JLabel("Password"));
        panel.add(txtPassword);

        panel.add(new JLabel("Role"));
        panel.add(cmbRole);

        panel.add(btnLogin);
        panel.add(btnReset);

        btnLogin.addActionListener(e -> prosesLogin());
        btnReset.addActionListener(e -> resetForm());

        return panel;
    }

    private void prosesLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String role = cmbRole.getSelectedItem().toString();

        // ===== VALIDASI =====
        if (username.length() < 3 || password.length() < 3) {
            JOptionPane.showMessageDialog(this,
                    "Username dan Password minimal 3 karakter",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ===== SIMULASI AUTH =====
        JOptionPane.showMessageDialog(this,
                "Login berhasil sebagai " + role,
                "Informasi",
                JOptionPane.INFORMATION_MESSAGE);

        // Di sini nantinya diarahkan ke Home / Dashboard
        // contoh:
        // if (role.equals("DEVELOPER")) new DashboardDevView().setVisible(true);
        // else new HomeUserView().setVisible(true);
        dispose();
    }

    private void resetForm() {
        txtUsername.setText("");
        txtPassword.setText("");
        cmbRole.setSelectedIndex(0);
    }
}

