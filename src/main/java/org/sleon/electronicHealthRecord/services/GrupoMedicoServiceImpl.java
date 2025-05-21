package org.sleon.electronicHealthRecord.services;

import org.sleon.electronicHealthRecord.models.GrupoMedico;
import org.sleon.electronicHealthRecord.repositories.GrupoMedicoRepositoryImpl;
import org.sleon.electronicHealthRecord.repositories.interfaces.UsuarioRepository;
import org.sleon.electronicHealthRecord.services.interfaces.UsurioService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GrupoMedicoServiceImpl implements UsurioService<GrupoMedico> {

    private UsuarioRepository<GrupoMedico> grupoMedicoRepository;
    private Connection conn;

    public GrupoMedicoServiceImpl(Connection conn) {
        this.conn = conn;
        this.grupoMedicoRepository = new GrupoMedicoRepositoryImpl(conn);
    }

    @Override
    public void crear(GrupoMedico grupoMedico) {
        try {
            grupoMedicoRepository.crear(grupoMedico);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<GrupoMedico> listar()  {
        try {
            return grupoMedicoRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<GrupoMedico> listarPorId(Long id)  {
        try {
            return grupoMedicoRepository.buscarPorId(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<GrupoMedico> buscarPorNombre(String nombre) {
        try {
            return grupoMedicoRepository.buscarPorNombre(nombre);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void actualizar(GrupoMedico grupoMedico){
        try {
            grupoMedicoRepository.actualizar(grupoMedico);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }
}
