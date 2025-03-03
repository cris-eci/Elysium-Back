package edu.eci.cvds.elysium.dto.salon;

public class ActualizarSalonDTO {
    private String mnemonico;
    private String valor;
    private int capacidad;

   public ActualizarSalonDTO(String mnemonico, String valor) {
        this.mnemonico = mnemonico;
        this.valor = valor;        
    }   

    public ActualizarSalonDTO(String mnemonico, int capacidad) {
        this.mnemonico = mnemonico;
        this.capacidad = capacidad;        
    }

    public String getMnemonico() {
        return mnemonico;
    }

    public int getCapacidad() {
        return capacidad;
    }
    

    public String getValor() {
        return valor;
    }


}
