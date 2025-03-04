package edu.eci.cvds.elysium.dto;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

import edu.eci.cvds.elysium.model.DiaSemanaModel;

public class ActualizarReservaDTO {
    private String idReserva;
    private char tipoCampo;
    private LocalDate value1;
    private DiaSemanaModel value2;
    private String value3;
    private boolean value4;

    public ActualizarReservaDTO(String idReserva, char tipoCampo, LocalDate value1, DiaSemanaModel value2, String value3, boolean value4) {
        this.idReserva = idReserva;
        this.tipoCampo = tipoCampo;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }

    public String getIdReserva(){return idReserva;} 
    public char getTipoCampo(){return tipoCampo;}
    public LocalDate getValue1(){return value1;}
    public DiaSemanaModel getValue2(){return value2;}
    public String getValue3(){return value3;}
    public boolean getValue4(){return value4;}
}