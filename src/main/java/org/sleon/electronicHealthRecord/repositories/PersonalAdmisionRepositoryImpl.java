package org.sleon.electronicHealthRecord.repositories;

import org.sleon.electronicHealthRecord.models.Administrador;
import org.sleon.electronicHealthRecord.models.PersonalAdmision;
import org.sleon.electronicHealthRecord.repositories.interfaces.UsuarioRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonalAdmisionRepositoryImpl implements UsuarioRepository<PersonalAdmision> {

    private Connection conn;

    public PersonalAdmisionRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void crear(PersonalAdmision personalAdmision) throws SQLException {
        String sql = "{CALL RegistrarPersonalAdmision(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, personalAdmision.getTelefono());
            stmt.setString(2, personalAdmision.getNombre());
            stmt.setString(3, personalAdmision.getApellidoMaterno());
            stmt.setString(4, personalAdmision.getApellidoPaterno());
            stmt.setString(5, personalAdmision.getGenero());
            stmt.setString(6, personalAdmision.getIdUsuario());
            stmt.setString(7, personalAdmision.getContrasenia());
            stmt.setLong(8, personalAdmision.getResponsableRegistro().getId());

            stmt.execute();
        }
    }

    @Override
    public List<PersonalAdmision> listar() throws SQLException {
        List<PersonalAdmision> personalAdmisionList = new ArrayList<>();

        String sql = """
        SELECT 
            u.id AS id,
            u.telefono,
            u.nombre,
            u.apellidoP,
            u.apellidoM,
            u.genero,

            ur.nombre AS responsable_nombre,
            ur.apellidoP AS responsable_apellidoP,
            ur.apellidoM AS responsable_apellidoM,
            ur.telefono AS responsable_telefono,
            pa.fechaRegistro

        FROM PERSONAL_ADMISION pa
        JOIN PERSONAL_HOSPITAL ph ON pa.id = ph.id
        JOIN USUARIO u ON ph.id = u.id

        JOIN USUARIO ur ON pa.responsableRegistro = ur.id;
    """;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PersonalAdmision personal = new PersonalAdmision();
                personal.setId(rs.getLong("id"));
                personal.setTelefono(rs.getString("telefono"));
                personal.setNombre(rs.getString("nombre"));
                personal.setApellidoPaterno(rs.getString("apellidoP"));
                personal.setApellidoMaterno(rs.getString("apellidoM"));
                personal.setGenero(rs.getString("genero"));
                personal.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());

                Administrador responsable = new Administrador();
                responsable.setNombre(rs.getString("responsable_nombre"));
                responsable.setApellidoPaterno(rs.getString("responsable_apellidoP"));
                responsable.setApellidoMaterno(rs.getString("responsable_apellidoM"));
                responsable.setTelefono(rs.getString("responsable_telefono"));
                personal.setResponsableRegistro(responsable);

                personalAdmisionList.add(personal);
            }
        }

        return personalAdmisionList;
    }

    @Override
    public List<PersonalAdmision> buscarPorNombre(String nombre) throws SQLException {
        return listar().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }

    @Override
    public Optional<PersonalAdmision> buscarPorId(Long id) throws SQLException {
        return listar().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public void actualizar(PersonalAdmision personalAdmision) throws SQLException {
        String sql = "{CALL ActualizarPersonalAdmision(?, ?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setLong(1, personalAdmision.getId());
            stmt.setString(2, personalAdmision.getTelefono());
            stmt.setString(3, personalAdmision.getNombre());
            stmt.setString(4, personalAdmision.getApellidoMaterno());
            stmt.setString(5, personalAdmision.getApellidoPaterno());
            stmt.setString(6, personalAdmision.getGenero());
            stmt.executeUpdate();
        }
    }
}
