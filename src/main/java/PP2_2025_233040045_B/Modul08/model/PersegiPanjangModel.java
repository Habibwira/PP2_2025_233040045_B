package PP2_2025_233040045_B.Modul08.model;

public class PersegiPanjangModel {
    private double panjang;
    private double lebar;
    private double luas;
    private double keliling;

    // menghitung luas (logika bisnis)
    public void hitungLuas (){
        this.luas = this.panjang * this.lebar;
    }
    
    // menghitung keliling
    public void hitungKeliling (){
        this.keliling = 2* (this.panjang * this.lebar);
    }
    
    // getters dan setters
    public void setPanjang(double panjang){
        this.panjang = panjang;
    }
    
    public void setLebar(double lebar) {
        this.lebar = lebar;
    }

    // menghitung luas (logika bisnis)
    public double getLuas (){
        return luas;
    }

    public double getKeliling() {
        return keliling;
    }
}
