package org.sleon.electronicHealthRecord.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NotaEvolucion {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private Paciente paciente;
    private String diagnosticosActuales;
    private int diasEstacionHospitalaria;
    private String signos_sintomas;
    private String datosExploracionFisica;
    private String comentariosUltimosExamenes;
    private String analisisEvolucionClinica;
    private String planEstudioTratamiento;
    private AtencionMedica atencionMedica;

    public void setDiagnosticosActuales(String diagnosticosActuales) {
        this.diagnosticosActuales = diagnosticosActuales;
    }

    public void setDiasEstacionHospitalaria(int diasEstacionHospitalaria) {
        this.diasEstacionHospitalaria = diasEstacionHospitalaria;
    }

    public void setSignos_sintomas(String signos_sintomas) {
        this.signos_sintomas = signos_sintomas;
    }

    public void setDatosExploracionFisica(String datosExploracionFisica) {
        this.datosExploracionFisica = datosExploracionFisica;
    }

    public void setComentariosUltimosExamenes(String comentariosUltimosExamenes) {
        this.comentariosUltimosExamenes = comentariosUltimosExamenes;
    }

    public void setAnalisisEvolucionClinica(String analisisEvolucionClinica) {
        this.analisisEvolucionClinica = analisisEvolucionClinica;
    }

    public void setPlanEstudioTratamiento(String planEstudioTratamiento) {
        this.planEstudioTratamiento = planEstudioTratamiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getDiagnosticosActuales() {
        return diagnosticosActuales;
    }

    public int getDiasEstacionHospitalaria() {
        return diasEstacionHospitalaria;
    }

    public String getSignos_sintomas() {
        return signos_sintomas;
    }

    public String getDatosExploracionFisica() {
        return datosExploracionFisica;
    }

    public String getComentariosUltimosExamenes() {
        return comentariosUltimosExamenes;
    }

    public String getAnalisisEvolucionClinica() {
        return analisisEvolucionClinica;
    }

    public String getPlanEstudioTratamiento() {
        return planEstudioTratamiento;
    }

    public AtencionMedica getAtencionMedica() {
        return atencionMedica;
    }

    public void setAtencionMedica(AtencionMedica atencionMedica) {
        this.atencionMedica = atencionMedica;
    }
}
