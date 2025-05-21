package org.sleon.electronicHealthRecord.models;

import java.sql.Date;

public class Administrador extends UsuarioHospital {
    private UsuarioHospital responsableRegistro;
    private Date fechaRegistro;

    public UsuarioHospital getResponsableRegistro() {
        return responsableRegistro;
    }

    public void setResponsableRegistro(UsuarioHospital responsableRegistro) {
        this.responsableRegistro = responsableRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
