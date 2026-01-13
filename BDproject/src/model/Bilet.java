package model;

import java.math.BigDecimal;

public class Bilet {
    private int idBilet;
    private BigDecimal pret;
    private int loc;
    private Pasager pasager;
    private Cursa cursa;

    public Bilet(int idBilet, BigDecimal pret, int loc, Pasager pasager, Cursa cursa) {
        this.idBilet = idBilet;
        this.pret = pret;
        this.loc = loc;
        this.pasager = pasager;
        this.cursa = cursa;
    }

    // Getters and setters
    public int getIdBilet() {
        return idBilet;
    }

    public void setIdBilet(int idBilet) {
        this.idBilet = idBilet;
    }

    public BigDecimal getPret() {
        return pret;
    }

    public void setPret(BigDecimal pret) {
        this.pret = pret;
    }

    public int getLoc() {
        return loc;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    public Pasager getPasager() {
        return pasager;
    }

    public void setPasager(Pasager pasager) {
        this.pasager = pasager;
    }

    public Cursa getCursa() {
        return cursa;
    }

    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }
}
