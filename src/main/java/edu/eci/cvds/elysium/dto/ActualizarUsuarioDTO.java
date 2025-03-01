package edu.eci.cvds.elysium.dto;
public class ActualizarUsuarioDTO {
    private int idInstitucional;
    private char tipoCampo;
    private String value;

    public ActualizarUsuarioDTO(int idInstitucional, char tipoCampo, String value) {
        this.idInstitucional = idInstitucional;
        this.tipoCampo = tipoCampo;
        this.value = value;
    }

    // Getters
    public int getIdInstitucional() { return idInstitucional; }
    public char getTipoCampo() { return tipoCampo; }
    public String getValue() { return value; }
}
