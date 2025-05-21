package org.sleon.electronicHealthRecord.repositories;

import org.sleon.electronicHealthRecord.models.*;
import org.sleon.electronicHealthRecord.repositories.interfaces.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioHospitalRepository implements Repository<UsuarioHospital> {

    private Connection connection;

    public UsuarioHospitalRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void delete(Long id) throws SQLException{
        try(PreparedStatement stmt = connection.prepareStatement("DELETE FROM USUARIO WHERE id = ?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<UsuarioHospital> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UsuarioHospital> findByCredentials(String username, String password, String tipo) throws SQLException {
        String sql = """
    SELECT usuario.id, usuario.nombre, usuario.apellidoM,
           usuario.apellidoP, usuario.genero, idUsuario,
           personal_hospital.contraseña, personal_hospital.tipo
    FROM usuario
    JOIN personal_hospital ON usuario.id = personal_hospital.id
    WHERE personal_hospital.idUsuario = (?) 
    AND personal_hospital.contraseña = (?)
    AND personal_hospital.tipo = (?);
    """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UsuarioHospital usuarioHospital = new UsuarioHospital();
                    usuarioHospital.setId(rs.getLong("id"));
                    usuarioHospital.setIdUsuario(rs.getString("idUsuario"));
                    usuarioHospital.setContrasenia(rs.getString("contraseña"));
                    usuarioHospital.setTipo(rs.getString("tipo"));
                    return Optional.of(usuarioHospital);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<UsuarioHospital> listAll() throws SQLException {
        String sql = "SELECT usuario.id,idUsuario, contraseña, telefono, nombre, apellidoP, apellidoM, genero, personal_hospital.tipo\n" +
                " FROM personal_hospital, usuario WHERE personal_hospital.id = usuario.id;";
        List<UsuarioHospital> usuariosHospital = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuariosHospital.add(mapAdministrador(rs));
            }
        }
        return usuariosHospital;
    }

    private UsuarioHospital mapAdministrador(ResultSet rs) throws SQLException {
        UsuarioHospital usuarioHospital = new UsuarioHospital();
        usuarioHospital.setId(rs.getLong("id"));
        usuarioHospital.setIdUsuario(rs.getString("idUsuario"));
        usuarioHospital.setContrasenia(rs.getString("contraseña"));
        usuarioHospital.setTelefono(rs.getString("telefono"));
        usuarioHospital.setNombre(rs.getString("nombre"));
        usuarioHospital.setApellidoPaterno(rs.getString("apellidoP"));
        usuarioHospital.setApellidoMaterno(rs.getString("apellidoM"));
        usuarioHospital.setGenero(rs.getString("genero"));
        usuarioHospital.setTipo(rs.getString("tipo"));
        return usuarioHospital;
    }

    public boolean existTel(String telefono, Long idUsuario) throws SQLException {
        String sql = "SELECT 1 FROM usuario WHERE telefono = ? AND id != ? LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, telefono);
            stmt.setLong(2, idUsuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // retorna true si hay otro usuario con ese número
        }
    }

    public boolean existIdUsuario(String idUsuario, Long id) throws SQLException {
        String sql = "SELECT 1 FROM personal_hospital WHERE idUsuario = ? AND id != ? LIMIT 1";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idUsuario);
            stmt.setLong(2, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // retorna true si hay otro usuario con ese idUsuario
        }
    }

    private static void setParam(UsuarioHospital usuario, CallableStatement stmt) throws SQLException {
        stmt.setString(1, usuario.getTelefono());
        stmt.setString(2, usuario.getNombre());
        stmt.setString(3, usuario.getApellidoMaterno());
        stmt.setString(4, usuario.getApellidoPaterno());
        stmt.setString(5, usuario.getGenero());
        stmt.setString(6, usuario.getIdUsuario());
        stmt.setString(7, usuario.getContrasenia());
    }

    public Optional<Acompaniante> existAcompaniante(String codigoInicioSesion) throws SQLException {
        String sql = """
        SELECT 
            a.id AS acompanante_id, 
            CONCAT(u.nombre, ' ', u.apellidoP, ' ', u.apellidoM) AS nombre_completo,
            u.tipo
        FROM 
            ACOMPAÑANTE a 
        JOIN 
            USUARIO u ON a.id = u.id 
        WHERE 
            a.codigoInicioSesion = ?
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codigoInicioSesion);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong("acompanante_id");
                    String nombreCompleto = resultSet.getString("nombre_completo");
                    String tipo = resultSet.getString("tipo");

                    Acompaniante acompaniante = new Acompaniante();
                    acompaniante.setId(id);
                    acompaniante.setTipo(tipo);
                    acompaniante.setNombre(nombreCompleto);
                    return Optional.of(acompaniante);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<UsuarioHospital> getIfRegistred(String nombre, String apellidoP, String apellidoM, String telefono) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE nombre = ? AND apellidoP = ? AND apellidoM = ? AND telefono = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellidoP);
            stmt.setString(3, apellidoM);
            stmt.setString(4, telefono);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UsuarioHospital usuario = new UsuarioHospital();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidoPaterno(rs.getString("apellidoP"));
                    usuario.setApellidoMaterno(rs.getString("apellidoM"));
                    usuario.setTelefono(rs.getString("telefono"));
                    usuario.setGenero(rs.getString("genero"));
                    usuario.setTipo(rs.getString("tipo"));
                    return Optional.of(usuario);
                }
            }
        }
        return Optional.empty();
    }


}
