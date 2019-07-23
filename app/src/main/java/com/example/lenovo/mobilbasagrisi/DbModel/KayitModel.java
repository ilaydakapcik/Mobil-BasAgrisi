package com.example.lenovo.mobilbasagrisi.DbModel;

public class KayitModel {
    private int id ;
    private String basTarih,sonTarih,siddet,tetikleyici,ilac;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBasTarih() {
        return basTarih;
    }

    public void setBasTarih(String basTarih) {
        this.basTarih = basTarih;
    }

    public String getSonTarih() {
        return sonTarih;
    }

    public void setSonTarih(String sonTarih) {
        this.sonTarih = sonTarih;
    }

    public String getSiddet() {
        return siddet;
    }

    public void setSiddet(String siddet) {
        this.siddet = siddet;
    }

    public String getTetikleyici() {
        return tetikleyici;
    }

    public void setTetikleyici(String tetikleyici) {
        this.tetikleyici = tetikleyici;
    }

    public String getIlac() {
        return ilac;
    }

    public void setIlac(String ilac) {
        this.ilac = ilac;
    }
}
