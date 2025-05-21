package org.sleon.electronicHealthRecord.services.interfaces;

import org.sleon.electronicHealthRecord.models.GrupoMedico;
import java.util.List;
import java.util.Optional;

public interface UsurioService<T>{
    void crear(T t);
    List<T> listar();
    Optional<T> listarPorId(Long id);
    List<T> buscarPorNombre(String nombre);
    void actualizar(T t);
}
