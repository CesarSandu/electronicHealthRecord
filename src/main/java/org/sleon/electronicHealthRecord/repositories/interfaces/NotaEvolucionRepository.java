package org.sleon.electronicHealthRecord.repositories.interfaces;

import org.sleon.electronicHealthRecord.models.NotaEvolucion;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface NotaEvolucionRepository {
    List<NotaEvolucion> listarPorIdAtencionMedica(Long id) throws SQLException;
    Optional<NotaEvolucion> listarPorId(Long id) throws SQLException;
    void crear(NotaEvolucion notaEvolucion) throws SQLException;
    void actualizar(NotaEvolucion notaEvolucion) throws SQLException;
    boolean existe(Long id, Date fecha) throws SQLException;
}
