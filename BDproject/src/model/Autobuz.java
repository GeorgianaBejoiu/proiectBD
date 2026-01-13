package model;

public class Autobuz {
    private int idAutobuz;
    private String numarInmatriculare;
    private int capacitate;

    public Autobuz() {}

    public Autobuz(int idAutobuz, String numarInmatriculare, int capacitate) {
        this.idAutobuz = idAutobuz;
        this.numarInmatriculare = numarInmatriculare;
        this.capacitate = capacitate;
    }

    public int getIdAutobuz() {
        return idAutobuz;
    }

    public void setIdAutobuz(int idAutobuz) {
        this.idAutobuz = idAutobuz;
    }

    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }

    public void setNumarInmatriculare(String numarInmatriculare) {
        this.numarInmatriculare = numarInmatriculare;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }
}
