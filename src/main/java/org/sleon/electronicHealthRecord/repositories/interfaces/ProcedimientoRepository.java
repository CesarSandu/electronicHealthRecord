package org.sleon.electronicHealthRecord.repositories.interfaces;

import org.sleon.electronicHealthRecord.models.ProcedimientoMedico;

import java.sql.SQLException;
import java.util.List;

public interface ProcedimientoRepository {
    List<ProcedimientoMedico> listarProcedimientosMedicos(Long idAtencionMedica) throws SQLException;
    void crear(ProcedimientoMedico procedimientoMedico) throws SQLException;
    void eliminar(Long id) throws SQLException;
    void actualizar(ProcedimientoMedico procedimientoMedico) throws SQLException;
}
