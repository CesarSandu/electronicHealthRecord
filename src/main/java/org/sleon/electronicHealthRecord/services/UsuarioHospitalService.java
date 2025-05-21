package org.sleon.electronicHealthRecord.services;

import org.sleon.electronicHealthRecord.models.Acompaniante;
import org.sleon.electronicHealthRecord.models.UsuarioHospital;
import org.sleon.electronicHealthRecord.repositories.UsuarioHospitalRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuarioHospitalService {

    private final UsuarioHospitalRepository usuarioRepository;

    public UsuarioHospitalService(Connection connection) {
        this.usuarioRepository = new UsuarioHospitalRepository(connection);
    }

    public void eliminarUsuario(Long id) {
        try {
            usuarioRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    public Optional<UsuarioHospital> buscarUsuarioPorId(Long id) {
        try {
            return usuarioRepository.findById(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    public Optional<UsuarioHospital> login(String idUsuario, String contrasenia, String tipo) {
        try {
            return usuarioRepository.findByCredentials(idUsuario, contrasenia, tipo);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }


    public Optional<Acompaniante> loginAcompaniante(String codigoInicioSesion) {
        try {
            return usuarioRepository.existAcompaniante(codigoInicioSesion);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    public List<UsuarioHospital> listarTodos() {
        try {
            return usuarioRepository.listAll();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    public boolean verificarTelefono(String telefono, Long idUsuario) {
        try {
            return usuarioRepository.existTel(telefono, idUsuario);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    public boolean verificarIdusuario(String idUsuario, Long id) {
        try{
            return usuarioRepository.existIdUsuario(idUsuario, id);
        }catch (Exception e){
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    public Optional<UsuarioHospital> registrado(String nombre, String apellidoM, String apellidoP, String telefono){
        try{
            return usuarioRepository.getIfRegistred(nombre,apellidoP,apellidoM,telefono);
        }catch (Exception e){
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }
}

