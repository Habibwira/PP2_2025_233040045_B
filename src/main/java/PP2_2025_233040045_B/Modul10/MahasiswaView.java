package PP2_2025_233040045_B.Modul10;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MahasiswaView extends JFrame {

    private JTextField txtNama, txtNIM, txtJurusan, txtCari;
    private JButton btnSimpan, btnEdit, btnHapus, btnCari;
    private JTable table;
    private DefaultTableModel model;

    private MahasiswaController controller;

    public MahasiswaView() {
        setTitle("CRUD Mahasiswa MVC");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new MahasiswaController(this);

        // ===== FORM =====
        JPanel panelForm = new JPanel(new GridLayout(4, 2));
        panelForm.add(new JLabel("Nama"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");

        panelForm.add(btnSimpan);
        panelForm.add(btnEdit);

        add(panelForm, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel(new String[]{"No", "Nama", "NIM", "Jurusan"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== SEARCH =====
        JPanel panelCari = new JPanel();
        txtCari = new JTextField(20);
        btnCari = new JButton("Cari");
        panelCari.add(txtCari);
        panelCari.add(btnCari);
        panelCari.add(btnHapus);

        add(panelCari, BorderLayout.SOUTH);

        // ===== EVENT =====
        btnSimpan.addActionListener(e -> controller.simpan());
        btnEdit.addActionListener(e -> controller.ubah());
        btnHapus.addActionListener(e -> controller.hapus());
        btnCari.addActionListener(e -> controller.cari());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                controller.isiForm(table.getSelectedRow());
            }
        });

        controller.loadTable();
    }

    // ===== GETTER =====
    public JTextField getTxtNama() {
        return txtNama;
    }

    public JTextField getTxtNIM() {
        return txtNIM;
    }

    public JTextField getTxtJurusan() {
        return txtJurusan;
    }

    public JTextField getTxtCari() {
        return txtCari;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void kosongkanForm() {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText("");
        txtCari.setText("");
    }

    public static void main(String[] args) {
        new MahasiswaView().setVisible(true);
    }
}
