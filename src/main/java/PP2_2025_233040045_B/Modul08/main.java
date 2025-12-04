package PP2_2025_233040045_B.Modul08;

import PP2_2025_233040045_B.Modul08.controller.PersegiPanjangController;
import PP2_2025_233040045_B.Modul08.model.PersegiPanjangModel;
import PP2_2025_233040045_B.Modul08.view.PersegiPanjangView;

public class main {
    public static void main(String[] args) {
        // 1. instansiasi Model
        PersegiPanjangModel model = new PersegiPanjangModel();
        
        // 2. Instansiasi view
        PersegiPanjangView view = new PersegiPanjangView();
        
        //instansiasi controller (menghubungkan model = view)
        PersegiPanjangController controller = new PersegiPanjangController(model, view);
        
        // 4. tampilkan view
        view.setVisible(true);
    }
}
