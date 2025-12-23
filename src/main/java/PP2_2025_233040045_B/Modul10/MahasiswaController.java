package PP2_2025_233040045_B.Modul10;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MahasiswaController {

    private MahasiswaModel model;
    private MahasiswaView view;

    public MahasiswaController(MahasiswaView view) {
        this.view = view;
        this.model = new MahasiswaModel();
    }

    public void loadData() {
        model.loadData(view.getModel());
    }

    // ===== SESUAI DENGAN YANG DIPANGGIL VIEW =====
    public void simpan() {
        String nama = view.getTxtNama().getText();
        String nim = view.getTxtNIM().getText();
        String jurusan = view.getTxtJurusan().getText();

        if (nama.isEmpty() || nim.isEmpty() || jurusan.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Data tidak boleh kosong");
            return;
        }

        if (model.isNIMExists(nim)) {
            JOptionPane.showMessageDialog(view, "NIM sudah ada");
            return;
        }

        if (model.tambahData(nama, nim, jurusan)) {
            JOptionPane.showMessageDialog(view, "Data berhasil disimpan");
            loadData();
            clearForm();
        }
    }

    public void ubah() {
        if (model.ubahData(
                view.getTxtNama().getText(),
                view.getTxtJurusan().getText(),
                view.getTxtNIM().getText())) {

            JOptionPane.showMessageDialog(view, "Data berhasil diubah");
            loadData();
            clearForm();
        }
    }

    public void hapus() {
        if (model.hapusData(view.getTxtNIM().getText())) {
            JOptionPane.showMessageDialog(view, "Data berhasil dihapus");
            loadData();
            clearForm();
        }
    }
    
    // dipanggil saat tabel diklik
public void isiForm(int row) {
    view.getTxtNama().setText(
        view.getModel().getValueAt(row, 1).toString()
    );
    view.getTxtNIM().setText(
        view.getModel().getValueAt(row, 2).toString()
    );
    view.getTxtJurusan().setText(
        view.getModel().getValueAt(row, 3).toString()
    );
}

// dipanggil saat form pertama kali dibuka
public void loadTable() {
    model.loadData(view.getModel());
}


    public void cari() {
        model.cariData(view.getModel(), view.getTxtCari().getText());
    }

    // ===== INI YANG TADI HILANG =====
    private void clearForm() {
        view.getTxtNama().setText("");
        view.getTxtNIM().setText("");
        view.getTxtJurusan().setText("");
    }
}
