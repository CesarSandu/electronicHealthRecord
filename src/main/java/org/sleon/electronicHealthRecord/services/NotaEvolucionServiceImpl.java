package org.sleon.electronicHealthRecord.services;

import org.sleon.electronicHealthRecord.models.NotaEvolucion;
import org.sleon.electronicHealthRecord.repositories.NotaEvolucionRepositoryImpl;
import org.sleon.electronicHealthRecord.repositories.interfaces.NotaEvolucionRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class NotaEvolucionServiceImpl implements NotaEvolucionRepository {

    private NotaEvolucionRepositoryImpl nteRepository;

    public NotaEvolucionServiceImpl(Connection con) {
        nteRepository = new NotaEvolucionRepositoryImpl(con);
    }

    @Override
    public List<NotaEvolucion> listarPorIdAtencionMedica(Long id) {
        try {
            return nteRepository.listarPorIdAtencionMedica(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<NotaEvolucion> listarPorId(Long id) {
        try {
            return nteRepository.listarPorId(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void crear(NotaEvolucion notaEvolucion) {
        try {
            nteRepository.crear(notaEvolucion);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void actualizar(NotaEvolucion notaEvolucion) {
        try {
            nteRepository.actualizar(notaEvolucion);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public boolean existe(Long id, Date fecha) {
        try {
            return nteRepository.existe(id, fecha);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }
}
