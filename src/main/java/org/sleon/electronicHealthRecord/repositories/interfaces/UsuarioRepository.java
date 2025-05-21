package org.sleon.electronicHealthRecord.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository <T>{
    void crear(T t) throws SQLException;
    List<T> listar() throws SQLException;
    List<T> buscarPorNombre(String nombre) throws SQLException;
    Optional<T> buscarPorId(Long id) throws SQLException;
    void actualizar(T t) throws SQLException;
}
