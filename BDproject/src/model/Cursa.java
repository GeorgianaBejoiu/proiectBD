package model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Cursa {
    private int idCursa;
    private Date dataPlecare;
    private Time oraPlecare;
    private Autogara autogara;
    private int idRutaFixa;
    private Integer idAutobuz; // nullable
    private List<Autobuz> autobuzeFolosite; // lista de autobuze folosite la cursa asta

    public Cursa(int idCursa, Date dataPlecare, Time oraPlecare, Autogara autogara, int idRutaFixa) {
        this.idCursa = idCursa;
        this.dataPlecare = dataPlecare;
        this.oraPlecare = oraPlecare;
        this.autogara = autogara;
        this.idRutaFixa = idRutaFixa;
    }

    // Getters și setters

    public int getIdCursa() {
        return idCursa;
    }

    public void setIdCursa(int idCursa) {
        this.idCursa = idCursa;
    }

    public Date getDataPlecare() {
        return dataPlecare;
    }

    public void setDataPlecare(Date dataPlecare) {
        this.dataPlecare = dataPlecare;
    }

    public Time getOraPlecare() {
        return oraPlecare;
    }

    public void setOraPlecare(Time oraPlecare) {
        this.oraPlecare = oraPlecare;
    }

    public Autogara getAutogara() {
        return autogara;
    }

    public void setAutogara(Autogara autogara) {
        this.autogara = autogara;
    }

    public int getIdRutaFixa() {
        return idRutaFixa;
    }

    public void setIdRutaFixa(int idRutaFixa) {
        this.idRutaFixa = idRutaFixa;
    }

    public Integer getIdAutobuz() {
        return idAutobuz;
    }

    public void setIdAutobuz(Integer idAutobuz) {
        this.idAutobuz = idAutobuz;
    }

    public List<Autobuz> getAutobuzeFolosite() {
        return autobuzeFolosite;
    }

    public void setAutobuzeFolosite(List<Autobuz> autobuzeFolosite) {
        this.autobuzeFolosite = autobuzeFolosite;
    }
}
