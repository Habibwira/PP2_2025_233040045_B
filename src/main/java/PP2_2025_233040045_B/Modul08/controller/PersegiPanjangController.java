package PP2_2025_233040045_B.Modul08.controller;

import PP2_2025_233040045_B.Modul08.model.PersegiPanjangModel;
import PP2_2025_233040045_B.Modul08.view.PersegiPanjangView;

import java.awt.event.*;

public class PersegiPanjangController {

    // Model dan view sebagai atribut
    private PersegiPanjangModel model;
    private PersegiPanjangView view;

    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;

        // menghubungkan tombol di view dengan logika controller
        this.view.addHitungLuasListener(new HitungLuasListener());
        this.view.addHitungKelilingListener(new HitungKelilingListener());
        this.view.addResetListener(new ResetListener());

    }

    // inner class untuk menangani event klik tombol
    class HitungLuasListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.setPanjang(view.getPanjang());
                model.setLebar(view.getLebar());
                model.hitungLuas();
                view.setHasil("Luas: " + model.getLuas());
            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Input harus berupa angka!");
            }
        }
    }

    class HitungKelilingListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.setPanjang(view.getPanjang());
                model.setLebar(view.getLebar());
                model.hitungKeliling();
                view.setHasil("Keliling: " + model.getKeliling());
            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Input harus berupa angka!");
            }
        }
    }

    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.reset();
        }
    }
}
