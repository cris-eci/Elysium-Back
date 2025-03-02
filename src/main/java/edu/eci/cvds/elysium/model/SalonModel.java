package edu.eci.cvds.elysium.model;

public class SalonModel {
    private String mnemonico;

    public SalonModel() {
    }

    public SalonModel(String mnemonico) {
        this.mnemonico = mnemonico;
    }

    public String getMnemonico() {
        return mnemonico;
    }

    public void setMnemonico(String mnemonico) {
        this.mnemonico = mnemonico;
    }
}
