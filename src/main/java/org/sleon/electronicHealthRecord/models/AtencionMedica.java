package org.sleon.electronicHealthRecord.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AtencionMedica {
    private Long id;
    private Paciente paciente;
    private Acompaniante acompaniante;
    private UsuarioHospital responsableRegistro;
    private List<NotaEvolucion> notasEvolucion;
    private List<ProcedimientoMedico> procedimientosMedicos;
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;
    private LocalTime horaIngreso;
    private LocalTime horaEgreso;
    private String estado;


    public AtencionMedica() {
        this.procedimientosMedicos = new ArrayList<>();
        this.notasEvolucion = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<NotaEvolucion> getNotasEvolucion() {
        return notasEvolucion;
    }

    public void setNotasEvolucion(List<NotaEvolucion> notasEvolucion) {
        this.notasEvolucion = notasEvolucion;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(LocalDate fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public LocalTime getHoraEgreso() {
        return horaEgreso;
    }

    public void setHoraEgreso(LocalTime horaEgreso) {
        this.horaEgreso = horaEgreso;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Acompaniante getAcompaniante() {
        return acompaniante;
    }

    public void setAcompaniante(Acompaniante acompaniante) {
        this.acompaniante = acompaniante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UsuarioHospital getResponsableRegistro() {
        return responsableRegistro;
    }

    public void setResponsableRegistro(UsuarioHospital responsableRegistro) {
        this.responsableRegistro = responsableRegistro;
    }
}
