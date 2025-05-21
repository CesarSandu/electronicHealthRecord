package org.sleon.electronicHealthRecord.models;

import java.util.Date;

public class GrupoMedico extends UsuarioHospital{
    private String especialidad;
    private UsuarioHospital responsableRegistro;
    private Date fechaRegistro;

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

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
