package model;

public class Statie {
    private int idStatie;
    private String numeStatie;
    private int timpStationare;

    public Statie(int idStatie, String numeStatie, int timpStationare) {
        this.idStatie = idStatie;
        this.numeStatie = numeStatie;
        this.timpStationare = timpStationare;
    }

    // Getters and setters
    public int getIdStatie() {
        return idStatie;
    }

    public void setIdStatie(int idStatie) {
        this.idStatie = idStatie;
    }

    public String getNumeStatie() {
        return numeStatie;
    }

    public void setNumeStatie(String numeStatie) {
        this.numeStatie = numeStatie;
    }

    public int getTimpStationare() {
        return timpStationare;
    }

    public void setTimpStationare(int timpStationare) {
        this.timpStationare = timpStationare;
    }
}
