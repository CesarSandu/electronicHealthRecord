package org.sleon.electronicHealthRecord.models;

import java.time.LocalDate;

public class PersonalAdmision extends UsuarioHospital{
    private LocalDate fechaRegistro;
    private UsuarioHospital responsableRegistro;

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public UsuarioHospital getResponsableRegistro() {
        return responsableRegistro;
    }

    public void setResponsableRegistro(UsuarioHospital responsableRegistro) {
        this.responsableRegistro = responsableRegistro;
    }
}
