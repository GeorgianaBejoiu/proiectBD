package model;

public class Pasager {
    private int idPasager;
    private String nume;
    private String prenume;

    public Pasager(int idPasager, String nume, String prenume) {
        this.idPasager = idPasager;
        this.nume = nume;
        this.prenume = prenume;
    }

    // Getters and setters
    public int getIdPasager() {
        return idPasager;
    }

    public void setIdPasager(int idPasager) {
        this.idPasager = idPasager;
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
}
