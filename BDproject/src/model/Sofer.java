package model;

public class Sofer {
    private int idSofer;
    private String nume;
    private String prenume;
    private String nrPermis;
    private int experienta;

    public Sofer(int idSofer, String nume, String prenume, String nrPermis, int experienta) {
        this.idSofer = idSofer;
        this.nume = nume;
        this.prenume = prenume;
        this.nrPermis = nrPermis;
        this.experienta = experienta;
    }

    // Getters and setters
    public int getIdSofer() {
        return idSofer;
    }

    public void setIdSofer(int idSofer) {
        this.idSofer = idSofer;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNrPermis() {
        return nrPermis;
    }

    public void setNrPermis(String nrPermis) {
        this.nrPermis = nrPermis;
    }

    public int getExperienta() {
        return experienta;
    }

    public void setExperienta(int experienta) {
        this.experienta = experienta;
    }
}
