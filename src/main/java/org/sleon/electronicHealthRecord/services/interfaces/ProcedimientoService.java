package org.sleon.electronicHealthRecord.services.interfaces;

import org.sleon.electronicHealthRecord.models.ProcedimientoMedico;

import java.sql.SQLException;
import java.util.List;

public interface ProcedimientoService {
    void crear(ProcedimientoMedico procedimiento) throws SQLException;
    List<ProcedimientoMedico> listar(Long idAtencionMedica) throws SQLException;
    void actualizar(ProcedimientoMedico procedimiento) throws SQLException;
}
