package model;

public class Autogara {
    private int idAutogara;
    private String nume;
    private String adresa; // Câmp nou adăugat
    private String oras;

    public Autogara() {}


    public Autogara(int idAutogara, String nume, String adresa, String oras) {
        this.idAutogara = idAutogara;
        this.nume = nume;
        this.adresa = adresa;
        this.oras = oras;
    }

    // Getter și Setter pentru Adresa
    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getIdAutogara() {
        return idAutogara;
    }

    public void setIdAutogara(int idAutogara) {
        this.idAutogara = idAutogara;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    // Optional: suprascriere toString pentru a vedea datele frumos la debug sau în ComboBox
    @Override
    public String toString() {
        return nume + " (" + oras + ")";
    }
}