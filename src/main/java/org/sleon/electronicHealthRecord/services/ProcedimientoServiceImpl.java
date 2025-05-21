package org.sleon.electronicHealthRecord.services;

import org.sleon.electronicHealthRecord.models.ProcedimientoMedico;
import org.sleon.electronicHealthRecord.repositories.ProcedimientoRepositoryImpl;
import org.sleon.electronicHealthRecord.services.interfaces.ProcedimientoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProcedimientoServiceImpl implements ProcedimientoService {

    private ProcedimientoRepositoryImpl procedimientoRepository;

    public ProcedimientoServiceImpl(Connection conn) {
        this.procedimientoRepository = new ProcedimientoRepositoryImpl(conn);
    }

    @Override
    public void crear(ProcedimientoMedico procedimiento){
        try {
            procedimientoRepository.crear(procedimiento);
        }catch (SQLException e){
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<ProcedimientoMedico> listar(Long idAtencionMedica){
        try {
            return procedimientoRepository.listarProcedimientosMedicos(idAtencionMedica);
        }catch (SQLException e){
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void actualizar(ProcedimientoMedico procedimiento){
        try {
            procedimientoRepository.actualizar(procedimiento);
        }catch (SQLException e){
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }
}
