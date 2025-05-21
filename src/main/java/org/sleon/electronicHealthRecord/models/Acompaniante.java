package org.sleon.electronicHealthRecord.models;

public class Acompaniante extends Usuario{
    private String parentesco;
    private String codigoInicioSession;
    private UsuarioHospital responsableRegistro;

    public String getCodigoInicioSession() {
        return codigoInicioSession;
    }

    public void setCodigoInicioSession(String codigoInicioSession) {
        this.codigoInicioSession = codigoInicioSession;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public UsuarioHospital getResponsableRegistro() {
        return responsableRegistro;
    }

    public void setResponsableRegistro(UsuarioHospital responsableRegistro) {
        this.responsableRegistro = responsableRegistro;
    }
}
