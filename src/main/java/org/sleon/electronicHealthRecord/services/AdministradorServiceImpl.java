package org.sleon.electronicHealthRecord.services;

import org.sleon.electronicHealthRecord.models.Administrador;
import org.sleon.electronicHealthRecord.repositories.AdministradorRepositoryImpl;
import org.sleon.electronicHealthRecord.repositories.interfaces.UsuarioRepository;
import org.sleon.electronicHealthRecord.services.interfaces.UsurioService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdministradorServiceImpl implements UsurioService<Administrador>{

    private Connection conn;
    private UsuarioRepository<Administrador> administradorRepository;

    public AdministradorServiceImpl(Connection conn) {
        this.conn = conn;
        administradorRepository = new AdministradorRepositoryImpl(conn);
    }

    @Override
    public void crear(Administrador administrador) {
        try {
            administradorRepository.crear(administrador);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Administrador> listar() {
        try {
            return administradorRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Administrador> listarPorId(Long id) {
        try {
            return administradorRepository.buscarPorId(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Administrador> buscarPorNombre(String nombre) {
        try {
            return administradorRepository.buscarPorNombre(nombre);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void actualizar(Administrador administrador) {
        try {
            administradorRepository.actualizar(administrador);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }
}
