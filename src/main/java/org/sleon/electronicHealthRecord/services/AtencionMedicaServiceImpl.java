package org.sleon.electronicHealthRecord.services;

import org.sleon.electronicHealthRecord.models.*;
import org.sleon.electronicHealthRecord.repositories.*;
import org.sleon.electronicHealthRecord.repositories.interfaces.AtencionMedicaRepository;
import org.sleon.electronicHealthRecord.repositories.interfaces.NotaEvolucionRepository;
import org.sleon.electronicHealthRecord.repositories.interfaces.ProcedimientoRepository;
import org.sleon.electronicHealthRecord.services.interfaces.AtencionMedicaService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AtencionMedicaServiceImpl implements AtencionMedicaService {

    private Connection conn;
    private PacienteRepositoryImpl pacienteRepository;
    private AcompanianteRepositoryImpl acompanianteRepository;
    private AtencionMedicaRepository  atencionMedicaRepository;
    private NotaEvolucionRepository notaEvolucionRepository;
    private ProcedimientoRepository procedimientoRepository;

    public AtencionMedicaServiceImpl(Connection conn) {
        this.conn = conn;
        this.atencionMedicaRepository = new AtencionMedicaRepositoryImpl(conn);
        this.pacienteRepository = new PacienteRepositoryImpl(conn);
        this.acompanianteRepository = new AcompanianteRepositoryImpl(conn);
        this.notaEvolucionRepository = new NotaEvolucionRepositoryImpl(conn);
        this.procedimientoRepository = new ProcedimientoRepositoryImpl(conn);
    }

    @Override
    public void crear(AtencionMedica atencionMedica) {
        try {
            atencionMedicaRepository.crear(atencionMedica);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            atencionMedicaRepository.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void establecerAlta(AtencionMedica atencionMedica) {
        try {
            atencionMedicaRepository.establecerAlta(atencionMedica);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<AtencionMedica> listar() {
        try {
            return atencionMedicaRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<AtencionMedica> buscarPorNumeroTelefonoPaciente(String telefonoPaciente) {
        try {
            return atencionMedicaRepository.buscarPorNumeroTelPaciente(telefonoPaciente);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<AtencionMedica> buscaratencionMedicaActual(String telefonoPaciente) {
        try {
            return atencionMedicaRepository.buscarAtencionMedicaActual(telefonoPaciente);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    public List<AtencionMedica> listarAtencionesMedicasActuales(){
        try {
            return atencionMedicaRepository.buscarAtencionMedicaEnCurso();
        }catch (SQLException e){
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    public List<AtencionMedica> listarPoridAcompañante(Long idAcomp){
        return listar()
                .stream()
                .filter(atm -> atm.getAcompaniante().getId().equals(idAcomp))
                .toList();
    }
}
