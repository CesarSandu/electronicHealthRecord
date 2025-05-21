package org.sleon.electronicHealthRecord.services;

import org.sleon.electronicHealthRecord.repositories.PacienteRepositoryImpl;
import org.sleon.electronicHealthRecord.repositories.interfaces.UsuarioRepository;
import org.sleon.electronicHealthRecord.services.interfaces.UsurioService;
import org.sleon.electronicHealthRecord.models.Paciente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PacienteServiceImpl implements UsurioService<Paciente>{

    private UsuarioRepository <Paciente> pacienteRepository;

    public PacienteServiceImpl(Connection conn) {
        this.pacienteRepository = new PacienteRepositoryImpl(conn);
    }

    @Override
    public void crear(Paciente paciente) {
        try {
            pacienteRepository.crear(paciente);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Paciente> listar() {
        try {
            return pacienteRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Paciente> listarPorId(Long id) {
        try {
            return pacienteRepository.buscarPorId(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Paciente> buscarPorNombre(String nombre) {
        try {
            return pacienteRepository.buscarPorNombre(nombre);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void actualizar(Paciente paciente) {
        try {
            pacienteRepository.actualizar(paciente);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }
}
