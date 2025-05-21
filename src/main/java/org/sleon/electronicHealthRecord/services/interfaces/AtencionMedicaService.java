package org.sleon.electronicHealthRecord.services.interfaces;

import org.sleon.electronicHealthRecord.models.AtencionMedica;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AtencionMedicaService {
    void crear(AtencionMedica atencionMedica);
    void eliminar(Long id);
    void establecerAlta(AtencionMedica atencionMedica);
    List<AtencionMedica> listar();
    List<AtencionMedica> buscarPorNumeroTelefonoPaciente(String telefonoPaciente);
    Optional<AtencionMedica> buscaratencionMedicaActual(String telefonoPaciente);
}


