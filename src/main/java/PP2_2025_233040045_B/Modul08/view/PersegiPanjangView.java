package PP2_2025_233040045_B.Modul08.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PersegiPanjangView extends JFrame {

    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasil = new JLabel("-");
    private JButton btnHitungLuas = new JButton("Hitung Luas");
    private JButton btnHitungKeliling = new JButton("Hitung Keliling");
    private JButton btnReset = new JButton("Reset");

    public PersegiPanjangView() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 220);
        this.setLayout(new GridLayout(5, 2, 10, 10));
        this.setTitle("MVC Kalkulator Persegi Panjang");

        this.add(new JLabel("Panjang"));
        this.add(txtPanjang);
        this.add(new JLabel("Lebar"));
        this.add(txtLebar);
        this.add(new JLabel("Hasil"));
        this.add(lblHasil);
        this.add(btnHitungLuas);
        this.add(btnHitungKeliling);
        this.add(btnReset);
    }

    public String getRawPanjang() {
        return txtPanjang.getText();
    }

    public String getRawLebar() {
        return txtLebar.getText();
    }

    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    public void setHasil(String hasil) {
        lblHasil.setText(hasil);
    }

    public void reset() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasil.setText("-");
    }

    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    public void addHitungLuasListener(ActionListener listener) {
        btnHitungLuas.addActionListener(listener);
    }

    public void addHitungKelilingListener(ActionListener listener) {
        btnHitungKeliling.addActionListener(listener);
    }

    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
}
