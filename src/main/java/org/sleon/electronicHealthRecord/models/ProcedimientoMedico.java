package org.sleon.electronicHealthRecord.models;

import java.sql.Date;
import java.sql.Time;

public class ProcedimientoMedico {
    private Long id;
    private String descripcion;
    private Time horaInicio;
    private Time horaFin;
    private Date fechaInicio;
    private Date fechaFin;
    private GrupoMedico grupoMedico;
    private AtencionMedica atencionMedica;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public GrupoMedico getGrupoMedico() {
        return grupoMedico;
    }

    public void setGrupoMedico(GrupoMedico grupoMedico) {
        this.grupoMedico = grupoMedico;
    }

    public AtencionMedica getAtencionMedica() {
        return atencionMedica;
    }

    public void setAtencionMedica(AtencionMedica atencionMedica) {
        this.atencionMedica = atencionMedica;
    }
}
