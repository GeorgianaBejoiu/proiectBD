package model;

public class RutaFixa {
    private int idRutaFixa;
    private String orasStart;
    private String orasDestinatie;
    private int distantaKm;

    public RutaFixa(int idRutaFixa, String orasStart, String orasDestinatie, int distantaKm) {
        this.idRutaFixa = idRutaFixa;
        this.orasStart = orasStart;
        this.orasDestinatie = orasDestinatie;
        this.distantaKm = distantaKm;
    }

    // Getters and setters
    public int getIdRutaFixa() {
        return idRutaFixa;
    }

    public void setIdRutaFixa(int idRutaFixa) {
        this.idRutaFixa = idRutaFixa;
    }

    public String getOrasStart() {
        return orasStart;
    }

    public void setOrasStart(String orasStart) {
        this.orasStart = orasStart;
    }

    public String getOrasDestinatie() {
        return orasDestinatie;
    }

    public void setOrasDestinatie(String orasDestinatie) {
        this.orasDestinatie = orasDestinatie;
    }

    public int getDistantaKm() {
        return distantaKm;
    }

    public void setDistantaKm(int distantaKm) {
        this.distantaKm = distantaKm;
    }
}
