package org.sleon.electronicHealthRecord.services;

import org.sleon.electronicHealthRecord.models.PersonalAdmision;
import org.sleon.electronicHealthRecord.repositories.PersonalAdmisionRepositoryImpl;
import org.sleon.electronicHealthRecord.repositories.interfaces.UsuarioRepository;
import org.sleon.electronicHealthRecord.services.interfaces.UsurioService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PersonalAdmisionServiceImpl implements UsurioService<PersonalAdmision> {

    private Connection conn;
    private UsuarioRepository <PersonalAdmision> usuarioRepository;

    public PersonalAdmisionServiceImpl(Connection conn) {
        this.conn = conn;
        this.usuarioRepository = new PersonalAdmisionRepositoryImpl(conn);
    }

    @Override
    public void crear(PersonalAdmision personalAdmision) {
        try {
            usuarioRepository.crear(personalAdmision);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<PersonalAdmision> listar() {
        try {
            return usuarioRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<PersonalAdmision> listarPorId(Long id) {
        try {
            return usuarioRepository.buscarPorId(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<PersonalAdmision> buscarPorNombre(String nombre) {
        try {
            return usuarioRepository.buscarPorNombre(nombre);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void actualizar(PersonalAdmision personalAdmision) {
        try {
            usuarioRepository.actualizar(personalAdmision);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }
}

