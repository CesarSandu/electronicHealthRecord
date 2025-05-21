package org.sleon.electronicHealthRecord.repositories.interfaces;

import org.sleon.electronicHealthRecord.models.AtencionMedica;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AtencionMedicaRepository {
    void crear(AtencionMedica atencionMedica) throws SQLException;
    List<AtencionMedica> listar() throws SQLException;
    void eliminar(Long id) throws SQLException;
    void establecerAlta(AtencionMedica atencionMedica) throws SQLException;
    List<AtencionMedica> buscarPorNumeroTelPaciente(String numeroTelPaciente) throws SQLException;
    List<AtencionMedica> buscarAtencionMedicaEnCurso() throws SQLException;
    Optional<AtencionMedica> buscarAtencionMedicaActual(String numeroTelPaciente) throws SQLException;
}


